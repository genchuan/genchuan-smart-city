package cn.iocoder.yudao.module.industry.controller.admin.park.asset.roadside.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 路侧泊位新增/修改 Request VO")
@Data
public class ParkRoadsideSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13879")
    private Long id;

    @Schema(description = "关联ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7766")
    @NotEmpty(message = "关联ID不能为空")
    private String assetExtendId;

    @Schema(description = "道路名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "道路名称不能为空")
    private String roadName;

    @Schema(description = "唯一泊位编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "唯一泊位编号不能为空")
    private String berthNumber;

    @Schema(description = "泊位类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "泊位类型不能为空")
    private String berthType;

    @Schema(description = "所属计费桩", example = "11757")
    private String feePileId;

    @Schema(description = "占用状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "占用状态不能为空")
    private String status;

    @Schema(description = "上次占用时间")
    private LocalDateTime lastOccupyTime;

    @Schema(description = "上次释放时间")
    private LocalDateTime lastReleaseTime;

    @Schema(description = "业务创建时间")
    private LocalDateTime roadsideCreateTime;

    @Schema(description = "业务更新时间")
    private LocalDateTime roadsideUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String roadsideRemark;

}