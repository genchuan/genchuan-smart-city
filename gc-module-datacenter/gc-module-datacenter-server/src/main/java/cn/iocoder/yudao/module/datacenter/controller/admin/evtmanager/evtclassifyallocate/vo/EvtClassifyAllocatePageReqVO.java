package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件分级分拨分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtClassifyAllocatePageReqVO extends PageParam {

    @Schema(description = "分拨ID，唯一编码，UUID", example = "11233")
    private String allocateId;

    @Schema(description = "事件编码，关联事件研判分析表")
    private String evtCode;

    @Schema(description = "事件类型ID，关联事件类型配置表", example = "1082")
    private String evtTypeId;

    @Schema(description = "事件类型名称，与类型ID同步", example = "赵六")
    private String evtTypeName;

    @Schema(description = "紧急程度，一般/紧急/特急，从研判表同步")
    private String urgencyLevel;

    @Schema(description = "分拨部门代码，关联部门信息表，分拨目标部门")
    private String allocateDeptCode;

    @Schema(description = "分拨部门名称，与部门代码同步", example = "张三")
    private String allocateDeptName;

    @Schema(description = "分拨人ID，分拨调度员账号，关联用户信息表(sys_user)", example = "17847")
    private String allocatorId;

    @Schema(description = "分拨人姓名，与分拨人ID同步", example = "赵六")
    private String allocatorName;

    @Schema(description = "分拨时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] allocateTime;

    @Schema(description = "分拨说明，分拨备注，如“优先处置，需1小时内反馈”")
    private String allocateDesc;

}