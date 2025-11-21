package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmonevt.vo.EarlyWarnMonEvtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmonevt.EarlyWarnMonEvtDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnmonevt.EarlyWarnMonEvtService;

@Tag(name = "管理后台 - 按监测事件预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-mon-evt")
@Validated
public class EarlyWarnMonEvtController {

    @Resource
    private EarlyWarnMonEvtService earlyWarnMonEvtService;

    @PostMapping("/create")
    @Operation(summary = "创建按监测事件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:create')")
    public CommonResult<Long> createEarlyWarnMonEvt(@Valid @RequestBody EarlyWarnMonEvtSaveReqVO createReqVO) {
        return success(earlyWarnMonEvtService.createEarlyWarnMonEvt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按监测事件预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:update')")
    public CommonResult<Boolean> updateEarlyWarnMonEvt(@Valid @RequestBody EarlyWarnMonEvtSaveReqVO updateReqVO) {
        earlyWarnMonEvtService.updateEarlyWarnMonEvt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按监测事件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:delete')")
    public CommonResult<Boolean> deleteEarlyWarnMonEvt(@RequestParam("id") Long id) {
        earlyWarnMonEvtService.deleteEarlyWarnMonEvt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按监测事件预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:query')")
    public CommonResult<EarlyWarnMonEvtRespVO> getEarlyWarnMonEvt(@RequestParam("id") Long id) {
        EarlyWarnMonEvtDO earlyWarnMonEvt = earlyWarnMonEvtService.getEarlyWarnMonEvt(id);
        return success(BeanUtils.toBean(earlyWarnMonEvt, EarlyWarnMonEvtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按监测事件预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:query')")
    public CommonResult<PageResult<EarlyWarnMonEvtRespVO>> getEarlyWarnMonEvtPage(@Valid EarlyWarnMonEvtPageReqVO pageReqVO) {
        PageResult<EarlyWarnMonEvtDO> pageResult = earlyWarnMonEvtService.getEarlyWarnMonEvtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnMonEvtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按监测事件预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-mon-evt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnMonEvtExcel(@Valid EarlyWarnMonEvtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnMonEvtDO> list = earlyWarnMonEvtService.getEarlyWarnMonEvtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按监测事件预警告警统计.xls", "数据", EarlyWarnMonEvtRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnMonEvtRespVO.class));
    }

}