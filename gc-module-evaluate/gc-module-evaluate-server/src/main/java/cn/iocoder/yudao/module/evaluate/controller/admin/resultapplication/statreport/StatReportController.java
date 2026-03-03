package cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.resultapplication.statreport.vo.StatReportSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.statreport.StatReportDO;
import cn.iocoder.yudao.module.evaluate.service.statreport.StatReportService;
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

@Tag(name = "管理后台 - 统计分析报")
@RestController
@RequestMapping("/evaluate/stat-report")
@Validated
public class StatReportController {

    @Resource
    private StatReportService statReportService;

    @PostMapping("/create")
    @Operation(summary = "创建统计分析报")
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:create')")
    public CommonResult<Long> createStatReport(@Valid @RequestBody StatReportSaveReqVO createReqVO) {
        return success(statReportService.createStatReport(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新统计分析报")
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:update')")
    public CommonResult<Boolean> updateStatReport(@Valid @RequestBody StatReportSaveReqVO updateReqVO) {
        statReportService.updateStatReport(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除统计分析报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:delete')")
    public CommonResult<Boolean> deleteStatReport(@RequestParam("id") Long id) {
        statReportService.deleteStatReport(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得统计分析报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:query')")
    public CommonResult<StatReportRespVO> getStatReport(@RequestParam("id") Long id) {
        StatReportDO statReport = statReportService.getStatReport(id);
        return success(BeanUtils.toBean(statReport, StatReportRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得统计分析报分页")
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:query')")
    public CommonResult<PageResult<StatReportRespVO>> getStatReportPage(@Valid StatReportPageReqVO pageReqVO) {
        PageResult<StatReportDO> pageResult = statReportService.getStatReportPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, StatReportRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出统计分析报 Excel")
    @PreAuthorize("@ss.hasPermission('evaluate:stat-report:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportStatReportExcel(@Valid StatReportPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<StatReportDO> list = statReportService.getStatReportPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "统计分析报.xls", "数据", StatReportRespVO.class,
                        BeanUtils.toBean(list, StatReportRespVO.class));
    }

}