package cn.iocoder.yudao.module.vehiclepass.constants.inspectmgmt;

/**
 * 巡检任务相关常量
 *
 * @author system
 */
public class CheckTaskConstants {

    // ==================== 状态常量 ====================
    /** 状态：待认领 */
    public static final String STATUS_PENDING_CLAIM = "待认领";

    /** 状态：处理中 */
    public static final String STATUS_PROCESSING = "处理中";

    /** 状态：已完成 */
    public static final String STATUS_COMPLETED = "已完成";
    /** 状态：已归档 */
    public static final String STATUS_ARCHIVED = "已归档";

    private CheckTaskConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
