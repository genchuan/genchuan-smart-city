package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.AnalysisMonCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismoncomp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismoncomp.AnalysisMonCompDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysismoncomp.AnalysisMonCompService;

@Tag(name = "管理后台 - 按监测部件分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-mon-comp")
@Validated
public class AnalysisMonCompController {

    @Resource
    private AnalysisMonCompService analysisMonCompService;

    @PostMapping("/create")
    @Operation(summary = "创建按监测部件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:create')")
    public CommonResult<Long> createAnalysisMonComp(@Valid @RequestBody AnalysisMonCompSaveReqVO createReqVO) {
        return success(analysisMonCompService.createAnalysisMonComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按监测部件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:update')")
    public CommonResult<Boolean> updateAnalysisMonComp(@Valid @RequestBody AnalysisMonCompSaveReqVO updateReqVO) {
        analysisMonCompService.updateAnalysisMonComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按监测部件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:delete')")
    public CommonResult<Boolean> deleteAnalysisMonComp(@RequestParam("id") Long id) {
        analysisMonCompService.deleteAnalysisMonComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按监测部件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:query')")
    public CommonResult<AnalysisMonCompRespVO> getAnalysisMonComp(@RequestParam("id") Long id) {
        AnalysisMonCompDO analysisMonComp = analysisMonCompService.getAnalysisMonComp(id);
        return success(BeanUtils.toBean(analysisMonComp, AnalysisMonCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按监测部件分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:query')")
    public CommonResult<PageResult<AnalysisMonCompRespVO>> getAnalysisMonCompPage(@Valid AnalysisMonCompPageReqVO pageReqVO) {
        PageResult<AnalysisMonCompDO> pageResult = analysisMonCompService.getAnalysisMonCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisMonCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按监测部件分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mon-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisMonCompExcel(@Valid AnalysisMonCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisMonCompDO> list = analysisMonCompService.getAnalysisMonCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按监测部件分析研判统计.xls", "数据", AnalysisMonCompRespVO.class,
                        BeanUtils.toBean(list, AnalysisMonCompRespVO.class));
    }

}