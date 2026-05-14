package cn.iocoder.yudao.module.inspectop.service.devicemonitor.bikechargemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.bikechargemonitor.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import java.util.*;

import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.bikechargemonitor.BikeChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.bikechargemonitor.BikeChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

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
    @LogRecord(type = BIKE_CHARGE_MONITOR_TYPE, subType = BIKE_CHARGE_MONITOR_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = BIKE_CHARGE_MONITOR_CREATE_SUCCESS)
    public Long createBikeChargeMonitor(BikeChargeMonitorSaveReqVO createReqVO) {
        // 插入
        BikeChargeMonitorDO bikeChargeMonitor = BeanUtils.toBean(createReqVO, BikeChargeMonitorDO.class);
        bikeChargeMonitorMapper.insert(bikeChargeMonitor);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return bikeChargeMonitor.getId();
    }

    @Override
    @LogRecord(type = BIKE_CHARGE_MONITOR_TYPE, subType = BIKE_CHARGE_MONITOR_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = BIKE_CHARGE_MONITOR_UPDATE_SUCCESS)
    public void updateBikeChargeMonitor(BikeChargeMonitorSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        BikeChargeMonitorDO oldBikeChargeMonitor = validateBikeChargeMonitorExists(updateReqVO.getId());

        // 2. 更新
        BikeChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, BikeChargeMonitorDO.class);
        bikeChargeMonitorMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        BikeChargeMonitorSaveReqVO oldVO = BeanUtils.toBean(oldBikeChargeMonitor, BikeChargeMonitorSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }


    @Override
    @LogRecord(type = BIKE_CHARGE_MONITOR_TYPE, subType = BIKE_CHARGE_MONITOR_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = BIKE_CHARGE_MONITOR_DELETE_SUCCESS)
    public void deleteBikeChargeMonitor(Long id) {
        // 校验存在
        validateBikeChargeMonitorExists(id);
        // 删除
        bikeChargeMonitorMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = BIKE_CHARGE_MONITOR_TYPE, subType = BIKE_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE,
            success = BIKE_CHARGE_MONITOR_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteBikeChargeMonitorListByIds(List<Long> ids) {
        // 删除
        bikeChargeMonitorMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }


    private BikeChargeMonitorDO validateBikeChargeMonitorExists(Long id) {
        BikeChargeMonitorDO bikeChargeMonitor = bikeChargeMonitorMapper.selectById(id);
        if (bikeChargeMonitor == null) {
            throw exception(BIKE_CHARGE_MONITOR_NOT_EXISTS);
        }
        return bikeChargeMonitor; // 返回查询到的对象
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

    @Override
    public BikeChargeMonitorLocationRespVO getBikeChargeMonitorLocation(Long id) {
        // 校验记录是否存在
        validateBikeChargeMonitorExists(id);

        // 从数据库查询定位信息（包含经度、纬度、场站名称、设备ID）
        BikeChargeMonitorLocationRespVO locationRespVO = bikeChargeMonitorMapper.selectLocationById(id);

        if (locationRespVO == null) {
            throw exception(BIKE_CHARGE_MONITOR_NOT_EXISTS);
        }

        // 注意：deviceCode 字段已由 Mapper 查询结果中的 device_id 填充
        return locationRespVO;
    }

    @Override
    @LogRecord(type = BIKE_CHARGE_MONITOR_TYPE, subType = BIKE_CHARGE_MONITOR_ALARM_SUB_TYPE,
            bizNo = "{{#alarmReqVO.id}}", success = BIKE_CHARGE_MONITOR_ALARM_SUCCESS)
    public void alarmBikeChargeMonitor(BikeChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateBikeChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        BikeChargeMonitorDO updateObj = new BikeChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());

        // 3. 执行更新操作
        bikeChargeMonitorMapper.updateById(updateObj);

        // 4. 设置日志上下文变量
        LogRecordContext.putVariable("alarmReqVO", alarmReqVO);
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