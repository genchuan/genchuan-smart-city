package cn.iocoder.yudao.module.datacenter.framework.util;

import java.util.UUID;

public class UuidUtils {

    /**
     * 生成UUID（不带横线，小写）
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 生成UUID（带横线，小写）
     */
    public static String generateUUIDWithHyphen() {
        return UUID.randomUUID().toString().toLowerCase();
    }

    /**
     * 生成UUID（不带横线，大写）
     */
    public static String generateUUIDUpperCase() {
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }
}
