package cn.iocoder.yudao.module.facility.service.manhole.manholecover;

import cn.hutool.core.util.ObjectUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverDetailRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverPageReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholecover.vo.ManholeCoverSaveReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeCoverRealTimeRefreshReqVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeCoverRealTimeRefreshRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeMonitorStatsRespVO;
import cn.iocoder.yudao.module.facility.controller.admin.manhole.manholemonitor.vo.ManholeCoverRealTimePageRespVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.disposalorder.DisposalOrderDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholecover.ManholeCoverDO;
import cn.iocoder.yudao.module.facility.dal.dataobject.manhole.manholemonitor.ManholeMonitorDO;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.disposalorder.DisposalOrderMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholecover.ManholeCoverMapper;
import cn.iocoder.yudao.module.facility.dal.mysql.manhole.manholemonitor.ManholeMonitorMapper;
import com.alibaba.nacos.client.naming.utils.CollectionUtils;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.COVER_NOT_EXISTS;
import static cn.iocoder.yudao.module.facility.enums.ErrorCodeConstants.COVER_NOT_EXISTS_OR_NOT_BELONG_TO_TENANT;

/**
 * 窨井盖设施 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManholeCoverServiceImpl implements ManholeCoverService {

    // Redis缓存前缀
    private static final String REDIS_KEY_PREFIX = "manhole:real_time:";
    // 时间格式化器
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    //  注入redisTemplate
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    private ManholeCoverMapper coverMapper;

    @Resource
    private ManholeMonitorMapper monitorMapper;

    @Resource
    private DisposalOrderMapper disposalOrderMapper;

    @Override
    public Long createCover(ManholeCoverSaveReqVO createReqVO) {
        // 插入
        ManholeCoverDO cover = BeanUtils.toBean(createReqVO, ManholeCoverDO.class);
        coverMapper.insert(cover);
        // 返回
        return cover.getId();
    }

    @Override
    public void updateCover(ManholeCoverSaveReqVO updateReqVO) {
        // 校验存在
        validateCoverExists(updateReqVO.getId());
        // 更新
        ManholeCoverDO updateObj = BeanUtils.toBean(updateReqVO, ManholeCoverDO.class);
        coverMapper.updateById(updateObj);

    }

    @Override
    public void deleteCover(Long id) {
        // 校验存在
        validateCoverExists(id);
        // 删除
        coverMapper.deleteById(id);
    }

    private void validateCoverExists(Long id) {
        if (coverMapper.selectById(id) == null) {
            throw exception(COVER_NOT_EXISTS);
        }
    }

    @Override
    public ManholeCoverDO getCover(Long id) {
        return coverMapper.selectById(id);
    }

    @Override
    public PageResult<ManholeCoverDO> getCoverPage(ManholeCoverPageReqVO pageReqVO) {
        return coverMapper.selectPage(pageReqVO);
    }

//    @Override
//    public ManholeCoverDetailRespVO getCoverDetail(Long id) {
//        ManholeCoverRealTimePageRespVO cover = monitorMapper.selectManholeDetailByCoverId(id);
//        if (ObjectUtil.isNull(cover)) {
//            throw exception(COVER_NOT_EXISTS);
//        }

//        ManholeCoverDetailRespVO detail = new ManholeCoverDetailRespVO();
//        BeanUtils.copyProperties(cover, detail);

//        ManholeMonitorVO latestMonitor = monitorMapper.selectManholeDetailByCoverId(id);
//        if (latestMonitor != null) {
//            BeanUtils.toBean(latestMonitor, detail);
//        }

//        ManholeConfigDO config = configMapper.selectByCoverId(id);
//        if (config != null) {
//            detail.setTiltAngleThreshold(config.getTiltAngleThreshold());
//            detail.setCollectFrequency(config.getCollectFrequency());
//        }

//        ManholeMonitorStatsRespVO stats = monitorMapper.select24HourStats(id);
//        if (stats == null) {
//            stats = new ManholeMonitorStatsRespVO();
//            stats.setAvgTiltAngle(BigDecimal.ZERO);
//            stats.setMaxTiltAngle(BigDecimal.ZERO);
//            stats.setMinTiltAngle(BigDecimal.ZERO);
//            stats.setAvgVibration(BigDecimal.ZERO);
//            stats.setMaxVibration(BigDecimal.ZERO);
//            stats.setMinVibration(BigDecimal.ZERO);
//        }
//        detail.setManholeMonitorStatsRespVO(stats);
//
//        List<DisposalOrderDO> faultRecords = disposalOrderMapper.selectFaultRecordsByCoverId(id);
//
//        System.out.println(faultRecords);
//
//        if (faultRecords == null) {
//            faultRecords = new ArrayList<>();
//        }
//        detail.setDisposalOrderDOList(faultRecords);
//
//        return detail;
//    }

    @Override
    public List<ManholeCoverRealTimeRefreshRespVO> refreshRealTimeData(ManholeCoverRealTimeRefreshReqVO reqVO) {
        // 1. 获取井盖ID列表（为空则查询当前租户所有井盖）
        List<Long> coverIds = reqVO.getCoverIds();
        Long tenantId = reqVO.getTenantId();
        if (CollectionUtils.isEmpty(coverIds)) {
            coverIds = coverMapper.selectCoverIdsByTenantId(tenantId);
            if (CollectionUtils.isEmpty(coverIds)) {
                return Collections.emptyList();
            }
        }

        // 2. 批量查询井盖基础信息
        Map<Long, ManholeCoverDO> coverMap = coverMapper.selectListByCoverIdsAndTenantId(coverIds, tenantId)
                .stream().collect(Collectors.toMap(ManholeCoverDO::getId, cover -> cover));

        // 3. 组装返回数据
        List<ManholeCoverRealTimeRefreshRespVO> resultList = new ArrayList<>();
        for (Long coverId : coverIds) {
            ManholeCoverDO coverDO = coverMap.get(coverId);
            if (coverDO == null) {
                throw exception(COVER_NOT_EXISTS_OR_NOT_BELONG_TO_TENANT);
            }

            // 3.1 从Redis获取实时数据（优先）
            Map<String, Object> realTimeData = getRealTimeDataFromRedis(coverId.toString());
            // 3.2 Redis无数据则从数据库获取最新监测数据
            if (realTimeData.isEmpty()) {
                realTimeData = getRealTimeDataFromDB(coverId);
            }

            // 3.3 组装响应VO
            ManholeCoverRealTimeRefreshRespVO respVO = assembleRespVO(coverDO, realTimeData, tenantId);
            resultList.add(respVO);
        }

        return resultList;
    }

    /**
     * 从Redis获取实时数据
     */
    private Map<String, Object> getRealTimeDataFromRedis(String coverId) {
        String redisKey = REDIS_KEY_PREFIX + coverId;
        Map<Object, Object> redisData = redisTemplate.opsForHash().entries(redisKey);

        if (redisData == null || redisData.isEmpty()) {
            return Collections.emptyMap();
        }

        // 转换为String-Object Map
        Map<String, Object> result = new HashMap<>();
        redisData.forEach((k, v) -> result.put(k.toString(), v));
        return result;
    }

    /**
     * 从数据库获取最新监测数据
     */
    private Map<String, Object> getRealTimeDataFromDB(Long coverId) {
        ManholeMonitorDO monitorDO = monitorMapper.selectLatestByCoverId(coverId);
        if (monitorDO == null) {
            return Collections.emptyMap();
        }

        Map<String, Object> result = new HashMap<>();
        result.put("tiltAngle", Optional.ofNullable(monitorDO.getTiltAngle()).orElse(BigDecimal.ZERO));
        result.put("vibrationData", Optional.ofNullable(monitorDO.getVibrationData()).orElse(BigDecimal.ZERO));
        // ====================== 修复这里 ======================
        // 安全转换字符串 -> BigDecimal，防止非数字报错
        BigDecimal waterLevel;
        try {
            waterLevel = new BigDecimal(monitorDO.getExtCommon1().trim());
        } catch (Exception e) {
            waterLevel = BigDecimal.ZERO;
        }
        result.put("waterLevel", waterLevel);
        // ======================================================
        result.put("collectTime", monitorDO.getCreateTime());
        result.put("deviceId", monitorDO.getDeviceId());

        // 查询设备在线状态
        String onlineStatus = monitorMapper.selectDeviceOnlineStatus(monitorDO.getDeviceId());
        result.put("onlineStatus", "在线".equals(onlineStatus) ? 1 : 0);

        return result;
    }

    /**
     * 组装响应VO
     */
    private ManholeCoverRealTimeRefreshRespVO assembleRespVO(ManholeCoverDO coverDO, Map<String, Object> realTimeData, Long tenantId) {
        ManholeCoverRealTimeRefreshRespVO respVO = new ManholeCoverRealTimeRefreshRespVO();
        // 基础信息
        respVO.setCoverId(coverDO.getId());
        respVO.setCoverCode(coverDO.getCoverNo());
        respVO.setTenantId(tenantId);

        // 实时数据
        // ====================== 核心修复：防止 null ======================
        BigDecimal tiltAngle = (BigDecimal) Optional.ofNullable(realTimeData.get("tiltAngle")).orElse(BigDecimal.ZERO);
        BigDecimal displacement = (BigDecimal) Optional.ofNullable(realTimeData.get("vibrationData")).orElse(BigDecimal.ZERO);
        BigDecimal waterLevel = (BigDecimal) Optional.ofNullable(realTimeData.get("waterLevel")).orElse(BigDecimal.ZERO);

        // 格式化数值（带单位）
        respVO.setTiltAngle(tiltAngle.setScale(1, BigDecimal.ROUND_HALF_UP) + "°");
        respVO.setDisplacement(displacement.setScale(1, BigDecimal.ROUND_HALF_UP) + "cm");
        respVO.setWaterLevel(waterLevel.setScale(1, BigDecimal.ROUND_HALF_UP) + "cm");

        // 采集时间
        Object collectTimeObj = realTimeData.get("collectTime");
        String collectTime = collectTimeObj instanceof LocalDateTime ?
                ((LocalDateTime) collectTimeObj).format(DATE_TIME_FORMATTER) :
                collectTimeObj != null ? collectTimeObj.toString() : "";
        respVO.setLatestCollectTime(collectTime);

        // 设备在线状态
        respVO.setOnlineStatus((Integer) Optional.ofNullable(realTimeData.get("onlineStatus")).orElse(0));

        // 井盖状态（根据指标判断）
        int coverStatus = 1; // 默认正常
        String statusName = "正常";
        if (tiltAngle.compareTo(new BigDecimal("10.0")) > 0) { // 倾斜角度超过10度
            coverStatus = 3;
            statusName = "倾斜";
        } else if (displacement.compareTo(new BigDecimal("5.0")) > 0) { // 位移超过5cm
            coverStatus = 2;
            statusName = "位移";
        } else if (waterLevel.compareTo(new BigDecimal("20.0")) > 0) { // 水位超过20cm
            coverStatus = 4;
            statusName = "水位异常";
        }
        respVO.setCoverStatus(coverStatus);
        respVO.setCoverStatusName(statusName);

        return respVO;
    }

}