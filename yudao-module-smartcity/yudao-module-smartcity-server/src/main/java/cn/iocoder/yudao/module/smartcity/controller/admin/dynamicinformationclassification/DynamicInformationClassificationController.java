package cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.DynamicInformationClassificationPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.DynamicInformationClassificationRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.dynamicinformationclassification.vo.DynamicInformationClassificationSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.dynamicinformationclassification.DynamicInformationClassificationDO;
import cn.iocoder.yudao.module.smartcity.service.dynamicinformationclassification.DynamicInformationClassificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 动态信息分类")
@RestController
@RequestMapping("/smartcity/dynamic-information-classification")
@Validated
public class DynamicInformationClassificationController {

    @Resource
    private DynamicInformationClassificationService dynamicInformationClassificationService;

    @PostMapping("/create")
    @Operation(summary = "创建动态信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:create')")
    public CommonResult<Long> createDynamicInformationClassification(@Valid @RequestBody DynamicInformationClassificationSaveReqVO createReqVO) {
        return success(dynamicInformationClassificationService.createDynamicInformationClassification(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新动态信息分类")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:update')")
    public CommonResult<Boolean> updateDynamicInformationClassification(@Valid @RequestBody DynamicInformationClassificationSaveReqVO updateReqVO) {
        dynamicInformationClassificationService.updateDynamicInformationClassification(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除动态信息分类")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:delete')")
    public CommonResult<Boolean> deleteDynamicInformationClassification(@RequestParam("id") Long id) {
        dynamicInformationClassificationService.deleteDynamicInformationClassification(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得动态信息分类")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:query')")
    public CommonResult<DynamicInformationClassificationRespVO> getDynamicInformationClassification(@RequestParam("id") Long id) {
        DynamicInformationClassificationDO dynamicInformationClassification = dynamicInformationClassificationService.getDynamicInformationClassification(id);
        return success(BeanUtils.toBean(dynamicInformationClassification, DynamicInformationClassificationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得动态信息分类分页")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:query')")
    public CommonResult<PageResult<DynamicInformationClassificationRespVO>> getDynamicInformationClassificationPage(@Valid DynamicInformationClassificationPageReqVO pageReqVO) {
        PageResult<DynamicInformationClassificationDO> pageResult = dynamicInformationClassificationService.getDynamicInformationClassificationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DynamicInformationClassificationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出动态信息分类 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:dynamic-information-classification:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDynamicInformationClassificationExcel(@Valid DynamicInformationClassificationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DynamicInformationClassificationDO> list = dynamicInformationClassificationService.getDynamicInformationClassificationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "动态信息分类.xls", "数据", DynamicInformationClassificationRespVO.class,
                        BeanUtils.toBean(list, DynamicInformationClassificationRespVO.class));
    }

}