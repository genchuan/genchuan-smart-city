package cn.iocoder.yudao.module.vehiclecharging.controller.admin.sharingratio.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "管理后台 - 分账比例 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SharingRatioRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "7179")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "方案编号，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("方案编号，唯一")
    private String sharingCode;

    @Schema(description = "方案名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("方案名称")
    private String sharingName;

    @Schema(description = "合作方", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("合作方")
    private String cooperator;

    @Schema(description = "分账类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("分账类型")
    private String sharingType;

    @Schema(description = "分账比例（%）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分账比例（%）")
    private BigDecimal sharingRatio;

    @Schema(description = "适用场站，多个用逗号分隔")
    @ExcelProperty("适用场站，多个用逗号分隔")
    private String applyStation;

    @Schema(description = "适用渠道，多个用逗号分隔")
    @ExcelProperty("适用渠道，多个用逗号分隔")
    private String applyChannel;

    @Schema(description = "生效时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("生效时间")
    private LocalDateTime effectTime;

    @Schema(description = "失效时间")
    @ExcelProperty("失效时间")
    private LocalDateTime expireTime;

    @Schema(description = "分账状态：未生效/已生效/已失效", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("分账状态：未生效/已生效/已失效")
    private String sharingStatus;

    @Schema(description = "备注", example = "你猜")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}