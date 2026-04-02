package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "窨井盖预警分页查询 Response VO")
public class ManholeCoverWarnPageRespVO {

    @Schema(description = "预警主键ID", example = "warn-202504-00189")
    private String warnId;

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "井盖编码", example = "MC-2025-00189")
    private String coverCode;

    @Schema(description = "井盖名称", example = "XX大道与YY路交叉口东侧井盖")
    private String coverName;

    @Schema(description = "所属区域ID", example = "d2e3f4g5-h6i7-8901-defg-123456789abc")
    private String areaId;

    @Schema(description = "所属区域名称", example = "XX区XX街道")
    private String areaName;

    @Schema(description = "预警类型", example = "2")
    private Integer warnType;

    @Schema(description = "预警类型名称", example = "水位超标")
    private String warnTypeName;

    @Schema(description = "预警级别：0-一般，1-重要，2-紧急", example = "1")
    private Integer warnLevel;

    @Schema(description = "预警级别名称", example = "重要")
    private String warnLevelName;

    @Schema(description = "预警指标值", example = "35cm")
    private String warnValue;

    @Schema(description = "指标阈值", example = "30cm")
    private String thresholdValue;

    @Schema(description = "预警发生时间", example = "2025-04-01 15:30:22")
    private String warnTime;

    @Schema(description = "预警状态", example = "0")
    private Integer warnStatus;

    @Schema(description = "预警状态名称", example = "未处理")
    private String warnStatusName;

    @Schema(description = "处理人ID", example = "")
    private String handleUserId;

    @Schema(description = "处理人姓名", example = "")
    private String handleUserName;

    @Schema(description = "租户ID", example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    private String tenantId;

}
