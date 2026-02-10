package cn.iocoder.yudao.module.park.framework.common;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
/**
 * 业务校验工具类（纯静态方法）
 * 说明：
 * 1. 只提供静态方法，禁止实例化和继承
 * 2. 用于业务校验，发现异常会抛出 ServiceException
 */
public final class BizValidator {

    private BizValidator() {
    }



    /**
     * 校验对象是否为 null，支持多个对象交错传参
     * 使用示例：
     * validateNotNull(couponDO, "优惠券不存在", orderTempDO, "订单不存在");
     *
     * @param objsAndMsgs 对象与提示信息交错传入，偶数索引为对象，奇数索引为错误信息
     */
    public static void validateNotNull(Object... objsAndMsgs) {
        // 校验长度必须为偶数
        if (objsAndMsgs.length % 2 != 0) {
            throw exception(new ErrorCode(500,
                    "validateNotNull 参数必须成对传入：对象 + 错误提示"));
        }

        for (int i = 0; i < objsAndMsgs.length; i += 2) {
            Object obj = objsAndMsgs[i];
            Object msgObj = objsAndMsgs[i + 1];

            // 校验奇数位是否为 String
            if (!(msgObj instanceof String)) {
                throw exception(new ErrorCode(500,
                        "validateNotNull 第 " + (i + 1) + " 个参数必须是 String 类型，用于错误提示, 当前值类型: "
                                + (msgObj == null ? "null" : msgObj.getClass().getName()) + ", 值: " + msgObj)
                );
            }

            String msg = (String) msgObj;

            // 如果对象为空，抛异常，同时显示第几个对象 + 提示信息
            if (obj == null) {
                throw exception(new ErrorCode(500, msg));
            }
        }
    }
}
