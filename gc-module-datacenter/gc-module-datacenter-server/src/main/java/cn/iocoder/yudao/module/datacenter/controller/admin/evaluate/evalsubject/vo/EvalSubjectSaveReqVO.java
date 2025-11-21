package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.evalsubject.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价主体管理新增/修改 Request VO")
@Data
public class EvalSubjectSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long id;

    @Schema(description = "评价主体ID")
    private String evalSubjectId;

    @Schema(description = "评价主体名称", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "评价主体名称不能为空")
    private String evalSubjectName;

    @Schema(description = "主体编码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主体编码不能为空")
    private String subjectCode;

    @Schema(description = "主体类型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主体类型不能为空")
    private String subjectType;

    @Schema(description = "所属部门ID")
    private String deptId;

    @Schema(description = "所属部门名称")
    private String deptName;

    @Schema(description = "成员IDs")
    private String memberIds;

    @Schema(description = "成员姓名")
    private String memberNames;

    @Schema(description = "联系人", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系人不能为空")
    private String contactPerson;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "联系电话不能为空")
    private String contactPhone;

    @Schema(description = "主体状态", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主体状态不能为空")
    private String subjectStatus;

    @Schema(description = "创建人(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人(业务)不能为空")
    private String createUserBiz;

    @Schema(description = "创建时间(业务)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "创建时间(业务)不能为空")
    private LocalDateTime createTimeBiz;

    @Schema(description = "更新人(业务)")
    private String updateUserBiz;

    @Schema(description = "更新时间(业务)")
    private LocalDateTime updateTimeBiz;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

}