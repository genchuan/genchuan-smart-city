package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxitem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 指标项管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalIdxItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指标项ID")
    @ExcelProperty("指标项ID")
    private String idxItemId;

    @Schema(description = "指标项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标项名称")
    private String idxItemName;

    @Schema(description = "指标项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标项编码")
    private String idxItemCode;

    @Schema(description = "所属指标分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属指标分类ID")
    private String idxCatId;

    @Schema(description = "所属指标分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属指标分类名称")
    private String idxCatName;

    @Schema(description = "指标类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指标类型")
    private String idxType;

    @Schema(description = "计算方式", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("计算方式")
    private String calcMethod;

    @Schema(description = "数据来源表")
    @ExcelProperty("数据来源表")
    private String dataSourceTable;

    @Schema(description = "数据来源字段")
    @ExcelProperty("数据来源字段")
    private String dataSourceField;

    @Schema(description = "达标阈值")
    @ExcelProperty("达标阈值")
    private String qualifiedThreshold;

    @Schema(description = "指标描述")
    @ExcelProperty("指标描述")
    private String idxDesc;

    @Schema(description = "启用状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("启用状态")
    private String enableStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人(业务)")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间(业务)")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    @ExcelProperty("更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    @ExcelProperty("更新时间(业务)")
    private LocalDateTime updateTimeBiz;

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

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}