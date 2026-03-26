package cn.iocoder.yudao.module.evaluate.controller.admin.platformreport.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 平台上报分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class PlatformReportPageReqVO extends PageParam {

    @Schema(description = "上报UUID", example = "1216")
    private String reportId;

    @Schema(description = "上报批次号")
    private String batchNo;

    @Schema(description = "关联评价任务ID", example = "2512")
    private String taskId;

    @Schema(description = "上报人")
    private String reportBy;

    @Schema(description = "上报时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] reportTime;

    @Schema(description = "上报文件名称", example = "李四")
    private String fileName;

    @Schema(description = "数据条数", example = "3432")
    private Integer dataCount;

    @Schema(description = "成功条数", example = "25863")
    private Integer successCount;

    @Schema(description = "失败条数", example = "16406")
    private Integer failCount;

    @Schema(description = "数据状态", example = "2")
    private String status;

    @Schema(description = "校验时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] checkTime;

    @Schema(description = "校验操作人")
    private String checkBy;

    @Schema(description = "错误文件下载链接", example = "https://www.iocoder.cn")
    private String errorFileUrl;

    @Schema(description = "模板下载状态：可下载/已下载", example = "2")
    private String templateStatus;

    @Schema(description = "文件预览链接：可预览/无", example = "https://www.iocoder.cn")
    private String filePreviewUrl;

    @Schema(description = "重新上传次数", example = "12871")
    private Integer reuploadCount;

    @Schema(description = "最近重新上传时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lastReuploadTime;

    @Schema(description = "失败原因摘要", example = "不香")
    private String failReason;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}