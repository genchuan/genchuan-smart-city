package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.AnalysisMngCompSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysismngcomp.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysismngcomp.AnalysisMngCompDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysismngcomp.AnalysisMngCompService;

@Tag(name = "管理后台 - 按管理部件分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-mng-comp")
@Validated
public class AnalysisMngCompController {

    @Resource
    private AnalysisMngCompService analysisMngCompService;

    @PostMapping("/create")
    @Operation(summary = "创建按管理部件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:create')")
    public CommonResult<Long> createAnalysisMngComp(@Valid @RequestBody AnalysisMngCompSaveReqVO createReqVO) {
        return success(analysisMngCompService.createAnalysisMngComp(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按管理部件分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:update')")
    public CommonResult<Boolean> updateAnalysisMngComp(@Valid @RequestBody AnalysisMngCompSaveReqVO updateReqVO) {
        analysisMngCompService.updateAnalysisMngComp(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按管理部件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:delete')")
    public CommonResult<Boolean> deleteAnalysisMngComp(@RequestParam("id") Long id) {
        analysisMngCompService.deleteAnalysisMngComp(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按管理部件分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:query')")
    public CommonResult<AnalysisMngCompRespVO> getAnalysisMngComp(@RequestParam("id") Long id) {
        AnalysisMngCompDO analysisMngComp = analysisMngCompService.getAnalysisMngComp(id);
        return success(BeanUtils.toBean(analysisMngComp, AnalysisMngCompRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按管理部件分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:query')")
    public CommonResult<PageResult<AnalysisMngCompRespVO>> getAnalysisMngCompPage(@Valid AnalysisMngCompPageReqVO pageReqVO) {
        PageResult<AnalysisMngCompDO> pageResult = analysisMngCompService.getAnalysisMngCompPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisMngCompRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按管理部件分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-mng-comp:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisMngCompExcel(@Valid AnalysisMngCompPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisMngCompDO> list = analysisMngCompService.getAnalysisMngCompPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按管理部件分析研判统计.xls", "数据", AnalysisMngCompRespVO.class,
                        BeanUtils.toBean(list, AnalysisMngCompRespVO.class));
    }

}