package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocateRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.EvtClassifyAllocateSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtclassifyallocate.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtclassifyallocate.EvtClassifyAllocateDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtclassifyallocate.EvtClassifyAllocateService;

@Tag(name = "管理后台 - 事件分级分拨")
@RestController
@RequestMapping("/datacenter/evt-classify-allocate")
@Validated
public class EvtClassifyAllocateController {

    @Resource
    private EvtClassifyAllocateService evtClassifyAllocateService;

    @PostMapping("/create")
    @Operation(summary = "创建事件分级分拨")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:create')")
    public CommonResult<Long> createEvtClassifyAllocate(@Valid @RequestBody EvtClassifyAllocateSaveReqVO createReqVO) {
        return success(evtClassifyAllocateService.createEvtClassifyAllocate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件分级分拨")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:update')")
    public CommonResult<Boolean> updateEvtClassifyAllocate(@Valid @RequestBody EvtClassifyAllocateSaveReqVO updateReqVO) {
        evtClassifyAllocateService.updateEvtClassifyAllocate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件分级分拨")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:delete')")
    public CommonResult<Boolean> deleteEvtClassifyAllocate(@RequestParam("id") Long id) {
        evtClassifyAllocateService.deleteEvtClassifyAllocate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件分级分拨")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:query')")
    public CommonResult<EvtClassifyAllocateRespVO> getEvtClassifyAllocate(@RequestParam("id") Long id) {
        EvtClassifyAllocateDO evtClassifyAllocate = evtClassifyAllocateService.getEvtClassifyAllocate(id);
        return success(BeanUtils.toBean(evtClassifyAllocate, EvtClassifyAllocateRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件分级分拨分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:query')")
    public CommonResult<PageResult<EvtClassifyAllocateRespVO>> getEvtClassifyAllocatePage(@Valid EvtClassifyAllocatePageReqVO pageReqVO) {
        PageResult<EvtClassifyAllocateDO> pageResult = evtClassifyAllocateService.getEvtClassifyAllocatePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtClassifyAllocateRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件分级分拨 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-classify-allocate:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtClassifyAllocateExcel(@Valid EvtClassifyAllocatePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtClassifyAllocateDO> list = evtClassifyAllocateService.getEvtClassifyAllocatePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件分级分拨.xls", "数据", EvtClassifyAllocateRespVO.class,
                        BeanUtils.toBean(list, EvtClassifyAllocateRespVO.class));
    }

}