package cn.iocoder.yudao.module.vehiclepass.constants.leavemgmt;

/**
 * 缴费核验相关常量
 *
 * @author system
 */
public class PayCheckConstants {

    // ==================== 状态常量 ====================
    /** 状态：已缴清 */
    public static final String STATUS_PAID = "已缴清";

    /** 状态：欠费 */
    public static final String STATUS_ARREARS = "欠费";

    private PayCheckConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
