package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationmgmt.stationinfo.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 场站信息 Response VO")
@Data
@ExcelIgnoreUnannotated
public class StationInfoRespVO {

    @Schema(description = "[主键ID] 主键，BIGINT，自增，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "12582")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "[场站编号] VARCHAR(32)，唯一，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场站编号")
    private String stationNo;

    @Schema(description = "[场站名称] VARCHAR(64)，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("场站名称")
    private String name;

    @Schema(description = "[场站类型] 如：公共/商业/小区/产业，关联芋道字典表：station_info_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("场站类型")
    private String type;

    @Schema(description = "[场站地址] VARCHAR(255)，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("场站地址")
    private String address;

    @Schema(description = "[泊位总数] INT，必填", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("泊位总数")
    private Integer spaceTotal;

    @Schema(description = "[负责人] 关联芋道用户表system_user", requiredMode = Schema.RequiredMode.REQUIRED, example = "13294")
    @ExcelProperty("负责人")
    private Long userId;

    @Schema(description = "[收费标准] VARCHAR(255)")
    @ExcelProperty("收费标准")
    private String feeStandard;

    @Schema(description = "[所属片区] 关联片区信息表area_info", example = "27106")
    @ExcelProperty("所属片区")
    private Long areaId;

    @Schema(description = "[运营类型] 如：直接管理/甲方代运营/本地化部署/横向对接/数据互通，关联芋道字典表：station_info_operate_type", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("运营类型")
    private String operateType;

    @Schema(description = "[状态] 如：未生效/已生效/已禁用，关联芋道字典表：station_info_status", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态")
    private String status;

    @Schema(description = "[绑定时间] DATETIME")
    @ExcelProperty("绑定时间")
    private LocalDateTime bindTime;

    @Schema(description = "[绑定人] 关联芋道用户表system_user", example = "10602")
    @ExcelProperty("绑定人")
    private Long bindUserId;

    @Schema(description = "[设备绑定数] INT，默认0", example = "27197")
    @ExcelProperty("设备绑定数")
    private Integer deviceCount;

    @Schema(description = "[车位绑定数] INT，默认0", example = "12943")
    @ExcelProperty("车位绑定数")
    private Integer spaceCount;

    @Schema(description = "[备注] TEXT", example = "随便")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "[备用字段1] VARCHAR(100)")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "[备用字段2] VARCHAR(100)")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}
