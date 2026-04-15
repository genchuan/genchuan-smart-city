package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtRecordDO;
import cn.iocoder.yudao.module.ordertrade.service.debtcollect.DebtRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.EXPORT;
import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 管理后台 - 逃费记录
 *
 * @author genchuan
 */
@Tag(name = "管理后台 -联合追缴- 逃费记录")
@RestController
@RequestMapping("/ordertrade/debt-record")
@Validated
public class DebtRecordController {

    @Resource
    private DebtRecordService debtRecordService;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建逃费记录")
    public CommonResult<Long> createDebtRecord(@Valid @RequestBody DebtRecordSaveReqVO createReqVO) {
        return success(debtRecordService.createDebtRecord(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新逃费记录")
    public CommonResult<Boolean> updateDebtRecord(@Valid @RequestBody DebtRecordSaveReqVO updateReqVO) {
        debtRecordService.updateDebtRecord(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除逃费记录")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteDebtRecord(@RequestParam("id") Long id) {
        debtRecordService.deleteDebtRecord(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除逃费记录")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteDebtRecordList(@RequestParam("ids") List<Long> ids) {
        debtRecordService.deleteDebtRecordListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得逃费记录详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<DebtRecordRespVO> getDebtRecord(@RequestParam("id") Long id) {
        DebtRecordDO obj = debtRecordService.getDebtRecord(id);
        return success(BeanUtils.toBean(obj, DebtRecordRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得逃费记录分页列表")
    public CommonResult<PageResult<DebtRecordRespVO>> getDebtRecordPage(@Valid DebtRecordPageReqVO pageReqVO) {
        PageResult<DebtRecordDO> pageResult = debtRecordService.getDebtRecordPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DebtRecordRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出逃费记录 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDebtRecordExcel(@Valid DebtRecordPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DebtRecordDO> list = debtRecordService.getDebtRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "逃费记录.xls", "数据", DebtRecordRespVO.class,
                BeanUtils.toBean(list, DebtRecordRespVO.class));
    }

  /*  @GetMapping("/batch-export")
    @Operation(summary = "批量导出逃费记录 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportDebtRecordExcel(@Valid DebtRecordPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DebtRecordDO> list = debtRecordService.getDebtRecordPage(pageReqVO).getList();
        ExcelUtils.write(response, "逃费记录批量导出.xls", "数据", DebtRecordRespVO.class,
                BeanUtils.toBean(list, DebtRecordRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/start-collect")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "发起追缴")
    public CommonResult<Boolean> startCollectDebtRecord(@Valid @RequestBody IdReqVO reqVO) {
        debtRecordService.startCollectDebtRecord(reqVO);
        return success(true);
    }
    @PutMapping("/update-progress")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "更新追缴进度")
    public CommonResult<Boolean> updateProgressDebtRecord(@Valid @RequestBody IdReqVO reqVO) {
        debtRecordService.updateProgressDebtRecord(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得逃费记录统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<DebtRecordChartRespVO> getDebtRecordChart(@Valid DebtRecordChartReqVO chartReqVO) {
        return success(debtRecordService.getDebtRecordChart(chartReqVO));
    }
}
