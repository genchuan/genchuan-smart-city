package cn.iocoder.yudao.module.vehiclepass.constants.entermgmt;

/**
 * 车辆入场申请相关常量
 *
 * @author system
 */
public class CarInputConstants {

    // ==================== 状态常量 ====================
    /** 状态：待审核 */
    public static final String STATUS_PENDING_REVIEW = "待审核";

    /** 状态：已通过 */
    public static final String STATUS_APPROVED = "已通过";

    /** 状态：已驳回 */
    public static final String STATUS_REJECTED = "已驳回";

    // ==================== 审核结果常量 ====================
    /** 审核结果：通过 */
    public static final String AUDIT_RESULT_PASS = "通过";

    /** 审核结果：驳回 */
    public static final String AUDIT_RESULT_REJECT = "驳回";

    private CarInputConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
