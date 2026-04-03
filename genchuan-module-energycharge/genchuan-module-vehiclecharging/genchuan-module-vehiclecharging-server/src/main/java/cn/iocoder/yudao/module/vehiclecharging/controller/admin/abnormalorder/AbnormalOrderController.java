package cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder;

import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import cn.iocoder.yudao.module.vehiclecharging.service.abnormalorder.AbnormalOrderService;
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

import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;


@Tag(name = "管理后台 - 异常订单")
@RestController
@RequestMapping("/vehiclecharging/abnormal-order")
@Validated
public class AbnormalOrderController {

    @Resource
    private AbnormalOrderService abnormalOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建异常订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:create')")
    public CommonResult<Long> createAbnormalOrder(@Valid @RequestBody AbnormalOrderSaveReqVO createReqVO) {
        return success(abnormalOrderService.createAbnormalOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新异常订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:update')")
    public CommonResult<Boolean> updateAbnormalOrder(@Valid @RequestBody AbnormalOrderSaveReqVO updateReqVO) {
        abnormalOrderService.updateAbnormalOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除异常订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:delete')")
    public CommonResult<Boolean> deleteAbnormalOrder(@RequestParam("id") Long id) {
        abnormalOrderService.deleteAbnormalOrder(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除异常订单")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:delete')")
    public CommonResult<Boolean> deleteAbnormalOrderList(@RequestParam("ids") List<Long> ids) {
        abnormalOrderService.deleteAbnormalOrderListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得异常订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:query')")
    public CommonResult<AbnormalOrderRespVO> getAbnormalOrder(@RequestParam("id") Long id) {
        AbnormalOrderDO abnormalOrder = abnormalOrderService.getAbnormalOrder(id);
        return success(BeanUtils.toBean(abnormalOrder, AbnormalOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得异常订单分页")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:query')")
    public CommonResult<PageResult<AbnormalOrderRespVO>> getAbnormalOrderPage(@Valid AbnormalOrderPageReqVO pageReqVO) {
        PageResult<AbnormalOrderDO> pageResult = abnormalOrderService.getAbnormalOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AbnormalOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出异常订单 Excel")
    @PreAuthorize("@ss.hasPermission('vehiclecharging:abnormal-order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAbnormalOrderExcel(@Valid AbnormalOrderPageReqVO pageReqVO,
                                         HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AbnormalOrderDO> list = abnormalOrderService.getAbnormalOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "异常订单.xls", "数据", AbnormalOrderRespVO.class,
                BeanUtils.toBean(list, AbnormalOrderRespVO.class));
    }

}