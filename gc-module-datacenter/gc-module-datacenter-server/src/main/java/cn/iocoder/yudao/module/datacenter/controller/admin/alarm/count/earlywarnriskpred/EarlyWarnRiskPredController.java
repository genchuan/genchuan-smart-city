package cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred;

import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnriskpred.vo.EarlyWarnRiskPredSaveReqVO;
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

import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnriskpred.EarlyWarnRiskPredDO;
import cn.iocoder.yudao.module.datacenter.service.alarm.count.earlywarnriskpred.EarlyWarnRiskPredService;

@Tag(name = "管理后台 - 预警告警风险趋势预测")
@RestController
@RequestMapping("/datacenter/early-warn-risk-pred")
@Validated
public class EarlyWarnRiskPredController {

    @Resource
    private EarlyWarnRiskPredService earlyWarnRiskPredService;

    @PostMapping("/create")
    @Operation(summary = "创建预警告警风险趋势预测")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:create')")
    public CommonResult<Long> createEarlyWarnRiskPred(@Valid @RequestBody EarlyWarnRiskPredSaveReqVO createReqVO) {
        return success(earlyWarnRiskPredService.createEarlyWarnRiskPred(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新预警告警风险趋势预测")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:update')")
    public CommonResult<Boolean> updateEarlyWarnRiskPred(@Valid @RequestBody EarlyWarnRiskPredSaveReqVO updateReqVO) {
        earlyWarnRiskPredService.updateEarlyWarnRiskPred(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除预警告警风险趋势预测")
    @Parameter(name = "id", description = "编号", required = true)
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:delete')")
    public CommonResult<Boolean> deleteEarlyWarnRiskPred(@RequestParam("id") Long id) {
        earlyWarnRiskPredService.deleteEarlyWarnRiskPred(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得预警告警风险趋势预测")
    @Parameter(name = "id", description = "编号", required = true, example = "1024")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:query')")
    public CommonResult<EarlyWarnRiskPredRespVO> getEarlyWarnRiskPred(@RequestParam("id") Long id) {
        EarlyWarnRiskPredDO earlyWarnRiskPred = earlyWarnRiskPredService.getEarlyWarnRiskPred(id);
        return success(BeanUtils.toBean(earlyWarnRiskPred, EarlyWarnRiskPredRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得预警告警风险趋势预测分页")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:query')")
    public CommonResult<PageResult<EarlyWarnRiskPredRespVO>> getEarlyWarnRiskPredPage(@Valid EarlyWarnRiskPredPageReqVO pageReqVO) {
        PageResult<EarlyWarnRiskPredDO> pageResult = earlyWarnRiskPredService.getEarlyWarnRiskPredPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, EarlyWarnRiskPredRespVO.class));
    }

    @GetMapping("/export-excel")
    @Operation(summary = "导出预警告警风险趋势预测 Excel")
    @PreAuthorize("@ss.hasPermission('datacenter:early-warn-risk-pred:export')")
    @ApiAccessLog(operateType = EXPORT)
    public void exportEarlyWarnRiskPredExcel(@Valid EarlyWarnRiskPredPageReqVO pageReqVO,
              HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<EarlyWarnRiskPredDO> list = earlyWarnRiskPredService.getEarlyWarnRiskPredPage(pageReqVO).getList();
        // 导出 Excel
        ExcelUtils.write(response, "预警告警风险趋势预测.xls", "数据", EarlyWarnRiskPredRespVO.class,
                        BeanUtils.toBean(list, EarlyWarnRiskPredRespVO.class));
    }

}