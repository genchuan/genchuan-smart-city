package cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.MunicipalPublicUtilitiesPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.MunicipalPublicUtilitiesRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.municipalpublicutilities.vo.MunicipalPublicUtilitiesSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.municipalpublicutilities.MunicipalPublicUtilitiesDO;
import cn.iocoder.yudao.module.smartcity.service.municipalpublicutilities.MunicipalPublicUtilitiesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

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