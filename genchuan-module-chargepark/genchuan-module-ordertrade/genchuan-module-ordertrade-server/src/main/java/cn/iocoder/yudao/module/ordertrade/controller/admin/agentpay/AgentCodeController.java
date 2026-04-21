package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentCodeDO;
import cn.iocoder.yudao.module.ordertrade.service.agentpay.AgentCodeService;
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

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.CREATE;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 代付管理 - 代付码")
@RestController
@RequestMapping("/ordertrade/agent-code")
@Validated
public class AgentCodeController {

    @Resource
    private AgentCodeService agentCodeService;

    @PostMapping("/create")
    @Operation(summary = "创建代付码")
    public CommonResult<Long> createAgentCode(@Valid @RequestBody AgentCodeSaveReqVO createReqVO) {
        return success(agentCodeService.createAgentCode(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付码")
    public CommonResult<Boolean> updateAgentCode(@Valid @RequestBody AgentCodeSaveReqVO updateReqVO) {
        agentCodeService.updateAgentCode(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付码")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAgentCode(@RequestParam("id") Long id) {
        agentCodeService.deleteAgentCode(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付码详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AgentCodeRespVO> getAgentCode(@RequestParam("id") Long id) {
        AgentCodeDO obj = agentCodeService.getAgentCode(id);
        return success(BeanUtils.toBean(obj, AgentCodeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付码分页列表")
    public CommonResult<PageResult<AgentCodeRespVO>> getAgentCodePage(@Valid AgentCodePageReqVO pageReqVO) {
        PageResult<AgentCodeDO> pageResult = agentCodeService.getAgentCodePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AgentCodeRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出代付码 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAgentCodeExcel(@Valid AgentCodePageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AgentCodeDO> list = agentCodeService.getAgentCodePage(pageReqVO).getList();
        ExcelUtils.write(response, "代付码.xls", "数据", AgentCodeRespVO.class,
                BeanUtils.toBean(list, AgentCodeRespVO.class));
    }

    @PostMapping("/generate")
    @ApiAccessLog(operateType = CREATE)
    @Operation(summary = "批量生成代付码")
    public CommonResult<Boolean> generateAgentCode(@Valid @RequestBody AgentCodeGenerateReqVO reqVO) {
        agentCodeService.generateAgentCode(reqVO);
        return success(true);
    }

    @PutMapping("/refresh")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "刷新代付码")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> refreshAgentCode(@RequestParam("id") Long id) {
        agentCodeService.refreshAgentCode(id);
        return success(true);
    }

    @PutMapping("/regenerate")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "重新生成代付码")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> regenerateAgentCode(@RequestParam("id") Long id) {
        agentCodeService.regenerateAgentCode(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得代付码统计图表数据")
    public CommonResult<AgentCodeChartRespVO> getAgentCodeChart(@Valid AgentCodeChartReqVO chartReqVO) {
        return success(agentCodeService.getAgentCodeChart(chartReqVO));
    }
}
