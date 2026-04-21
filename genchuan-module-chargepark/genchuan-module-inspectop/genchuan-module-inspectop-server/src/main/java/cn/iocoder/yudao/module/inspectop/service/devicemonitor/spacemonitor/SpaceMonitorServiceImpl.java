package cn.iocoder.yudao.module.inspectop.service.devicemonitor.spacemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo.SpaceMonitorAlarmReqVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.spacemonitor.SpaceMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

/**
 * 车位状态监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class SpaceMonitorServiceImpl implements SpaceMonitorService {

    @Resource
    private SpaceMonitorMapper spaceMonitorMapper;

    @Override
    public Long createSpaceMonitor(SpaceMonitorSaveReqVO createReqVO) {
        // 插入
        SpaceMonitorDO spaceMonitor = BeanUtils.toBean(createReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.insert(spaceMonitor);

        // 返回
        return spaceMonitor.getId();
    }

    @Override
    public void updateSpaceMonitor(SpaceMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateSpaceMonitorExists(updateReqVO.getId());
        // 更新
        SpaceMonitorDO updateObj = BeanUtils.toBean(updateReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpaceMonitor(Long id) {
        // 校验存在
        validateSpaceMonitorExists(id);
        // 删除
        spaceMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteSpaceMonitorListByIds(List<Long> ids) {
        // 删除
        spaceMonitorMapper.deleteByIds(ids);
        }


    private void validateSpaceMonitorExists(Long id) {
        if (spaceMonitorMapper.selectById(id) == null) {
            throw exception(SPACE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public SpaceMonitorDO getSpaceMonitor(Long id) {
        return spaceMonitorMapper.selectById(id);
    }

    @Override
    public PageResult<SpaceMonitorRespVO> getSpaceMonitorPage(SpaceMonitorPageReqVO pageReqVO) {
        // 创建分页对象
        Page<SpaceMonitorRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper方法，注意接收返回值
        Page<SpaceMonitorRespVO> resultPage = spaceMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    // 在 SpaceMonitorServiceImpl.java 中添加以下方法
    @Override
    public SpaceMonitorLocationRespVO getSpaceMonitorLocation(Long id) {
        // 校验记录是否存在
        validateSpaceMonitorExists(id);

        // 调用Mapper的定位查询方法
        SpaceMonitorLocationRespVO location = spaceMonitorMapper.selectLocationById(id);

        if (location == null) {
            // 如果查询结果为空，抛出异常
            throw exception(SPACE_MONITOR_NOT_EXISTS);
        }

        return location;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSpaceMonitorAlarm(SpaceMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateSpaceMonitorExists(alarmReqVO.getId());

        // 2. 构建更新对象
        SpaceMonitorDO updateObj = new SpaceMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmStatus("ALARM"); // 设置为告警状态
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());
        updateObj.setAlarmTime(LocalDateTime.now()); // 设置告警时间为当前时间

        // 3. 更新数据库
        spaceMonitorMapper.updateById(updateObj);
    }

    // SpaceMonitorServiceImpl.java
    @Override
    public SpaceMonitorChartRespVO getSpaceMonitorChart(SpaceMonitorChartReqVO reqVO) {
        SpaceMonitorChartRespVO result = new SpaceMonitorChartRespVO();

        // 1. 获取地图数据
        List<SpaceMonitorChartRespVO.MapData> mapDataList = spaceMonitorMapper.selectMapData(reqVO);
        result.setMapData(mapDataList);

        // 2. 获取趋势数据
        List<SpaceMonitorChartRespVO.TrendData> trendDataList = spaceMonitorMapper.selectTrendData(reqVO);
        result.setTrendData(trendDataList);

        // 3. 获取卡片数据
        SpaceMonitorChartRespVO.CardData cardData = spaceMonitorMapper.selectCardData(reqVO);
        result.setCardData(cardData);

        return result;
    }

}