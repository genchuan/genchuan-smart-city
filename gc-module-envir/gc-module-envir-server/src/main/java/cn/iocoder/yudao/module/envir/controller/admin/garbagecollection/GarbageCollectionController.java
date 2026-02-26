package cn.iocoder.yudao.module.envir.controller.admin.garbagecollection;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.*;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo.*;
import cn.iocoder.yudao.module.envir.service.garbagecollection.GarbageCollectionService;

@Tag(name = "管理后台 - 收运计划")
@RestController
@RequestMapping("/envir/garbage-collection")
@Validated
public class GarbageCollectionController {

    @Resource
    private GarbageCollectionService garbageCollectionService;

    @PostMapping("/create")
    @Operation(summary = "创建收运计划")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:create')")
    public CommonResult<Long> createGarbageCollection(@Valid @RequestBody GarbageCollectionSaveReqVO createReqVO) {
        return success(garbageCollectionService.createGarbageCollection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新收运计划")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:update')")
    public CommonResult<Boolean> updateGarbageCollection(@Valid @RequestBody GarbageCollectionSaveReqVO updateReqVO) {
        garbageCollectionService.updateGarbageCollection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除收运计划")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:delete')")
    public CommonResult<Boolean> deleteGarbageCollection(@RequestParam("id") Long id) {
        garbageCollectionService.deleteGarbageCollection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得收运计划")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:query')")
    public CommonResult<GarbageCollectionRespVO> getGarbageCollection(@RequestParam("id") Long id) {
        GarbageCollectionDO garbageCollection = garbageCollectionService.getGarbageCollection(id);
        return success(BeanUtils.toBean(garbageCollection, GarbageCollectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得收运计划分页")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:query')")
    public CommonResult<PageResult<GarbageCollectionRespVO>> getGarbageCollectionPage(@Valid GarbageCollectionPageReqVO pageReqVO) {
        PageResult<GarbageCollectionDO> pageResult = garbageCollectionService.getGarbageCollectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GarbageCollectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出收运计划 Excel")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGarbageCollectionExcel(@Valid GarbageCollectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GarbageCollectionDO> list = garbageCollectionService.getGarbageCollectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "收运计划.xls", "数据", GarbageCollectionRespVO.class,
                        BeanUtils.toBean(list, GarbageCollectionRespVO.class));
    }

    @GetMapping("/list-detail")
    @Operation(summary = "获取收运计划列表(详情)")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:query')")
    public CommonResult<List<GarbageCollectionDetailDO>> getGarbageCollectionListDetail() {
        List<GarbageCollectionDetailDO> list = garbageCollectionService.getGarbageCollectionListDetail();
        return success(list);
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得收运计划详情(分页)")
    @PreAuthorize("@ss.hasPermission('envir:garbage-collection:query')")
    public CommonResult<PageResult<GarbageCollectionDetailDO>> getGarbageCollectionDetailPage(
            @Valid GarbageCollectionPageReqVO pageReqVO) {
        PageResult<GarbageCollectionDetailDO> pageResult =
                garbageCollectionService.getGarbageCollectionDetailPage(pageReqVO);

        return success(pageResult);
    }
}