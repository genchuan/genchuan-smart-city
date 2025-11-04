package cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunityRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunitySaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcommunity.GridCommunityDO;
import cn.iocoder.yudao.module.datacenter.service.grid.area.gridcommunity.GridCommunityService;

@Tag(name = "管理后台 - 社区（村）行政区划配置")
@RestController
@RequestMapping("/datacenter/grid-community")
@Validated
public class GridCommunityController {

    @Resource
    private GridCommunityService gridCommunityService;

    @PostMapping("/create")
    @Operation(summary = "创建社区（村）行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:create')")
    public CommonResult<Long> createGridCommunity(@Valid @RequestBody GridCommunitySaveReqVO createReqVO) {
        return success(gridCommunityService.createGridCommunity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新社区（村）行政区划配置")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:update')")
    public CommonResult<Boolean> updateGridCommunity(@Valid @RequestBody GridCommunitySaveReqVO updateReqVO) {
        gridCommunityService.updateGridCommunity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除社区（村）行政区划配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:delete')")
    public CommonResult<Boolean> deleteGridCommunity(@RequestParam("id") Long id) {
        gridCommunityService.deleteGridCommunity(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得社区（村）行政区划配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<GridCommunityRespVO> getGridCommunity(@RequestParam("id") Long id) {
        GridCommunityDO gridCommunity = gridCommunityService.getGridCommunity(id);
        return success(BeanUtils.toBean(gridCommunity, GridCommunityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得社区（村）行政区划配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<PageResult<GridCommunityRespVO>> getGridCommunityPage(@Valid GridCommunityPageReqVO pageReqVO) {
        PageResult<GridCommunityDO> pageResult = gridCommunityService.getGridCommunityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridCommunityRespVO.class));
    }

    @GetMapping("/list")
    @Operation(summary = "获得所有社区（村）行政区划配置列表")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<List<GridCommunityRespVO>> getGridCommunityList() {
        List<GridCommunityDO> list = gridCommunityService.getGridCommunityList();
        return success(BeanUtils.toBean(list, GridCommunityRespVO.class));
    }

    @GetMapping("/list-by-street")
    @Operation(summary = "根据街道ID获取社区列表")
    @Parameter(name = "streetId", description = "街道ID", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<List<GridCommunityRespVO>> getGridCommunityListByStreetId(@RequestParam("streetId") String streetId) {
        List<GridCommunityDO> list = gridCommunityService.getGridCommunityListByStreetId(streetId);
        return success(BeanUtils.toBean(list, GridCommunityRespVO.class));
    }

    @GetMapping("/list-by-streets")
    @Operation(summary = "根据街道ID列表批量获取社区列表")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<List<GridCommunityRespVO>> getGridCommunityListByStreetIds(@RequestParam("streetIds") List<String> streetIds) {
        List<GridCommunityDO> list = gridCommunityService.getGridCommunityListByStreetIds(streetIds);
        return success(BeanUtils.toBean(list, GridCommunityRespVO.class));
    }

    @GetMapping("/list-by-name")
    @Operation(summary = "根据社区名称模糊查询")
    @Parameter(name = "communityName", description = "社区名称", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:query')")
    public CommonResult<List<GridCommunityRespVO>> getGridCommunityListByName(@RequestParam("communityName") String communityName) {
        List<GridCommunityDO> list = gridCommunityService.getGridCommunityListByName(communityName);
        return success(BeanUtils.toBean(list, GridCommunityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出社区（村）行政区划配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-community:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridCommunityExcel(@Valid GridCommunityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridCommunityDO> list = gridCommunityService.getGridCommunityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "社区（村）行政区划配置.xls", "数据", GridCommunityRespVO.class,
                        BeanUtils.toBean(list, GridCommunityRespVO.class));
    }

}