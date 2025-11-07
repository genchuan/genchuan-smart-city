package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtanalysis.vo;

import lombok.*;

import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 事件研判分析分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class EvtAnalysisPageReqVO extends PageParam {

    @Schema(description = "研判ID，唯一编码，UUID", example = "2676")
    private String analysisId;

    @Schema(description = "事件编码，关联事件接报登记表")
    private String evtCode;

    @Schema(description = "事件名称，拼接“类型名称+事件名称”，如“交通拥堵 - XX路”", example = "赵六")
    private String evtName;

    @Schema(description = "研判人ID，研判分析师账号，关联用户信息表(sys_user)", example = "27868")
    private String analystId;

    @Schema(description = "研判人姓名，与研判人ID同步", example = "赵六")
    private String analystName;

    @Schema(description = "研判时间，格式yyyy - MM - dd HH:mm:ss，系统自动生成")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] analysisTime;

    @Schema(description = "事件原因，研判的事件原因，如“交通事故导致XX路拥堵”", example = "不好")
    private String evtReason;

    @Schema(description = "影响范围，描述影响范围，如“XX路1公里内，涉及2个小区”")
    private String impactRange;

    @Schema(description = "紧急程度，一般/紧急/特急，研判确定的紧急等级")
    private String urgencyLevel;

    @Schema(description = "研判结论，处置建议，如“优先调度交警处置事故，疏导车流”")
    private String conclusion;

    @Schema(description = "关联监测数据ID，关联监测部件数据表，如摄像头数据", example = "19045")
    private String relMonDataId;

    @Schema(description = "监测数据摘要，监测数据简要描述，如“XX摄像头显示20辆车辆拥堵”")
    private String monDataAbstract;

    @Schema(description = "是否需要分拨，1（是）/0（否），标识是否需后续分拨")
    private String needAllocate;

}