package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.cardmgmt.stockcontrol.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.cardconfig.CardConfigService;
import cn.iocoder.yudao.module.chargepark.marketop.service.cardmgmt.stockcontrol.StockControlService;
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

@Tag(name = "管理后台 - 库存管控")
@RestController
@RequestMapping("/marketop/stock-control")
public class StockControlController {

    @Resource
    private StockControlService stockControlService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private CardConfigService cardConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得库存管控分页")
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public CommonResult<PageResult<StockControlRespVO>> getPage(StockControlPageReqVO reqVO) {
        // 如果startTime和endTime都为空，且date不为空，将date转为当天开始和结束时间
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
                && reqVO.getDate() != null && !reqVO.getDate().isEmpty()) {
            java.time.LocalDate localDate = java.time.LocalDate.parse(reqVO.getDate());
            reqVO.setStartTime(localDate.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(localDate.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<StockControlDO> pageResult = stockControlService.getPage(reqVO);
        PageResult<StockControlRespVO> bean = BeanUtils.toBean(pageResult, StockControlRespVO.class);
        injectUserNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得库存管控详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:stock-control:query')")
    public CommonResult<StockControlRespVO> get(@RequestParam("id") Long id) {
        StockControlDO stockControl = stockControlService.get(id);
        StockControlRespVO respVO = BeanUtils.toBean(stockControl, StockControlRespVO.class);
        if (respVO != null) injectUserNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
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
    public CommonResult<Boolean> warn(@RequestBody StockControlWarnReqVO reqVO) {
        stockControlService.warn(reqVO.getId());
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
    public CommonResult<StockControlChartRespVO> getChart(@RequestParam(value = "startTime", required = false) Long startTime,
                                                          @RequestParam(value = "endTime", required = false) Long endTime,
                                                          @RequestParam(value = "stationId", required = false) Long stationId) {
        return CommonResult.success(stockControlService.getChart(startTime, endTime, stationId));
    }

    private void injectUserNames(List<StockControlRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        Set<Long> cardIds = new HashSet<>();
        for (var item : list) {
            if (StrUtil.isNotBlank(item.getCreator())) {
                Long id = safeParseLong(item.getCreator());
                if (id != null) userIds.add(id);
            }
            if (item.getCardId() != null) {
                cardIds.add(item.getCardId());
            }
        }
        // 翻译创建者名称
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (StrUtil.isNotBlank(item.getCreator())) {
                    AdminUserRespDTO user = userMap.get(safeParseLong(item.getCreator()));
                    if (user != null) item.setCreatorName(user.getNickname());
                }
            }
        }
        // 翻译卡种名称
        if (!cardIds.isEmpty()) {
            Map<Long, String> cardNameMap = new HashMap<>();
            for (Long cardId : cardIds) {
                CardConfigDO card = cardConfigService.get(cardId);
                if (card != null) cardNameMap.put(cardId, card.getName());
            }
            for (var item : list) {
                if (item.getCardId() != null) {
                    String name = cardNameMap.get(item.getCardId());
                    if (name != null) item.setCardName(name);
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
