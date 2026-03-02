package cn.iocoder.yudao.module.evaluate.controller.admin.evalreport.report.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评价报告 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ReportRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31689")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "报告UUID（主键，UUID）", example = "23514")
    @ExcelProperty("报告UUID（主键，UUID）")
    private String reportId;

    @Schema(description = "报告编号")
    @ExcelProperty("报告编号")
    private String code;

    @Schema(description = "关联模板ID（关联eval_report_template.template_id）", example = "3845")
    @ExcelProperty("关联模板ID（关联eval_report_template.template_id）")
    private String templateId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "29720")
    @ExcelProperty("评价对象ID（关联eval_object.object_id）")
    private String objectId;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "2273")
    @ExcelProperty("关联评价任务ID（关联eval_task.task_id）")
    private String taskId;

    @Schema(description = "报告状态ID（关联sys_report_status.status_id）", example = "28846")
    @ExcelProperty("报告状态ID（关联sys_report_status.status_id）")
    private String statusId;

    @Schema(description = "生成人（关联sys_user.user_id）")
    @ExcelProperty("生成人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务生成时间（生成时间）")
    @ExcelProperty("业务生成时间（生成时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "生成方式（单份生成/批量生成）", example = "2")
    @ExcelProperty("生成方式（单份生成/批量生成）")
    private String createType;

    @Schema(description = "报告文件大小（字节）")
    @ExcelProperty("报告文件大小（字节）")
    private Long fileSize;

    @Schema(description = "下载次数", example = "23808")
    @ExcelProperty("下载次数")
    private Integer downloadCount;

    @Schema(description = "最新下载时间")
    @ExcelProperty("最新下载时间")
    private LocalDateTime latestDownloadTime;

    @Schema(description = "补充编辑次数", example = "17245")
    @ExcelProperty("补充编辑次数")
    private Integer editCount;

    @Schema(description = "生成启动时间")
    @ExcelProperty("生成启动时间")
    private LocalDateTime startCreateTime;

    @Schema(description = "生成进度")
    @ExcelProperty("生成进度")
    private String createProgress;

    @Schema(description = "当前处理节点")
    @ExcelProperty("当前处理节点")
    private String processNode;

    @Schema(description = "预计完成时间")
    @ExcelProperty("预计完成时间")
    private LocalDateTime expectCompleteTime;

    @Schema(description = "数据同步状态（已同步/同步中/同步失败）", example = "2")
    @ExcelProperty("数据同步状态（已同步/同步中/同步失败）")
    private String dataSyncStatus;

    @Schema(description = "生成失败原因", example = "不香")
    @ExcelProperty("生成失败原因")
    private String failReason;

    @Schema(description = "核心数据完整性校验结果（已通过/未通过）")
    @ExcelProperty("核心数据完整性校验结果（已通过/未通过）")
    private String dataCheckResult;

    @Schema(description = "补充编辑入口状态（可编辑/不可编辑）", example = "1")
    @ExcelProperty("补充编辑入口状态（可编辑/不可编辑）")
    private String editStatus;

    @Schema(description = "重新生成次数", example = "7163")
    @ExcelProperty("重新生成次数")
    private Integer recreateCount;

    @Schema(description = "最新重新生成时间")
    @ExcelProperty("最新重新生成时间")
    private LocalDateTime latestRecreateTime;

    @Schema(description = "预览次数", example = "27266")
    @ExcelProperty("预览次数")
    private Integer previewCount;

    @Schema(description = "待补充章节")
    @ExcelProperty("待补充章节")
    private String needEditChapter;

    @Schema(description = "补充编辑状态（未开始/编辑中/待提交）")
    @ExcelProperty("补充编辑状态（未开始/编辑中/待提交）")
    private String editProgress;

    @Schema(description = "已补充章节数")
    @ExcelProperty("已补充章节数")
    private Integer editedChapterNum;

    @Schema(description = "总待补充章节数")
    @ExcelProperty("总待补充章节数")
    private Integer totalNeedChapterNum;

    @Schema(description = "附件上传状态（未上传/部分上传/已完成）", example = "1")
    @ExcelProperty("附件上传状态（未上传/部分上传/已完成）")
    private String attachStatus;

    @Schema(description = "最晚补充完成时间")
    @ExcelProperty("最晚补充完成时间")
    private LocalDateTime latestEditTime;

    @Schema(description = "完成时间")
    @ExcelProperty("完成时间")
    private LocalDateTime finishTime;

    @Schema(description = "分发部门数")
    @ExcelProperty("分发部门数")
    private Integer distDeptNum;

    @Schema(description = "归档状态（已归档/待归档）", example = "2")
    @ExcelProperty("归档状态（已归档/待归档）")
    private String archiveStatus;

    @Schema(description = "补充编辑记录数")
    @ExcelProperty("补充编辑记录数")
    private Integer editRecordNum;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}