package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 车位信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkingSpaceInfoRespVO {

    @Schema(description = "[主键ID] 主键，BIGINT，自增，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "2145")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "[车位编号] VARCHAR(32)，唯一，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("车位编号")
    private String spaceNo;

    @Schema(description = "[所属场站] 关联场站信息表station_info，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "14673")
//    @ExcelProperty("所属场站")
    @ExcelIgnore
    private Long stationId;

    @Schema(description = "[所属场站名称] 关联场站信息表station_info，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "14673")
    @ExcelProperty("所属场站名称")
    private String stationName;

    @Schema(description = "[所属车库] VARCHAR(64)，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属车库")
    private String garage;

    @Schema(description = "[车位位置] VARCHAR(64)")
    @ExcelProperty("车位位置")
    private String location;

    @Schema(description = "[车位类型] 如：普通车位/充电车位，关联芋道字典表：parking_space_info_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("车位类型")
    private String type;

    @Schema(description = "[设备类型] 如：地锁/充电桩/摄像头，关联芋道字典表：parking_space_info_device_type", example = "1")
    @ExcelProperty("设备类型")
    private String deviceType;

    @Schema(description = "[车位二维码] VARCHAR(255)")
    @ExcelProperty("车位二维码")
    private String qrcode;

    @Schema(description = "[状态] 如：未绑定/已绑定/已禁用，关联芋道字典表：parking_space_info_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "[实时状态] 如：空闲/占用/故障，关联芋道字典表：parking_space_info_real_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("实时状态")
    private String realStatus;

    @Schema(description = "[绑定时间] DATETIME")
    @ExcelProperty("绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "[绑定人] 关联芋道用户表system_user", example = "27418")
    @ExcelProperty("绑定人")
    private Long bindUserId;

    @Schema(description = "[绑定设备] BIGINT", example = "14479")
    @ExcelProperty("绑定设备")
    private Long deviceId;

    @Schema(description = "[状态更新时间] DATETIME")
    @ExcelProperty("状态更新时间")
    private LocalDateTime statusUpdateTime;

    @Schema(description = "[备注] TEXT", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "[备用字段1] VARCHAR(100)")
//    @ExcelProperty("备用字段1")
    @ExcelIgnore
    private String reserve1;

    @Schema(description = "[备用字段2] VARCHAR(100)")
//    @ExcelProperty("备用字段2")
    @ExcelIgnore
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}
