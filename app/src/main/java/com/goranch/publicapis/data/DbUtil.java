package com.goranch.publicapis.data;

/**
 * Created by goran on 19/05/2017.
 */

public class DbUtil {
    public static String strSeparator = "__,__";

    public static String convertArrayToString(String[] array) {
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            str.append(array[i]);
            // Do not append comma at the end of last element
            if (i < array.length - 1) {
                str.append(strSeparator);
            }
        }
        return str.toString();
    }

    public static String[] convertStringToArray(String str) {
        String[] arr = str.split(strSeparator);
        return arr;
    }
}
