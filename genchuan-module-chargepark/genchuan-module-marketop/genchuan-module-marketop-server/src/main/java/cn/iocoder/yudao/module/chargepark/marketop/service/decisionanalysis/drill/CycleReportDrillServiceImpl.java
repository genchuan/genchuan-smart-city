package cn.iocoder.yudao.module.chargepark.marketop.service.decisionanalysis.drill;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.drill.vo.*;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardConfigDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.CardOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.cardmgmt.StockControlDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.decisionanalysis.CycleReportDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeOrderDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointLotteryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PrizeMgmtDO;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardConfigMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.CardOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.cardmgmt.StockControlMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.decisionanalysis.CycleReportMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeOrderMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointActivityMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PointLotteryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity.PrizeMgmtMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.StockControlWarnStatusEnum;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.CYCLE_REPORT_NOT_EXISTS;

@Service
@Validated
public class CycleReportDrillServiceImpl implements CycleReportDrillService {

    @Resource
    private CycleReportMapper cycleReportMapper;
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
    private AdminUserApi adminUserApi;
    @Resource
    private ExchangeOrderMapper exchangeOrderMapper;
    @Resource
    private StockControlMapper stockControlMapper;

    @Override
    public PageResult<CycleReportDrillReportCycleRespVO> drillReportCycle(CycleReportDrillReportCycleReqVO reqVO) {
        PageResult<CycleReportDO> pageResult = cycleReportMapper.selectPage(new cn.iocoder.yudao.module.chargepark.marketop.controller.admin.decisionanalysis.cyclereport.vo.CycleReportPageReqVO() {{
            setReportCycle(reqVO.getReportCycle());
            setTenantId(reqVO.getTenantId());
            setPageNo(reqVO.getPageNo());
            setPageSize(reqVO.getPageSize());
        }});
        List<CycleReportDrillReportCycleRespVO> list = new ArrayList<>();
        for (CycleReportDO report : pageResult.getList()) {
            CycleReportDrillReportCycleRespVO resp = BeanUtils.toBean(report, CycleReportDrillReportCycleRespVO.class);
            resp.setReportStatus(report.getGenerateStatus());
            resp.setVerifyRate(report.getCouponVerifyRate());
            if (report.getStatStartTime() != null && report.getStatEndTime() != null) {
                resp.setStatCycle(report.getStatStartTime().toLocalDate().toString());
            }
            Map<String, String> scaleMap = Map.of("日报", "日", "周报", "周", "月报", "月", "季报", "季", "半年报", "半年", "年报", "年", "自定义报表", "自定义");
            resp.setTimeScale(scaleMap.getOrDefault(report.getReportCycle(), ""));
            list.add(resp);
        }
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportDrillActivityCountRespVO> drillActivityCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<PointActivityDO> wrapper = new LambdaQueryWrapperX<>();
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(PointActivityDO::getCreateTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(PointActivityDO::getCreateTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(PointActivityDO::getId);
        PageResult<PointActivityDO> pageResult = pointActivityMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillActivityCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportDrillActivityCountRespVO.class);
        // 翻译创建者名称
        injectCreatorNames(list, CycleReportDrillActivityCountRespVO::getCreator, CycleReportDrillActivityCountRespVO::setCreator);
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportDrillJoinUserCountRespVO> drillJoinUserCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        Page<CycleReportDrillJoinUserCountRespVO> page = new Page<>(reqVO.getPageNo(), reqVO.getPageSize());
        IPage<CycleReportDrillJoinUserCountRespVO> pageResult = pointLotteryMapper.selectPageDrillJoinUserCount(
                page, report.getStatStartTime(), report.getStatEndTime());
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportDrillLotteryCountRespVO> drillLotteryCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<>();
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(PointLotteryDO::getLotteryTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(PointLotteryDO::getLotteryTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(PointLotteryDO::getId);
        PageResult<PointLotteryDO> pageResult = pointLotteryMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillLotteryCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportDrillLotteryCountRespVO.class);
        injectUserNames(list);
        return new PageResult<>(list, pageResult.getTotal());
    }

    @Override
    public PageResult<CycleReportDrillWinningRateRespVO> drillWinningRate(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<PointLotteryDO> wrapper = new LambdaQueryWrapperX<PointLotteryDO>()
                .geIfPresent(PointLotteryDO::getPrizeId, 0);
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(PointLotteryDO::getLotteryTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(PointLotteryDO::getLotteryTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(PointLotteryDO::getId);
        PageResult<PointLotteryDO> pageResult = pointLotteryMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillWinningRateRespVO> list = new ArrayList<>();
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
        for (PointLotteryDO r : pageResult.getList()) {
            CycleReportDrillWinningRateRespVO resp = new CycleReportDrillWinningRateRespVO();
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
    public PageResult<CycleReportDrillCouponSendCountRespVO> drillCouponSendCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<>();
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(CouponMgmtDO::getSendTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(CouponMgmtDO::getSendTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillCouponSendCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportDrillCouponSendCountRespVO.class);
        Set<Long> userIds = new HashSet<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            if (c.getSenderId() != null) userIds.add(c.getSenderId());
            if (c.getReceiverId() != null) userIds.add(c.getReceiverId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (int i = 0; i < pageResult.getList().size(); i++) {
            CouponMgmtDO c = pageResult.getList().get(i);
            CycleReportDrillCouponSendCountRespVO resp = list.get(i);
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
    public PageResult<CycleReportDrillVerifyRateRespVO> drillVerifyRate(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<CouponMgmtDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.isNotNull(CouponMgmtDO::getVerifyTime);
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(CouponMgmtDO::getVerifyTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(CouponMgmtDO::getVerifyTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(CouponMgmtDO::getId);
        PageResult<CouponMgmtDO> pageResult = couponMgmtMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillVerifyRateRespVO> list = new ArrayList<>();
        Set<Long> userIds = new HashSet<>();
        for (CouponMgmtDO c : pageResult.getList()) {
            if (c.getReceiverId() != null) userIds.add(c.getReceiverId());
            if (c.getSenderId() != null) userIds.add(c.getSenderId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        for (CouponMgmtDO c : pageResult.getList()) {
            CycleReportDrillVerifyRateRespVO resp = new CycleReportDrillVerifyRateRespVO();
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
    public PageResult<CycleReportDrillCardOrderCountRespVO> drillCardOrderCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<>();
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(CardOrderDO::getCreateTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(CardOrderDO::getCreateTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(CardOrderDO::getId);
        PageResult<CardOrderDO> pageResult = cardOrderMapper.selectPage(reqVO, wrapper);
        List<CycleReportDrillCardOrderCountRespVO> list = BeanUtils.toBean(pageResult.getList(), CycleReportDrillCardOrderCountRespVO.class);
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
            CycleReportDrillCardOrderCountRespVO resp = list.get(i);
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
    public PageResult<CycleReportDrillRevenueRespVO> drillRevenue(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<CardOrderDO> wrapper = new LambdaQueryWrapperX<>();
        wrapper.isNotNull(CardOrderDO::getPayTime);
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(CardOrderDO::getPayTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(CardOrderDO::getPayTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(CardOrderDO::getId);
        PageResult<CardOrderDO> pageResult = cardOrderMapper.selectPage(reqVO, wrapper);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (CardOrderDO o : pageResult.getList()) {
            userIds.add(o.getUserId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        List<CycleReportDrillRevenueRespVO> list = new ArrayList<>();
        for (CardOrderDO o : pageResult.getList()) {
            CycleReportDrillRevenueRespVO resp = new CycleReportDrillRevenueRespVO();
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
    public PageResult<CycleReportDrillExchangeCountRespVO> drillExchangeCount(CycleReportDrillBaseReqVO reqVO) {
        CycleReportDO report = validateReportExists(reqVO.getReportId());
        LambdaQueryWrapperX<ExchangeOrderDO> wrapper = new LambdaQueryWrapperX<>();
        if (report.getStatStartTime() != null) {
            wrapper.geIfPresent(ExchangeOrderDO::getCreateTime, report.getStatStartTime());
        }
        if (report.getStatEndTime() != null) {
            wrapper.leIfPresent(ExchangeOrderDO::getCreateTime, report.getStatEndTime());
        }
        wrapper.orderByDesc(ExchangeOrderDO::getId);
        PageResult<ExchangeOrderDO> pageResult = exchangeOrderMapper.selectPage(reqVO, wrapper);
        // 批量获取用户信息
        Set<Long> userIds = new HashSet<>();
        for (ExchangeOrderDO o : pageResult.getList()) {
            if (o.getUserId() != null) userIds.add(o.getUserId());
        }
        Map<Long, AdminUserRespDTO> userMap = userIds.isEmpty() ? Map.of() : adminUserApi.getUserMap(userIds);
        List<CycleReportDrillExchangeCountRespVO> list = new ArrayList<>();
        for (ExchangeOrderDO o : pageResult.getList()) {
            CycleReportDrillExchangeCountRespVO resp = new CycleReportDrillExchangeCountRespVO();
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
    public PageResult<CycleReportDrillTotalStockRespVO> drillTotalStock(CycleReportDrillBaseReqVO reqVO) {
        validateReportExists(reqVO.getReportId());
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
        List<CycleReportDrillTotalStockRespVO> list = new ArrayList<>();
        for (StockControlDO s : pageResult.getList()) {
            CycleReportDrillTotalStockRespVO resp = new CycleReportDrillTotalStockRespVO();
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
    public PageResult<CycleReportDrillWarnStockCountRespVO> drillWarnStockCount(CycleReportDrillBaseReqVO reqVO) {
        validateReportExists(reqVO.getReportId());
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
        List<CycleReportDrillWarnStockCountRespVO> list = new ArrayList<>();
        for (StockControlDO s : pageResult.getList()) {
            CycleReportDrillWarnStockCountRespVO resp = new CycleReportDrillWarnStockCountRespVO();
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

    private CycleReportDO validateReportExists(Long id) {
        CycleReportDO report = cycleReportMapper.selectById(id);
        if (report == null) {
            throw exception(CYCLE_REPORT_NOT_EXISTS);
        }
        return report;
    }

    private void injectUserNames(List<CycleReportDrillLotteryCountRespVO> list) {
        if (list == null || list.isEmpty()) return;
        Set<Long> userIds = new HashSet<>();
        for (var item : list) {
            if (item.getUserId() != null) userIds.add(item.getUserId());
        }
        if (userIds.isEmpty()) return;
        Map<Long, AdminUserRespDTO> userMap = adminUserApi.getUserMap(userIds);
        for (var item : list) {
            if (item.getUserId() != null) {
                AdminUserRespDTO user = userMap.get(item.getUserId());
                if (user != null) item.setUserName(user.getNickname());
            }
        }
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
