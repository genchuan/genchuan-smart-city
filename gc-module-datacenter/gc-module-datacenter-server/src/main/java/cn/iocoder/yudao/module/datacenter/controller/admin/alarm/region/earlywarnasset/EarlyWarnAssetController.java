package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnasset.vo.EarlyWarnAssetSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnasset.EarlyWarnAssetDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.region.earlywarnasset.EarlyWarnAssetService;

@Tag(name = "管理后台 - 按资产分域预警告警统计")
@RestController
@RequestMapping("/datacenter/early-warn-asset")
@Validated
public class EarlyWarnAssetController {

    @Resource
    private EarlyWarnAssetService earlyWarnAssetService;

    @PostMapping("/create")
    @Operation(summary = "创建按资产分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:create')")
    public CommonResult<Long> createEarlyWarnAsset(@Valid @RequestBody EarlyWarnAssetSaveReqVO createReqVO) {
        return success(earlyWarnAssetService.createEarlyWarnAsset(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按资产分域预警告警统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:update')")
    public CommonResult<Boolean> updateEarlyWarnAsset(@Valid @RequestBody EarlyWarnAssetSaveReqVO updateReqVO) {
        earlyWarnAssetService.updateEarlyWarnAsset(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按资产分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:delete')")
    public CommonResult<Boolean> deleteEarlyWarnAsset(@RequestParam("id") Long id) {
        earlyWarnAssetService.deleteEarlyWarnAsset(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按资产分域预警告警统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:query')")
    public CommonResult<EarlyWarnAssetRespVO> getEarlyWarnAsset(@RequestParam("id") Long id) {
        EarlyWarnAssetDO earlyWarnAsset = earlyWarnAssetService.getEarlyWarnAsset(id);
        return success(BeanUtils.toBean(earlyWarnAsset, EarlyWarnAssetRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按资产分域预警告警统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:query')")
    public CommonResult<PageResult<EarlyWarnAssetRespVO>> getEarlyWarnAssetPage(@Valid EarlyWarnAssetPageReqVO pageReqVO) {
        PageResult<EarlyWarnAssetDO> pageResult = earlyWarnAssetService.getEarlyWarnAssetPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnAssetRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按资产分域预警告警统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-asset:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnAssetExcel(@Valid EarlyWarnAssetPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnAssetDO> list = earlyWarnAssetService.getEarlyWarnAssetPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按资产分域预警告警统计.xls", "数据", EarlyWarnAssetRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnAssetRespVO.class));
    }

}