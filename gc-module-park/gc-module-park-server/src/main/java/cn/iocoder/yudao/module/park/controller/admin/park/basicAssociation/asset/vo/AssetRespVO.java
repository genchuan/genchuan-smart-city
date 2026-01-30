package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.asset.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产-thingsboard Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30235")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产编码")
    private String assetCode;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "资产类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("资产类型")
    private String assetType;

    @Schema(description = "所属区域编码")
    @ExcelProperty("所属区域编码")
    private String regionCode;

    @Schema(description = "状态：正常/停用", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("状态：正常/停用")
    private String assetStatus;

    @Schema(description = "入账时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("入账时间")
    private LocalDateTime entryTime;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime assetCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime assetUpdateTime;

    @Schema(description = "业务备注", example = "你说的对")
    @ExcelProperty("业务备注")
    private String assetRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}