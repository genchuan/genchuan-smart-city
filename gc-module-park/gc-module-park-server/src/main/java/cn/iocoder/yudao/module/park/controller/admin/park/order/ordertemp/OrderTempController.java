package cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp;

import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempGenerateReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.ordertemp.vo.OrderTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.ordertemp.OrderTempDO;
import cn.iocoder.yudao.module.park.service.park.order.ordertemp.OrderTempService;
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


@Tag(name = "管理后台 - 临停订单")
@RestController
@RequestMapping("/park/order-temp")
@Validated
public class OrderTempController {

    @Resource
    private OrderTempService orderTempService;

    //车辆出场的时候调用
    @PostMapping("/generate")
    @Operation(summary = "创建临停订单,自动计算金额")
    @PreAuthorize("@ss.hasPermission('park:order-temp:generate')")
    public CommonResult<Long> generateOrderTemp(@Valid @RequestBody OrderTempGenerateReqVO reqVO) {
        Long generateOrderTempId = orderTempService.generateOrderTemp(reqVO);
        return success(generateOrderTempId);
    }
    @PostMapping("/create")
    @Operation(summary = "（暂时别用）创建临停订单")
    @PreAuthorize("@ss.hasPermission('park:order-temp:create')")
    public CommonResult<Long> createOrderTemp(@Valid @RequestBody OrderTempSaveReqVO createReqVO) {
        return success(orderTempService.createOrderTemp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新临停订单")
    @PreAuthorize("@ss.hasPermission('park:order-temp:update')")
    public CommonResult<Boolean> updateOrderTemp(@Valid @RequestBody OrderTempSaveReqVO updateReqVO) {
        orderTempService.updateOrderTemp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除临停订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-temp:delete')")
    public CommonResult<Boolean> deleteOrderTemp(@RequestParam("id") Long id) {
        orderTempService.deleteOrderTemp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得临停订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-temp:query')")
    public CommonResult<OrderTempRespVO> getOrderTemp(@RequestParam("id") Long id) {
        OrderTempDO orderTemp = orderTempService.getOrderTemp(id);
        return success(BeanUtils.toBean(orderTemp, OrderTempRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得临停订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-temp:query')")
    public CommonResult<PageResult<OrderTempRespVO>> getOrderTempPage(@Valid OrderTempPageReqVO pageReqVO) {
        PageResult<OrderTempDO> pageResult = orderTempService.getOrderTempPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderTempRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出临停订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-temp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderTempExcel(@Valid OrderTempPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderTempDO> list = orderTempService.getOrderTempPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "临停订单.xls", "数据", OrderTempRespVO.class,
                        BeanUtils.toBean(list, OrderTempRespVO.class));
    }

}
