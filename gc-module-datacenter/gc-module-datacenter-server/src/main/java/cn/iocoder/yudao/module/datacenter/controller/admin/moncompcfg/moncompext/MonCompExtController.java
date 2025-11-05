package cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext;

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.MonCompExtSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompext.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompext.MonCompExtDO;
import cn.iocoder.yudao.module.datacenter.service.moncompcfg.moncompext.MonCompExtService;

@Tag(name = "管理后台 - 监测部件扩展配置")
@RestController
@RequestMapping("/datacenter/mon-comp-ext")
@Validated
public class MonCompExtController {

    @Resource
    private MonCompExtService monCompExtService;

    @PostMapping("/create")
    @Operation(summary = "创建监测部件扩展配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:create')")
    public CommonResult<Long> createMonCompExt(@Valid @RequestBody MonCompExtSaveReqVO createReqVO) {
        return success(monCompExtService.createMonCompExt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新监测部件扩展配置")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:update')")
    public CommonResult<Boolean> updateMonCompExt(@Valid @RequestBody MonCompExtSaveReqVO updateReqVO) {
        monCompExtService.updateMonCompExt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除监测部件扩展配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:delete')")
    public CommonResult<Boolean> deleteMonCompExt(@RequestParam("id") Long id) {
        monCompExtService.deleteMonCompExt(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得监测部件扩展配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:query')")
    public CommonResult<MonCompExtRespVO> getMonCompExt(@RequestParam("id") Long id) {
        MonCompExtDO monCompExt = monCompExtService.getMonCompExt(id);
        return success(BeanUtils.toBean(monCompExt, MonCompExtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得监测部件扩展配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:query')")
    public CommonResult<PageResult<MonCompExtRespVO>> getMonCompExtPage(@Valid MonCompExtPageReqVO pageReqVO) {
        PageResult<MonCompExtDO> pageResult = monCompExtService.getMonCompExtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MonCompExtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出监测部件扩展配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:mon-comp-ext:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMonCompExtExcel(@Valid MonCompExtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MonCompExtDO> list = monCompExtService.getMonCompExtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "监测部件扩展配置.xls", "数据", MonCompExtRespVO.class,
                        BeanUtils.toBean(list, MonCompExtRespVO.class));
    }

}