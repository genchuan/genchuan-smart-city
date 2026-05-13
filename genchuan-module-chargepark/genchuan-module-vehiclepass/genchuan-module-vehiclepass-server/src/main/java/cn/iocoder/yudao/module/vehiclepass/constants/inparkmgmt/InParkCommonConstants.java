package cn.iocoder.yudao.module.vehiclepass.constants.inparkmgmt;

/**
 * 在场管理通用常量
 *
 * @author system
 */
public class InParkCommonConstants {

    // ==================== 通用状态常量 ====================
    /** 状态：待处理 */
    public static final String STATUS_PENDING = "待处理";

    /** 状态：处理中 */
    public static final String STATUS_PROCESSING = "处理中";

    /** 状态：已关闭 */
    public static final String STATUS_CLOSED = "已关闭";

    private InParkCommonConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
