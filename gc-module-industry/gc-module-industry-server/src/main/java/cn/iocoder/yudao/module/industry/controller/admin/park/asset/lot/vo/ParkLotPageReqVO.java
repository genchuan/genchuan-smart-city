package cn.iocoder.yudao.module.industry.controller.admin.park.asset.lot.vo;

import lombok.*;

import java.time.LocalTime;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车场信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkLotPageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "10180")
    private String assetExtendId;

    @Schema(description = "总车位数")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数")
    private Integer availableSpace;

    @Schema(description = "车场类型", example = "2")
    private String parkType;

    @Schema(description = "开放时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] openTime;

    @Schema(description = "关闭时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalTime[] closeTime;

    @Schema(description = "运营商户ID", example = "21469")
    private String managementMerchantId;

    @Schema(description = "默认费率策略ID", example = "14321")
    private String feeStrategyId;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lotCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] lotUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    private String lotRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}