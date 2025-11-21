package cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata;

import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.data.evalrealtimedata.vo.EvalRealTimeDataSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.data.evalrealtimedata.EvalRealTimeDataDO;
import cn.iocoder.yudao.module.datacenter.service.evaluate.data.evalrealtimedata.EvalRealTimeDataService;

@Tag(name = "管理后台 - 实时监测数据接入")
@RestController
@RequestMapping("/datacenter/eval-real-time-data")
@Validated
public class EvalRealTimeDataController {

    @Resource
    private EvalRealTimeDataService evalRealTimeDataService;

    @PostMapping("/create")
    @Operation(summary = "创建实时监测数据接入")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:create')")
    public CommonResult<Long> createEvalRealTimeData(@Valid @RequestBody EvalRealTimeDataSaveReqVO createReqVO) {
        return success(evalRealTimeDataService.createEvalRealTimeData(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新实时监测数据接入")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:update')")
    public CommonResult<Boolean> updateEvalRealTimeData(@Valid @RequestBody EvalRealTimeDataSaveReqVO updateReqVO) {
        evalRealTimeDataService.updateEvalRealTimeData(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除实时监测数据接入")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:delete')")
    public CommonResult<Boolean> deleteEvalRealTimeData(@RequestParam("id") Long id) {
        evalRealTimeDataService.deleteEvalRealTimeData(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得实时监测数据接入")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:query')")
    public CommonResult<EvalRealTimeDataRespVO> getEvalRealTimeData(@RequestParam("id") Long id) {
        EvalRealTimeDataDO evalRealTimeData = evalRealTimeDataService.getEvalRealTimeData(id);
        return success(BeanUtils.toBean(evalRealTimeData, EvalRealTimeDataRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得实时监测数据接入分页")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:query')")
    public CommonResult<PageResult<EvalRealTimeDataRespVO>> getEvalRealTimeDataPage(@Valid EvalRealTimeDataPageReqVO pageReqVO) {
        PageResult<EvalRealTimeDataDO> pageResult = evalRealTimeDataService.getEvalRealTimeDataPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EvalRealTimeDataRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出实时监测数据接入 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:eval-real-time-data:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEvalRealTimeDataExcel(@Valid EvalRealTimeDataPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EvalRealTimeDataDO> list = evalRealTimeDataService.getEvalRealTimeDataPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "实时监测数据接入.xls", "数据", EvalRealTimeDataRespVO.class,
                        BeanUtils.toBean(list, EvalRealTimeDataRespVO.class));
    }

}