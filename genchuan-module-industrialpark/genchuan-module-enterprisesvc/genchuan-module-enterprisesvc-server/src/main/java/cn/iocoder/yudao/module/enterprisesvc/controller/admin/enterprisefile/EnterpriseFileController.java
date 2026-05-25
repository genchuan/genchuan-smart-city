package cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile;

import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
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

import cn.iocoder.yudao.module.enterprisesvc.controller.admin.enterprisefile.vo.*;
import cn.iocoder.yudao.module.enterprisesvc.dal.dataobject.enterprisefile.EnterpriseFileDO;
import cn.iocoder.yudao.module.enterprisesvc.service.enterprisefile.EnterpriseFileService;

@Tag(name = "管理后台 - 企业档案")
@RestController
@RequestMapping("/enterprisesvc/enterprise-file")
@Validated
public class EnterpriseFileController {

    @Resource
    private EnterpriseFileService enterpriseFileService;

    @PostMapping("/create-test")
    @Operation(summary = "创建企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:create')")
    public CommonResult<Long> createEnterprise(@Valid @RequestBody EnterpriseFileCreateReqVO createReqVO) {
        return success(enterpriseFileService.createEnterpriseFile(createReqVO));
    }

    @PostMapping("/create")
    @Operation(summary = "企业档案填报")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:create')")
    public CommonResult<Long> createEnterpriseFile(@Valid @RequestBody EnterpriseFileCreateReqVO createReqVO) {
        return success(enterpriseFileService.createEnterpriseFile(createReqVO));
    }

    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    @Operation(summary = "上传企业资质证明文件")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:upload')")
    public CommonResult<String> uploadEnterpriseFile(@Valid @ModelAttribute EnterpriseFileUploadReqVO uploadReqVO) {
        return success(enterpriseFileService.uploadEnterpriseFile(uploadReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "修改企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:update')")
    public CommonResult<Boolean> updateEnterpriseFile(@Valid @RequestBody EnterpriseFileSaveReqVO updateReqVO) {
        enterpriseFileService.updateEnterpriseFile(updateReqVO);
        return success(true);
    }

    @PutMapping("/update-enterprise")
    @Operation(summary = "维护企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:update')")
    public CommonResult<Boolean> updateEnterprise(@Valid @RequestBody EnterpriseFileSaveReqVO updateReqVO) {
        enterpriseFileService.updateEnterpriseFile(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除企业档案")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:delete')")
    public CommonResult<Boolean> deleteEnterpriseFile(@RequestParam("id") Long id) {
        enterpriseFileService.deleteEnterpriseFile(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除企业档案")
                @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:delete')")
    public CommonResult<Boolean> deleteEnterpriseFileList(@RequestParam("ids") List<Long> ids) {
        enterpriseFileService.deleteEnterpriseFileListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得企业档案")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:query')")
    public CommonResult<EnterpriseFileRespVO> getEnterpriseFile(@RequestParam("id") Long id) {
        EnterpriseFileDO enterpriseFile = enterpriseFileService.getEnterpriseFile(id);
        return success(BeanUtils.toBean(enterpriseFile, EnterpriseFileRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得企业档案分页")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:query')")
    public CommonResult<PageResult<EnterpriseFileRespVO>> getEnterpriseFilePage(@Valid EnterpriseFilePageReqVO pageReqVO) {
        PageResult<EnterpriseFileDO> pageResult = enterpriseFileService.getEnterpriseFilePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EnterpriseFileRespVO.class));
    }

    @PutMapping("/audit")
    @Operation(summary = "审核企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:audit')")
    public CommonResult<Boolean> auditEnterpriseFile(@Valid @RequestBody EnterpriseFileAuditReqVO auditReqVO) {
        enterpriseFileService.auditEnterpriseFile(auditReqVO.getId());
        return success(true);
    }

    @PutMapping("/reject")
    @Operation(summary = "驳回企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:reject')")
    public CommonResult<Boolean> rejectEnterpriseFile(@Valid @RequestBody EnterpriseFileRejectReqVO rejectReqVO) {
        enterpriseFileService.rejectEnterpriseFile(rejectReqVO);
        return success(true);
    }

    @PutMapping("/resubmit")
    @Operation(summary = "重新提交企业档案")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:resubmit')")
    public CommonResult<Boolean> resubmitEnterpriseFile(@Valid @RequestBody EnterpriseFileResubmitReqVO resubmitReqVO) {
        enterpriseFileService.resubmitEnterpriseFile(resubmitReqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获取企业档案分布态势图表")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:query')")
    public CommonResult<EnterpriseFileChartRespVO> getEnterpriseFileChart() {
        return success(enterpriseFileService.getEnterpriseFileChart());
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出企业档案 Excel")
    @PreAuthorize("@ss.hasPermission('enterprisesvc:enterprise-file:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEnterpriseFileExcel(@Valid EnterpriseFilePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EnterpriseFileDO> list = enterpriseFileService.getEnterpriseFilePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "企业档案.xls", "数据", EnterpriseFileRespVO.class,
                        BeanUtils.toBean(list, EnterpriseFileRespVO.class));
    }

}
