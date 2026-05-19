package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt;

import cn.hutool.core.util.StrUtil;
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
import cn.iocoder.yudao.module.chargepark.marketop.enums.CouponMgmtStatusEnum;
import cn.iocoder.yudao.module.chargepark.marketop.enums.CouponMgmtTypeEnum;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.LogRecordConstants.*;

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
    @LogRecord(type = COUPON_MGMT_TYPE, subType = COUPON_MGMT_CREATE_SUB_TYPE, bizNo = "{{#couponMgmt.id}}",
            success = COUPON_MGMT_CREATE_SUCCESS)
    public Long create(CouponMgmtCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        CouponMgmtDO couponMgmt = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmt.setStatus(CouponMgmtStatusEnum.NOT_RECEIVED.getValue());
        couponMgmtMapper.insert(couponMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("couponMgmt", couponMgmt);
        return couponMgmt.getId();
    }

    @Override
    @LogRecord(type = COUPON_MGMT_TYPE, subType = COUPON_MGMT_UPDATE_SUB_TYPE, bizNo = "{{#reqVO.id}}",
            success = COUPON_MGMT_UPDATE_SUCCESS)
    public void update(CouponMgmtUpdateReqVO reqVO) {
        CouponMgmtDO couponMgmtDO = validateExists(reqVO.getId());
        CouponMgmtDO updateObj = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmtMapper.updateById(updateObj);
        // 记录操作日志上下文
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, BeanUtils.toBean(couponMgmtDO, CouponMgmtUpdateReqVO.class));
        LogRecordContext.putVariable("couponMgmt", updateObj);
    }

    @Override
    @LogRecord(type = COUPON_MGMT_TYPE, subType = COUPON_MGMT_SEND_SUB_TYPE, bizNo = "{{#id}}",
            success = COUPON_MGMT_SEND_SUCCESS)
    public void send(Long id, Long userId) {
        CouponMgmtDO couponMgmt = validateExists(id);
//        if (!"0".equals(couponMgmt.getStatus())) {
//            throw exception(COUPON_MGMT_STATUS_ERROR);
//        }
        couponMgmt.setStatus(CouponMgmtStatusEnum.RECEIVED.getValue());
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        couponMgmt.setSenderId(loginUserId); // 发放人为当前操作用户，由Controller层设置
        couponMgmt.setSendTime(LocalDateTime.now());
        couponMgmt.setReceiverId(userId);
        couponMgmtMapper.updateById(couponMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("couponMgmtName", couponMgmt.getName());
    }

    @Override
    @LogRecord(type = COUPON_MGMT_TYPE, subType = COUPON_MGMT_VERIFY_SUB_TYPE, bizNo = "{{#id}}",
            success = COUPON_MGMT_VERIFY_SUCCESS)
    public void verify(Long id) {
        CouponMgmtDO couponMgmt = validateExists(id);
//        if (!"1".equals(couponMgmt.getStatus())) {
//            throw exception(COUPON_MGMT_STATUS_ERROR);
//        }
        couponMgmt.setStatus(CouponMgmtStatusEnum.USED.getValue());
        couponMgmt.setVerifyTime(LocalDateTime.now());
        couponMgmtMapper.updateById(couponMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("couponMgmtName", couponMgmt.getName());
    }

    @Override
    @LogRecord(type = COUPON_MGMT_TYPE, subType = COUPON_MGMT_RESEND_SUB_TYPE, bizNo = "{{#id}}",
            success = COUPON_MGMT_RESEND_SUCCESS)
    public void resend(Long id, Long receiverId, Long newValidTime) {
        CouponMgmtDO couponMgmt = validateExists(id);
//        if (!"2".equals(couponMgmt.getStatus())) {
//            throw exception(COUPON_MGMT_STATUS_ERROR);
//        }
        Long loginUserId = SecurityFrameworkUtils.getLoginUserId();
        couponMgmt.setStatus(CouponMgmtStatusEnum.NOT_RECEIVED.getValue());
        couponMgmt.setSenderId(loginUserId);
        couponMgmt.setSendTime(LocalDateTime.now());
        couponMgmt.setReceiverId(receiverId);
        couponMgmt.setValidTime(LocalDateTime.ofInstant(java.time.Instant.ofEpochMilli(newValidTime), java.time.ZoneId.systemDefault()));
        couponMgmtMapper.updateById(couponMgmt);
        // 记录操作日志上下文
        LogRecordContext.putVariable("couponMgmtName", couponMgmt.getName());
    }

    @Override
    public CouponMgmtChartRespVO getChart() {
        CouponMgmtChartRespVO respVO = new CouponMgmtChartRespVO();

        List<CouponMgmtDO> allList = couponMgmtMapper.selectList(new LambdaQueryWrapperX<>());

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
    public List<CouponMgmtDO> getSimpleList() {
        return couponMgmtMapper.selectList();
    }

    @Override
    public void importCouponMgmtList(List<CouponMgmtImportExcelVO> list) {
        if (list == null || list.isEmpty()) {
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (CouponMgmtImportExcelVO excelVO : list) {
            validateNameUnique(null, excelVO.getName());
            // type: 中文名称 -> 枚举值
            String typeValue = CouponMgmtTypeEnum.valueOfLabel(excelVO.getType());
            if (typeValue == null) {
                throw exception(COUPON_MGMT_IMPORT_TYPE_INVALID, excelVO.getType());
            }
            // stationIds: 中文逗号分隔的场站名称 -> 逗号分隔的ID
            String stationIdStr = convertStationNamesToIds(excelVO.getStationIds());
            // 解析有效期
            LocalDateTime validTime = LocalDateTime.parse(excelVO.getValidTime(), formatter);
            CouponMgmtDO couponMgmt = BeanUtils.toBean(excelVO, CouponMgmtDO.class);
            couponMgmt.setType(typeValue);
            couponMgmt.setStationIds(stationIdStr);
            couponMgmt.setValidTime(validTime);
            couponMgmt.setStatus(CouponMgmtStatusEnum.NOT_RECEIVED.getValue());
            couponMgmtMapper.insert(couponMgmt);
        }
    }

    private String convertStationNamesToIds(String stationNames) {
        if (StrUtil.isBlank(stationNames)) {
            return null;
        }
        List<String> names = Arrays.stream(stationNames.split(","))
                .map(String::trim)
                .filter(StrUtil::isNotBlank)
                .collect(Collectors.toList());
        if (names.isEmpty()) {
            return null;
        }
        List<Map<String, Object>> stations = couponMgmtMapper.selectStationIdsByNames(names);
        Map<String, String> nameToId = new HashMap<>();
        for (Map<String, Object> station : stations) {
            nameToId.put(String.valueOf(station.get("name")), String.valueOf(station.get("id")));
        }
        List<String> ids = new ArrayList<>();
        for (String name : names) {
            String id = nameToId.get(name);
            if (id == null) {
                throw exception(COUPON_MGMT_IMPORT_STATION_NOT_FOUND, name);
            }
            ids.add(id);
        }
        return String.join(",", ids);
    }

}
