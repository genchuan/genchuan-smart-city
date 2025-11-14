package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.crossregiondispatch.CrossRegionDispatchDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.crossregiondispatch.CrossRegionDispatchService;

@Tag(name = "管理后台 - 跨域调度协调")
@RestController
@RequestMapping("/datacenter/cross-region-dispatch")
@Validated
public class CrossRegionDispatchController {

    @Resource
    private CrossRegionDispatchService crossRegionDispatchService;

    @PostMapping("/create")
    @Operation(summary = "创建跨域调度协调")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:create')")
    public CommonResult<Long> createCrossRegionDispatch(@Valid @RequestBody CrossRegionDispatchSaveReqVO createReqVO) {
        return success(crossRegionDispatchService.createCrossRegionDispatch(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新跨域调度协调")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:update')")
    public CommonResult<Boolean> updateCrossRegionDispatch(@Valid @RequestBody CrossRegionDispatchSaveReqVO updateReqVO) {
        crossRegionDispatchService.updateCrossRegionDispatch(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除跨域调度协调")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:delete')")
    public CommonResult<Boolean> deleteCrossRegionDispatch(@RequestParam("id") Long id) {
        crossRegionDispatchService.deleteCrossRegionDispatch(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得跨域调度协调")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:query')")
    public CommonResult<CrossRegionDispatchRespVO> getCrossRegionDispatch(@RequestParam("id") Long id) {
        CrossRegionDispatchDO crossRegionDispatch = crossRegionDispatchService.getCrossRegionDispatch(id);
        return success(BeanUtils.toBean(crossRegionDispatch, CrossRegionDispatchRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得跨域调度协调分页")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:query')")
    public CommonResult<PageResult<CrossRegionDispatchRespVO>> getCrossRegionDispatchPage(@Valid CrossRegionDispatchPageReqVO pageReqVO) {
        PageResult<CrossRegionDispatchDO> pageResult = crossRegionDispatchService.getCrossRegionDispatchPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CrossRegionDispatchRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出跨域调度协调 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:cross-region-dispatch:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCrossRegionDispatchExcel(@Valid CrossRegionDispatchPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CrossRegionDispatchDO> list = crossRegionDispatchService.getCrossRegionDispatchPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "跨域调度协调.xls", "数据", CrossRegionDispatchRespVO.class,
                        BeanUtils.toBean(list, CrossRegionDispatchRespVO.class));
    }

}