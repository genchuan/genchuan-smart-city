package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleBillDO;
import cn.iocoder.yudao.module.ordertrade.service.splitsetttle.SettleBillService;
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

@Tag(name = "订单交易 - 分账结算 - 结算单据")
@RestController
@RequestMapping("/ordertrade/settle-bill")
@Validated
public class SettleBillController {

    @Resource
    private SettleBillService settleBillService;

    @PostMapping("/create")
    @Operation(summary = "创建结算单据")
    public CommonResult<Long> createSettleBill(@Valid @RequestBody SettleBillSaveReqVO createReqVO) {
        return success(settleBillService.createSettleBill(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结算单据")
    public CommonResult<Boolean> updateSettleBill(@Valid @RequestBody SettleBillSaveReqVO updateReqVO) {
        settleBillService.updateSettleBill(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结算单据")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteSettleBill(@RequestParam("id") Long id) {
        settleBillService.deleteSettleBill(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结算单据详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<SettleBillRespVO> getSettleBill(@RequestParam("id") Long id) {
        SettleBillDO obj = settleBillService.getSettleBill(id);
        return success(BeanUtils.toBean(obj, SettleBillRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结算单据分页列表")
    public CommonResult<PageResult<SettleBillRespVO>> getSettleBillPage(@Valid SettleBillPageReqVO pageReqVO) {
        PageResult<SettleBillDO> pageResult = settleBillService.getSettleBillPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SettleBillRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出结算单据 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSettleBillExcel(@Valid SettleBillPageReqVO pageReqVO,
                                      HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SettleBillDO> list = settleBillService.getSettleBillPage(pageReqVO).getList();
        ExcelUtils.write(response, "结算单据.xls", "数据", SettleBillRespVO.class,
                BeanUtils.toBean(list, SettleBillRespVO.class));
    }

    @PostMapping("/audit-pass")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "通过结算单据")
    public CommonResult<Boolean> approveSettleBill(@Valid @RequestBody IdReqVO reqVO) {
        settleBillService.approveSettleBill(reqVO);
        return success(true);
    }

    @PostMapping("/audit-reject")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "驳回结算单据")
    public CommonResult<Boolean> rejectSettleBill(@Valid @RequestBody IdReqVO reqVO) {
        settleBillService.rejectSettleBill(reqVO);
        return success(true);
    }

    @PostMapping("/settle")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "结算")
    public CommonResult<Boolean> settleSettleBill(@Valid @RequestBody IdReqVO reqVO) {
        settleBillService.settleSettleBill(reqVO);
        return success(true);
    }

    @PostMapping("/regenerate")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "重新生成结算单据")
    public CommonResult<Boolean> regenerateSettleBill(@Valid @RequestBody IdReqVO reqVO) {
        settleBillService.regenerateSettleBill(reqVO);
        return success(true);
    }

    @PostMapping("/batch-audit")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "批量审核结算单据")
    public CommonResult<Boolean> batchAuditSettleBill(@RequestBody List<Long> ids) {
        settleBillService.batchAuditSettleBill(ids);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得结算单据统计图表数据")
    public CommonResult<SettleBillChartRespVO> getSettleBillChart(@Valid SettleBillChartReqVO chartReqVO) {
        return success(settleBillService.getSettleBillChart(chartReqVO));
    }
}
