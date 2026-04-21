// 路径: framework/excel/EnumExcelConverter.java
package cn.iocoder.yudao.module.ordertrade.framework.excel;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

import java.lang.reflect.Field;

/**
 * 通用枚举转换器，配合 @EnumFormat 注解使用
 */
public class EnumExcelConverter implements Converter<String> {

    @Override
    public Class<String> supportJavaTypeKey() {
        return String.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public String convertToJavaData(ReadCellData<?> cellData,
                                    ExcelContentProperty contentProperty,
                                    GlobalConfiguration globalConfiguration) {
        return cellData.getStringValue();
    }

    @Override
    public WriteCellData<String> convertToExcelData(String value,
                                                    ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) throws Exception {
        if (value == null) return new WriteCellData<>("");

        Field field = contentProperty.getField();
        EnumFormat annotation = field.getAnnotation(EnumFormat.class);
        if (annotation == null) return new WriteCellData<>(value);

        // 通过反射调用枚举的 labelOf 方法
        Class<?> enumClass = annotation.value();
        try {
            java.lang.reflect.Method method = enumClass.getMethod("labelOf", String.class);
            String label = (String) method.invoke(null, value);
            return new WriteCellData<>(label != null ? label : value);
        } catch (Exception e) {
            return new WriteCellData<>(value);
        }
    }
}
