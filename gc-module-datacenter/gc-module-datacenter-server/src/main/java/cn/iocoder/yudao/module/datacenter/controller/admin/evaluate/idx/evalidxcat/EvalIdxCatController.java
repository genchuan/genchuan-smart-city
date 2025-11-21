package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.idx.evalidxcat.vo.EvalIdxCatSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.idx.evalidxcat.EvalIdxCatDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.idx.evalidxcat.EvalIdxCatService;

@Tag(name = "管理后台 - 指标分类管理")
@RestController
@RequestMapping("/datacenter/eval-idx-cat")
@Validated
public class EvalIdxCatController {

    @Resource
    private EvalIdxCatService evalIdxCatService;

    @PostMapping("/create")
    @Operation(summary = "创建指标分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:create')")
    public CommonResult<Long> createEvalIdxCat(@Valid @RequestBody EvalIdxCatSaveReqVO createReqVO) {
        return success(evalIdxCatService.createEvalIdxCat(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标分类管理")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:update')")
    public CommonResult<Boolean> updateEvalIdxCat(@Valid @RequestBody EvalIdxCatSaveReqVO updateReqVO) {
        evalIdxCatService.updateEvalIdxCat(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标分类管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:delete')")
    public CommonResult<Boolean> deleteEvalIdxCat(@RequestParam("id") Long id) {
        evalIdxCatService.deleteEvalIdxCat(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标分类管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:query')")
    public CommonResult<EvalIdxCatRespVO> getEvalIdxCat(@RequestParam("id") Long id) {
        EvalIdxCatDO evalIdxCat = evalIdxCatService.getEvalIdxCat(id);
        return success(BeanUtils.toBean(evalIdxCat, EvalIdxCatRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标分类管理分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:query')")
    public CommonResult<PageResult<EvalIdxCatRespVO>> getEvalIdxCatPage(@Valid EvalIdxCatPageReqVO pageReqVO) {
        PageResult<EvalIdxCatDO> pageResult = evalIdxCatService.getEvalIdxCatPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalIdxCatRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标分类管理 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-idx-cat:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalIdxCatExcel(@Valid EvalIdxCatPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalIdxCatDO> list = evalIdxCatService.getEvalIdxCatPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标分类管理.xls", "数据", EvalIdxCatRespVO.class,
                        BeanUtils.toBean(list, EvalIdxCatRespVO.class));
    }

}