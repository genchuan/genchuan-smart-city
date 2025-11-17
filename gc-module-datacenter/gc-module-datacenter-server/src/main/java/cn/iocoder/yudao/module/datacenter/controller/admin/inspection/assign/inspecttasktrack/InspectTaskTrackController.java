package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.assign.inspecttasktrack.vo.InspectTaskTrackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.assign.inspecttasktrack.InspectTaskTrackDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.assign.inspecttasktrack.InspectTaskTrackService;

@Tag(name = "管理后台 - 巡查巡检任务跟踪")
@RestController
@RequestMapping("/datacenter/inspect-task-track")
@Validated
public class InspectTaskTrackController {

    @Resource
    private InspectTaskTrackService inspectTaskTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检任务跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:create')")
    public CommonResult<Long> createInspectTaskTrack(@Valid @RequestBody InspectTaskTrackSaveReqVO createReqVO) {
        return success(inspectTaskTrackService.createInspectTaskTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检任务跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:update')")
    public CommonResult<Boolean> updateInspectTaskTrack(@Valid @RequestBody InspectTaskTrackSaveReqVO updateReqVO) {
        inspectTaskTrackService.updateInspectTaskTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检任务跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:delete')")
    public CommonResult<Boolean> deleteInspectTaskTrack(@RequestParam("id") Long id) {
        inspectTaskTrackService.deleteInspectTaskTrack(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检任务跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:query')")
    public CommonResult<InspectTaskTrackRespVO> getInspectTaskTrack(@RequestParam("id") Long id) {
        InspectTaskTrackDO inspectTaskTrack = inspectTaskTrackService.getInspectTaskTrack(id);
        return success(BeanUtils.toBean(inspectTaskTrack, InspectTaskTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检任务跟踪分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:query')")
    public CommonResult<PageResult<InspectTaskTrackRespVO>> getInspectTaskTrackPage(@Valid InspectTaskTrackPageReqVO pageReqVO) {
        PageResult<InspectTaskTrackDO> pageResult = inspectTaskTrackService.getInspectTaskTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectTaskTrackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检任务跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-task-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTaskTrackExcel(@Valid InspectTaskTrackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectTaskTrackDO> list = inspectTaskTrackService.getInspectTaskTrackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检任务跟踪.xls", "数据", InspectTaskTrackRespVO.class,
                        BeanUtils.toBean(list, InspectTaskTrackRespVO.class));
    }

}