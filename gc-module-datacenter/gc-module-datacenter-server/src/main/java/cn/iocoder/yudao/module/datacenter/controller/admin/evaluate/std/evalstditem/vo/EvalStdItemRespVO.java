package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstditem.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import java.math.BigDecimal;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 标准项管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalStdItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "标准项ID")
    @ExcelProperty("标准项ID")
    private String stdItemId;

    @Schema(description = "标准项名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准项名称")
    private String stdItemName;

    @Schema(description = "标准项编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准项编码")
    private String stdItemCode;

    @Schema(description = "所属标准分类ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属标准分类ID")
    private String stdCatId;

    @Schema(description = "所属标准分类名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("所属标准分类名称")
    private String stdCatName;

    @Schema(description = "标准等级", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("标准等级")
    private String stdLevel;

    @Schema(description = "分数下限", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("分数下限")
    private BigDecimal scoreMin;

    @Schema(description = "分数上限")
    @ExcelProperty("分数上限")
    private BigDecimal scoreMax;

    @Schema(description = "标准描述")
    @ExcelProperty("标准描述")
    private String stdDesc;

    @Schema(description = "排序序号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("排序序号")
    private Integer sortNum;

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