package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.managedmattermajor.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 管理事项分类表（含大类和小类） Response VO")
@Data
@ExcelIgnoreUnannotated
public class ManagedMatterMajorRespVO {

    @Schema(description = "事项ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "24635")
    @ExcelProperty("事项ID")
    private Long id;

    @Schema(description = "父类ID（关联本表id，大类为NULL）", example = "24213")
    @ExcelProperty("父类ID（关联本表id，大类为NULL）")
    private String parentId;

    @Schema(description = "事项代码（大类2位/小类3位）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("事项代码（大类2位/小类3位）")
    private String matterCode;

    @Schema(description = "事项名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("事项名称")
    private String matterName;

    @Schema(description = "事项说明")
    @ExcelProperty("事项说明")
    private String matterDesc;

    @Schema(description = "排序序号")
    @ExcelProperty("排序序号")
    private Integer sortNum;

    @Schema(description = "启用状态（1启用/0禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("启用状态（1启用/0禁用）")
    private String enableStatus;

    @Schema(description = "是否扩展类（仅小类使用：0标准类/1扩展类）")
    @ExcelProperty("是否扩展类（仅小类使用：0标准类/1扩展类）")
    private String isExtend;

    @Schema(description = "主管部门代码（仅小类使用）")
    @ExcelProperty("主管部门代码（仅小类使用）")
    private String deptCode;

    @Schema(description = "主管部门名称（仅小类使用）", example = "李四")
    @ExcelProperty("主管部门名称（仅小类使用）")
    private String deptName;

    @Schema(description = "工作流模型标识")
    @ExcelProperty("工作流模型标识")
    private String flowInstanceId;

    @Schema(description = "分类扩展字段2")
    @ExcelProperty("分类扩展字段2")
    private String extCategory2;

    @Schema(description = "通用扩展字段1")
    @ExcelProperty("通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @ExcelProperty("通用扩展字段2")
    private String extCommon2;

    @Schema(description = "创建时间", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}