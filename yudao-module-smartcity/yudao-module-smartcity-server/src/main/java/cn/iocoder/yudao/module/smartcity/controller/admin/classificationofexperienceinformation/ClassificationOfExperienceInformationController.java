package cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.classificationofexperienceinformation.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.classificationofexperienceinformation.ClassificationOfExperienceInformationDO;
import cn.iocoder.yudao.module.smartcity.service.classificationofexperienceinformation.ClassificationOfExperienceInformationService;

@Tag(name = "管理后台 - 经验信息分类")
@RestController
@RequestMapping("/smartcity/classification-of-experience-information")
@Validated
public class ClassificationOfExperienceInformationController {

    @Resource
    private ClassificationOfExperienceInformationService classificationOfExperienceInformationService;

    @PostMapping("/create")
    @Operation(summary = "创建经验信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:create')")
    public CommonResult<Long> createClassificationOfExperienceInformation(@Valid @RequestBody ClassificationOfExperienceInformationSaveReqVO createReqVO) {
        return success(classificationOfExperienceInformationService.createClassificationOfExperienceInformation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新经验信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:update')")
    public CommonResult<Boolean> updateClassificationOfExperienceInformation(@Valid @RequestBody ClassificationOfExperienceInformationSaveReqVO updateReqVO) {
        classificationOfExperienceInformationService.updateClassificationOfExperienceInformation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除经验信息分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:delete')")
    public CommonResult<Boolean> deleteClassificationOfExperienceInformation(@RequestParam("id") Long id) {
        classificationOfExperienceInformationService.deleteClassificationOfExperienceInformation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得经验信息分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:query')")
    public CommonResult<ClassificationOfExperienceInformationRespVO> getClassificationOfExperienceInformation(@RequestParam("id") Long id) {
        ClassificationOfExperienceInformationDO classificationOfExperienceInformation = classificationOfExperienceInformationService.getClassificationOfExperienceInformation(id);
        return success(BeanUtils.toBean(classificationOfExperienceInformation, ClassificationOfExperienceInformationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得经验信息分类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:query')")
    public CommonResult<PageResult<ClassificationOfExperienceInformationRespVO>> getClassificationOfExperienceInformationPage(@Valid ClassificationOfExperienceInformationPageReqVO pageReqVO) {
        PageResult<ClassificationOfExperienceInformationDO> pageResult = classificationOfExperienceInformationService.getClassificationOfExperienceInformationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ClassificationOfExperienceInformationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出经验信息分类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:classification-of-experience-information:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportClassificationOfExperienceInformationExcel(@Valid ClassificationOfExperienceInformationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ClassificationOfExperienceInformationDO> list = classificationOfExperienceInformationService.getClassificationOfExperienceInformationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "经验信息分类.xls", "数据", ClassificationOfExperienceInformationRespVO.class,
                        BeanUtils.toBean(list, ClassificationOfExperienceInformationRespVO.class));
    }

}