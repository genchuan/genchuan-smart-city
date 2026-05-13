package cn.iocoder.yudao.module.vehiclepass.constants.inparkmgmt;

/**
 * 油车处理相关常量
 *
 * @author system
 */
public class OilCarHandleConstants {

    // ==================== 状态常量 ====================
    /** 状态：处理中 */
    public static final String STATUS_PROCESSING = "处理中";

    /** 状态：已关闭 */
    public static final String STATUS_CLOSED = "已关闭";

    // ==================== 处理类型常量 ====================
    /** 处理类型：处置 */
    public static final String HANDLE_TYPE_DISPOSE = "处置";

    /** 处理类型：忽略 */
    public static final String HANDLE_TYPE_IGNORE = "忽略";

    // ==================== 处理方法常量 ====================
    /** 处理方法：已处置 */
    public static final String HANDLE_METHOD_DISPOSED = "已处置";

    // ==================== 忽略原因常量 ====================
    /** 忽略原因：批量忽略 */
    public static final String IGNORE_REASON_BATCH = "批量忽略";

    private OilCarHandleConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
