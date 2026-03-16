package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 评价对象 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ObjectRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "30082")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价对象ID（UUID）", example = "22397")
    @ExcelProperty("评价对象ID（UUID）")
    private String objectId;

    @Schema(description = "对象名称", example = "芋艿")
    @ExcelProperty("对象名称")
    private String name;

    @Schema(description = "对象编码")
    @ExcelProperty("对象编码")
    private String code;

    @Schema(description = "所属区域编码（关联sys_area.area_code）")
    @ExcelProperty("所属区域编码（关联sys_area.area_code）")
    private String areaCode;

    @Schema(description = "对象类型ID（关联sys_object_type.type_id）", example = "10168")
    @ExcelProperty("对象类型ID（关联sys_object_type.type_id）")
    private String objectTypeId;

    @Schema(description = "负责人ID（关联sys_user.user_id）", example = "16200")
    @ExcelProperty("负责人ID（关联sys_user.user_id）")
    private String managerId;

    @Schema(description = "关联网格/部门ID（关联eval_related_object.related_id）", example = "24916")
    @ExcelProperty("关联网格/部门ID（关联eval_related_object.related_id）")
    private String relatedId;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "16713")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private Integer statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建人（系统字段）")
    @ExcelProperty("创建人ID（系统字段）")
    private String creator;
    @Schema(description = "更新人（系统字段）")
    @ExcelProperty("更新人（系统字段）")
    private String updater;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

    @Schema(description = "更新人ID（关联sys_user.user_id）")
    @ExcelProperty("更新人ID（关联sys_user.user_id）")
    private String updateBy;

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime bizUpdateTime;

    @Schema(description = "变更日志")
    @ExcelProperty("变更日志")
    private String changeLog;

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

    @Schema(description = "更新时间（业务字段）")
    @ExcelProperty("更新时间（业务字段）")
    private LocalDateTime updateTime;
    // ========== 新增联表展示字段（前端需要的展示值） ==========
    @Schema(description = "所属区域名称（关联sys_area.area_name）", example = "北京市朝阳区")
    @ExcelProperty("所属区域名称")
    private String areaName; // 所属区域名称

    @Schema(description = "对象类型名称（关联sys_object_type.name）", example = "企业")
    @ExcelProperty("对象类型名称")
    private String objectTypeName; // 对象类型名称

    @Schema(description = "负责人姓名（关联sys_user.user_name）", example = "张三")
    @ExcelProperty("负责人姓名")
    private String managerName; // 负责人姓名

    @Schema(description = "负责人联系电话（关联sys_user.user_phone）", example = "13800138000")
    @ExcelProperty("负责人联系电话")
    private String managerPhone; // 负责人联系电话

    @Schema(description = "关联网格/部门名称（关联eval_related_object.related_name）", example = "望京网格")
    @ExcelProperty("关联网格/部门名称")
    private String relatedName; // 关联网格/部门名称

    @Schema(description = "状态名称（关联sys_status.name）", example = "启用")
    @ExcelProperty("状态名称")
    private String statusName; // 状态名称（启用/停用）

    @Schema(description = "创建人姓名（关联sys_user.user_name）", example = "李四")
    @ExcelProperty("创建人姓名")
    private String createUserName; // 创建人姓名

    @Schema(description = "更新人姓名（关联sys_user.user_name）", example = "王五")
    @ExcelProperty("更新人姓名")
    private String updateUserName; // 更新人姓名（停用场景=停用操作人）

    @Schema(description = "变更日志（截取前50字）", example = "2026-02-25：新增评价对象，负责人张三")
    @ExcelProperty("变更日志（截取前50字）")
    private String changeLogShort; // 变更日志简写（截取前50字）

    @Schema(description = "status_id=1的记录数（传指定statusId时，仅该状态有值，其余为0）")
    @ExcelProperty("status_id=1的记录数")
    private Long status1Count;

    @Schema(description = "status_id=2的记录数")
    @ExcelProperty("status_id=2的记录数")
    private Long status2Count;

    @Schema(description = "符合条件的总记录数（过滤deleted=1后）")
    @ExcelProperty("符合条件的总记录数（过滤deleted=1后）")
    private Long totalCount;
}