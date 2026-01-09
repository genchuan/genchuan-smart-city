package cn.iocoder.yudao.module.industry.controller.admin.park.asset.space.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 车位信息分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkSpacePageReqVO extends PageParam {

    @Schema(description = "关联ID", example = "25027")
    private String assetExtendId;

    @Schema(description = "所属车场ID", example = "26025")
    private String lotId;

    @Schema(description = "所属车库ID", example = "12268")
    private String garageId;

    @Schema(description = "唯一车位编号")
    private String spaceNumber;

    @Schema(description = "车位类型：普通/新能源/残疾人专用/子母位", example = "1")
    private String spaceType;

    @Schema(description = "绑定车牌列表")
    private String bindCarList;

    @Schema(description = "是否可预约：0-否/1-是")
    private Boolean isReservable;

    @Schema(description = "状态：空闲/占用/预约/故障/禁用", example = "1")
    private String status;

    @Schema(description = "业务创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] spaceCreateTime;

    @Schema(description = "业务更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] spaceUpdateTime;

    @Schema(description = "业务备注", example = "你猜")
    private String spaceRemark;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}