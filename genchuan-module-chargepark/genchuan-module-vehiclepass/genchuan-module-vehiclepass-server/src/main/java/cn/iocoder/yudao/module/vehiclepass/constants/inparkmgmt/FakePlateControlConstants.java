package cn.iocoder.yudao.module.vehiclepass.constants.inparkmgmt;

/**
 * 套牌车控制相关常量
 *
 * @author system
 */
public class FakePlateControlConstants {

    // ==================== 状态常量 ====================
    /** 状态：处理中 */
    public static final String STATUS_PROCESSING = "处理中";

    /** 状态：已关闭 */
    public static final String STATUS_CLOSED = "已关闭";

    // ==================== 处理类型常量 ====================
    /** 处理类型：核查 */
    public static final String HANDLE_TYPE_CHECK = "核查";

    /** 处理类型：忽略 */
    public static final String HANDLE_TYPE_IGNORE = "忽略";

    // ==================== 处理进度常量 ====================
    /** 处理进度：已核查 */
    public static final String HANDLE_PROGRESS_CHECKED = "已核查";

    /** 处理进度：已忽略 */
    public static final String HANDLE_PROGRESS_IGNORED = "已忽略";

    // ==================== 忽略原因常量 ====================
    /** 忽略原因：批量忽略 */
    public static final String IGNORE_REASON_BATCH = "批量忽略";

    private FakePlateControlConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
