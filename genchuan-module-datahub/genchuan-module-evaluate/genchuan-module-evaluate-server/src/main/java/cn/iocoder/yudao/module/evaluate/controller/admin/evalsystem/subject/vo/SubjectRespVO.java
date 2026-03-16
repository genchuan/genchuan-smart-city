package cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo;

import cn.idev.excel.annotation.ExcelIgnoreUnannotated;
import cn.idev.excel.annotation.ExcelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Schema(description = "管理后台 - 评价主体 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SubjectRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9636")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "评价主体ID（UUID）", example = "2007")
    @ExcelProperty("评价主体ID（UUID）")
    private String subjectId;

    @Schema(description = "主体名称", example = "赵六")
    @ExcelProperty("主体名称")
    private String name;

    @Schema(description = "主体编码")
    @ExcelProperty("主体编码")
    private String code;

    @Schema(description = "主体类型ID（关联sys_subject_type.type_id）", example = "24741")
    @ExcelProperty("主体类型ID（关联sys_subject_type.type_id）")
    private String subjectTypeId;

    @Schema(description = "联系人ID（关联sys_user.user_id）", example = "30257")
    @ExcelProperty("联系人ID（关联sys_user.user_id）")
    private String contactId;

    @Schema(description = "成员数量", example = "26756")
    @ExcelProperty("成员数量")
    private Integer memberCount;

    @Schema(description = "使用次数", example = "26")
    @ExcelProperty("使用次数")
    private Integer useCount;

    @Schema(description = "状态ID（关联sys_status.status_id）", example = "14834")
    @ExcelProperty("状态ID（关联sys_status.status_id）")
    private String statusId;

    @Schema(description = "创建人ID（关联sys_user.user_id）")
    @ExcelProperty("创建人ID（关联sys_user.user_id）")
    private String createBy;

    @Schema(description = "创建人ID（系统字段）")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间（业务字段）")
    @ExcelProperty("创建时间（业务字段）")
    private LocalDateTime bizCreateTime;

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
    @Schema(description = "更新时间")
    @ExcelProperty("更新时间")
    private LocalDateTime updateTime;
    // -------------------------- 新增联表展示字段 --------------------------
    @Schema(description = "主体类型名称（关联sys_subject_type）", example = "人工主体")
    @ExcelProperty("主体类型名称")
    private String subjectTypeName;

    @Schema(description = "状态名称（关联sys_status）", example = "启用")
    @ExcelProperty("状态名称")
    private String statusName;

    @Schema(description = "联系人姓名（关联sys_user）", example = "张三")
    @ExcelProperty("联系人姓名")
    private String contactName;

    @Schema(description = "联系人电话（关联sys_user）", example = "13800138000")
    @ExcelProperty("联系人电话")
    private String contactPhone;

    @Schema(description = "创建人姓名（关联sys_user）", example = "李四")
    @ExcelProperty("创建人")
    private String createUserName;

    @Schema(description = "更新人姓名（也可用作停用操作人）")
    private String updateByName;

    @Schema(description = "成员列表（仅人工主体返回，关联sys_user）", example = "[{\"userId\":\"xxx\",\"userName\":\"王五\",\"userPhone\":\"13900139000\"}]")
    private List<SubjectMemberListRespVO> memberList;
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

    // -------------------------- 内部成员VO（用于展示成员列表） --------------------------
    @Data
    public static class SubjectMemberListRespVO {
        @Schema(description = "成员用户ID", example = "uuid-xxx")
        private String userId;

        @Schema(description = "成员姓名", example = "王五")
        private String userName;

        @Schema(description = "成员联系电话", example = "13900139000")
        private String userPhone;

        @Schema(description = "加入时间", example = "2024-01-01 10:00:00")
        private LocalDateTime joinTime;

        @Schema(description = "评价主体Id")
        private String subjectId;
    }
}
