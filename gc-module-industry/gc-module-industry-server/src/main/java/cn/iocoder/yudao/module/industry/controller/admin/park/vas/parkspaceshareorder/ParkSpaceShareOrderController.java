package cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderRespVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkspaceshareorder.vo.ParkSpaceShareOrderSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkspaceshareorder.ParkSpaceShareOrderDO;
import cn.iocoder.yudao.module.industry.service.park.vas.parkspaceshareorder.ParkSpaceShareOrderService;
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


@Tag(name = "漳州停车管理-增值服务域 - 车位共享订单")
@RestController
@RequestMapping("/industry/park-space-share-order")
@Validated
public class ParkSpaceShareOrderController {

    @Resource
    private ParkSpaceShareOrderService parkSpaceShareOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建车位共享订单")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:create')")
    public CommonResult<Long> createParkSpaceShareOrder(@Valid @RequestBody ParkSpaceShareOrderSaveReqVO createReqVO) {
        return success(parkSpaceShareOrderService.createParkSpaceShareOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新车位共享订单")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:update')")
    public CommonResult<Boolean> updateParkSpaceShareOrder(@Valid @RequestBody ParkSpaceShareOrderSaveReqVO updateReqVO) {
        parkSpaceShareOrderService.updateParkSpaceShareOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除车位共享订单")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:delete')")
    public CommonResult<Boolean> deleteParkSpaceShareOrder(@RequestParam("id") Long id) {
        parkSpaceShareOrderService.deleteParkSpaceShareOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得车位共享订单")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:query')")
    public CommonResult<ParkSpaceShareOrderRespVO> getParkSpaceShareOrder(@RequestParam("id") Long id) {
        ParkSpaceShareOrderDO parkSpaceShareOrder = parkSpaceShareOrderService.getParkSpaceShareOrder(id);
        return success(BeanUtils.toBean(parkSpaceShareOrder, ParkSpaceShareOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得车位共享订单分页")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:query')")
    public CommonResult<PageResult<ParkSpaceShareOrderRespVO>> getParkSpaceShareOrderPage(@Valid ParkSpaceShareOrderPageReqVO pageReqVO) {
        PageResult<ParkSpaceShareOrderDO> pageResult = parkSpaceShareOrderService.getParkSpaceShareOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ParkSpaceShareOrderRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出车位共享订单 Excel")
    @PreAuthorize("@ss.hasPermission('industry:park-space-share-order:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportParkSpaceShareOrderExcel(@Valid ParkSpaceShareOrderPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ParkSpaceShareOrderDO> list = parkSpaceShareOrderService.getParkSpaceShareOrderPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "车位共享订单.xls", "数据", ParkSpaceShareOrderRespVO.class,
                        BeanUtils.toBean(list, ParkSpaceShareOrderRespVO.class));
    }

}
