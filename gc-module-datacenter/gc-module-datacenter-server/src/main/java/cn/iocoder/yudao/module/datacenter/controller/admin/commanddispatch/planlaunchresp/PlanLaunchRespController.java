package cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.planlaunchresp.vo.PlanLaunchRespSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.planlaunchresp.PlanLaunchRespDO;
import cn.iocoder.yudao.module.datacenter.service.commanddispatch.planlaunchresp.PlanLaunchRespService;

@Tag(name = "管理后台 - 预案启动响应")
@RestController
@RequestMapping("/datacenter/plan-launch-resp")
@Validated
public class PlanLaunchRespController {

    @Resource
    private PlanLaunchRespService planLaunchRespService;

    @PostMapping("/create")
    @Operation(summary = "创建预案启动响应")
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:create')")
    public CommonResult<Long> createPlanLaunchResp(@Valid @RequestBody PlanLaunchRespSaveReqVO createReqVO) {
        return success(planLaunchRespService.createPlanLaunchResp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预案启动响应")
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:update')")
    public CommonResult<Boolean> updatePlanLaunchResp(@Valid @RequestBody PlanLaunchRespSaveReqVO updateReqVO) {
        planLaunchRespService.updatePlanLaunchResp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预案启动响应")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:delete')")
    public CommonResult<Boolean> deletePlanLaunchResp(@RequestParam("id") Long id) {
        planLaunchRespService.deletePlanLaunchResp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预案启动响应")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:query')")
    public CommonResult<PlanLaunchRespRespVO> getPlanLaunchResp(@RequestParam("id") Long id) {
        PlanLaunchRespDO planLaunchResp = planLaunchRespService.getPlanLaunchResp(id);
        return success(BeanUtils.toBean(planLaunchResp, PlanLaunchRespRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预案启动响应分页")
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:query')")
    public CommonResult<PageResult<PlanLaunchRespRespVO>> getPlanLaunchRespPage(@Valid PlanLaunchRespPageReqVO pageReqVO) {
        PageResult<PlanLaunchRespDO> pageResult = planLaunchRespService.getPlanLaunchRespPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PlanLaunchRespRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预案启动响应 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:plan-launch-resp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPlanLaunchRespExcel(@Valid PlanLaunchRespPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PlanLaunchRespDO> list = planLaunchRespService.getPlanLaunchRespPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预案启动响应.xls", "数据", PlanLaunchRespRespVO.class,
                        BeanUtils.toBean(list, PlanLaunchRespRespVO.class));
    }

}