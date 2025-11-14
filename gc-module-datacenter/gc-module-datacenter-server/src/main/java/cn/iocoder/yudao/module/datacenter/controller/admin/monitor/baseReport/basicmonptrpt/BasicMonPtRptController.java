package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonptrpt.vo.BasicMonPtRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonptrpt.BasicMonPtRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonptrpt.BasicMonPtRptService;

@Tag(name = "管理后台 - 基础监测点位报表")
@RestController
@RequestMapping("/datacenter/basic-mon-pt-rpt")
@Validated
public class BasicMonPtRptController {

    @Resource
    private BasicMonPtRptService basicMonPtRptService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测点位报表")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:create')")
    public CommonResult<Long> createBasicMonPtRpt(@Valid @RequestBody BasicMonPtRptSaveReqVO createReqVO) {
        return success(basicMonPtRptService.createBasicMonPtRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测点位报表")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:update')")
    public CommonResult<Boolean> updateBasicMonPtRpt(@Valid @RequestBody BasicMonPtRptSaveReqVO updateReqVO) {
        basicMonPtRptService.updateBasicMonPtRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测点位报表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:delete')")
    public CommonResult<Boolean> deleteBasicMonPtRpt(@RequestParam("id") Long id) {
        basicMonPtRptService.deleteBasicMonPtRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测点位报表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:query')")
    public CommonResult<BasicMonPtRptRespVO> getBasicMonPtRpt(@RequestParam("id") Long id) {
        BasicMonPtRptDO basicMonPtRpt = basicMonPtRptService.getBasicMonPtRpt(id);
        return success(BeanUtils.toBean(basicMonPtRpt, BasicMonPtRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测点位报表分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:query')")
    public CommonResult<PageResult<BasicMonPtRptRespVO>> getBasicMonPtRptPage(@Valid BasicMonPtRptPageReqVO pageReqVO) {
        PageResult<BasicMonPtRptDO> pageResult = basicMonPtRptService.getBasicMonPtRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonPtRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测点位报表 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-pt-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonPtRptExcel(@Valid BasicMonPtRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonPtRptDO> list = basicMonPtRptService.getBasicMonPtRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测点位报表.xls", "数据", BasicMonPtRptRespVO.class,
                        BeanUtils.toBean(list, BasicMonPtRptRespVO.class));
    }

}