package cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks;

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

import cn.iocoder.yudao.module.smartcity.controller.admin.keyworktasks.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.keyworktasks.KeyWorkTasksDO;
import cn.iocoder.yudao.module.smartcity.service.keyworktasks.KeyWorkTasksService;

@Tag(name = "管理后台 - 重点工作任务")
@RestController
@RequestMapping("/smartcity/key-work-tasks")
@Validated
public class KeyWorkTasksController {

    @Resource
    private KeyWorkTasksService keyWorkTasksService;

    @PostMapping("/create")
    @Operation(summary = "创建重点工作任务")
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:create')")
    public CommonResult<Long> createKeyWorkTasks(@Valid @RequestBody KeyWorkTasksSaveReqVO createReqVO) {
        return success(keyWorkTasksService.createKeyWorkTasks(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新重点工作任务")
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:update')")
    public CommonResult<Boolean> updateKeyWorkTasks(@Valid @RequestBody KeyWorkTasksSaveReqVO updateReqVO) {
        keyWorkTasksService.updateKeyWorkTasks(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除重点工作任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:delete')")
    public CommonResult<Boolean> deleteKeyWorkTasks(@RequestParam("id") Long id) {
        keyWorkTasksService.deleteKeyWorkTasks(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得重点工作任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:query')")
    public CommonResult<KeyWorkTasksRespVO> getKeyWorkTasks(@RequestParam("id") Long id) {
        KeyWorkTasksDO keyWorkTasks = keyWorkTasksService.getKeyWorkTasks(id);
        return success(BeanUtils.toBean(keyWorkTasks, KeyWorkTasksRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得重点工作任务分页")
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:query')")
    public CommonResult<PageResult<KeyWorkTasksRespVO>> getKeyWorkTasksPage(@Valid KeyWorkTasksPageReqVO pageReqVO) {
        PageResult<KeyWorkTasksDO> pageResult = keyWorkTasksService.getKeyWorkTasksPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, KeyWorkTasksRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出重点工作任务 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:key-work-tasks:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportKeyWorkTasksExcel(@Valid KeyWorkTasksPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<KeyWorkTasksDO> list = keyWorkTasksService.getKeyWorkTasksPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "重点工作任务.xls", "数据", KeyWorkTasksRespVO.class,
                        BeanUtils.toBean(list, KeyWorkTasksRespVO.class));
    }

}