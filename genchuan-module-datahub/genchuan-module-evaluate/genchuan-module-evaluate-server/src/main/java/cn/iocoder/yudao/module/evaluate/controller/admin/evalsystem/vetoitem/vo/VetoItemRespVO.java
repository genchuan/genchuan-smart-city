package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.vetoitem.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 否决项 Response VO")
@Data
@ExcelIgnoreUnannotated
public class VetoItemRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "15184")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "否决项ID（UUID）", example = "10952")
    @ExcelProperty("否决项ID（UUID）")
    private String vetoItemId;

    @Schema(description = "否决项名称", example = "赵六")
    @ExcelProperty("否决项名称")
    private String name;

    @Schema(description = "适用对象类型ID（关联sys_object_type.type_id）", example = "31212")
    @ExcelProperty("适用对象类型ID（关联sys_object_type.type_id）")
    private String objectTypeId;

    @Schema(description = "否决条件")
    @ExcelProperty("否决条件")
    private String condition;

    @Schema(description = "生效周期")
    @ExcelProperty("生效周期")
    private String validCycle;

    @Schema(description = "否决项数量", example = "20951")
    @ExcelProperty("否决项数量")
    private Integer count;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "17190")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID（关联sys_user.user_id）")

    private String updateBy;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

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
