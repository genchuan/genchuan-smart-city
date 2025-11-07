package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

@Schema(description = "管理后台 - 事件分级分拨新增/修改 Request VO")
@Data
public class EvtClassifyAllocateSaveReqVO {

    @Schema(description = "自增主键ID", requiredMode = Schema.RequiredMode.REQUIRED, example = "1178")
    private Long id;

    @Schema(description = "分拨ID，唯一编码，UUID", requiredMode = Schema.RequiredMode.REQUIRED, example = "11233")
    @NotEmpty(message = "分拨ID，唯一编码，UUID不能为空")
    private String allocateId;

    @Schema(description = "事件编码，关联事件研判分析表", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "事件编码，关联事件研判分析表不能为空")
    private String evtCode;

    @Schema(description = "事件类型ID，关联事件类型配置表", requiredMode = Schema.RequiredMode.REQUIRED, example = "1082")
    @NotEmpty(message = "事件类型ID，关联事件类型配置表不能为空")
    private String evtTypeId;

    @Schema(description = "事件类型名称，与类型ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "事件类型名称，与类型ID同步不能为空")
    private String evtTypeName;

    @Schema(description = "紧急程度，一般/紧急/特急，从研判表同步", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "紧急程度，一般/紧急/特急，从研判表同步不能为空")
    private String urgencyLevel;

    @Schema(description = "分拨部门代码，关联部门信息表，分拨目标部门", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "分拨部门代码，关联部门信息表，分拨目标部门不能为空")
    private String allocateDeptCode;

    @Schema(description = "分拨部门名称，与部门代码同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "张三")
    @NotEmpty(message = "分拨部门名称，与部门代码同步不能为空")
    private String allocateDeptName;

    @Schema(description = "分拨人ID，分拨调度员账号，关联用户信息表(sys_user)", requiredMode = Schema.RequiredMode.REQUIRED, example = "17847")
    @NotEmpty(message = "分拨人ID，分拨调度员账号，关联用户信息表(sys_user)不能为空")
    private String allocatorId;

    @Schema(description = "分拨人姓名，与分拨人ID同步", requiredMode = Schema.RequiredMode.REQUIRED, example = "赵六")
    @NotEmpty(message = "分拨人姓名，与分拨人ID同步不能为空")
    private String allocatorName;

    @Schema(description = "分拨时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    private LocalDateTime allocateTime;

    @Schema(description = "分拨说明，分拨备注，如“优先处置，需1小时内反馈”")
    private String allocateDesc;

}