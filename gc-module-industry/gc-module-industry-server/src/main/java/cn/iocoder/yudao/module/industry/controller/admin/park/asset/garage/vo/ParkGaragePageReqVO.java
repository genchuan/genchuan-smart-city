package cn.iocoder.yudao.module.industry.controller.admin.park.asset.garage.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车库信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkGaragePageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "14707")
    private String assetExtendId;

    @Schema(description = "关联车场ID", example = "22467")
    private String lotId;

    @Schema(description = "楼层数", example = "25104")
    private Integer floorCount;

    @Schema(description = "总车位数")
    private Integer totalSpace;

    @Schema(description = "当前可用车位数")
    private Integer availableSpace;

    @Schema(description = "门禁类型：车牌识别/刷卡/人脸识别", example = "1")
    private String accessControlType;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] garageCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] garageUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    private String garageRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}