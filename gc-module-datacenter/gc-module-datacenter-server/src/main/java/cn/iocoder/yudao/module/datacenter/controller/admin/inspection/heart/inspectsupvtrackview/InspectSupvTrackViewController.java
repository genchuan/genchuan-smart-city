package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvtrackview.vo.InspectSupvTrackViewSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.heart.inspectsupvtrackview.InspectSupvTrackViewService;

@Tag(name = "管理后台 - 巡查巡检督办进度跟踪视图")
@RestController
@RequestMapping("/datacenter/inspect-supv-track-view")
@Validated
public class InspectSupvTrackViewController {

    @Resource
    private InspectSupvTrackViewService inspectSupvTrackViewService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检督办进度跟踪视图")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:create')")
    public CommonResult<Long> createInspectSupvTrackView(@Valid @RequestBody InspectSupvTrackViewSaveReqVO createReqVO) {
        return success(inspectSupvTrackViewService.createInspectSupvTrackView(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检督办进度跟踪视图")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:update')")
    public CommonResult<Boolean> updateInspectSupvTrackView(@Valid @RequestBody InspectSupvTrackViewSaveReqVO updateReqVO) {
        inspectSupvTrackViewService.updateInspectSupvTrackView(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检督办进度跟踪视图")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:delete')")
    public CommonResult<Boolean> deleteInspectSupvTrackView(@RequestParam("id") Long id) {
        inspectSupvTrackViewService.deleteInspectSupvTrackView(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检督办进度跟踪视图")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:query')")
    public CommonResult<InspectSupvTrackViewRespVO> getInspectSupvTrackView(@RequestParam("id") Long id) {
        InspectSupvTrackViewDO inspectSupvTrackView = inspectSupvTrackViewService.getInspectSupvTrackView(id);
        return success(BeanUtils.toBean(inspectSupvTrackView, InspectSupvTrackViewRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检督办进度跟踪视图分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:query')")
    public CommonResult<PageResult<InspectSupvTrackViewRespVO>> getInspectSupvTrackViewPage(@Valid InspectSupvTrackViewPageReqVO pageReqVO) {
        PageResult<InspectSupvTrackViewDO> pageResult = inspectSupvTrackViewService.getInspectSupvTrackViewPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectSupvTrackViewRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检督办进度跟踪视图 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-supv-track-view:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectSupvTrackViewExcel(@Valid InspectSupvTrackViewPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectSupvTrackViewDO> list = inspectSupvTrackViewService.getInspectSupvTrackViewPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检督办进度跟踪视图.xls", "数据", InspectSupvTrackViewRespVO.class,
                        BeanUtils.toBean(list, InspectSupvTrackViewRespVO.class));
    }

}