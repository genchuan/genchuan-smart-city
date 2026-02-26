/*
package cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype;

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

import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.problemtype.ProblemTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.problemtype.ProblemTypeService;

@Tag(name = "环境卫生管理 - 问题类型字典表")
@RestController
@RequestMapping("/envirhealth/problem-type")
@Validated
public class ProblemTypeController {

    @Resource
    private ProblemTypeService problemTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建问题类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:create')")
    public CommonResult<Long> createProblemType(@Valid @RequestBody ProblemTypeSaveReqVO createReqVO) {
        return success(problemTypeService.createProblemType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题类型字典表")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:update')")
    public CommonResult<Boolean> updateProblemType(@Valid @RequestBody ProblemTypeSaveReqVO updateReqVO) {
        problemTypeService.updateProblemType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题类型字典表")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:delete')")
    public CommonResult<Boolean> deleteProblemType(@RequestParam("id") Long id) {
        problemTypeService.deleteProblemType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题类型字典表")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:query')")
    public CommonResult<ProblemTypeRespVO> getProblemType(@RequestParam("id") Long id) {
        ProblemTypeDO problemType = problemTypeService.getProblemType(id);
        return success(BeanUtils.toBean(problemType, ProblemTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题类型字典表分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:query')")
    public CommonResult<PageResult<ProblemTypeRespVO>> getProblemTypePage(@Valid ProblemTypePageReqVO pageReqVO) {
        PageResult<ProblemTypeDO> pageResult = problemTypeService.getProblemTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ProblemTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问题类型字典表 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportProblemTypeExcel(@Valid ProblemTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ProblemTypeDO> list = problemTypeService.getProblemTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问题类型字典表.xls", "数据", ProblemTypeRespVO.class,
                        BeanUtils.toBean(list, ProblemTypeRespVO.class));
    }

}*/
