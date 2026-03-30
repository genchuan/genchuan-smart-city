package cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 评价报告新增/修改 Request VO")
@Data
public class ReportSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31689")
    private Long id;

    @Schema(description = "报告UUID（主键，UUID）", example = "23514")
    private String reportId;

    @Schema(description = "报告编号")
    private String code;

    @Schema(description = "关联模板ID（关联eval_report_template.template_id）", example = "3845")
    private String templateId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "29720")
    private String objectId;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "2273")
    private String taskId;

    @Schema(description = "报告状态ID（关联sys_report_status.status_id）", example = "28846")
    private String statusId;

    @Schema(description = "生成人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务生成时间（生成时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "生成方式（单份生成/批量生成）", example = "2")
    private String createType;

    @Schema(description = "报告文件大小（字节）")
    private Long fileSize;

    @Schema(description = "下载次数", example = "23808")
    private Integer downloadCount;

    @Schema(description = "最新下载时间")
    private LocalDateTime latestDownloadTime;

    @Schema(description = "补充编辑次数", example = "17245")
    private Integer editCount;

    @Schema(description = "生成启动时间")
    private LocalDateTime startCreateTime;

    @Schema(description = "生成进度")
    private String createProgress;

    @Schema(description = "当前处理节点")
    private String processNode;

    @Schema(description = "预计完成时间")
    private LocalDateTime expectCompleteTime;

    @Schema(description = "数据同步状态（已同步/同步中/同步失败）", example = "2")
    private String dataSyncStatus;

    @Schema(description = "生成失败原因", example = "不香")
    private String failReason;

    @Schema(description = "核心数据完整性校验结果（已通过/未通过）")
    private String dataCheckResult;

    @Schema(description = "补充编辑入口状态（可编辑/不可编辑）", example = "1")
    private String editStatus;

    @Schema(description = "重新生成次数", example = "7163")
    private Integer recreateCount;

    @Schema(description = "最新重新生成时间")
    private LocalDateTime latestRecreateTime;

    @Schema(description = "预览次数", example = "27266")
    private Integer previewCount;

    @Schema(description = "待补充章节")
    private String needEditChapter;

    @Schema(description = "补充编辑状态（未开始/编辑中/待提交）")
    private String editProgress;

    @Schema(description = "已补充章节数")
    private Integer editedChapterNum;

    @Schema(description = "总待补充章节数")
    private Integer totalNeedChapterNum;

    @Schema(description = "附件上传状态（未上传/部分上传/已完成）", example = "1")
    private String attachStatus;

    @Schema(description = "最晚补充完成时间")
    private LocalDateTime latestEditTime;

    @Schema(description = "完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "分发部门数")
    private Integer distDeptNum;

    @Schema(description = "归档状态（已归档/待归档）", example = "2")
    private String archiveStatus;

    @Schema(description = "补充编辑记录数")
    private Integer editRecordNum;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}