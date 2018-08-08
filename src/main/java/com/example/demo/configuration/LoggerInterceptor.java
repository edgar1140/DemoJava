package com.example.demo.configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.google.gson.*;

/**
 * Logging handler for various classes.
 * Spring AOP reference http://docs.spring.io/spring/docs/current/spring-framework-reference/html/aop.html
 *
 * @author jray (2016.04.08 11:59 AM)
 */
@Aspect
@Component
public class LoggerInterceptor {
    public static final Level LOG_PARAMETERS_LEVEL = Level.DEBUG;
    public static final Level LOG_TIME_LEVEL = Level.INFO;
    public static final String START_MESSAGE = "{}(..) Start";
    public static final String START_PARAMETERS_MESSAGE = "{}(..) Start data: {}";
    public static final int SMALL_ARGUMENT_LOGGING_SIZE = 1000;
    public static final int LARGE_ARGUMENT_LOGGING_SIZE = 50000;
    public static final String END_MESSAGE = "{}(..) End Run Time: {} ms";
    public static final String END_PARAMETERS_MESSAGE = "{}(..) End Run Time: {} ms data: {}";
    private static final Logger LOG = LogManager.getLogger(LoggerInterceptor.class);
    private static final Gson GSON = configureLoggingGson();
    private static final Pattern LOG_AS_JSON_PATTERN = Pattern.compile("^java.lang.|.*[eE]xception.*" +
        "|^org.springframework.http.ResponseEntity");

    @Around("(execution(* com.example.demo.api.*.*(..))" +
        "&& !execution(* com.example.demo.api.RootController.*(..)))")
    public Object logAroundApi(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logAround(proceedingJoinPoint, true, true, LARGE_ARGUMENT_LOGGING_SIZE);
    }

    @Around("execution(* com.example.demo.agent.*.*(..))")
    public Object logAroundAgent(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logAround(proceedingJoinPoint, true, false, SMALL_ARGUMENT_LOGGING_SIZE);
    }

//    @Around("execution(* com.example.demo.service.*.*(..))")
//    public Object logAroundService(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
//        return logAround(proceedingJoinPoint, true, false, SMALL_ARGUMENT_LOGGING_SIZE);
//    }

    // !!!! Exclude SecurityDao from the possibility of logging parameters.
    @Around("execution(* com.example.demo.dao.*.*(..)))")
    public Object logAroundDao(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        return logAround(proceedingJoinPoint, true, false, SMALL_ARGUMENT_LOGGING_SIZE);
    }

    private Object logAround(ProceedingJoinPoint proceedingJoinPoint, boolean logParameters, boolean logResult, int maxLogObjectSize) throws Throwable {
        final String shortSignature;
        final long startTime;
        final String wrappedClassName = proceedingJoinPoint.getSignature().getDeclaringTypeName();
        final Logger wrappedClassLog = LogManager.getLogger(wrappedClassName);
        final Level wrappedClassLogLevel = wrappedClassLog.getLevel();

        if (logParameters && wrappedClassLog.isEnabled(LOG_PARAMETERS_LEVEL)) {
            shortSignature = proceedingJoinPoint.getSignature().getName();
            final Object[] argumentArray = proceedingJoinPoint.getArgs();
            final String logString = getArgumentsAsString(maxLogObjectSize, argumentArray);
            wrappedClassLog.log(wrappedClassLogLevel, START_PARAMETERS_MESSAGE, shortSignature, logString);
            startTime = System.currentTimeMillis();
        } else if (wrappedClassLog.isEnabled(LOG_TIME_LEVEL)) {
            shortSignature = proceedingJoinPoint.getSignature().getName();
            wrappedClassLog.log(wrappedClassLogLevel, START_MESSAGE, shortSignature);
            startTime = System.currentTimeMillis();
        } else {
            shortSignature = null;
            startTime = 0;
        }

        Object ret = null;
        try {
            ret = proceedingJoinPoint.proceed();
        } finally {
            if (logResult && wrappedClassLog.isEnabled(LOG_PARAMETERS_LEVEL)) {
                final long endTime = System.currentTimeMillis();
                final long runTime = endTime - startTime;
                final String logString = getArgumentsAsString(maxLogObjectSize, ret);
                wrappedClassLog.log(wrappedClassLogLevel, END_PARAMETERS_MESSAGE, shortSignature, runTime, logString);
            } else if (wrappedClassLog.isEnabled(LOG_TIME_LEVEL)) {
                final long endTime = System.currentTimeMillis();
                final long runTime = endTime - startTime;
                wrappedClassLog.log(wrappedClassLogLevel, END_MESSAGE, shortSignature, runTime);
            }
        }
        return ret;
    }

    public static String getArgumentsAsString(int sizeLimit, Object... argumentArray) {
        final List<String> argumentList = new ArrayList<>(argumentArray.length);
        try {
            if (argumentArray.length > 0) {
                for (Object arg : argumentArray) {
                    if (arg == null) {
                        argumentList.add(null);
                    } else {
                        final boolean logAsJson = LOG_AS_JSON_PATTERN.matcher(arg.getClass().getName()).find();
                        final String argString;
                        if (logAsJson) {
                            argString = getGsonForArgument(arg);
                        } else {
                            argString = arg.toString();
                        }
                        final String argStringFinal;
                        final int argStringLength = argString.length();
                        if (sizeLimit == 0) {
                            argStringFinal = argString;
                        } else if (argStringLength >= sizeLimit) {
                            argStringFinal = argString.substring(0, sizeLimit - 1);
                        } else {
                            argStringFinal = argString;
                        }
                        argumentList.add(argStringFinal);
                    }
                }
            }
        } catch (Throwable t) {
            LOG.error("Error getting string to log.", t);
        }
        return argumentList.toString();
    }

    private static String getGsonForArgument(Object arg) {
        try {
            return GSON.toJson(arg);
        } catch (Throwable t) {
            return arg.getClass().getSimpleName();
        }
    }

    public static Gson configureLoggingGson() {
        final String dateFormat = StdDateFormat.DATE_FORMAT_STR_ISO8601;//"yyyy-MM-dd'T'HH:mm:ss.SSSZ"

        final ExclusionStrategy exclusionStrategy = new ExclusionStrategy() {
            @Override
            public boolean shouldSkipField(FieldAttributes f) {
                return false;
            }

            @Override
            public boolean shouldSkipClass(Class<?> clazz) {
                return false;
            }
        };

        // Log a placeholder if a byte array is not null
        final JsonSerializer<byte[]> byteArraySerializer = (src, typeOfSrc, context) -> {
            final JsonObject jsonObject;
            if (src == null) {
                jsonObject = null;
            } else {
                jsonObject = new JsonObject();
                jsonObject.addProperty("byte[]", "value not null, but logging value not allowed");
            }
            return jsonObject;
        };

        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(byte[].class, byteArraySerializer);
        gsonBuilder.serializeNulls();
        gsonBuilder.setDateFormat(dateFormat);
        gsonBuilder.addSerializationExclusionStrategy(exclusionStrategy);

        return gsonBuilder.create();
    }
}
