package cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.PublicConsultationSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicconsultation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicconsultation.PublicConsultationDO;
import cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publicconsultation.PublicConsultationService;

@Tag(name = "管理后台 - 咨询建议")
@RestController
@RequestMapping("/datacenter/public-consultation")
@Validated
public class PublicConsultationController {

    @Resource
    private PublicConsultationService publicConsultationService;

    @PostMapping("/create")
    @Operation(summary = "创建咨询建议")
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:create')")
    public CommonResult<Long> createPublicConsultation(@Valid @RequestBody PublicConsultationSaveReqVO createReqVO) {
        return success(publicConsultationService.createPublicConsultation(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新咨询建议")
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:update')")
    public CommonResult<Boolean> updatePublicConsultation(@Valid @RequestBody PublicConsultationSaveReqVO updateReqVO) {
        publicConsultationService.updatePublicConsultation(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除咨询建议")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:delete')")
    public CommonResult<Boolean> deletePublicConsultation(@RequestParam("id") Long id) {
        publicConsultationService.deletePublicConsultation(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得咨询建议")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:query')")
    public CommonResult<PublicConsultationRespVO> getPublicConsultation(@RequestParam("id") Long id) {
        PublicConsultationDO publicConsultation = publicConsultationService.getPublicConsultation(id);
        return success(BeanUtils.toBean(publicConsultation, PublicConsultationRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得咨询建议分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:query')")
    public CommonResult<PageResult<PublicConsultationRespVO>> getPublicConsultationPage(@Valid PublicConsultationPageReqVO pageReqVO) {
        PageResult<PublicConsultationDO> pageResult = publicConsultationService.getPublicConsultationPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicConsultationRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出咨询建议 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-consultation:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicConsultationExcel(@Valid PublicConsultationPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicConsultationDO> list = publicConsultationService.getPublicConsultationPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "咨询建议.xls", "数据", PublicConsultationRespVO.class,
                        BeanUtils.toBean(list, PublicConsultationRespVO.class));
    }

}