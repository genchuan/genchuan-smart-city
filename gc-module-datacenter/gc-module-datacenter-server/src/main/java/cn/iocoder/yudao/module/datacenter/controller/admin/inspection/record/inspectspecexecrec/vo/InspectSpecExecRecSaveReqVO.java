package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.record.inspectspecexecrec.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 专项巡查执行记录新增/修改 Request VO")
@Data
public class InspectSpecExecRecSaveReqVO {

    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "记录ID")
    private String specExecId;

    @Schema(description = "记录编码")
    private String specExecCode;

    @Schema(description = "关联专项计划ID")
    private String specPlanId;

    @Schema(description = "关联专项计划名称")
    private String specPlanName;

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

    @Schema(description = "关联标准名称")
    private String stdName;

    @Schema(description = "执行开始时间")
    private LocalDateTime execStartTime;

    @Schema(description = "执行完成时间")
    private LocalDateTime execCompleteTime;

    @Schema(description = "检查项执行明细")
    private String ckItemDetail;

    @Schema(description = "不合格项数量")
    private Integer unqualifiedCount;

    @Schema(description = "不合格项详情")
    private String unqualifiedDetail;

    @Schema(description = "执行评分")
    private Integer execScore;

    @Schema(description = "专项执行备注")
    private String specExecRemark;

    @Schema(description = "执行设备编码")
    private String deviceCode;

    @Schema(description = "执行地点坐标X")
    private BigDecimal execLocX;

    @Schema(description = "执行地点坐标Y")
    private BigDecimal execLocY;

    @Schema(description = "创建人")
    private String bizCreateUser;

    @Schema(description = "创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    private String bizUpdateUser;

    @Schema(description = "更新时间")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}