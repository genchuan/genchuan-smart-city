package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车位信息分页 Request VO")
@Data
public class ParkingSpaceInfoPageReqVO extends PageParam {

    @Schema(description = "[车位编号] VARCHAR(32)，唯一，必填")
    private String spaceNo;

    @Schema(description = "[所属场站] 关联场站信息表station_info，必填", example = "14673")
    private Long stationId;

    @Schema(description = "[所属车库] VARCHAR(64)，必填")
    private String garage;

    @Schema(description = "[车位位置] VARCHAR(64)")
    private String location;

    @Schema(description = "[车位类型] 如：普通车位/充电车位，关联芋道字典表：parking_space_info_type", example = "1")
    private String type;

    @Schema(description = "[设备类型] 如：地锁/充电桩/摄像头，关联芋道字典表：parking_space_info_device_type", example = "1")
    private String deviceType;

    @Schema(description = "[车位二维码] VARCHAR(255)")
    private String qrcode;

    @Schema(description = "[状态] 如：未绑定/已绑定/已禁用，关联芋道字典表：parking_space_info_status", example = "2")
    private String status;

    @Schema(description = "[实时状态] 如：空闲/占用/故障，关联芋道字典表：parking_space_info_real_status", example = "2")
    private String realStatus;

    @Schema(description = "[绑定时间] DATETIME")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bindTime;

    @Schema(description = "[绑定人] 关联芋道用户表system_user", example = "27418")
    private Long bindUserId;

    @Schema(description = "[绑定设备] BIGINT", example = "14479")
    private Long deviceId;

    @Schema(description = "[状态更新时间] DATETIME")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] statusUpdateTime;

    @Schema(description = "[备注] TEXT", example = "你猜")
    private String remark;

    @Schema(description = "[备用字段1] VARCHAR(100)")
    private String reserve1;

    @Schema(description = "[备用字段2] VARCHAR(100)")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
