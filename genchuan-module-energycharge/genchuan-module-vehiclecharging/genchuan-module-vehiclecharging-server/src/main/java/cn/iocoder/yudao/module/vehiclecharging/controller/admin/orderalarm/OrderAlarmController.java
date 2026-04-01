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

@Tag(name = "管理后台 - 订单告警")
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

    @GetMapping("/page")
    @Operation(summary = "获得订单告警分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:order-alarm:query')")
    public CommonResult<PageResult<OrderAlarmRespVO>> getOrderAlarmPage(@Valid OrderAlarmPageReqVO pageReqVO) {
        PageResult<OrderAlarmDO> pageResult = orderAlarmService.getOrderAlarmPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderAlarmRespVO.class));
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

}