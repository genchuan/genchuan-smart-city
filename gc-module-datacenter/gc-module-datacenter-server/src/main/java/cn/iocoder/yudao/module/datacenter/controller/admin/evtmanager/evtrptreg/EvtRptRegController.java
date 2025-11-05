package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtrptreg.EvtRptRegDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evtrptreg.EvtRptRegService;

@Tag(name = "管理后台 - 事件接报登记")
@RestController
@RequestMapping("/datacenter/evt-rpt-reg")
@Validated
public class EvtRptRegController {

    @Resource
    private EvtRptRegService evtRptRegService;

    @PostMapping("/create")
    @Operation(summary = "创建事件接报登记")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:create')")
    public CommonResult<Long> createEvtRptReg(@Valid @RequestBody EvtRptRegSaveReqVO createReqVO) {
        return success(evtRptRegService.createEvtRptReg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件接报登记")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:update')")
    public CommonResult<Boolean> updateEvtRptReg(@Valid @RequestBody EvtRptRegSaveReqVO updateReqVO) {
        evtRptRegService.updateEvtRptReg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件接报登记")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:delete')")
    public CommonResult<Boolean> deleteEvtRptReg(@RequestParam("id") Long id) {
        evtRptRegService.deleteEvtRptReg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件接报登记")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:query')")
    public CommonResult<EvtRptRegRespVO> getEvtRptReg(@RequestParam("id") Long id) {
        EvtRptRegDO evtRptReg = evtRptRegService.getEvtRptReg(id);
        return success(BeanUtils.toBean(evtRptReg, EvtRptRegRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件接报登记分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:query')")
    public CommonResult<PageResult<EvtRptRegRespVO>> getEvtRptRegPage(@Valid EvtRptRegPageReqVO pageReqVO) {
        PageResult<EvtRptRegDO> pageResult = evtRptRegService.getEvtRptRegPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtRptRegRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件接报登记 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-rpt-reg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtRptRegExcel(@Valid EvtRptRegPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtRptRegDO> list = evtRptRegService.getEvtRptRegPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件接报登记.xls", "数据", EvtRptRegRespVO.class,
                        BeanUtils.toBean(list, EvtRptRegRespVO.class));
    }

}