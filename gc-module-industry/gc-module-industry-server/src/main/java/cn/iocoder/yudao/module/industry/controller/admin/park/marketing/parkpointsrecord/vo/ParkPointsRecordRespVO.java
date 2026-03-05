package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrecord.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 积分变动记录 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPointsRecordRespVO {

    @Schema(description = "[主键ID] 积分变动记录唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "25687")
    @ExcelProperty("[主键ID] 积分变动记录唯一标识")
    private Long id;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "10044")
    @ExcelProperty("[用户ID] 用户唯一标识")
    private Long userId;

    @Schema(description = "[变动类型] 如:增加/减少", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[变动类型] 如:增加/减少")
    private String pointsType;

    @Schema(description = "[变动积分] 本次积分变动数值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[变动积分] 本次积分变动数值")
    private BigDecimal pointsAmount;

    @Schema(description = "[触发来源] 如:订单支付/积分兑换/活动奖励/过期扣除", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[触发来源] 如:订单支付/积分兑换/活动奖励/过期扣除")
    private String triggerSource;

    @Schema(description = "[关联ID] 关联业务ID，如订单ID/兑换ID/活动ID", example = "29193")
    @ExcelProperty("[关联ID] 关联业务ID，如订单ID/兑换ID/活动ID")
    private Long relatedId;

    @Schema(description = "[变动时间] 积分发生变动的时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[变动时间] 积分发生变动的时间")
    private LocalDateTime changeTime;

    @Schema(description = "[变动后余额] 本次积分变动后的积分余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[变动后余额] 本次积分变动后的积分余额")
    private BigDecimal balanceAfter;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 积分变动相关备注说明", example = "你猜")
    @ExcelProperty("[备注] 积分变动相关备注说明")
    private String remark;

    @Schema(description = "[通用扩展字段1] 通用扩展字段1")
    @ExcelProperty("[通用扩展字段1] 通用扩展字段1")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2] 通用扩展字段2")
    @ExcelProperty("[通用扩展字段2] 通用扩展字段2")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3] 通用扩展字段3")
    @ExcelProperty("[通用扩展字段3] 通用扩展字段3")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4] 通用扩展字段4")
    @ExcelProperty("[通用扩展字段4] 通用扩展字段4")
    private String extCommon4;

}
