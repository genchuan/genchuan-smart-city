package cn.iocoder.yudao.module.industry.controller.admin.park.through.trafficinspection.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 通行稽查分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkTrafficInspectionPageReqVO extends PageParam {

    @Schema(description = "稽查记录ID（UUID）", example = "27965")
    private String inspectionId;

    @Schema(description = "车牌")
    private String carNumber;

    @Schema(description = "入场记录ID", example = "31444")
    private String entryId;

    @Schema(description = "离场记录ID", example = "23200")
    private String exitId;

    @Schema(description = "违规类型：套牌/逃费/无权限通行/其他", example = "2")
    private String violationType;

    @Schema(description = "违规时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] violationTime;

    @Schema(description = "证据ID列表")
    private String evidenceIds;

    @Schema(description = "处置状态：未处置/处置中/已处置", example = "2")
    private String disposalStatus;

    @Schema(description = "处置内容")
    private String disposalContent;

    @Schema(description = "处置人ID")
    private Long disposalBy;

    @Schema(description = "处置时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] disposalTime;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectionCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] inspectionUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String inspectionRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}