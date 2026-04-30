package cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.lxscommon.vo.BatchStatusUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.parkingspace.parkingspaceinfo.vo.ops.ImportResultVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRuleRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRuleSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.AddFeeRuleReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.feerule.FeeRuleService;
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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;


@Tag(name = "管理后台 - 收费规则")
@RestController
@RequestMapping("/stationresource/fee-rule")
@Validated
//@Hidden
public class FeeRuleController {

    @Resource
    private FeeRuleService feeRuleService;

    @GetMapping("/chart")
    @Operation(summary = "收费规则统计（柱状图+卡片）")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:query')")
    public CommonResult<FeeRuleChartRespVO> getFeeRuleChart() {
        FeeRuleChartRespVO chartData = feeRuleService.getFeeRuleChart();
        return CommonResult.success(chartData);
    }
    @PutMapping("/enable")
    @Operation(summary = "批量生效收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:update')")
    public CommonResult<Boolean> enableFeeRule(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        feeRuleService.enableFeeRule(reqVO.getIds());
        return CommonResult.success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "批量禁用收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:update')")
    public CommonResult<Boolean> disableFeeRule(@Valid @RequestBody BatchStatusUpdateReqVO reqVO) {
        feeRuleService.disableFeeRule(reqVO.getIds());
        return CommonResult.success(true);
    }
    @PutMapping("/update")
    @Operation(summary = "更新收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:update')")
    public CommonResult<Boolean> updateFeeRule2(@Valid @RequestBody FeeRuleUpdateReqVO updateReqVO) {
        feeRuleService.updateFeeRuleBiz(updateReqVO);
        return success(true);
    }
    @GetMapping("/import-template")
    @Operation(summary = "下载收费规则导入模板")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:import')")
    public void importFeeRuleTemplate(HttpServletResponse response) throws Exception {
        VrvExcelUtils.downloadImportTemplate(response, AddFeeRuleReqVO.class);
    }

    @PostMapping("/import")
    @Operation(summary = "导入收费规则", description = "上传Excel文件")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:import')")
    public CommonResult<FeeRuleImportResp> importFeeRule(
            @RequestPart("file") MultipartFile file,
            @RequestParam(value = "updateSupport", defaultValue = "false") boolean updateSupport) throws Exception {
        FeeRuleImportResp result = feeRuleService.importFeeRule(file, updateSupport);
        return success(result);
    }
    @PostMapping("/create")
    @Operation(summary = "新增收费规则")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:create')")
    public CommonResult<Boolean> addFeeRule(@Valid @RequestBody AddFeeRuleReqVO reqVO) {
        feeRuleService.addFeeRule(reqVO);
        return CommonResult.success(true);
    }
    @GetMapping("/page")
    @Operation(summary = "获得收费规则分页")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:query')")
    public CommonResult<PageResult<FeeRuleRespVO>> getFeeRulePage(@Valid FeeRulePageReqVO pageReqVO) {
        PageResult<FeeRuleDO> pageResult = feeRuleService.getFeeRulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FeeRuleRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得收费规则")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:query')")
    public CommonResult<FeeRuleRespVO> getFeeRule(@RequestParam("id") Long id) {
        FeeRuleDO feeRule = feeRuleService.getFeeRule(id);
        return success(BeanUtils.toBean(feeRule, FeeRuleRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出收费规则 Excel")
    @PreAuthorize("@ss.hasPermission('stationresource:fee-rule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFeeRuleExcel(@Valid FeeRulePageReqVO pageReqVO,
                                   HttpServletResponse response) throws IOException {
        // 0. 配置
        String inputFileName = "收费规则_";

        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FeeRuleDO> list = feeRuleService.getFeeRulePage(pageReqVO).getList();

        // 1、强制设置响应头，确保浏览器触发下载
        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setCharacterEncoding("utf-8");
        // 2、动态生成文件名，带上当前日期
        String dateStr = java.time.LocalDate.now().toString();
        String fileOriginName = inputFileName + dateStr + ".xls";
        String fileName = URLEncoder.encode(fileOriginName, StandardCharsets.UTF_8.toString())
                .replaceAll("\\+", "%20").replace("UTF-8","");
        response.setHeader("Content-Disposition", "attachment; filename*=" + fileName);

        // 3、调用 ExcelUtils 导出
        ExcelUtils.write(response, "收费规则.xls", "数据", FeeRuleRespVO.class,
                BeanUtils.toBean(list, FeeRuleRespVO.class));
    }
    //==================================================================================




}
