package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdataqualck.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产数据质量检查 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetDataQualCkRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1042")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "质量检查ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31677")
    @ExcelProperty("质量检查ID")
    private String assetDataQualId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9910")
    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @Schema(description = "检查数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("检查数据类型")
    private String ckDataType;

    @Schema(description = "检查项名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("检查项名称")
    private String ckItemName;

    @Schema(description = "检查项代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查项代码")
    private String ckItemCode;

    @Schema(description = "检查结果", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查结果")
    private String ckResult;

    @Schema(description = "错误描述")
    @ExcelProperty("错误描述")
    private String errorDesc;

    @Schema(description = "检查时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查时间")
    private LocalDateTime ckTime;

    @Schema(description = "检查方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("检查方式")
    private String ckMethod;

    @Schema(description = "处理状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("处理状态")
    private String handleStatus;

    @Schema(description = "处理人")
    @ExcelProperty("处理人")
    private String handleUser;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "分类扩展字段1")
    @ExcelProperty("分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "系统创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTime;

}