package cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.refundmgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.refundmgmt.AmountCheckDO;
import cn.iocoder.yudao.module.ordertrade.service.refundmgmt.AmountCheckService;
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
 * 管理后台 - 金额核算
 *
 * @author genchuan
 */
@Tag(name = "订单交易 -退款管理- 金额核算")
@RestController
@RequestMapping("/ordertrade/amount-check")
@Validated
public class AmountCheckController {

    @Resource
    private AmountCheckService amountCheckService;

    // ==================== ① 标准CRUD ====================

   /* @PostMapping("/create")
    @Operation(summary = "创建金额核算")
    public CommonResult<Long> createAmountCheck(@Valid @RequestBody AmountCheckSaveReqVO createReqVO) {
        return success(amountCheckService.createAmountCheck(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新金额核算")
    public CommonResult<Boolean> updateAmountCheck(@Valid @RequestBody AmountCheckSaveReqVO updateReqVO) {
        amountCheckService.updateAmountCheck(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除金额核算")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteAmountCheck(@RequestParam("id") Long id) {
        amountCheckService.deleteAmountCheck(id);
        return success(true);
    }

    @DeleteMapping("/delete-list")
    @Operation(summary = "批量删除金额核算")
    @Parameter(name = "ids", description = "主键列表", required = true)
    public CommonResult<Boolean> deleteAmountCheckList(@RequestParam("ids") List<Long> ids) {
        amountCheckService.deleteAmountCheckListByIds(ids);
        return success(true);
    }
*/
    @GetMapping("/get")
    @Operation(summary = "获得金额核算详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<AmountCheckRespVO> getAmountCheck(@RequestParam("id") Long id) {
        AmountCheckDO obj = amountCheckService.getAmountCheck(id);
        return success(BeanUtils.toBean(obj, AmountCheckRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得金额核算分页列表")
    public CommonResult<PageResult<AmountCheckRespVO>> getAmountCheckPage(@Valid AmountCheckPageReqVO pageReqVO) {
        PageResult<AmountCheckDO> pageResult = amountCheckService.getAmountCheckPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, AmountCheckRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出金额核算 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportAmountCheckExcel(@Valid AmountCheckPageReqVO pageReqVO,
                                 HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AmountCheckDO> list = amountCheckService.getAmountCheckPage(pageReqVO).getList();
        ExcelUtils.write(response, "金额核算.xls", "数据", AmountCheckRespVO.class,
                BeanUtils.toBean(list, AmountCheckRespVO.class));
    }

   /* @GetMapping("/batch-export")
    @Operation(summary = "批量导出金额核算 Excel（芋道原生导出风格）")
    @ApiAccessLog(operateType = EXPORT)
    public void batchExportAmountCheckExcel(@Valid AmountCheckPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<AmountCheckDO> list = amountCheckService.getAmountCheckPage(pageReqVO).getList();
        ExcelUtils.write(response, "金额核算批量导出.xls", "数据", AmountCheckRespVO.class,
                BeanUtils.toBean(list, AmountCheckRespVO.class));
    }*/

    // ==================== ② 业务操作接口 ====================

    @PostMapping("/check")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "金额核算")
    public CommonResult<Boolean> checkAmountCheck(@Valid @RequestBody IdReqVO reqVO) {
        amountCheckService.checkAmountCheck(reqVO);
        return success(true);
    }

    @PostMapping("/calculate")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "批量核算")
    public CommonResult<Boolean> doCheckAmountCheck(@Valid @RequestBody IdsReqVO reqVO) {
        amountCheckService.doCheckAmountCheck(reqVO);
        return success(true);
    }
    @PutMapping("/confirm")
    @ApiAccessLog(operateType = UPDATE)  // ← 加这一行
    @Operation(summary = "确认核算结果")
    public CommonResult<Boolean> confirmAmountCheck(@Valid @RequestBody IdReqVO reqVO) {
        amountCheckService.confirmAmountCheck(reqVO);
        return success(true);
    }

    // ==================== ③ 数据可视化接口 ====================

    @GetMapping("/chart")
    @Operation(summary = "获得金额核算统计图表数据（折线图+柱状图/饼图+卡片）")
    public CommonResult<AmountCheckChartRespVO> getAmountCheckChart(@Valid AmountCheckChartReqVO chartReqVO) {
        return success(amountCheckService.getAmountCheckChart(chartReqVO));
    }
}
