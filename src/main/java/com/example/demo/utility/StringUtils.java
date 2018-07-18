package com.example.demo.utility;

public class StringUtils {
   public static boolean isEmpty(String str) {
       return str == null || str.trim().isEmpty();
   }
   public static boolean isNotEmpty(String str) {
       return !isEmpty(str);
   }
}