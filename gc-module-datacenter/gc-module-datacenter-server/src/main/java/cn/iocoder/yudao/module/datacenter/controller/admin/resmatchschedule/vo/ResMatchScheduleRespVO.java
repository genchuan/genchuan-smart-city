package cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;
import com.alibaba.excel.annotation.*;

@Schema(description = "管理后台 - 资源匹配调度 Response VO")
@Data
@ExcelIgnoreUnannotated
public class ResMatchScheduleRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "22316")
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "调度ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22687")
    @ExcelProperty("调度ID，唯一编码，UUID生成")
    private String resScheduleId;

    @Schema(description = "关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "13199")
    @ExcelProperty("关联方案ID，关联决策方案表的方案ID，决策方案表（biz_decision_scheme）")
    private String relSchemeId;

    @Schema(description = "关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @ExcelProperty("关联方案名称，与方案ID同步，决策方案表（biz_decision_scheme）")
    private String relSchemeName;

    /**
     * 资源需求明细，JSON格式存储资源需求（如：[{"res_type_id":"id1","res_type_name":"传感器","demand_count":3}]）
     * 资源类型表（sys_res_type）
     */
    @Schema(
            description = "资源需求明细，JSON格式存储资源需求（如：[{\"res_type_id\":\"id1\",\"res_type_name\":\"传感器\",\"demand_count\":3}]），资源类型表（sys_res_type）",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @ExcelProperty("资源需求明细")
    private String resDemandDetail;

    /**
     * 匹配资源明细，JSON格式存储匹配结果（如：[{"res_id":"id1","res_name":"传感器-001","allocate_count":1}]）
     * 资源信息表（biz_res_info）
     */
    @Schema(
            description = "匹配资源明细，JSON格式存储匹配结果（如：[{\"res_id\":\"id1\",\"res_name\":\"传感器-001\",\"allocate_count\":1}]），资源信息表（biz_res_info）",
            requiredMode = Schema.RequiredMode.REQUIRED
    )
    @ExcelProperty("匹配资源明细")
    private String matchedResDetail;

    @Schema(description = "调度状态：待匹配/匹配中/匹配成功/匹配失败/调度完成，标识调度进度", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @ExcelProperty("调度状态：待匹配/匹配中/匹配成功/匹配失败/调度完成，标识调度进度")
    private String scheduleStatus;

    @Schema(description = "匹配失败原因，调度状态为“匹配失败”时必填（如“传感器库存仅2台，需求3台”）", example = "不喜欢")
    @ExcelProperty("匹配失败原因，调度状态为“匹配失败”时必填（如“传感器库存仅2台，需求3台”）")
    private String matchFailReason;

    @Schema(description = "调度工单ID，关联资源调度工单表的工单ID，资源调度工单表（biz_res_schedule_wo）", example = "6277")
    @ExcelProperty("调度工单ID，关联资源调度工单表的工单ID，资源调度工单表（biz_res_schedule_wo）")
    private String scheduleWoId;

    @Schema(description = "调度人，发起调度的用户ID，用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调度人，发起调度的用户ID，用户信息表（sys_user）")
    private String scheduleUser;

    @Schema(description = "调度时间，格式：yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("调度时间，格式：yyyy-MM-dd HH:mm:ss")
    private LocalDateTime scheduleTime;

    @Schema(description = "完成时间，格式：yyyy-MM-dd HH:mm:ss，调度状态为“调度完成”时必填")
    @ExcelProperty("完成时间，格式：yyyy-MM-dd HH:mm:ss，调度状态为“调度完成”时必填")
    private LocalDateTime completeTime;

    @Schema(description = "资源负责人，资源管理负责人ID，用户信息表（sys_user）")
    @ExcelProperty("资源负责人，资源管理负责人ID，用户信息表（sys_user）")
    private String resMngr;

    @Schema(description = "资源负责人姓名，与负责人ID同步，用户信息表（sys_user）", example = "张三")
    @ExcelProperty("资源负责人姓名，与负责人ID同步，用户信息表（sys_user）")
    private String resMngrName;

    @Schema(description = "分类扩展字段1，预留，如“调度批次”")
    @ExcelProperty("分类扩展字段1，预留，如“调度批次”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“调度批次”")
    @ExcelProperty("分类扩展字段2，预留，如“调度批次”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储资源使用期限")
    @ExcelProperty("通用扩展字段1，预留，存储资源使用期限")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储资源使用期限")
    @ExcelProperty("通用扩展字段2，预留，存储资源使用期限")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    @ExcelProperty("系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    @ExcelProperty("系统更新时间")
    private LocalDateTime updateTimeSys;

}