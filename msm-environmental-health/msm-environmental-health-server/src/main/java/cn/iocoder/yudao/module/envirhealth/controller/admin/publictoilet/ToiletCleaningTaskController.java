package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskWithJoinRespVO;
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

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask.ToiletCleaningTaskService;

@Tag(name = "环境卫生管理 - 公厕保洁任务")
@RestController
@RequestMapping("/envirhealth/toilet-cleaning-task")
@Validated
public class ToiletCleaningTaskController {

    @Resource
    private ToiletCleaningTaskService toiletCleaningTaskService;

    @PostMapping("/create")
    @Operation(summary = "创建公厕保洁任务")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:create')")
    public CommonResult<Long> createToiletCleaningTask(@Valid @RequestBody ToiletCleaningTaskSaveReqVO createReqVO) {
        return success(toiletCleaningTaskService.createToiletCleaningTask(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新公厕保洁任务")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:update')")
    public CommonResult<Boolean> updateToiletCleaningTask(@Valid @RequestBody ToiletCleaningTaskSaveReqVO updateReqVO) {
        toiletCleaningTaskService.updateToiletCleaningTask(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除公厕保洁任务")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:delete')")
    public CommonResult<Boolean> deleteToiletCleaningTask(@RequestParam("id") Long id) {
        toiletCleaningTaskService.deleteToiletCleaningTask(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得公厕保洁任务")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<ToiletCleaningTaskRespVO> getToiletCleaningTask(@RequestParam("id") Long id) {
        ToiletCleaningTaskDO toiletCleaningTask = toiletCleaningTaskService.getToiletCleaningTask(id);
        return success(BeanUtils.toBean(toiletCleaningTask, ToiletCleaningTaskRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得公厕保洁任务分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<PageResult<ToiletCleaningTaskRespVO>> getToiletCleaningTaskPage(@Valid ToiletCleaningTaskPageReqVO pageReqVO) {
        PageResult<ToiletCleaningTaskDO> pageResult = toiletCleaningTaskService.getToiletCleaningTaskPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ToiletCleaningTaskRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出公厕保洁任务 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportToiletCleaningTaskExcel(@Valid ToiletCleaningTaskPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ToiletCleaningTaskDO> list = toiletCleaningTaskService.getToiletCleaningTaskPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "公厕保洁任务.xls", "数据", ToiletCleaningTaskRespVO.class,
                        BeanUtils.toBean(list, ToiletCleaningTaskRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公厕保洁任务详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<PageResult<ToiletCleaningTaskWithJoinRespVO>> getToiletCleaningTaskPageDetail(@Valid ToiletCleaningTaskPageReqVO pageReqVO) {
        PageResult<ToiletCleaningTaskWithJoinRespVO> pageResult = toiletCleaningTaskService.getToiletCleaningTaskJoinPage(pageReqVO);
        return success(pageResult);
    }

}