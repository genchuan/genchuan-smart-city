package cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack;

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

import cn.iocoder.yudao.module.inspectop.controller.admin.inspecttrack.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.inspecttrack.InspectTrackDO;
import cn.iocoder.yudao.module.inspectop.service.inspecttrack.InspectTrackService;

@Tag(name = "管理后台 - 巡检轨迹")
@RestController
@RequestMapping("/inspectop/inspect-track")
@Validated
public class InspectTrackController {

    @Resource
    private InspectTrackService inspectTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建巡检轨迹")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:create')")
    public CommonResult<Long> createInspectTrack(@Valid @RequestBody InspectTrackSaveReqVO createReqVO) {
        return success(inspectTrackService.createInspectTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡检轨迹")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:update')")
    public CommonResult<Boolean> updateInspectTrack(@Valid @RequestBody InspectTrackSaveReqVO updateReqVO) {
        inspectTrackService.updateInspectTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡检轨迹")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:delete')")
    public CommonResult<Boolean> deleteInspectTrack(@RequestParam("id") Long id) {
        inspectTrackService.deleteInspectTrack(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除巡检轨迹")
                @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:delete')")
    public CommonResult<Boolean> deleteInspectTrackList(@RequestParam("ids") List<Long> ids) {
        inspectTrackService.deleteInspectTrackListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡检轨迹")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:query')")
    public CommonResult<InspectTrackRespVO> getInspectTrack(@RequestParam("id") Long id) {
        InspectTrackDO inspectTrack = inspectTrackService.getInspectTrack(id);
        return success(BeanUtils.toBean(inspectTrack, InspectTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡检轨迹分页")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:query')")
    public CommonResult<PageResult<InspectTrackRespVO>> getInspectTrackPage(@Valid InspectTrackPageReqVO pageReqVO) {
        PageResult<InspectTrackDO> pageResult = inspectTrackService.getInspectTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectTrackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡检轨迹 Excel")
    @PreAuthorize("@ss.hasPermission('inspectop:inspect-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectTrackExcel(@Valid InspectTrackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectTrackDO> list = inspectTrackService.getInspectTrackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡检轨迹.xls", "数据", InspectTrackRespVO.class,
                        BeanUtils.toBean(list, InspectTrackRespVO.class));
    }

}