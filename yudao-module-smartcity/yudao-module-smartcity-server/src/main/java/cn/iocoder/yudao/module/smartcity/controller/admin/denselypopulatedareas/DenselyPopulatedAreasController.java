package cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.denselypopulatedareas.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.denselypopulatedareas.DenselyPopulatedAreasDO;
import cn.iocoder.yudao.module.smartcity.service.denselypopulatedareas.DenselyPopulatedAreasService;

@Tag(name = "管理后台 - 人员密集场所安全")
@RestController
@RequestMapping("/smartcity/densely-populated-areas")
@Validated
public class DenselyPopulatedAreasController {

    @Resource
    private DenselyPopulatedAreasService denselyPopulatedAreasService;

    @PostMapping("/create")
    @Operation(summary = "创建人员密集场所安全")
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:create')")
    public CommonResult<Long> createDenselyPopulatedAreas(@Valid @RequestBody DenselyPopulatedAreasSaveReqVO createReqVO) {
        return success(denselyPopulatedAreasService.createDenselyPopulatedAreas(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新人员密集场所安全")
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:update')")
    public CommonResult<Boolean> updateDenselyPopulatedAreas(@Valid @RequestBody DenselyPopulatedAreasSaveReqVO updateReqVO) {
        denselyPopulatedAreasService.updateDenselyPopulatedAreas(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除人员密集场所安全")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:delete')")
    public CommonResult<Boolean> deleteDenselyPopulatedAreas(@RequestParam("id") Long id) {
        denselyPopulatedAreasService.deleteDenselyPopulatedAreas(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得人员密集场所安全")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:query')")
    public CommonResult<DenselyPopulatedAreasRespVO> getDenselyPopulatedAreas(@RequestParam("id") Long id) {
        DenselyPopulatedAreasDO denselyPopulatedAreas = denselyPopulatedAreasService.getDenselyPopulatedAreas(id);
        return success(BeanUtils.toBean(denselyPopulatedAreas, DenselyPopulatedAreasRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得人员密集场所安全分页")
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:query')")
    public CommonResult<PageResult<DenselyPopulatedAreasRespVO>> getDenselyPopulatedAreasPage(@Valid DenselyPopulatedAreasPageReqVO pageReqVO) {
        PageResult<DenselyPopulatedAreasDO> pageResult = denselyPopulatedAreasService.getDenselyPopulatedAreasPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DenselyPopulatedAreasRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出人员密集场所安全 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:densely-populated-areas:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDenselyPopulatedAreasExcel(@Valid DenselyPopulatedAreasPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DenselyPopulatedAreasDO> list = denselyPopulatedAreasService.getDenselyPopulatedAreasPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "人员密集场所安全.xls", "数据", DenselyPopulatedAreasRespVO.class,
                        BeanUtils.toBean(list, DenselyPopulatedAreasRespVO.class));
    }

}