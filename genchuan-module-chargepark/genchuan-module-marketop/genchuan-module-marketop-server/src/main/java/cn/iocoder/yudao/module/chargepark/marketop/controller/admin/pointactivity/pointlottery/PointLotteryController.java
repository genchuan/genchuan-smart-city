package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointlottery.PointLotteryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Tag(name = "管理后台 - 积分抽奖")
@RestController
@RequestMapping("/marketop/point-lottery")
public class PointLotteryController {

    @Resource
    private PointLotteryService pointLotteryService;

    @GetMapping("/page")
    @Operation(summary = "获得积分抽奖分页")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PageResult<PointLotteryRespVO>> getPage(PointLotteryPageReqVO reqVO) {
        PageResult<PointLotteryDO> pageResult = pointLotteryService.getPage(reqVO);
        return CommonResult.success(BeanUtils.toBean(pageResult, PointLotteryRespVO.class));
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分抽奖详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PointLotteryRespVO> get(@RequestParam("id") Long id) {
        PointLotteryDO lottery = pointLotteryService.get(id);
        return CommonResult.success(BeanUtils.toBean(lottery, PointLotteryRespVO.class));
    }

    @PutMapping("/check")
    @Operation(summary = "核查积分抽奖记录")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<Boolean> check(@RequestParam("id") Long id, @RequestParam("checkResult") String checkResult) {
        pointLotteryService.check(id, checkResult);
        return CommonResult.success(true);
    }

    @GetMapping("/export")
    @Operation(summary = "导出积分抽奖记录")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public void export(PointLotteryPageReqVO reqVO, HttpServletResponse response) throws IOException {
        reqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<PointLotteryDO> pageResult = pointLotteryService.getPage(reqVO);
        List<PointLotteryRespVO> list = BeanUtils.toBean(pageResult.getList(), PointLotteryRespVO.class);
        ExcelUtils.write(response, "积分抽奖.xlsx", "数据", PointLotteryRespVO.class, list);
    }

    @GetMapping("/chart")
    @Operation(summary = "积分抽奖图表统计")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PointLotteryChartRespVO> getChart(@RequestParam(value = "timeRange", required = false) String timeRange) {
        return CommonResult.success(pointLotteryService.getChart(timeRange));
    }

}
