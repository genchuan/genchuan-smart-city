package cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 资源匹配调度新增/修改 Request VO")
@Data
public class ResMatchScheduleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22316")
    private Long id;

    @Schema(description = "调度ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22687")
    @NotEmpty(message = "调度ID，唯一编码，UUID生成不能为空")
    private String resScheduleId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13199")
    @NotEmpty(message = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）不能为空")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）不能为空")
    private String relSchemeName;

    @Schema(description = "资源需求明细，JSON格式存储资源需求")
    @NotEmpty(message = "资源需求明细，JSON格式存储资源需求")
    private String resDemandDetail;

    @Schema(description = "匹配资源明细，JSON格式存储匹配结果")
    private String matchedResDetail;

    @Schema(description = "调度状态：待匹配/匹配中/匹配成功/匹配失败/调度完成，标识调度进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "调度状态：待匹配/匹配中/匹配成功/匹配失败/调度完成，标识调度进度不能为空")
    private String scheduleStatus;

    @Schema(description = "匹配失败原因，调度状态为“匹配失败”时必填（如“传感器库存仅2台，需求3台”）", example = "不喜欢")
    private String matchFailReason;

    @Schema(description = "调度工单ID，关联资源调度工单表的工单ID，资源调度工单表（biz_res_schedule_wo）", example = "6277")
    private String scheduleWoId;

    @Schema(description = "调度人，发起调度的用户ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "调度人，发起调度的用户ID，用户信息表（sys_user）不能为空")
    private String scheduleUser;

    @Schema(description = "调度时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调度时间，格式：yyyy-MM-dd HH:mm:ss不能为空")
    private LocalDateTime scheduleTime;

    @Schema(description = "完成时间，格式：yyyy-MM-dd HH:mm:ss，调度状态为“调度完成”时必填")
    private LocalDateTime completeTime;

    @Schema(description = "资源负责人，资源管理负责人ID，用户信息表（sys_user）")
    private String resMngr;

    @Schema(description = "资源负责人姓名，与负责人ID同步，用户信息表（sys_user）", example = "张三")
    private String resMngrName;

    @Schema(description = "分类扩展字段1，预留，如“调度批次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“调度批次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储资源使用期限")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储资源使用期限")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}