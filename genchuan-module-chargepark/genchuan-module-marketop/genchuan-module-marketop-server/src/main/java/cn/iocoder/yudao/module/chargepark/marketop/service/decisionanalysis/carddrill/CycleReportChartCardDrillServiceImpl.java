package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.carddrill;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.carddrill.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlWarnStatusEnum;
import cn.iocoder.yudao.module.system.api.dept.DeptApi;
import cn.iocoder.yudao.module.system.api.dept.dto.DeptRespDTO;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
@Validated
public class CycleReportChartCardDrillServiceImpl implements CycleReportChartCardDrillService {

    @Resource
    private PointActivityMapper pointActivityMapper;
    @Resource
    private PointLotteryMapper pointLotteryMapper;
    @Resource
    private CouponMgmtMapper couponMgmtMapper;
    @Resource
    private CardOrderMapper cardOrderMapper;
    @Resource
    private CardConfigMapper cardConfigMapper;
    @Resource
    private PrizeMgmtMapper prizeMgmtMapper;
    @Resource
    private StockControlMapper stockControlMapper;
    @Resource
    private ExchangeOrderMapper exchangeOrderMapper;
    @Resource
    private AdminUserApi adminUserApi;
//    @Resource
//    private DeptApi deptApi;

    @Override
    public PageResult<CycleReportChartCardDrillActivityCountRespVO> drillActivityCount(CycleReportChartCardDrillActivityCountReqVO reqVO) {
        LambdaQueryWrapperX<PointActivityDO> wrapper = new LambdaQueryWrapperX<PointActivityDO>()
                .orderByDesc(PointActivityDO::getId);
        PageResult<PointActivityDO> pageResult = pointActivityMapper.selectPage(reqVO, wrapper);
        List<CycleReportChartCardDrillActivityCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportChartCardDrillActivityCountRespVO.class);
        // 翻译创建者名称
        injectCreatorNames(list, CycleReportChartCardDrillActivityCountRespVO::getCreator, CycleReportChartCardDrillActivityCountRespVO::setCreator);
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillJoinUserCountRespVO> drillJoinUserCount(CycleReportChartCardDrillJoinUserCountReqVO reqVO) {
        // 查询全量抽奖记录，按userId分组取最早记录
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<>();
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

        // 批量获取部门信息
        Set<Long> deptIds = new HashSet<>();
        for (AdminUserRespDTO user : userMap.values()) {
            if (user.getDeptId() != null) deptIds.add(user.getDeptId());
        }
//        Map<Long, DeptRespDTO> deptMap = deptIds.isEmpty() ? Map.of() : deptApi.getDeptMap(deptIds);

        // 查询关联活动
        List<PointActivityDO> activities = pointActivityMapper.selectList(new LambdaQueryWrapperX<PointActivityDO>()
                .orderByDesc(PointActivityDO::getId));
        Map<Long, PointActivityDO> activityMap = new HashMap<>();
        for (PointActivityDO a : activities) {
            activityMap.putIfAbsent(a.getId(), a);
        }

        List<CycleReportChartCardDrillJoinUserCountRespVO> list = new ArrayList<>();
        for (PointLotteryDO r : pagedRecords) {
            CycleReportChartCardDrillJoinUserCountRespVO resp = new CycleReportChartCardDrillJoinUserCountRespVO();
            resp.setUserId(r.getUserId());
            AdminUserRespDTO user = userMap.get(r.getUserId());
            if (user != null) {
                resp.setUserName(user.getNickname());
                if (user.getDeptId() != null) {
//                    DeptRespDTO dept = deptMap.get(user.getDeptId());
//                    if (dept != null) resp.setDeptName(dept.getName());
                }
            }
            resp.setJoinTime(r.getLotteryTime());
            // 关联最近的活动
            if (!activities.isEmpty()) {
                PointActivityDO relatedActivity = activities.get(0);
                resp.setJoinActivityId(relatedActivity.getId());
                resp.setJoinActivityName(relatedActivity.getName());
            }
            list.add(resp);
        }
        return new PageResult<>(list, (long) total);
    }

    @Override
    public PageResult<CycleReportChartCardDrillLotteryCountRespVO> drillLotteryCount(CycleReportChartCardDrillLotteryCountReqVO reqVO) {
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<PointLotteryDO>()
                .orderByDesc(PointLotteryDO::getId);
        PageResult<PointLotteryDO> pageResult = pointLotteryMapper.selectPage(reqVO, wrapper);
        List<CycleReportChartCardDrillLotteryCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportChartCardDrillLotteryCountRespVO.class);
        // 注入用户名
        Set<Long> userIds = new HashSet<>();
        for (PointLotteryDO r : pageResult.getList()) {
            if (r.getUserId() != null) userIds.add(r.getUserId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (int i = 0; i < pageResult.getList().size(); i++) {
            PointLotteryDO r = pageResult.getList().get(i);
            CycleReportChartCardDrillLotteryCountRespVO resp = list.get(i);
            AdminUserRespDTO user = userMap.get(r.getUserId());
            if (user != null) resp.setUserName(user.getNickname());
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillWinningRateRespVO> drillWinningRate(CycleReportChartCardDrillWinningRateReqVO reqVO) {
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.isNotNull(PointLotteryDO::getPrizeId);
        wrapper.orderByDesc(PointLotteryDO::getId);
        PageResult<PointLotteryDO> pageResult = pointLotteryMapper.selectPage(reqVO, wrapper);
        // 批量获取用户和奖品信息
        Set<Long> userIds = new HashSet<>();
        Set<Long> prizeIds = new HashSet<>();
        for (PointLotteryDO r : pageResult.getList()) {
            userIds.add(r.getUserId());
            if (r.getPrizeId() != null) prizeIds.add(r.getPrizeId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        Map<Long, PrizeMgmtDO> prizeMap = new HashMap<>();
        for (Long prizeId : prizeIds) {
            PrizeMgmtDO prize = prizeMgmtMapper.selectById(prizeId);
            if (prize != null) prizeMap.put(prizeId, prize);
        }

        List<CycleReportChartCardDrillWinningRateRespVO> list = new ArrayList<>();
        for (PointLotteryDO r : pageResult.getList()) {
            CycleReportChartCardDrillWinningRateRespVO resp = new CycleReportChartCardDrillWinningRateRespVO();
            resp.setLotteryId(r.getId());
            resp.setLotteryNo(r.getNo());
            resp.setUserId(r.getUserId());
            AdminUserRespDTO user = userMap.get(r.getUserId());
            if (user != null) resp.setUserName(user.getNickname());
            resp.setLotteryTime(r.getLotteryTime());
            resp.setPrizeId(r.getPrizeId());
            PrizeMgmtDO prize = prizeMap.get(r.getPrizeId());
            if (prize != null) {
                resp.setPrizeName(prize.getName());
                resp.setPrizeType(prize.getType());
            }
            resp.setSendTime(r.getSendTime());
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillCouponSendCountRespVO> drillCouponSendCount(CycleReportChartCardDrillCouponSendCountReqVO reqVO) {
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<CouponMgmtDO>()
                .orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        List<CycleReportChartCardDrillCouponSendCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportChartCardDrillCouponSendCountRespVO.class);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            if (c.getSenderId() != null) userIds.add(c.getSenderId());
            if (c.getReceiverId() != null) userIds.add(c.getReceiverId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (int i = 0; i < pageResult.getList().size(); i++) {
            CouponMgmtDO c = pageResult.getList().get(i);
            CycleReportChartCardDrillCouponSendCountRespVO resp = list.get(i);
            if (c.getSenderId() != null) {
                AdminUserRespDTO u = userMap.get(c.getSenderId());
                if (u != null) resp.setSenderName(u.getNickname());
            }
            if (c.getReceiverId() != null) {
                AdminUserRespDTO u = userMap.get(c.getReceiverId());
                if (u != null) resp.setReceiverName(u.getNickname());
            }
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillVerifyRateRespVO> drillVerifyRate(CycleReportChartCardDrillVerifyRateReqVO reqVO) {
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.isNotNull(CouponMgmtDO::getVerifyTime);
        wrapper.orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            if (c.getReceiverId() != null) userIds.add(c.getReceiverId());
            if (c.getSenderId() != null) userIds.add(c.getSenderId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);

        List<CycleReportChartCardDrillVerifyRateRespVO> list = new ArrayList<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            CycleReportChartCardDrillVerifyRateRespVO resp = new CycleReportChartCardDrillVerifyRateRespVO();
            resp.setCouponId(c.getId());
            resp.setCouponName(c.getName());
            resp.setCouponType(c.getType());
            resp.setAmount(c.getAmount());
            resp.setReceiverId(c.getReceiverId());
            if (c.getReceiverId() != null) {
                AdminUserRespDTO u = userMap.get(c.getReceiverId());
                if (u != null) resp.setReceiverName(u.getNickname());
            }
            resp.setVerifyTime(c.getVerifyTime());
            resp.setVerifyPersonId(c.getSenderId());
            if (c.getSenderId() != null) {
                AdminUserRespDTO u = userMap.get(c.getSenderId());
                if (u != null) resp.setVerifyPersonName(u.getNickname());
            }
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillCardOrderCountRespVO> drillCardOrderCount(CycleReportChartCardDrillCardOrderCountReqVO reqVO) {
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<CardOrderDO>()
                .orderByDesc(CardOrderDO::getId);
        PageResult<CardOrderDO> pageResult = cardOrderMapper.selectPage(reqVO, wrapper);
        List<CycleReportChartCardDrillCardOrderCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportChartCardDrillCardOrderCountRespVO.class);
        // 批量获取用户和卡种信息
        Set<Long> userIds = new HashSet<>();
        Set<Long> cardIds = new HashSet<>();
        for (CardOrderDO o : pageResult.getList()) {
            userIds.add(o.getUserId());
            if (o.getCardId() != null) cardIds.add(o.getCardId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        Map<Long, CardConfigDO> cardMap = new HashMap<>();
        for (Long cardId : cardIds) {
            CardConfigDO card = cardConfigMapper.selectById(cardId);
            if (card != null) cardMap.put(cardId, card);
        }
        for (int i = 0; i < pageResult.getList().size(); i++) {
            CardOrderDO o = pageResult.getList().get(i);
            CycleReportChartCardDrillCardOrderCountRespVO resp = list.get(i);
            AdminUserRespDTO user = userMap.get(o.getUserId());
            if (user != null) resp.setUserName(user.getNickname());
            CardConfigDO card = cardMap.get(o.getCardId());
            if (card != null) {
                resp.setCardName(card.getName());
                resp.setCardType(card.getType());
            }
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillRevenueRespVO> drillRevenue(CycleReportChartCardDrillRevenueReqVO reqVO) {
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.isNotNull(CardOrderDO::getPayTime);
        wrapper.orderByDesc(CardOrderDO::getId);
        PageResult<CardOrderDO> pageResult = cardOrderMapper.selectPage(reqVO, wrapper);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (CardOrderDO o : pageResult.getList()) {
            userIds.add(o.getUserId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        List<CycleReportChartCardDrillRevenueRespVO> list = new ArrayList<>();
        for (CardOrderDO o : pageResult.getList()) {
            CycleReportChartCardDrillRevenueRespVO resp = new CycleReportChartCardDrillRevenueRespVO();
            resp.setOrderId(o.getId());
            resp.setOrderNo(o.getNo());
            resp.setOrderAmount(o.getAmount());
            resp.setPayTime(o.getPayTime());
            resp.setUserId(o.getUserId());
            AdminUserRespDTO user = userMap.get(o.getUserId());
            if (user != null) resp.setUserName(user.getNickname());
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillExchangeCountRespVO> drillExchangeCount(CycleReportChartCardDrillExchangeCountReqVO reqVO) {
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<ExchangeOrderDO>()
                .orderByDesc(ExchangeOrderDO::getId);
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderMapper.selectPage(reqVO, wrapper);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (ExchangeOrderDO o : pageResult.getList()) {
            if (o.getUserId() != null) userIds.add(o.getUserId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);

        List<CycleReportChartCardDrillExchangeCountRespVO> list = new ArrayList<>();
        for (ExchangeOrderDO o : pageResult.getList()) {
            CycleReportChartCardDrillExchangeCountRespVO resp = new CycleReportChartCardDrillExchangeCountRespVO();
            resp.setId(o.getId());
            resp.setNo(o.getNo());
            resp.setUserId(o.getUserId());
            AdminUserRespDTO user = userMap.get(o.getUserId());
            if (user != null) resp.setUserName(user.getNickname());
            resp.setGoodsName(o.getGoodsName());
            resp.setCostPoint(o.getCostPoint());
            resp.setPayStatus(o.getPayStatus());
            resp.setExchangeTime(o.getCreateTime());
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillTotalStockRespVO> drillTotalStock(CycleReportChartCardDrillTotalStockReqVO reqVO) {
        LambdaQueryWrapperX<StockControlDO> wrapper = new LambdaQueryWrapperX<StockControlDO>()
                .orderByDesc(StockControlDO::getId);
        PageResult<StockControlDO> pageResult = stockControlMapper.selectPage(reqVO, wrapper);
        // 批量获取卡种信息
        Set<Long> cardIds = new HashSet<>();
        for (StockControlDO s : pageResult.getList()) {
            if (s.getCardId() != null) cardIds.add(s.getCardId());
        }
        Map<Long, CardConfigDO> cardMap = new HashMap<>();
        for (Long cardId : cardIds) {
            CardConfigDO card = cardConfigMapper.selectById(cardId);
            if (card != null) cardMap.put(cardId, card);
        }

        List<CycleReportChartCardDrillTotalStockRespVO> list = new ArrayList<>();
        for (StockControlDO s : pageResult.getList()) {
            CycleReportChartCardDrillTotalStockRespVO resp = new CycleReportChartCardDrillTotalStockRespVO();
            resp.setCardId(s.getCardId());
            resp.setCurrentStock(s.getCurrentStock());
            resp.setWarnThreshold(s.getWarnThreshold());
            resp.setStatus(s.getStatus());
            CardConfigDO card = cardMap.get(s.getCardId());
            if (card != null) {
                resp.setCardName(card.getName());
                resp.setCardType(card.getType());
            }
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportChartCardDrillWarnStockCountRespVO> drillWarnStockCount(CycleReportChartCardDrillWarnStockCountReqVO reqVO) {
        LambdaQueryWrapperX<StockControlDO> wrapper = new LambdaQueryWrapperX<StockControlDO>()
                .eq(StockControlDO::getWarnStatus, StockControlWarnStatusEnum.WARNED.getValue())
                .orderByDesc(StockControlDO::getId);
        PageResult<StockControlDO> pageResult = stockControlMapper.selectPage(reqVO, wrapper);
        // 批量获取卡种信息
        Set<Long> cardIds = new HashSet<>();
        for (StockControlDO s : pageResult.getList()) {
            if (s.getCardId() != null) cardIds.add(s.getCardId());
        }
        Map<Long, CardConfigDO> cardMap = new HashMap<>();
        for (Long cardId : cardIds) {
            CardConfigDO card = cardConfigMapper.selectById(cardId);
            if (card != null) cardMap.put(cardId, card);
        }

        List<CycleReportChartCardDrillWarnStockCountRespVO> list = new ArrayList<>();
        for (StockControlDO s : pageResult.getList()) {
            CycleReportChartCardDrillWarnStockCountRespVO resp = new CycleReportChartCardDrillWarnStockCountRespVO();
            resp.setCardId(s.getCardId());
            resp.setCurrentStock(s.getCurrentStock());
            resp.setWarnThreshold(s.getWarnThreshold());
            resp.setWarnStatus(s.getWarnStatus());
            CardConfigDO card = cardMap.get(s.getCardId());
            if (card != null) {
                resp.setCardName(card.getName());
                resp.setCardType(card.getType());
            }
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    private <T> void injectCreatorNames(List<T> list, java.util.function.Function<T, String> getter, java.util.function.BiConsumer<T, String> setter) {
        if (list == null || list.isEmpty()) return;
        Set<Long> creatorIds = new HashSet<>();
        for (T item : list) {
            Long id = safeParseLong(getter.apply(item));
            if (id != null) creatorIds.add(id);
        }
        if (creatorIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(creatorIds);
        for (T item : list) {
            Long id = safeParseLong(getter.apply(item));
            if (id != null) {
                AdminUserRespDTO user = userMap.get(id);
                if (user != null) setter.accept(item, user.getNickname());
            }
        }
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
