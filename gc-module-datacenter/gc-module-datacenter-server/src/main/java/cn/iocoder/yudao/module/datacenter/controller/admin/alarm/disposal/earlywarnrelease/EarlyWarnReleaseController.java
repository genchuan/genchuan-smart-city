package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleasePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleaseRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnrelease.vo.EarlyWarnReleaseSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnrelease.EarlyWarnReleaseDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.disposal.earlywarnrelease.EarlyWarnReleaseService;

@Tag(name = "管理后台 - 预警告警解除")
@RestController
@RequestMapping("/datacenter/early-warn-release")
@Validated
public class EarlyWarnReleaseController {

    @Resource
    private EarlyWarnReleaseService earlyWarnReleaseService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警解除")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:create')")
    public CommonResult<Long> createEarlyWarnRelease(@Valid @RequestBody EarlyWarnReleaseSaveReqVO createReqVO) {
        return success(earlyWarnReleaseService.createEarlyWarnRelease(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警解除")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:update')")
    public CommonResult<Boolean> updateEarlyWarnRelease(@Valid @RequestBody EarlyWarnReleaseSaveReqVO updateReqVO) {
        earlyWarnReleaseService.updateEarlyWarnRelease(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警解除")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:delete')")
    public CommonResult<Boolean> deleteEarlyWarnRelease(@RequestParam("id") Long id) {
        earlyWarnReleaseService.deleteEarlyWarnRelease(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警解除")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:query')")
    public CommonResult<EarlyWarnReleaseRespVO> getEarlyWarnRelease(@RequestParam("id") Long id) {
        EarlyWarnReleaseDO earlyWarnRelease = earlyWarnReleaseService.getEarlyWarnRelease(id);
        return success(BeanUtils.toBean(earlyWarnRelease, EarlyWarnReleaseRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警解除分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:query')")
    public CommonResult<PageResult<EarlyWarnReleaseRespVO>> getEarlyWarnReleasePage(@Valid EarlyWarnReleasePageReqVO pageReqVO) {
        PageResult<EarlyWarnReleaseDO> pageResult = earlyWarnReleaseService.getEarlyWarnReleasePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnReleaseRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警解除 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-release:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnReleaseExcel(@Valid EarlyWarnReleasePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnReleaseDO> list = earlyWarnReleaseService.getEarlyWarnReleasePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警解除.xls", "数据", EarlyWarnReleaseRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnReleaseRespVO.class));
    }

}