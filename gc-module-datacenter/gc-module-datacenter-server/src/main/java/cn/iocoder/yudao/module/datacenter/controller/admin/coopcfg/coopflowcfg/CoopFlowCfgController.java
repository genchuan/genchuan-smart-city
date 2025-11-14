package cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopflowcfg.CoopFlowCfgDO;
import cn.iocoder.yudao.module.datacenter.service.coopcfg.coopflowcfg.CoopFlowCfgService;

@Tag(name = "管理后台 - 联动流程配置")
@RestController
@RequestMapping("/datacenter/coop-flow-cfg")
@Validated
public class CoopFlowCfgController {

    @Resource
    private CoopFlowCfgService coopFlowCfgService;

    @PostMapping("/create")
    @Operation(summary = "创建联动流程配置")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:create')")
    public CommonResult<Long> createCoopFlowCfg(@Valid @RequestBody CoopFlowCfgSaveReqVO createReqVO) {
        return success(coopFlowCfgService.createCoopFlowCfg(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新联动流程配置")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:update')")
    public CommonResult<Boolean> updateCoopFlowCfg(@Valid @RequestBody CoopFlowCfgSaveReqVO updateReqVO) {
        coopFlowCfgService.updateCoopFlowCfg(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除联动流程配置")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:delete')")
    public CommonResult<Boolean> deleteCoopFlowCfg(@RequestParam("id") Long id) {
        coopFlowCfgService.deleteCoopFlowCfg(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得联动流程配置")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:query')")
    public CommonResult<CoopFlowCfgRespVO> getCoopFlowCfg(@RequestParam("id") Long id) {
        CoopFlowCfgDO coopFlowCfg = coopFlowCfgService.getCoopFlowCfg(id);
        return success(BeanUtils.toBean(coopFlowCfg, CoopFlowCfgRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得联动流程配置分页")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:query')")
    public CommonResult<PageResult<CoopFlowCfgRespVO>> getCoopFlowCfgPage(@Valid CoopFlowCfgPageReqVO pageReqVO) {
        PageResult<CoopFlowCfgDO> pageResult = coopFlowCfgService.getCoopFlowCfgPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, CoopFlowCfgRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出联动流程配置 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:coop-flow-cfg:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportCoopFlowCfgExcel(@Valid CoopFlowCfgPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<CoopFlowCfgDO> list = coopFlowCfgService.getCoopFlowCfgPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "联动流程配置.xls", "数据", CoopFlowCfgRespVO.class,
                        BeanUtils.toBean(list, CoopFlowCfgRespVO.class));
    }

}