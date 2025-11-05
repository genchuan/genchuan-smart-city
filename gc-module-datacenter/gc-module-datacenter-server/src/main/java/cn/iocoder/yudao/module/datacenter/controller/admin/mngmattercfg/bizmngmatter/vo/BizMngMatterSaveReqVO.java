package cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理事项信息新增/修改 Request VO")
@Data
public class BizMngMatterSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "2169")
    private Long id;

    @Schema(description = "事项ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "22275")
    @NotEmpty(message = "事项ID，唯一编码，UUID生成不能为空")
    private String mngMatterId;

    @Schema(description = "事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事项标识码，格式6位行政码+2位大类码+3位小类码+5位顺序码，唯一不能为空")
    private String matterCode;

    @Schema(description = "事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "事项名称，关联小类名称+位置，如“XX路私搭乱建”，关联管理事项小类表(gc_biz_mng_matter_minor)不能为空")
    private String matterName;

    @Schema(description = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "1064")
    @NotEmpty(message = "关联管理事项小类ID，关联管理事项小类表(gc_biz_mng_matter_minor)不能为空")
    private String minorId;

    @Schema(description = "关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "关联管理事项小类名称，与小类ID同步，不可修改，关联管理事项小类表(gc_biz_mng_matter_minor)不能为空")
    private String minorName;

    @Schema(description = "关联单元网格ID，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "30383")
    @NotEmpty(message = "关联单元网格ID，关联网格信息表(biz_grid_info)不能为空")
    private String gridId;

    @Schema(description = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "关联单元网格名称，与网格ID同步，关联网格信息表(biz_grid_info)不能为空")
    private String gridName;

    @Schema(description = "事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)", requiredMode = Schema.RequiredMode.REQUIRED, example = "2")
    @NotEmpty(message = "事项状态，如待处置/处置中/已办结/已驳回，关联问题状态字典表(sys_dict_problem_status)不能为空")
    private String matterStatus;

    @Schema(description = "事项等级，如一级/二级/三级，按影响范围判定")
    private String matterLevel;

    @Schema(description = "主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "主管部门代码，关联主管部门信用代码，关联部门信息表(sys_org)不能为空")
    private String deptCode;

    @Schema(description = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)", requiredMode = Schema.RequiredMode.REQUIRED, example = "李四")
    @NotEmpty(message = "主管部门名称，与部门代码同步，关联部门信息表(sys_org)不能为空")
    private String deptName;

    @Schema(description = "事发位置，详细位置描述，如“XX路与XX路交叉口东北侧”")
    private String incidentLocation;

    @Schema(description = "录入人账号，关联用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "录入人账号，关联用户信息表(sys_user)不能为空")
    private String createUser;

    @Schema(description = "修改人账号，关联用户信息表(sys_user)")
    private String updateUser;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}