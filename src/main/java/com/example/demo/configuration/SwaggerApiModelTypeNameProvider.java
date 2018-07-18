package com.example.demo.configuration;

import static com.google.common.base.Strings.emptyToNull;
import static org.springframework.core.annotation.AnnotationUtils.findAnnotation;

import java.util.Optional;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import io.swagger.annotations.ApiModel;
import springfox.documentation.schema.DefaultTypeNameProvider;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.common.SwaggerPluginSupport;

/**
 * This class is used to help Springfox Swagger auto API documentation.
 * It uses the full path name of classes instead of the simple name in order to avoid documentation conflicts if classes have the same simple name.
 * - e.g. -
 * Given two classes used on separate endpoints:
 * -
 * com.cspire.inventory.model.internal.response.carrierlock.GetStatus
 * com.cspire.inventory.model.internal.response.preorder.GetStatus
 * -
 * Springfox would use the simple class name for the Swagger documentation page.
 * "GetStatus"
 * The documentation web page would fail for one of these as the model location is flattened without reference to package structure or endpoint names.
 * This class allows us to avoid model name collision/overwrite.
 * It also allows us to still use the @ApiModel annotation as normal if special naming is needed.
 *
 * @author jray (2018.04.12 10:23 AM)
 */
@Component
@Order(SwaggerPluginSupport.SWAGGER_PLUGIN_ORDER - 1)
public class SwaggerApiModelTypeNameProvider extends DefaultTypeNameProvider {
    @Override
    public String nameFor(Class<?> type) {
        final ApiModel annotation = findAnnotation(type, ApiModel.class);
        final String defaultTypeName = type.getName();
        if (annotation != null) {
            return Optional.ofNullable(emptyToNull(annotation.value())).orElse(defaultTypeName);
        }
        return defaultTypeName;
    }

    @Override
    public boolean supports(DocumentationType delimiter) {
        return SwaggerPluginSupport.pluginDoesApply(delimiter);
    }
}