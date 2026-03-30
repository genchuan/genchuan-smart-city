package cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderperiod.vo.OrderPeriodSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderperiod.OrderPeriodDO;
import cn.iocoder.yudao.module.park.service.park.order.orderperiod.OrderPeriodService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 期卡订单")
@RestController
@RequestMapping("/park/order-period")
@Validated
public class OrderPeriodController {

    @Resource
    private OrderPeriodService orderPeriodService;

    @PostMapping("/create")
    @Operation(summary = "创建期卡订单")
    @PreAuthorize("@ss.hasPermission('park:order-period:create')")
    public CommonResult<Long> createOrderPeriod(@Valid @RequestBody OrderPeriodSaveReqVO createReqVO) {
        return success(orderPeriodService.createOrderPeriod(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新期卡订单")
    @PreAuthorize("@ss.hasPermission('park:order-period:update')")
    public CommonResult<Boolean> updateOrderPeriod(@Valid @RequestBody OrderPeriodSaveReqVO updateReqVO) {
        orderPeriodService.updateOrderPeriod(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除期卡订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-period:delete')")
    public CommonResult<Boolean> deleteOrderPeriod(@RequestParam("id") Long id) {
        orderPeriodService.deleteOrderPeriod(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得期卡订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-period:query')")
    public CommonResult<OrderPeriodRespVO> getOrderPeriod(@RequestParam("id") Long id) {
        OrderPeriodDO orderPeriod = orderPeriodService.getOrderPeriod(id);
        return success(BeanUtils.toBean(orderPeriod, OrderPeriodRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得期卡订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-period:query')")
    public CommonResult<PageResult<OrderPeriodRespVO>> getOrderPeriodPage(@Valid OrderPeriodPageReqVO pageReqVO) {
        PageResult<OrderPeriodDO> pageResult = orderPeriodService.getOrderPeriodPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderPeriodRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出期卡订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-period:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderPeriodExcel(@Valid OrderPeriodPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderPeriodDO> list = orderPeriodService.getOrderPeriodPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "期卡订单.xls", "数据", OrderPeriodRespVO.class,
                        BeanUtils.toBean(list, OrderPeriodRespVO.class));
    }

}
