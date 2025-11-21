package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "管理后台 - 风险传播链分页 Request VO")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RiskPropagateChainPageReqVO extends PageParam {

    @Schema(description = "传播链ID")
    private String propagateChainId;

    @Schema(description = "传播链编号")
    private String propagateChainNo;

    @Schema(description = "源头预警ID")
    private String sourceWarnId;

    @Schema(description = "源头预警名称")
    private String sourceWarnName;

    @Schema(description = "传播节点序号")
    private Integer nodeSeq;

    @Schema(description = "传播节点内容")
    private String nodeContent;

    @Schema(description = "节点类型")
    private String nodeType;

    @Schema(description = "发生时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] occurTime;

    @Schema(description = "影响范围")
    private String impactRange;

    @Schema(description = "关联设备ID")
    private String deviceId;

    @Schema(description = "关联设备名称")
    private String deviceName;

    @Schema(description = "记录人ID")
    private String recUserId;

    @Schema(description = "记录人姓名")
    private String recUserName;

    @Schema(description = "分类扩展字段1")
    private String extCat1;

    @Schema(description = "分类扩展字段2")
    private String extCat2;

    @Schema(description = "通用扩展字段1")
    private String extCommon1;

    @Schema(description = "通用扩展字段2")
    private String extCommon2;

    @Schema(description = "create_time")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}