package cn.iocoder.yudao.module.vehiclecharging.service.charginglot;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.charginglot.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.charginglot.ChargingLotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.charginglot.ChargingLotMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 充电车位 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ChargingLotServiceImpl implements ChargingLotService {

    @Resource
    private ChargingLotMapper chargingLotMapper;

    @Override
    public Long createChargingLot(ChargingLotSaveReqVO createReqVO) {
        // 插入
        ChargingLotDO chargingLot = BeanUtils.toBean(createReqVO, ChargingLotDO.class);
        chargingLotMapper.insert(chargingLot);

        // 返回
        return chargingLot.getId();
    }

    @Override
    public void updateChargingLot(ChargingLotSaveReqVO updateReqVO) {
        // 校验存在
        validateChargingLotExists(updateReqVO.getId());
        // 更新
        ChargingLotDO updateObj = BeanUtils.toBean(updateReqVO, ChargingLotDO.class);
        chargingLotMapper.updateById(updateObj);
    }

    @Override
    public void deleteChargingLot(Long id) {
        // 校验存在
        validateChargingLotExists(id);
        // 删除
        chargingLotMapper.deleteById(id);
    }

    @Override
        public void deleteChargingLotListByIds(List<Long> ids) {
        // 删除
        chargingLotMapper.deleteByIds(ids);
        }


    private void validateChargingLotExists(Long id) {
        if (chargingLotMapper.selectById(id) == null) {
            throw exception(CHARGING_LOT_NOT_EXISTS);
        }
    }

    @Override
    public ChargingLotDO getChargingLot(Long id) {
        return chargingLotMapper.selectById(id);
    }

    @Override
    public PageResult<ChargingLotDO> getChargingLotPage(ChargingLotPageReqVO pageReqVO) {
        return chargingLotMapper.selectPage(pageReqVO);
    }


    @Override
    public ChargingLotChartRespVO getChargingLotChart() {
        // 1. 查询当前租户下的所有充电车位数据
        List<ChargingLotDO> list = chargingLotMapper.selectList();

        // 2. 初始化统计结果对象
        ChargingLotChartRespVO chartRespVO = new ChargingLotChartRespVO();
        // 2.1 初始化各场站统计Map，Key为场站ID，Value为StationLot统计对象
        Map<Long, ChargingLotChartRespVO.StationLot> stationLotMap = new HashMap<>();
        // 2.2 初始化状态统计Map，Key为状态(lotStatus)，Value为数量
        Map<String, Integer> statusCountMap = new HashMap<>();

        // 3. 遍历所有车位，进行统计
        for (ChargingLotDO lot : list) {
            // 3.1 全局计数
            String status = lot.getLotStatus();
            if ("0".equals(status)) {
                chartRespVO.setIdleCount((chartRespVO.getIdleCount() == null ? 0 : chartRespVO.getIdleCount()) + 1);
            } else if ("1".equals(status)) {
                chartRespVO.setOccupiedCount((chartRespVO.getOccupiedCount() == null ? 0 : chartRespVO.getOccupiedCount()) + 1);
            } else if ("2".equals(status)) {
                chartRespVO.setMaintainCount((chartRespVO.getMaintainCount() == null ? 0 : chartRespVO.getMaintainCount()) + 1);
            }

            // 3.2 按状态统计
            statusCountMap.put(status, statusCountMap.getOrDefault(status, 0) + 1);

            // 3.3 按场站统计
            Long stationId = lot.getStationId();
            String stationKey = (stationId != null) ? String.valueOf(stationId) : "未知场站";

            ChargingLotChartRespVO.StationLot stationStat = stationLotMap.get(stationId);
            if (stationStat == null) {
                stationStat = new ChargingLotChartRespVO.StationLot();
                stationStat.setStationId(stationId);
                // 设置场站名称 - 这里用stationId，实际应该从场站表获取名称
                stationStat.setStationName("场站-" + stationKey);
                stationStat.setTotalCount(0);
                stationStat.setIdleCount(0);
                stationStat.setOccupiedCount(0);
                stationStat.setMaintainCount(0);
                stationLotMap.put(stationId, stationStat);
            }

            stationStat.setTotalCount(stationStat.getTotalCount() + 1);
            if ("0".equals(status)) {
                stationStat.setIdleCount(stationStat.getIdleCount() + 1);
            } else if ("1".equals(status)) {
                stationStat.setOccupiedCount(stationStat.getOccupiedCount() + 1);
            } else if ("2".equals(status)) {
                stationStat.setMaintainCount(stationStat.getMaintainCount() + 1);
            }
        }

        // 4. 计算全局总数
        chartRespVO.setTotalCount(list.size());

        // 5. 构建状态占比列表（饼图数据）
        List<ChargingLotChartRespVO.StatusRatio> statusRatioList = new ArrayList<>();
        BigDecimal total = new BigDecimal(chartRespVO.getTotalCount());

        // 定义字典值到状态名称的映射
        Map<String, String> statusNameMap = new HashMap<>();
        statusNameMap.put("0", "空闲");
        statusNameMap.put("1", "占用");
        statusNameMap.put("2", "维护中");

        for (Map.Entry<String, Integer> entry : statusCountMap.entrySet()) {
            ChargingLotChartRespVO.StatusRatio ratio = new ChargingLotChartRespVO.StatusRatio();

            // 获取字典值对应的状态名称
            String statusKey = entry.getKey();
            String statusName = statusNameMap.getOrDefault(statusKey, "未知状态");

            ratio.setStatus(statusName);
            ratio.setCount(entry.getValue());

            // 计算占比，保留4位小数
            if (total.compareTo(BigDecimal.ZERO) > 0) {
                ratio.setRatio(new BigDecimal(entry.getValue()).divide(total, 4, BigDecimal.ROUND_HALF_UP));
            } else {
                ratio.setRatio(BigDecimal.ZERO);
            }
            statusRatioList.add(ratio);
        }
        chartRespVO.setStatusRatio(statusRatioList);

        // 6. 构建场站列表（柱状图数据）
        List<ChargingLotChartRespVO.StationLot> stationLotList = new ArrayList<>(stationLotMap.values());
        // 按总车位数降序排序
        stationLotList.sort((a, b) -> b.getTotalCount() - a.getTotalCount());
        chartRespVO.setStationLotList(stationLotList);

        return chartRespVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateChargingLotStatus(Long id, Integer occupyTime, String lotStatus) {
        // 1. 校验车位是否存在
        ChargingLotDO chargingLot = chargingLotMapper.selectById(id);
        if (chargingLot == null) {
            throw exception(CHARGING_LOT_NOT_EXISTS);
        }

        // 2. 构建更新对象
        ChargingLotDO updateObj = new ChargingLotDO();
        updateObj.setId(id);
        updateObj.setLotStatus(lotStatus);

        // 3. 根据目标状态，决定如何更新占用时长(occupyTime)
        switch (lotStatus) {
            case "1": // 状态：占用
                if (occupyTime == null || occupyTime < 0) {
                    throw exception("占用时长不合法");
                }
                updateObj.setOccupyTime(occupyTime);
                break;
            case "0": // 状态：空闲
            case "2": // 状态：维护中
                updateObj.setOccupyTime(null); // 清空占用时长
                break;
            default:
                throw exception("状态值不合法");
        }

        // 4. 执行更新
        chargingLotMapper.updateById(updateObj);
    }

}