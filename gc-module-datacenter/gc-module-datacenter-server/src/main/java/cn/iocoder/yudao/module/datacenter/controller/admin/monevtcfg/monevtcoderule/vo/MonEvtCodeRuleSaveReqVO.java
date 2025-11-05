package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 监测事件标识码规则新增/修改 Request VO")
@Data
public class MonEvtCodeRuleSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "10310")
    private Long id;

    @Schema(description = "规则ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "17712")
    @NotEmpty(message = "规则ID，唯一编码，UUID生成不能为空")
    private String monEvtRuleId;

    @Schema(description = "规则名称，如“监测事件18位标识码规则”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "规则名称，如“监测事件18位标识码规则”不能为空")
    private String ruleName;

    @Schema(description = "行政代码位数，固定为6位，符合GB/T 2260", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "行政代码位数，固定为6位，符合GB/T 2260不能为空")
    private Integer adminCodeLen;

    @Schema(description = "大类代码位数，固定为2位，关联事件分类表大类代码；监测事件分类配置表（sys_mon_evt_cat）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "大类代码位数，固定为2位，关联事件分类表大类代码；监测事件分类配置表（sys_mon_evt_cat）不能为空")
    private Integer majorCodeLen;

    @Schema(description = "中类代码位数，固定为2位，关联事件分类表中类代码；监测事件分类配置表（sys_mon_evt_cat）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "中类代码位数，固定为2位，关联事件分类表中类代码；监测事件分类配置表（sys_mon_evt_cat）不能为空")
    private Integer midCodeLen;

    @Schema(description = "小类代码位数，固定为2位，关联事件分类表小类代码；监测事件分类配置表（sys_mon_evt_cat）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "小类代码位数，固定为2位，关联事件分类表小类代码；监测事件分类配置表（sys_mon_evt_cat）不能为空")
    private Integer minorCodeLen;

    @Schema(description = "顺序码位数，固定为6位，从000001顺序编排", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "顺序码位数，固定为6位，从000001顺序编排不能为空")
    private Integer seqCodeLen;

    @Schema(description = "顺序码生成规则，同一行政+小类下按上报时间递增，默认“同一县级行政区域+小类下按上报时间递增”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "顺序码生成规则，同一行政+小类下按上报时间递增，默认“同一县级行政区域+小类下按上报时间递增”不能为空")
    private String seqGenRule;

    @Schema(description = "启用状态，同一时间仅一个规则启用，1（启用）/0（禁用）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "启用状态，同一时间仅一个规则启用，1（启用）/0（禁用）不能为空")
    private String enableStatus;

    @Schema(description = "创建人，填写创建人账号，用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，填写创建人账号，用户信息表(sys_user)不能为空")
    private String createUser;

    @Schema(description = "更新人，填写更新人账号，用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}