package cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.EmerTeamCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerteamcat.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerteamcat.EmerTeamCatDO;
import cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerteamcat.EmerTeamCatService;

@Tag(name = "管理后台 - 救援队伍分类配置")
@RestController
@RequestMapping("/datacenter/emer-team-cat")
@Validated
public class EmerTeamCatController {

    @Resource
    private EmerTeamCatService emerTeamCatService;

    @PostMapping("/create")
    @Operation(summary = "创建救援队伍分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:create')")
    public CommonResult<Long> createEmerTeamCat(@Valid @RequestBody EmerTeamCatSaveReqVO createReqVO) {
        return success(emerTeamCatService.createEmerTeamCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新救援队伍分类配置")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:update')")
    public CommonResult<Boolean> updateEmerTeamCat(@Valid @RequestBody EmerTeamCatSaveReqVO updateReqVO) {
        emerTeamCatService.updateEmerTeamCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除救援队伍分类配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:delete')")
    public CommonResult<Boolean> deleteEmerTeamCat(@RequestParam("id") Long id) {
        emerTeamCatService.deleteEmerTeamCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得救援队伍分类配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:query')")
    public CommonResult<EmerTeamCatRespVO> getEmerTeamCat(@RequestParam("id") Long id) {
        EmerTeamCatDO emerTeamCat = emerTeamCatService.getEmerTeamCat(id);
        return success(BeanUtils.toBean(emerTeamCat, EmerTeamCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得救援队伍分类配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:query')")
    public CommonResult<PageResult<EmerTeamCatRespVO>> getEmerTeamCatPage(@Valid EmerTeamCatPageReqVO pageReqVO) {
        PageResult<EmerTeamCatDO> pageResult = emerTeamCatService.getEmerTeamCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EmerTeamCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出救援队伍分类配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:emer-team-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEmerTeamCatExcel(@Valid EmerTeamCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EmerTeamCatDO> list = emerTeamCatService.getEmerTeamCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "救援队伍分类配置.xls", "数据", EmerTeamCatRespVO.class,
                        BeanUtils.toBean(list, EmerTeamCatRespVO.class));
    }

}