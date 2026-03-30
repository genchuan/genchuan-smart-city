package cn.iocoder.yudao.module.kitchen.framework.lxsutils.common.verify;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;

public final class VerifyUtil {

    /**
     * 校验多个对象是否为 null
     * 报错信息会提示第几个参数为 null
     */
    /**
     * 校验多个对象是否为 null
     * 报错信息会提示调用方法名 + 第几个参数为 null
     */
    public static void verifyNotNullSimple(Object... params) {
        List<Integer> nullIndexes = new LinkedList<>();

        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                nullIndexes.add(i + 1);
            }
        }

        if (!nullIndexes.isEmpty()) {
            // 获取调用方法名
            StackTraceElement[] stack = Thread.currentThread().getStackTrace();
            // stack[0] getStackTrace, stack[1] 当前方法, stack[2] 调用方法
            String methodName = stack.length > 2 ? stack[2].getMethodName() : "未知方法";

            StringBuilder sb = new StringBuilder();
            sb.append(methodName).append(" 方法里，第");
            for (int i = 0; i < nullIndexes.size(); i++) {
                sb.append(nullIndexes.get(i));
                if (i != nullIndexes.size() - 1) {
                    sb.append("、");
                }
            }
            sb.append("个参数不能为 null");

            throw exception(sb.toString());
        }
    }
    /**
     * 校验多个对象是否为 null
     *
     * 使用方法：
     * VerifyUtil.verifyNotNull(a, "a", b, "b", c, "c");
     *
     * @param paramsAndNames  参数和名称成对传入，如：a, "a", b, "b"
     * @throws IllegalArgumentException 如果有 null，会抛出异常，信息包含所有 null 的参数名
     */
    public static void verifyNotNull(Object... paramsAndNames) {
        if (paramsAndNames.length % 2 != 0) {
            throw new IllegalArgumentException("参数必须成对出现: 值, 名称");
        }

        List<String> nullNames = new ArrayList<>();

        for (int i = 0; i < paramsAndNames.length; i += 2) {
            Object value = paramsAndNames[i];
            String name = String.valueOf(paramsAndNames[i + 1]);

            if (value == null) {
                nullNames.add(name);
            }
        }

        if (!nullNames.isEmpty()) {
            throw exception(String.join("、", nullNames) + " 不能为 null");
        }
    }

    /**
     * 校验多个对象是否为 null,并且返回提示信息
     *
     * 使用方法：
     * VerifyUtil.verifyNotNull(a, "a", b, "b", c, "c");
     *
     * @param paramsAndNames  参数和名称成对传入，如：a, "a", b, "b"
     * @throws IllegalArgumentException 如果有 null，会抛出异常，信息包含所有 null 的参数名
     */
    public static void verifyNotNullWithMsg(Object... paramsAndNames) {
        if (paramsAndNames.length % 2 != 0) {
            throw new IllegalArgumentException("参数必须成对出现: 值, 名称");
        }

        List<String> nullMsgList = new ArrayList<>();

        for (int i = 0; i < paramsAndNames.length; i += 2) {
            Object value = paramsAndNames[i];
            String name = String.valueOf(paramsAndNames[i + 1]);

            if (value == null) {
                nullMsgList.add(name);
            }
        }

        if (!nullMsgList.isEmpty()) {
            throw exception(String.join("、", nullMsgList));
        }
    }
}
