package cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.scope.vo.ScopeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.scope.ScopeDO;
import cn.iocoder.yudao.module.evaluate.service.scope.ScopeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 范围字典")
@RestController
@RequestMapping("/evaluate/scope")
@Validated
public class ScopeController {

    @Resource
    private ScopeService scopeService;

    @PostMapping("/create")
    @Operation(summary = "创建范围字典")
    @PreAuthorize("@ss.hasPermission('evaluate:scope:create')")
    public CommonResult<Long> createScope(@Valid @RequestBody ScopeSaveReqVO createReqVO) {
        return success(scopeService.createScope(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新范围字典")
    @PreAuthorize("@ss.hasPermission('evaluate:scope:update')")
    public CommonResult<Boolean> updateScope(@Valid @RequestBody ScopeSaveReqVO updateReqVO) {
        scopeService.updateScope(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除范围字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:scope:delete')")
    public CommonResult<Boolean> deleteScope(@RequestParam("id") Long id) {
        scopeService.deleteScope(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得范围字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:scope:query')")
    public CommonResult<ScopeRespVO> getScope(@RequestParam("id") Long id) {
        ScopeDO scope = scopeService.getScope(id);
        return success(BeanUtils.toBean(scope, ScopeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得范围字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:scope:query')")
    public CommonResult<PageResult<ScopeRespVO>> getScopePage(@Valid ScopePageReqVO pageReqVO) {
        PageResult<ScopeDO> pageResult = scopeService.getScopePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ScopeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出范围字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:scope:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportScopeExcel(@Valid ScopePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ScopeDO> list = scopeService.getScopePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "范围字典.xls", "数据", ScopeRespVO.class,
                        BeanUtils.toBean(list, ScopeRespVO.class));
    }

}