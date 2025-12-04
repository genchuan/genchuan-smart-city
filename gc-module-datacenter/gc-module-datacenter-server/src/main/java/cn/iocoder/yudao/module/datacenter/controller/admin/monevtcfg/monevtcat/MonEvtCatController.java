package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcat.MonEvtCatDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtcat.MonEvtCatService;

@Tag(name = "管理后台 - 监测事件分类配置")
@RestController
@RequestMapping("/datacenter/mon-evt-cat")
@Validated
public class MonEvtCatController {

    @Resource
    private MonEvtCatService monEvtCatService;

    @PostMapping("/create")
    @Operation(summary = "创建监测事件分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:create')")
    public CommonResult<Long> createMonEvtCat(@Valid @RequestBody MonEvtCatSaveReqVO createReqVO) {
        return success(monEvtCatService.createMonEvtCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测事件分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:update')")
    public CommonResult<Boolean> updateMonEvtCat(@Valid @RequestBody MonEvtCatSaveReqVO updateReqVO) {
        monEvtCatService.updateMonEvtCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测事件分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:delete')")
    public CommonResult<Boolean> deleteMonEvtCat(@RequestParam("id") Long id) {
        monEvtCatService.deleteMonEvtCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测事件分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
//    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:query')")
    public CommonResult<MonEvtCatRespVO> getMonEvtCat(@RequestParam("id") Long id) {
        MonEvtCatDO monEvtCat = monEvtCatService.getMonEvtCat(id);
        return success(BeanUtils.toBean(monEvtCat, MonEvtCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测事件分类配置分页")
//    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:query')")
    public CommonResult<PageResult<MonEvtCatRespVO>> getMonEvtCatPage(@Valid MonEvtCatPageReqVO pageReqVO) {
        PageResult<MonEvtCatDO> pageResult = monEvtCatService.getMonEvtCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测事件分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtCatExcel(@Valid MonEvtCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtCatDO> list = monEvtCatService.getMonEvtCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测事件分类配置.xls", "数据", MonEvtCatRespVO.class,
                        BeanUtils.toBean(list, MonEvtCatRespVO.class));
    }

}