package cn.iocoder.yudao.module.envir.controller.admin.urbanvillage;

import cn.iocoder.yudao.module.envir.dal.dataobject.roadcleaning.RoadCleaningDetailDO;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDetailDO;
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

import cn.iocoder.yudao.module.envir.controller.admin.urbanvillage.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.urbanvillage.UrbanVillageDO;
import cn.iocoder.yudao.module.envir.service.urbanvillage.UrbanVillageService;

@Tag(name = "管理后台 - 城中村")
@RestController
@RequestMapping("/envir/urban-village")
@Validated
public class UrbanVillageController {

    @Resource
    private UrbanVillageService urbanVillageService;

    @PostMapping("/create")
    @Operation(summary = "创建城中村")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:create')")
    public CommonResult<Long> createUrbanVillage(@Valid @RequestBody UrbanVillageSaveReqVO createReqVO) {
        return success(urbanVillageService.createUrbanVillage(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新城中村")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:update')")
    public CommonResult<Boolean> updateUrbanVillage(@Valid @RequestBody UrbanVillageSaveReqVO updateReqVO) {
        urbanVillageService.updateUrbanVillage(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除城中村")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:urban-village:delete')")
    public CommonResult<Boolean> deleteUrbanVillage(@RequestParam("id") Long id) {
        urbanVillageService.deleteUrbanVillage(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得城中村")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:query')")
    public CommonResult<UrbanVillageRespVO> getUrbanVillage(@RequestParam("id") Long id) {
        UrbanVillageDO urbanVillage = urbanVillageService.getUrbanVillage(id);
        return success(BeanUtils.toBean(urbanVillage, UrbanVillageRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得城中村分页")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:query')")
    public CommonResult<PageResult<UrbanVillageRespVO>> getUrbanVillagePage(@Valid UrbanVillagePageReqVO pageReqVO) {
        PageResult<UrbanVillageDO> pageResult = urbanVillageService.getUrbanVillagePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, UrbanVillageRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出城中村 Excel")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportUrbanVillageExcel(@Valid UrbanVillagePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<UrbanVillageDO> list = urbanVillageService.getUrbanVillagePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "城中村.xls", "数据", UrbanVillageRespVO.class,
                        BeanUtils.toBean(list, UrbanVillageRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取城中村列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:urban-village:query')")
    public CommonResult<List<UrbanVillageDetailDO>> getUrbanVillageListDetail() {
        List<UrbanVillageDetailDO> list = urbanVillageService.getUrbanVillageListDetail();
        return success(list);
    }
}