package cn.iocoder.yudao.module.vehiclepass.service.inparkmgmt.inparkstatus;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusAlarmReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusChartRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusLocationRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusPageReqVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusRespVO;
import cn.iocoder.yudao.module.vehiclepass.controller.admin.inparkmgmt.inparkstatus.vo.InParkStatusSaveReqVO;
import cn.iocoder.yudao.module.vehiclepass.dal.dataobject.inparkmgmt.inparkstatus.InParkStatusDO;
import cn.iocoder.yudao.module.vehiclepass.dal.mysql.inparkmgmt.inparkstatus.InParkStatusMapper;
import cn.iocoder.yudao.module.vehiclepass.framework.util.MapValueUtils;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclepass.enums.ErrorCodeConstants.PARK_STATUS_NOT_EXISTS;

/**
 * 在停状态 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class InParkStatusServiceImpl implements InParkStatusService {

    @Resource
    private InParkStatusMapper parkStatusMapper;

    @Override
    public Long createParkStatus(InParkStatusSaveReqVO createReqVO) {
        // 插入
        InParkStatusDO parkStatus = BeanUtils.toBean(createReqVO, InParkStatusDO.class);
        parkStatusMapper.insert(parkStatus);

        // 返回
        return parkStatus.getId();
    }

    @Override
    public void updateParkStatus(InParkStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateParkStatusExists(updateReqVO.getId());
        // 更新
        InParkStatusDO updateObj = BeanUtils.toBean(updateReqVO, InParkStatusDO.class);
        parkStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkStatus(Long id) {
        // 校验存在
        validateParkStatusExists(id);
        // 删除
        parkStatusMapper.deleteById(id);
    }

    @Override
    public void deleteParkStatusListByIds(List<Long> ids) {
        // 删除
        parkStatusMapper.deleteByIds(ids);
    }


    private void validateParkStatusExists(Long id) {
        if (parkStatusMapper.selectById(id) == null) {
            throw exception(PARK_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public InParkStatusDO getParkStatus(Long id) {
        return parkStatusMapper.selectById(id);
    }

    @Override
    public PageResult<InParkStatusDO> getParkStatusPage(InParkStatusPageReqVO pageReqVO) {
        return parkStatusMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<InParkStatusRespVO> getInParkStatusPage(InParkStatusPageReqVO pageReqVO) {
        Page<InParkStatusRespVO> page = new Page<>(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        IPage<InParkStatusRespVO> pageResult = parkStatusMapper.selectPageJoinSpaceStation(page, pageReqVO);
        return new PageResult<>(pageResult.getRecords(), pageResult.getTotal());
    }

    @Override
    public InParkStatusLocationRespVO getInParkStatusLocation(InParkStatusLocationReqVO reqVO) {
        Map<String, Object> location = parkStatusMapper.selectLocationById(reqVO.getId());
        if (location == null) {
            throw exception(PARK_STATUS_NOT_EXISTS);
        }

        InParkStatusLocationRespVO respVO = new InParkStatusLocationRespVO();
        Object lonObj = location.get("lon");
        Object latObj = location.get("lat");
        if (lonObj != null) {
            respVO.setLon(new java.math.BigDecimal(lonObj.toString()));
        }
        if (latObj != null) {
            respVO.setLat(new java.math.BigDecimal(latObj.toString()));
        }
        respVO.setSpaceName((String) location.get("spaceName"));
        respVO.setStationName((String) location.get("stationName"));
        return respVO;
    }

    @Override
    public void remindParkStatus(Long id) {
        // 校验记录存在
        InParkStatusDO parkStatus = parkStatusMapper.selectById(id);
        if (parkStatus == null) {
            throw exception(PARK_STATUS_NOT_EXISTS);
        }
        // TODO: 调用短信或推送服务提醒车主
    }

    @Override
    public void alarmParkStatus(InParkStatusAlarmReqVO reqVO) {
        // 校验记录存在
        InParkStatusDO parkStatus = parkStatusMapper.selectById(reqVO.getId());
        if (parkStatus == null) {
            throw exception(PARK_STATUS_NOT_EXISTS);
        }
        // TODO: 调用短信或推送服务提醒车主
        // 更新告警内容到备注
        InParkStatusDO updateObj = new InParkStatusDO();
        updateObj.setId(reqVO.getId());
        updateObj.setRemark(reqVO.getAlarmContent());
        parkStatusMapper.updateById(updateObj);
    }

    @Override
    public InParkStatusChartRespVO getInParkStatusChart(InParkStatusChartReqVO chartReqVO) {
        InParkStatusChartRespVO respVO = new InParkStatusChartRespVO();

        // 1. 车辆分布
        List<Map<String, Object>> carLocationList = parkStatusMapper.selectCarLocationList(chartReqVO.getStationId());
        List<InParkStatusChartRespVO.CarLocation> carLocations = new ArrayList<>();
        for (Map<String, Object> map : carLocationList) {
            InParkStatusChartRespVO.CarLocation item = new InParkStatusChartRespVO.CarLocation();
            item.setPlateNo((String) map.get("plateNo"));
            Object lonObj = map.get("lon");
            Object latObj = map.get("lat");
            if (lonObj != null) item.setLon(new java.math.BigDecimal(lonObj.toString()));
            if (latObj != null) item.setLat(new java.math.BigDecimal(latObj.toString()));
            item.setSpaceName((String) map.get("spaceName"));
            carLocations.add(item);
        }
        respVO.setCarLocationList(carLocations);

        // 2. 在停量趋势
        List<Map<String, Object>> trendList = parkStatusMapper.selectInParkCountTrend(chartReqVO.getStationId());
        List<InParkStatusChartRespVO.InParkCountTrend> trends = new ArrayList<>();
        for (Map<String, Object> map : trendList) {
            InParkStatusChartRespVO.InParkCountTrend item = new InParkStatusChartRespVO.InParkCountTrend();
            item.setTime((String) map.get("time"));
            item.setCount(MapValueUtils.getLongValue(map, "count"));
            trends.add(item);
        }
        respVO.setInParkCountTrend(trends);

        // 3. 卡片数据
        Map<String, Object> stats = parkStatusMapper.selectInParkStats(chartReqVO.getStationId());
        InParkStatusChartRespVO.CardData cardData = new InParkStatusChartRespVO.CardData();
        cardData.setInParkCarCount(MapValueUtils.getLongValue(stats, "inParkCarCount"));
        cardData.setOverTimeCarCount(MapValueUtils.getLongValue(stats, "overTimeCarCount"));
        respVO.setCardData(cardData);

        return respVO;
    }

}