package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 指标管理分页 Request VO")
@Data
public class TargetMgmtPageReqVO extends PageParam {

    @Schema(description = "指标名称", example = "张三")
    private String targetName;

    @Schema(description = "指标总分")
    private BigDecimal totalScore;

    @Schema(description = "预警阈值")
    private BigDecimal warnThreshold;

    @Schema(description = "评价人类型：教职工/家长/领导", example = "1")
    private String evaluatorType;

    @Schema(description = "计分方式：累计赋分/接口赋分", example = "1")
    private String scoreType;

    @Schema(description = "启用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] enableTime;

    @Schema(description = "停用时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] disableTime;

    @Schema(description = "状态：未启用/已启用", example = "1")
    private String status;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}