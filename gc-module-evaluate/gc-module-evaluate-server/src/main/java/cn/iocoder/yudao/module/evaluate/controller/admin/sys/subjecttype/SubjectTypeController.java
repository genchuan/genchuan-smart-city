package cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypePageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypeRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.subjecttype.vo.SubjectTypeSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.module.evaluate.service.subjecttype.SubjectTypeService;
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

@Tag(name = "管理后台 - 主体类型字典")
@RestController
@RequestMapping("/evaluate/subject-type")
@Validated
public class SubjectTypeController {

    @Resource
    private SubjectTypeService subjectTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建主体类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:create')")
    public CommonResult<Long> createSubjectType(@Valid @RequestBody SubjectTypeSaveReqVO createReqVO) {
        return success(subjectTypeService.createSubjectType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新主体类型字典")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:update')")
    public CommonResult<Boolean> updateSubjectType(@Valid @RequestBody SubjectTypeSaveReqVO updateReqVO) {
        subjectTypeService.updateSubjectType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除主体类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:delete')")
    public CommonResult<Boolean> deleteSubjectType(@RequestParam("id") Long id) {
        subjectTypeService.deleteSubjectType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得主体类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:query')")
    public CommonResult<SubjectTypeRespVO> getSubjectType(@RequestParam("id") Long id) {
        SubjectTypeDO subjectType = subjectTypeService.getSubjectType(id);
        return success(BeanUtils.toBean(subjectType, SubjectTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得主体类型字典分页")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:query')")
    public CommonResult<PageResult<SubjectTypeRespVO>> getSubjectTypePage(@Valid SubjectTypePageReqVO pageReqVO) {
        PageResult<SubjectTypeDO> pageResult = subjectTypeService.getSubjectTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SubjectTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出主体类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:subject-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSubjectTypeExcel(@Valid SubjectTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SubjectTypeDO> list = subjectTypeService.getSubjectTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "主体类型字典.xls", "数据", SubjectTypeRespVO.class,
                        BeanUtils.toBean(list, SubjectTypeRespVO.class));
    }

}