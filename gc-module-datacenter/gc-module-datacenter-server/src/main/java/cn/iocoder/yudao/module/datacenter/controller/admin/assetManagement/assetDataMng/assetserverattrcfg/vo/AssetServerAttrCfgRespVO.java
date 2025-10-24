package cn.iocoder.yudao.module.datacenter.controller.admin.assetManagement.assetDataMng.assetserverattrcfg.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资产服务端属性配置 Response VO")
@Data
@ExcelIgnoreUnannotated
public class AssetServerAttrCfgRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "19978")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "服务端属性ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "6917")
    @ExcelProperty("服务端属性ID")
    private String assetServerAttrId;

    @Schema(description = "关联资产ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "4342")
    @ExcelProperty("关联资产ID")
    private String relAssetId;

    @Schema(description = "关联资产名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @ExcelProperty("关联资产名称")
    private String relAssetName;

    @Schema(description = "属性名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @ExcelProperty("属性名称")
    private String attrName;

    @Schema(description = "属性代码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性代码")
    private String attrCode;

    @Schema(description = "属性数据类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("属性数据类型")
    private String attrDataType;

    @Schema(description = "属性值", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("属性值")
    private String attrValue;

    @Schema(description = "采集频率")
    @ExcelProperty("采集频率")
    private String collectFreq;

    @Schema(description = "最后采集时间")
    @ExcelProperty("最后采集时间")
    private LocalDateTime lastCollectTime;

    @Schema(description = "配置时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("配置时间")
    private LocalDateTime cfgTime;

    @Schema(description = "操作人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作人")
    private String operUser;

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