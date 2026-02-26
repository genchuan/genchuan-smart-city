package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutioninspection.InstitutionInspectionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.InstitutionInspectionDetailDO;
import org.springframework.web.bind.annotation.*;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionInspectionDO;
import cn.iocoder.yudao.module.envirhealth.service.publicinstitution.institutioninspection.InstitutionInspectionService;

@Tag(name = "环境卫生管理 - 公共机构核查")
@RestController
@RequestMapping("/envirhealth/institution-inspection")
@Validated
public class InstitutionInspectionController {

    @Resource
    private InstitutionInspectionService institutionInspectionService;

    @PostMapping("/create")
    @Operation(summary = "创建公共机构核查")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:create')")
    public CommonResult<Long> createInstitutionInspection(@Valid @RequestBody InstitutionInspectionSaveReqVO createReqVO) {
        return success(institutionInspectionService.createInstitutionInspection(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公共机构核查")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:update')")
    public CommonResult<Boolean> updateInstitutionInspection(@Valid @RequestBody InstitutionInspectionSaveReqVO updateReqVO) {
        institutionInspectionService.updateInstitutionInspection(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公共机构核查")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:delete')")
    public CommonResult<Boolean> deleteInstitutionInspection(@RequestParam("id") Long id) {
        institutionInspectionService.deleteInstitutionInspection(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公共机构核查")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:query')")
    public CommonResult<InstitutionInspectionRespVO> getInstitutionInspection(@RequestParam("id") Long id) {
        InstitutionInspectionDO institutionInspection = institutionInspectionService.getInstitutionInspection(id);
        return success(BeanUtils.toBean(institutionInspection, InstitutionInspectionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公共机构核查分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:query')")
    public CommonResult<PageResult<InstitutionInspectionRespVO>> getInstitutionInspectionPage(@Valid InstitutionInspectionPageReqVO pageReqVO) {
        PageResult<InstitutionInspectionDO> pageResult = institutionInspectionService.getInstitutionInspectionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InstitutionInspectionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公共机构核查 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInstitutionInspectionExcel(@Valid InstitutionInspectionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InstitutionInspectionDO> list = institutionInspectionService.getInstitutionInspectionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公共机构核查.xls", "数据", InstitutionInspectionRespVO.class,
                        BeanUtils.toBean(list, InstitutionInspectionRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公共机构核查详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:institution-inspection:query')")
    public CommonResult<PageResult<InstitutionInspectionDetailDO>> getPublicToiletDetailPage(
            @Valid InstitutionInspectionPageReqVO pageReqVO) {
        PageResult<InstitutionInspectionDetailDO> pageResult =
                institutionInspectionService.getInstitutionInspectionDetailPage(pageReqVO);

        return success(pageResult);
    }
}