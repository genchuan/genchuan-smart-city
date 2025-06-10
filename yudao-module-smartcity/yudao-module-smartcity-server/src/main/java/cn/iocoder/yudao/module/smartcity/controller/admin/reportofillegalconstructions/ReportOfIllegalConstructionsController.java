package cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.ReportOfIllegalConstructionsPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.ReportOfIllegalConstructionsRespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.reportofillegalconstructions.vo.ReportOfIllegalConstructionsSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.reportofillegalconstructions.ReportOfIllegalConstructionsDO;
import cn.iocoder.yudao.module.smartcity.service.reportofillegalconstructions.ReportOfIllegalConstructionsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "管理后台 - 违建上报")
@RestController
@RequestMapping("/smartcity/report-of-illegal-constructions")
@Validated
public class ReportOfIllegalConstructionsController {

    @Resource
    private ReportOfIllegalConstructionsService reportOfIllegalConstructionsService;

    @PostMapping("/create")
    @Operation(summary = "创建违建上报")
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:create')")
    public CommonResult<Long> createReportOfIllegalConstructions(@Valid @RequestBody ReportOfIllegalConstructionsSaveReqVO createReqVO) {
        return success(reportOfIllegalConstructionsService.createReportOfIllegalConstructions(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新违建上报")
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:update')")
    public CommonResult<Boolean> updateReportOfIllegalConstructions(@Valid @RequestBody ReportOfIllegalConstructionsSaveReqVO updateReqVO) {
        reportOfIllegalConstructionsService.updateReportOfIllegalConstructions(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除违建上报")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:delete')")
    public CommonResult<Boolean> deleteReportOfIllegalConstructions(@RequestParam("id") Long id) {
        reportOfIllegalConstructionsService.deleteReportOfIllegalConstructions(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得违建上报")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:query')")
    public CommonResult<ReportOfIllegalConstructionsRespVO> getReportOfIllegalConstructions(@RequestParam("id") Long id) {
        ReportOfIllegalConstructionsDO reportOfIllegalConstructions = reportOfIllegalConstructionsService.getReportOfIllegalConstructions(id);
        return success(BeanUtils.toBean(reportOfIllegalConstructions, ReportOfIllegalConstructionsRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得违建上报分页")
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:query')")
    public CommonResult<PageResult<ReportOfIllegalConstructionsRespVO>> getReportOfIllegalConstructionsPage(@Valid ReportOfIllegalConstructionsPageReqVO pageReqVO) {
        PageResult<ReportOfIllegalConstructionsDO> pageResult = reportOfIllegalConstructionsService.getReportOfIllegalConstructionsPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, ReportOfIllegalConstructionsRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出违建上报 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:report-of-illegal-constructions:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportReportOfIllegalConstructionsExcel(@Valid ReportOfIllegalConstructionsPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<ReportOfIllegalConstructionsDO> list = reportOfIllegalConstructionsService.getReportOfIllegalConstructionsPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "违建上报.xls", "数据", ReportOfIllegalConstructionsRespVO.class,
                        BeanUtils.toBean(list, ReportOfIllegalConstructionsRespVO.class));
    }

}