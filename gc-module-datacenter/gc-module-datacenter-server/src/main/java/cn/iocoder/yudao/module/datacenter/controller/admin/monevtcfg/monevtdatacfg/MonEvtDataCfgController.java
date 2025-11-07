package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.MonEvtDataCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtdatacfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtdatacfg.MonEvtDataCfgDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtdatacfg.MonEvtDataCfgService;

@Tag(name = "管理后台 - 监测事件数据配置")
@RestController
@RequestMapping("/datacenter/mon-evt-data-cfg")
@Validated
public class MonEvtDataCfgController {

    @Resource
    private MonEvtDataCfgService monEvtDataCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件数据配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:create')")
    public CommonResult<Long> createMonEvtDataCfg(@Valid @RequestBody MonEvtDataCfgSaveReqVO createReqVO) {
        return success(monEvtDataCfgService.createMonEvtDataCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件数据配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:update')")
    public CommonResult<Boolean> updateMonEvtDataCfg(@Valid @RequestBody MonEvtDataCfgSaveReqVO updateReqVO) {
        monEvtDataCfgService.updateMonEvtDataCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件数据配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:delete')")
    public CommonResult<Boolean> deleteMonEvtDataCfg(@RequestParam("id") Long id) {
        monEvtDataCfgService.deleteMonEvtDataCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件数据配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:query')")
    public CommonResult<MonEvtDataCfgRespVO> getMonEvtDataCfg(@RequestParam("id") Long id) {
        MonEvtDataCfgDO monEvtDataCfg = monEvtDataCfgService.getMonEvtDataCfg(id);
        return success(BeanUtils.toBean(monEvtDataCfg, MonEvtDataCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件数据配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:query')")
    public CommonResult<PageResult<MonEvtDataCfgRespVO>> getMonEvtDataCfgPage(@Valid MonEvtDataCfgPageReqVO pageReqVO) {
        PageResult<MonEvtDataCfgDO> pageResult = monEvtDataCfgService.getMonEvtDataCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtDataCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件数据配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-data-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtDataCfgExcel(@Valid MonEvtDataCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtDataCfgDO> list = monEvtDataCfgService.getMonEvtDataCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件数据配置.xls", "数据", MonEvtDataCfgRespVO.class,
                        BeanUtils.toBean(list, MonEvtDataCfgRespVO.class));
    }

}