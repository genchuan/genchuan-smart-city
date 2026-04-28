package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 收费规则 Response VO")
@Data
@ExcelIgnoreUnannotated
public class FeeRuleRespVO {

    @Schema(description = "[主键ID] 主键，BIGINT，自增", requiredMode = Schema.RequiredMode.REQUIRED, example = "25831")
    @ExcelProperty("[主键ID]")
    private Long id;

    @Schema(description = "[所属场站] 关联场站信息表 station_info.id，必填", requiredMode = Schema.RequiredMode.REQUIRED, example = "26061")
    @ExcelProperty("[所属场站]")
    private Long stationId;

    @Schema(description = "[费率类型] 如：停车收费/充电收费/混合收费", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[费率类型]")
    private String rateType;

    @Schema(description = "[免费时长] 单位分钟")
    @ExcelProperty("[免费时长]")
    private Integer freeTime;

    @Schema(description = "[计费单位] 如：小时/15分钟/次", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[计费单位]")
    private String chargeUnit;

    @Schema(description = "[状态] 如：待生效/已生效/已禁用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态]")
    private String status;

    @Schema(description = "[审核时间]")
    @ExcelProperty("[审核时间]")
    private LocalDateTime auditTime;

    @Schema(description = "[审核人] 关联芋道用户表 system_user.id", example = "19185")
    @ExcelProperty("[审核人]")
    private Long auditUserId;

    @Schema(description = "[订单匹配率] 默认0")
    @ExcelProperty("[订单匹配率]")
    private BigDecimal matchRate;

    @Schema(description = "[首小时价格]", example = "19983")
    @ExcelProperty("[首小时价格]")
    private BigDecimal firstHourPrice;

    @Schema(description = "[后续阶梯价格]varchar存储", example = "27651")
    @ExcelProperty("[后续阶梯价格]")
    private String stepPrice;

    @Schema(description = "[封顶价格]", example = "27701")
    @ExcelProperty("[封顶价格]")
    private BigDecimal maxPrice;

    @Schema(description = "[峰谷电价配置] varchar存储")
    @ExcelProperty("[峰谷电价配置]")
    private String peakValleyConfig;

    @Schema(description = "[会员优惠配置]varchar存储")
    @ExcelProperty("[会员优惠配置]")
    private String memberConfig;

    @Schema(description = "[备注]", example = "你说的对")
    @ExcelProperty("[备注]")
    private String remark;

    @Schema(description = "[备用字段1]")
    @ExcelProperty("[备用字段1]")
    private String reserve1;

    @Schema(description = "[备用字段2]")
    @ExcelProperty("[备用字段2]")
    private String reserve2;

    @Schema(description = "[创建者] 创建人账号/姓名")
    @ExcelProperty("[创建者]")
    private String creator;

    @Schema(description = "[更新者] 更新人账号/姓名")
    @ExcelProperty("[更新者]")
    private String updater;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间]")
    private LocalDateTime createTime;

    @Schema(description = "[更新时间] 记录最后更新时间")
    @ExcelProperty("[更新时间]")
    private LocalDateTime updateTime;

}
