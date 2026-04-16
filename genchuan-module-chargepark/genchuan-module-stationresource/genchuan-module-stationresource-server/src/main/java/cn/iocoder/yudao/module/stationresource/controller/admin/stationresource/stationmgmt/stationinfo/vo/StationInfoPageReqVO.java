package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo;

import jakarta.validation.constraints.Size;
import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 场站信息分页 Request VO")
@Data
public class StationInfoPageReqVO extends PageParam {


    @Schema(description = "[场站编号] VARCHAR(32)，唯一，必填")
    private String stationNo;

    @Schema(description = "[场站名称] VARCHAR(64)，必填", example = "王五")
    private String name;

    @Schema(description = "[场站类型] 如：公共/商业/小区/产业，关联芋道字典表：station_info_type", example = "1")
    private String type;

    @Schema(description = "[场站地址] VARCHAR(255)，必填")
    private String address;

    @Schema(description = "[泊位总数] INT，必填")
    private Integer spaceTotal;

    @Schema(description = "[负责人] 关联芋道用户表system_user", example = "13294")
    private Long userId;

    @Schema(description = "[收费标准] VARCHAR(255)")
    private String feeStandard;

    @Schema(description = "[所属片区] 关联片区信息表area_info", example = "27106")
    private Long areaId;

    @Schema(description = "[运营类型] 如：直接管理/甲方代运营/本地化部署/横向对接/数据互通，关联芋道字典表：station_info_operate_type", example = "1")
    private String operateType;

    @Schema(description = "[状态] 如：未生效/已生效/已禁用，关联芋道字典表：station_info_status", example = "2")
    private String status;

    @Schema(description = "[绑定时间] DATETIME")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] bindTime;

    @Schema(description = "[绑定人] 关联芋道用户表system_user", example = "10602")
    private Long bindUserId;

    @Schema(description = "[设备绑定数] INT，默认0", example = "27197")
    private Integer deviceCount;

    @Schema(description = "[车位绑定数] INT，默认0", example = "12943")
    private Integer spaceCount;

    @Schema(description = "[备注] TEXT", example = "随便")
    private String remark;

    @Schema(description = "[备用字段1] VARCHAR(100)")
    private String reserve1;

    @Schema(description = "[备用字段2] VARCHAR(100)")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}
