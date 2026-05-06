package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.chart;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartBarDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartLineDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartPieDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ActivityConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Validated
public class CycleReportChartServiceImpl implements CycleReportChartService {

    @Resource
    private PointActivityMapper pointActivityMapper;
    @Resource
    private PointLotteryMapper pointLotteryMapper;
    @Resource
    private CouponMgmtMapper couponMgmtMapper;
    @Resource
    private CardConfigMapper cardConfigMapper;
    @Resource
    private PrizeMgmtMapper prizeMgmtMapper;
    @Resource
    private ActivityConfigMapper activityConfigMapper;
    @Resource
    private ExchangeOrderMapper exchangeOrderMapper;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public PageResult<Map<String, Object>> lineDrill(CycleReportChartLineDrillReqVO reqVO) {
        // 解析日期，支持 "2026-04" 格式
        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            YearMonth ym = YearMonth.parse(reqVO.getDate());
            startTime = ym.atDay(1).atStartOfDay();
            endTime = ym.atEndOfMonth().atTime(LocalTime.MAX);
        } catch (Exception e) {
            // 尝试按完整日期解析 "2026-04-29"
            LocalDate ld = LocalDate.parse(reqVO.getDate());
            startTime = ld.atStartOfDay();
            endTime = ld.atTime(LocalTime.MAX);
        }

        // 查询该时段内的抽奖记录，按用户分组取最早记录
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.ge(PointLotteryDO::getLotteryTime, startTime);
        wrapper.le(PointLotteryDO::getLotteryTime, endTime);
        wrapper.orderByDesc(PointLotteryDO::getId);
        List<PointLotteryDO> allRecords = pointLotteryMapper.selectList(wrapper);

        // 按userId分组取最早记录
        Map<Long, PointLotteryDO> firstByUser = new LinkedHashMap<>();
        for (PointLotteryDO r : allRecords) {
            firstByUser.putIfAbsent(r.getUserId(), r);
        }

        // 手动分页
        int total = firstByUser.size();
        int fromIndex = (reqVO.getPageNo() - 1) * reqVO.getPageSize();
        int toIndex = Math.min(fromIndex + reqVO.getPageSize(), total);
        List<PointLotteryDO> pagedRecords = new ArrayList<>(firstByUser.values())
                .subList(Math.min(fromIndex, total), toIndex);

        // 批量获取用户信息
        Set<Long> userIds = pagedRecords.stream().map(PointLotteryDO::getUserId).collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);

        // 查询关联活动
        LambdaQueryWrapperX<PointActivityDO> actWrapper = new LambdaQueryWrapperX<>();
        actWrapper.le(PointActivityDO::getStartTime, endTime);
        actWrapper.ge(PointActivityDO::getEndTime, startTime);
        actWrapper.orderByDesc(PointActivityDO::getId);
        List<PointActivityDO> activities = pointActivityMapper.selectList(actWrapper);
        PointActivityDO relatedActivity = activities.isEmpty() ? null : activities.get(0);

        List<Map<String, Object>> list = new ArrayList<>();
        for (PointLotteryDO r : pagedRecords) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("userId", r.getUserId());
            AdminUserRespDTO user = userMap.get(r.getUserId());
            item.put("userName", user != null ? user.getNickname() : null);
            item.put("joinTime", r.getLotteryTime());
            if (relatedActivity != null) {
                item.put("joinActivityId", relatedActivity.getId());
                item.put("joinActivityName", relatedActivity.getName());
            }
            item.put("tenantId", null);
            list.add(item);
        }
        return new PageResult<>(list, (long) total);
    }

    @Override
    public PageResult<Map<String, Object>> barDrill(CycleReportChartBarDrillReqVO reqVO) {
        String categoryType = reqVO.getCategoryType();
        String categoryName = reqVO.getCategoryName();

        switch (categoryType) {
            case "activityType":
                return barDrillActivityType(reqVO, categoryName);
            case "prizeType":
                return barDrillPrizeType(reqVO, categoryName);
            case "couponType":
                return barDrillCouponType(reqVO, categoryName);
            case "cardType":
                return barDrillCardType(reqVO, categoryName);
            case "exchangeCategoryType":
                return barDrillExchangeCategory(reqVO, categoryName);
            default:
                return PageResult.empty();
        }
    }

    private PageResult<Map<String, Object>> barDrillActivityType(CycleReportChartBarDrillReqVO reqVO, String categoryName) {
        LambdaQueryWrapperX<ActivityConfigDO> wrapper = new LambdaQueryWrapperX<ActivityConfigDO>()
//                .eqIfPresent(ActivityConfigDO::getType, categoryName)
                .orderByDesc(ActivityConfigDO::getId);
        PageResult<ActivityConfigDO> pageResult = activityConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        // 收集创建者ID
        Set<Long> creatorIds = new HashSet<>();
        for (ActivityConfigDO a : pageResult.getList()) {
            Long id = safeParseLong(a.getCreator());
            if (id != null) creatorIds.add(id);
        }
        Map<Long, AdminUserRespDTO> userMap = creatorIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(creatorIds);
        for (ActivityConfigDO a : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", a.getId());
            item.put("name", a.getName());
            item.put("type", a.getType());
            item.put("startTime", a.getEffectTime());
            item.put("endTime", null);
            item.put("joinCount", a.getJoinCount());
            item.put("status", a.getStatus());
            Long cid = safeParseLong(a.getCreator());
            item.put("creator", cid != null && userMap.get(cid) != null ? userMap.get(cid).getNickname() : a.getCreator());
            item.put("createTime", a.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> barDrillPrizeType(CycleReportChartBarDrillReqVO reqVO, String categoryName) {
        LambdaQueryWrapperX<PrizeMgmtDO> wrapper = new LambdaQueryWrapperX<PrizeMgmtDO>()
//                .eqIfPresent(PrizeMgmtDO::getType, categoryName)
                .orderByDesc(PrizeMgmtDO::getId);
        PageResult<PrizeMgmtDO> pageResult = prizeMgmtMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (PrizeMgmtDO p : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", p.getId());
            item.put("name", p.getName());
            item.put("type", p.getType());
            item.put("stock", p.getStock());
            item.put("status", p.getStatus());
            item.put("sendCount", p.getSendCount());
            item.put("createTime", p.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> barDrillCouponType(CycleReportChartBarDrillReqVO reqVO, String categoryName) {
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
//                .eqIfPresent(CouponMgmtDO::getType, categoryName)
                .orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", c.getId());
            item.put("name", c.getName());
            item.put("type", c.getType());
            item.put("amount", c.getAmount());
            item.put("status", c.getStatus());
            item.put("sendTime", c.getSendTime());
            item.put("createTime", c.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> barDrillCardType(CycleReportChartBarDrillReqVO reqVO, String categoryName) {
        LambdaQueryWrapperX<CardConfigDO> wrapper = new LambdaQueryWrapperX<CardConfigDO>()
//                .eqIfPresent(CardConfigDO::getType, categoryName)
                .orderByDesc(CardConfigDO::getId);
        PageResult<CardConfigDO> pageResult = cardConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (CardConfigDO c : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", c.getId());
            item.put("name", c.getName());
            item.put("type", c.getType());
            item.put("price", c.getPrice());
            item.put("status", c.getStatus());
            item.put("saleCount", c.getSaleCount());
            item.put("createTime", c.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> barDrillExchangeCategory(CycleReportChartBarDrillReqVO reqVO, String categoryName) {
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<ExchangeOrderDO>()
                .eqIfPresent(ExchangeOrderDO::getCategoryId, reqVO.getCategoryId())
                .orderByDesc(ExchangeOrderDO::getId);
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (ExchangeOrderDO r : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r.getId());
            item.put("no", r.getNo());
            item.put("userId", r.getUserId());
            item.put("categoryId", r.getCategoryId());
            item.put("goodsName", r.getGoodsName());
            item.put("costPoint", r.getCostPoint());
            item.put("payStatus", r.getPayStatus());
            item.put("payTime", r.getPayTime());
            item.put("createTime", r.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<Map<String, Object>> pieDrill(CycleReportChartPieDrillReqVO reqVO) {
        String pieType = reqVO.getPieType();
        String pieName = reqVO.getPieName();

        switch (pieType) {
            case "ruleType":
                return pieDrillRuleType(reqVO, pieName);
            case "configType":
                return pieDrillConfigType(reqVO, pieName);
            case "packageType":
                return pieDrillPackageType(reqVO, pieName);
            default:
                return PageResult.empty();
        }
    }

    private PageResult<Map<String, Object>> pieDrillRuleType(CycleReportChartPieDrillReqVO reqVO, String pieName) {
        // 规则类型 → 查询积分活动，按类型筛选（获取规则/消耗规则/赠送规则对应活动类型）
        LambdaQueryWrapperX<PointActivityDO> wrapper = new LambdaQueryWrapperX<PointActivityDO>()
                .eqIfPresent(PointActivityDO::getType, pieName)
                .orderByDesc(PointActivityDO::getId);
        PageResult<PointActivityDO> pageResult = pointActivityMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (PointActivityDO a : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", a.getId());
            item.put("ruleName", a.getName());
            item.put("ruleType", a.getType());
            item.put("status", a.getStatus());
            item.put("createTime", a.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> pieDrillConfigType(CycleReportChartPieDrillReqVO reqVO, String pieName) {
        // 配置类型 → 查询卡种配置，按类型筛选
        LambdaQueryWrapperX<CardConfigDO> wrapper = new LambdaQueryWrapperX<CardConfigDO>()
                .eqIfPresent(CardConfigDO::getType, pieName)
                .orderByDesc(CardConfigDO::getId);
        PageResult<CardConfigDO> pageResult = cardConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (CardConfigDO c : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", c.getId());
            item.put("configName", c.getName());
            item.put("configType", c.getType());
            item.put("status", c.getStatus());
            item.put("createTime", c.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private PageResult<Map<String, Object>> pieDrillPackageType(CycleReportChartPieDrillReqVO reqVO, String pieName) {
        // 券包类型 → 查询优惠券，按类型筛选
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
                .eqIfPresent(CouponMgmtDO::getType, pieName)
                .orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", c.getId());
            item.put("packageName", c.getName());
            item.put("packageType", c.getType());
            item.put("status", c.getStatus());
            item.put("createTime", c.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private Long safeParseLong(String s) {
        if (s == null) return null;
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }

}
