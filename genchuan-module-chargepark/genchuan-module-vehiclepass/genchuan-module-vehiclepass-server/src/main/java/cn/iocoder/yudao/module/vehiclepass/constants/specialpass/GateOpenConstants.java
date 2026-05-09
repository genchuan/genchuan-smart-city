package cn.iocoder.yudao.module.vehiclepass.constants.specialpass;

/**
 * 开闸申请相关常量
 *
 * @author system
 */
public class GateOpenConstants {

    // ==================== 状态常量 ====================
    /** 状态：待审批 */
    public static final String STATUS_PENDING_APPROVAL = "待审批";

    /** 状态：已通过 */
    public static final String STATUS_APPROVED = "已通过";

    /** 状态：已驳回 */
    public static final String STATUS_REJECTED = "已驳回";

    /** 状态：已执行 */
    public static final String STATUS_EXECUTED = "已执行";

    private GateOpenConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
