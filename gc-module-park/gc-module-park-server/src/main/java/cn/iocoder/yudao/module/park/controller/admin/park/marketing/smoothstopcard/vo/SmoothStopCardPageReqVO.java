package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 畅停卡分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SmoothStopCardPageReqVO extends PageParam {

    @Schema(description = "[卡名称]", example = "李四")
    private String cardName;

    @Schema(description = "[卡种类型]如:日卡/周卡/月卡/季卡/年卡", example = "1")
    private String cardType;

    @Schema(description = "[有效天数]")
    private Integer validDays;

    @Schema(description = "[生效时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] effectiveTime;

    @Schema(description = "[失效时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] expireTime;

    @Schema(description = "[持有者ID] 关联park_user.id", example = "27719")
    private Long holderId;

    @Schema(description = "[可绑定车牌数]")
    private Integer bindCarLimit;

    @Schema(description = "[已绑定车牌号] 逗号分隔的字符串")
    private String boundCarNumbers;

    @Schema(description = "[适用车场ID列表] 逗号分隔的字符串，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[卡种描述]", example = "你说的对")
    private String description;

    @Schema(description = "[创建时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

    @Schema(description = "[备注]", example = "你猜")
    private String remark;

}
