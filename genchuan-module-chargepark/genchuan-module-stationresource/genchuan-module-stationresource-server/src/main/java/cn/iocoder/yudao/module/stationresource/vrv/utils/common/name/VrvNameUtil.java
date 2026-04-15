package cn.iocoder.yudao.module.stationresource.vrv.utils.common.name;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public final class VrvNameUtil {

    /** 日期格式 */
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyyMMdd");

    /**
     * 生成整改编号[通用]
     * 示例：RECTIFY20260314000001
     */
    public static String generateCode() {

        String codePrefix="COMCODE";

        return generateCode(codePrefix);
    }

    /**
     * 生成整改编号[自定义前缀]
     * 示例：RECTIFY20260314000001
     */
    public static String generateCode(String codePrefix) {

        String date = LocalDate.now().format(DATE_FORMAT);
        return codePrefix + date + UUID.randomUUID().toString().substring(0, 8);
    }
}
