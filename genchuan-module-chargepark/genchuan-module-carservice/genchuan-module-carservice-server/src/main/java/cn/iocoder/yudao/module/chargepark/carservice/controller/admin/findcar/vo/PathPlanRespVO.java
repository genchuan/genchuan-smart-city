package cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 路径规划 Response VO")
@Data
@ExcelIgnoreUnannotated
public class PathPlanRespVO {

    @Schema(description = "主键 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "用户 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long userId;

    @Schema(description = "用户名（关联 system_user.nickname）")
    @ExcelProperty("用户")
    private String userName;

    @Schema(description = "起点位置（经度,纬度 格式）", example = "118.675324,24.896541")
    @ExcelProperty("起点位置")
    private String startLocation;

    @Schema(description = "起点位置汉字地址（列表页展示用）", example = "福建省泉州市丰泽区津淮街 123 号")
    @ExcelProperty("起点地址")
    private String startLocationName;

    @Schema(description = "终点位置（经度,纬度 格式）", example = "118.685324,24.906541")
    @ExcelProperty("终点位置")
    private String endLocation;

    @Schema(description = "终点位置汉字地址（列表页展示用）", example = "福建省泉州市鲤城区中山路 456 号")
    @ExcelProperty("终点地址")
    private String endLocationName;

    @Schema(description = "规划时间")
    @ExcelProperty("规划时间")
    private LocalDateTime planTime;

    @Schema(description = "路径长度（米）")
    @ExcelProperty("路径长度（米）")
    private Integer pathLength;

    @Schema(description = "预计时长（秒）")
    @ExcelProperty("预计时长（秒）")
    private Integer expectDuration;

    @Schema(description = "备用字段 1")
    private String reserve1;

    @Schema(description = "备用字段 2")
    private String reserve2;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间")
    private LocalDateTime updateTime;

}
