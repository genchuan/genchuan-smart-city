package cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monitor.baseReport.basicmonidxrpt.vo.BasicMonIdxRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptDO;
import cn.iocoder.yudao.module.datacenter.service.monitor.baseReport.basicmonidxrpt.BasicMonIdxRptService;

@Tag(name = "管理后台 - 基础监测指标报")
@RestController
@RequestMapping("/datacenter/basic-mon-idx-rpt")
@Validated
public class BasicMonIdxRptController {

    @Resource
    private BasicMonIdxRptService basicMonIdxRptService;

    @PostMapping("/create")
    @Operation(summary = "创建基础监测指标报")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:create')")
    public CommonResult<Long> createBasicMonIdxRpt(@Valid @RequestBody BasicMonIdxRptSaveReqVO createReqVO) {
        return success(basicMonIdxRptService.createBasicMonIdxRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新基础监测指标报")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:update')")
    public CommonResult<Boolean> updateBasicMonIdxRpt(@Valid @RequestBody BasicMonIdxRptSaveReqVO updateReqVO) {
        basicMonIdxRptService.updateBasicMonIdxRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除基础监测指标报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:delete')")
    public CommonResult<Boolean> deleteBasicMonIdxRpt(@RequestParam("id") Long id) {
        basicMonIdxRptService.deleteBasicMonIdxRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得基础监测指标报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:query')")
    public CommonResult<BasicMonIdxRptRespVO> getBasicMonIdxRpt(@RequestParam("id") Long id) {
        BasicMonIdxRptDO basicMonIdxRpt = basicMonIdxRptService.getBasicMonIdxRpt(id);
        return success(BeanUtils.toBean(basicMonIdxRpt, BasicMonIdxRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得基础监测指标报分页")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:query')")
    public CommonResult<PageResult<BasicMonIdxRptRespVO>> getBasicMonIdxRptPage(@Valid BasicMonIdxRptPageReqVO pageReqVO) {
        PageResult<BasicMonIdxRptDO> pageResult = basicMonIdxRptService.getBasicMonIdxRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, BasicMonIdxRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出基础监测指标报 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:basic-mon-idx-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportBasicMonIdxRptExcel(@Valid BasicMonIdxRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<BasicMonIdxRptDO> list = basicMonIdxRptService.getBasicMonIdxRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "基础监测指标报.xls", "数据", BasicMonIdxRptRespVO.class,
                        BeanUtils.toBean(list, BasicMonIdxRptRespVO.class));
    }

}