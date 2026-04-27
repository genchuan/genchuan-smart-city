package cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.excel.core.util.ExcelUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointlottery.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.prizemgmt.PrizeMgmtService;
import cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.pointlottery.PointLotteryService;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@Tag(name = "管理后台 - 积分抽奖")
@RestController
@RequestMapping("/marketop/point-lottery")
public class PointLotteryController {

    @Resource
    private PointLotteryService pointLotteryService;

    @Resource
    private AdminUserApi adminUserApi;

    @Resource
    private PrizeMgmtService prizeMgmtService;

    @GetMapping("/page")
    @Operation(summary = "获得积分抽奖分页")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PageResult<PointLotteryRespVO>> getPage(PointLotteryPageReqVO reqVO) {
        // 如果startTime和endTime都为null，且lotteryTime不为空，将lotteryTime转为当天开始和结束时间
        if (reqVO.getStartTime() == null && reqVO.getEndTime() == null
                && reqVO.getLotteryTime() != null && !reqVO.getLotteryTime().isEmpty()) {
            java.time.LocalDate date = java.time.LocalDate.parse(reqVO.getLotteryTime());
            reqVO.setStartTime(date.atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            reqVO.setEndTime(date.plusDays(1).atStartOfDay().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
        }
        PageResult<PointLotteryDO> pageResult = pointLotteryService.getPage(reqVO);
        PageResult<PointLotteryRespVO> bean = BeanUtils.toBean(pageResult, PointLotteryRespVO.class);
        injectNames(bean.getList());
        return CommonResult.success(bean);
    }

    @GetMapping("/get")
    @Operation(summary = "获得积分抽奖详情")
    @Parameter(name = "id", description = "主键ID", required = true)
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<PointLotteryRespVO> get(@RequestParam("id") Long id) {
        PointLotteryDO lottery = pointLotteryService.get(id);
        PointLotteryRespVO respVO = BeanUtils.toBean(lottery, PointLotteryRespVO.class);
        if (respVO != null) injectNames(Collections.singletonList(respVO));
        return CommonResult.success(respVO);
    }

    @PutMapping("/check")
    @Operation(summary = "核查积分抽奖记录")
    @PreAuthorize("@ss.hasPermission('marketop:point-lottery:query')")
    public CommonResult<Boolean> check(@Valid @RequestBody PointLotteryCheckReqVO reqVO) {
        pointLotteryService.check(reqVO.getId(), reqVO.getCheckResult());
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
    public CommonResult<PointLotteryChartRespVO> getChart() {
        return CommonResult.success(pointLotteryService.getChart());
    }

    private void injectNames(List<PointLotteryRespVO> list) {
        if (list == null || list.isEmpty()) return;
        // 收集用户ID（userId + senderId）
        Set<Long> userIds = new HashSet<>();
        Set<Long> prizeIds = new HashSet<>();
        for (var item : list) {
            if (item.getUserId() != null) userIds.add(item.getUserId());
            if (item.getSenderId() != null) userIds.add(item.getSenderId());
            if (item.getPrizeId() != null) prizeIds.add(item.getPrizeId());
        }
        // 翻译用户名称
        if (!userIds.isEmpty()) {
            Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
            for (var item : list) {
                if (item.getUserId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getUserId());
                    if (user != null) item.setUserName(user.getNickname());
                }
                if (item.getSenderId() != null) {
                    AdminUserRespDTO user = userMap.get(item.getSenderId());
                    if (user != null) item.setSenderName(user.getNickname());
                }
            }
        }
        // 翻译奖品名称
        if (!prizeIds.isEmpty()) {
            Map<Long, String> prizeNameMap = new HashMap<>();
            for (Long prizeId : prizeIds) {
                PrizeMgmtDO prize = prizeMgmtService.get(prizeId);
                if (prize != null) prizeNameMap.put(prizeId, prize.getName());
            }
            for (var item : list) {
                if (item.getPrizeId() != null) {
                    String name = prizeNameMap.get(item.getPrizeId());
                    if (name != null) item.setPrizeName(name);
                }
            }
        }
    }

}
