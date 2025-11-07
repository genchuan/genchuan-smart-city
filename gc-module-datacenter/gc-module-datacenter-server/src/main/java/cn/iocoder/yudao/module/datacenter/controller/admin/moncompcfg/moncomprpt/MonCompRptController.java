package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.MonCompRptSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncomprpt.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncomprpt.MonCompRptDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncomprpt.MonCompRptService;

@Tag(name = "管理后台 - 监测部件统计报")
@RestController
@RequestMapping("/datacenter/mon-comp-rpt")
@Validated
public class MonCompRptController {

    @Resource
    private MonCompRptService monCompRptService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件统计报")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:create')")
    public CommonResult<Long> createMonCompRpt(@Valid @RequestBody MonCompRptSaveReqVO createReqVO) {
        return success(monCompRptService.createMonCompRpt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件统计报")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:update')")
    public CommonResult<Boolean> updateMonCompRpt(@Valid @RequestBody MonCompRptSaveReqVO updateReqVO) {
        monCompRptService.updateMonCompRpt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件统计报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:delete')")
    public CommonResult<Boolean> deleteMonCompRpt(@RequestParam("id") Long id) {
        monCompRptService.deleteMonCompRpt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件统计报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:query')")
    public CommonResult<MonCompRptRespVO> getMonCompRpt(@RequestParam("id") Long id) {
        MonCompRptDO monCompRpt = monCompRptService.getMonCompRpt(id);
        return success(BeanUtils.toBean(monCompRpt, MonCompRptRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件统计报分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:query')")
    public CommonResult<PageResult<MonCompRptRespVO>> getMonCompRptPage(@Valid MonCompRptPageReqVO pageReqVO) {
        PageResult<MonCompRptDO> pageResult = monCompRptService.getMonCompRptPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompRptRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件统计报 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-rpt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompRptExcel(@Valid MonCompRptPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompRptDO> list = monCompRptService.getMonCompRptPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件统计报.xls", "数据", MonCompRptRespVO.class,
                        BeanUtils.toBean(list, MonCompRptRespVO.class));
    }

}