package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价对象新增/修改 Request VO")
@Data
public class ObjectSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30082")
    private Long id;

    @Schema(description = "评价对象ID（UUID）", example = "22397")
    private String objectId;

    @Schema(description = "对象名称", example = "芋艿")
    @ExcelProperty(index = 0)
    private String name;

    @Schema(description = "对象编码")
    @ExcelProperty(index = 1)
    private String code;

    @Schema(description = "所属区域编码（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "对象类型ID（关联sys_object_type.type_id）", example = "10168")
    private String objectTypeId;

    @Schema(description = "负责人ID（关联sys_user.user_id）", example = "16200")
    private String managerId;

    @Schema(description = "负责人联系电话")
    @ExcelProperty(index = 5) // 新增：匹配第五列，赋值给userPhone
    private String managerPhone;

    @Schema(description = "关联网格/部门ID（关联eval_related_object.related_id）", example = "24916")
    private String relatedId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "1")
    @ExcelProperty(index = 7)
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "变更日志")
    private String changeLog;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "通用扩展字段3")
    private String extCommon3;

    @Schema(description = "通用扩展字段4")
    private String extCommon4;

    @Schema(description = "创建人（系统字段）")
    private String creator;
    @Schema(description = "更新人（系统字段）")
    private String updater;

    // ===== 新增Excel导入专用字段（只用于接收名称，加@ExcelProperty）=====
    @Schema(description = "所属区域名称（仅Excel导入用）")
    @ExcelProperty(index = 2) // 匹配Excel表头“所属区域”
    private String areaName; // 仅导入时接收“上海市”等名称

    @Schema(description = "对象类型名称（仅Excel导入用）")
    @ExcelProperty(index = 3) // 匹配Excel表头“对象类型”
    private String objectTypeName; // 仅导入时接收“事业单位”等名称

    @Schema(description = "负责人名称（仅Excel导入用）")
    @ExcelProperty(index = 4) // 匹配Excel表头“负责人”
    private String managerName; // 仅导入时接收“李四”等名称

    @Schema(description = "关联网格名称（仅Excel导入用）")
    @ExcelProperty(index = 6) // 匹配Excel表头“关联网格/部门”
    private String relatedName; // 仅导入时接收“技术研发部”等名称

    @Schema(description = "创建人名称")
    private String createUserName;
}