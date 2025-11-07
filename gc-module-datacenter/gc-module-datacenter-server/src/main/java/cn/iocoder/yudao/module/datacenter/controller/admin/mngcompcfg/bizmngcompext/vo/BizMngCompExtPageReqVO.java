package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompext.vo;

import lombok.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import java.time.LocalDateTime;


@Schema(description = "管理后台 - 管理部件扩展管理部件配置分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BizMngCompExtPageReqVO extends PageParam {

    @Schema(description = "扩展ID，唯一编码，UUID生成", example = "30700")
    private String mngCompExtId;

    @Schema(description = "归属大类ID，无则归“其他”，关联管理部件大类ID，关联管理部件大类表(gc_biz_mng_comp_major)", example = "27108")
    private String majorId;

    @Schema(description = "归属大类名称，与大类ID同步，关联管理部件大类表(gc_biz_mng_comp_major)", example = "王五")
    private String majorName;

    @Schema(description = "扩展小类代码，080 - 999，倒排编码，避免冲突")
    private String extMinorCode;

    @Schema(description = "扩展小类名称，名称加“（自定义）”", example = "赵六")
    private String extMinorName;

    @Schema(description = "扩展小类说明，描述用途，如“智能充电桩: 电动汽车充电设备”")
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