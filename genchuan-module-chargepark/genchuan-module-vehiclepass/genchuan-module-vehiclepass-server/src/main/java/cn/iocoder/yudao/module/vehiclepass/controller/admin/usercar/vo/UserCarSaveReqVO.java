package cn.iocoder.yudao.module.vehiclepass.controller.admin.usercar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDateTime;
import jakarta.validation.constraints.*;

@Schema(description = "管理后台 - 用户车辆新增/修改 Request VO")
@Data
public class UserCarSaveReqVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "所属用户ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotNull(message = "所属用户ID不能为空")
    private Long userId;

    @Schema(description = "车牌号码", requiredMode = Schema.RequiredMode.REQUIRED, example = "闽C12345")
    @NotEmpty(message = "车牌号码不能为空")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", requiredMode = Schema.RequiredMode.REQUIRED, example = "蓝牌")
    @NotEmpty(message = "车牌颜色不能为空")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "小型车")
    @NotEmpty(message = "车辆类型不能为空")
    private String carType;

    @Schema(description = "绑定时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绑定时间不能为空")
    private LocalDateTime bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", requiredMode = Schema.RequiredMode.REQUIRED, example = "待审核")
    @NotEmpty(message = "绑定状态不能为空")
    private String status;

    @Schema(description = "审核人ID", example = "1")
    private Long auditorId;

    @Schema(description = "审核时间")
    private LocalDateTime auditTime;

    @Schema(description = "审核备注", example = "审核通过")
    private String auditRemark;

    @Schema(description = "备注", example = "用户自有车辆")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}
