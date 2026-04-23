package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentOrderDO;
import cn.iocoder.yudao.module.ordertrade.service.agentpay.AgentOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 代付管理 - 代付订单")
@RestController
@RequestMapping("/ordertrade/agent-order")
@Validated
public class AgentOrderController {

    @Resource
    private AgentOrderService agentOrderService;

    @PostMapping("/create")
    @Operation(summary = "创建代付订单")
    public CommonResult<Long> createAgentOrder(@Valid @RequestBody AgentOrderSaveReqVO createReqVO) {
        return success(agentOrderService.createAgentOrder(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付订单")
    public CommonResult<Boolean> updateAgentOrder(@Valid @RequestBody AgentOrderSaveReqVO updateReqVO) {
        agentOrderService.updateAgentOrder(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付订单")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAgentOrder(@RequestParam("id") Long id) {
        agentOrderService.deleteAgentOrder(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付订单详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AgentOrderRespVO> getAgentOrder(@RequestParam("id") Long id) {
        AgentOrderDO obj = agentOrderService.getAgentOrder(id);
        return success(BeanUtils.toBean(obj, AgentOrderRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付订单分页列表")
    public CommonResult<PageResult<AgentOrderRespVO>> getAgentOrderPage(@Valid AgentOrderPageReqVO pageReqVO) {
        PageResult<AgentOrderDO> pageResult = agentOrderService.getAgentOrderPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AgentOrderRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出代付订单 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAgentOrderExcel(@Valid AgentOrderPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AgentOrderDO> list = agentOrderService.getAgentOrderPage(pageReqVO).getList();
        ExcelUtils.write(response, "代付订单.xls", "数据", AgentOrderRespVO.class,
                BeanUtils.toBean(list, AgentOrderRespVO.class));
    }

    @PostMapping("/batch-export")
    @ApiAccessLog(operateType = EXPORT)
    @Operation(summary = "批量导出代付订单 Excel")
    public void batchExportAgentOrderExcel(@RequestBody List<Long> ids,
                                            HttpServletResponse response) throws IOException {
        List<AgentOrderDO> list = agentOrderService.getAgentOrderByIds(ids);
        ExcelUtils.write(response, "代付订单.xls", "数据", AgentOrderRespVO.class,
                BeanUtils.toBean(list, AgentOrderRespVO.class));
    }

    @PostMapping("/pay")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "支付代付订单")
    public CommonResult<Boolean> payAgentOrder(@Valid @RequestBody IdReqVO reqVO) {
        agentOrderService.payAgentOrder(reqVO);
        return success(true);
    }

    @PostMapping("/invoice")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "开票")
    public CommonResult<Boolean> invoiceAgentOrder(@Valid @RequestBody IdReqVO reqVO) {
        agentOrderService.invoiceAgentOrder(reqVO);
        return success(true);
    }

    @PutMapping("/cancel")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "取消代付订单")
    public CommonResult<Boolean> cancelAgentOrder(@Valid @RequestBody IdReqVO reqVO) {
        agentOrderService.cancelAgentOrder(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得代付订单统计图表数据")
    public CommonResult<AgentOrderChartRespVO> getAgentOrderChart(@Valid AgentOrderChartReqVO chartReqVO) {
        return success(agentOrderService.getAgentOrderChart(chartReqVO));
    }
}
