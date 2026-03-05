package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 统计分析报新增/修改 Request VO")
@Data
public class StatReportSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29117")
    private Long id;

    @Schema(description = "报表UUID（主键，UUID）", example = "20640")
    private String reportId;

    @Schema(description = "报表编号")
    private String code;

    @Schema(description = "报表名称", example = "赵六")
    private String name;

    @Schema(description = "报表类型ID（关联sys_report_type.type_id）", example = "11642")
    private String typeId;

    @Schema(description = "关联评价任务ID（关联eval_task.task_id）", example = "30920")
    private String taskId;

    @Schema(description = "统计维度（关联sys_stat_dimension.dimension_id）")
    private String dimension;

    @Schema(description = "报表状态（关联sys_report_status.status_id）", example = "1")
    private String status;

    @Schema(description = "生成人（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "业务生成时间（生成时间）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "导出时间")
    private LocalDateTime exportTime;

    @Schema(description = "导出人（关联sys_user.user_id）")
    private String exportBy;

    @Schema(description = "报表格式")
    private String format;

    @Schema(description = "数据更新时间")
    private LocalDateTime dataUpdateTime;

    @Schema(description = "生成耗时（秒）")
    private BigDecimal costTime;

    @Schema(description = "预览次数", example = "26510")
    private Integer previewCount;

    @Schema(description = "最新预览时间")
    private LocalDateTime latestPreviewTime;

    @Schema(description = "报表文件大小（字节）")
    private Long fileSize;

    @Schema(description = "数据来源")
    private String dataSource;

    @Schema(description = "导出次数", example = "24156")
    private Integer exportCount;

    @Schema(description = "最新导出时间")
    private LocalDateTime latestExportTime;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

}