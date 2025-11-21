package cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytrack.vo.InspectRectifyTrackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytrack.InspectRectifyTrackDO;
import cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytrack.InspectRectifyTrackService;

@Tag(name = "管理后台 - 巡查巡检整改进度跟踪视图")
@RestController
@RequestMapping("/datacenter/inspect-rectify-track")
@Validated
public class InspectRectifyTrackController {

    @Resource
    private InspectRectifyTrackService inspectRectifyTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查巡检整改进度跟踪视图")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:create')")
    public CommonResult<Long> createInspectRectifyTrack(@Valid @RequestBody InspectRectifyTrackSaveReqVO createReqVO) {
        return success(inspectRectifyTrackService.createInspectRectifyTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查巡检整改进度跟踪视图")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:update')")
    public CommonResult<Boolean> updateInspectRectifyTrack(@Valid @RequestBody InspectRectifyTrackSaveReqVO updateReqVO) {
        inspectRectifyTrackService.updateInspectRectifyTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查巡检整改进度跟踪视图")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:delete')")
    public CommonResult<Boolean> deleteInspectRectifyTrack(@RequestParam("id") Long id) {
        inspectRectifyTrackService.deleteInspectRectifyTrack(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查巡检整改进度跟踪视图")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:query')")
    public CommonResult<InspectRectifyTrackRespVO> getInspectRectifyTrack(@RequestParam("id") Long id) {
        InspectRectifyTrackDO inspectRectifyTrack = inspectRectifyTrackService.getInspectRectifyTrack(id);
        return success(BeanUtils.toBean(inspectRectifyTrack, InspectRectifyTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查巡检整改进度跟踪视图分页")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:query')")
    public CommonResult<PageResult<InspectRectifyTrackRespVO>> getInspectRectifyTrackPage(@Valid InspectRectifyTrackPageReqVO pageReqVO) {
        PageResult<InspectRectifyTrackDO> pageResult = inspectRectifyTrackService.getInspectRectifyTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectRectifyTrackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查巡检整改进度跟踪视图 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:inspect-rectify-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectRectifyTrackExcel(@Valid InspectRectifyTrackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectRectifyTrackDO> list = inspectRectifyTrackService.getInspectRectifyTrackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查巡检整改进度跟踪视图.xls", "数据", InspectRectifyTrackRespVO.class,
                        BeanUtils.toBean(list, InspectRectifyTrackRespVO.class));
    }

}