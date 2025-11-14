package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 调度效果反馈新增/修改 Request VO")
@Data
public class DispatchEffectFeedbackSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "23561")
    private Long id;

    @Schema(description = "反馈ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "4638")
    @NotEmpty(message = "反馈ID，唯一编码，UUID生成不能为空")
    private String feedbackId;

    @Schema(description = "关联指令ID，关联调度指令表", requiredMode = Schema.RequiredMode.REQUIRED, example = "10353")
    @NotEmpty(message = "关联指令ID，关联调度指令表不能为空")
    private String commandId;

    @Schema(description = "指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "指令编号，与指令ID同步，关联调度指令表（biz_dispatch_command）不能为空")
    private String commandNo;

    @Schema(description = "执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20638")
    @NotEmpty(message = "执行单位ID，与指令ID同步，联动单位信息表（biz_coop_unit_info）不能为空")
    private String execUnitId;

    @Schema(description = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "执行单位名称，与执行单位ID同步，联动单位信息表（biz_coop_unit_info）不能为空")
    private String execUnitName;

    @Schema(description = "执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "执行效果描述，如“已按指令完成2辆救援车调度，转移被困人员20名，设备正常”不能为空")
    private String effectDesc;

    @Schema(description = "反馈附件路径，执行现场照片/视频路径，多个用逗号分隔")
    private String feedbackAttachPath;

    @Schema(description = "反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "9610")
    @NotEmpty(message = "反馈人ID，执行单位反馈人账号，关联用户信息表（sys_user）不能为空")
    private String feedbackUserId;

    @Schema(description = "反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "反馈人姓名，与反馈人ID同步，关联用户信息表（sys_user）不能为空")
    private String feedbackUserName;

    @Schema(description = "反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "反馈时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成不能为空")
    private LocalDateTime feedbackTime;

    @Schema(description = "评估结果，有效/部分有效/无效，管理员评估结果")
    private String evalResult;

    @Schema(description = "评估说明，评估理由，如“救援及时，转移人员到位，效果有效”")
    private String evalDesc;

    @Schema(description = "评估人ID，管理员账号，评估后必填，关联用户信息表（sys_user）", example = "28022")
    private String evalUserId;

    @Schema(description = "评估时间，格式yyyy-MM-dd HH:mm:ss，评估后必填")
    private LocalDateTime evalTime;

    @Schema(description = "分类扩展字段1，预留，如“效果评分（1-5分）”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“效果评分（1-5分）”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储评估报告路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储评估报告路径")
    private String extCommon2;

}