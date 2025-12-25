package cn.iocoder.yudao.module.industry.framework.util.lxs;

/**
 * 地区工具类
 * <p>
 * 功能：
 * 1. 根据地区码（6位或12位）判断地区级别
 * 2. 级别定义：
 *    1 - 省级
 *    2 - 市级
 *    3 - 县级
 *    4 - 街道级
 *    5 - 社区级
 * 3. 格式非法时返回 null
 */
public class RegionUtils {

    /**
     * 获取地区级别
     *
     * @param code 地区码，6位或12位数字
     * @return 级别：1-省，2-市，3-县，4-街道，5-社区；格式不合法返回 null
     */
    public static Integer getRegionLevel(String code) {
        if (code == null) return null;

        // 校验长度和数字格式，必须是 6 位或 12 位数字
        if (!code.matches("\\d{6}|\\d{12}")) {
            return null; // 非法格式
        }

        int length = code.length();

        // 前6位决定省/市/县
        String province = code.substring(0, 2);
        String city = code.substring(2, 4);
        String county = code.substring(4, 6);

        if (length == 6) {
            // 6位码只判断省市县
            if ("00".equals(city)) return 1;   // 省级
            if ("00".equals(county)) return 2; // 市级
            return 3;                           // 县级
        } else {
            // 12位码判断街道/社区
            String street = code.substring(6, 9);
            String community = code.substring(9, 12);

            if ("00".equals(city)) return 1;         // 省级
            if ("00".equals(county)) return 2;       // 市级
            if ("000".equals(street)) return 3;      // 县级
            if ("000".equals(community)) return 4;   // 街道级
            return 5;                                // 社区级
        }
    }

    /**
     * 获取地区码有效位数
     *
     * 规则：
     * 6位码：
     *   省级 -> 2
     *   市级 -> 4
     *   县级 -> 6
     * 12位码：
     *   省级 -> 2
     *   市级 -> 4
     *   县级 -> 6
     *   街道 -> 9
     *   社区 -> 12
     *
     * @param code 6位或12位地区码
     * @return 有效位数；格式非法返回 null
     */
    public static Integer getEffectiveLength(String code) {
        Integer level = getRegionLevel(code);
        if (level == null) return null;
        switch (level) {
            case 1 -> { return 2; }   // 省
            case 2 -> { return 4; }   // 市
            case 3 -> { return 6; }   // 县
            case 4 -> { return 9; }   // 街道
            case 5 -> { return 12; }  // 社区
            default -> { return null; }
        }
    }

    /**
     * 通过地区等级 获取地区码有效位数
     *
     * 规则：
     * 6位码：
     *   省级 -> 2
     *   市级 -> 4
     *   县级 -> 6
     * 12位码：
     *   省级 -> 2
     *   市级 -> 4
     *   县级 -> 6
     *   街道 -> 9
     *   社区 -> 12
     *
     * @param level 地区等级
     * @return 有效位数；格式非法返回 null
     */
    public static Integer getEffectiveLengthByLevel(Integer level) {
        if (level == null) return null;
        switch (level) {
            case 1 -> { return 2; }   // 省
            case 2 -> { return 4; }   // 市
            case 3 -> { return 6; }   // 县
            case 4 -> { return 9; }   // 街道
            case 5 -> { return 12; }  // 社区
            default -> { return null; }
        }
    }

    public static void main(String[] args) {
        // 测试地区码，包括合法6位/12位码，以及非法码
        String[] codes = {
                "110000",         // 省
                "110100",         // 市
                "110101",         // 县
                "110000000000",   // 省
                "110100000000",   // 市
                "110101000000",   // 县
                "110101001000",   // 街道
                "110101001001",   // 社区
                "abc",            // 非法
                "1101",           // 非法
                "110101001",      // 非法
                null              // 非法
        };

        for (String code : codes) {
            Integer level = getRegionLevel(code);
            Integer effLength = getEffectiveLength(code);

            String levelDesc;
            if (level == null) {
                levelDesc = "非法";
            } else {
                switch (level) {
                    case 1 -> levelDesc = "省";
                    case 2 -> levelDesc = "市";
                    case 3 -> levelDesc = "县";
                    case 4 -> levelDesc = "街道";
                    case 5 -> levelDesc = "社区";
                    default -> levelDesc = "未知";
                }
            }

            System.out.printf("地区码: %s -> 级别: %s (%s), 有效位数: %s%n",
                    code, level, levelDesc, effLength);
        }
    }


}
