package cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype.vo.InstitutionTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype.vo.InstitutionTypeRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.institutiontype.vo.InstitutionTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.institutiontype.InstitutionTypeDO;
import cn.iocoder.yudao.module.envirhealth.service.institutiontype.InstitutionTypeService;
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

@Tag(name = "字典表 - 机构类型")
@RestController
@RequestMapping("/envirhealth/institution-type")
@Validated
public class InstitutionTypeController {

    @Resource
    private InstitutionTypeService institutionTypeService;

    @PostMapping("/create")
    @Operation(summary = "创建机构类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:create')")
    public CommonResult<Long> createInstitutionType(@Valid @RequestBody InstitutionTypeSaveReqVO createReqVO) {
        return success(institutionTypeService.createInstitutionType(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新机构类型字典")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:update')")
    public CommonResult<Boolean> updateInstitutionType(@Valid @RequestBody InstitutionTypeSaveReqVO updateReqVO) {
        institutionTypeService.updateInstitutionType(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除机构类型字典")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:delete')")
    public CommonResult<Boolean> deleteInstitutionType(@RequestParam("id") Long id) {
        institutionTypeService.deleteInstitutionType(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得机构类型字典")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:query')")
    public CommonResult<InstitutionTypeRespVO> getInstitutionType(@RequestParam("id") Long id) {
        InstitutionTypeDO institutionType = institutionTypeService.getInstitutionType(id);
        return success(BeanUtils.toBean(institutionType, InstitutionTypeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得机构类型字典分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:query')")
    public CommonResult<PageResult<InstitutionTypeRespVO>> getInstitutionTypePage(@Valid InstitutionTypePageReqVO pageReqVO) {
        PageResult<InstitutionTypeDO> pageResult = institutionTypeService.getInstitutionTypePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InstitutionTypeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出机构类型字典 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-type:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstitutionTypeExcel(@Valid InstitutionTypePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstitutionTypeDO> list = institutionTypeService.getInstitutionTypePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "机构类型字典.xls", "数据", InstitutionTypeRespVO.class,
                        BeanUtils.toBean(list, InstitutionTypeRespVO.class));
    }

    /**
     * 获得机构类型字典下拉框选项
     * 前端下拉框直接调用该接口
     */
    @GetMapping("/options")
    @Operation(summary = "获得机构类型(下拉框)")
    @PreAuthorize("@ss.hasPermission('health:institution-type:query')")
    public CommonResult<List<OptionVO>> getInstitutionTypeOptions() {
        return success(institutionTypeService.getInstitutionTypeOptions());
    }

}
