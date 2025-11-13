package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnarea.vo.EarlyWarnAreaSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnarea.EarlyWarnAreaDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnarea.EarlyWarnAreaService;

@Tag(name = "管理后台 - 预警告警区域维度统计")
@RestController
@RequestMapping("/datacenter/early-warn-area")
@Validated
public class EarlyWarnAreaController {

    @Resource
    private EarlyWarnAreaService earlyWarnAreaService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警区域维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:create')")
    public CommonResult<Long> createEarlyWarnArea(@Valid @RequestBody EarlyWarnAreaSaveReqVO createReqVO) {
        return success(earlyWarnAreaService.createEarlyWarnArea(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警区域维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:update')")
    public CommonResult<Boolean> updateEarlyWarnArea(@Valid @RequestBody EarlyWarnAreaSaveReqVO updateReqVO) {
        earlyWarnAreaService.updateEarlyWarnArea(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警区域维度统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:delete')")
    public CommonResult<Boolean> deleteEarlyWarnArea(@RequestParam("id") Long id) {
        earlyWarnAreaService.deleteEarlyWarnArea(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警区域维度统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:query')")
    public CommonResult<EarlyWarnAreaRespVO> getEarlyWarnArea(@RequestParam("id") Long id) {
        EarlyWarnAreaDO earlyWarnArea = earlyWarnAreaService.getEarlyWarnArea(id);
        return success(BeanUtils.toBean(earlyWarnArea, EarlyWarnAreaRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警区域维度统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:query')")
    public CommonResult<PageResult<EarlyWarnAreaRespVO>> getEarlyWarnAreaPage(@Valid EarlyWarnAreaPageReqVO pageReqVO) {
        PageResult<EarlyWarnAreaDO> pageResult = earlyWarnAreaService.getEarlyWarnAreaPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnAreaRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警区域维度统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-area:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnAreaExcel(@Valid EarlyWarnAreaPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnAreaDO> list = earlyWarnAreaService.getEarlyWarnAreaPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警区域维度统计.xls", "数据", EarlyWarnAreaRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnAreaRespVO.class));
    }

}