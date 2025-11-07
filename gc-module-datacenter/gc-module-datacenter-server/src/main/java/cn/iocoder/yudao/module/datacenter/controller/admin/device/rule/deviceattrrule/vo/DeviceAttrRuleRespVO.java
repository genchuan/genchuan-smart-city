package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.deviceattrrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备属性规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceAttrRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "属性规则ID")
    @ExcelProperty("属性规则ID")
    private String attrRuleId;

    @Schema(description = "设备分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备分类ID")
    private String deviceCatId;

    @Schema(description = "设备分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备分类名称")
    private String deviceCatName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性名称")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性代码")
    private String attrCode;

    @Schema(description = "数据类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("数据类型")
    private String dataType;

    @Schema(description = "字段长度")
    @ExcelProperty("字段长度")
    private Integer fieldLength;

    @Schema(description = "是否必选", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否必选")
    private String isRequired;

    @Schema(description = "计量单位")
    @ExcelProperty("计量单位")
    private String unit;

    @Schema(description = "值域范围")
    @ExcelProperty("值域范围")
    private String valueRange;

    @Schema(description = "默认值")
    @ExcelProperty("默认值")
    private String defaultValue;

    @Schema(description = "属性说明")
    @ExcelProperty("属性说明")
    private String attrDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTimeSys;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTime;

}