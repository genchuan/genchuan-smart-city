package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 系统功能指南 Response VO")
@Data
@ExcelIgnoreUnannotated
public class SystemFunctionGuideRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "147")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "指南ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "14500")
    @ExcelProperty("指南ID，唯一编码，UUID生成")
    private String systemFunctionGuideId;

    @Schema(description = "指南标题，如《地理编码坐标系配置指南》", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("指南标题，如《地理编码坐标系配置指南》")
    private String guideTitle;

    @Schema(description = "关联功能ID，关联系统功能ID，系统功能表（sys_system_function）", requiredMode = Schema.RequiredMode.REQUIRED, example = "21517")
    @ExcelProperty("关联功能ID，关联系统功能ID，系统功能表（sys_system_function）")
    private String relatedFunctionId;

    @Schema(description = "关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @ExcelProperty("关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）")
    private String relatedFunctionName;

    @Schema(description = "功能用途说明，如“配置坐标系，确保地理数据统一”")
    @ExcelProperty("功能用途说明，如“配置坐标系，确保地理数据统一”")
    private String functionPurpose;

    @Schema(description = "操作步骤（支持富文本，含截图）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("操作步骤（支持富文本，含截图）")
    private String operSteps;

    @Schema(description = "常见问题及解决方案，如“坐标系配置失败：检查代码唯一性”")
    @ExcelProperty("常见问题及解决方案，如“坐标系配置失败：检查代码唯一性”")
    private String commonProblems;

    @Schema(description = "适用角色，如“管理员”，角色表（sys_role）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("适用角色，如“管理员”，角色表（sys_role）")
    private String applicableRole;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）", example = "29800")
    @ExcelProperty("指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）")
    private String guideAttachId;

    @Schema(description = "指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    @ExcelProperty("指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String guideAttachPath;

    @Schema(description = "指南状态：已发布/草稿，标识指南是否公开", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("指南状态：已发布/草稿，标识指南是否公开")
    private String guideStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("创建时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    @ExcelProperty("更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“功能模块：地理编码/网格管理”")
    @ExcelProperty("分类扩展字段1，预留，如“功能模块：地理编码/网格管理”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“更新频率：季度/年度”")
    @ExcelProperty("分类扩展字段2，预留，如“更新频率：季度/年度”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联政策：GB/T 2260”")
    @ExcelProperty("通用扩展字段1，预留，如“关联政策：GB/T 2260”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数”")
    @ExcelProperty("通用扩展字段2，预留，如“查看次数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}