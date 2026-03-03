package cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.detail.PublicInstitutionDetailDO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.service.publicinstitution.publicinstitution.PublicInstitutionService;

@Tag(name = "环境卫生管理 - 公共机构")
@RestController
@RequestMapping("/envirhealth/public-institution")
@Validated
public class PublicInstitutionController {

    @Resource
    private PublicInstitutionService publicInstitutionService;

    @PostMapping("/create")
    @Operation(summary = "创建公共机构")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:create')")
    public CommonResult<Long> createPublicInstitution(@Valid @RequestBody PublicInstitutionSaveReqVO createReqVO) {
        return success(publicInstitutionService.createPublicInstitution(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公共机构")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:update')")
    public CommonResult<Boolean> updatePublicInstitution(@Valid @RequestBody PublicInstitutionSaveReqVO updateReqVO) {
        publicInstitutionService.updatePublicInstitution(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公共机构")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:delete')")
    public CommonResult<Boolean> deletePublicInstitution(@RequestParam("id") Long id) {
        publicInstitutionService.deletePublicInstitution(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公共机构")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:query')")
    public CommonResult<PublicInstitutionRespVO> getPublicInstitution(@RequestParam("id") Long id) {
        PublicInstitutionDO publicInstitution = publicInstitutionService.getPublicInstitution(id);
        return success(BeanUtils.toBean(publicInstitution, PublicInstitutionRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公共机构分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:query')")
    public CommonResult<PageResult<PublicInstitutionRespVO>> getPublicInstitutionPage(@Valid PublicInstitutionPageReqVO pageReqVO) {
        PageResult<PublicInstitutionDO> pageResult = publicInstitutionService.getPublicInstitutionPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicInstitutionRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公共机构 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicInstitutionExcel(@Valid PublicInstitutionPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicInstitutionDO> list = publicInstitutionService.getPublicInstitutionPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公共机构.xls", "数据", PublicInstitutionRespVO.class,
                        BeanUtils.toBean(list, PublicInstitutionRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公共机构详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:public-institution:query')")
    public CommonResult<PageResult<PublicInstitutionDetailDO>> getPublicInstitutionDetailPage(
            @Valid PublicInstitutionPageReqVO pageReqVO) {
        PageResult<PublicInstitutionDetailDO> pageResult =
                publicInstitutionService.getPublicInstitutionDetailPage(pageReqVO);

        return success(pageResult);
    }
}