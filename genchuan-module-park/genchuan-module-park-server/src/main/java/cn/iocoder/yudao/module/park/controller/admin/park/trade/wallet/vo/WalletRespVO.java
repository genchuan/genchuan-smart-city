package cn.iocoder.yudao.module.park.controller.admin.park.trade.wallet.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 用户钱包 Response VO")
@Data
@ExcelIgnoreUnannotated
public class WalletRespVO {

    @Schema(description = "[主键ID] 用户钱包唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20842")
    @ExcelProperty("[主键ID] 用户钱包唯一标识")
    private Long id;

    @Schema(description = "[用户ID] 用户唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "28666")
    @ExcelProperty("[用户ID] 用户唯一标识")
    private Long userId;

    @Schema(description = "[当前余额] 用户钱包当前余额", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[当前余额] 用户钱包当前余额")
    private BigDecimal balance;

    @Schema(description = "[状态] 如:冻结/正常", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态] 如:冻结/正常")
    private String status;

    @Schema(description = "[版本号] 版本号(乐观锁)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[版本号] 版本号(乐观锁)")
    private Integer version;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[通用扩展字段1]")
    @ExcelProperty("[通用扩展字段1]")
    private String extCommon1;

    @Schema(description = "[通用扩展字段2]")
    @ExcelProperty("[通用扩展字段2]")
    private String extCommon2;

    @Schema(description = "[通用扩展字段3]")
    @ExcelProperty("[通用扩展字段3]")
    private String extCommon3;

    @Schema(description = "[通用扩展字段4]")
    @ExcelProperty("[通用扩展字段4]")
    private String extCommon4;

}
