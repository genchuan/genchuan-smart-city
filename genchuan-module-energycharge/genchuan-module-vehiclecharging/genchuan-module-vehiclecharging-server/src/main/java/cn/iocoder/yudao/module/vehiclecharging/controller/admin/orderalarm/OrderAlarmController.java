package cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
import jakarta.validation.*;
import jakarta.servlet.http.*;
import java.util.*;
import java.io.IOException;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.*;

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.orderalarm.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.orderalarm.OrderAlarmDO;
import cn.iocoder.yudao.module.vehiclecharging.service.orderalarm.OrderAlarmService;

@Tag(name = "汽车充电 - 订单告警")
@RestController
@RequestMapping("/vehiclecharging/order-alarm")
@Validated
public class OrderAlarmController {

    @Resource
    private OrderAlarmService orderAlarmService;

    @PostMapping("/create")
    @Operation(summary = "创建订单告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:create')")
    public CommonResult<Long> createOrderAlarm(@Valid @RequestBody OrderAlarmSaveReqVO createReqVO) {
        return success(orderAlarmService.createOrderAlarm(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新订单告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:update')")
    public CommonResult<Boolean> updateOrderAlarm(@Valid @RequestBody OrderAlarmSaveReqVO updateReqVO) {
        orderAlarmService.updateOrderAlarm(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除订单告警")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:delete')")
    public CommonResult<Boolean> deleteOrderAlarm(@RequestParam("id") Long id) {
        orderAlarmService.deleteOrderAlarm(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除订单告警")
                @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:delete')")
    public CommonResult<Boolean> deleteOrderAlarmList(@RequestParam("ids") List<Long> ids) {
        orderAlarmService.deleteOrderAlarmListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得订单告警")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:query')")
    public CommonResult<OrderAlarmRespVO> getOrderAlarm(@RequestParam("id") Long id) {
        OrderAlarmDO orderAlarm = orderAlarmService.getOrderAlarm(id);
        return success(BeanUtils.toBean(orderAlarm, OrderAlarmRespVO.class));
    }

//    @GetMapping("/page")
//    @Operation(summary = "获得订单告警分页")
//    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:query')")
//    public CommonResult<PageResult<OrderAlarmRespVO>> getOrderAlarmPage(@Valid OrderAlarmPageReqVO pageReqVO) {
//        PageResult<OrderAlarmDO> pageResult = orderAlarmService.getOrderAlarmPage(pageReqVO);
//        return success(BeanUtils.toBean(pageResult, OrderAlarmRespVO.class));
//    }

    @GetMapping("/page")
    @Operation(summary = "获得订单告警分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:query')")
    public CommonResult<PageResult<OrderAlarmRespVO>> getOrderAlarmPage(@Valid OrderAlarmPageReqVO pageReqVO) {
        // 调用服务层获取分页结果
        PageResult<OrderAlarmDO> pageResult = orderAlarmService.getOrderAlarmPage(pageReqVO);

        // 将DO转换为VO，并添加模拟数据
        List<OrderAlarmRespVO> voList = new ArrayList<>();
        for (OrderAlarmDO doObj : pageResult.getList()) {
            OrderAlarmRespVO vo = BeanUtils.toBean(doObj, OrderAlarmRespVO.class);

            // 添加模拟的充电桩名称
            if (doObj.getPileCode() != null && !doObj.getPileCode().isEmpty()) {
                vo.setPileName("模拟充电桩-" + doObj.getPileCode());
            } else {
                vo.setPileName("未关联充电桩");
            }

            voList.add(vo);
        }

        // 创建新的分页结果
        PageResult<OrderAlarmRespVO> voPageResult = new PageResult<>(
                voList, pageResult.getTotal()
        );

        return success(voPageResult);
    }

    @PutMapping("/verify")
    @Operation(summary = "核实订单告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:verify')")
    public CommonResult<Boolean> verifyOrderAlarm(@Valid @RequestBody OrderAlarmVerifyReqVO verifyReqVO) {
        orderAlarmService.verifyOrderAlarm(verifyReqVO);
        return success(true);
    }

    @PutMapping("/handle")
    @Operation(summary = "处理订单告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:handle')")
    public CommonResult<Boolean> handleOrderAlarm(@Valid @RequestBody OrderAlarmHandleReqVO handleReqVO) {
        orderAlarmService.handleOrderAlarm(handleReqVO);
        return success(true);
    }

    @PutMapping("/complete")
    @Operation(summary = "完结订单告警")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:complete')")
    public CommonResult<Boolean> completeOrderAlarm(@Valid @RequestBody OrderAlarmCompleteReqVO completeReqVO) {
        orderAlarmService.completeOrderAlarm(completeReqVO);
        return success(true);
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出订单告警 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderAlarmExcel(@Valid OrderAlarmPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderAlarmDO> list = orderAlarmService.getOrderAlarmPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "订单告警.xls", "数据", OrderAlarmRespVO.class,
                        BeanUtils.toBean(list, OrderAlarmRespVO.class));
    }

    @GetMapping("/chart")
    @Operation(summary = "获取订单告警图表统计数据")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:chart')")
    public CommonResult<OrderAlarmChartRespVO> getOrderAlarmChart(@Valid OrderAlarmChartReqVO reqVO) {
        OrderAlarmChartRespVO chartData = orderAlarmService.getOrderAlarmChartData(reqVO);
        return success(chartData);
    }

    @PutMapping("/remark")
    @Operation(summary = "更新订单告警备注")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:remark')")
    public CommonResult<Boolean> remarkOrderAlarm(@Valid @RequestBody OrderAlarmRemarkReqVO remarkReqVO) {
        orderAlarmService.remarkOrderAlarm(remarkReqVO);
        return success(true);
    }

}