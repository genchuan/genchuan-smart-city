package cn.iocoder.yudao.module.park.controller.admin.park.marketing.smoothstopcard.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 畅停卡 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SmoothStopCardRespVO {

    @Schema(description = "[主键ID] 畅停卡唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "24819")
    @ExcelProperty("[主键ID] 畅停卡唯一标识")
    private Long id;

    @Schema(description = "[卡名称]", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[卡名称]")
    private String cardName;

    @Schema(description = "[卡种类型]如:日卡/周卡/月卡/季卡/年卡", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("[卡种类型]如:日卡/周卡/月卡/季卡/年卡")
    private String cardType;

    @Schema(description = "[有效天数]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[有效天数]")
    private Integer validDays;

    @Schema(description = "[生效时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[生效时间]")
    private LocalDateTime effectiveTime;

    @Schema(description = "[失效时间]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[失效时间]")
    private LocalDateTime expireTime;

    @Schema(description = "[持有者ID] 关联park_user.id", requiredMode = Schema.RequiredMode.REQUIRED, example = "27719")
    @ExcelProperty("[持有者ID] 关联park_user.id")
    private Long holderId;

    @Schema(description = "[可绑定车牌数]", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[可绑定车牌数]")
    private Integer bindCarLimit;

    @Schema(description = "[已绑定车牌号] 逗号分隔的字符串")
    @ExcelProperty("[已绑定车牌号] 逗号分隔的字符串")
    private String boundCarNumbers;

    @Schema(description = "[适用车场ID列表] 逗号分隔的字符串，关联park_lot.id")
    @ExcelProperty("[适用车场ID列表] 逗号分隔的字符串，关联park_lot.id")
    private String applyLotIds;

    @Schema(description = "[卡种描述]", example = "你说的对")
    @ExcelProperty("[卡种描述]")
    private String description;

    @Schema(description = "[创建时间]")
    @ExcelProperty("[创建时间]")
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

    @Schema(description = "[备注]", example = "你猜")
    @ExcelProperty("[备注]")
    private String remark;

}
