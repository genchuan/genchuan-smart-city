package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 期卡套餐分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ParkPeriodPackagePageReqVO extends PageParam {

    @Schema(description = "[套餐名称] 期卡套餐名称", example = "李四")
    private String packageName;

    @Schema(description = "[套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义", example = "2")
    private String packageType;

    @Schema(description = "[适用车场ID列表] JSON 格式字符串，存储车场ID集合")
    private String applyLotIds;

    @Schema(description = "[适用车位类型] 普通 / 新能源 / 专用", example = "2")
    private String spaceType;

    @Schema(description = "[原价] 套餐原价", example = "11554")
    private BigDecimal originalPrice;

    @Schema(description = "[售价] 套餐实际销售价格", example = "19230")
    private BigDecimal salePrice;

    @Schema(description = "[有效天数] 套餐有效天数")
    private Integer validDays;

    @Schema(description = "[状态] 上架 / 下架 / 暂停销售", example = "2")
    private String status;

    @Schema(description = "[销售数量] 套餐累计销售数量", example = "7244")
    private Integer salesCount;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[备注] 期卡套餐相关说明", example = "随便")
    private String remark;

    @Schema(description = "[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    private String extCommon4;

}
