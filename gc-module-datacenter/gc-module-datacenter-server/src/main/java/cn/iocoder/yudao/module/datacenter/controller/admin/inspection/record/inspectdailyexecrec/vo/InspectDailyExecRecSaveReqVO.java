package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectdailyexecrec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 日常巡查执行记录新增/修改 Request VO")
@Data
public class InspectDailyExecRecSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "记录ID")
    private String dailyExecId;

    @Schema(description = "执行记录编码")
    private String dailyExecCode;

    @Schema(description = "关联任务ID")
    private String taskId;

    @Schema(description = "关联任务编码")
    private String taskCode;

    @Schema(description = "执行人员ID")
    private String personId;

    @Schema(description = "执行人员姓名")
    private String personName;

    @Schema(description = "关联标准ID")
    private String stdId;

    @Schema(description = "执行开始时间")
    private LocalDateTime execStartTime;

    @Schema(description = "执行完成时间")
    private LocalDateTime execCompleteTime;

    @Schema(description = "检查项执行情况")
    private String ckItemExec;

    @Schema(description = "执行评分")
    private Integer execScore;

    @Schema(description = "执行备注")
    private String execRemark;

    @Schema(description = "关联设备编码")
    private String deviceCode;

    @Schema(description = "执行地点坐标X")
    private BigDecimal execLocX;

    @Schema(description = "执行地点坐标Y")
    private BigDecimal execLocY;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}