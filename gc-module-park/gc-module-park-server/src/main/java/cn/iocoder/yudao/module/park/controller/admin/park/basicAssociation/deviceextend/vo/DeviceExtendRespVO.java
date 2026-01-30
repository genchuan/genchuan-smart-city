package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.deviceextend.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 设备扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class DeviceExtendRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "13340")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "设备类型：道闸/摄像头/计费桩/传感器/边缘网关", example = "2")
    @ExcelProperty("设备类型：道闸/摄像头/计费桩/传感器/边缘网关")
    private String deviceType;

    @Schema(description = "所属资产ID", example = "7173")
    @ExcelProperty("所属资产ID")
    private Long assetId;

    @Schema(description = "唯一设备编码")
    @ExcelProperty("唯一设备编码")
    private String deviceCode;

    @Schema(description = "运行状态：在线/离线/故障/维护", example = "2")
    @ExcelProperty("运行状态：在线/离线/故障/维护")
    private String deviceStatus;

    @Schema(description = "安装时间")
    @ExcelProperty("安装时间")
    private LocalDateTime installTime;

    @Schema(description = "上次维护时间")
    @ExcelProperty("上次维护时间")
    private LocalDateTime lastMaintainTime;

    @Schema(description = "下次维护时间")
    @ExcelProperty("下次维护时间")
    private LocalDateTime nextMaintainTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime deviceCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime deviceUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    @ExcelProperty("业务备注")
    private String deviceRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}