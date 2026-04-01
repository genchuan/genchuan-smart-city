package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation.ChargingStationMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.enums.GlobalErrorCodeConstants.BAD_REQUEST;
import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.CHARGING_STATION_CODE_EXISTS;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.CHARGING_STATION_NOT_EXISTS;
import static com.alibaba.nacos.shaded.io.grpc.Status.NOT_FOUND;

@Service
@Validated
public class ChargingStationServiceImpl implements ChargingStationService{

    @Resource
    private ChargingStationMapper chargingStationMapper;

    @Override
    public PageResult<ChargingStationRespVO> getChargingStationPage(ChargingStationPageReqVO reqVO) {
        PageResult<ChargingStationDO> page = chargingStationMapper.selectPage(reqVO);
        return BeanUtils.toBean(page, ChargingStationRespVO.class);
    }

    @Override
    public Long createChargingStation(ChargingStationCreateReqVO createReqVO) {
        // 1. 校验场站编号唯一
        validateStationCodeUnique(createReqVO.getStationCode());

        // 2. 构建DO对象
        ChargingStationDO station = new ChargingStationDO();
        station.setStationCode(createReqVO.getStationCode());
        station.setStationName(createReqVO.getStationName());
        station.setAddress(createReqVO.getAddress());
        station.setCoopMode(createReqVO.getCoopMode());
        station.setOpenTime(createReqVO.getOpenTime());
        station.setPriceService(createReqVO.getPriceService());
        station.setManager(createReqVO.getManager());
        station.setLon(createReqVO.getLon());
        station.setLat(createReqVO.getLat());
        station.setRemark(createReqVO.getRemark());
        station.setReserve1(createReqVO.getReserve1());
        station.setReserve2(createReqVO.getReserve2());

        // 默认启用状态（可根据业务调整）
        station.setStationStatus("未启用");

        // 3. 插入数据库
        chargingStationMapper.insert(station);

        // 4. 返回主键ID
        return station.getId();
    }

    /**
     * 校验场站编号唯一
     */
    private void validateStationCodeUnique(String stationCode) {
        ChargingStationDO station = chargingStationMapper.selectOne(
                new LambdaQueryWrapper<ChargingStationDO>()
                        .eq(ChargingStationDO::getStationCode, stationCode)
        );
        if (station != null) {
            throw exception(CHARGING_STATION_NOT_EXISTS);
        }
    }

    @Override
    public void updateChargingStation(ChargingStationUpdateReqVO updateReqVO) {
        // 1. 校验场站存在
        ChargingStationDO oldStation = validateStationExists(updateReqVO.getId());
        // 2. 校验场站编号唯一（排除自身）
        validateStationCodeUnique(updateReqVO.getStationCode(), updateReqVO.getId());

        // 3. 构建更新对象
        ChargingStationDO updateObj = new ChargingStationDO();
        updateObj.setId(updateReqVO.getId());
        updateObj.setStationCode(updateReqVO.getStationCode());
        updateObj.setStationName(updateReqVO.getStationName());
        updateObj.setAddress(updateReqVO.getAddress());
        updateObj.setCoopMode(updateReqVO.getCoopMode());
        updateObj.setOpenTime(updateReqVO.getOpenTime());
        updateObj.setPriceService(updateReqVO.getPriceService());
        updateObj.setManager(updateReqVO.getManager());
        updateObj.setLon(updateReqVO.getLon());
        updateObj.setLat(updateReqVO.getLat());
        updateObj.setRemark(updateReqVO.getRemark());
        updateObj.setReserve1(updateReqVO.getReserve1());
        updateObj.setReserve2(updateReqVO.getReserve2());

        // 4. 执行更新
        chargingStationMapper.updateById(updateObj);
    }

    /**
     * 校验场站编号唯一（编辑时排除自己）
     */
    private void validateStationCodeUnique(String stationCode, Long id) {
        ChargingStationDO station = chargingStationMapper.selectOne(
                new LambdaQueryWrapper<ChargingStationDO>()
                        .eq(ChargingStationDO::getStationCode, stationCode)
                        .ne(ChargingStationDO::getId, id) // 排除当前ID
        );
        if (station != null) {
            throw exception(CHARGING_STATION_CODE_EXISTS);
        }
    }

    /**
     * 校验场站是否存在
     */
    private ChargingStationDO validateStationExists(Long id) {
        ChargingStationDO station = chargingStationMapper.selectById(id);
        if (station == null) {
            throw new ServiceException(CHARGING_STATION_NOT_EXISTS);
        }
        return station;
    }

    /**
     * 获得充电站
     */
    @Override
    public ChargingStationRespVO getChargingStation(Long id) {
        ChargingStationDO station = validateStationExists(id);
        return BeanUtils.toBean(station, ChargingStationRespVO.class);
    }
    /**
     * 停用充电站
     */
    @Override
    public void batchDisable(ChargingStationDisableReqVO reqVO) {
        // 1. 批量更新：状态改为 已停用 + 填写停用原因
        LambdaUpdateWrapper<ChargingStationDO> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(ChargingStationDO::getId, reqVO.getIds());
        updateWrapper.set(ChargingStationDO::getStationStatus, "已停用");
        updateWrapper.set(ChargingStationDO::getStopReason, reqVO.getStopReason());

        chargingStationMapper.update(null, updateWrapper);
    }

    /**
     * 批量启用充电站
     */
    @Override
    public void batchEnable(ChargingStationEnableReqVO reqVO) {
        LambdaUpdateWrapper<ChargingStationDO> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(ChargingStationDO::getId, reqVO.getIds());
        // 启用：改为已启用，清空停用原因
        updateWrapper.set(ChargingStationDO::getStationStatus, "已启用");
        updateWrapper.set(ChargingStationDO::getStopReason, null);

        chargingStationMapper.update(null, updateWrapper);
    }
    /**
     * 停用充电站
     */
    @Override
    public void disable(Long id, String stopReason) {
        ChargingStationDO update = new ChargingStationDO();
        update.setId(id);
        update.setStationStatus("已停用");
        update.setStopReason(stopReason);
        chargingStationMapper.updateById(update);
    }
    /**
     * 启用充电站
     */
    @Override
    public void enable(Long id) {
        ChargingStationDO update = new ChargingStationDO();
        update.setId(id);
        update.setStationStatus("已启用");
        update.setStopReason(null); // 启用清空原因
        chargingStationMapper.updateById(update);
    }
    /**
     * 获得充电站列表, 用于 Excel 导出
     *
     * @param reqVO 列表请求
     * @return 充电站列表
     */
    @Override
    public PageResult<ChargingStationDO> getChargingStationPage(ChargingStationExportReqVO reqVO) {
        LambdaQueryWrapper<ChargingStationDO> wrapper = Wrappers.lambdaQuery();
        // 模糊查询
        wrapper.like(StrUtil.isNotBlank(reqVO.getStationCode()), ChargingStationDO::getStationCode, reqVO.getStationCode());
        wrapper.like(StrUtil.isNotBlank(reqVO.getStationName()), ChargingStationDO::getStationName, reqVO.getStationName());
        wrapper.like(StrUtil.isNotBlank(reqVO.getAddress()), ChargingStationDO::getAddress, reqVO.getAddress());
        // 精确匹配
        wrapper.eq(StrUtil.isNotBlank(reqVO.getCoopMode()), ChargingStationDO::getCoopMode, reqVO.getCoopMode());
        wrapper.eq(StrUtil.isNotBlank(reqVO.getStationStatus()), ChargingStationDO::getStationStatus, reqVO.getStationStatus());
        // 未删除
        wrapper.eq(ChargingStationDO::getDeleted, false);
        // 分页查询
        return chargingStationMapper.selectPage(reqVO, wrapper);
    }
    /**
     * 获取充电站统计
     *
     * @return 充电站统计
     */
    @Override
    public ChargingStationChartRespVO getChartData() {
        // 1. 查询所有未删除的充电站
        LambdaQueryWrapper<ChargingStationDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ChargingStationDO::getDeleted, false);
        List<ChargingStationDO> stationList = chargingStationMapper.selectList(wrapper);
        System.out.println(stationList);

        // ===================== 2. 统计各种数量 =====================
        int totalCount = 0;       // 总数
        int enableCount = 0;      // 已启用
        int disableCount = 0;     // 已停用
        int waitCount = 0;        // 未启用

        // 地址统计（key=地址，value=数量）
        Map<String, Integer> addressMap = new HashMap<>();

        // 地图点位集合
        List<ChargingStationChartRespVO.StationPoint> stationPoints = new ArrayList<>();

        // ===================== 遍历所有场站，一个一个统计 =====================
        for (ChargingStationDO station : stationList) {
            // 总数 +1
            totalCount++;

            // 按状态统计
            String status = station.getStationStatus();
            if ("已启用".equals(status)) {
                enableCount++;
            } else if ("已停用".equals(status)) {
                disableCount++;
            } else if ("未启用".equals(status)) {
                waitCount++;
            }

            // ===================== 地址统计（一模一样的地址就数量+1） =====================
            String address = station.getAddress();
            if (addressMap.containsKey(address)) {
                // 地址已存在，数量+1
                int count = addressMap.get(address);
                addressMap.put(address, count + 1);
            } else {
                // 地址不存在，设为1
                addressMap.put(address, 1);
            }

            // ===================== 封装地图点位 =====================
            ChargingStationChartRespVO.StationPoint point = new ChargingStationChartRespVO.StationPoint();
            point.setId(station.getId());
            point.setStationName(station.getStationName());
            point.setLon(station.getLon());
            point.setLat(station.getLat());
            point.setStatus(station.getStationStatus());
            point.setStatusName(station.getStationStatus());
            stationPoints.add(point);
        }

        // ===================== 把地址统计 转成前端需要的格式 =====================
        List<ChargingStationChartRespVO.AreaStat> areaList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : addressMap.entrySet()) {
            ChargingStationChartRespVO.AreaStat stat = new ChargingStationChartRespVO.AreaStat();
            stat.setAreaName(entry.getKey());    // 地址
            stat.setCount(entry.getValue());     // 数量
            areaList.add(stat);
        }

        // ===================== 封装返回 =====================
        ChargingStationChartRespVO resp = new ChargingStationChartRespVO();
        resp.setTotalCount(totalCount);
        resp.setEnableCount(enableCount);
        resp.setDisableCount(disableCount);
        resp.setWaitCount(waitCount);
        resp.setAreaList(areaList);
        resp.setStationPoints(stationPoints);

        return resp;
    }

    @Override
    public void batchChangeCooperationModeAndLeader(ChargingStationBatchUpdateReqVO reqVO) {
        // 1. 构建更新条件
        LambdaUpdateWrapper<ChargingStationDO> updateWrapper = Wrappers.lambdaUpdate();
        updateWrapper.in(ChargingStationDO::getId, reqVO.getIds()); // 批量更新哪些ID

        // 2. 构建要更新的字段 (只更新你要求的两个字段)
        ChargingStationDO updateEntity = new ChargingStationDO();
        updateEntity.setCoopMode(reqVO.getCoopMode());
        updateEntity.setManager(reqVO.getManager());

        // 3. 执行更新 (MyBatis Plus 批量更新)
        chargingStationMapper.update(updateEntity, updateWrapper);
    }
}
