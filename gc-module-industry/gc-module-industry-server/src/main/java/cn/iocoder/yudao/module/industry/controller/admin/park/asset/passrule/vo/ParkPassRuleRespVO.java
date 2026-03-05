package cn.iocoder.yudao.module.industry.controller.admin.park.asset.passrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 通行规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPassRuleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20418")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "通行规则ID（UUID）", requiredMode = Schema.RequiredMode.REQUIRED, example = "24778")
    @ExcelProperty("通行规则ID（UUID）")
    private String passRuleId;

    @Schema(description = "规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("规则名称")
    private String ruleName;

    @Schema(description = "关联出入口ID", example = "15051")
    @ExcelProperty("关联出入口ID")
    private String entryExitId;

    @Schema(description = "允许车辆类型")
    @ExcelProperty("允许车辆类型")
    private String allowCarTypes;

    @Schema(description = "禁止车辆类型")
    @ExcelProperty("禁止车辆类型")
    private String forbidCarTypes;

    @Schema(description = "高峰时段规则")
    @ExcelProperty("高峰时段规则")
    private String peakTimeRule;

    @Schema(description = "平峰时段规则")
    @ExcelProperty("平峰时段规则")
    private String offPeakTimeRule;

    @Schema(description = "状态：启用/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：启用/禁用")
    private String status;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime passRuleCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime passRuleUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String passRuleRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}