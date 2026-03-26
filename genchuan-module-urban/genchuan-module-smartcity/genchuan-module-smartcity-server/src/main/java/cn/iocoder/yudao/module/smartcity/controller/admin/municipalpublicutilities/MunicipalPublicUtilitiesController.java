package cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities.MunicipalPublicUtilitiesDO;
import cn.iocoder.yudao.module.smartcity.service.municipalpublicutilities.MunicipalPublicUtilitiesService;

@Tag(name = "管理后台 - 市政公用")
@RestController
@RequestMapping("/smartcity/municipal-public-utilities")
@Validated
public class MunicipalPublicUtilitiesController {

    @Resource
    private MunicipalPublicUtilitiesService municipalPublicUtilitiesService;

    @PostMapping("/create")
    @Operation(summary = "创建市政公用")
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:create')")
    public CommonResult<Long> createMunicipalPublicUtilities(@Valid @RequestBody MunicipalPublicUtilitiesSaveReqVO createReqVO) {
        return success(municipalPublicUtilitiesService.createMunicipalPublicUtilities(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新市政公用")
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:update')")
    public CommonResult<Boolean> updateMunicipalPublicUtilities(@Valid @RequestBody MunicipalPublicUtilitiesSaveReqVO updateReqVO) {
        municipalPublicUtilitiesService.updateMunicipalPublicUtilities(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除市政公用")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:delete')")
    public CommonResult<Boolean> deleteMunicipalPublicUtilities(@RequestParam("id") Long id) {
        municipalPublicUtilitiesService.deleteMunicipalPublicUtilities(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得市政公用")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:query')")
    public CommonResult<MunicipalPublicUtilitiesRespVO> getMunicipalPublicUtilities(@RequestParam("id") Long id) {
        MunicipalPublicUtilitiesDO municipalPublicUtilities = municipalPublicUtilitiesService.getMunicipalPublicUtilities(id);
        return success(BeanUtils.toBean(municipalPublicUtilities, MunicipalPublicUtilitiesRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得市政公用分页")
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:query')")
    public CommonResult<PageResult<MunicipalPublicUtilitiesRespVO>> getMunicipalPublicUtilitiesPage(@Valid MunicipalPublicUtilitiesPageReqVO pageReqVO) {
        PageResult<MunicipalPublicUtilitiesDO> pageResult = municipalPublicUtilitiesService.getMunicipalPublicUtilitiesPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MunicipalPublicUtilitiesRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出市政公用 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:municipal-public-utilities:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMunicipalPublicUtilitiesExcel(@Valid MunicipalPublicUtilitiesPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MunicipalPublicUtilitiesDO> list = municipalPublicUtilitiesService.getMunicipalPublicUtilitiesPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "市政公用.xls", "数据", MunicipalPublicUtilitiesRespVO.class,
                        BeanUtils.toBean(list, MunicipalPublicUtilitiesRespVO.class));
    }

}