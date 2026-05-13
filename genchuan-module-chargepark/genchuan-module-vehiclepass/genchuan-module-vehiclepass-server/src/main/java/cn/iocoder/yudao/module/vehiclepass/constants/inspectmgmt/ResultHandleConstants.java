package cn.iocoder.yudao.module.vehiclepass.constants.inspectmgmt;

/**
 * 结果处理相关常量
 *
 * @author system
 */
public class ResultHandleConstants {

    // ==================== 状态常量 ====================
    /** 状态：待处置 */
    public static final String STATUS_PENDING_HANDLE = "待处置";

    /** 状态：已完成 */
    public static final String STATUS_COMPLETED = "已完成";

    /** 状态：已驳回 */
    public static final String STATUS_REJECTED = "已驳回";

    private ResultHandleConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
