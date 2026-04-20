package cn.iocoder.yudao.module.inspectop.service.bikechargemonitor;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.bikechargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.bikechargemonitor.BikeChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.bikechargemonitor.BikeChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 两轮充电监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class BikeChargeMonitorServiceImpl implements BikeChargeMonitorService {

    @Resource
    private BikeChargeMonitorMapper bikeChargeMonitorMapper;

    @Override
    public Long createBikeChargeMonitor(BikeChargeMonitorSaveReqVO createReqVO) {
        // 插入
        BikeChargeMonitorDO bikeChargeMonitor = BeanUtils.toBean(createReqVO, BikeChargeMonitorDO.class);
        bikeChargeMonitorMapper.insert(bikeChargeMonitor);

        // 返回
        return bikeChargeMonitor.getId();
    }

    @Override
    public void updateBikeChargeMonitor(BikeChargeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateBikeChargeMonitorExists(updateReqVO.getId());
        // 更新
        BikeChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, BikeChargeMonitorDO.class);
        bikeChargeMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteBikeChargeMonitor(Long id) {
        // 校验存在
        validateBikeChargeMonitorExists(id);
        // 删除
        bikeChargeMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteBikeChargeMonitorListByIds(List<Long> ids) {
        // 删除
        bikeChargeMonitorMapper.deleteByIds(ids);
        }


    private void validateBikeChargeMonitorExists(Long id) {
        if (bikeChargeMonitorMapper.selectById(id) == null) {
            throw exception(BIKE_CHARGE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public BikeChargeMonitorDO getBikeChargeMonitor(Long id) {
        return bikeChargeMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<BikeChargeMonitorRespVO> getBikeChargeMonitorPage(BikeChargeMonitorPageReqVO pageReqVO) {
        // 1. 创建 MyBatis-Plus 分页对象
        Page<BikeChargeMonitorRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 2. 调用Mapper的关联查询方法
        Page<BikeChargeMonitorRespVO> resultPage = bikeChargeMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 3. 直接构造 PageResult 并返回
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    // 在BikeChargeMonitorServiceImpl类中添加以下方法
    @Override
    public BikeChargeMonitorLocationRespVO getBikeChargeMonitorLocation(Long id) {
        // 校验记录是否存在
        validateBikeChargeMonitorExists(id);

        // 从数据库查询定位信息（包含经度、纬度、场站名称）
        BikeChargeMonitorLocationRespVO locationRespVO = bikeChargeMonitorMapper.selectLocationById(id);

        if (locationRespVO == null) {
            throw exception(BIKE_CHARGE_MONITOR_NOT_EXISTS);
        }

        // 注意：这里不设置deviceCode，留给Controller层处理模拟数据
        return locationRespVO;
    }

    @Override
    public void alarmBikeChargeMonitor(BikeChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateBikeChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        BikeChargeMonitorDO updateObj = new BikeChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());

        // 3. 执行更新操作
        bikeChargeMonitorMapper.updateById(updateObj);
    }

    @Override
    public BikeChargeMonitorChartRespVO getBikeChargeMonitorChart(BikeChargeMonitorChartReqVO reqVO) {
        // 创建响应对象
        BikeChargeMonitorChartRespVO result = new BikeChargeMonitorChartRespVO();

        // 1. 获取地图数据
        List<BikeChargeMonitorChartRespVO.MapData> mapDataList = bikeChargeMonitorMapper.selectMapData(reqVO);
        result.setMapData(mapDataList);

        // 2. 获取趋势数据
        List<BikeChargeMonitorChartRespVO.TrendData> trendDataList = bikeChargeMonitorMapper.selectTrendData(reqVO);
        result.setTrendData(trendDataList);

        // 3. 获取卡片数据
        BikeChargeMonitorChartRespVO.CardData cardData = bikeChargeMonitorMapper.selectCardData(reqVO);
        result.setCardData(cardData);

        return result;
    }


}