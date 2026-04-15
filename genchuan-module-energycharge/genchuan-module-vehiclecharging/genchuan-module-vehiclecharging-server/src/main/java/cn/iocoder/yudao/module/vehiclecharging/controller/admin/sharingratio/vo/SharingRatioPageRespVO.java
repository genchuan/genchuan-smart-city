package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "汽车充电 - 分账比例响应 VO")
@Data
public class SharingRatioPageRespVO {

    @Schema(description = "主键ID", example = "1")
    private Long id;

    @Schema(description = "方案编号", example = "SR-20250301-001")
    private String sharingCode;

    @Schema(description = "方案名称", example = "场站合作基础分账")
    private String sharingName;

    @Schema(description = "合作方", example = "XX 能源科技有限公司")
    private String cooperator;

    @Schema(description = "分账类型", example = "电费分账")
    private String sharingType;

    @Schema(description = "分账比例(%)", example = "30.00")
    private BigDecimal sharingRatio;

    @Schema(description = "适用场站", example = "泉州丰泽场站，泉州晋江场站")
    private String applyStation;

    @Schema(description = "适用渠道", example = "小程序，APP")
    private String applyChannel;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "失效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "分账状态", example = "已生效")
    private String sharingStatus;

    @Schema(description = "备注", example = "2025 年度合作分账方案")
    private String remark;

    @Schema(description = "创建者", example = "admin")
    private String creator;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

    @Schema(description = "更新时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;

}