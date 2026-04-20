package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo;

import cn.idev.excel.annotation.ExcelProperty;
import cn.iocoder.yudao.module.ordertrade.enums.CollectMethodEnum;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumExcelConverter;
import cn.iocoder.yudao.module.ordertrade.framework.excel.EnumFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 追缴配置 Response VO")
@Data
public class CollectConfigRespVO {

    @Schema(description = "主键ID")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "配置编号")
    @ExcelProperty("配置编号")
    private String configNo;
    @Schema(description = "追缴方式")
    @ExcelProperty(value = "追缴方式", converter = EnumExcelConverter.class)
    @EnumFormat(CollectMethodEnum.class)
    private String collectMethod;
    @Schema(description = "推送模板ID")
    @ExcelProperty("推送模板ID")
    private Long templateId;
    @Schema(description = "推送频次（小时）")
    @ExcelProperty("推送频次（小时）")
    private Integer pushFrequency;
    @Schema(description = "状态")
    @ExcelProperty("状态")
    private String status;
    @Schema(description = "配置说明")
    @ExcelProperty("配置说明")
    private String remark;
    @Schema(description = "操作人ID")
    @ExcelProperty("操作人ID")
    private Long operatorId;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;
}
