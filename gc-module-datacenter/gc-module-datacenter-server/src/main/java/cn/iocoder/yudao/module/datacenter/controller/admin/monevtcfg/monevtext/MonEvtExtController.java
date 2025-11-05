package cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext;

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtext.MonEvtExtDO;
import cn.iocoder.yudao.module.datacenter.service.monevtcfg.monevtext.MonEvtExtService;

@Tag(name = "管理后台 - 扩展监测事件配置")
@RestController
@RequestMapping("/datacenter/mon-evt-ext")
@Validated
public class MonEvtExtController {

    @Resource
    private MonEvtExtService monEvtExtService;

    @PostMapping("/create")
    @Operation(summary = "创建扩展监测事件配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:create')")
    public CommonResult<Long> createMonEvtExt(@Valid @RequestBody MonEvtExtSaveReqVO createReqVO) {
        return success(monEvtExtService.createMonEvtExt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新扩展监测事件配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:update')")
    public CommonResult<Boolean> updateMonEvtExt(@Valid @RequestBody MonEvtExtSaveReqVO updateReqVO) {
        monEvtExtService.updateMonEvtExt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除扩展监测事件配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:delete')")
    public CommonResult<Boolean> deleteMonEvtExt(@RequestParam("id") Long id) {
        monEvtExtService.deleteMonEvtExt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得扩展监测事件配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:query')")
    public CommonResult<MonEvtExtRespVO> getMonEvtExt(@RequestParam("id") Long id) {
        MonEvtExtDO monEvtExt = monEvtExtService.getMonEvtExt(id);
        return success(BeanUtils.toBean(monEvtExt, MonEvtExtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得扩展监测事件配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:query')")
    public CommonResult<PageResult<MonEvtExtRespVO>> getMonEvtExtPage(@Valid MonEvtExtPageReqVO pageReqVO) {
        PageResult<MonEvtExtDO> pageResult = monEvtExtService.getMonEvtExtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonEvtExtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出扩展监测事件配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-evt-ext:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonEvtExtExcel(@Valid MonEvtExtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonEvtExtDO> list = monEvtExtService.getMonEvtExtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "扩展监测事件配置.xls", "数据", MonEvtExtRespVO.class,
                        BeanUtils.toBean(list, MonEvtExtRespVO.class));
    }

}