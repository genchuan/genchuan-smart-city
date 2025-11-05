package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.MonCompCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcat.MonCompCatDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompcat.MonCompCatService;

@Tag(name = "管理后台 - 监测部件分类配置")
@RestController
@RequestMapping("/datacenter/mon-comp-cat")
@Validated
public class MonCompCatController {

    @Resource
    private MonCompCatService monCompCatService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:create')")
    public CommonResult<Long> createMonCompCat(@Valid @RequestBody MonCompCatSaveReqVO createReqVO) {
        return success(monCompCatService.createMonCompCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:update')")
    public CommonResult<Boolean> updateMonCompCat(@Valid @RequestBody MonCompCatSaveReqVO updateReqVO) {
        monCompCatService.updateMonCompCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:delete')")
    public CommonResult<Boolean> deleteMonCompCat(@RequestParam("id") Long id) {
        monCompCatService.deleteMonCompCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:query')")
    public CommonResult<MonCompCatRespVO> getMonCompCat(@RequestParam("id") Long id) {
        MonCompCatDO monCompCat = monCompCatService.getMonCompCat(id);
        return success(BeanUtils.toBean(monCompCat, MonCompCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:query')")
    public CommonResult<PageResult<MonCompCatRespVO>> getMonCompCatPage(@Valid MonCompCatPageReqVO pageReqVO) {
        PageResult<MonCompCatDO> pageResult = monCompCatService.getMonCompCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompCatExcel(@Valid MonCompCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompCatDO> list = monCompCatService.getMonCompCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件分类配置.xls", "数据", MonCompCatRespVO.class,
                        BeanUtils.toBean(list, MonCompCatRespVO.class));
    }

}