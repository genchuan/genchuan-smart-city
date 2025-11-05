package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件办结归档分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtArchPageReqVO extends PageParam {

    @Schema(description = "归档ID，唯一编码，UUID", example = "26984")
    private String archId;

    @Schema(description = "工单ID，关联事件工单表", example = "24777")
    private String woId;

    @Schema(description = "事件编码，关联事件分级分拨表")
    private String evtCode;

    @Schema(description = "办结时间，格式yyyy - MM - dd HH:mm:ss")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] completeTime;

    @Schema(description = "办结说明")
    private String completeDesc;

}