package cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask.ToiletCleaningTaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

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

    @DeleteMapping("/delete-batch")
    @Operation(summary = "批量删除公厕保洁任务")
    @Parameter(name = "ids", description = "编号列表", required = true)
    @PreAuthorize("@ss.hasPermission('health:toilet-cleaning-task:delete')")
    public CommonResult<Boolean> deleteToiletCleaningTaskBatch(@RequestBody List<Long> ids) {
        toiletCleaningTaskService.deleteToiletCleaningTaskBatch(ids);
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

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("公厕保洁任务_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "公厕保洁任务.xls", "数据", ToiletCleaningTaskRespVO.class,
                        BeanUtils.toBean(list, ToiletCleaningTaskRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得公厕保洁任务详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<PageResult<ToiletCleaningTaskDetailDO>> getToiletCleaningTaskDetailPage(
            @Valid ToiletCleaningTaskPageReqVO pageReqVO) {
        PageResult<ToiletCleaningTaskDetailDO> pageResult =
                toiletCleaningTaskService.getToiletCleaningTaskDetailPage(pageReqVO);

        return success(pageResult);
    }

    @PostMapping("/upload-photos")
    @Operation(summary = "上传多张图片")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:update')")
    public CommonResult<List<String>> uploadPhotos(
            @RequestParam("id") Long id,
            @RequestParam("files") MultipartFile[] files) {
        List<String> photoUrls = toiletCleaningTaskService.uploadPhotos(id, Arrays.asList(files));
        return success(photoUrls);
    }

    @GetMapping("/photos/{id}")
    @Operation(summary = "获取图片列表")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<List<String>> getPhotos(@PathVariable Long id) {
        List<String> photos = toiletCleaningTaskService.getPhotos(id);
        return success(photos);
    }

    @DeleteMapping("/photo")
    @Operation(summary = "删除图片")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:update')")
    public CommonResult<Boolean> deletePhoto(
            @RequestParam("id") Long id,
            @RequestParam("photoUrl") String photoUrl) {
        toiletCleaningTaskService.deletePhoto(id, photoUrl);
        return success(true);
    }

    @PutMapping("/batch-adjust")
    @Operation(summary = "批量调整公厕保洁任务")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:update')")
    public CommonResult<Boolean> batchAdjustToiletCleaningTask(
            @Valid @RequestBody ToiletCleaningTaskBatchAdjustReqVO reqVO) {
        toiletCleaningTaskService.batchAdjustToiletCleaningTask(reqVO);
        return success(true);
    }

    @GetMapping("/chart/pending")
    @Operation(summary = "卡片/圆环图/柱状图统计(待执行)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<ToiletCleaningTaskPendingRespVO> getPending() {
        return success(toiletCleaningTaskService.getPending());
    }

    @GetMapping("/chart/summary")
    @Operation(summary = "卡片/圆环图/柱状图统计(已完成)")
    @PreAuthorize("@ss.hasPermission('envirhealth:toilet-cleaning-task:query')")
    public CommonResult<ToiletCleaningTaskSummaryRespVO> getSummary() {
        return success(toiletCleaningTaskService.getSummary());
    }
}