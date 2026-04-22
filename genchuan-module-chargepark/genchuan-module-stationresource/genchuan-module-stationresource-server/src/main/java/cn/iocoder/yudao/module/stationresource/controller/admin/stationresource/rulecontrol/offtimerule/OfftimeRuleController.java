package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.*;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.offtimerule.OfftimeRuleDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.offtimerule.OfftimeRuleService;
import cn.iocoder.yudao.module.stationresource.vrv.utils.common.excel.VrvExcelUtils;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 错时规则")
@RestController
@RequestMapping("/stationresource/offtime-rule")
@Validated
//@Hidden
public class OfftimeRuleController {

    @Resource
    private OfftimeRuleService offtimeRuleService;

    @GetMapping("/chart")
    @Operation(summary = "错时规则统计（折线图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:query')")
    public CommonResult<OfftimeRuleChartRespVO> getOfftimeRuleChart() {
        OfftimeRuleChartRespVO chartData = offtimeRuleService.getOfftimeRuleChart();
        return CommonResult.success(chartData);
    }

    @PutMapping("/enable")
    @Operation(summary = "批量生效错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:update')")
    public CommonResult<Boolean> enableOfftimeRule(@RequestBody List<Long> ids) {
        offtimeRuleService.enableOfftimeRule(ids);
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:update')")
    public CommonResult<Boolean> disableOfftimeRule(@RequestBody List<Long> ids) {
        offtimeRuleService.disableOfftimeRule(ids);
        return CommonResult.success(true);
    }

    @PostMapping("/create")
    @Operation(summary = "新增错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:create')")
    public CommonResult<Boolean> createOfftimeRule(@Valid @RequestBody OfftimeRuleCreateReqVO createReqVO) {
        offtimeRuleService.createOfftimeRule(createReqVO);
        return success(true);
    }

    @PutMapping("/update")
    @Operation(summary = "更新错时规则")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:update')")
    public CommonResult<Boolean> updateOfftimeRule(@Valid @RequestBody OfftimeRuleUpdateReqVO updateReqVO) {
        offtimeRuleService.updateOfftimeRule(updateReqVO);
        return success(true);
    }

    @GetMapping("/import-template")
    @Operation(summary = "下载错时规则导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:import')")
    public void importOfftimeRuleTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, OfftimeRuleCreateReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入错时规则", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:import')")
    public CommonResult<OfftimeRuleImportResp> importOfftimeRule(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        OfftimeRuleImportResp result = offtimeRuleService.importOfftimeRule(file, updateSupport);
        return success(result);
    }

    @GetMapping("/page")
    @Operation(summary = "获得错时规则分页")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:query')")
    public CommonResult<PageResult<OfftimeRuleRespVO>> getOfftimeRulePage(@Valid OfftimeRulePageReqVO pageReqVO) {
        PageResult<OfftimeRuleDO> pageResult = offtimeRuleService.getOfftimeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, OfftimeRuleRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得错时规则详情")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:query')")
    public CommonResult<OfftimeRuleRespVO> getOfftimeRule(@RequestParam("id") Long id) {
        OfftimeRuleDO rule = offtimeRuleService.getOfftimeRule(id);
        return success(BeanUtils.toBean(rule, OfftimeRuleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出错时规则 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:offtime-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportOfftimeRuleExcel(@Valid OfftimeRulePageReqVO pageReqVO,
                                       HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<OfftimeRuleDO> list = offtimeRuleService.getOfftimeRulePage(pageReqVO).getList();
        ExcelUtils.write(response, "错时规则.xls", "数据", OfftimeRuleRespVO.class,
                BeanUtils.toBean(list, OfftimeRuleRespVO.class));
    }

}
