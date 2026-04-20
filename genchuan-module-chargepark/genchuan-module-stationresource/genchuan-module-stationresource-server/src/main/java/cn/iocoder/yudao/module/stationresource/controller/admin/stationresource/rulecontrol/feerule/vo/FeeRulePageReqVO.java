package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 收费规则分页 Request VO")
@Data
public class FeeRulePageReqVO extends PageParam {

    @Schema(description = "[所属场站] 关联场站信息表 station_info.id，必填", example = "26061")
    private Long stationId;

    @Schema(description = "[费率类型] 如：停车收费/充电收费/混合收费", example = "1")
    private String rateType;

    @Schema(description = "[免费时长] 单位分钟")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private Integer[] freeTime;

    @Schema(description = "[计费单位] 如：小时/15分钟/次")
    private String chargeUnit;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", example = "2")
    private String status;

    @Schema(description = "[审核时间]")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] auditTime;

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

    @Schema(description = "[创建者] 创建人账号/姓名")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}
