package cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene;

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppScenePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppSceneRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.AnalysisAppSceneSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.analysis.analysisappscene.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.analysis.analysisappscene.AnalysisAppSceneDO;
import cn.iocoder.yudao.module.datacenter.service.analysis.analysisappscene.AnalysisAppSceneService;

@Tag(name = "管理后台 - 按应用场景分析研判统计")
@RestController
@RequestMapping("/datacenter/analysis-app-scene")
@Validated
public class AnalysisAppSceneController {

    @Resource
    private AnalysisAppSceneService analysisAppSceneService;

    @PostMapping("/create")
    @Operation(summary = "创建按应用场景分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:create')")
    public CommonResult<Long> createAnalysisAppScene(@Valid @RequestBody AnalysisAppSceneSaveReqVO createReqVO) {
        return success(analysisAppSceneService.createAnalysisAppScene(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新按应用场景分析研判统计")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:update')")
    public CommonResult<Boolean> updateAnalysisAppScene(@Valid @RequestBody AnalysisAppSceneSaveReqVO updateReqVO) {
        analysisAppSceneService.updateAnalysisAppScene(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除按应用场景分析研判统计")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:delete')")
    public CommonResult<Boolean> deleteAnalysisAppScene(@RequestParam("id") Long id) {
        analysisAppSceneService.deleteAnalysisAppScene(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得按应用场景分析研判统计")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:query')")
    public CommonResult<AnalysisAppSceneRespVO> getAnalysisAppScene(@RequestParam("id") Long id) {
        AnalysisAppSceneDO analysisAppScene = analysisAppSceneService.getAnalysisAppScene(id);
        return success(BeanUtils.toBean(analysisAppScene, AnalysisAppSceneRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得按应用场景分析研判统计分页")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:query')")
    public CommonResult<PageResult<AnalysisAppSceneRespVO>> getAnalysisAppScenePage(@Valid AnalysisAppScenePageReqVO pageReqVO) {
        PageResult<AnalysisAppSceneDO> pageResult = analysisAppSceneService.getAnalysisAppScenePage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AnalysisAppSceneRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出按应用场景分析研判统计 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:analysis-app-scene:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAnalysisAppSceneExcel(@Valid AnalysisAppScenePageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AnalysisAppSceneDO> list = analysisAppSceneService.getAnalysisAppScenePage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "按应用场景分析研判统计.xls", "数据", AnalysisAppSceneRespVO.class,
                        BeanUtils.toBean(list, AnalysisAppSceneRespVO.class));
    }

}