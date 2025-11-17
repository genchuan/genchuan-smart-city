package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicaisvc.PublicAiSvcDO;
import cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicaisvc.PublicAiSvcService;

@Tag(name = "管理后台 - 智能客服知识库")
@RestController
@RequestMapping("/datacenter/public-ai-svc")
@Validated
public class PublicAiSvcController {

    @Resource
    private PublicAiSvcService publicAiSvcService;

    @PostMapping("/create")
    @Operation(summary = "创建智能客服知识库")
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:create')")
    public CommonResult<Long> createPublicAiSvc(@Valid @RequestBody PublicAiSvcSaveReqVO createReqVO) {
        return success(publicAiSvcService.createPublicAiSvc(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新智能客服知识库")
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:update')")
    public CommonResult<Boolean> updatePublicAiSvc(@Valid @RequestBody PublicAiSvcSaveReqVO updateReqVO) {
        publicAiSvcService.updatePublicAiSvc(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除智能客服知识库")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:delete')")
    public CommonResult<Boolean> deletePublicAiSvc(@RequestParam("id") Long id) {
        publicAiSvcService.deletePublicAiSvc(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得智能客服知识库")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:query')")
    public CommonResult<PublicAiSvcRespVO> getPublicAiSvc(@RequestParam("id") Long id) {
        PublicAiSvcDO publicAiSvc = publicAiSvcService.getPublicAiSvc(id);
        return success(BeanUtils.toBean(publicAiSvc, PublicAiSvcRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得智能客服知识库分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:query')")
    public CommonResult<PageResult<PublicAiSvcRespVO>> getPublicAiSvcPage(@Valid PublicAiSvcPageReqVO pageReqVO) {
        PageResult<PublicAiSvcDO> pageResult = publicAiSvcService.getPublicAiSvcPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicAiSvcRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出智能客服知识库 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-ai-svc:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicAiSvcExcel(@Valid PublicAiSvcPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicAiSvcDO> list = publicAiSvcService.getPublicAiSvcPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "智能客服知识库.xls", "数据", PublicAiSvcRespVO.class,
                        BeanUtils.toBean(list, PublicAiSvcRespVO.class));
    }

}