package cn.iocoder.yudao.module.vehiclepass.constants.leavemgmt;

/**
 * 结束停车相关常量
 *
 * @author system
 */
public class EndParkConstants {

    // ==================== 状态常量 ====================
    /** 状态：已支付 */
    public static final String STATUS_PAID = "已支付";

    /** 状态：已确认 */
    public static final String STATUS_CONFIRMED = "已确认";

    /** 状态：已取消 */
    public static final String STATUS_CANCELLED = "已取消";

    private EndParkConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
