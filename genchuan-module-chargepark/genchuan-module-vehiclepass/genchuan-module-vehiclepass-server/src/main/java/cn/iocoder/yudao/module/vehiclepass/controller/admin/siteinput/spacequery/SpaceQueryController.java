package cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery;

import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQuerySaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.siteinput.spacequery.SpaceQueryDO;
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


import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.siteinput.spacequery.vo.SpaceQueryChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.service.siteinput.spacequery.SpaceQueryService;

@Tag(name = "管理后台 - 泊位查询")
@RestController
@RequestMapping("/vehiclepass/space-query")
@Validated
public class SpaceQueryController {

    @Resource
    private SpaceQueryService queryService;

    @PostMapping("/create")
    @Operation(summary = "创建泊位查询")
    @PreAuthorize("@ss.hasPermission('space:query:create')")
    public CommonResult<Long> createQuery(@Valid @RequestBody SpaceQuerySaveReqVO createReqVO) {
        return success(queryService.createQuery(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新泊位查询")
    @PreAuthorize("@ss.hasPermission('space:query:update')")
    public CommonResult<Boolean> updateQuery(@Valid @RequestBody SpaceQuerySaveReqVO updateReqVO) {
        queryService.updateQuery(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除泊位查询")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('space:query:delete')")
    public CommonResult<Boolean> deleteQuery(@RequestParam("id") Long id) {
        queryService.deleteQuery(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除泊位查询")
    @PreAuthorize("@ss.hasPermission('space:query:delete')")
    public CommonResult<Boolean> deleteQueryList(@RequestParam("ids") List<Long> ids) {
        queryService.deleteQueryListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得泊位查询")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('space:query:query')")
    public CommonResult<SpaceQueryRespVO> getQuery(@RequestParam("id") Long id) {
        SpaceQueryDO query = queryService.getQuery(id);
        return success(BeanUtils.toBean(query, SpaceQueryRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得泊位查询分页")
    @PreAuthorize("@ss.hasPermission('vehiclepass:space-query:query')")
    public CommonResult<PageResult<SpaceQueryRespVO>> getQueryPage(@Valid SpaceQueryPageReqVO pageReqVO) {
        return success(queryService.getQueryPageWithJoin(pageReqVO));
    }

    @GetMapping("/location")
    @Operation(summary = "获取泊位查询定位")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('vehiclepass:space-query:location')")
    public CommonResult<SpaceQueryLocationRespVO> getLocation(@RequestParam("id") Long id) {
        return success(queryService.getLocation(id));
    }

    @GetMapping("/chart")
    @Operation(summary = "获取泊位查询统计")
    @PreAuthorize("@ss.hasPermission('vehiclepass:space-query:chart')")
    public CommonResult<SpaceQueryChartRespVO> getChart(@Valid SpaceQueryChartReqVO chartReqVO) {
        return success(queryService.getChart(chartReqVO));
    }

    @GetMapping("/export")
    @Operation(summary = "导出泊位查询 Excel")
    @PreAuthorize("@ss.hasPermission('space:query:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportQueryExcel(SpaceQueryPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageNo(1);
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SpaceQueryDO> list = queryService.getQueryPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "泊位查询.xls", "数据", SpaceQueryRespVO.class,
                BeanUtils.toBean(list, SpaceQueryRespVO.class));
    }

}