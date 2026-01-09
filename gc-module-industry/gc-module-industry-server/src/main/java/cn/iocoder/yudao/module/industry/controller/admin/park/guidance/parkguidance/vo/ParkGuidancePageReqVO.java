package cn.iocoder.yudao.module.industry.controller.admin.park.guidance.parkguidance.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 停车诱导服务分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkGuidancePageReqVO extends PageParam {

    @Schema(description = "停车诱导业务唯一标识", example = "10181")
    private String guidanceId;

    @Schema(description = "用户唯一标识", example = "6353")
    private Long userId;

    @Schema(description = "用户当前位置X坐标")
    private BigDecimal userPosX;

    @Schema(description = "用户当前位置Y坐标")
    private BigDecimal userPosY;

    @Schema(description = "推荐的停车场唯一标识", example = "13522")
    private Long recommendParkId;

    @Schema(description = "推荐的停车场名称", example = "芋艿")
    private String recommendParkName;

    @Schema(description = "推荐停车场剩余泊位数量")
    private Integer remainBerths;

    @Schema(description = "到达推荐停车场的预计行驶时间（秒）")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] estDriveTime;

    @Schema(description = "推荐停车场收费标准")
    private String chargeStd;

    @Schema(description = "停车诱导采用的方式")
    private String guidanceMethod;

    @Schema(description = "执行停车诱导的时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] guidanceTime;

    @Schema(description = "用户在诱导过程中的行为记录")
    private String userBehavior;

    @Schema(description = "业务记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "所属行政区划名称", example = "李四")
    private String regionName;

    @Schema(description = "所属网格名称", example = "李四")
    private String gridName;

    @Schema(description = "关联的管理事项名称", example = "赵六")
    private String matterName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}
