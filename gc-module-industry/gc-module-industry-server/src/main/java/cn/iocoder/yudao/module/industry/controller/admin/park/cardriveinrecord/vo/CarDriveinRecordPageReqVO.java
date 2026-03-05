package cn.iocoder.yudao.module.industry.controller.admin.park.cardriveinrecord.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车辆入场记录分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CarDriveinRecordPageReqVO extends PageParam {

    @Schema(description = "停车编号（车辆在停车场停车唯一编号）", example = "7627")
    private String recordId;

    @Schema(description = "入口编号")
    private String entranceNo;

    @Schema(description = "入口名称", example = "赵六")
    private String entranceName;

    @Schema(description = "车牌类型", example = "1")
    private String plateType;

    @Schema(description = "车牌号")
    private String plateNumber;

    @Schema(description = "进场时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] driveInTime;

    @Schema(description = "进场图片，图片URL地址或base64格式")
    private String driveInPhoto;

    @Schema(description = "空闲车位数")
    private Integer emptyPlot;

    @Schema(description = "收费员账号", example = "25003")
    private String operatorId;

    @Schema(description = "收费员名称", example = "李四")
    private String operatorName;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}