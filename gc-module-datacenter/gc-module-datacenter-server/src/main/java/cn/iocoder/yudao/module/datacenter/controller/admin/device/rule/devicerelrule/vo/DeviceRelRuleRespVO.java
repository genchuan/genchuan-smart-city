package cn.iocoder.yudao.module.datacenter.controller.admin.device.rule.devicerelrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备关联规则配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceRelRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联规则ID")
    @ExcelProperty("关联规则ID")
    private String relRuleId;

    @Schema(description = "设备分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备分类ID")
    private String deviceCatId;

    @Schema(description = "设备分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("设备分类名称")
    private String deviceCatName;

    @Schema(description = "关联对象类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象类型")
    private String relObjType;

    @Schema(description = "关联对象ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象ID")
    private String relObjId;

    @Schema(description = "关联对象名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联对象名称")
    private String relObjName;

    @Schema(description = "关联必选标识", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("关联必选标识")
    private String isRequired;

    @Schema(description = "关联校验规则")
    @ExcelProperty("关联校验规则")
    private String relCkRule;

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