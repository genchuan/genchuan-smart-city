package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 预案启动响应新增/修改 Request VO")
@Data
public class PlanLaunchRespSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "9328")
    private Long id;

    @Schema(description = "响应ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "18257")
    @NotEmpty(message = "响应ID，唯一编码，UUID不能为空")
    private String respId;

    @Schema(description = "响应编号，系统自动生成，格式“RESP+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "响应编号，系统自动生成，格式“RESP+年份（4位）+6位流水号”不能为空")
    private String respNo;

    @Schema(description = "关联预案ID，关联预案库表", requiredMode = Schema.RequiredMode.REQUIRED, example = "26139")
    @NotEmpty(message = "关联预案ID，关联预案库表不能为空")
    private String planId;

    @Schema(description = "预案名称，与预案ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "预案名称，与预案ID同步不能为空")
    private String planName;

    @Schema(description = "预案版本，与预案ID同步", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "预案版本，与预案ID同步不能为空")
    private String planVersion;

    @Schema(description = "关联事件编码，关联事件接报登记表，无事件时为空")
    private String evtCode;

    @Schema(description = "事件名称，与事件编码同步", example = "芋艿")
    private String evtName;

    @Schema(description = "启动原因，如“XX区域3小时降雨量超150mm，引发内涝，需启动III级响应”", requiredMode = Schema.RequiredMode.REQUIRED, example = "不香")
    @NotEmpty(message = "启动原因，如“XX区域3小时降雨量超150mm，引发内涝，需启动III级响应”不能为空")
    private String launchReason;

    @Schema(description = "响应等级，IV级/III级/II级/I级，按事件严重程度选择", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "响应等级，IV级/III级/II级/I级，按事件严重程度选择不能为空")
    private String respLevel;

    @Schema(description = "启动人ID，预案启动管理员账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6678")
    @NotEmpty(message = "启动人ID，预案启动管理员账号，关联用户信息表（sys_user）不能为空")
    private String launchUserId;

    @Schema(description = "启动人姓名，与启动人ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "启动人姓名，与启动人ID同步不能为空")
    private String launchUserName;

    @Schema(description = "启动时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "启动时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成不能为空")
    private LocalDateTime launchTime;

    @Schema(description = "响应状态，启动中/响应完成/响应终止，默认启动中", example = "1")
    private String respStatus;

    @Schema(description = "终止原因，响应终止时填写，如“内涝已缓解，无需继续响应”", example = "不对")
    private String terminateReason;

    @Schema(description = "终止时间，格式yyyy-MM-dd HH:mm:ss，响应终止时必填")
    private LocalDateTime terminateTime;

    @Schema(description = "需同步的联动单位ID，多个用逗号分隔，关联联动单位信息表（biz_coop_unit_info）")
    private String syncUnitIds;

    @Schema(description = "与单位IDs同步，关联联动单位信息表（biz_coop_unit_info）")
    private String syncUnitNames;

    @Schema(description = "预留，如“响应指挥长”")
    private String extCat1;

    @Schema(description = "预留，如“响应指挥长”")
    private String extCat2;

    @Schema(description = "预留，存储响应部署方案路径")
    private String extCommon1;

    @Schema(description = "预留，存储响应部署方案路径")
    private String extCommon2;

}