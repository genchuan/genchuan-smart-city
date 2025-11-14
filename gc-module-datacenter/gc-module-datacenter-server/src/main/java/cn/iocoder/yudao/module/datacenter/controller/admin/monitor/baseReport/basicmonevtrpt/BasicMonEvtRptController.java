package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonevtrpt.vo.BasicMonEvtRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonevtrpt.BasicMonEvtRptService;

@Tag(name = "管理后台 - 基础监测事件报")
@RestController
@RequestMapping("/datacenter/basic-mon-evt-rpt")
@Validated
public class BasicMonEvtRptController {

    @Resource
    private BasicMonEvtRptService basicMonEvtRptService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测事件报")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:create')")
    public CommonResult<Long> createBasicMonEvtRpt(@Valid @RequestBody BasicMonEvtRptSaveReqVO createReqVO) {
        return success(basicMonEvtRptService.createBasicMonEvtRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测事件报")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:update')")
    public CommonResult<Boolean> updateBasicMonEvtRpt(@Valid @RequestBody BasicMonEvtRptSaveReqVO updateReqVO) {
        basicMonEvtRptService.updateBasicMonEvtRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测事件报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:delete')")
    public CommonResult<Boolean> deleteBasicMonEvtRpt(@RequestParam("id") Long id) {
        basicMonEvtRptService.deleteBasicMonEvtRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测事件报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:query')")
    public CommonResult<BasicMonEvtRptRespVO> getBasicMonEvtRpt(@RequestParam("id") Long id) {
        BasicMonEvtRptDO basicMonEvtRpt = basicMonEvtRptService.getBasicMonEvtRpt(id);
        return success(BeanUtils.toBean(basicMonEvtRpt, BasicMonEvtRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测事件报分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:query')")
    public CommonResult<PageResult<BasicMonEvtRptRespVO>> getBasicMonEvtRptPage(@Valid BasicMonEvtRptPageReqVO pageReqVO) {
        PageResult<BasicMonEvtRptDO> pageResult = basicMonEvtRptService.getBasicMonEvtRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonEvtRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测事件报 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-evt-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonEvtRptExcel(@Valid BasicMonEvtRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonEvtRptDO> list = basicMonEvtRptService.getBasicMonEvtRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测事件报.xls", "数据", BasicMonEvtRptRespVO.class,
                        BeanUtils.toBean(list, BasicMonEvtRptRespVO.class));
    }

}