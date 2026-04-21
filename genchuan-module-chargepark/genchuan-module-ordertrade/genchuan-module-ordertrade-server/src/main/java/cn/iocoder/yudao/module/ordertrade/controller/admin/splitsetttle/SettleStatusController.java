package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.ordermgmt.vo.IdReqVO;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SettleStatusDO;
import cn.iocoder.yudao.module.ordertrade.service.splitsetttle.SettleStatusService;
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

@Tag(name = "订单交易 - 分账结算 - 结算状态")
@RestController
@RequestMapping("/ordertrade/settle-status")
@Validated
public class SettleStatusController {

    @Resource
    private SettleStatusService settleStatusService;

    @PostMapping("/create")
    @Operation(summary = "创建结算状态")
    public CommonResult<Long> createSettleStatus(@Valid @RequestBody SettleStatusSaveReqVO createReqVO) {
        return success(settleStatusService.createSettleStatus(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新结算状态")
    public CommonResult<Boolean> updateSettleStatus(@Valid @RequestBody SettleStatusSaveReqVO updateReqVO) {
        settleStatusService.updateSettleStatus(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除结算状态")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteSettleStatus(@RequestParam("id") Long id) {
        settleStatusService.deleteSettleStatus(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得结算状态详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<SettleStatusRespVO> getSettleStatus(@RequestParam("id") Long id) {
        SettleStatusDO obj = settleStatusService.getSettleStatus(id);
        return success(BeanUtils.toBean(obj, SettleStatusRespVO.class));
    }

    @GetMapping("/page")
    @Operation(summary = "获得结算状态分页列表")
    public CommonResult<PageResult<SettleStatusRespVO>> getSettleStatusPage(@Valid SettleStatusPageReqVO pageReqVO) {
        PageResult<SettleStatusDO> pageResult = settleStatusService.getSettleStatusPage(pageReqVO);
        return success(BeanUtils.toBean(pageResult, SettleStatusRespVO.class));
    }

    @GetMapping("/export")
    @Operation(summary = "导出结算状态 Excel")
    @ApiAccessLog(operateType = EXPORT)
    public void exportSettleStatusExcel(@Valid SettleStatusPageReqVO pageReqVO,
                                        HttpServletResponse response) throws IOException {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        List<SettleStatusDO> list = settleStatusService.getSettleStatusPage(pageReqVO).getList();
        ExcelUtils.write(response, "结算状态.xls", "数据", SettleStatusRespVO.class,
                BeanUtils.toBean(list, SettleStatusRespVO.class));
    }

    @PutMapping("/check")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "核查结算状态")
    public CommonResult<Boolean> checkSettleStatus(@Valid @RequestBody IdReqVO reqVO) {
        settleStatusService.checkSettleStatus(reqVO);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得结算状态统计图表数据")
    public CommonResult<SettleStatusChartRespVO> getSettleStatusChart(@Valid SettleStatusChartReqVO chartReqVO) {
        return success(settleStatusService.getSettleStatusChart(chartReqVO));
    }
}
