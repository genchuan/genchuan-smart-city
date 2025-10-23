package cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv;

import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.validation.constraints.*;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.evalgriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evalgriddiv.EvalGridDivDO;
import cn.iocoder.yudao.module.datacenter.service.evalgriddiv.EvalGridDivService;

@Tag(name = "管理后台 - 评价网格划分")
@RestController
@RequestMapping("/datacenter/eval-grid-div")
@Validated
public class EvalGridDivController {

    @Resource
    private EvalGridDivService evalGridDivService;

    @PostMapping("/create")
    @Operation(summary = "创建评价网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:create')")
    public CommonResult<Long> createEvalGridDiv(@Valid @RequestBody EvalGridDivSaveReqVO createReqVO) {
        return success(evalGridDivService.createEvalGridDiv(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新评价网格划分")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:update')")
    public CommonResult<Boolean> updateEvalGridDiv(@Valid @RequestBody EvalGridDivSaveReqVO updateReqVO) {
        evalGridDivService.updateEvalGridDiv(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除评价网格划分")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:delete')")
    public CommonResult<Boolean> deleteEvalGridDiv(@RequestParam("id") Long id) {
        evalGridDivService.deleteEvalGridDiv(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得评价网格划分")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<EvalGridDivRespVO> getEvalGridDiv(@RequestParam("id") Long id) {
        EvalGridDivDO evalGridDiv = evalGridDivService.getEvalGridDiv(id);
        return success(BeanUtils.toBean(evalGridDiv, EvalGridDivRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得评价网格划分分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:query')")
    public CommonResult<PageResult<EvalGridDivRespVO>> getEvalGridDivPage(@Valid EvalGridDivPageReqVO pageReqVO) {
        PageResult<EvalGridDivDO> pageResult = evalGridDivService.getEvalGridDivPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalGridDivRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出评价网格划分 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-grid-div:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalGridDivExcel(@Valid EvalGridDivPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalGridDivDO> list = evalGridDivService.getEvalGridDivPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "评价网格划分.xls", "数据", EvalGridDivRespVO.class,
                        BeanUtils.toBean(list, EvalGridDivRespVO.class));
    }

}