package cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkperiodpackage.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 期卡套餐 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ParkPeriodPackageRespVO {

    @Schema(description = "[主键ID] 期卡套餐唯一标识", requiredMode = Schema.RequiredMode.REQUIRED, example = "20313")
    @ExcelProperty("[主键ID] 期卡套餐唯一标识")
    private Long id;

    @Schema(description = "[套餐名称] 期卡套餐名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("[套餐名称] 期卡套餐名称")
    private String packageName;

    @Schema(description = "[套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[套餐类型] 日卡 / 周卡 / 月卡 / 季卡 / 年卡 / 自定义")
    private String packageType;

    @Schema(description = "[适用车场ID列表] JSON 格式字符串，存储车场ID集合", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[适用车场ID列表] JSON 格式字符串，存储车场ID集合")
    private String applyLotIds;

    @Schema(description = "[适用车位类型] 普通 / 新能源 / 专用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[适用车位类型] 普通 / 新能源 / 专用")
    private String spaceType;

    @Schema(description = "[原价] 套餐原价", requiredMode = Schema.RequiredMode.REQUIRED, example = "11554")
    @ExcelProperty("[原价] 套餐原价")
    private BigDecimal originalPrice;

    @Schema(description = "[售价] 套餐实际销售价格", requiredMode = Schema.RequiredMode.REQUIRED, example = "19230")
    @ExcelProperty("[售价] 套餐实际销售价格")
    private BigDecimal salePrice;

    @Schema(description = "[有效天数] 套餐有效天数", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("[有效天数] 套餐有效天数")
    private Integer validDays;

    @Schema(description = "[状态] 上架 / 下架 / 暂停销售", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("[状态] 上架 / 下架 / 暂停销售")
    private String status;

    @Schema(description = "[销售数量] 套餐累计销售数量", example = "7244")
    @ExcelProperty("[销售数量] 套餐累计销售数量")
    private Integer salesCount;

    @Schema(description = "[创建时间] 记录创建时间")
    @ExcelProperty("[创建时间] 记录创建时间")
    private LocalDateTime createTime;

    @Schema(description = "[备注] 期卡套餐相关说明", example = "随便")
    @ExcelProperty("[备注] 期卡套餐相关说明")
    private String remark;

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
