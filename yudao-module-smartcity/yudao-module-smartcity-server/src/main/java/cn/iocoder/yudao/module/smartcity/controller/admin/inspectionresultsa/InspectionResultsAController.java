package cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.InspectionResultsAPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.InspectionResultsARespVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.inspectionresultsa.vo.InspectionResultsASaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.inspectionresultsa.InspectionResultsADO;
import cn.iocoder.yudao.module.smartcity.service.inspectionresultsa.InspectionResultsAService;
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

@Tag(name = "管理后台 - 巡查结果")
@RestController
@RequestMapping("/smartcity/inspection-results-a")
@Validated
public class InspectionResultsAController {

    @Resource
    private InspectionResultsAService inspectionResultsAService;

    @PostMapping("/create")
    @Operation(summary = "创建巡查结果")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:create')")
    public CommonResult<Long> createInspectionResultsA(@Valid @RequestBody InspectionResultsASaveReqVO createReqVO) {
        return success(inspectionResultsAService.createInspectionResultsA(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新巡查结果")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:update')")
    public CommonResult<Boolean> updateInspectionResultsA(@Valid @RequestBody InspectionResultsASaveReqVO updateReqVO) {
        inspectionResultsAService.updateInspectionResultsA(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除巡查结果")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:delete')")
    public CommonResult<Boolean> deleteInspectionResultsA(@RequestParam("id") Long id) {
        inspectionResultsAService.deleteInspectionResultsA(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得巡查结果")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:query')")
    public CommonResult<InspectionResultsARespVO> getInspectionResultsA(@RequestParam("id") Long id) {
        InspectionResultsADO inspectionResultsA = inspectionResultsAService.getInspectionResultsA(id);
        return success(BeanUtils.toBean(inspectionResultsA, InspectionResultsARespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得巡查结果分页")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:query')")
    public CommonResult<PageResult<InspectionResultsARespVO>> getInspectionResultsAPage(@Valid InspectionResultsAPageReqVO pageReqVO) {
        PageResult<InspectionResultsADO> pageResult = inspectionResultsAService.getInspectionResultsAPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, InspectionResultsARespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出巡查结果 Excel")
    @PreAuthorize("@ss.hasPermission('smartcity:inspection-results-a:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportInspectionResultsAExcel(@Valid InspectionResultsAPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<InspectionResultsADO> list = inspectionResultsAService.getInspectionResultsAPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "巡查结果.xls", "数据", InspectionResultsARespVO.class,
                        BeanUtils.toBean(list, InspectionResultsARespVO.class));
    }

}