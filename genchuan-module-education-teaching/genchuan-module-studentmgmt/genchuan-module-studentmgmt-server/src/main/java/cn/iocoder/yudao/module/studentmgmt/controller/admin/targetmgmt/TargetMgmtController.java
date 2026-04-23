package cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.targetmgmt.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.targetmgmt.TargetMgmtDO;
import cn.iocoder.yudao.module.studentmgmt.service.targetmgmt.TargetMgmtService;

@Tag(name = "学生管理后台 - 指标管理")
@RestController
@RequestMapping("/studentmgmt/target-mgmt")
@Validated
public class TargetMgmtController {

    @Resource
    private TargetMgmtService targetMgmtService;

    @PostMapping("/create")
    @Operation(summary = "创建指标管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:create')")
    public CommonResult<Long> createTargetMgmt(@Valid @RequestBody TargetMgmtSaveReqVO createReqVO) {
        return success(targetMgmtService.createTargetMgmt(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新指标管理")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:update')")
    public CommonResult<Boolean> updateTargetMgmt(@Valid @RequestBody TargetMgmtSaveReqVO updateReqVO) {
        targetMgmtService.updateTargetMgmt(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除指标管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:delete')")
    public CommonResult<Boolean> deleteTargetMgmt(@RequestParam("id") Long id) {
        targetMgmtService.deleteTargetMgmt(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除指标管理")
                @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:delete')")
    public CommonResult<Boolean> deleteTargetMgmtList(@RequestParam("ids") List<Long> ids) {
        targetMgmtService.deleteTargetMgmtListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得指标管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtRespVO> getTargetMgmt(@RequestParam("id") Long id) {
        TargetMgmtDO targetMgmt = targetMgmtService.getTargetMgmt(id);
        return success(BeanUtils.toBean(targetMgmt, TargetMgmtRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得指标管理分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<PageResult<TargetMgmtRespVO>> getTargetMgmtPage(@Valid TargetMgmtPageReqVO pageReqVO) {
        PageResult<TargetMgmtDO> pageResult = targetMgmtService.getTargetMgmtPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, TargetMgmtRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出指标管理 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportTargetMgmtExcel(@Valid TargetMgmtPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<TargetMgmtDO> list = targetMgmtService.getTargetMgmtPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "指标管理.xls", "数据", TargetMgmtRespVO.class,
                        BeanUtils.toBean(list, TargetMgmtRespVO.class));
    }

    @PutMapping("/config")
    @Operation(summary = "配置")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:config')")
    public CommonResult<Boolean> config(@Valid @RequestBody TargetMgmtConfigReqVO reqVO) {
        boolean isSuccess = targetMgmtService.config(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/enable")
    @Operation(summary = "启用")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:enable')")
    public CommonResult<Boolean> enable(@Valid @RequestBody TargetMgmtEnableReqVO reqVO) {
        boolean isSuccess = targetMgmtService.enable(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/disable")
    @Operation(summary = "停用")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:disable')")
    public CommonResult<Boolean> disable(@Valid @RequestBody TargetMgmtEnableReqVO reqVO) {
        boolean isSuccess = targetMgmtService.disable(reqVO);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "德育指标配置看板")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtChartRespVO> chart(@Valid TargetMgmtChartReqVO reqVO) {
        TargetMgmtChartRespVO vo = targetMgmtService.chart(reqVO);
        return success(vo);
    }
    @GetMapping("/chart/targetIndex")
    @Operation(summary = "指标核心指标统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:target-mgmt:query')")
    public CommonResult<TargetMgmtChartIndexRespVO> targetIndex() {
        TargetMgmtChartIndexRespVO vo = targetMgmtService.targetIndex();
        return success(vo);
    }
}