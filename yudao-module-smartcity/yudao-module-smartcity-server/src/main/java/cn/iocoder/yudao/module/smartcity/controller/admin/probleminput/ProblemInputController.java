package cn.iocoder.yudao.module.smartcity.controller.admin.probleminput;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.probleminput.ProblemInputDO;
import cn.iocoder.yudao.module.smartcity.service.probleminput.ProblemInputService;

@Tag(name = "管理后台 - 问题录入")
@RestController
@RequestMapping("/smartcity/problem-input")
@Validated
public class ProblemInputController {

    @Resource
    private ProblemInputService problemInputService;

    @PostMapping("/create")
    @Operation(summary = "创建问题录入")
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:create')")
    public CommonResult<Long> createProblemInput(@Valid @RequestBody ProblemInputSaveReqVO createReqVO) {
        return success(problemInputService.createProblemInput(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题录入")
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:update')")
    public CommonResult<Boolean> updateProblemInput(@Valid @RequestBody ProblemInputSaveReqVO updateReqVO) {
        problemInputService.updateProblemInput(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题录入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:delete')")
    public CommonResult<Boolean> deleteProblemInput(@RequestParam("id") Long id) {
        problemInputService.deleteProblemInput(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题录入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:query')")
    public CommonResult<ProblemInputRespVO> getProblemInput(@RequestParam("id") Long id) {
        ProblemInputDO problemInput = problemInputService.getProblemInput(id);
        return success(BeanUtils.toBean(problemInput, ProblemInputRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题录入分页")
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:query')")
    public CommonResult<PageResult<ProblemInputRespVO>> getProblemInputPage(@Valid ProblemInputPageReqVO pageReqVO) {
        PageResult<ProblemInputDO> pageResult = problemInputService.getProblemInputPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProblemInputRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问题录入 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:problem-input:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProblemInputExcel(@Valid ProblemInputPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProblemInputDO> list = problemInputService.getProblemInputPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问题录入.xls", "数据", ProblemInputRespVO.class,
                        BeanUtils.toBean(list, ProblemInputRespVO.class));
    }

}