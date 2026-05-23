package cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle;

import cn.iocoder.yudao.framework.apilog.core.annotation.ApiAccessLog;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.splitsetttle.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.splitsetttle.SplitRateDO;
import cn.iocoder.yudao.module.ordertrade.framework.utils.UserNameInjector;
import cn.iocoder.yudao.module.ordertrade.service.splitsetttle.SplitRateService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static cn.iocoder.yudao.framework.apilog.core.enums.OperateTypeEnum.UPDATE;
import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

@Tag(name = "订单交易 - 分账结算 - 分账比例")
@RestController
@RequestMapping("/ordertrade/split-rate")
@Validated
public class SplitRateController {

    @Resource
    private SplitRateService splitRateService;

    @Resource
    private AdminUserApi adminUserApi;

    @PostMapping("/create")
    @Operation(summary = "创建分账比例")
    public CommonResult<Long> createSplitRate(@Valid @RequestBody SplitRateSaveReqVO createReqVO) {
        return success(splitRateService.createSplitRate(createReqVO));
    }

    @PutMapping("/update")
    @Operation(summary = "更新分账比例")
    public CommonResult<Boolean> updateSplitRate(@Valid @RequestBody SplitRateSaveReqVO updateReqVO) {
        splitRateService.updateSplitRate(updateReqVO);
        return success(true);
    }

    @DeleteMapping("/delete")
    @Operation(summary = "删除分账比例")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> deleteSplitRate(@RequestParam("id") Long id) {
        splitRateService.deleteSplitRate(id);
        return success(true);
    }

    @GetMapping("/get")
    @Operation(summary = "获得分账比例详情")
    @Parameter(name = "id", description = "主键", required = true, example = "1024")
    public CommonResult<SplitRateRespVO> getSplitRate(@RequestParam("id") Long id) {
        SplitRateDO obj = splitRateService.getSplitRate(id);
        SplitRateRespVO respVO = BeanUtils.toBean(obj, SplitRateRespVO.class);
        if (respVO != null) {
            injectAuditorName(List.of(respVO));
        }
        return success(respVO);
    }

    @GetMapping("/page")
    @Operation(summary = "获得分账比例分页列表")
    public CommonResult<PageResult<SplitRateRespVO>> getSplitRatePage(@Valid SplitRatePageReqVO pageReqVO) {
        PageResult<SplitRateDO> pageResult = splitRateService.getSplitRatePage(pageReqVO);
        PageResult<SplitRateRespVO> respPage = BeanUtils.toBean(pageResult, SplitRateRespVO.class);
        injectAuditorName(respPage.getList());
        return success(respPage);
    }

    @PutMapping("/enable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "生效分账比例")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> enableSplitRate(@RequestParam("id") Long id) {
        splitRateService.enableSplitRate(id);
        return success(true);
    }

    @PutMapping("/disable")
    @ApiAccessLog(operateType = UPDATE)
    @Operation(summary = "禁用分账比例")
    @Parameter(name = "id", description = "主键", required = true)
    public CommonResult<Boolean> disableSplitRate(@RequestParam("id") Long id) {
        splitRateService.disableSplitRate(id);
        return success(true);
    }

    @GetMapping("/chart")
    @Operation(summary = "获得分账比例统计图表数据")
    public CommonResult<SplitRateChartRespVO> getSplitRateChart(@Valid SplitRateChartReqVO chartReqVO) {
        return success(splitRateService.getSplitRateChart(chartReqVO));
    }

    private void injectAuditorName(List<SplitRateRespVO> list) {
        UserNameInjector.inject(list, adminUserApi,
                UserNameInjector.field(SplitRateRespVO::getAuditorId, SplitRateRespVO::setAuditorName));
    }
}
