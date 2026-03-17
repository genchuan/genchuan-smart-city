package cn.iocoder.yudao.module.evaluate.controller.admin.inspection.record.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 考察记录新增/修改 Request VO")
@Data
public class RecordSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22657")
    private Long id;

    @Schema(description = "考察记录UUID", example = "30995")
    private String recordId;

    @Schema(description = "记录编号")
    private String code;

    @Schema(description = "关联考察计划ID", example = "3792")
    private String planId;

    @Schema(description = "考察对象ID", example = "2854")
    private String objectId;

    @Schema(description = "考察人员ID（多个用逗号分隔）")
    private String inspectBy;

    @Schema(description = "考察时间")
    private LocalDateTime inspectTime;

    @Schema(description = "考察得分")
    private BigDecimal totalScore;

    @Schema(description = "最终考察得分")
    private BigDecimal finalScore;

    @Schema(description = "问题描述摘要")
    private String problemDesc;

    @Schema(description = "照片数量", example = "32216")
    private Integer photoCount;

    @Schema(description = "状态", example = "1")
    private String status;

    @Schema(description = "提交时间")
    private LocalDateTime submitTime;

    @Schema(description = "审核人")
    private String auditBy;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "驳回意见摘要")
    private String rejectOpinion;

    @Schema(description = "草稿保存时间")
    private LocalDateTime draftTime;

    @Schema(description = "最后编辑时间")
    private LocalDateTime lastEditTime;

    @Schema(description = "最后编辑人")
    private String lastEditBy;

    @Schema(description = "照片上传状态：未上传/部分上传/全部上传", example = "1")
    private String photoStatus;

    @Schema(description = "撤回次数", example = "7102")
    private Integer recallCount;

    @Schema(description = "最后撤回时间")
    private LocalDateTime lastRecallTime;

    @Schema(description = "待审核时长（小时）")
    private BigDecimal waitAuditHour;

    @Schema(description = "数据同步状态：已同步/同步中/未同步", example = "2")
    private String dataSyncStatus;

    @Schema(description = "同步时间")
    private LocalDateTime syncTime;

    @Schema(description = "重新提交次数", example = "23306")
    private Integer resubmitCount;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}