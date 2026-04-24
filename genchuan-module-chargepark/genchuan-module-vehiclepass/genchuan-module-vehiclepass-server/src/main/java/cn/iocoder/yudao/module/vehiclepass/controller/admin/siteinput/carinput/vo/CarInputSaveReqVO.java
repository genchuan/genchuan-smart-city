package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.carinput.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车辆录入新增/修改 Request VO")
@Data
public class CarInputSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11968")
    private Long id;

    @Schema(description = "车牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌不能为空")
    private String plateNo;

    @Schema(description = "车位ID，关联车位表", requiredMode = Schema.RequiredMode.REQUIRED, example = "7352")
    @NotNull(message = "车位ID，关联车位表不能为空")
    private Long spaceId;

    @Schema(description = "录入时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "录入时间不能为空")
    private LocalDateTime inputTime;

    @Schema(description = "审核状态：待审核/已通过/已驳回，关联字典car_input_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "审核状态：待审核/已通过/已驳回，关联字典car_input_status不能为空")
    private String status;

    @Schema(description = "片区ID，关联片区表", requiredMode = Schema.RequiredMode.REQUIRED, example = "15735")
    @NotNull(message = "片区ID，关联片区表不能为空")
    private Long areaId;

    @Schema(description = "录入人ID，关联芋道用户表system_user", example = "2919")
    private Long inputUserId;

    @Schema(description = "审核人ID，关联芋道用户表system_user", example = "18416")
    private Long auditUserId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见")
    private String auditComment;

    @Schema(description = "备注", example = "你说的对")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}