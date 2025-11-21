package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntime.vo.EarlyWarnTimeSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntime.EarlyWarnTimeDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarntime.EarlyWarnTimeService;

@Tag(name = "管理后台 - 预警告警时间维度统计")
@RestController
@RequestMapping("/datacenter/early-warn-time")
@Validated
public class EarlyWarnTimeController {

    @Resource
    private EarlyWarnTimeService earlyWarnTimeService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警时间维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:create')")
    public CommonResult<Long> createEarlyWarnTime(@Valid @RequestBody EarlyWarnTimeSaveReqVO createReqVO) {
        return success(earlyWarnTimeService.createEarlyWarnTime(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警时间维度统计")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:update')")
    public CommonResult<Boolean> updateEarlyWarnTime(@Valid @RequestBody EarlyWarnTimeSaveReqVO updateReqVO) {
        earlyWarnTimeService.updateEarlyWarnTime(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警时间维度统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:delete')")
    public CommonResult<Boolean> deleteEarlyWarnTime(@RequestParam("id") Long id) {
        earlyWarnTimeService.deleteEarlyWarnTime(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警时间维度统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:query')")
    public CommonResult<EarlyWarnTimeRespVO> getEarlyWarnTime(@RequestParam("id") Long id) {
        EarlyWarnTimeDO earlyWarnTime = earlyWarnTimeService.getEarlyWarnTime(id);
        return success(BeanUtils.toBean(earlyWarnTime, EarlyWarnTimeRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警时间维度统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:query')")
    public CommonResult<PageResult<EarlyWarnTimeRespVO>> getEarlyWarnTimePage(@Valid EarlyWarnTimePageReqVO pageReqVO) {
        PageResult<EarlyWarnTimeDO> pageResult = earlyWarnTimeService.getEarlyWarnTimePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnTimeRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警时间维度统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-time:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnTimeExcel(@Valid EarlyWarnTimePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnTimeDO> list = earlyWarnTimeService.getEarlyWarnTimePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警时间维度统计.xls", "数据", EarlyWarnTimeRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnTimeRespVO.class));
    }

}