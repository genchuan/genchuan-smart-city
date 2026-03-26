package cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.watersupplyagreement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersupplyagreement.WaterSupplyAgreementDO;
import cn.iocoder.yudao.module.waterdetection.service.watersupplyagreement.WaterSupplyAgreementService;

@Tag(name = "管理后台 - 供水协议管理")
@RestController
@RequestMapping("/waterdetection/water-supply-agreement")
@Validated
public class WaterSupplyAgreementController {

    @Resource
    private WaterSupplyAgreementService waterSupplyAgreementService;

    @PostMapping("/create")
    @Operation(summary = "创建供水协议管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:create')")
    public CommonResult<Long> createWaterSupplyAgreement(@Valid @RequestBody WaterSupplyAgreementSaveReqVO createReqVO) {
        return success(waterSupplyAgreementService.createWaterSupplyAgreement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新供水协议管理")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:update')")
    public CommonResult<Boolean> updateWaterSupplyAgreement(@Valid @RequestBody WaterSupplyAgreementSaveReqVO updateReqVO) {
        waterSupplyAgreementService.updateWaterSupplyAgreement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除供水协议管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:delete')")
    public CommonResult<Boolean> deleteWaterSupplyAgreement(@RequestParam("id") Long id) {
        waterSupplyAgreementService.deleteWaterSupplyAgreement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得供水协议管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:query')")
    public CommonResult<WaterSupplyAgreementRespVO> getWaterSupplyAgreement(@RequestParam("id") Long id) {
        WaterSupplyAgreementDO waterSupplyAgreement = waterSupplyAgreementService.getWaterSupplyAgreement(id);
        return success(BeanUtils.toBean(waterSupplyAgreement, WaterSupplyAgreementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得供水协议管理分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:query')")
    public CommonResult<PageResult<WaterSupplyAgreementRespVO>> getWaterSupplyAgreementPage(@Valid WaterSupplyAgreementPageReqVO pageReqVO) {
        PageResult<WaterSupplyAgreementDO> pageResult = waterSupplyAgreementService.getWaterSupplyAgreementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, WaterSupplyAgreementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出供水协议管理 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:water-supply-agreement:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportWaterSupplyAgreementExcel(@Valid WaterSupplyAgreementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<WaterSupplyAgreementDO> list = waterSupplyAgreementService.getWaterSupplyAgreementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "供水协议管理.xls", "数据", WaterSupplyAgreementRespVO.class,
                        BeanUtils.toBean(list, WaterSupplyAgreementRespVO.class));
    }

}