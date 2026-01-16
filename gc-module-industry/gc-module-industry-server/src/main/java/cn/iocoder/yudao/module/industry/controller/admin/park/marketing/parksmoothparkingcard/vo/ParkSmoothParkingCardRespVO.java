package cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 畅停卡 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkSmoothParkingCardRespVO {

    @Schema(description = "[主键ID] 畅停卡唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "4747")
    @ExcelProperty("[主键ID] 畅停卡唯一标识")
    private Long id;

    @Schema(description = "[卡码] 唯一卡码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[卡码] 唯一卡码")
    private String cardCode;

    @Schema(description = "[卡种类型] 如:日卡/周卡/月卡/季卡/年卡/通用卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[卡种类型] 如:日卡/周卡/月卡/季卡/年卡/通用卡")
    private String cardType;

    @Schema(description = "[卡名称] 畅停卡名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @ExcelProperty("[卡名称] 畅停卡名称")
    private String cardName;

    @Schema(description = "[适用范围类型] 如:全局/区域/车场", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[适用范围类型] 如:全局/区域/车场")
    private String applyScopeType;

    @Schema(description = "[适用范围值] 车场ID或12位行政区全码，英文逗号分隔")
    @ExcelProperty("[适用范围值] 车场ID或12位行政区全码，英文逗号分隔")
    private String applyScopeValue;

    @Schema(description = "[有效天数] 卡片有效天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[有效天数] 卡片有效天数")
    private Integer validDays;

    @Schema(description = "[原价] 畅停卡原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "27581")
    @ExcelProperty("[原价] 畅停卡原价")
    private BigDecimal originalPrice;

    @Schema(description = "[售价] 畅停卡实际销售价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "4394")
    @ExcelProperty("[售价] 畅停卡实际销售价格")
    private BigDecimal salePrice;

    @Schema(description = "[状态] 如:未激活/已激活/已过期/已注销", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[状态] 如:未激活/已激活/已过期/已注销")
    private String status;

    @Schema(description = "[持卡人ID] 持卡人用户ID", example = "14828")
    @ExcelProperty("[持卡人ID] 持卡人用户ID")
    private Long userId;

    @Schema(description = "[激活时间] 卡片激活时间")
    @ExcelProperty("[激活时间] 卡片激活时间")
    private LocalDateTime activateTime;

    @Schema(description = "[过期时间] 卡片到期失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[过期时间] 卡片到期失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 畅停卡相关备注说明", example = "你说的对")
    @ExcelProperty("[备注] 畅停卡相关备注说明")
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
