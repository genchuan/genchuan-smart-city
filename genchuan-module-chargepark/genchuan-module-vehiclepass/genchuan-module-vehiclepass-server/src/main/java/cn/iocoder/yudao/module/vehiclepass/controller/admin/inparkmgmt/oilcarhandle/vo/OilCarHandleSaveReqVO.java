package cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.oilcarhandle.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 油车占位处置新增/修改 Request VO")
@Data
public class OilCarHandleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18580")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", example = "27831")
    private Long spaceId;

    @Schema(description = "识别时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "识别时间不能为空")
    private LocalDateTime identifyTime;

    @Schema(description = "占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "占位类型：燃油车占位 / 其他，关联字典oil_car_handle_occupy_type不能为空")
    private String occupyType;

    @Schema(description = "处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "处置状态：未处理 / 处理中 / 已关闭，关联字典oil_car_handle_status不能为空")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "8546")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "处置人ID，关联system_user用户表", example = "15483")
    private Long handleUserId;

    @Schema(description = "处置时间")
    private LocalDateTime handleTime;

    @Schema(description = "处置方式")
    private String handleMethod;

    @Schema(description = "忽略理由", example = "不喜欢")
    private String ignoreReason;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}