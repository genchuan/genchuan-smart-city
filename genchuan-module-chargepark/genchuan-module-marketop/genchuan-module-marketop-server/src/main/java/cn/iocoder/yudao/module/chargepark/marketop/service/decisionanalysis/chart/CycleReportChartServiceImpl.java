package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.chart;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartBarDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartLineDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.chart.vo.CycleReportChartPieDrillReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.PackageConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ReceiveRecordDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ActivityConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.PackageConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.ReceiveRecordMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.RuleConfigMapper;
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
    private ReceiveRecordMapper receiveRecordMapper;
    @Resource
    private CardOrderMapper cardOrderMapper;
    @Resource
    private StockControlMapper stockControlMapper;
    @Resource
    private RuleConfigMapper ruleConfigMapper;
    @Resource
    private PackageConfigMapper packageConfigMapper;
    @Resource
    private AdminUserApi adminUserApi;

    @Override
    public PageResult<Map<String, Object>> lineDrill(CycleReportChartLineDrillReqVO reqVO) {
        String lineType = reqVO.getLineType();

        switch (lineType) {
            case "activityTrend":
                return lineDrillActivityTrend(reqVO);
            case "lotteryTrend":
                return lineDrillLotteryTrend(reqVO);
            case "couponTrend":
                return lineDrillCouponTrend(reqVO);
            case "orderTrend":
                return lineDrillOrderTrend(reqVO);
            case "stockTrend":
                return lineDrillStockTrend(reqVO);
            default:
                return PageResult.empty();
        }
    }

    private LocalDateTime[] parseDateRange(String date) {
        LocalDateTime startTime;
        LocalDateTime endTime;
        try {
            YearMonth ym = YearMonth.parse(date);
            startTime = ym.atDay(1).atStartOfDay();
            endTime = ym.atEndOfMonth().atTime(LocalTime.MAX);
        } catch (Exception e) {
            LocalDate ld = LocalDate.parse(date);
            startTime = ld.atStartOfDay();
            endTime = ld.atTime(LocalTime.MAX);
        }
        return new LocalDateTime[]{startTime, endTime};
    }

    /** 活动参与趋势 → point_activity */
    private PageResult<Map<String, Object>> lineDrillActivityTrend(CycleReportChartLineDrillReqVO reqVO) {
        LocalDateTime[] range = parseDateRange(reqVO.getDate());
        LambdaQueryWrapperX<PointActivityDO> wrapper = new LambdaQueryWrapperX<PointActivityDO>()
                .betweenIfPresent(PointActivityDO::getCreateTime, range[0], range[1])
                .orderByDesc(PointActivityDO::getId);
        PageResult<PointActivityDO> pageResult = pointActivityMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        Set<Long> creatorIds = new HashSet<>();
        for (PointActivityDO a : pageResult.getList()) {
            Long id = safeParseLong(a.getCreator());
            if (id != null) creatorIds.add(id);
        }
        Map<Long, AdminUserRespDTO> userMap = creatorIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(creatorIds);
        for (PointActivityDO a : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", a.getId());
            item.put("name", a.getName());
            item.put("type", a.getType());
            item.put("startTime", a.getStartTime());
            item.put("endTime", a.getEndTime());
            item.put("joinCount", a.getJoinCount());
            item.put("status", a.getStatus());
            Long cid = safeParseLong(a.getCreator());
            item.put("creator", cid != null && userMap.get(cid) != null ? userMap.get(cid).getNickname() : a.getCreator());
            item.put("createTime", a.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 抽奖趋势 → point_lottery */
    private PageResult<Map<String, Object>> lineDrillLotteryTrend(CycleReportChartLineDrillReqVO reqVO) {
        LocalDateTime[] range = parseDateRange(reqVO.getDate());
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<PointLotteryDO>()
                .betweenIfPresent(PointLotteryDO::getCreateTime, range[0], range[1])
                .orderByDesc(PointLotteryDO::getId);
        PageResult<PointLotteryDO> pageResult = pointLotteryMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        Set<Long> userIds = pageResult.getList().stream().map(PointLotteryDO::getUserId).collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (PointLotteryDO r : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r.getId());
            item.put("userId", r.getUserId());
            AdminUserRespDTO user = userMap.get(r.getUserId());
            item.put("userName", user != null ? user.getNickname() : null);
            item.put("lotteryTime", r.getLotteryTime());
            item.put("costPoint", r.getCostPoint());
            item.put("status", r.getStatus());
            item.put("createTime", r.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 优惠券发放趋势 → receive_record */
    private PageResult<Map<String, Object>> lineDrillCouponTrend(CycleReportChartLineDrillReqVO reqVO) {
        LocalDateTime[] range = parseDateRange(reqVO.getDate());
        LambdaQueryWrapperX<ReceiveRecordDO> wrapper = new LambdaQueryWrapperX<ReceiveRecordDO>()
                .betweenIfPresent(ReceiveRecordDO::getCreateTime, range[0], range[1])
                .orderByDesc(ReceiveRecordDO::getId);
        PageResult<ReceiveRecordDO> pageResult = receiveRecordMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        Set<Long> userIds = pageResult.getList().stream().map(ReceiveRecordDO::getUserId).collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (ReceiveRecordDO r : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r.getId());
            item.put("no", r.getNo());
            item.put("userId", r.getUserId());
            AdminUserRespDTO user = userMap.get(r.getUserId());
            item.put("userName", user != null ? user.getNickname() : null);
            item.put("couponId", r.getCouponId());
            item.put("receiveTime", r.getReceiveTime());
            item.put("status", r.getStatus());
            item.put("verifyTime", r.getVerifyTime());
            item.put("createTime", r.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 订单量趋势 → card_order */
    private PageResult<Map<String, Object>> lineDrillOrderTrend(CycleReportChartLineDrillReqVO reqVO) {
        LocalDateTime[] range = parseDateRange(reqVO.getDate());
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<CardOrderDO>()
                .betweenIfPresent(CardOrderDO::getCreateTime, range[0], range[1])
                .orderByDesc(CardOrderDO::getId);
        PageResult<CardOrderDO> pageResult = cardOrderMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        Set<Long> userIds = pageResult.getList().stream().map(CardOrderDO::getUserId).collect(Collectors.toSet());
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (CardOrderDO o : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", o.getId());
            item.put("no", o.getNo());
            item.put("userId", o.getUserId());
            AdminUserRespDTO user = userMap.get(o.getUserId());
            item.put("userName", user != null ? user.getNickname() : null);
            item.put("cardId", o.getCardId());
            item.put("amount", o.getAmount());
            item.put("payStatus", o.getPayStatus());
            item.put("payTime", o.getPayTime());
            item.put("createTime", o.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 库存趋势 → stock_control */
    private PageResult<Map<String, Object>> lineDrillStockTrend(CycleReportChartLineDrillReqVO reqVO) {
        LocalDateTime[] range = parseDateRange(reqVO.getDate());
        LambdaQueryWrapperX<StockControlDO> wrapper = new LambdaQueryWrapperX<StockControlDO>()
                .betweenIfPresent(StockControlDO::getCreateTime, range[0], range[1])
                .orderByDesc(StockControlDO::getId);
        PageResult<StockControlDO> pageResult = stockControlMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (StockControlDO s : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", s.getId());
            item.put("cardId", s.getCardId());
            item.put("currentStock", s.getCurrentStock());
            item.put("warnThreshold", s.getWarnThreshold());
            item.put("status", s.getStatus());
            item.put("warnStatus", s.getWarnStatus());
            item.put("syncTime", s.getSyncTime());
            item.put("createTime", s.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<Map<String, Object>> barDrill(CycleReportChartBarDrillReqVO reqVO) {
        String categoryType = reqVO.getCategoryType();
        String categoryValue = reqVO.getCategoryValue();

        switch (categoryType) {
            case "activityType":
                return barDrillActivityType(reqVO, categoryValue);
            case "prizeType":
                return barDrillPrizeType(reqVO, categoryValue);
            case "couponType":
                return barDrillCouponType(reqVO, categoryValue);
            case "cardType":
                return barDrillCardType(reqVO, categoryValue);
            case "exchangeCategoryType":
                return barDrillExchangeCategory(reqVO, categoryValue);
            default:
                return PageResult.empty();
        }
    }

    private PageResult<Map<String, Object>> barDrillActivityType(CycleReportChartBarDrillReqVO reqVO, String categoryValue) {
        LambdaQueryWrapperX<ActivityConfigDO> wrapper = new LambdaQueryWrapperX<ActivityConfigDO>()
                .eqIfPresent(ActivityConfigDO::getType, categoryValue)
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

    private PageResult<Map<String, Object>> barDrillPrizeType(CycleReportChartBarDrillReqVO reqVO, String categoryValue) {
        LambdaQueryWrapperX<PrizeMgmtDO> wrapper = new LambdaQueryWrapperX<PrizeMgmtDO>()
                .eqIfPresent(PrizeMgmtDO::getType, categoryValue)
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

    private PageResult<Map<String, Object>> barDrillCouponType(CycleReportChartBarDrillReqVO reqVO, String categoryValue) {
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
                .eqIfPresent(CouponMgmtDO::getType, categoryValue)
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

    private PageResult<Map<String, Object>> barDrillCardType(CycleReportChartBarDrillReqVO reqVO, String categoryValue) {
        LambdaQueryWrapperX<CardConfigDO> wrapper = new LambdaQueryWrapperX<CardConfigDO>()
                .eqIfPresent(CardConfigDO::getType, categoryValue)
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

    private PageResult<Map<String, Object>> barDrillExchangeCategory(CycleReportChartBarDrillReqVO reqVO, String categoryValue) {
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
        String pieValue = reqVO.getPieValue();

        switch (pieType) {
            case "ruleType":
                return pieDrillRuleType(reqVO, pieValue);
            case "configType":
                return pieDrillConfigType(reqVO, pieValue);
            case "packageType":
                return pieDrillPackageType(reqVO, pieValue);
            default:
                return PageResult.empty();
        }
    }

    /** 规则类型占比 → rule_config（对应getChart中selectRuleConfigTypeCount） */
    private PageResult<Map<String, Object>> pieDrillRuleType(CycleReportChartPieDrillReqVO reqVO, String pieValue) {
        LambdaQueryWrapperX<RuleConfigDO> wrapper = new LambdaQueryWrapperX<RuleConfigDO>()
                .eqIfPresent(RuleConfigDO::getType, pieValue)
                .orderByDesc(RuleConfigDO::getId);
        PageResult<RuleConfigDO> pageResult = ruleConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (RuleConfigDO r : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", r.getId());
            item.put("ruleName", r.getName());
            item.put("ruleType", r.getType());
            item.put("status", r.getStatus());
            item.put("createTime", r.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 配置类型占比 → activity_config（对应getChart中selectActivityConfigTypeCountForPie） */
    private PageResult<Map<String, Object>> pieDrillConfigType(CycleReportChartPieDrillReqVO reqVO, String pieValue) {
        LambdaQueryWrapperX<ActivityConfigDO> wrapper = new LambdaQueryWrapperX<ActivityConfigDO>()
                .eqIfPresent(ActivityConfigDO::getType, pieValue)
                .orderByDesc(ActivityConfigDO::getId);
        PageResult<ActivityConfigDO> pageResult = activityConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (ActivityConfigDO a : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", a.getId());
            item.put("configName", a.getName());
            item.put("configType", a.getType());
            item.put("status", a.getStatus());
            item.put("createTime", a.getCreateTime());
            list.add(item);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    /** 券包类型占比 → package_config（对应getChart中selectPackageConfigTypeCount） */
    private PageResult<Map<String, Object>> pieDrillPackageType(CycleReportChartPieDrillReqVO reqVO, String pieValue) {
        LambdaQueryWrapperX<PackageConfigDO> wrapper = new LambdaQueryWrapperX<PackageConfigDO>()
                .eqIfPresent(PackageConfigDO::getType, pieValue)
                .orderByDesc(PackageConfigDO::getId);
        PageResult<PackageConfigDO> pageResult = packageConfigMapper.selectPage(reqVO, wrapper);
        List<Map<String, Object>> list = new ArrayList<>();
        for (PackageConfigDO p : pageResult.getList()) {
            Map<String, Object> item = new LinkedHashMap<>();
            item.put("id", p.getId());
            item.put("packageName", p.getName());
            item.put("packageType", p.getType());
            item.put("status", p.getStatus());
            item.put("createTime", p.getCreateTime());
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
