package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo;

import cn.iocoder.yudao.framework.excel.core.annotations.DictFormat;
import cn.iocoder.yudao.framework.excel.core.convert.DictConvert;
import cn.iocoder.yudao.module.chargepark.marketop.enums.DictTypeConstants;
import lombok.Data;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import cn.idev.excel.annotation.ExcelProperty;

/**
 * 积分活动 Excel 导入 VO
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PointActivityImportExcelVO {

    @ExcelProperty("活动名称")
    private String name;

    @ExcelProperty(value = "活动类型", converter = DictConvert.class)
    @DictFormat(DictTypeConstants.POINT_ACTIVITY_TYPE)
    private String type;

    @ExcelProperty("开始时间")
    private String startTime;

    @ExcelProperty("结束时间")
    private String endTime;

    @ExcelProperty("积分规则")
    private String rule;

    @ExcelProperty("活动描述")
    private String description;

    @ExcelProperty("适用场站(多个用逗号分隔)")
    private String stationIds;

}
