package cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralactivity.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralactivity.MoralActivityDO;
import cn.iocoder.yudao.module.studentmgmt.service.moralactivity.MoralActivityService;
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
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "学生管理后台 - 德育活动")
@RestController
@RequestMapping("/studentmgmt/moral-activity")
@Validated
public class MoralActivityController {

    @Resource
    private MoralActivityService moralActivityService;

    @PostMapping("/create")
    @Operation(summary = "创建德育活动")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:create')")
    public CommonResult<Long> createMoralActivity(@Valid @RequestBody MoralActivitySaveReqVO createReqVO) {
        return success(moralActivityService.createMoralActivity(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新德育活动")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:update')")
    public CommonResult<Boolean> updateMoralActivity(@Valid @RequestBody MoralActivitySaveReqVO updateReqVO) {
        moralActivityService.updateMoralActivity(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除德育活动")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:delete')")
    public CommonResult<Boolean> deleteMoralActivity(@RequestParam("id") Long id) {
        moralActivityService.deleteMoralActivity(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除德育活动")
                @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:delete')")
    public CommonResult<Boolean> deleteMoralActivityList(@RequestParam("ids") List<Long> ids) {
        moralActivityService.deleteMoralActivityListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得德育活动")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:query')")
    public CommonResult<MoralActivityRespVO> getMoralActivity(@RequestParam("id") Long id) {
        MoralActivityDO moralActivity = moralActivityService.getMoralActivity(id);
        return success(BeanUtils.toBean(moralActivity, MoralActivityRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得德育活动分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:query')")
    public CommonResult<PageResult<MoralActivityRespVO>> getMoralActivityPage(@Valid MoralActivityPageReqVO pageReqVO) {
        PageResult<MoralActivityDO> pageResult = moralActivityService.getMoralActivityPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, MoralActivityRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出德育活动 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportMoralActivityExcel(@Valid MoralActivityPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<MoralActivityDO> list = moralActivityService.getMoralActivityPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "德育活动.xls", "数据", MoralActivityRespVO.class,
                        BeanUtils.toBean(list, MoralActivityRespVO.class));
    }
    @PutMapping("/publish")
    @Operation(summary = "发布")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:publish')")
    public CommonResult<Boolean> publish(@Valid @RequestBody MoralActivityPublishReqVO reqVO) {
        boolean isSuccess = moralActivityService.publish(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/join")
    @Operation(summary = "报名")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:join')")
    public CommonResult<Boolean> join(@Valid @RequestBody MoralActivityJoinReqVO reqVO) {
        boolean isSuccess = moralActivityService.join(reqVO);
        return success(isSuccess);
    }
    @PutMapping("/record")
    @Operation(summary = "记录")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:record')")
    public CommonResult<Boolean> record(@Valid @RequestBody MoralActivityRecordReqVO reqVO) {
        boolean isSuccess = moralActivityService.record(reqVO);
        return success(isSuccess);
    }
    @GetMapping("/chart")
    @Operation(summary = "德育活动态势看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:chart')")
    public CommonResult<MoralActivityChartRespVO> chart(@Valid MoralActivityChartReqVO reqVO) {
        MoralActivityChartRespVO vo = moralActivityService.chart(reqVO);
        return success(vo);
    }
    @GetMapping("/chart/activityCount")
    @Operation(summary = "活动参与 / 类型数量统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:moral-activity:query')")
    public CommonResult<ChartActivityCountRespVO> activityCount(@Valid MoralActivityChartReqVO reqVO) {
        ChartActivityCountRespVO vo = moralActivityService.activityCount(reqVO);
        return success(vo);
    }

}