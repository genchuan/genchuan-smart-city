package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.traceability.risksourcearch.vo.RiskSourceArchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.traceability.risksourcearch.RiskSourceArchDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.traceability.risksourcearch.RiskSourceArchService;

@Tag(name = "管理后台 - 风险源档案")
@RestController
@RequestMapping("/datacenter/risk-source-arch")
@Validated
public class RiskSourceArchController {

    @Resource
    private RiskSourceArchService riskSourceArchService;

    @PostMapping("/create")
    @Operation(summary = "创建风险源档案")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:create')")
    public CommonResult<Long> createRiskSourceArch(@Valid @RequestBody RiskSourceArchSaveReqVO createReqVO) {
        return success(riskSourceArchService.createRiskSourceArch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新风险源档案")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:update')")
    public CommonResult<Boolean> updateRiskSourceArch(@Valid @RequestBody RiskSourceArchSaveReqVO updateReqVO) {
        riskSourceArchService.updateRiskSourceArch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除风险源档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:delete')")
    public CommonResult<Boolean> deleteRiskSourceArch(@RequestParam("id") Long id) {
        riskSourceArchService.deleteRiskSourceArch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得风险源档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:query')")
    public CommonResult<RiskSourceArchRespVO> getRiskSourceArch(@RequestParam("id") Long id) {
        RiskSourceArchDO riskSourceArch = riskSourceArchService.getRiskSourceArch(id);
        return success(BeanUtils.toBean(riskSourceArch, RiskSourceArchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得风险源档案分页")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:query')")
    public CommonResult<PageResult<RiskSourceArchRespVO>> getRiskSourceArchPage(@Valid RiskSourceArchPageReqVO pageReqVO) {
        PageResult<RiskSourceArchDO> pageResult = riskSourceArchService.getRiskSourceArchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiskSourceArchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出风险源档案 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:risk-source-arch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiskSourceArchExcel(@Valid RiskSourceArchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiskSourceArchDO> list = riskSourceArchService.getRiskSourceArchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "风险源档案.xls", "数据", RiskSourceArchRespVO.class,
                        BeanUtils.toBean(list, RiskSourceArchRespVO.class));
    }

}