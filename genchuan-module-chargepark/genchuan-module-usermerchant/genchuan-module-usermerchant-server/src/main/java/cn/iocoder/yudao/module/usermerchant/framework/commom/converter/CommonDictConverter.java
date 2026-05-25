package cn.iocoder.yudao.module.usermerchant.framework.commom.converter;

import cn.idev.excel.converters.Converter;
import cn.idev.excel.enums.CellDataTypeEnum;
import cn.idev.excel.metadata.GlobalConfiguration;
import cn.idev.excel.metadata.data.ReadCellData;
import cn.idev.excel.metadata.data.WriteCellData;
import cn.idev.excel.metadata.property.ExcelContentProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * 通用 Excel 字典转换器
 * 根据字段名自动将数字映射为中文说明
 * 目前支持：
 * - changeType : 1->获取, 2->消耗
 * - status     : 0->异常, 1->正常
 */
public class CommonDictConverter implements Converter<Integer> {

    // 定义各字段的映射关系
    private static final Map<String, Map<Integer, String>> FIELD_DICT = new HashMap<>();

    static {
        // changeType 字段的映射
        Map<Integer, String> changeTypeMap = new HashMap<>();
        changeTypeMap.put(1, "获取");
        changeTypeMap.put(2, "消耗");
        FIELD_DICT.put("changeType", changeTypeMap);

        // status 字段的映射
        Map<Integer, String> statusMap = new HashMap<>();
        statusMap.put(0, "异常");
        statusMap.put(1, "正常");
        FIELD_DICT.put("status", statusMap);
    }

    @Override
    public Class<Integer> supportJavaTypeKey() {
        return Integer.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public WriteCellData<String> convertToExcelData(Integer value, ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        // 获取当前字段的名称（Java 属性名）
        String fieldName = contentProperty.getField().getName();
        Map<Integer, String> dict = FIELD_DICT.get(fieldName);
        String display;
        if (dict != null) {
            display = dict.getOrDefault(value, String.valueOf(value));
        } else {
            display = String.valueOf(value);
        }
        return new WriteCellData<>(display);
    }

    @Override
    public Integer convertToJavaData(ReadCellData<?> cellData, ExcelContentProperty contentProperty,
                                     GlobalConfiguration globalConfiguration) {
        // 导入时不需要转换，直接返回 null（本业务未使用导入功能）
        return null;
    }
}