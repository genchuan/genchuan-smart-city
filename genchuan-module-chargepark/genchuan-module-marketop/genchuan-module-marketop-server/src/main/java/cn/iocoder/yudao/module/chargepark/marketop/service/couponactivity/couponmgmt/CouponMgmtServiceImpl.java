package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.security.core.util.SecurityFrameworkUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtImportExcelVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class CouponMgmtServiceImpl implements CouponMgmtService {

    @Resource
    private CouponMgmtMapper couponMgmtMapper;

    @Override
    public PageResult<CouponMgmtDO> getPage(CouponMgmtPageReqVO reqVO) {
        return couponMgmtMapper.selectPage(reqVO);
    }

    @Override
    public CouponMgmtDO get(Long id) {
        return couponMgmtMapper.selectById(id);
    }

    @Override
    public Long create(CouponMgmtCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        CouponMgmtDO couponMgmt = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmt.setStatus("0");
        couponMgmtMapper.insert(couponMgmt);
        return couponMgmt.getId();
    }

    @Override
    public void update(CouponMgmtUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        CouponMgmtDO updateObj = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmtMapper.updateById(updateObj);
    }

    @Override
    public void send(Long id, Long userId) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"0".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        couponMgmt.setStatus("1");
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        couponMgmt.setSenderId(loginUserId); // 发放人为当前操作用户，由Controller层设置
        couponMgmt.setSendTime(LocalDateTime.now());
        couponMgmt.setReceiverId(userId);
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public void verify(Long id) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"1".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        couponMgmt.setStatus("2");
        couponMgmt.setVerifyTime(LocalDateTime.now());
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public void resend(Long id, Long receiverId, Long newValidTime) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"2".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        couponMgmt.setStatus("1");
        couponMgmt.setSenderId(loginUserId);
        couponMgmt.setSendTime(LocalDateTime.now());
        couponMgmt.setReceiverId(receiverId);
        couponMgmt.setValidTime(LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(newValidTime), java.time.ZoneId.systemDefault()));
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public CouponMgmtChartRespVO getChart(Long startTime, Long endTime, Long stationId) {
        CouponMgmtChartRespVO respVO = new CouponMgmtChartRespVO();

        // 构建时间范围
        LocalDateTime startDateTime = startTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(startTime), ZoneId.systemDefault()) : null;
        LocalDateTime endDateTime = endTime != null
                ? LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(endTime), ZoneId.systemDefault()) : null;

        // 构建基础查询条件
        LambdaQueryWrapperX<CouponMgmtDO> baseWrapper = new LambdaQueryWrapperX<>();
        if (startDateTime != null) {
            baseWrapper.ge(CouponMgmtDO::getCreateTime, startDateTime);
        }
        if (endDateTime != null) {
            baseWrapper.le(CouponMgmtDO::getCreateTime, endDateTime);
        }
        if (stationId != null) {
            baseWrapper.apply("FIND_IN_SET({0}, station_ids)", stationId);
        }
        List<CouponMgmtDO> allList = couponMgmtMapper.selectList(baseWrapper);

        // sendCount: status为1的记录数
        long sendCount = allList.stream().filter(item -> "1".equals(item.getStatus())).count();
        respVO.setSendCount((int) sendCount);

        // verifyRate: status为2的数量 / 总记录数
        long verifyCount = allList.stream().filter(item -> "2".equals(item.getStatus())).count();
        BigDecimal verifyRate = allList.isEmpty() ? BigDecimal.ZERO
                : BigDecimal.valueOf(verifyCount).divide(BigDecimal.valueOf(allList.size()), 4, RoundingMode.HALF_UP).multiply(BigDecimal.valueOf(100));
        respVO.setVerifyRate(verifyRate);

        // sendTrend: 每天的记录数
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Map<String, Long> dailyCount = allList.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getCreateTime() != null ? item.getCreateTime().toLocalDate().format(dateFormatter) : "unknown",
                        Collectors.counting()));
        List<CouponMgmtChartRespVO.TrendItem> sendTrend = dailyCount.entrySet().stream()
                .map(entry -> {
                    CouponMgmtChartRespVO.TrendItem item = new CouponMgmtChartRespVO.TrendItem();
                    item.setDate(entry.getKey());
                    item.setCount(entry.getValue().intValue());
                    return item;
                })
                .sorted(java.util.Comparator.comparing(CouponMgmtChartRespVO.TrendItem::getDate))
                .collect(Collectors.toList());
        respVO.setSendTrend(sendTrend);

        // typeDistribution: 按type分组统计数量
        Map<String, Long> typeCount = allList.stream()
                .collect(Collectors.groupingBy(
                        item -> item.getType() != null ? item.getType() : "unknown",
                        Collectors.counting()));
        List<CouponMgmtChartRespVO.TypeCountItem> typeDistribution = typeCount.entrySet().stream()
                .map(entry -> {
                    CouponMgmtChartRespVO.TypeCountItem item = new CouponMgmtChartRespVO.TypeCountItem();
                    item.setType(entry.getKey());
                    item.setCount(entry.getValue().intValue());
                    return item;
                })
                .collect(Collectors.toList());
        respVO.setTypeDistribution(typeDistribution);

        return respVO;
    }

    private CouponMgmtDO validateExists(Long id) {
        CouponMgmtDO couponMgmt = couponMgmtMapper.selectById(id);
        if (couponMgmt == null) {
            throw exception(COUPON_MGMT_NOT_EXISTS);
        }
        return couponMgmt;
    }

    private void validateNameUnique(Long id, String name) {
        CouponMgmtDO existing = couponMgmtMapper.selectOne(CouponMgmtDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(COUPON_MGMT_NAME_EXISTS);
        }
    }

    @Override
    public void importCouponMgmtList(List<CouponMgmtImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        for (CouponMgmtImportExcelVO excelVO : list) {
            validateNameUnique(null, excelVO.getName());
            CouponMgmtDO couponMgmt = BeanUtils.toBean(excelVO, CouponMgmtDO.class);
            couponMgmt.setStatus("0");
            couponMgmtMapper.insert(couponMgmt);
        }
    }

}
