package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofguideinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofguideinformation.ClassificationOfGuideInformationDO;
import cn.iocoder.yudao.module.smartcity.service.classificationofguideinformation.ClassificationOfGuideInformationService;

@Tag(name = "管理后台 - 指南信息分类")
@RestController
@RequestMapping("/smartcity/classification-of-guide-information")
@Validated
public class ClassificationOfGuideInformationController {

    @Resource
    private ClassificationOfGuideInformationService classificationOfGuideInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建指南信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:create')")
    public CommonResult<Long> createClassificationOfGuideInformation(@Valid @RequestBody ClassificationOfGuideInformationSaveReqVO createReqVO) {
        return success(classificationOfGuideInformationService.createClassificationOfGuideInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指南信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:update')")
    public CommonResult<Boolean> updateClassificationOfGuideInformation(@Valid @RequestBody ClassificationOfGuideInformationSaveReqVO updateReqVO) {
        classificationOfGuideInformationService.updateClassificationOfGuideInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指南信息分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:delete')")
    public CommonResult<Boolean> deleteClassificationOfGuideInformation(@RequestParam("id") Long id) {
        classificationOfGuideInformationService.deleteClassificationOfGuideInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指南信息分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:query')")
    public CommonResult<ClassificationOfGuideInformationRespVO> getClassificationOfGuideInformation(@RequestParam("id") Long id) {
        ClassificationOfGuideInformationDO classificationOfGuideInformation = classificationOfGuideInformationService.getClassificationOfGuideInformation(id);
        return success(BeanUtils.toBean(classificationOfGuideInformation, ClassificationOfGuideInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指南信息分类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:query')")
    public CommonResult<PageResult<ClassificationOfGuideInformationRespVO>> getClassificationOfGuideInformationPage(@Valid ClassificationOfGuideInformationPageReqVO pageReqVO) {
        PageResult<ClassificationOfGuideInformationDO> pageResult = classificationOfGuideInformationService.getClassificationOfGuideInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClassificationOfGuideInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指南信息分类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-guide-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClassificationOfGuideInformationExcel(@Valid ClassificationOfGuideInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClassificationOfGuideInformationDO> list = classificationOfGuideInformationService.getClassificationOfGuideInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指南信息分类.xls", "数据", ClassificationOfGuideInformationRespVO.class,
                        BeanUtils.toBean(list, ClassificationOfGuideInformationRespVO.class));
    }

}