package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.MonCompDataCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompdatacfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompdatacfg.MonCompDataCfgDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompdatacfg.MonCompDataCfgService;

@Tag(name = "管理后台 - 监测部件数据配置")
@RestController
@RequestMapping("/datacenter/mon-comp-data-cfg")
@Validated
public class MonCompDataCfgController {

    @Resource
    private MonCompDataCfgService monCompDataCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件数据配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:create')")
    public CommonResult<Long> createMonCompDataCfg(@Valid @RequestBody MonCompDataCfgSaveReqVO createReqVO) {
        return success(monCompDataCfgService.createMonCompDataCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件数据配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:update')")
    public CommonResult<Boolean> updateMonCompDataCfg(@Valid @RequestBody MonCompDataCfgSaveReqVO updateReqVO) {
        monCompDataCfgService.updateMonCompDataCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件数据配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:delete')")
    public CommonResult<Boolean> deleteMonCompDataCfg(@RequestParam("id") Long id) {
        monCompDataCfgService.deleteMonCompDataCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件数据配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:query')")
    public CommonResult<MonCompDataCfgRespVO> getMonCompDataCfg(@RequestParam("id") Long id) {
        MonCompDataCfgDO monCompDataCfg = monCompDataCfgService.getMonCompDataCfg(id);
        return success(BeanUtils.toBean(monCompDataCfg, MonCompDataCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件数据配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:query')")
    public CommonResult<PageResult<MonCompDataCfgRespVO>> getMonCompDataCfgPage(@Valid MonCompDataCfgPageReqVO pageReqVO) {
        PageResult<MonCompDataCfgDO> pageResult = monCompDataCfgService.getMonCompDataCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompDataCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件数据配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-data-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompDataCfgExcel(@Valid MonCompDataCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompDataCfgDO> list = monCompDataCfgService.getMonCompDataCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件数据配置.xls", "数据", MonCompDataCfgRespVO.class,
                        BeanUtils.toBean(list, MonCompDataCfgRespVO.class));
    }

}