package cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare;

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

import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare.DormCompareDO;
import cn.iocoder.yudao.module.studentmgmt.service.dormcompare.DormCompareService;

@Tag(name = "学生管理后台 - 宿舍评比")
@RestController
@RequestMapping("/studentmgmt/dorm-compare")
@Validated
public class DormCompareController {

    @Resource
    private DormCompareService dormCompareService;

    @PostMapping("/create")
    @Operation(summary = "创建宿舍评比")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:create')")
    public CommonResult<Long> createDormCompare(@Valid @RequestBody DormCompareSaveReqVO createReqVO) {
        return success(dormCompareService.createDormCompare(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新宿舍评比")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:update')")
    public CommonResult<Boolean> updateDormCompare(@Valid @RequestBody DormCompareUpdateReqVO updateReqVO) {
        dormCompareService.updateDormCompare(updateReqVO);
        return success(true);

    }
    @DeleteMapping("/delete")
    @Operation(summary = "删除宿舍评比")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:delete')")
    public CommonResult<Boolean> deleteDormCompare(@RequestParam("id") Long id) {
        dormCompareService.deleteDormCompare(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Parameter(name = "ids", description = "编号", required = true)
    @Operation(summary = "批量删除宿舍评比")
                @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:delete')")
    public CommonResult<Boolean> deleteDormCompareList(@RequestParam("ids") List<Long> ids) {
        dormCompareService.deleteDormCompareListByIds(ids);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得宿舍评比")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:query')")
    public CommonResult<DormCompareRespVO> getDormCompare(@RequestParam("id") Long id) {
        DormCompareDO dormCompare = dormCompareService.getDormCompare(id);
        return success(BeanUtils.toBean(dormCompare, DormCompareRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得宿舍评比分页")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:query')")
    public CommonResult<PageResult<DormCompareRespVO>> getDormComparePage(@Valid DormComparePageReqVO pageReqVO) {
        PageResult<DormCompareDO> pageResult = dormCompareService.getDormComparePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DormCompareRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出宿舍评比 Excel")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDormCompareExcel(@Valid DormComparePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DormCompareDO> list = dormCompareService.getDormComparePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "宿舍评比.xls", "数据", DormCompareRespVO.class,
                        BeanUtils.toBean(list, DormCompareRespVO.class));
    }
    @PutMapping("/score")
    @Operation(summary = "打分")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:score')")
    public CommonResult<Boolean> score(@Valid @RequestBody List<DormCompareScoreReqVO> reqList) {
        boolean isSuccess = dormCompareService.score(reqList);
        return success(isSuccess);
    }
    @PutMapping("/summary")
    @Operation(summary = "汇总")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:summary')")
    public CommonResult<Boolean> summary(@Valid @RequestBody DormCompareSummaryReqVO reqVo) {
        boolean isSuccess = dormCompareService.summary(reqVo);
        return success(isSuccess);
    }

    @PutMapping("/push")
    @Operation(summary = "推送")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:push')")
    public CommonResult<Boolean> push(@Valid @RequestBody DormComparePushReqVO reqVo) {
        boolean isSuccess = dormCompareService.push(reqVo);
        return success(isSuccess);
    }

    @GetMapping("/chart")
    @Operation(summary = "宿舍评比得分看板")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:query')")
    public CommonResult<DormCompareChartRespVO> chart(@Valid DormCompareChartReqVO reqVo) {
        DormCompareChartRespVO vo = dormCompareService.chart(reqVo);
        return success(vo);
    }

    @GetMapping("/chart/scoreRank")
    @Operation(summary = "宿舍得分排名统计")
    @PreAuthorize("@ss.hasPermission('studentmgmt:dorm-compare:query')")
    public CommonResult<DormCompareRankRespVO> scoreRank(@Valid DormCompareChartReqVO reqVo) {
        DormCompareRankRespVO vo = dormCompareService.scoreRank(reqVo);
        return success(vo);
    }

}
