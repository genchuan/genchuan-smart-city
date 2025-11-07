package cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement;

import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.security.access.prepost.PreAuthorize;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

import cn.iocoder.yudao.module.smartcity.controller.admin.patroltaskmanagement.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patroltaskmanagement.PatrolTaskManagementDO;
import cn.iocoder.yudao.module.smartcity.service.patroltaskmanagement.PatrolTaskManagementService;

@Tag(name = "管理后台 - 巡査任务管理")
@RestController
@RequestMapping("/smartcity/patrol-task-management")
@Validated
public class PatrolTaskManagementController {

    @Resource
    private PatrolTaskManagementService patrolTaskManagementService;

    @PostMapping("/create")
    @Operation(summary = "创建巡査任务管理")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:create')")
    public CommonResult<Long> createPatrolTaskManagement(@Valid @RequestBody PatrolTaskManagementSaveReqVO createReqVO) {
        return success(patrolTaskManagementService.createPatrolTaskManagement(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡査任务管理")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:update')")
    public CommonResult<Boolean> updatePatrolTaskManagement(@Valid @RequestBody PatrolTaskManagementSaveReqVO updateReqVO) {
        patrolTaskManagementService.updatePatrolTaskManagement(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡査任务管理")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:delete')")
    public CommonResult<Boolean> deletePatrolTaskManagement(@RequestParam("id") Long id) {
        patrolTaskManagementService.deletePatrolTaskManagement(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡査任务管理")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:query')")
    public CommonResult<PatrolTaskManagementRespVO> getPatrolTaskManagement(@RequestParam("id") Long id) {
        PatrolTaskManagementDO patrolTaskManagement = patrolTaskManagementService.getPatrolTaskManagement(id);
        return success(BeanUtils.toBean(patrolTaskManagement, PatrolTaskManagementRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡査任务管理分页")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:query')")
    public CommonResult<PageResult<PatrolTaskManagementRespVO>> getPatrolTaskManagementPage(@Valid PatrolTaskManagementPageReqVO pageReqVO) {
        PageResult<PatrolTaskManagementDO> pageResult = patrolTaskManagementService.getPatrolTaskManagementPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, PatrolTaskManagementRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡査任务管理 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:patrol-task-management:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportPatrolTaskManagementExcel(@Valid PatrolTaskManagementPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<PatrolTaskManagementDO> list = patrolTaskManagementService.getPatrolTaskManagementPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡査任务管理.xls", "数据", PatrolTaskManagementRespVO.class,
                        BeanUtils.toBean(list, PatrolTaskManagementRespVO.class));
    }

}