package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.result.evalresultsystemdock.vo.EvalResultSystemDockSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.result.evalresultsystemdock.EvalResultSystemDockDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.result.evalresultsystemdock.EvalResultSystemDockService;

@Tag(name = "管理后台 - 结果系统对接")
@RestController
@RequestMapping("/datacenter/eval-result-system-dock")
@Validated
public class EvalResultSystemDockController {

    @Resource
    private EvalResultSystemDockService evalResultSystemDockService;

    @PostMapping("/create")
    @Operation(summary = "创建结果系统对接")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:create')")
    public CommonResult<Long> createEvalResultSystemDock(@Valid @RequestBody EvalResultSystemDockSaveReqVO createReqVO) {
        return success(evalResultSystemDockService.createEvalResultSystemDock(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结果系统对接")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:update')")
    public CommonResult<Boolean> updateEvalResultSystemDock(@Valid @RequestBody EvalResultSystemDockSaveReqVO updateReqVO) {
        evalResultSystemDockService.updateEvalResultSystemDock(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结果系统对接")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:delete')")
    public CommonResult<Boolean> deleteEvalResultSystemDock(@RequestParam("id") Long id) {
        evalResultSystemDockService.deleteEvalResultSystemDock(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结果系统对接")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:query')")
    public CommonResult<EvalResultSystemDockRespVO> getEvalResultSystemDock(@RequestParam("id") Long id) {
        EvalResultSystemDockDO evalResultSystemDock = evalResultSystemDockService.getEvalResultSystemDock(id);
        return success(BeanUtils.toBean(evalResultSystemDock, EvalResultSystemDockRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结果系统对接分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:query')")
    public CommonResult<PageResult<EvalResultSystemDockRespVO>> getEvalResultSystemDockPage(@Valid EvalResultSystemDockPageReqVO pageReqVO) {
        PageResult<EvalResultSystemDockDO> pageResult = evalResultSystemDockService.getEvalResultSystemDockPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalResultSystemDockRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出结果系统对接 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-result-system-dock:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalResultSystemDockExcel(@Valid EvalResultSystemDockPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalResultSystemDockDO> list = evalResultSystemDockService.getEvalResultSystemDockPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "结果系统对接.xls", "数据", EvalResultSystemDockRespVO.class,
                        BeanUtils.toBean(list, EvalResultSystemDockRespVO.class));
    }

}