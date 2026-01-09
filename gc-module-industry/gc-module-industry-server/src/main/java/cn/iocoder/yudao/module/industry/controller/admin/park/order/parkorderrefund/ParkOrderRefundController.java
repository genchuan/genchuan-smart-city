package cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parkorderrefund.vo.ParkOrderRefundSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parkorderrefund.ParkOrderRefundDO;
import cn.iocoder.yudao.module.industry.service.park.order.parkorderrefund.ParkOrderRefundService;
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



@Tag(name = "管理后台 - 退款订单")
@RestController
@RequestMapping("/industry/park-order-refund")
@Validated
public class ParkOrderRefundController {

    @Resource
    private ParkOrderRefundService orderRefundService;

    @PostMapping("/create")
    @Operation(summary = "创建退款订单")
    @PreAuthorize("@ss.hasPermission('park:order-refund:create')")
    public CommonResult<Long> createOrderRefund(@Valid @RequestBody ParkOrderRefundSaveReqVO createReqVO) {
        return success(orderRefundService.createOrderRefund(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新退款订单")
    @PreAuthorize("@ss.hasPermission('park:order-refund:update')")
    public CommonResult<Boolean> updateOrderRefund(@Valid @RequestBody ParkOrderRefundSaveReqVO updateReqVO) {
        orderRefundService.updateOrderRefund(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除退款订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('park:order-refund:delete')")
    public CommonResult<Boolean> deleteOrderRefund(@RequestParam("id") Long id) {
        orderRefundService.deleteOrderRefund(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得退款订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('park:order-refund:query')")
    public CommonResult<ParkOrderRefundRespVO> getOrderRefund(@RequestParam("id") Long id) {
        ParkOrderRefundDO orderRefund = orderRefundService.getOrderRefund(id);
        return success(BeanUtils.toBean(orderRefund, ParkOrderRefundRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得退款订单分页")
    @PreAuthorize("@ss.hasPermission('park:order-refund:query')")
    public CommonResult<PageResult<ParkOrderRefundRespVO>> getOrderRefundPage(@Valid ParkOrderRefundPageReqVO pageReqVO) {
        PageResult<ParkOrderRefundDO> pageResult = orderRefundService.getOrderRefundPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkOrderRefundRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出退款订单 Excel")
    @PreAuthorize("@ss.hasPermission('park:order-refund:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOrderRefundExcel(@Valid ParkOrderRefundPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkOrderRefundDO> list = orderRefundService.getOrderRefundPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "退款订单.xls", "数据", ParkOrderRefundRespVO.class,
                        BeanUtils.toBean(list, ParkOrderRefundRespVO.class));
    }

}
