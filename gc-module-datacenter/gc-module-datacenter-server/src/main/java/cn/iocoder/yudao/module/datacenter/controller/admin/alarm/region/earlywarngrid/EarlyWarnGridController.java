package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarngrid.vo.EarlyWarnGridSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarngrid.EarlyWarnGridDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarngrid.EarlyWarnGridService;

@Tag(name = "管理后台 - 按网格分域预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-grid")
@Validated
public class EarlyWarnGridController {

    @Resource
    private EarlyWarnGridService earlyWarnGridService;

    @PostMapping("/create")
    @Operation(summary = "创建按网格分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:create')")
    public CommonResult<Long> createEarlyWarnGrid(@Valid @RequestBody EarlyWarnGridSaveReqVO createReqVO) {
        return success(earlyWarnGridService.createEarlyWarnGrid(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按网格分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:update')")
    public CommonResult<Boolean> updateEarlyWarnGrid(@Valid @RequestBody EarlyWarnGridSaveReqVO updateReqVO) {
        earlyWarnGridService.updateEarlyWarnGrid(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按网格分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:delete')")
    public CommonResult<Boolean> deleteEarlyWarnGrid(@RequestParam("id") Long id) {
        earlyWarnGridService.deleteEarlyWarnGrid(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按网格分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:query')")
    public CommonResult<EarlyWarnGridRespVO> getEarlyWarnGrid(@RequestParam("id") Long id) {
        EarlyWarnGridDO earlyWarnGrid = earlyWarnGridService.getEarlyWarnGrid(id);
        return success(BeanUtils.toBean(earlyWarnGrid, EarlyWarnGridRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按网格分域预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:query')")
    public CommonResult<PageResult<EarlyWarnGridRespVO>> getEarlyWarnGridPage(@Valid EarlyWarnGridPageReqVO pageReqVO) {
        PageResult<EarlyWarnGridDO> pageResult = earlyWarnGridService.getEarlyWarnGridPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnGridRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按网格分域预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-grid:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnGridExcel(@Valid EarlyWarnGridPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnGridDO> list = earlyWarnGridService.getEarlyWarnGridPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按网格分域预警告警统计.xls", "数据", EarlyWarnGridRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnGridRespVO.class));
    }

}