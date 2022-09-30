package com.liu.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "").substring(0,6);
    }

    public static String createToken(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }
}
