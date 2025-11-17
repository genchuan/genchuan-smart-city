package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.systemfunctionguide.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 系统功能指南分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class SystemFunctionGuidePageReqVO extends PageParam {

    @Schema(description = "指南ID，唯一编码，UUID生成", example = "14500")
    private String systemFunctionGuideId;

    @Schema(description = "指南标题，如《地理编码坐标系配置指南》")
    private String guideTitle;

    @Schema(description = "关联功能ID，关联系统功能ID，系统功能表（sys_system_function）", example = "21517")
    private String relatedFunctionId;

    @Schema(description = "关联功能名称，与关联功能ID同步，系统功能表（sys_system_function）", example = "李四")
    private String relatedFunctionName;

    @Schema(description = "功能用途说明，如“配置坐标系，确保地理数据统一”")
    private String functionPurpose;

    @Schema(description = "操作步骤（支持富文本，含截图）")
    private String operSteps;

    @Schema(description = "常见问题及解决方案，如“坐标系配置失败：检查代码唯一性”")
    private String commonProblems;

    @Schema(description = "适用角色，如“管理员”，角色表（sys_role）")
    private String applicableRole;

    @Schema(description = "指南附件ID，关联附件ID（如操作视频），文件存储表（sys_file_storage）", example = "29800")
    private String guideAttachId;

    @Schema(description = "指南附件路径，附件存储路径，文件存储表（sys_file_storage）")
    private String guideAttachPath;

    @Schema(description = "指南状态：已发布/草稿，标识指南是否公开", example = "2")
    private String guideStatus;

    @Schema(description = "创建人，创建人ID，用户信息表（sys_user）")
    private String createUser;

    @Schema(description = "创建时间，格式：yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

    @Schema(description = "更新人，更新人ID，用户信息表（sys_user）")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“功能模块：地理编码/网格管理”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“更新频率：季度/年度”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“关联政策：GB/T 2260”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“查看次数”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}