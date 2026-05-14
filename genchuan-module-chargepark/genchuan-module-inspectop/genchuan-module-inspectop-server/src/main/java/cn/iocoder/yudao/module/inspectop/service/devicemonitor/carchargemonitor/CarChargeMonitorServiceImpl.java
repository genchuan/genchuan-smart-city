package cn.iocoder.yudao.module.inspectop.service.devicemonitor.carchargemonitor;

import cn.iocoder.yudao.module.inspectop.controller.admin.devicemonitor.carchargemonitor.vo.*;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import com.mzt.logapi.context.LogRecordContext;
import com.mzt.logapi.service.impl.DiffParseFunction;
import com.mzt.logapi.starter.annotation.LogRecord;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.devicemonitor.carchargemonitor.CarChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.devicemonitor.carchargemonitor.CarChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;
import static cn.iocoder.yudao.module.inspectop.enums.LogRecordConstants.*;

/**
 * 汽车充电监测 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class CarChargeMonitorServiceImpl implements CarChargeMonitorService {

    @Resource
    private CarChargeMonitorMapper carChargeMonitorMapper;

    @Override
    @LogRecord(type = CAR_CHARGE_MONITOR_TYPE, subType = CAR_CHARGE_MONITOR_CREATE_SUB_TYPE,
            bizNo = "{{#createReqVO.id}}", success = CAR_CHARGE_MONITOR_CREATE_SUCCESS)
    public Long createCarChargeMonitor(CarChargeMonitorSaveReqVO createReqVO) {
        // 插入
        CarChargeMonitorDO carChargeMonitor = BeanUtils.toBean(createReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.insert(carChargeMonitor);

        // 设置日志上下文变量
        LogRecordContext.putVariable("createReqVO", createReqVO);

        // 返回
        return carChargeMonitor.getId();
    }

    @Override
    @LogRecord(type = CAR_CHARGE_MONITOR_TYPE, subType = CAR_CHARGE_MONITOR_UPDATE_SUB_TYPE,
            bizNo = "{{#updateReqVO.id}}", success = CAR_CHARGE_MONITOR_UPDATE_SUCCESS)
    public void updateCarChargeMonitor(CarChargeMonitorSaveReqVO updateReqVO) {
        // 1. 校验存在，并获取旧数据用于日志对比
        CarChargeMonitorDO oldCarChargeMonitor = validateCarChargeMonitorExists(updateReqVO.getId());

        // 2. 更新
        CarChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.updateById(updateObj);

        // 3. 记录操作日志上下文（用于DIFF比较）
        // 将旧数据转换为VO对象，存入日志上下文
        CarChargeMonitorSaveReqVO oldVO = BeanUtils.toBean(oldCarChargeMonitor, CarChargeMonitorSaveReqVO.class);
        LogRecordContext.putVariable(DiffParseFunction.OLD_OBJECT, oldVO);
    }

    @Override
    @LogRecord(type = CAR_CHARGE_MONITOR_TYPE, subType = CAR_CHARGE_MONITOR_DELETE_SUB_TYPE,
            bizNo = "{{#id}}", success = CAR_CHARGE_MONITOR_DELETE_SUCCESS)
    public void deleteCarChargeMonitor(Long id) {
        // 校验存在
        validateCarChargeMonitorExists(id);
        // 删除
        carChargeMonitorMapper.deleteById(id);
    }

    @Override
    @LogRecord(type = CAR_CHARGE_MONITOR_TYPE, subType = CAR_CHARGE_MONITOR_DELETE_LIST_SUB_TYPE,
            success = CAR_CHARGE_MONITOR_DELETE_LIST_SUCCESS, bizNo = "")
    public void deleteCarChargeMonitorListByIds(List<Long> ids) {
        // 删除
        carChargeMonitorMapper.deleteByIds(ids);

        // 设置日志上下文变量
        LogRecordContext.putVariable("ids", ids);
    }


    private CarChargeMonitorDO validateCarChargeMonitorExists(Long id) {
        CarChargeMonitorDO carChargeMonitor = carChargeMonitorMapper.selectById(id);
        if (carChargeMonitor == null) {
            throw exception(CAR_CHARGE_MONITOR_NOT_EXISTS);
        }
        return carChargeMonitor; // 返回查询到的对象
    }

    @Override
    public CarChargeMonitorRespVO getCarChargeMonitor(Long id) {
        // 1. 先查询基础记录是否存在
        CarChargeMonitorDO carChargeMonitor = carChargeMonitorMapper.selectById(id);
        if (carChargeMonitor == null) {
            throw exception(CAR_CHARGE_MONITOR_NOT_EXISTS);
        }

        // 2. 创建分页对象（虽然是单条查询，但使用Mapper现有的关联查询方法需要Page对象）
        Page<CarChargeMonitorRespVO> page = new Page<>(1, 1);

        // 3. 创建查询条件对象
        CarChargeMonitorPageReqVO reqVO = new CarChargeMonitorPageReqVO();
        reqVO.setPageNo(1);
        reqVO.setPageSize(1);
        // 不设置其他条件，只需要根据id查询

        // 4. 调用Mapper的关联查询方法
        Page<CarChargeMonitorRespVO> resultPage = carChargeMonitorMapper.selectPageWithJoin(page, reqVO);

        // 5. 从结果中获取第一条记录
        if (resultPage.getRecords() != null && !resultPage.getRecords().isEmpty()) {
            return resultPage.getRecords().get(0);
        }

        // 6. 如果查询失败，返回DO转换的VO（此时stationName为null，但其他字段有值）
        return BeanUtils.toBean(carChargeMonitor, CarChargeMonitorRespVO.class);
    }

    @Override
    public PageResult<CarChargeMonitorRespVO> getCarChargeMonitorPage(CarChargeMonitorPageReqVO pageReqVO) {
        // 创建分页对象
        Page<CarChargeMonitorRespVO> mpPage = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        // 调用Mapper的关联查询方法
        Page<CarChargeMonitorRespVO> resultPage = carChargeMonitorMapper.selectPageWithJoin(mpPage, pageReqVO);

        // 直接构造PageResult
        return new PageResult<>(resultPage.getRecords(), resultPage.getTotal());
    }

    @Override
    public CarChargeMonitorLocationRespVO getCarChargeMonitorLocation(Long id) {
        // 校验记录是否存在
        validateCarChargeMonitorExists(id);

        // 从数据库查询定位信息（包含经度、纬度、场站名称）
        CarChargeMonitorLocationRespVO locationRespVO = carChargeMonitorMapper.selectLocationById(id);

        if (locationRespVO == null) {
            throw exception(CAR_CHARGE_MONITOR_NOT_EXISTS);
        }

        // 在Controller层模拟deviceCode字段
        return locationRespVO;
    }

    @Override
    @LogRecord(type = CAR_CHARGE_MONITOR_TYPE, subType = CAR_CHARGE_MONITOR_ALARM_SUB_TYPE,
            bizNo = "{{#alarmReqVO.id}}", success = CAR_CHARGE_MONITOR_ALARM_SUCCESS)
    public void alarmCarChargeMonitor(CarChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateCarChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        CarChargeMonitorDO updateObj = new CarChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());

        // 3. 执行更新操作
        carChargeMonitorMapper.updateById(updateObj);

        // 4. 设置日志上下文变量
        LogRecordContext.putVariable("alarmReqVO", alarmReqVO);
    }

    @Override
    public CarChargeMonitorChartRespVO getCarChargeMonitorChart(CarChargeMonitorChartReqVO reqVO) {
        CarChargeMonitorChartRespVO result = new CarChargeMonitorChartRespVO();

        // 1. 获取地图数据
        List<CarChargeMonitorChartRespVO.MapData> mapDataList = carChargeMonitorMapper.selectMapData(reqVO);
        result.setMapData(mapDataList);

        // 2. 获取趋势数据
        List<CarChargeMonitorChartRespVO.TrendData> trendDataList = carChargeMonitorMapper.selectTrendData(reqVO);
        result.setTrendData(trendDataList);

        // 3. 获取卡片数据
        CarChargeMonitorChartRespVO.CardData cardData = carChargeMonitorMapper.selectCardData(reqVO);
        result.setCardData(cardData);

        return result;
    }

}