package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDateTime;
import cn.idev.excel.annotation.*;

@Schema(description = "汽车充电 - 订单告警 Response VO")
@Data
@ExcelIgnoreUnannotated
public class OrderAlarmRespVO {

    @Schema(description = "主键ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("主键ID")
    private Long id;

    @Schema(description = "告警编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("告警编号")
    private String alarmCode;

    @Schema(description = "订单编号", requiredMode = Schema.RequiredMode.REQUIRED)
    @ExcelProperty("订单编号")
    private String orderCode;

    @Schema(description = "用户ID")
    @ExcelProperty("用户ID")
    private String userId;

    @Schema(description = "车牌号")
    @ExcelProperty("车牌号")
    private String plateNo;

    @Schema(description = "异常类型")
    @ExcelProperty("异常类型")
    private String abnormalType;

    @Schema(description = "告警时间")
    @ExcelProperty("告警时间")
    private LocalDateTime alarmTime;

    @Schema(description = "关联充电桩编号")
    @ExcelProperty("关联充电桩编号")
    private String pileCode;

    @Schema(description = "关联充电桩名称（模拟）")
    @ExcelProperty("关联充电桩名称")
    private String pileName;

    @Schema(description = "告警状态")
    @ExcelProperty("告警状态")
    private String alarmStatus;

    @Schema(description = "核实结果")
    @ExcelProperty("核实结果")
    private String verifyResult;

    @Schema(description = "处理措施")
    @ExcelProperty("处理措施")
    private String handleMeasure;

    @Schema(description = "处理时间")
    @ExcelProperty("处理时间")
    private LocalDateTime handleTime;

    @Schema(description = "备注")
    @ExcelProperty("备注")
    private String remark;

    @Schema(description = "备用字段1")
    @ExcelProperty("备用字段1")
    private String reserve1;

    @Schema(description = "备用字段2")
    @ExcelProperty("备用字段2")
    private String reserve2;

    @Schema(description = "创建人")
    @ExcelProperty("创建人")
    private String creator;

    @Schema(description = "创建时间")
    @ExcelProperty("创建时间")
    private LocalDateTime createTime;

}