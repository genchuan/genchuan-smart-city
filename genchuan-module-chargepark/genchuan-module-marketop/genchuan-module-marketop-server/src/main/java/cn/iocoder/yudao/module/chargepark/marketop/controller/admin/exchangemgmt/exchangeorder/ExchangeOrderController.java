package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangeorder.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeOrderPayStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory.ExchangeCategoryService;
import cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangeorder.ExchangeOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import cn.hutool.core.util.StrUtil;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 兑换订单")
@RestController
@RequestMapping("/marketop/exchange-order")
public class ExchangeOrderController {

    @Resource
    private ExchangeOrderService exchangeOrderService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private ExchangeCategoryService exchangeCategoryService;

    @GetMapping("/page")
    @Operation(summary = "获得兑换订单分页")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<PageResult<ExchangeOrderRespVO>> getPage(ExchangeOrderPageReqVO reqVO) {
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderService.getPage(reqVO);
        PageResult<ExchangeOrderRespVO> bean = BeanUtils.toBean(pageResult, ExchangeOrderRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得兑换订单详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderRespVO> get(@RequestParam("id") Long id) {
        ExchangeOrderDO exchangeOrder = exchangeOrderService.get(id);
        ExchangeOrderRespVO respVO = BeanUtils.toBean(exchangeOrder, ExchangeOrderRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/pay")
    @Operation(summary = "支付兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:pay')")
    public CommonResult<Boolean> pay(@Valid @RequestBody ExchangeOrderPayReqVO reqVO) {
        exchangeOrderService.pay(reqVO.getId());
        return CommonResult.success(true);
    }

    @PutMapping("/ship")
    @Operation(summary = "发货兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:deliver')")
    public CommonResult<Boolean> deliver(@Valid @RequestBody ExchangeOrderDeliverReqVO reqVO) {
        exchangeOrderService.deliver(reqVO);
        return CommonResult.success(true);
    }

    @PutMapping("/cancel")
    @Operation(summary = "取消兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:cancel')")
    public CommonResult<Boolean> cancel(@Valid @RequestBody ExchangeOrderCancelReqVO reqVO) {
        exchangeOrderService.cancel(reqVO.getId());
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void export(ExchangeOrderPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderService.getPage(reqVO);
        List<ExchangeOrderRespVO> list = BeanUtils.toBean(pageResult.getList(), ExchangeOrderRespVO.class);
        injectUserNames(list);
        list.forEach(item -> {
            item.setPayStatus(ExchangeOrderPayStatusEnum.labelOf(item.getPayStatus()));
        });
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderRespVO.class, list);
    }

    @GetMapping("/batch-export")
    @Operation(summary = "批量导出兑换订单")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public void batchExport(@RequestParam(value = "ids", required = false) List<Long> ids, HttpServletResponse response) throws IOException {
        List<ExchangeOrderDO> list = new ArrayList<>();
        if (ids != null && !ids.isEmpty()) {
            list = exchangeOrderService.getListByIds(ids);}
//        } else {
//            reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//            list = exchangeOrderService.getPage(reqVO).getList();
//        }
        List<ExchangeOrderRespVO> voList = BeanUtils.toBean(list, ExchangeOrderRespVO.class);
        injectUserNames(voList);
        ExcelUtils.write(response, "兑换订单.xlsx", "数据", ExchangeOrderRespVO.class, voList);
    }

    @GetMapping("/chart")
    @Operation(summary = "兑换订单统计图表")
    @PreAuthorize("@ss.hasPermission('marketop:exchange-order:query')")
    public CommonResult<ExchangeOrderChartRespVO> getChart() {
        return CommonResult.success(exchangeOrderService.getChart());
    }

    private void injectUserNames(List<ExchangeOrderRespVO> list) {
        if (list == null || list.isEmpty()) return;
        // 收集用户ID（creator + userId）
        Set<Long> userIds = new HashSet<>();
        Set<Long> categoryIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
            if (item.getUserId() != null) {
                userIds.add(item.getUserId());
            }
            if (item.getCategoryId() != null) {
                categoryIds.add(item.getCategoryId());
            }
        }
        // 翻译用户名称（creatorName + userName）
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
                if (item.getUserId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getUserId());
                    if (user != null) item.setUserName(user.getNickname());
                }
            }
        }
        // 翻译类目名称（categoryName）
        if (!categoryIds.isEmpty()) {
            Map<Long, String> categoryNameMap = new HashMap<>();
            for (Long categoryId : categoryIds) {
                ExchangeCategoryDO category = exchangeCategoryService.get(categoryId);
                if (category != null) categoryNameMap.put(categoryId, category.getName());
            }
            for (var item : list) {
                if (item.getCategoryId() != null) {
                    String name = categoryNameMap.get(item.getCategoryId());
                    if (name != null) item.setCategoryName(name);
                }
            }
        }
    }

    private Long safeParseLong(String s) {
        if (s == null) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
