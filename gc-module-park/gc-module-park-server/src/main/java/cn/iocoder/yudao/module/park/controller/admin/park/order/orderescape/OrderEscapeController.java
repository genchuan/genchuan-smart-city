package cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape;

import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapeRespVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.orderescape.vo.OrderEscapeSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.orderescape.OrderEscapeDO;
import cn.iocoder.yudao.module.park.service.park.order.orderescape.OrderEscapeService;
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


@Tag(name = "管理后台 - 逃费订单")
@RestController
@RequestMapping("/park/order-escape")
@Validated
public class OrderEscapeController {

    @Resource
    private OrderEscapeService orderEscapeService;

    @PostMapping("/create")
    @Operation(summary = "创建逃费订单")
    @PreAuthorize("@ss.hasPermission('park:order-escape:create')")
    public CommonResult<Long> createOrderEscape(@Valid @RequestBody OrderEscapeSaveReqVO createReqVO) {
        return success(orderEscapeService.createOrderEscape(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新逃费订单")
    @PreAuthorize("@ss.hasPermission('park:order-escape:update')")
    public CommonResult<Boolean> updateOrderEscape(@Valid @RequestBody OrderEscapeSaveReqVO updateReqVO) {
        orderEscapeService.updateOrderEscape(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除逃费订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-escape:delete')")
    public CommonResult<Boolean> deleteOrderEscape(@RequestParam("id") Long id) {
        orderEscapeService.deleteOrderEscape(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得逃费订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-escape:query')")
    public CommonResult<OrderEscapeRespVO> getOrderEscape(@RequestParam("id") Long id) {
        OrderEscapeDO orderEscape = orderEscapeService.getOrderEscape(id);
        return success(BeanUtils.toBean(orderEscape, OrderEscapeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得逃费订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-escape:query')")
    public CommonResult<PageResult<OrderEscapeRespVO>> getOrderEscapePage(@Valid OrderEscapePageReqVO pageReqVO) {
        PageResult<OrderEscapeDO> pageResult = orderEscapeService.getOrderEscapePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OrderEscapeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出逃费订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-escape:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderEscapeExcel(@Valid OrderEscapePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OrderEscapeDO> list = orderEscapeService.getOrderEscapePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "逃费订单.xls", "数据", OrderEscapeRespVO.class,
                        BeanUtils.toBean(list, OrderEscapeRespVO.class));
    }

}
