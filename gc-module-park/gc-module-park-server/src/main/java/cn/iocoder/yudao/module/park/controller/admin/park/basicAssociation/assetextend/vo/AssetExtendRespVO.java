package cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.assetextend.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产扩展 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetExtendRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "26436")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产类型：车场/车库/路侧泊位/车位/出入口", example = "1")
    @ExcelProperty("资产类型：车场/车库/路侧泊位/车位/出入口")
    private String assetType;

    @Schema(description = "资产名称", example = "王五")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "唯一资产编码")
    @ExcelProperty("唯一资产编码")
    private String assetCode;

    @Schema(description = "状态：正常/故障/停用", example = "2")
    @ExcelProperty("状态：正常/故障/停用")
    private String assetStatus;

    @Schema(description = "所属区域编码")
    @ExcelProperty("所属区域编码")
    private String regionCode;

    @Schema(description = "详细地址")
    @ExcelProperty("详细地址")
    private String address;

    @Schema(description = "经度")
    @ExcelProperty("经度")
    private BigDecimal longitude;

    @Schema(description = "纬度")
    @ExcelProperty("纬度")
    private BigDecimal latitude;

    @Schema(description = "业务创建时间")
    @ExcelProperty("业务创建时间")
    private LocalDateTime assetCreateTime;

    @Schema(description = "业务更新时间")
    @ExcelProperty("业务更新时间")
    private LocalDateTime assetUpdateTime;

    @Schema(description = "业务备注", example = "随便")
    @ExcelProperty("业务备注")
    private String assetRemark;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}