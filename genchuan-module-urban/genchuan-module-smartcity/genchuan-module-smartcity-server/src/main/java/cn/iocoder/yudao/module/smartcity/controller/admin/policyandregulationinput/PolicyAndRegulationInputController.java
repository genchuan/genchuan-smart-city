package cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.policyandregulationinput.PolicyAndRegulationInputDO;
import cn.iocoder.yudao.module.smartcity.service.policyandregulationinput.PolicyAndRegulationInputService;

@Tag(name = "管理后台 - 政策法规录入")
@RestController
@RequestMapping("/smartcity/policy-and-regulation-input")
@Validated
public class PolicyAndRegulationInputController {

    @Resource
    private PolicyAndRegulationInputService policyAndRegulationInputService;

    @PostMapping("/create")
    @Operation(summary = "创建政策法规录入")
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:create')")
    public CommonResult<Long> createPolicyAndRegulationInput(@Valid @RequestBody PolicyAndRegulationInputSaveReqVO createReqVO) {
        return success(policyAndRegulationInputService.createPolicyAndRegulationInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策法规录入")
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:update')")
    public CommonResult<Boolean> updatePolicyAndRegulationInput(@Valid @RequestBody PolicyAndRegulationInputSaveReqVO updateReqVO) {
        policyAndRegulationInputService.updatePolicyAndRegulationInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策法规录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:delete')")
    public CommonResult<Boolean> deletePolicyAndRegulationInput(@RequestParam("id") Long id) {
        policyAndRegulationInputService.deletePolicyAndRegulationInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策法规录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:query')")
    public CommonResult<PolicyAndRegulationInputRespVO> getPolicyAndRegulationInput(@RequestParam("id") Long id) {
        PolicyAndRegulationInputDO policyAndRegulationInput = policyAndRegulationInputService.getPolicyAndRegulationInput(id);
        return success(BeanUtils.toBean(policyAndRegulationInput, PolicyAndRegulationInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策法规录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:query')")
    public CommonResult<PageResult<PolicyAndRegulationInputRespVO>> getPolicyAndRegulationInputPage(@Valid PolicyAndRegulationInputPageReqVO pageReqVO) {
        PageResult<PolicyAndRegulationInputDO> pageResult = policyAndRegulationInputService.getPolicyAndRegulationInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PolicyAndRegulationInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政策法规录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:policy-and-regulation-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPolicyAndRegulationInputExcel(@Valid PolicyAndRegulationInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PolicyAndRegulationInputDO> list = policyAndRegulationInputService.getPolicyAndRegulationInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政策法规录入.xls", "数据", PolicyAndRegulationInputRespVO.class,
                        BeanUtils.toBean(list, PolicyAndRegulationInputRespVO.class));
    }

}