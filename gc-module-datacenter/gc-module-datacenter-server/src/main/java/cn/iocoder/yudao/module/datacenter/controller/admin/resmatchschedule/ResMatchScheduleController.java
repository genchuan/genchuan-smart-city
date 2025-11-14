package cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resmatchschedule.ResMatchScheduleDO;
import cn.iocoder.yudao.module.datacenter.service.resmatchschedule.ResMatchScheduleService;

@Tag(name = "管理后台 - 资源匹配调度")
@RestController
@RequestMapping("/datacenter/res-match-schedule")
@Validated
public class ResMatchScheduleController {

    @Resource
    private ResMatchScheduleService resMatchScheduleService;

    @PostMapping("/create")
    @Operation(summary = "创建资源匹配调度")
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:create')")
    public CommonResult<Long> createResMatchSchedule(@Valid @RequestBody ResMatchScheduleSaveReqVO createReqVO) {
        return success(resMatchScheduleService.createResMatchSchedule(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新资源匹配调度")
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:update')")
    public CommonResult<Boolean> updateResMatchSchedule(@Valid @RequestBody ResMatchScheduleSaveReqVO updateReqVO) {
        resMatchScheduleService.updateResMatchSchedule(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除资源匹配调度")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:delete')")
    public CommonResult<Boolean> deleteResMatchSchedule(@RequestParam("id") Long id) {
        resMatchScheduleService.deleteResMatchSchedule(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得资源匹配调度")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:query')")
    public CommonResult<ResMatchScheduleRespVO> getResMatchSchedule(@RequestParam("id") Long id) {
        ResMatchScheduleDO resMatchSchedule = resMatchScheduleService.getResMatchSchedule(id);
        return success(BeanUtils.toBean(resMatchSchedule, ResMatchScheduleRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得资源匹配调度分页")
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:query')")
    public CommonResult<PageResult<ResMatchScheduleRespVO>> getResMatchSchedulePage(@Valid ResMatchSchedulePageReqVO pageReqVO) {
        PageResult<ResMatchScheduleDO> pageResult = resMatchScheduleService.getResMatchSchedulePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ResMatchScheduleRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出资源匹配调度 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:res-match-schedule:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportResMatchScheduleExcel(@Valid ResMatchSchedulePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ResMatchScheduleDO> list = resMatchScheduleService.getResMatchSchedulePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "资源匹配调度.xls", "数据", ResMatchScheduleRespVO.class,
                        BeanUtils.toBean(list, ResMatchScheduleRespVO.class));
    }

}