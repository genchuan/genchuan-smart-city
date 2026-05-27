package cn.iocoder.yudao.module.usermerchant.framework.commom.utils;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.usermerchant.framework.annotation.ImportRequired;
import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;

import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.EMPTY_LIST;

public class ImportValidator {

    /**
     * 校验单行导入数据，检查所有标记了 @ImportRequired 的字段是否非空
     * @param obj         要校验的对象
     * @param rowNum      行号（用于错误提示，从1开始，通常是Excel行号）
     * @throws ServiceException 当必填字段为空时抛出
     */
    public static void validateRequiredFields(Object obj, int rowNum) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(ImportRequired.class)) {
                field.setAccessible(true);
                try {
                    Object value = field.get(obj);
                    // 判断是否为空：null 或 空字符串
                    if (value == null) {
                        throw new ServiceException(EMPTY_LIST);
                    }
                    if (value instanceof String && StrUtil.isBlank((String) value)) {
                        throw new ServiceException(EMPTY_LIST);
                    }
                    // 其他类型（数字、日期等）只要不为 null 即视为有值
                } catch (IllegalAccessException e) {
                    throw new ServiceException(EMPTY_LIST);
                }
            }
        }
    }
}