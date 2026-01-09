package cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车位信息新增/修改 Request VO")
@Data
public class ParkSpaceSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24853")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "25027")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "所属车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26025")
    @NotEmpty(message = "所属车场ID不能为空")
    private String lotId;

    @Schema(description = "所属车库ID", example = "12268")
    private String garageId;

    @Schema(description = "唯一车位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "唯一车位编号不能为空")
    private String spaceNumber;

    @Schema(description = "车位类型：普通/新能源/残疾人专用/子母位", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "车位类型：普通/新能源/残疾人专用/子母位不能为空")
    private String spaceType;

    @Schema(description = "绑定车牌列表")
    private String bindCarList;

    @Schema(description = "是否可预约：0-否/1-是", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "是否可预约：0-否/1-是不能为空")
    private Boolean isReservable;

    @Schema(description = "状态：空闲/占用/预约/故障/禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "状态：空闲/占用/预约/故障/禁用不能为空")
    private String status;

    @Schema(description = "业务创建时间")
    private LocalDateTime spaceCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime spaceUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String spaceRemark;

}