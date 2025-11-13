package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.riskpropagatechain.vo.RiskPropagateChainSaveReqVO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.riskpropagatechain.RiskPropagateChainDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.traceability.riskpropagatechain.RiskPropagateChainService;

@Tag(name = "管理后台 - 风险传播链")
@RestController
@RequestMapping("/datacenter/risk-propagate-chain")
@Validated
public class RiskPropagateChainController {

    @Resource
    private RiskPropagateChainService riskPropagateChainService;

    @PostMapping("/create")
    @Operation(summary = "创建风险传播链")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:create')")
    public CommonResult<Long> createRiskPropagateChain(@Valid @RequestBody RiskPropagateChainSaveReqVO createReqVO) {
        return success(riskPropagateChainService.createRiskPropagateChain(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新风险传播链")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:update')")
    public CommonResult<Boolean> updateRiskPropagateChain(@Valid @RequestBody RiskPropagateChainSaveReqVO updateReqVO) {
        riskPropagateChainService.updateRiskPropagateChain(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除风险传播链")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:delete')")
    public CommonResult<Boolean> deleteRiskPropagateChain(@RequestParam("id") Long id) {
        riskPropagateChainService.deleteRiskPropagateChain(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得风险传播链")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:query')")
    public CommonResult<RiskPropagateChainRespVO> getRiskPropagateChain(@RequestParam("id") Long id) {
        RiskPropagateChainDO riskPropagateChain = riskPropagateChainService.getRiskPropagateChain(id);
        return success(BeanUtils.toBean(riskPropagateChain, RiskPropagateChainRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得风险传播链分页")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:query')")
    public CommonResult<PageResult<RiskPropagateChainRespVO>> getRiskPropagateChainPage(@Valid RiskPropagateChainPageReqVO pageReqVO) {
        PageResult<RiskPropagateChainDO> pageResult = riskPropagateChainService.getRiskPropagateChainPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiskPropagateChainRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出风险传播链 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-propagate-chain:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiskPropagateChainExcel(@Valid RiskPropagateChainPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiskPropagateChainDO> list = riskPropagateChainService.getRiskPropagateChainPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "风险传播链.xls", "数据", RiskPropagateChainRespVO.class,
                        BeanUtils.toBean(list, RiskPropagateChainRespVO.class));
    }

}