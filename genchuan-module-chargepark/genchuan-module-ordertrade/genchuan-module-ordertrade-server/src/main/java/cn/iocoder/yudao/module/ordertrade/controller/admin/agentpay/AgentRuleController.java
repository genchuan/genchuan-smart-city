package cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.agentpay.vo.*;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.agentpay.AgentRuleDO;
import cn.iocoder.yudao.module.ordertrade.service.agentpay.AgentRuleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 代付管理 - 代付规则")
@RestController
@RequestMapping("/ordertrade/agent-rule")
@Validated
public class AgentRuleController {

    @Resource
    private AgentRuleService agentRuleService;

    @PostMapping("/create")
    @Operation(summary = "创建代付规则")
    public CommonResult<Long> createAgentRule(@Valid @RequestBody AgentRuleSaveReqVO createReqVO) {
        return success(agentRuleService.createAgentRule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新代付规则")
    public CommonResult<Boolean> updateAgentRule(@Valid @RequestBody AgentRuleSaveReqVO updateReqVO) {
        agentRuleService.updateAgentRule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除代付规则")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAgentRule(@RequestParam("id") Long id) {
        agentRuleService.deleteAgentRule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得代付规则详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AgentRuleRespVO> getAgentRule(@RequestParam("id") Long id) {
        AgentRuleDO obj = agentRuleService.getAgentRule(id);
        return success(BeanUtils.toBean(obj, AgentRuleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得代付规则分页列表")
    public CommonResult<PageResult<AgentRuleRespVO>> getAgentRulePage(@Valid AgentRulePageReqVO pageReqVO) {
        return success(agentRuleService.getAgentRulePage(pageReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出代付规则 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAgentRuleExcel(@Valid AgentRulePageReqVO pageReqVO,
                                     HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AgentRuleRespVO> list = agentRuleService.getAgentRulePage(pageReqVO).getList();
        ExcelUtils.write(response, "代付规则.xls", "数据", AgentRuleRespVO.class, list);
    }

    @PostMapping("/import")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "导入代付规则")
    public CommonResult<Boolean> importAgentRule(@RequestParam("file") MultipartFile file,
                                                  @RequestParam(value = "updateSupport", defaultValue = "false") Boolean updateSupport) throws Exception {
        List<AgentRuleImportExcelVO> importList = ExcelUtils.read(file, AgentRuleImportExcelVO.class);
        agentRuleService.importAgentRule(importList, updateSupport);
        return success(true);
    }

    @GetMapping("/import-template")
    @Operation(summary = "获得代付规则导入模板")
    public void importTemplate(HttpServletResponse response) throws IOException {
        List<AgentRuleImportExcelVO> list = List.of(
                AgentRuleImportExcelVO.builder()
                        .name("示例规则").merchantId(1L).agentType("merchant")
                        .singleLimit(new java.math.BigDecimal("1000")).dayLimit(new java.math.BigDecimal("10000"))
                        .scene("停车代付").status("pending").remark("示例数据")
                        .build()
        );
        ExcelUtils.write(response, "代付规则导入模板.xls", "代付规则列表", AgentRuleImportExcelVO.class, list);
    }

    @PutMapping("/enable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "生效代付规则")
    public CommonResult<Boolean> enableAgentRule(@RequestParam("id") Long id) {
        agentRuleService.enableAgentRule(id);
        return success(true);
    }

    @PutMapping("/disable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "禁用代付规则")
    public CommonResult<Boolean> disableAgentRule(@RequestParam("id") Long id) {
        agentRuleService.disableAgentRule(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得代付规则统计图表数据")
    public CommonResult<AgentRuleChartRespVO> getAgentRuleChart(@Valid AgentRuleChartReqVO chartReqVO) {
        return success(agentRuleService.getAgentRuleChart(chartReqVO));
    }
}
