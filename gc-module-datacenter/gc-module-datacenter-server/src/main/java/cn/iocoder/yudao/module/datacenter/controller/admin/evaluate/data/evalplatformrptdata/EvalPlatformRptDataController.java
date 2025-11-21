package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalplatformrptdata.vo.EvalPlatformRptDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalplatformrptdata.EvalPlatformRptDataDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalplatformrptdata.EvalPlatformRptDataService;

@Tag(name = "管理后台 - 平台上报数据")
@RestController
@RequestMapping("/datacenter/eval-platform-rpt-data")
@Validated
public class EvalPlatformRptDataController {

    @Resource
    private EvalPlatformRptDataService evalPlatformRptDataService;

    @PostMapping("/create")
    @Operation(summary = "创建平台上报数据")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:create')")
    public CommonResult<Long> createEvalPlatformRptData(@Valid @RequestBody EvalPlatformRptDataSaveReqVO createReqVO) {
        return success(evalPlatformRptDataService.createEvalPlatformRptData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新平台上报数据")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:update')")
    public CommonResult<Boolean> updateEvalPlatformRptData(@Valid @RequestBody EvalPlatformRptDataSaveReqVO updateReqVO) {
        evalPlatformRptDataService.updateEvalPlatformRptData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除平台上报数据")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:delete')")
    public CommonResult<Boolean> deleteEvalPlatformRptData(@RequestParam("id") Long id) {
        evalPlatformRptDataService.deleteEvalPlatformRptData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得平台上报数据")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:query')")
    public CommonResult<EvalPlatformRptDataRespVO> getEvalPlatformRptData(@RequestParam("id") Long id) {
        EvalPlatformRptDataDO evalPlatformRptData = evalPlatformRptDataService.getEvalPlatformRptData(id);
        return success(BeanUtils.toBean(evalPlatformRptData, EvalPlatformRptDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得平台上报数据分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:query')")
    public CommonResult<PageResult<EvalPlatformRptDataRespVO>> getEvalPlatformRptDataPage(@Valid EvalPlatformRptDataPageReqVO pageReqVO) {
        PageResult<EvalPlatformRptDataDO> pageResult = evalPlatformRptDataService.getEvalPlatformRptDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalPlatformRptDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出平台上报数据 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-platform-rpt-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalPlatformRptDataExcel(@Valid EvalPlatformRptDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalPlatformRptDataDO> list = evalPlatformRptDataService.getEvalPlatformRptDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "平台上报数据.xls", "数据", EvalPlatformRptDataRespVO.class,
                        BeanUtils.toBean(list, EvalPlatformRptDataRespVO.class));
    }

}