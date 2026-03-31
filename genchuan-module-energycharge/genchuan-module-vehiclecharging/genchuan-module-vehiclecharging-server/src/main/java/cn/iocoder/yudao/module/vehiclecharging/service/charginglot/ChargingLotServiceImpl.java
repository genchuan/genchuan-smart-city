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
        // 注意：根据业务要求，这里应实现多租户数据隔离。从现有代码看，ChargingLotDO并未直接包含tenant_id字段，
        // 因此假设数据隔离已在Mapper层的BaseMapperX或LambdaQueryWrapperX中通过自动添加tenant_id条件实现。
        // 如果尚未实现，需要在此处或Mapper中手动添加租户过滤条件。
        List<ChargingLotDO> list = chargingLotMapper.selectList();

        // 2. 初始化统计结果对象
        ChargingLotChartRespVO chartRespVO = new ChargingLotChartRespVO();
        // 2.1 初始化各场站统计Map，Key为场站编码(stationCode)，Value为StationLot统计对象
        Map<String, ChargingLotChartRespVO.StationLot> stationLotMap = new HashMap<>();
        // 2.2 初始化状态统计Map，Key为状态(lotStatus)，Value为数量
        Map<String, Integer> statusCountMap = new HashMap<>();

        // 3. 遍历所有车位，进行统计
        for (ChargingLotDO lot : list) {
            // 3.1 全局计数
            switch (lot.getLotStatus()) {
                case "空闲":
                    chartRespVO.setIdleCount((chartRespVO.getIdleCount() == null ? 0 : chartRespVO.getIdleCount()) + 1);
                    break;
                case "占用":
                    chartRespVO.setOccupiedCount((chartRespVO.getOccupiedCount() == null ? 0 : chartRespVO.getOccupiedCount()) + 1);
                    break;
                case "维护中":
                    chartRespVO.setMaintainCount((chartRespVO.getMaintainCount() == null ? 0 : chartRespVO.getMaintainCount()) + 1);
                    break;
                default:
                    // 可记录日志或其他状态处理
                    break;
            }
            // 3.2 按状态统计
            statusCountMap.put(lot.getLotStatus(), statusCountMap.getOrDefault(lot.getLotStatus(), 0) + 1);

            // 3.3 按场站统计
            // 使用stationCode作为场站标识，如果stationCode为空，归到“未知场站”
            String stationKey = lot.getLotType() != null ? lot.getLotCode() : "未知场站";
            ChargingLotChartRespVO.StationLot stationStat = stationLotMap.computeIfAbsent(stationKey, k -> {
                ChargingLotChartRespVO.StationLot newStat = new ChargingLotChartRespVO.StationLot();
                newStat.setTotalCount(0);
                newStat.setIdleCount(0);
                newStat.setOccupiedCount(0);
                newStat.setMaintainCount(0);
                return newStat;
            });

            stationStat.setTotalCount(stationStat.getTotalCount() + 1);
            switch (lot.getLotStatus()) {
                case "空闲":
                    stationStat.setIdleCount(stationStat.getIdleCount() + 1);
                    break;
                case "占用":
                    stationStat.setOccupiedCount(stationStat.getOccupiedCount() + 1);
                    break;
                case "维护中":
                    stationStat.setMaintainCount(stationStat.getMaintainCount() + 1);
                    break;
            }
        }

        // 4. 计算全局总数
        chartRespVO.setTotalCount(list.size());

        // 5. 构建状态占比列表（饼图数据）
        List<ChargingLotChartRespVO.StatusRatio> statusRatioList = new ArrayList<>();
        BigDecimal total = new BigDecimal(chartRespVO.getTotalCount());
        for (Map.Entry<String, Integer> entry : statusCountMap.entrySet()) {
            ChargingLotChartRespVO.StatusRatio ratio = new ChargingLotChartRespVO.StatusRatio();
            ratio.setStatus(entry.getKey());
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
        // 可以按总车位数或其他规则排序
        stationLotList.sort((a, b) -> b.getTotalCount() - a.getTotalCount());
        chartRespVO.setStationLotList(stationLotList);

        return chartRespVO;
    }

}