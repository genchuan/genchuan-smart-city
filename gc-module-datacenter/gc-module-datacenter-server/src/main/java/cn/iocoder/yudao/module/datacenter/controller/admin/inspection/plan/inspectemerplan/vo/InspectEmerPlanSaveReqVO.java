package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.plan.inspectemerplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 应急巡查计划信息新增/修改 Request VO")
@Data
public class InspectEmerPlanSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "计划ID")
    private String emerPlanId;

    @Schema(description = "计划名称")
    private String emerPlanName;

    @Schema(description = "计划编码")
    private String emerPlanCode;

    @Schema(description = "应急主题")
    private String emerTopic;

    @Schema(description = "关联应急事件ID")
    private String emerEvtId;

    @Schema(description = "关联应急事件名称")
    private String emerEvtName;

    @Schema(description = "所属区域代码")
    private String areaCode;

    @Schema(description = "所属区域名称")
    private String areaName;

    @Schema(description = "关联标准ID")
    private String stdId;

    @Schema(description = "关联标准名称")
    private String stdName;

    @Schema(description = "负责人员IDs")
    private String personIds;

    @Schema(description = "负责人员Names")
    private String personNames;

    @Schema(description = "执行时间")
    private LocalDateTime executeTime;

    @Schema(description = "执行截止时间")
    private LocalDateTime executeDeadline;

    @Schema(description = "计划状态")
    private Boolean planStatus;

    @Schema(description = "创建人")
    private String createUser;

    @Schema(description = "创建时间")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人")
    private String updateUser;

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