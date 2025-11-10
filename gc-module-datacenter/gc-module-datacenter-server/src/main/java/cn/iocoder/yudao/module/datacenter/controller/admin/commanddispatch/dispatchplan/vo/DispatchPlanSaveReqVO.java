package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchplan.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 调度方案新增/修改 Request VO")
@Data
public class DispatchPlanSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "27084")
    private Long id;

    @Schema(description = "方案ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "17786")
    @NotEmpty(message = "方案ID，唯一编码，UUID生成不能为空")
    private String planId;

    @Schema(description = "方案编号，系统自动生成，格式“DISP+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "方案编号，系统自动生成，格式“DISP+年份（4位）+6位流水号”不能为空")
    private String planNo;

    @Schema(description = "关联响应ID，关联预案启动响应表", requiredMode = Schema.RequiredMode.REQUIRED, example = "24068")
    @NotEmpty(message = "关联响应ID，关联预案启动响应表不能为空")
    private String respId;

    @Schema(description = "响应编号，与响应ID同步，关联预案启动响应表（biz_plan_launch_resp）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "响应编号，与响应ID同步，关联预案启动响应表（biz_plan_launch_resp）不能为空")
    private String respNo;

    @Schema(description = "方案名称，如“XX内涝救援资源调度方案”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "方案名称，如“XX内涝救援资源调度方案”不能为空")
    private String planName;

    @Schema(description = "调度需求描述，如“需调配5辆救援车、200件救生衣至XX内涝点”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "调度需求描述，如“需调配5辆救援车、200件救生衣至XX内涝点”不能为空")
    private String demandDesc;

    @Schema(description = "资源类型，应急物资/救援队伍/救援车辆/救援设备，标识调度资源类型", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "资源类型，应急物资/救援队伍/救援车辆/救援设备，标识调度资源类型不能为空")
    private String resType;

    @Schema(description = "资源ID，关联对应资源表（物资/队伍等），应急物资信息表（biz_emer_mat_info）/救援队伍信息表（biz_emer_team_info）等", requiredMode = Schema.RequiredMode.REQUIRED, example = "22401")
    @NotEmpty(message = "资源ID，关联对应资源表（物资/队伍等），应急物资信息表（biz_emer_mat_info）/救援队伍信息表（biz_emer_team_info）等不能为空")
    private String resId;

    @Schema(description = "资源名称，与资源ID同步，对应资源表", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "资源名称，与资源ID同步，对应资源表不能为空")
    private String resName;

    @Schema(description = "调度数量，调度资源数量（物资/设备按单位，队伍按人数，车辆按辆）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "调度数量，调度资源数量（物资/设备按单位，队伍按人数，车辆按辆）不能为空")
    private Integer dispatchQuantity;

    @Schema(description = "调配目的地，如“XX路与XX路交叉口内涝点”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "调配目的地，如“XX路与XX路交叉口内涝点”不能为空")
    private String dispatchDest;

    @Schema(description = "目的地坐标X，度，2000国家大地坐标系经度")
    private BigDecimal destCoordX;

    @Schema(description = "目的地坐标Y，度，2000国家大地坐标系纬度")
    private BigDecimal destCoordY;

    @Schema(description = "负责人ID，调度负责人账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "10895")
    @NotEmpty(message = "负责人ID，调度负责人账号，关联用户信息表（sys_user）不能为空")
    private String chargerId;

    @Schema(description = "负责人姓名，与负责人ID同步，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "负责人姓名，与负责人ID同步，关联用户信息表（sys_user）不能为空")
    private String chargerName;

    @Schema(description = "要求完成时间，格式yyyy-MM-dd HH:mm:ss", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "要求完成时间，格式yyyy-MM-dd HH:mm:ss不能为空")
    private LocalDateTime requireCompleteTime;

    @Schema(description = "方案状态，待执行/执行中/已完成/已取消，默认待执行", example = "1")
    private String planStatus;

    @Schema(description = "创建人，方案制定人账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "创建人，方案制定人账号，关联用户信息表（sys_user）不能为空")
    private String createUser;

    @Schema(description = "分类扩展字段1，预留，如“调度优先级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“调度优先级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储调度路线规划路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储调度路线规划路径")
    private String extCommon2;

}