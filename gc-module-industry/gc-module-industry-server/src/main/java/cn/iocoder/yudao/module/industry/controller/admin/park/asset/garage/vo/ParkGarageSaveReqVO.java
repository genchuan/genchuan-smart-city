package cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 车库信息新增/修改 Request VO")
@Data
public class ParkGarageSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "29018")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "14707")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "关联车场ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22467")
    @NotEmpty(message = "关联车场ID不能为空")
    private String lotId;

    @Schema(description = "楼层数", requiredMode = Schema.RequiredMode.REQUIRED, example = "25104")
    @NotNull(message = "楼层数不能为空")
    private Integer floorCount;

    @Schema(description = "总车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "总车位数不能为空")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "当前可用车位数不能为空")
    private Integer availableSpace;

    @Schema(description = "门禁类型：车牌识别/刷卡/人脸识别", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "门禁类型：车牌识别/刷卡/人脸识别不能为空")
    private String accessControlType;

    @Schema(description = "业务创建时间")
    private LocalDateTime garageCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime garageUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String garageRemark;

}