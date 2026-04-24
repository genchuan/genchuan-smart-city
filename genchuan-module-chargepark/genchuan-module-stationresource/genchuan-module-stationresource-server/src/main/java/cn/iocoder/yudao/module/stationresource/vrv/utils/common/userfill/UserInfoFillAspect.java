package cn.iocoder.yudao.module.stationresource.vrv.utils.common.userfill;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;

@Aspect
@Component
public class UserInfoFillAspect {

    private final JdbcTemplate jdbcTemplate;

    public UserInfoFillAspect(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Around("@within(fillUserInfo)")
    public Object fill(ProceedingJoinPoint joinPoint, FillUserInfo fillUserInfo) throws Throwable {
        Object result = joinPoint.proceed();
        handleResult(result, fillUserInfo);
        return result;
    }

    private void handleResult(Object result, FillUserInfo fillUserInfo) {
        if (result == null) return;

        if (result instanceof CommonResult<?> commonResult) {
            Object data = commonResult.getData();
            handleData(data, fillUserInfo);
            return;
        }

        handleData(result, fillUserInfo);
    }

    private void handleData(Object data, FillUserInfo fillUserInfo) {
        if (data == null) return;

        if (data instanceof PageResult<?> pageResult) {
            List<?> list = pageResult.getList();
            list.forEach(item -> fillItem(item, fillUserInfo));
            return;
        }

        if (data instanceof Collection<?> list) {
            list.forEach(item -> fillItem(item, fillUserInfo));
            return;
        }

        fillItem(data, fillUserInfo);
    }

    // 🔥🔥🔥 核心逻辑：只有 String 类型 + 纯数字 才替换
    private void fillItem(Object item, FillUserInfo fillUserInfo) {
        if (item == null) return;

        try {
            // 处理 creator
            Field creatorField = item.getClass().getDeclaredField("creator");
            Object creatorVal = getFieldValue(creatorField, item);
            if (isStringNumber(creatorVal)) {
                setValue(creatorField, item, "亘川");
            }

            // 处理 updater
            Field updaterField = item.getClass().getDeclaredField("updater");
            Object updaterVal = getFieldValue(updaterField, item);
            if (isStringNumber(updaterVal)) {
                setValue(updaterField, item, "亘川");
            }
        } catch (Exception ignored) {}
    }

    // ✅ 判断：是否是 String 类型，并且内容是纯数字
    private boolean isStringNumber(Object value) {
        if (value == null) return false;
        if (!(value instanceof String str)) return false; // 必须是String
        return str.matches("\\d+"); // 纯数字正则：0-9
    }

    // 获取字段值
    private Object getFieldValue(Field field, Object item) throws IllegalAccessException {
        if (field == null) return null;
        field.setAccessible(true);
        return field.get(item);
    }

    // 设置字段值
    private void setValue(Field field, Object item, Object value) throws IllegalAccessException {
        if (field == null) return;
        field.setAccessible(true);
        field.set(item, value);
    }
}
