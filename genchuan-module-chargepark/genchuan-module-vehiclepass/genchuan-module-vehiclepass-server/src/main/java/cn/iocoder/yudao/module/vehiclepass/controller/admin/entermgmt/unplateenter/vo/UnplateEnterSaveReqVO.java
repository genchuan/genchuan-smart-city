package cn.iocoder.yudao.module.vehiclepass.controller.admin.entermgmt.unplateenter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 无牌入场新增/修改 Request VO")
@Data
public class UnplateEnterSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31822")
    private Long id;

    @Schema(description = "车辆类型：小型车/中型车/大型车/其他，关联字典unplate_enter_car_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "车辆类型：小型车/中型车/大型车/其他，关联字典unplate_enter_car_type不能为空")
    private String carType;

    @Schema(description = "车辆颜色")
    private String carColor;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系电话不能为空")
    private String phone;

    @Schema(description = "登记时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "登记时间不能为空")
    private LocalDateTime registerTime;

    @Schema(description = "审核状态：待审核/已通过/已驳回，关联字典unplate_enter_status", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "2")
    private String status;

    @Schema(description = "场站ID，关联场站表", requiredMode = Schema.RequiredMode.REQUIRED, example = "28427")
    @NotNull(message = "场站ID，关联场站表不能为空")
    private Long stationId;

    @Schema(description = "审核人ID，关联system_user用户表", example = "23198")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "备注", example = "随便")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}