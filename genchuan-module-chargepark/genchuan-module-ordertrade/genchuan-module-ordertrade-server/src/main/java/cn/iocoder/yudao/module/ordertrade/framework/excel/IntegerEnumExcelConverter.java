package cn.iocoder.yudao.module.ordertrade.framework.excel;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

import java.lang.reflect.Field;

/**
 * Integer 类型枚举转换器，配合 @IntegerEnumFormat 注解使用
 */
public class IntegerEnumExcelConverter implements Converter<Integer> {

    @Override
    public Class<Integer> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData,
                                     ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        String s = cellData.getStringValue();
        if (s == null || s.isEmpty()) return null;
        try { return Integer.parseInt(s); } catch (NumberFormatException e) { return null; }
    }

    @Override
    public WriteCellData<String> convertToExcelData(Integer value,
                                                     ExcelContentProperty contentProperty,
                                                     GlobalConfiguration globalConfiguration) throws Exception {
        if (value == null) return new WriteCellData<>("");
        Field field = contentProperty.getField();
        IntegerEnumFormat annotation = field.getAnnotation(IntegerEnumFormat.class);
        if (annotation == null) return new WriteCellData<>(String.valueOf(value));
        Class<?> enumClass = annotation.value();
        try {
            java.lang.reflect.Method method = enumClass.getMethod("labelOf", Integer.class);
            String label = (String) method.invoke(null, value);
            return new WriteCellData<>(label != null ? label : String.valueOf(value));
        } catch (Exception e) {
            return new WriteCellData<>(String.valueOf(value));
        }
    }
}
