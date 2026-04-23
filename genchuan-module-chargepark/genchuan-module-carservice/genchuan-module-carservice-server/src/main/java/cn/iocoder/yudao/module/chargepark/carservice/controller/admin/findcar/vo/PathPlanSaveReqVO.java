package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 路径规划新增/修改 Request VO")
@Data
public class PathPlanSaveReqVO {

    @Schema(description = "主键 ID", example = "1024")
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1001")
    @NotNull(message = "用户 ID 不能为空")
    private Long userId;

    @Schema(description = "起点位置坐标（格式：经度,纬度，前端地图 SDK 选点时填充）",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "118.675324,24.896541")
    @NotBlank(message = "起点位置不能为空")
    private String startLocation;

    @Schema(description = "起点位置汉字地址（前端地图 SDK 选点时回传，供列表页展示）",
            example = "福建省泉州市丰泽区津淮街 123 号")
    private String startLocationName;

    @Schema(description = "终点位置坐标（格式：经度,纬度，前端地图 SDK 选点时填充）",
            requiredMode = Schema.RequiredMode.REQUIRED, example = "118.685324,24.906541")
    @NotBlank(message = "终点位置不能为空")
    private String endLocation;

    @Schema(description = "终点位置汉字地址（前端地图 SDK 选点时回传，供列表页展示）",
            example = "福建省泉州市鲤城区中山路 456 号")
    private String endLocationName;

    @Schema(description = "规划时间（创建时由后端默认为当前时间）")
    private LocalDateTime planTime;

    @Schema(description = "路径长度（米）")
    private Integer pathLength;

    @Schema(description = "预计时长（秒）")
    private Integer expectDuration;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

}
