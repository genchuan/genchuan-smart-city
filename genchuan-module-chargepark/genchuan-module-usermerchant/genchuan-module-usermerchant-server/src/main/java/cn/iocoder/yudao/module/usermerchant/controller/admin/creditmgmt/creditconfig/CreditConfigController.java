package cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

import cn.iocoder.yudao.module.usermerchant.controller.admin.creditmgmt.creditconfig.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.creditmgmt.creditconfig.CreditConfigDO;
import cn.iocoder.yudao.module.usermerchant.service.creditmgmt.creditconfig.CreditConfigService;

@Tag(name = "管理后台 - 信用配置")
@RestController
@RequestMapping("/usermerchant/credit-config")
@Validated
public class CreditConfigController {

    @Resource
    private CreditConfigService creditConfigService;

    @GetMapping("/page")
    @Operation(summary = "获得信用配置分页")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:query')")
    public CommonResult<PageResult<CreditConfigRespVO>> getCreditConfigPage(@Valid CreditConfigPageReqVO pageReqVO) {
        PageResult<CreditConfigDO> pageResult = creditConfigService.getCreditConfigPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CreditConfigRespVO.class));
    }

    @PostMapping("/create")
    @Operation(summary = "创建信用配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:create')")
    public CommonResult<Long> createCreditConfig(@Valid @RequestBody CreditConfigSaveReqVO createReqVO) {
        return success(creditConfigService.createCreditConfig(createReqVO));
    }

    @PutMapping("/save") //需要修改，逻辑有问题
    @Operation(summary = "保存信用配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:save')")
    public CommonResult<Boolean> saveConfig(@Valid @RequestBody CreditConfigSaveReqVO saveReqVO) {
        creditConfigService.saveConfig(saveReqVO);
        return success(true);
    }

    @PutMapping("/enable")
    @Operation(summary = "信用配置生效")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:enable')")
    public CommonResult<Boolean> enableCreditConfig(@Valid @RequestBody CreditConfigStatusReqVO reqVO) {
        creditConfigService.updateConfigStatus(reqVO.getIds(), "已生效");
        return success(true);
    }

    @PutMapping("/disable")
    @Operation(summary = "信用配置禁用")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:disable')")
    public CommonResult<Boolean> disableMemberConfig(@Valid @RequestBody CreditConfigStatusReqVO reqVO) {
        creditConfigService.updateConfigStatus(reqVO.getIds(), "未生效");
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得信用配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:query')")
    public CommonResult<CreditConfigRespVO> getCreditConfig(@RequestParam("id") Long id) {
        CreditConfigDO creditConfig = creditConfigService.getCreditConfig(id);
        return success(BeanUtils.toBean(creditConfig, CreditConfigRespVO.class));
    }

    @PutMapping("/update")
    @Operation(summary = "更新信用配置")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:update')")
    public CommonResult<Boolean> updateCreditConfig(@Valid @RequestBody CreditConfigSaveReqVO updateReqVO) {
        creditConfigService.updateCreditConfig(updateReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "信用配置统计")
    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:query')")
    public CommonResult<CreditConfigChartRespVO> getCreditConfigChart(@Valid CreditConfigChartReqVO chartReqVO) {
        return success(creditConfigService.getCreditConfigChart(chartReqVO));
    }

//    @DeleteMapping("/delete")
//    @Operation(summary = "删除信用配置")
//    @Parameter(name = "id", description = "编号", required = true)
//    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:delete')")
//    public CommonResult<Boolean> deleteCreditConfig(@RequestParam("id") Long id) {
//        creditConfigService.deleteCreditConfig(id);
//        return success(true);
//    }
//
//    @DeleteMapping("/delete-list")
//    @Parameter(name = "ids", description = "编号", required = true)
//    @Operation(summary = "批量删除信用配置")
//                @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:delete')")
//    public CommonResult<Boolean> deleteCreditConfigList(@RequestParam("ids") List<Long> ids) {
//        creditConfigService.deleteCreditConfigListByIds(ids);
//        return success(true);
//    }
//
//    @GetMapping("/export-excel")
//    @Operation(summary = "导出信用配置 Excel")
//    @PreAuthorize("@ss.hasPermission('usermerchant:credit-config:export')")
//    @ApiAccessLog(operateType = EXPORT)
//    public void exportCreditConfigExcel(@Valid CreditConfigPageReqVO pageReqVO,
//              HttpServletResponse response) throws IOException {
//        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
//        List<CreditConfigDO> list = creditConfigService.getCreditConfigPage(pageReqVO).getList();
//        // 导出 Excel
//        ExcelUtils.write(response, "信用配置.xls", "数据", CreditConfigRespVO.class,
//                        BeanUtils.toBean(list, CreditConfigRespVO.class));
//    }

}