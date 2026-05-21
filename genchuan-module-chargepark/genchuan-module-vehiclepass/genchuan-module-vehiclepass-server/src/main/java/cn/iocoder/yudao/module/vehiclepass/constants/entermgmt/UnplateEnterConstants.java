package cn.iocoder.yudao.module.vehiclepass.constants.entermgmt;

/**
 * 无牌车入场相关常量
 *
 * @author system
 */
public class UnplateEnterConstants {

    // ==================== 状态常量 ====================
    /** 状态：待审核 */
    public static final String STATUS_PENDING_REVIEW = "待审核";

    /** 状态：已通过 */
    public static final String STATUS_APPROVED = "通过";

    /** 状态：已入场 */
    public static final String STATUS_ENTERED = "已入场";

    private UnplateEnterConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
