package cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype.vo.ProblemTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype.vo.ProblemTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dictionary.problemtype.vo.ProblemTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.problemtype.ProblemTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.dictionary.problemtype.ProblemTypeService;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
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

@Tag(name = "字典表 - 问题类型")
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

    /**
     * 获得问题类型下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得问题类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('envirhealth:problem-type:query')")
    public CommonResult<List<OptionVO>> getProblemTypeOptions() {
        return success(problemTypeService.getProblemTypeOptions());
    }
}
