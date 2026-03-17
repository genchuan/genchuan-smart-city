package cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.archiverecord.vo;

import com.alibaba.excel.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

@Schema(description = "管理后台 - 评价结果存档 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ArchiveRecordRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "20404")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "存档UUID（主键，UUID）", example = "19943")
    @ExcelProperty("存档UUID（主键，UUID）")
    private String archiveId;

    @Schema(description = "存档编号")
    @ExcelProperty("存档编号")
    private String code;

    @Schema(description = "关联公示记录ID（关联eval_public_record.public_id）", example = "32732")
    @ExcelProperty("关联公示记录ID（关联eval_public_record.public_id）")
    private String publicId;

    @Schema(description = "评价对象ID（关联eval_object.object_id）", example = "13506")
    @ExcelProperty("评价对象ID（关联eval_object.object_id）")
    private String objectId;

    @Schema(description = "关联审核记录ID（关联eval_audit_record.audit_id）", example = "14968")
    @ExcelProperty("关联审核记录ID（关联eval_audit_record.audit_id）")
    private String auditId;

    @Schema(description = "评价标准ID（关联eval_standard_item.standard_item_id）", example = "9580")
    @ExcelProperty("评价标准ID（关联eval_standard_item.standard_item_id）")
    private String standardId;

    @Schema(description = "评价得分")
    @ExcelProperty("评价得分")
    private BigDecimal evalScore;

    @Schema(description = "存档状态（关联sys_archive_status.status_id）", example = "2")
    @ExcelProperty("存档状态（关联sys_archive_status.status_id）")
    private String status;

    @Schema(description = "申请存档时间")
    @ExcelProperty("申请存档时间")
    private LocalDateTime applyTime;

    @Schema(description = "存档人（关联sys_user.user_id）")
    @ExcelProperty("存档人（关联sys_user.user_id）")
    private String archiveBy;

    @Schema(description = "实际存档时间")
    @ExcelProperty("实际存档时间")
    private LocalDateTime actualTime;

    @Schema(description = "存档附件数量", example = "4948")
    @ExcelProperty("存档附件数量")
    private Integer attachmentCount;

    @Schema(description = "存档存储位置")
    @ExcelProperty("存档存储位置")
    private String storeLocation;

    @Schema(description = "数据溯源链接", example = "https://www.iocoder.cn")
    @ExcelProperty("数据溯源链接")
    private String traceUrl;

    @Schema(description = "查询次数", example = "9977")
    @ExcelProperty("查询次数")
    private Integer queryCount;

    @Schema(description = "最新查询时间")
    @ExcelProperty("最新查询时间")
    private LocalDateTime latestQueryTime;

    @Schema(description = "待存档原因", example = "不对")
    @ExcelProperty("待存档原因")
    private String waitReason;

    @Schema(description = "待存档时长（小时）")
    @ExcelProperty("待存档时长（小时）")
    private BigDecimal waitHour;

    @Schema(description = "存档条件校验结果（已满足/未满足）")
    @ExcelProperty("存档条件校验结果（已满足/未满足）")
    private String checkResult;

    @Schema(description = "关联申诉记录ID（关联eval_appeal_record.appeal_id）", example = "7339")
    @ExcelProperty("关联申诉记录ID（关联eval_appeal_record.appeal_id）")
    private String appealId;

    @Schema(description = "申诉复核状态", example = "1")
    @ExcelProperty("申诉复核状态")
    private String appealStatus;

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