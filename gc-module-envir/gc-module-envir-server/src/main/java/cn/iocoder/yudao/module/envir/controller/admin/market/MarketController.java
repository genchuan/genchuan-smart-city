package cn.iocoder.yudao.module.envir.controller.admin.market;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDetailDO;
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

import cn.iocoder.yudao.module.envir.controller.admin.market.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envir.service.market.MarketService;

@Tag(name = "管理后台 - 集贸市场")
@RestController
@RequestMapping("/envir/market")
@Validated
public class MarketController {

    @Resource
    private MarketService marketService;

    @PostMapping("/create")
    @Operation(summary = "创建集贸市场")
    @PreAuthorize("@ss.hasPermission('envir:market:create')")
    public CommonResult<Long> createMarket(@Valid @RequestBody MarketSaveReqVO createReqVO) {
        return success(marketService.createMarket(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新集贸市场")
    @PreAuthorize("@ss.hasPermission('envir:market:update')")
    public CommonResult<Boolean> updateMarket(@Valid @RequestBody MarketSaveReqVO updateReqVO) {
        marketService.updateMarket(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除集贸市场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:market:delete')")
    public CommonResult<Boolean> deleteMarket(@RequestParam("id") Long id) {
        marketService.deleteMarket(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得集贸市场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:market:query')")
    public CommonResult<MarketRespVO> getMarket(@RequestParam("id") Long id) {
        MarketDO market = marketService.getMarket(id);
        return success(BeanUtils.toBean(market, MarketRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得集贸市场分页")
    @PreAuthorize("@ss.hasPermission('envir:market:query')")
    public CommonResult<PageResult<MarketRespVO>> getMarketPage(@Valid MarketPageReqVO pageReqVO) {
        PageResult<MarketDO> pageResult = marketService.getMarketPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MarketRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出集贸市场 Excel")
    @PreAuthorize("@ss.hasPermission('envir:market:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMarketExcel(@Valid MarketPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MarketDO> list = marketService.getMarketPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "集贸市场.xls", "数据", MarketRespVO.class,
                        BeanUtils.toBean(list, MarketRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取集贸市场列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:market:query')")
    public CommonResult<List<MarketDetailDO>> getMarketListDetail() {
        List<MarketDetailDO> list = marketService.getMarketListDetail();
        return success(list);
    }
}