package cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.PublicFeedbackSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicfeedback.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicfeedback.PublicFeedbackDO;
import cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicfeedback.PublicFeedbackService;

@Tag(name = "管理后台 - 意见反馈")
@RestController
@RequestMapping("/datacenter/public-feedback")
@Validated
public class PublicFeedbackController {

    @Resource
    private PublicFeedbackService publicFeedbackService;

    @PostMapping("/create")
    @Operation(summary = "创建意见反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:create')")
    public CommonResult<Long> createPublicFeedback(@Valid @RequestBody PublicFeedbackSaveReqVO createReqVO) {
        return success(publicFeedbackService.createPublicFeedback(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新意见反馈")
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:update')")
    public CommonResult<Boolean> updatePublicFeedback(@Valid @RequestBody PublicFeedbackSaveReqVO updateReqVO) {
        publicFeedbackService.updatePublicFeedback(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除意见反馈")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:delete')")
    public CommonResult<Boolean> deletePublicFeedback(@RequestParam("id") Long id) {
        publicFeedbackService.deletePublicFeedback(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得意见反馈")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:query')")
    public CommonResult<PublicFeedbackRespVO> getPublicFeedback(@RequestParam("id") Long id) {
        PublicFeedbackDO publicFeedback = publicFeedbackService.getPublicFeedback(id);
        return success(BeanUtils.toBean(publicFeedback, PublicFeedbackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得意见反馈分页")
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:query')")
    public CommonResult<PageResult<PublicFeedbackRespVO>> getPublicFeedbackPage(@Valid PublicFeedbackPageReqVO pageReqVO) {
        PageResult<PublicFeedbackDO> pageResult = publicFeedbackService.getPublicFeedbackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PublicFeedbackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出意见反馈 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:public-feedback:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPublicFeedbackExcel(@Valid PublicFeedbackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PublicFeedbackDO> list = publicFeedbackService.getPublicFeedbackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "意见反馈.xls", "数据", PublicFeedbackRespVO.class,
                        BeanUtils.toBean(list, PublicFeedbackRespVO.class));
    }

}