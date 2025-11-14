package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.regional.domdivmonevt.vo.DomDivMonEvtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.regional.domdivmonevt.DomDivMonEvtDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.regional.domdivmonevt.DomDivMonEvtService;

@Tag(name = "管理后台 - 监测事件分域")
@RestController
@RequestMapping("/datacenter/dom-div-mon-evt")
@Validated
public class DomDivMonEvtController {

    @Resource
    private DomDivMonEvtService domDivMonEvtService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:create')")
    public CommonResult<Long> createDomDivMonEvt(@Valid @RequestBody DomDivMonEvtSaveReqVO createReqVO) {
        return success(domDivMonEvtService.createDomDivMonEvt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件分域")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:update')")
    public CommonResult<Boolean> updateDomDivMonEvt(@Valid @RequestBody DomDivMonEvtSaveReqVO updateReqVO) {
        domDivMonEvtService.updateDomDivMonEvt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件分域")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:delete')")
    public CommonResult<Boolean> deleteDomDivMonEvt(@RequestParam("id") Long id) {
        domDivMonEvtService.deleteDomDivMonEvt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件分域")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:query')")
    public CommonResult<DomDivMonEvtRespVO> getDomDivMonEvt(@RequestParam("id") Long id) {
        DomDivMonEvtDO domDivMonEvt = domDivMonEvtService.getDomDivMonEvt(id);
        return success(BeanUtils.toBean(domDivMonEvt, DomDivMonEvtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件分域分页")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:query')")
    public CommonResult<PageResult<DomDivMonEvtRespVO>> getDomDivMonEvtPage(@Valid DomDivMonEvtPageReqVO pageReqVO) {
        PageResult<DomDivMonEvtDO> pageResult = domDivMonEvtService.getDomDivMonEvtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DomDivMonEvtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件分域 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:dom-div-mon-evt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDomDivMonEvtExcel(@Valid DomDivMonEvtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DomDivMonEvtDO> list = domDivMonEvtService.getDomDivMonEvtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件分域.xls", "数据", DomDivMonEvtRespVO.class,
                        BeanUtils.toBean(list, DomDivMonEvtRespVO.class));
    }

}