package cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.faq.FaqDO;
import cn.iocoder.yudao.module.datacenter.service.operationguide.faq.FaqService;

@Tag(name = "管理后台 - 常见问题解答")
@RestController
@RequestMapping("/datacenter/faq")
@Validated
public class FaqController {

    @Resource
    private FaqService faqService;

    @PostMapping("/create")
    @Operation(summary = "创建常见问题解答")
    @PreAuthorize("@ss.hasPermission('datacenter:faq:create')")
    public CommonResult<Long> createFaq(@Valid @RequestBody FaqSaveReqVO createReqVO) {
        return success(faqService.createFaq(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新常见问题解答")
    @PreAuthorize("@ss.hasPermission('datacenter:faq:update')")
    public CommonResult<Boolean> updateFaq(@Valid @RequestBody FaqSaveReqVO updateReqVO) {
        faqService.updateFaq(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除常见问题解答")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:faq:delete')")
    public CommonResult<Boolean> deleteFaq(@RequestParam("id") Long id) {
        faqService.deleteFaq(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得常见问题解答")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:faq:query')")
    public CommonResult<FaqRespVO> getFaq(@RequestParam("id") Long id) {
        FaqDO faq = faqService.getFaq(id);
        return success(BeanUtils.toBean(faq, FaqRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得常见问题解答分页")
    @PreAuthorize("@ss.hasPermission('datacenter:faq:query')")
    public CommonResult<PageResult<FaqRespVO>> getFaqPage(@Valid FaqPageReqVO pageReqVO) {
        PageResult<FaqDO> pageResult = faqService.getFaqPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, FaqRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出常见问题解答 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:faq:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportFaqExcel(@Valid FaqPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<FaqDO> list = faqService.getFaqPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "常见问题解答.xls", "数据", FaqRespVO.class,
                        BeanUtils.toBean(list, FaqRespVO.class));
    }

}