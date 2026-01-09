package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofpoliciesandregulations.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsDO;
import cn.iocoder.yudao.module.smartcity.service.classificationofpoliciesandregulations.ClassificationOfPoliciesAndRegulationsService;

@Tag(name = "管理后台 - 政策法规分类")
@RestController
@RequestMapping("/smartcity/classification-of-policies-and-regulations")
@Validated
public class ClassificationOfPoliciesAndRegulationsController {

    @Resource
    private ClassificationOfPoliciesAndRegulationsService classificationOfPoliciesAndRegulationsService;

    @PostMapping("/create")
    @Operation(summary = "创建政策法规分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:create')")
    public CommonResult<Long> createClassificationOfPoliciesAndRegulations(@Valid @RequestBody ClassificationOfPoliciesAndRegulationsSaveReqVO createReqVO) {
        return success(classificationOfPoliciesAndRegulationsService.createClassificationOfPoliciesAndRegulations(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新政策法规分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:update')")
    public CommonResult<Boolean> updateClassificationOfPoliciesAndRegulations(@Valid @RequestBody ClassificationOfPoliciesAndRegulationsSaveReqVO updateReqVO) {
        classificationOfPoliciesAndRegulationsService.updateClassificationOfPoliciesAndRegulations(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除政策法规分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:delete')")
    public CommonResult<Boolean> deleteClassificationOfPoliciesAndRegulations(@RequestParam("id") Long id) {
        classificationOfPoliciesAndRegulationsService.deleteClassificationOfPoliciesAndRegulations(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得政策法规分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:query')")
    public CommonResult<ClassificationOfPoliciesAndRegulationsRespVO> getClassificationOfPoliciesAndRegulations(@RequestParam("id") Long id) {
        ClassificationOfPoliciesAndRegulationsDO classificationOfPoliciesAndRegulations = classificationOfPoliciesAndRegulationsService.getClassificationOfPoliciesAndRegulations(id);
        return success(BeanUtils.toBean(classificationOfPoliciesAndRegulations, ClassificationOfPoliciesAndRegulationsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得政策法规分类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:query')")
    public CommonResult<PageResult<ClassificationOfPoliciesAndRegulationsRespVO>> getClassificationOfPoliciesAndRegulationsPage(@Valid ClassificationOfPoliciesAndRegulationsPageReqVO pageReqVO) {
        PageResult<ClassificationOfPoliciesAndRegulationsDO> pageResult = classificationOfPoliciesAndRegulationsService.getClassificationOfPoliciesAndRegulationsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClassificationOfPoliciesAndRegulationsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出政策法规分类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-policies-and-regulations:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClassificationOfPoliciesAndRegulationsExcel(@Valid ClassificationOfPoliciesAndRegulationsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClassificationOfPoliciesAndRegulationsDO> list = classificationOfPoliciesAndRegulationsService.getClassificationOfPoliciesAndRegulationsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "政策法规分类.xls", "数据", ClassificationOfPoliciesAndRegulationsRespVO.class,
                        BeanUtils.toBean(list, ClassificationOfPoliciesAndRegulationsRespVO.class));
    }

}