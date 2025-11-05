package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 管理部件扩展管理部件配置新增/修改 Request VO")
@Data
public class BizMngCompExtSaveReqVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "17311")
    private Long id;

    @Schema(description = "扩展ID，唯一编码，UUID生成", requiredMode = Schema.RequiredMode.REQUIRED, example = "30700")
    @NotEmpty(message = "扩展ID，唯一编码，UUID生成不能为空")
    private String mngCompExtId;

    @Schema(description = "归属大类ID，无则归“其他”，关联管理部件大类ID，关联管理部件大类表(gc_biz_mng_comp_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "27108")
    @NotEmpty(message = "归属大类ID，无则归“其他”，关联管理部件大类ID，关联管理部件大类表(gc_biz_mng_comp_major)不能为空")
    private String majorId;

    @Schema(description = "归属大类名称，与大类ID同步，关联管理部件大类表(gc_biz_mng_comp_major)", requiredMode = Schema.RequiredMode.REQUIRED, example = "王五")
    @NotEmpty(message = "归属大类名称，与大类ID同步，关联管理部件大类表(gc_biz_mng_comp_major)不能为空")
    private String majorName;

    @Schema(description = "扩展小类代码，080 - 999，倒排编码，避免冲突", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "扩展小类代码，080 - 999，倒排编码，避免冲突不能为空")
    private String extMinorCode;

    @Schema(description = "扩展小类名称，名称加“（自定义）”", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "扩展小类名称，名称加“（自定义）”不能为空")
    private String extMinorName;

    @Schema(description = "扩展小类说明，描述用途，如“智能充电桩: 电动汽车充电设备”", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "扩展小类说明，描述用途，如“智能充电桩: 电动汽车充电设备”不能为空")
    private String extMinorDesc;

    @Schema(description = "建议主管部门代码，建议部门代码，关联部门信息表(sys_org)")
    private String suggestDeptCode;

    @Schema(description = "建议主管部门名称，与部门代码同步，关联部门信息表(sys_org)", example = "王五")
    private String suggestDeptName;

    @Schema(description = "系统创建时间")
    private LocalDateTime createTimeSys;

    @Schema(description = "系统更新时间")
    private LocalDateTime updateTimeSys;

}