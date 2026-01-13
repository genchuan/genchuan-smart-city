package cn.iocoder.yudao.module.industry.controller.admin.park.through.inspectionevidence.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 稽查证据分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkInspectionEvidencePageReqVO extends PageParam {

    @Schema(description = "证据ID（UUID）", example = "27107")
    private String evidenceId;

    @Schema(description = "稽查记录ID", example = "27633")
    private String inspectionId;

    @Schema(description = "证据类型：图片/视频/日志", example = "1")
    private String evidenceType;

    @Schema(description = "存储地址", example = "https://www.iocoder.cn")
    private String evidenceUrl;

    @Schema(description = "描述")
    private String evidenceDesc;

    @Schema(description = "上传时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] uploadTime;

    @Schema(description = "上传人ID")
    private Long uploadBy;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] evidenceCreateTime;

    @Schema(description = "业务备注", example = "随便")
    private String evidenceRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}