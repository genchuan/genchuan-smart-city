package cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.decisionoptsugg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionoptsugg.DecisionOptSuggDO;
import cn.iocoder.yudao.module.datacenter.service.decisionoptsugg.DecisionOptSuggService;

@Tag(name = "管理后台 - 决策优化建议")
@RestController
@RequestMapping("/datacenter/decision-opt-sugg")
@Validated
public class DecisionOptSuggController {

    @Resource
    private DecisionOptSuggService decisionOptSuggService;

    @PostMapping("/create")
    @Operation(summary = "创建决策优化建议")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:create')")
    public CommonResult<Long> createDecisionOptSugg(@Valid @RequestBody DecisionOptSuggSaveReqVO createReqVO) {
        return success(decisionOptSuggService.createDecisionOptSugg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新决策优化建议")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:update')")
    public CommonResult<Boolean> updateDecisionOptSugg(@Valid @RequestBody DecisionOptSuggSaveReqVO updateReqVO) {
        decisionOptSuggService.updateDecisionOptSugg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除决策优化建议")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:delete')")
    public CommonResult<Boolean> deleteDecisionOptSugg(@RequestParam("id") Long id) {
        decisionOptSuggService.deleteDecisionOptSugg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得决策优化建议")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:query')")
    public CommonResult<DecisionOptSuggRespVO> getDecisionOptSugg(@RequestParam("id") Long id) {
        DecisionOptSuggDO decisionOptSugg = decisionOptSuggService.getDecisionOptSugg(id);
        return success(BeanUtils.toBean(decisionOptSugg, DecisionOptSuggRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得决策优化建议分页")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:query')")
    public CommonResult<PageResult<DecisionOptSuggRespVO>> getDecisionOptSuggPage(@Valid DecisionOptSuggPageReqVO pageReqVO) {
        PageResult<DecisionOptSuggDO> pageResult = decisionOptSuggService.getDecisionOptSuggPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DecisionOptSuggRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出决策优化建议 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-opt-sugg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDecisionOptSuggExcel(@Valid DecisionOptSuggPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DecisionOptSuggDO> list = decisionOptSuggService.getDecisionOptSuggPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "决策优化建议.xls", "数据", DecisionOptSuggRespVO.class,
                        BeanUtils.toBean(list, DecisionOptSuggRespVO.class));
    }

}