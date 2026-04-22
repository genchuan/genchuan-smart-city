package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Schema(description = "场站资源报表响应 VO")
public class StationOpReportRespVO {

    @Schema(description = "主键 ID 报表记录ID")
    private Long id;

    @Schema(description = "统计编码")
    private String statCode;

    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
    private String reportCycle;

    @Schema(description = "报表开始时间")
    private LocalDateTime reportStartTime;

    @Schema(description = "报表结束时间")
    private LocalDateTime reportEndTime;

    @Schema(description = "总片区数 来源：area_info 表统计有效记录总数")
    private Long totalAreaCount;

    @Schema(description = "覆盖场站数 来源：area_info 表 station_count 字段求和")
    private Long coverStationCount;

    @Schema(description = "总站场数 来源：station_info 表统计有效记录总数")
    private Long totalStationCount;

    @Schema(description = "正常运营数 来源：station_info 表 status = 已生效 的场站数量")
    private Long normalOperateCount;

    @Schema(description = "总车位数 来源：parking_space_info 表统计有效车位总数")
    private Long totalSpaceCount;

    @Schema(description = "可用车位数 来源：parking_space_info 表 real_status = 空闲 的车位数量")
    private Long availableSpaceCount;

    @Schema(description = "生效规则数 来源：time_permission + fee_rule + charge_park_link 表 status = 已生效 的规则总数")
    private Long effectiveRuleCount;

    // TODO 这是有时间筛选的
    @Schema(description = "订单量 来源：订单相关表统计当前周期内订单总数（预留）")
    private Long orderCount;

    // TODO 这是有时间筛选的
    @Schema(description = "营收 来源：订单相关表统计当前周期内总营收（预留）")
    private BigDecimal revenue;

    @Schema(description = "追缴完成率 来源：debt_expand 表 recovery_rate 字段平均值")
    private BigDecimal recoveryRate;

    @Schema(description = "押金订单量 来源：deposit_plan 表 deposit_order_count 字段求和")
    private Long depositOrderCount;

    @Schema(description = "生成状态（生成中/已生成/生成失败）")
    private String generateStatus;

    @Schema(description = "报表生成时间")
    private String generateTime;

    @Schema(description = "操作人 关联 system_user 用户名称")
    private String operator;

    @Schema(description = "报表导出次数")
    private Long exportCount;

    @Schema(description = "自定义筛选配置（仅自定义报表使用）")
    private String customConfig;

    @Schema(description = "备注 扩展说明")
    private String remark;

    @Schema(description = "备用字段1 预留扩展")
    private String reserve1;

    @Schema(description = "备用字段2 预留扩展")
    private String reserve2;

    @Schema(description = "创建者 关联 system_user 创建人")
    private String creator;

    @Schema(description = "更新者 更新人账号/姓名")
    private String updater;

//    @Schema(description = "删除标识 0-未删除 1-已删除")
//    private Boolean deleted;

    @Schema(description = "创建时间")
    private String createTime;

    @Schema(description = "更新时间")
    private String updateTime;

}

//package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.stationreport.vo.ops;
//
//import io.swagger.v3.oas.annotations.media.Schema;
//import lombok.Data;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Data
//@Schema(description = "场站资源报表响应 VO")
//public class StationOpReportRespVO {
//
//    @Schema(description = "主键 ID 报表记录ID")
//    private Long id;
//
//    @Schema(description = "报表周期（日报/周报/月报/季报/半年报/年报/自定义报表）")
//    private String reportCycle;
//
//    @Schema(description = "报表开始时间")
//    private LocalDateTime reportStartTime;
//
//    @Schema(description = "报表结束时间")
//    private LocalDateTime reportEndTime;
//
//    @Schema(description = "总片区数 来源：area_info 表统计有效记录总数")
//    private Long totalAreaCount;
//
//    @Schema(description = "覆盖场站数 来源：area_info 表 station_count 字段求和")
//    private Long coverStationCount;
//
//    @Schema(description = "总站场数 来源：station_info 表统计有效记录总数")
//    private Long totalStationCount;
//
//    @Schema(description = "正常运营数 来源：station_info 表 status = 已生效 的场站数量")
//    private Long normalOperateCount;
//
//    @Schema(description = "总车位数 来源：parking_space_info 表统计有效车位总数")
//    private Long totalSpaceCount;
//
//    @Schema(description = "可用车位数 来源：parking_space_info 表 real_status = 空闲 的车位数量")
//    private Long availableSpaceCount;
//
//    @Schema(description = "生效规则数 来源：time_permission + fee_rule + charge_park_link 表 status = 已生效 的规则总数")
//    private Long effectiveRuleCount;
//
//    // TODO 这是有时间筛选的
//    @Schema(description = "订单量 来源：订单相关表统计当前周期内订单总数（预留）")
//    private Long orderCount;
//
//    // TODO 这是有时间筛选的
//    @Schema(description = "营收 来源：订单相关表统计当前周期内总营收（预留）")
//    private BigDecimal revenue;
//
//    @Schema(description = "追缴完成率 来源：debt_expand 表 recovery_rate 字段平均值")
//    private BigDecimal recoveryRate;
//
//    @Schema(description = "押金订单量 来源：deposit_plan 表 deposit_order_count 字段求和")
//    private Long depositOrderCount;
//
//    @Schema(description = "生成状态（生成中/已生成/生成失败）")
//    private String generateStatus;
//
//    @Schema(description = "报表生成时间")
//    private String generateTime;
//
//    @Schema(description = "操作人 关联 system_user 用户名称")
//    private String operator;
//
//    @Schema(description = "报表导出次数")
//    private Long exportCount;
//
//    @Schema(description = "创建者 关联 system_user 创建人")
//    private String creator;
//
//    @Schema(description = "创建时间")
//    private String createTime;
//
//    @Schema(description = "更新时间")
//    private String updateTime;
//
//}
