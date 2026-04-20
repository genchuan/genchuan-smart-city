package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收费规则新增/修改 Request VO")
@Data
public class FeeRuleSaveReqVO {

    @Schema(description = "[主键ID] 主键，BIGINT，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "25831")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info.id，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "26061")
    @NotNull(message = "[所属场站] 关联场站信息表 station_info.id，必填不能为空")
    private Long stationId;

    @Schema(description = "[费率类型] 如：停车收费/充电收费/混合收费", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "[费率类型] 如：停车收费/充电收费/混合收费不能为空")
    private String rateType;

    @Schema(description = "[免费时长] 单位分钟")
    private Integer freeTime;

    @Schema(description = "[计费单位] 如：小时/15分钟/次", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "[计费单位] 如：小时/15分钟/次不能为空")
    private String chargeUnit;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "[状态] 如：待生效/已生效/已禁用不能为空")
    private String status;

    @Schema(description = "[审核时间]")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user.id", example = "19185")
    private Long auditUserId;

    @Schema(description = "[订单匹配率] 默认0")
    private BigDecimal matchRate;

    @Schema(description = "[首小时价格]", example = "19983")
    private BigDecimal firstHourPrice;

    @Schema(description = "[后续阶梯价格]varchar存储", example = "27651")
    private String stepPrice;

    @Schema(description = "[封顶价格]", example = "27701")
    private BigDecimal maxPrice;

    @Schema(description = "[峰谷电价配置] varchar存储")
    private String peakValleyConfig;

    @Schema(description = "[会员优惠配置]varchar存储")
    private String memberConfig;

    @Schema(description = "[备注]", example = "你说的对")
    private String remark;

    @Schema(description = "[备用字段1]")
    private String reserve1;

    @Schema(description = "[备用字段2]")
    private String reserve2;

}
