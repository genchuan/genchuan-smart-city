package cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.service.roadcleaning.cleaningproblem.CleaningProblemService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "环境卫生管理 - 道路清扫问题")
@RestController
@RequestMapping("/envirhealth/cleaning-problem")
@Validated
public class CleaningProblemController {

    @Resource
    private CleaningProblemService cleaningProblemService;

    @PostMapping("/create")
    @Operation(summary = "创建道路清扫问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:create')")
    public CommonResult<Long> createCleaningProblem(@Valid @RequestBody CleaningProblemSaveReqVO createReqVO) {
        return success(cleaningProblemService.createCleaningProblem(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新道路清扫问题")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:update')")
    public CommonResult<Boolean> updateCleaningProblem(@Valid @RequestBody CleaningProblemSaveReqVO updateReqVO) {
        cleaningProblemService.updateCleaningProblem(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除道路清扫问题")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:delete')")
    public CommonResult<Boolean> deleteCleaningProblem(@RequestParam("id") Long id) {
        cleaningProblemService.deleteCleaningProblem(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得道路清扫问题")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<CleaningProblemRespVO> getCleaningProblem(@RequestParam("id") Long id) {
        CleaningProblemDO cleaningProblem = cleaningProblemService.getCleaningProblem(id);
        return success(BeanUtils.toBean(cleaningProblem, CleaningProblemRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得道路清扫问题分页")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<PageResult<CleaningProblemRespVO>> getCleaningProblemPage(@Valid CleaningProblemPageReqVO pageReqVO) {
        PageResult<CleaningProblemDO> pageResult = cleaningProblemService.getCleaningProblemPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CleaningProblemRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出道路清扫问题 Excel")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCleaningProblemExcel(@Valid CleaningProblemPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CleaningProblemDO> list = cleaningProblemService.getCleaningProblemPage(pageReqVO).getList();

        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
        response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode("道路清扫问题_" +
                        LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")) + ".xls", "UTF-8"));
        response.setCharacterEncoding("UTF-8");

        // 导出 Excel
        ExcelUtils.write(response, "道路清扫问题.xls", "数据", CleaningProblemRespVO.class,
                        BeanUtils.toBean(list, CleaningProblemRespVO.class));
    }

    @GetMapping("/detail-page")
    @Operation(summary = "获得道路清扫问题详情(分页)")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<PageResult<CleaningProblemDetailDO>> getPublicToiletDetailPage(
            @Valid CleaningProblemPageReqVO pageReqVO) {
        PageResult<CleaningProblemDetailDO> pageResult =
                cleaningProblemService.getCleaningProblemDetailPage(pageReqVO);

        return success(pageResult);
    }

    @PostMapping("/batch-process")
    @Operation(summary = "批量处理道路清扫问题处置状态")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:batch-process')")
    public CommonResult<Boolean> batchProcessCleaningProblems(
            @Valid @RequestBody CleaningProblemBatchProcessReqVO batchReqVO) {
        cleaningProblemService.batchUpdateCleaningProblemStatus(batchReqVO);
        return success(true);
    }

    @GetMapping("/chart/pending")
    @Operation(summary = "卡片/圆环图/柱状图(待处置)")
    @PreAuthorize("@ss.hasPermission('envirhealth:cleaning-problem:query')")
    public CommonResult<CleaningProblemPendingRespVO> getCleaningProblemPendingDashboard() {
        return success(cleaningProblemService.getCleaningProblemPendingDashboard());
    }
}