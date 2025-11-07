package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.EvtDisposalTrackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtdisposaltrack.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtdisposaltrack.EvtDisposalTrackDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtdisposaltrack.EvtDisposalTrackService;

@Tag(name = "管理后台 - 事件处置跟踪")
@RestController
@RequestMapping("/datacenter/evt-disposal-track")
@Validated
public class EvtDisposalTrackController {

    @Resource
    private EvtDisposalTrackService evtDisposalTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建事件处置跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:create')")
    public CommonResult<Long> createEvtDisposalTrack(@Valid @RequestBody EvtDisposalTrackSaveReqVO createReqVO) {
        return success(evtDisposalTrackService.createEvtDisposalTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件处置跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:update')")
    public CommonResult<Boolean> updateEvtDisposalTrack(@Valid @RequestBody EvtDisposalTrackSaveReqVO updateReqVO) {
        evtDisposalTrackService.updateEvtDisposalTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件处置跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:delete')")
    public CommonResult<Boolean> deleteEvtDisposalTrack(@RequestParam("id") Long id) {
        evtDisposalTrackService.deleteEvtDisposalTrack(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件处置跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:query')")
    public CommonResult<EvtDisposalTrackRespVO> getEvtDisposalTrack(@RequestParam("id") Long id) {
        EvtDisposalTrackDO evtDisposalTrack = evtDisposalTrackService.getEvtDisposalTrack(id);
        return success(BeanUtils.toBean(evtDisposalTrack, EvtDisposalTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件处置跟踪分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:query')")
    public CommonResult<PageResult<EvtDisposalTrackRespVO>> getEvtDisposalTrackPage(@Valid EvtDisposalTrackPageReqVO pageReqVO) {
        PageResult<EvtDisposalTrackDO> pageResult = evtDisposalTrackService.getEvtDisposalTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtDisposalTrackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件处置跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-disposal-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtDisposalTrackExcel(@Valid EvtDisposalTrackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtDisposalTrackDO> list = evtDisposalTrackService.getEvtDisposalTrackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件处置跟踪.xls", "数据", EvtDisposalTrackRespVO.class,
                        BeanUtils.toBean(list, EvtDisposalTrackRespVO.class));
    }

}