package cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.RelMngCompSymbolSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.relmngcompsymbol.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.relmngcompsymbol.RelMngCompSymbolDO;
import cn.iocoder.yudao.module.datacenter.service.mngcompcfg.relmngcompsymbol.RelMngCompSymbolService;

@Tag(name = "管理后台 - 管理部件图示关联")
@RestController
@RequestMapping("/datacenter/rel-mng-comp-symbol")
@Validated
public class RelMngCompSymbolController {

    @Resource
    private RelMngCompSymbolService relMngCompSymbolService;

    @PostMapping("/create")
    @Operation(summary = "创建管理部件图示关联")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:create')")
    public CommonResult<Long> createRelMngCompSymbol(@Valid @RequestBody RelMngCompSymbolSaveReqVO createReqVO) {
        return success(relMngCompSymbolService.createRelMngCompSymbol(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新管理部件图示关联")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:update')")
    public CommonResult<Boolean> updateRelMngCompSymbol(@Valid @RequestBody RelMngCompSymbolSaveReqVO updateReqVO) {
        relMngCompSymbolService.updateRelMngCompSymbol(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除管理部件图示关联")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:delete')")
    public CommonResult<Boolean> deleteRelMngCompSymbol(@RequestParam("id") Long id) {
        relMngCompSymbolService.deleteRelMngCompSymbol(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得管理部件图示关联")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:query')")
    public CommonResult<RelMngCompSymbolRespVO> getRelMngCompSymbol(@RequestParam("id") Long id) {
        RelMngCompSymbolDO relMngCompSymbol = relMngCompSymbolService.getRelMngCompSymbol(id);
        return success(BeanUtils.toBean(relMngCompSymbol, RelMngCompSymbolRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得管理部件图示关联分页")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:query')")
    public CommonResult<PageResult<RelMngCompSymbolRespVO>> getRelMngCompSymbolPage(@Valid RelMngCompSymbolPageReqVO pageReqVO) {
        PageResult<RelMngCompSymbolDO> pageResult = relMngCompSymbolService.getRelMngCompSymbolPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, RelMngCompSymbolRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出管理部件图示关联 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:rel-mng-comp-symbol:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportRelMngCompSymbolExcel(@Valid RelMngCompSymbolPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<RelMngCompSymbolDO> list = relMngCompSymbolService.getRelMngCompSymbolPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "管理部件图示关联.xls", "数据", RelMngCompSymbolRespVO.class,
                        BeanUtils.toBean(list, RelMngCompSymbolRespVO.class));
    }

}