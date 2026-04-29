package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 信用配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class CreditConfigRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29133")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "加减分规则", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("加减分规则")
    private String ruleDesc;

    @Schema(description = "等级阈值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("等级阈值")
    private String levelThreshold;

    @Schema(description = "配置状态：未生效/已生效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("配置状态：未生效/已生效")
    private String status;

    @Schema(description = "生效时间")
    @ExcelProperty("生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "备注", example = "你说的对")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}