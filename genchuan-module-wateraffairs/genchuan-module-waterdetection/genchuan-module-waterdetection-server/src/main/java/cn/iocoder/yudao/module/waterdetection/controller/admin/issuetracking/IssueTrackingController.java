package cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking;

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

import cn.iocoder.yudao.module.waterdetection.controller.admin.issuetracking.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.issuetracking.IssueTrackingDO;
import cn.iocoder.yudao.module.waterdetection.service.issuetracking.IssueTrackingService;

@Tag(name = "管理后台 - 问题上报与闭环跟踪")
@RestController
@RequestMapping("/waterdetection/issue-tracking")
@Validated
public class IssueTrackingController {

    @Resource
    private IssueTrackingService issueTrackingService;

    @PostMapping("/create")
    @Operation(summary = "创建问题上报与闭环跟踪")
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:create')")
    public CommonResult<Long> createIssueTracking(@Valid @RequestBody IssueTrackingSaveReqVO createReqVO) {
        return success(issueTrackingService.createIssueTracking(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新问题上报与闭环跟踪")
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:update')")
    public CommonResult<Boolean> updateIssueTracking(@Valid @RequestBody IssueTrackingSaveReqVO updateReqVO) {
        issueTrackingService.updateIssueTracking(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除问题上报与闭环跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:delete')")
    public CommonResult<Boolean> deleteIssueTracking(@RequestParam("id") Long id) {
        issueTrackingService.deleteIssueTracking(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得问题上报与闭环跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:query')")
    public CommonResult<IssueTrackingRespVO> getIssueTracking(@RequestParam("id") Long id) {
        IssueTrackingDO issueTracking = issueTrackingService.getIssueTracking(id);
        return success(BeanUtils.toBean(issueTracking, IssueTrackingRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得问题上报与闭环跟踪分页")
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:query')")
    public CommonResult<PageResult<IssueTrackingRespVO>> getIssueTrackingPage(@Valid IssueTrackingPageReqVO pageReqVO) {
        PageResult<IssueTrackingDO> pageResult = issueTrackingService.getIssueTrackingPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, IssueTrackingRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出问题上报与闭环跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('waterdetection:issue-tracking:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportIssueTrackingExcel(@Valid IssueTrackingPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<IssueTrackingDO> list = issueTrackingService.getIssueTrackingPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "问题上报与闭环跟踪.xls", "数据", IssueTrackingRespVO.class,
                        BeanUtils.toBean(list, IssueTrackingRespVO.class));
    }

}