package cn.iocoder.yudao.module.facility.controller.admin.manhole.manholewarn.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
@Schema(description = "窨井盖预警分页查询 Request VO")
public class ManholeCoverWarnPageReqVO {

    @Schema(description = "窨井盖唯一ID", example = "c1d2e3f4-g5h6-7890-cdef-0123456789ab")
    private String coverId;

    @Schema(description = "所属区域ID", example = "d2e3f4g5-h6i7-8901-defg-123456789abc")
    private String areaId;

    @Schema(description = "预警类型：0-倾斜超标，1-位移超标，2-水位超标，3-井盖开启超时，4-设备离线", example = "2")
    private Integer warnType;

    @Schema(description = "预警状态：0-未处理，1-处理中，2-已解决，3-已忽略", example = "0")
    private Integer warnStatus;

    @Schema(description = "预警发生起始时间", example = "2025-04-01 00:00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime warnTimeStart;

    @Schema(description = "预警发生结束时间", example = "2025-04-01 23:59:59")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime warnTimeEnd;

    @Schema(description = "租户ID（多租户隔离）", requiredMode = Schema.RequiredMode.REQUIRED, example = "e3f4g5h6-i7j8-9012-efgh-23456789abcd")
    @NotBlank(message = "租户ID不能为空")
    private String tenantId;

    @Schema(description = "页码", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "页码不能为空")
    private Integer pageNo = 1;

    @Schema(description = "每页条数", requiredMode = Schema.RequiredMode.REQUIRED, example = "10")
    @NotNull(message = "每页条数不能为空")
    private Integer pageSize = 10;

}
