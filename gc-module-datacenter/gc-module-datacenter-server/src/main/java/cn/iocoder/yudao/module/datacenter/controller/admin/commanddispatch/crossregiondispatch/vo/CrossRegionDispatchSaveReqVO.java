package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 跨域调度协调新增/修改 Request VO")
@Data
public class CrossRegionDispatchSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "16073")
    private Long id;

    @Schema(description = "协调ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "26121")
    @NotEmpty(message = "协调ID，唯一编码，UUID生成不能为空")
    private String coordinateId;

    @Schema(description = "协调编号，系统自动生成，格式“CROSS_DISP+年份（4位）+6位流水号”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "协调编号，系统自动生成，格式“CROSS_DISP+年份（4位）+6位流水号”不能为空")
    private String coordinateNo;

    @Schema(description = "申请单位ID，发起申请的本地联动单位ID，关联联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "6806")
    @NotEmpty(message = "申请单位ID，发起申请的本地联动单位ID，关联联动单位信息表（biz_coop_unit_info）不能为空")
    private String applyUnitId;

    @Schema(description = "申请单位名称，与申请单位ID同步，关联联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "申请单位名称，与申请单位ID同步，关联联动单位信息表（biz_coop_unit_info）不能为空")
    private String applyUnitName;

    @Schema(description = "协作单位ID，跨域协作单位ID，关联联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "2640")
    @NotEmpty(message = "协作单位ID，跨域协作单位ID，关联联动单位信息表（biz_coop_unit_info）不能为空")
    private String cooperUnitId;

    @Schema(description = "协作单位名称，与协作单位ID同步，关联联动单位信息表（biz_coop_unit_info）", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "协作单位名称，与协作单位ID同步，关联联动单位信息表（biz_coop_unit_info）不能为空")
    private String cooperUnitName;

    @Schema(description = "申请理由，如“XX市遭遇特大洪涝，本地冲锋舟仅3辆，需邻市支援5辆”", requiredMode = Schema.RequiredMode.REQUIRED, example = "不香")
    @NotEmpty(message = "申请理由，如“XX市遭遇特大洪涝，本地冲锋舟仅3辆，需邻市支援5辆”不能为空")
    private String applyReason;

    @Schema(description = "所需资源类型，应急物资/救援队伍/救援车辆/救援设备", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
    @NotEmpty(message = "所需资源类型，应急物资/救援队伍/救援车辆/救援设备不能为空")
    private String resType;

    @Schema(description = "所需资源名称，如“冲锋舟”“专业抗洪队伍”", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "所需资源名称，如“冲锋舟”“专业抗洪队伍”不能为空")
    private String resName;

    @Schema(description = "所需数量，资源需求数量", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "所需数量，资源需求数量不能为空")
    private Integer requireQuantity;

    @Schema(description = "协作区域，如“XX市XX区”，关联行政区划表（sys_area）", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "协作区域，如“XX市XX区”，关联行政区划表（sys_area）不能为空")
    private String cooperRegion;

    @Schema(description = "申请状态，待审核/已通过/已驳回，默认待审核", example = "2")
    private String applyStatus;

    @Schema(description = "申请人ID，申请管理员账号，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "20060")
    @NotEmpty(message = "申请人ID，申请管理员账号，关联用户信息表（sys_user）不能为空")
    private String applyUserId;

    @Schema(description = "申请人姓名，与申请人ID同步，关联用户信息表（sys_user）", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "申请人姓名，与申请人ID同步，关联用户信息表（sys_user）不能为空")
    private String applyUserName;

    @Schema(description = "申请时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "申请时间，格式yyyy-MM-dd HH:mm:ss，系统自动生成不能为空")
    private LocalDateTime applyTime;

    @Schema(description = "审核人ID，协作单位审核人账号，审核后必填，关联用户信息表（sys_user）", example = "29761")
    private String auditUserId;

    @Schema(description = "审核人姓名，与审核人ID同步，关联用户信息表（sys_user）", example = "张三")
    private String auditUserName;

    @Schema(description = "审核时间，格式yyyy-MM-dd HH:mm:ss，审核后必填")
    private LocalDateTime auditTime;

    @Schema(description = "审核意见，审核反馈，如“同意支援5辆冲锋舟，24小时内到位”")
    private String auditOpinion;

    @Schema(description = "分类扩展字段1，预留，如“协调优先级”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“协调优先级”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，存储协作协议路径")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，存储协作协议路径")
    private String extCommon2;

}