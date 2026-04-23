package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory.ExchangeCategoryService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Tag(name = "管理后台 - 兑换类目")
@RestController
@RequestMapping("/marketop/exchange-category")
public class ExchangeCategoryController {

    @Resource
    private ExchangeCategoryService exchangeCategoryService;

    @Resource
    private AdminUserApi adminUserApi;

    @GetMapping("/page")
    @Operation(summary = "获得兑换类目分页")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<PageResult<ExchangeCategoryRespVO>> getPage(ExchangeCategoryPageReqVO reqVO) {
        PageResult<ExchangeCategoryDO> pageResult = exchangeCategoryService.getPage(reqVO);
        PageResult<ExchangeCategoryRespVO> bean = BeanUtils.toBean(pageResult, ExchangeCategoryRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得兑换类目详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<ExchangeCategoryRespVO> get(@RequestParam("id") Long id) {
        ExchangeCategoryDO exchangeCategory = exchangeCategoryService.get(id);
        ExchangeCategoryRespVO respVO = BeanUtils.toBean(exchangeCategory, ExchangeCategoryRespVO.class);
        injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
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

    @GetMapping("/get-import-template")
    @Operation(summary = "获得导入兑换类目模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<ExchangeCategoryImportExcelVO> list = Arrays.asList(
                ExchangeCategoryImportExcelVO.builder().name("数码配件").scope("全平台").sort(1).description("各类充电、数码相关配件").build(),
                ExchangeCategoryImportExcelVO.builder().name("生活用品").scope("指定场站").sort(2).description("日常生活用品").build()
        );
        ExcelUtils.write(response, "兑换类目导入模板.xls", "类目列表", ExchangeCategoryImportExcelVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "兑换类目统计图表")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-category:query')")
    public CommonResult<ExchangeCategoryChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(exchangeCategoryService.getChart(timeRange));
    }

    private void injectUserNames(List<ExchangeCategoryRespVO> list) {
        // 收集所有需要查询的用户ID
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                userIds.add(Long.valueOf(item.getCreator()));
            }
            if (item.getAuditorId() != null) {
                userIds.add(item.getAuditorId());
            }
        }
        if (userIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                AdminUserRespDTO user = userMap.get(Long.valueOf(item.getCreator()));
                if (user != null) item.setCreatorName(user.getNickname());
            }
            if (item.getAuditorId() != null) {
                AdminUserRespDTO user = userMap.get(item.getAuditorId());
                if (user != null) item.setAuditorName(user.getNickname());
            }
        }
    }

}
