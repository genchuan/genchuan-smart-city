package cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evttypecfg.EvtTypeCfgDO;
import cn.iocoder.yudao.module.datacenter.service.evtmanager.evttypecfg.EvtTypeCfgService;

@Tag(name = "管理后台 - 事件类型配置")
@RestController
@RequestMapping("/datacenter/evt-type-cfg")
@Validated
public class EvtTypeCfgController {

    @Resource
    private EvtTypeCfgService evtTypeCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建事件类型配置")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:create')")
    public CommonResult<Long> createEvtTypeCfg(@Valid @RequestBody EvtTypeCfgSaveReqVO createReqVO) {
        return success(evtTypeCfgService.createEvtTypeCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新事件类型配置")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:update')")
    public CommonResult<Boolean> updateEvtTypeCfg(@Valid @RequestBody EvtTypeCfgSaveReqVO updateReqVO) {
        evtTypeCfgService.updateEvtTypeCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除事件类型配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:delete')")
    public CommonResult<Boolean> deleteEvtTypeCfg(@RequestParam("id") Long id) {
        evtTypeCfgService.deleteEvtTypeCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得事件类型配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:query')")
    public CommonResult<EvtTypeCfgRespVO> getEvtTypeCfg(@RequestParam("id") Long id) {
        EvtTypeCfgDO evtTypeCfg = evtTypeCfgService.getEvtTypeCfg(id);
        return success(BeanUtils.toBean(evtTypeCfg, EvtTypeCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得事件类型配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:query')")
    public CommonResult<PageResult<EvtTypeCfgRespVO>> getEvtTypeCfgPage(@Valid EvtTypeCfgPageReqVO pageReqVO) {
        PageResult<EvtTypeCfgDO> pageResult = evtTypeCfgService.getEvtTypeCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvtTypeCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出事件类型配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:evt-type-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvtTypeCfgExcel(@Valid EvtTypeCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvtTypeCfgDO> list = evtTypeCfgService.getEvtTypeCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "事件类型配置.xls", "数据", EvtTypeCfgRespVO.class,
                        BeanUtils.toBean(list, EvtTypeCfgRespVO.class));
    }

}