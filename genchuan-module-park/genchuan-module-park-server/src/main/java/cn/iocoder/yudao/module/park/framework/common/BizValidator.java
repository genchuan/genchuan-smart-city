package cn.iocoder.yudao.module.park.framework.common;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
/**
 * 业务校验工具类（纯静态方法）
 * 说明：
 * 1. 只提供静态方法，禁止实例化和继承
 * 2. 用于业务校验，发现异常会抛出 ServiceException
 */
@Slf4j
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

    /**
     * 校验多个字段是否为 null
     * 使用示例：
     * validateNotNullValue("status", status, "startTime", startTime);
     *
     * 若存在多个字段为 null，统一抛出异常：
     * 字段不能为空：status, startTime
     */
    public static void validateNotNullValue(Object... fieldNameAndValues) {
        log.error("### validateNotNullValue version = 2026-02-12-01");



        // 1. 参数个数必须为偶数
        if (fieldNameAndValues == null || fieldNameAndValues.length == 0) {
            return;
        }
        if (fieldNameAndValues.length % 2 != 0) {
            throw exception(new ErrorCode(
                    500,
                    "validateNotNullValue 参数必须成对传入：字段名 + 字段值"
            ));
        }

        //为null的字段的名称列表
        List<String> nullFieldNames = new ArrayList<>();

        // 2. 遍历校验
        for (int i = 0; i < fieldNameAndValues.length; i += 2) {
            Object fieldNameObj = fieldNameAndValues[i];
            Object fieldValue = fieldNameAndValues[i + 1];

            // 字段名必须是 String
            if (!(fieldNameObj instanceof String)) {
                throw exception(new ErrorCode(
                        500,
                        "validateNotNullValue 第 " + i + " 个参数必须是 String 类型字段名"
                ));
            }

            String fieldName = ((String) fieldNameObj).trim();
            if (fieldName.isEmpty()) {
                continue;
            }

            // 值为 null，记录字段名
            if (fieldValue == null) {
                nullFieldNames.add(fieldName);
            }
        }

        // 3. 统一抛异常
        // 3. 统一抛异常（附带调用方法）
        if (!nullFieldNames.isEmpty()) {
            StackTraceElement caller = getCaller();

            String callerInfo = "";
            if (caller != null) {
                callerInfo = String.format(
                        "（调用方法：%s.%s:%d）",
                        caller.getClassName(),
                        caller.getMethodName(),
                        caller.getLineNumber()
                );
            }

            throw exception(new ErrorCode(
                    500,
                    "字段不能为空：" + String.join(", ", nullFieldNames) + " " + callerInfo
            ));
        }
    }

    private static StackTraceElement getCaller() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        boolean foundThisMethod = false;

        for (StackTraceElement element : stackTrace) {
            if (foundThisMethod) {
                return element;
            }
            if ("validateNotNullValue".equals(element.getMethodName())) {
                foundThisMethod = true;
            }
        }
        return null;
    }

}
