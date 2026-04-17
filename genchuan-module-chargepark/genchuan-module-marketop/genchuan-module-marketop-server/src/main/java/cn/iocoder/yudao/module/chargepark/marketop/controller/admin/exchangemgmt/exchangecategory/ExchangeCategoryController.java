package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory.ExchangeCategoryService;
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

@Tag(name = "管理后台 - 兑换类目")
@RestController
@RequestMapping("/marketop/exchange-category")
public class ExchangeCategoryController {

    @Resource
    private ExchangeCategoryService exchangeCategoryService;

    @GetMapping("/page")
    @Operation(summary = "获得兑换类目分页")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<PageResult<ExchangeCategoryRespVO>> getPage(ExchangeCategoryPageReqVO reqVO) {
        PageResult<ExchangeCategoryDO> pageResult = exchangeCategoryService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, ExchangeCategoryRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得兑换类目详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<ExchangeCategoryRespVO> get(@RequestParam("id") Long id) {
        ExchangeCategoryDO exchangeCategory = exchangeCategoryService.get(id);
        return CommonResult.success(BeanUtils.toBean(exchangeCategory, ExchangeCategoryRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建兑换类目")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:create')")
    public CommonResult<Long> create(@Valid @RequestBody ExchangeCategoryCreateReqVO reqVO) {
        return CommonResult.success(exchangeCategoryService.create(reqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新兑换类目")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:update')")
    public CommonResult<Boolean> update(@Valid @RequestBody ExchangeCategoryUpdateReqVO reqVO) {
        exchangeCategoryService.update(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "生效兑换类目")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:update')")
    public CommonResult<Boolean> enable(@RequestParam("id") Long id) {
        exchangeCategoryService.enable(id);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "禁用兑换类目")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:update')")
    public CommonResult<Boolean> disable(@RequestParam("id") Long id) {
        exchangeCategoryService.disable(id);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出兑换类目")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public void export(ExchangeCategoryPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ExchangeCategoryDO> pageResult = exchangeCategoryService.getPage(reqVO);
        List<ExchangeCategoryRespVO> list = BeanUtils.toBean(pageResult.getList(), ExchangeCategoryRespVO.class);
        ExcelUtils.write(response, "兑换类目.xlsx", "数据", ExchangeCategoryRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "兑换类目统计图表")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<ExchangeCategoryChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(exchangeCategoryService.getChart(timeRange));
    }

}
