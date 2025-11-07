package cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridtopiclayer.vo.GridTopicLayerSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridtopiclayer.GridTopicLayerDO;
import cn.iocoder.yudao.module.datacenter.service.grid.icons.gridtopiclayer.GridTopicLayerService;

@Tag(name = "管理后台 - 网格专题图层管理")
@RestController
@RequestMapping("/datacenter/grid-topic-layer")
@Validated
public class GridTopicLayerController {

    @Resource
    private GridTopicLayerService gridTopicLayerService;

    @PostMapping("/create")
    @Operation(summary = "创建网格专题图层管理")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:create')")
    public CommonResult<Long> createGridTopicLayer(@Valid @RequestBody GridTopicLayerSaveReqVO createReqVO) {
        return success(gridTopicLayerService.createGridTopicLayer(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新网格专题图层管理")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:update')")
    public CommonResult<Boolean> updateGridTopicLayer(@Valid @RequestBody GridTopicLayerSaveReqVO updateReqVO) {
        gridTopicLayerService.updateGridTopicLayer(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除网格专题图层管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:delete')")
    public CommonResult<Boolean> deleteGridTopicLayer(@RequestParam("id") Long id) {
        gridTopicLayerService.deleteGridTopicLayer(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得网格专题图层管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:query')")
    public CommonResult<GridTopicLayerRespVO> getGridTopicLayer(@RequestParam("id") Long id) {
        GridTopicLayerDO gridTopicLayer = gridTopicLayerService.getGridTopicLayer(id);
        return success(BeanUtils.toBean(gridTopicLayer, GridTopicLayerRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得网格专题图层管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:query')")
    public CommonResult<PageResult<GridTopicLayerRespVO>> getGridTopicLayerPage(@Valid GridTopicLayerPageReqVO pageReqVO) {
        PageResult<GridTopicLayerDO> pageResult = gridTopicLayerService.getGridTopicLayerPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, GridTopicLayerRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出网格专题图层管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:grid-topic-layer:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportGridTopicLayerExcel(@Valid GridTopicLayerPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<GridTopicLayerDO> list = gridTopicLayerService.getGridTopicLayerPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "网格专题图层管理.xls", "数据", GridTopicLayerRespVO.class,
                        BeanUtils.toBean(list, GridTopicLayerRespVO.class));
    }

}