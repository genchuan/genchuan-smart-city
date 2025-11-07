package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetdatamng.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产数据管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetDataMngRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17423")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "资产数据ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1418")
    @ExcelProperty("资产数据ID")
    private String assetDataId;

    @Schema(description = "关联资产分类ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15050")
    @ExcelProperty("关联资产分类ID")
    private String relAssetCatId;

    @Schema(description = "关联资产分类名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("关联资产分类名称")
    private String relAssetCatName;

    @Schema(description = "资产编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("资产编码")
    private String assetCode;

    @Schema(description = "资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("资产名称")
    private String assetName;

    @Schema(description = "资产状态", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @ExcelProperty("资产状态")
    private String assetStatus;

    @Schema(description = "安装时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("安装时间")
    private LocalDateTime installTime;

    @Schema(description = "使用年限")
    @ExcelProperty("使用年限")
    private Integer serviceLife;

    @Schema(description = "资产负责人")
    @ExcelProperty("资产负责人")
    private String assetManager;

    @Schema(description = "资产负责人电话")
    @ExcelProperty("资产负责人电话")
    private String assetManagerTel;

    @Schema(description = "资产描述")
    @ExcelProperty("资产描述")
    private String assetDesc;

    @Schema(description = "创建人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人")
    private String createUser;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createdTime;

    @Schema(description = "更新人")
    @ExcelProperty("更新人")
    private String updateUser;

    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updatedTime;

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