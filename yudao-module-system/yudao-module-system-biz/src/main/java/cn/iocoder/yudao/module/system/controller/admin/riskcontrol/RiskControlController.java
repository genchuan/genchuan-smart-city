package cn.iocoder.yudao.module.system.controller.admin.riskcontrol;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import javax.validation.constraints.*;
import javax.validation.*;
import javax.servlet.http.*;
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

import cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.riskcontrol.RiskControlDO;
import cn.iocoder.yudao.module.system.service.riskcontrol.RiskControlService;

@Tag(name = "管理后台 - 风险管控")
@RestController
@RequestMapping("/system/risk-control")
@Validated
public class RiskControlController {

    @Resource
    private RiskControlService riskControlService;

    @PostMapping("/create")
    @Operation(summary = "创建风险管控")
    @PreAuthorize("@ss.hasPermission('system:risk-control:create')")
    public CommonResult<Integer> createRiskControl(@Valid @RequestBody RiskControlSaveReqVO createReqVO) {
        return success(riskControlService.createRiskControl(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新风险管控")
    @PreAuthorize("@ss.hasPermission('system:risk-control:update')")
    public CommonResult<Boolean> updateRiskControl(@Valid @RequestBody RiskControlSaveReqVO updateReqVO) {
        riskControlService.updateRiskControl(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除风险管控")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('system:risk-control:delete')")
    public CommonResult<Boolean> deleteRiskControl(@RequestParam("id") Integer id) {
        riskControlService.deleteRiskControl(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得风险管控")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('system:risk-control:query')")
    public CommonResult<RiskControlRespVO> getRiskControl(@RequestParam("id") Integer id) {
        RiskControlDO riskControl = riskControlService.getRiskControl(id);
        return success(BeanUtils.toBean(riskControl, RiskControlRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得风险管控分页")
    @PreAuthorize("@ss.hasPermission('system:risk-control:query')")
    public CommonResult<PageResult<RiskControlRespVO>> getRiskControlPage(@Valid RiskControlPageReqVO pageReqVO) {
        PageResult<RiskControlDO> pageResult = riskControlService.getRiskControlPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RiskControlRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出风险管控 Excel")
    @PreAuthorize("@ss.hasPermission('system:risk-control:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRiskControlExcel(@Valid RiskControlPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RiskControlDO> list = riskControlService.getRiskControlPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "风险管控.xls", "数据", RiskControlRespVO.class,
                        BeanUtils.toBean(list, RiskControlRespVO.class));
    }

}