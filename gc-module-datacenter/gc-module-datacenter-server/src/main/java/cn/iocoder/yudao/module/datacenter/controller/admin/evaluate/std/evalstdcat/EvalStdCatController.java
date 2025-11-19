package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.std.evalstdcat.vo.EvalStdCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.std.evalstdcat.EvalStdCatDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.std.evalstdcat.EvalStdCatService;

@Tag(name = "管理后台 - 标准分类管理")
@RestController
@RequestMapping("/datacenter/eval-std-cat")
@Validated
public class EvalStdCatController {

    @Resource
    private EvalStdCatService evalStdCatService;

    @PostMapping("/create")
    @Operation(summary = "创建标准分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:create')")
    public CommonResult<Long> createEvalStdCat(@Valid @RequestBody EvalStdCatSaveReqVO createReqVO) {
        return success(evalStdCatService.createEvalStdCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新标准分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:update')")
    public CommonResult<Boolean> updateEvalStdCat(@Valid @RequestBody EvalStdCatSaveReqVO updateReqVO) {
        evalStdCatService.updateEvalStdCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除标准分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:delete')")
    public CommonResult<Boolean> deleteEvalStdCat(@RequestParam("id") Long id) {
        evalStdCatService.deleteEvalStdCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得标准分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:query')")
    public CommonResult<EvalStdCatRespVO> getEvalStdCat(@RequestParam("id") Long id) {
        EvalStdCatDO evalStdCat = evalStdCatService.getEvalStdCat(id);
        return success(BeanUtils.toBean(evalStdCat, EvalStdCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得标准分类管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:query')")
    public CommonResult<PageResult<EvalStdCatRespVO>> getEvalStdCatPage(@Valid EvalStdCatPageReqVO pageReqVO) {
        PageResult<EvalStdCatDO> pageResult = evalStdCatService.getEvalStdCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalStdCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出标准分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-std-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalStdCatExcel(@Valid EvalStdCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalStdCatDO> list = evalStdCatService.getEvalStdCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "标准分类管理.xls", "数据", EvalStdCatRespVO.class,
                        BeanUtils.toBean(list, EvalStdCatRespVO.class));
    }

}