package cn.iocoder.yudao.module.inspectop.service.carchargemonitor;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.inspectop.controller.admin.carchargemonitor.vo.*;
import cn.iocoder.yudao.module.inspectop.dal.dataobject.carchargemonitor.CarChargeMonitorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.inspectop.dal.mysql.carchargemonitor.CarChargeMonitorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.inspectop.enums.ErrorCodeConstants.*;

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
    public Long createCarChargeMonitor(CarChargeMonitorSaveReqVO createReqVO) {
        // 插入
        CarChargeMonitorDO carChargeMonitor = BeanUtils.toBean(createReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.insert(carChargeMonitor);

        // 返回
        return carChargeMonitor.getId();
    }

    @Override
    public void updateCarChargeMonitor(CarChargeMonitorSaveReqVO updateReqVO) {
        // 校验存在
        validateCarChargeMonitorExists(updateReqVO.getId());
        // 更新
        CarChargeMonitorDO updateObj = BeanUtils.toBean(updateReqVO, CarChargeMonitorDO.class);
        carChargeMonitorMapper.updateById(updateObj);
    }

    @Override
    public void deleteCarChargeMonitor(Long id) {
        // 校验存在
        validateCarChargeMonitorExists(id);
        // 删除
        carChargeMonitorMapper.deleteById(id);
    }

    @Override
        public void deleteCarChargeMonitorListByIds(List<Long> ids) {
        // 删除
        carChargeMonitorMapper.deleteByIds(ids);
        }


    private void validateCarChargeMonitorExists(Long id) {
        if (carChargeMonitorMapper.selectById(id) == null) {
            throw exception(CAR_CHARGE_MONITOR_NOT_EXISTS);
        }
    }

    @Override
    public CarChargeMonitorDO getCarChargeMonitor(Long id) {
        return carChargeMonitorMapper.selectById(id);
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
        // 这里不设置deviceCode，留给Controller层处理
        return locationRespVO;
    }

    @Override
    public void alarmCarChargeMonitor(CarChargeMonitorAlarmReqVO alarmReqVO) {
        // 1. 校验记录是否存在
        validateCarChargeMonitorExists(alarmReqVO.getId());

        // 2. 更新告警备注
        CarChargeMonitorDO updateObj = new CarChargeMonitorDO();
        updateObj.setId(alarmReqVO.getId());
        updateObj.setAlarmRemark(alarmReqVO.getAlarmRemark());
        carChargeMonitorMapper.updateById(updateObj);
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