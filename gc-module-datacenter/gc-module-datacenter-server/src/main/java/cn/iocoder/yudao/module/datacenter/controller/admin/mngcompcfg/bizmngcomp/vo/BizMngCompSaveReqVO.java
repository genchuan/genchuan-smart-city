package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcomp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDate;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件信息管理新增/修改 Request VO")
@Data
public class BizMngCompSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "31206")
    private Long id;

    @Schema(description = "部件ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "15847")
    @NotEmpty(message = "部件ID，唯一编码，UUID生成不能为空")
    private String mngCompId;

    @Schema(description = "部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "部件标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一不能为空")
    private String compCode;

    @Schema(description = "部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "芋艿")
    @NotEmpty(message = "部件名称，关联小类名称+位置，如“XX路电力井盖”，关联管理部件小类表(gc_biz_mng_comp_minor)不能为空")
    private String compName;

    @Schema(description = "关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "15559")
    @NotEmpty(message = "关联管理部件小类ID，关联管理部件小类表(gc_biz_mng_comp_minor)不能为空")
    private String minorId;

    @Schema(description = "关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "关联管理部件小类名称，与小类ID同步，不可改，关联管理部件小类表(gc_biz_mng_comp_minor)不能为空")
    private String minorName;

    @Schema(description = "主管部门代码，主管部门信用代码，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主管部门代码，主管部门信用代码，关联部门信息表(sys_org)不能为空")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)不能为空")
    private String deptName;

    @Schema(description = "关联单元网格ID，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "6907")
    @NotEmpty(message = "关联单元网格ID，关联网格信息表(biz_grid_info)不能为空")
    private String gridId;

    @Schema(description = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)不能为空")
    private String gridName;

    @Schema(description = "部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "部件状态，如完好/破损/丢失/废弃，关联部件状态字典表(sys_dict_mng_comp_state)不能为空")
    private String compStatus;

    @Schema(description = "部件普查日期，格式YYYYMMDD", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "部件普查日期，格式YYYYMMDD不能为空")
    private LocalDate initDate;

    @Schema(description = "状态/权属变更时更新日期，格式YYYYMMDD")
    private LocalDate changeDate;

    @Schema(description = "数据来源，如实测/人工上报/普查，关联数据来源字典表(sys_dict_data_source)")
    private String dataSource;

    @Schema(description = "录入人账号，关联用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "录入人账号，关联用户信息表(sys_user)不能为空")
    private String createUser;

    @Schema(description = "修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "分类扩展字段1，预留，如“部件材质”")
    private String extCat1;

    @Schema(description = "分类扩展字段2，预留，如“部件材质”")
    private String extCat2;

    @Schema(description = "通用扩展字段1，预留，如“安装时间”")
    private String extCommon1;

    @Schema(description = "通用扩展字段2，预留，如“安装时间”")
    private String extCommon2;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}