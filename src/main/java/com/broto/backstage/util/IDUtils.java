package com.broto.backstage.util;

import org.bson.types.ObjectId;

import java.util.UUID;

/**
 * Created by yitao on 2016/9/28.
 */
public class IDUtils {

    public static String genUUID(){
        return UUID.randomUUID().toString();
    }

    public static String genUUID32(){
        return UUID.randomUUID().toString().replaceAll("-","");
    }

    public static String genObjectId(){
        return new ObjectId().toHexString();
    }

}
