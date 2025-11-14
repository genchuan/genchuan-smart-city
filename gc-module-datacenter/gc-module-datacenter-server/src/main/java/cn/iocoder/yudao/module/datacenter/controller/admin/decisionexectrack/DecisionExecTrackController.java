package cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack;

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

import cn.iocoder.yudao.module.datacenter.controller.admin.decisionexectrack.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.decisionexectrack.DecisionExecTrackDO;
import cn.iocoder.yudao.module.datacenter.service.decisionexectrack.DecisionExecTrackService;

@Tag(name = "管理后台 - 决策执行跟踪")
@RestController
@RequestMapping("/datacenter/decision-exec-track")
@Validated
public class DecisionExecTrackController {

    @Resource
    private DecisionExecTrackService decisionExecTrackService;

    @PostMapping("/create")
    @Operation(summary = "创建决策执行跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:create')")
    public CommonResult<Long> createDecisionExecTrack(@Valid @RequestBody DecisionExecTrackSaveReqVO createReqVO) {
        return success(decisionExecTrackService.createDecisionExecTrack(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新决策执行跟踪")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:update')")
    public CommonResult<Boolean> updateDecisionExecTrack(@Valid @RequestBody DecisionExecTrackSaveReqVO updateReqVO) {
        decisionExecTrackService.updateDecisionExecTrack(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除决策执行跟踪")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:delete')")
    public CommonResult<Boolean> deleteDecisionExecTrack(@RequestParam("id") Long id) {
        decisionExecTrackService.deleteDecisionExecTrack(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得决策执行跟踪")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:query')")
    public CommonResult<DecisionExecTrackRespVO> getDecisionExecTrack(@RequestParam("id") Long id) {
        DecisionExecTrackDO decisionExecTrack = decisionExecTrackService.getDecisionExecTrack(id);
        return success(BeanUtils.toBean(decisionExecTrack, DecisionExecTrackRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得决策执行跟踪分页")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:query')")
    public CommonResult<PageResult<DecisionExecTrackRespVO>> getDecisionExecTrackPage(@Valid DecisionExecTrackPageReqVO pageReqVO) {
        PageResult<DecisionExecTrackDO> pageResult = decisionExecTrackService.getDecisionExecTrackPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DecisionExecTrackRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出决策执行跟踪 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:decision-exec-track:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDecisionExecTrackExcel(@Valid DecisionExecTrackPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DecisionExecTrackDO> list = decisionExecTrackService.getDecisionExecTrackPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "决策执行跟踪.xls", "数据", DecisionExecTrackRespVO.class,
                        BeanUtils.toBean(list, DecisionExecTrackRespVO.class));
    }

}