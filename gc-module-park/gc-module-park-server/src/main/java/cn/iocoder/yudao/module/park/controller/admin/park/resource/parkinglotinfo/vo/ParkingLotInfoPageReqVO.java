package cn.iocoder.yudao.module.park.controller.admin.park.resource.parkinglotinfo.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车场信息管理分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkingLotInfoPageReqVO extends PageParam {

    @Schema(description = "停车场ID", example = "14750")
    private String lotId;

    @Schema(description = "停车场名称", example = "王五")
    private String lotName;

    @Schema(description = "车场类型", example = "2")
    private String lotType;

    @Schema(description = "所属区域")
    private String region;

    @Schema(description = "总车位数")
    private Integer totalSpaces;

    @Schema(description = "可用车位数")
    private Integer availableSpaces;

    @Schema(description = "车场状态", example = "1")
    private String lotStatus;

    @Schema(description = "收费标准")
    private String feeStandard;

    @Schema(description = "营业时间")
    private String businessHours;

    @Schema(description = "运营商户")
    private String operator;

    @Schema(description = "联系人")
    private String contactPerson;

    @Schema(description = "联系电话")
    private String contactPhone;

    @Schema(description = "所属行政区划")
    private String areaCode;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}