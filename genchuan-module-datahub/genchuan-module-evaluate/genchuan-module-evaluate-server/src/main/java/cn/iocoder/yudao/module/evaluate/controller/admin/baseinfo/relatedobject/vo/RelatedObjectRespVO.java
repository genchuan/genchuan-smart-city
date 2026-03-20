package cn.iocoder.yudao.module.evaluate.controller.admin.baseinfo.relatedobject.vo;

//import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
//import com.alibaba.excel.annotation.ExcelProperty;
import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 关联对象 Response VO")
@Data
@ExcelIgnoreUnannotated
public class RelatedObjectRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10475")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "关联对象ID（UUID）", example = "25166")
    @ExcelProperty("关联对象ID（UUID）")
    private String relatedId;

    @Schema(description = "关联对象名称", example = "芋艿")
    @ExcelProperty("关联对象名称")
    private String relatedName;

    @Schema(description = "关联对象类型：关联sys_object_type.type_id", example = "1")
    @ExcelProperty("关联对象类型：关联sys_object_type.type_id")
    private String relatedType;

    @Schema(description = "关联对象编码",example = "ffff")
    @ExcelProperty("关联对象编码")
    private String relatedCode;

    @Schema(description = "更新人，关联sys_user.user_id",example = "dfdfdf1")
    @ExcelProperty("更新人，关联sys_user.user_id")
    private String updateBy;

    @Schema(description = "上级关联对象ID（关联eval_related_object.related_id）", example = "4100")
    @ExcelProperty("上级关联对象ID（关联eval_related_object.related_id）")
    private String parentId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "489")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @ExcelProperty("通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @ExcelProperty("通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}