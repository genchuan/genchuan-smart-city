package cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.debtcollect.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.debtcollect.DebtIdentifyDO;
import cn.iocoder.yudao.module.ordertrade.service.debtcollect.DebtIdentifyService;
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
 * 管理后台 联合追缴- 逃费识别
 *
 * @author genchuan
 */
@Tag(name = "管理后台 -联合追缴- 逃费识别")
@RestController
@RequestMapping("/ordertrade/debt-identify")
@Validated
public class DebtIdentifyController {

    @Resource
    private DebtIdentifyService debtIdentifyService;

    // ==================== ① 标准CRUD ====================
/*
    @PostMapping("/create")
    @Operation(summary = "创建逃费识别")
    public CommonResult<Long> createDebtIdentify(@Valid @RequestBody DebtIdentifySaveReqVO createReqVO) {
        return success(debtIdentifyService.createDebtIdentify(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新逃费识别")
    public CommonResult<Boolean> updateDebtIdentify(@Valid @RequestBody DebtIdentifySaveReqVO updateReqVO) {
        debtIdentifyService.updateDebtIdentify(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除逃费识别")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteDebtIdentify(@RequestParam("id") Long id) {
        debtIdentifyService.deleteDebtIdentify(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除逃费识别")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteDebtIdentifyList(@RequestParam("ids") List<Long> ids) {
        debtIdentifyService.deleteDebtIdentifyListByIds(ids);
        return success(true);
    }*/

    @GetMapping("/get")
    @Operation(summary = "获得逃费识别详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<DebtIdentifyRespVO> getDebtIdentify(@RequestParam("id") Long id) {
        DebtIdentifyDO obj = debtIdentifyService.getDebtIdentify(id);
        return success(BeanUtils.toBean(obj, DebtIdentifyRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得逃费识别分页列表")
    public CommonResult<PageResult<DebtIdentifyRespVO>> getDebtIdentifyPage(@Valid DebtIdentifyPageReqVO pageReqVO) {
        PageResult<DebtIdentifyDO> pageResult = debtIdentifyService.getDebtIdentifyPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, DebtIdentifyRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出逃费识别 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportDebtIdentifyExcel(@Valid DebtIdentifyPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DebtIdentifyDO> list = debtIdentifyService.getDebtIdentifyPage(pageReqVO).getList();
        ExcelUtils.write(response, "逃费识别.xls", "数据", DebtIdentifyRespVO.class,
                BeanUtils.toBean(list, DebtIdentifyRespVO.class));
    }

    /*@GetMapping("/batch-export")
    @Operation(summary = "批量导出逃费识别 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportDebtIdentifyExcel(@Valid DebtIdentifyPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<DebtIdentifyDO> list = debtIdentifyService.getDebtIdentifyPage(pageReqVO).getList();
        ExcelUtils.write(response, "逃费识别批量导出.xls", "数据", DebtIdentifyRespVO.class,
                BeanUtils.toBean(list, DebtIdentifyRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PutMapping("/identify")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "识别逃费")
    public CommonResult<Boolean> identifyDebtIdentify(@Valid @RequestBody IdReqVO reqVO) {
        debtIdentifyService.identifyDebtIdentify(reqVO);
        return success(true);
    }
    @PutMapping("/mark")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "标记欠费")
    public CommonResult<Boolean> markDebtIdentify(@Valid @RequestBody IdReqVO reqVO) {
        debtIdentifyService.markDebtIdentify(reqVO);
        return success(true);
    }
    @PostMapping("/batch-identify")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "批量识别")
    public CommonResult<Boolean> batchIdentifyDebtIdentify(@Valid @RequestBody IdsReqVO reqVO) {
        debtIdentifyService.batchIdentifyDebtIdentify(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得逃费识别统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<DebtIdentifyChartRespVO> getDebtIdentifyChart(@Valid DebtIdentifyChartReqVO chartReqVO) {
        return success(debtIdentifyService.getDebtIdentifyChart(chartReqVO));
    }
}
