package cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.workhome.vo.*;
import cn.iocoder.yudao.module.studentmgmt.service.workhome.WorkHomeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 学工首页")
@RestController
@RequestMapping("/studentmgmt/work-home")
@Validated
public class WorkHomeController {

    @Resource
    private WorkHomeService workHomeService;

    @GetMapping("/page")
    @Operation(summary = "获得学工首页分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:work-home:query')")
    public CommonResult<PageResult<WorkHomeRespVO>> getWorkHomePage(@Valid WorkHomePageReqVO pageReqVO) {
        return success(workHomeService.getWorkHomePage(pageReqVO));
    }

    @GetMapping("/chart")
    @Operation(summary = "学工综合数据看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:work-home:query')")
    public CommonResult<WorkHomeChartRespVO> chart(@Valid WorkHomeChartReqVO reqVO) {
        return success(workHomeService.chart(reqVO));
    }

    @GetMapping("/chart/dimensionCount")
    @Operation(summary = "各维度记录分布统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:work-home:query')")
    public CommonResult<List<WorkHomeDimensionCountRespVO>> dimensionCount(@Valid WorkHomeChartReqVO reqVO) {
        return success(workHomeService.dimensionCount(reqVO));
    }

    @GetMapping("/chart/scoreAnalysis")
    @Operation(summary = "班级整体发展维度评分统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:work-home:query')")
    public CommonResult<List<WorkHomeScoreAnalysisRespVO>> scoreAnalysis(@Valid WorkHomeScoreAnalysisReqVO reqVO) {
        return success(workHomeService.scoreAnalysis(reqVO));
    }

    @GetMapping("/chart/coreIndex")
    @Operation(summary = "核心指标统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:work-home:query')")
    public CommonResult<List<WorkHomeCoreIndexRespVO>> coreIndex(@Valid WorkHomeCoreIndexReqVO reqVO) {
        return success(workHomeService.coreIndex(reqVO));
    }

}
