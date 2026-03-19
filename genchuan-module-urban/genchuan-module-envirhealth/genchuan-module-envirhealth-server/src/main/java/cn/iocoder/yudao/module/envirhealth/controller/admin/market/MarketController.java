package cn.iocoder.yudao.module.envirhealth.controller.admin.market;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.market.vo.MarketSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.market.MarketDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.MarketDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.market.MarketService;
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
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 集贸市场")
@RestController
@RequestMapping("/envirhealth/market")
@Validated
public class MarketController {

    @Resource
    private MarketService marketService;

    @PostMapping("/create")
    @Operation(summary = "创建集贸市场")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:create')")
    public CommonResult<Long> createMarket(@Valid @RequestBody MarketSaveReqVO createReqVO) {
        return success(marketService.createMarket(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新集贸市场")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:update')")
    public CommonResult<Boolean> updateMarket(@Valid @RequestBody MarketSaveReqVO updateReqVO) {
        marketService.updateMarket(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除集贸市场")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:market:delete')")
    public CommonResult<Boolean> deleteMarket(@RequestParam("id") Long id) {
        marketService.deleteMarket(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得集贸市场")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:query')")
    public CommonResult<MarketRespVO> getMarket(@RequestParam("id") Long id) {
        MarketDO market = marketService.getMarket(id);
        return success(BeanUtils.toBean(market, MarketRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得集贸市场分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:query')")
    public CommonResult<PageResult<MarketRespVO>> getMarketPage(@Valid MarketPageReqVO pageReqVO) {
        PageResult<MarketDO> pageResult = marketService.getMarketPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MarketRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出集贸市场 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMarketExcel(@Valid MarketPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MarketDO> list = marketService.getMarketPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("集贸市场_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "集贸市场.xls", "数据", MarketRespVO.class,
                        BeanUtils.toBean(list, MarketRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得集贸市场详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:market:query')")
    public CommonResult<PageResult<MarketDetailDO>> getMarketDetailPage(
            @Valid MarketPageReqVO pageReqVO) {
        PageResult<MarketDetailDO> pageResult =
                marketService.getMarketDetailPage(pageReqVO);

        return success(pageResult);
    }
}
