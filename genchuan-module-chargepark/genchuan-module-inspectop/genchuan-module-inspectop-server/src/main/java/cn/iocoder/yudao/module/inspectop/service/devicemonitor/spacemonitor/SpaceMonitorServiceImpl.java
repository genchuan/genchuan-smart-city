package cn.iocoder.yudao.module.inspectop.service.devicemonitor.spacemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo.SpaceMonitorAlarmReqVO;
import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.spacemonitor.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.spacemonitor.SpaceMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.spacemonitor.SpaceMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

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
    @LogRecord(type = SPACE_MONITOR_TYPE, subType = SPACE_MONITOR_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = SPACE_MONITOR_CREATE_SUCCESS)
    public Long createSpaceMonitor(SpaceMonitorSaveReqVO createReqVO) {
        // 插入
        SpaceMonitorDO spaceMonitor = BeanUtils.toBean(createReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.insert(spaceMonitor);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return spaceMonitor.getId();
    }

    @Override
    @LogRecord(type = SPACE_MONITOR_TYPE, subType = SPACE_MONITOR_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = SPACE_MONITOR_UPDATE_SUCCESS)
    public void updateSpaceMonitor(SpaceMonitorSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        SpaceMonitorDO oldSpaceMonitor = validateSpaceMonitorExists(updateReqVO.getId());

        // 2. 更新
        SpaceMonitorDO updateObj = BeanUtils.toBean(updateReqVO, SpaceMonitorDO.class);
        spaceMonitorMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        SpaceMonitorSaveReqVO oldVO = BeanUtils.toBean(oldSpaceMonitor, SpaceMonitorSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = SPACE_MONITOR_TYPE, subType = SPACE_MONITOR_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = SPACE_MONITOR_DELETE_SUCCESS)
    public void deleteSpaceMonitor(Long id) {
        // 校验存在
        validateSpaceMonitorExists(id);
        // 删除
        spaceMonitorMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = SPACE_MONITOR_TYPE, subType = SPACE_MONITOR_DELETE_LIST_SUB_TYPE,
            success = SPACE_MONITOR_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteSpaceMonitorListByIds(List<Long> ids) {
        // 删除
        spaceMonitorMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }

    // 修改验证方法，使其返回SpaceMonitorDO对象，用于update方法的日志对比
    private SpaceMonitorDO validateSpaceMonitorExists(Long id) {
        SpaceMonitorDO spaceMonitor = spaceMonitorMapper.selectById(id);
        if (spaceMonitor == null) {
            throw exception(SPACE_MONITOR_NOT_EXISTS);
        }
        return spaceMonitor; // 返回查询到的对象
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
    @LogRecord(type = SPACE_MONITOR_TYPE, subType = SPACE_MONITOR_UPDATE_ALARM_SUB_TYPE,
            bizNo = "{{#alarmReqVO.id}}", success = SPACE_MONITOR_UPDATE_ALARM_SUCCESS)
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

        // 4. 设置日志上下文变量
        LogRecordContext.putVariable("alarmReqVO", alarmReqVO);
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