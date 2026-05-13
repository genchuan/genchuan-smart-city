package cn.iocoder.yudao.module.vehiclepass.constants.common;

import java.math.RoundingMode;

/**
 * 计算相关常量
 *
 * @author system
 */
public class CalculationConstants {

    /** 百分比计算因子 */
    public static final int PERCENTAGE_FACTOR = 100;

    /** 百分比精确计算因子（用于保留两位小数的百分比） */
    public static final double PERCENTAGE_PRECISE_FACTOR = 10000.0;

    /** 百分比精确计算除数 */
    public static final double PERCENTAGE_PRECISE_DIVISOR = 100.0;

    /** 毫秒转秒因子 */
    public static final int MILLIS_TO_SECONDS = 1000;

    /** 默认小数精度 */
    public static final int DEFAULT_SCALE = 2;

    /** 默认舍入模式：四舍五入 */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.HALF_UP;

    /** 默认置信度 */
    public static final String DEFAULT_CONFIDENCE = "0.00";

    private CalculationConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
