package cn.iocoder.yudao.module.usermerchant.controller.admin.membercenter.memberconfig.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 会员配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class MemberConfigPageRespVO {

    @Schema(description = "自增主键", requiredMode = Schema.RequiredMode.REQUIRED, example = "29169")
    @ExcelProperty("自增主键")
    private Long id;

    @Schema(description = "是否开启积分抵扣", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("是否开启积分抵扣")
    private Boolean pointTradeDeductEnable;

    @Schema(description = "积分抵扣(单位：分)", requiredMode = Schema.RequiredMode.REQUIRED, example = "22325")
    @ExcelProperty("积分抵扣(单位：分)")
    private Integer pointTradeDeductUnitPrice;

    @Schema(description = "积分抵扣最大值", example = "9825")
    @ExcelProperty("积分抵扣最大值")
    private Integer pointTradeDeductMaxPrice;

    @Schema(description = "1 元赠送多少分")
    @ExcelProperty("1 元赠送多少分")
    private Long pointTradeGivePoint;

    @Schema(description = "创建者")
    @ExcelProperty("创建者")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新者")
    @ExcelProperty("更新者")
    private String updater;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}