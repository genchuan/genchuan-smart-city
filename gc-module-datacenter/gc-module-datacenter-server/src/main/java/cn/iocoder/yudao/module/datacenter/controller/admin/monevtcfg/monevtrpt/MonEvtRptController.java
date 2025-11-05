package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.MonEvtRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtrpt.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtrpt.MonEvtRptDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtrpt.MonEvtRptService;

@Tag(name = "管理后台 - 监测事件统计报")
@RestController
@RequestMapping("/datacenter/mon-evt-rpt")
@Validated
public class MonEvtRptController {

    @Resource
    private MonEvtRptService monEvtRptService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件统计报")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:create')")
    public CommonResult<Long> createMonEvtRpt(@Valid @RequestBody MonEvtRptSaveReqVO createReqVO) {
        return success(monEvtRptService.createMonEvtRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件统计报")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:update')")
    public CommonResult<Boolean> updateMonEvtRpt(@Valid @RequestBody MonEvtRptSaveReqVO updateReqVO) {
        monEvtRptService.updateMonEvtRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件统计报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:delete')")
    public CommonResult<Boolean> deleteMonEvtRpt(@RequestParam("id") Long id) {
        monEvtRptService.deleteMonEvtRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件统计报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:query')")
    public CommonResult<MonEvtRptRespVO> getMonEvtRpt(@RequestParam("id") Long id) {
        MonEvtRptDO monEvtRpt = monEvtRptService.getMonEvtRpt(id);
        return success(BeanUtils.toBean(monEvtRpt, MonEvtRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件统计报分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:query')")
    public CommonResult<PageResult<MonEvtRptRespVO>> getMonEvtRptPage(@Valid MonEvtRptPageReqVO pageReqVO) {
        PageResult<MonEvtRptDO> pageResult = monEvtRptService.getMonEvtRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件统计报 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtRptExcel(@Valid MonEvtRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtRptDO> list = monEvtRptService.getMonEvtRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件统计报.xls", "数据", MonEvtRptRespVO.class,
                        BeanUtils.toBean(list, MonEvtRptRespVO.class));
    }

}