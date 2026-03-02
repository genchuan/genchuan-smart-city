package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Schema(description = "管理后台 - 评价对象创建 Request VO")
@Data
public class ObjectCreateReqVO {

    @Schema(description = "对象名称", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotBlank(message = "对象名称不能为空")
    @Length(max = 100, message = "对象名称长度不能超过100个字符")
    private String name;

    @Schema(description = "对象编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "OBJ001")
    @NotBlank(message = "对象编码不能为空")
    @Length(max = 50, message = "对象编码长度不能超过50个字符")
    private String code;

    @Schema(description = "所属区域编码", requiredMode = Schema.RequiredMode.REQUIRED, example = "110101")
    @NotBlank(message = "所属区域不能为空")
    private String areaCode;

    @Schema(description = "对象类型ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "type_001")
    @NotBlank(message = "对象类型不能为空")
    private String objectTypeId;

    @Schema(description = "负责人ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "user_001")
    @NotBlank(message = "负责人不能为空")
    private String managerId;

    @Schema(description = "联系电话", requiredMode = Schema.RequiredMode.REQUIRED, example = "13800138000")
    @NotBlank(message = "联系电话不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String phone;

    @Schema(description = "关联网格/部门ID", example = "related_001")
    private String relatedId;

    @Schema(description = "状态ID", example = "status_001")
    private String statusId = "status_001"; // 默认启用状态

    @Schema(description = "变更日志")
    @Length(max = 1000, message = "变更日志长度不能超过1000个字符")
    private String changeLog;

    @Schema(description = "通用扩展字段1")
    @Length(max = 100, message = "通用扩展字段1长度不能超过100个字符")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    @Length(max = 100, message = "通用扩展字段2长度不能超过100个字符")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    @Length(max = 100, message = "通用扩展字段3长度不能超过100个字符")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    @Length(max = 100, message = "通用扩展字段4长度不能超过100个字符")
    private String extCommon4;
}
