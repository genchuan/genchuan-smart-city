package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parkpointsrule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 积分规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPointsRuleRespVO {

    @Schema(description = "[主键ID] 积分规则唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "13529")
    @ExcelProperty("[主键ID] 积分规则唯一标识")
    private Long id;

    @Schema(description = "[规则名称] 积分规则名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("[规则名称] 积分规则名称")
    private String ruleName;

    @Schema(description = "[触发类型] 如:停车消费/充值/分享/投诉反馈/会员任务", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[触发类型] 如:停车消费/充值/分享/投诉反馈/会员任务")
    private String triggerType;

    @Schema(description = "[固定积分值] 固定赠送的积分值")
    @ExcelProperty("[固定积分值] 固定赠送的积分值")
    private BigDecimal pointsAmount;

    @Schema(description = "[积分比例] 积分计算比例，0~1 小数")
    @ExcelProperty("[积分比例] 积分计算比例，0~1 小数")
    private BigDecimal pointsRatio;

    @Schema(description = "[单日上限] 单条规则单日可获得的积分上限")
    @ExcelProperty("[单日上限] 单条规则单日可获得的积分上限")
    private BigDecimal upperLimit;

    @Schema(description = "[状态] 如:禁用/启用", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[状态] 如:禁用/启用")
    private String status;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 积分规则相关备注说明", example = "随便")
    @ExcelProperty("[备注] 积分规则相关备注说明")
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
