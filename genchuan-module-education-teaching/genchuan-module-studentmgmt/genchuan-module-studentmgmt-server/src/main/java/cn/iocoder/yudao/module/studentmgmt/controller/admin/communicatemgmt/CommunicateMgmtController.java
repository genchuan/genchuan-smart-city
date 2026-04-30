package cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.communicatemgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.communicatemgmt.CommunicateMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.communicatemgmt.CommunicateMgmtService;

@Tag(name = "学生管理后台 - 沟通管理")
@RestController
@RequestMapping("/studentmgmt/communicate-mgmt")
@Validated
public class CommunicateMgmtController {

    @Resource
    private CommunicateMgmtService communicateMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建沟通管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:create')")
    public CommonResult<Long> createCommunicateMgmt(@Valid @RequestBody CommunicateMgmtSaveReqVO createReqVO) {
        return success(communicateMgmtService.createCommunicateMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新沟通管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:update')")
    public CommonResult<Boolean> updateCommunicateMgmt(@Valid @RequestBody CommunicateMgmtSaveReqVO updateReqVO) {
        communicateMgmtService.updateCommunicateMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除沟通管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:delete')")
    public CommonResult<Boolean> deleteCommunicateMgmt(@RequestParam("id") Long id) {
        communicateMgmtService.deleteCommunicateMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除沟通管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:delete')")
    public CommonResult<Boolean> deleteCommunicateMgmtList(@RequestParam("ids") List<Long> ids) {
        communicateMgmtService.deleteCommunicateMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得沟通管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:query')")
    public CommonResult<CommunicateMgmtRespVO> getCommunicateMgmt(@RequestParam("id") Long id) {
        CommunicateMgmtDO communicateMgmt = communicateMgmtService.getCommunicateMgmt(id);
        return success(BeanUtils.toBean(communicateMgmt, CommunicateMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得沟通管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:query')")
    public CommonResult<PageResult<CommunicateMgmtRespVO>> getCommunicateMgmtPage(@Valid CommunicateMgmtPageReqVO pageReqVO) {
        PageResult<CommunicateMgmtDO> pageResult = communicateMgmtService.getCommunicateMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CommunicateMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出沟通管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCommunicateMgmtExcel(@Valid CommunicateMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CommunicateMgmtDO> list = communicateMgmtService.getCommunicateMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "沟通管理.xls", "数据", CommunicateMgmtRespVO.class,
                        BeanUtils.toBean(list, CommunicateMgmtRespVO.class));
    }

    @PutMapping("/publish")
    @Operation(summary = "发布")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:publish')")
    public CommonResult<Boolean> publish(@Valid @RequestBody CommunicateMgmtPublishReqVO reqVO) {
        return success(communicateMgmtService.publish(reqVO));
    }

    @PutMapping("/feedback")
    @Operation(summary = "反馈")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:feedback')")
    public CommonResult<Boolean> feedback(@Valid @RequestBody CommunicateMgmtFeedbackReqVO reqVO) {
        return success(communicateMgmtService.feedback(reqVO));
    }

    @PutMapping("/reply")
    @Operation(summary = "反馈")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:reply')")
    public CommonResult<Boolean> reply(@Valid @RequestBody CommunicateMgmtReplyReqVO reqVO) {
        return success(communicateMgmtService.reply(reqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "家校协同互动看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:chart')")
    public CommonResult<CommunicateMgmtChartRespVO> chart(@Valid CommunicateMgmtChartReqVO reqVO) {
        return success(communicateMgmtService.chart(reqVO));
    }

    @PutMapping("/interactIndex")
    @Operation(summary = "互动核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:communicate-mgmt:chart')")
    public CommonResult<CommunicateInteractIndexRespVO> interactIndex(@Valid CommunicateMgmtChartReqVO reqVO) {
        return success(communicateMgmtService.interactIndex(reqVO));
    }

}
