package cn.iocoder.yudao.module.usermerchant.controller.admin.groupclient.groupcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 集团车辆新增/修改 Request VO")
@Data
public class GroupCarUpdateReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18321")
    private Long id;

    @Schema(description = "所属集团ID，关联group_info.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "14580")
    @NotNull(message = "所属集团ID，关联group_info.id不能为空")
    private Long groupId;

    @Schema(description = "车牌号码，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌号码，唯一不能为空")
    private String plateNo;

    @Schema(description = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "车牌颜色：蓝牌/黄牌/绿牌/黑牌/白牌不能为空")
    private String plateColor;

    @Schema(description = "车辆类型：小型车/大型车/新能源/其他", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "车辆类型：小型车/大型车/新能源/其他不能为空")
    private String carType;

    @Schema(description = "绑定时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "绑定时间不能为空")
    private LocalDateTime bindTime;

    @Schema(description = "绑定状态：待审核/已绑定/已解绑", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "绑定状态：待审核/已绑定/已解绑不能为空")
    private String status;

    @Schema(description = "备注", example = "你猜")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

}