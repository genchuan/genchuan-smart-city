package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 评价主体管理 Response VO")
@Data
@ExcelIgnoreUnannotated
public class EvalSubjectRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价主体ID")
    @ExcelProperty("评价主体ID")
    private String evalSubjectId;

    @Schema(description = "评价主体名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("评价主体名称")
    private String evalSubjectName;

    @Schema(description = "主体编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主体编码")
    private String subjectCode;

    @Schema(description = "主体类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主体类型")
    private String subjectType;

    @Schema(description = "所属部门ID")
    @ExcelProperty("所属部门ID")
    private String deptId;

    @Schema(description = "所属部门名称")
    @ExcelProperty("所属部门名称")
    private String deptName;

    @Schema(description = "成员IDs")
    @ExcelProperty("成员IDs")
    private String memberIds;

    @Schema(description = "成员姓名")
    @ExcelProperty("成员姓名")
    private String memberNames;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系人")
    private String contactPerson;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("联系电话")
    private String contactPhone;

    @Schema(description = "主体状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主体状态")
    private String subjectStatus;

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