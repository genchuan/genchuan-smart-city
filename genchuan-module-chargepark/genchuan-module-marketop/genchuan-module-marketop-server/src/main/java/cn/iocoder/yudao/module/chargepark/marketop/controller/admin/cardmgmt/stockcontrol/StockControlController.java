package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol.StockControlService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 库存管控")
@RestController
@RequestMapping("/marketop/stock-control")
public class StockControlController {

    @Resource
    private StockControlService stockControlService;

    @GetMapping("/page")
    @Operation(summary = "获得库存管控分页")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public CommonResult<PageResult<StockControlRespVO>> getPage(StockControlPageReqVO reqVO) {
        PageResult<StockControlDO> pageResult = stockControlService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, StockControlRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得库存管控详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public CommonResult<StockControlRespVO> get(@RequestParam("id") Long id) {
        StockControlDO stockControl = stockControlService.get(id);
        return CommonResult.success(BeanUtils.toBean(stockControl, StockControlRespVO.class));
    }

    @PutMapping("/restock")
    @Operation(summary = "库存补货")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:update')")
    public CommonResult<Boolean> restock(@Valid @RequestBody StockControlRestockReqVO reqVO) {
        stockControlService.restock(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/warn")
    @Operation(summary = "库存预警")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:update')")
    public CommonResult<Boolean> warn(@RequestParam("id") Long id) {
        stockControlService.warn(id);
        return CommonResult.success(true);
    }

    @PutMapping("/allocate")
    @Operation(summary = "库存调配")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:update')")
    public CommonResult<Boolean> allocate(@Valid @RequestBody StockControlAllocateReqVO reqVO) {
        stockControlService.allocate(reqVO);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出库存管控")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public void export(StockControlPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<StockControlDO> pageResult = stockControlService.getPage(reqVO);
        List<StockControlRespVO> list = BeanUtils.toBean(pageResult.getList(), StockControlRespVO.class);
        ExcelUtils.write(response, "库存管控.xlsx", "数据", StockControlRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "库存管控图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public CommonResult<StockControlChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(stockControlService.getChart(timeRange));
    }

}
