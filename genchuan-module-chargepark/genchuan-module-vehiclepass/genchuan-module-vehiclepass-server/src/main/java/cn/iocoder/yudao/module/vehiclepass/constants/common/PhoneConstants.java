package cn.iocoder.yudao.module.vehiclepass.constants.common;

/**
 * 手机号相关常量
 *
 * @author system
 */
public class PhoneConstants {

    /** 手机号标准长度 */
    public static final int PHONE_LENGTH = 11;

    /** 手机号脱敏起始位置 */
    public static final int MASK_START = 3;

    /** 手机号脱敏结束位置 */
    public static final int MASK_END = 7;

    /** 手机号正则：1开头的11位数字 */
    public static final String PHONE_REGEX = "^1[3-9]\\d{9}$";

    /** 手机号格式错误提示 */
    public static final String PHONE_REGEX_MESSAGE = "手机号格式不正确";

    private PhoneConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
