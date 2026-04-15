package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import lombok.*;
import java.util.*;
import io.swagger.v3.oas.annotations.media.Schema;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;

import static cn.iocoder.yudao.framework.common.util.date.DateUtils.FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND;

@Schema(description = "汽车充电 - 订单告警分页 Request VO")
@Data
public class OrderAlarmPageReqVO extends PageParam {

    @Schema(description = "告警编号")
    private String alarmCode;

    @Schema(description = "订单编号")
    private String orderCode;

    @Schema(description = "用户ID")
    private String userId;

    @Schema(description = "车牌号")
    private String plateNo;

    @Schema(description = "异常类型")
    private String abnormalType;

    @Schema(description = "告警时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] alarmTime;

    @Schema(description = "关联充电桩编号")
    private String pileCode;

    @Schema(description = "告警状态")
    private String alarmStatus;

    @Schema(description = "核实结果")
    private String verifyResult;

    @Schema(description = "处理措施")
    private String handleMeasure;

    @Schema(description = "处理时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] handleTime;

    @Schema(description = "备注")
    private String remark;

    @Schema(description = "备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    private String reserve2;

    @Schema(description = "创建人")
    private String creator;

    @Schema(description = "创建时间")
    @DateTimeFormat(pattern = FORMAT_YEAR_MONTH_DAY_HOUR_MINUTE_SECOND)
    private LocalDateTime[] createTime;

}