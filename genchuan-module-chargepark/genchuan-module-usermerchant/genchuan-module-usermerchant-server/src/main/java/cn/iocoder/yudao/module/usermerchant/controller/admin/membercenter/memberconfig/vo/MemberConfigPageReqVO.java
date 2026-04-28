package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 会员配置分页 Request VO")
@Data
public class MemberConfigPageReqVO extends PageParam {

    @Schema(description = "是否开启积分抵扣")
    private Boolean pointTradeDeductEnable;

    @Schema(description = "积分抵扣(单位：分)", example = "22325")
    private Integer pointTradeDeductUnitPrice;

    @Schema(description = "积分抵扣最大值", example = "9825")
    private Integer pointTradeDeductMaxPrice;

    @Schema(description = "1 元赠送多少分")
    private Long pointTradeGivePoint;

    @Schema(description = "创建者")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新者")
    private String updater;

    @Schema(description = "更新时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] updateTime;

}