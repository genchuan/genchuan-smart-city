package cn.iocoder.yudao.module.vehiclecharging.service.chargingstation;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.chargingstation.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.chargingstation.ChargingStationDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.sysarea.AreaDO;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.chargingstation.ChargingStationMapper;
import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.sysarea.AreaMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
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

    @Resource
    private AreaMapper sysAreaMapper;

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
        update.setStopReason(""); // 启用清空原因
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
     * 获取充电站图表统计
     */
    @Override
    public ChargingStationChartRespVO getChartData(ChargingStationChartReqVO reqVO) {
        // 1. 拼接查询条件
        LambdaQueryWrapper<ChargingStationDO> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(ChargingStationDO::getDeleted, Boolean.FALSE);

        // 时间范围
        if (StrUtil.isNotBlank(reqVO.getStartTime())) {
            wrapper.ge(ChargingStationDO::getCreateTime, new Date(Long.parseLong(reqVO.getStartTime()) * 1000));
        }
        if (StrUtil.isNotBlank(reqVO.getEndTime())) {
            wrapper.le(ChargingStationDO::getCreateTime, new Date(Long.parseLong(reqVO.getEndTime()) * 1000));
        }

        List<ChargingStationDO> stationList = chargingStationMapper.selectList(wrapper);
        if (CollUtil.isEmpty(stationList)) {
            return new ChargingStationChartRespVO();
        }

        // ===================== 卡片统计 =====================
        ChargingStationChartRespVO.CardInfo cardInfo = new ChargingStationChartRespVO.CardInfo();
        int total = 0, enable = 0, disable = 0, unEnable = 0;

        // 区域统计：key=areaId, value=[总数, 启用数]
        Map<Long, int[]> areaMap = new HashMap<>();
        List<ChargingStationChartRespVO.StationMap> mapList = new ArrayList<>();

        for (ChargingStationDO station : stationList) {
            total++;
            String status = station.getStationStatus();

            if ("已启用".equals(status)) enable++;
            else if ("已停用".equals(status)) disable++;
            else if ("未启用".equals(status)) unEnable++;

            // 区域统计
            Long areaId = station.getAreaId();
            if (areaId != null && areaId > 0) {
                int[] arr = areaMap.getOrDefault(areaId, new int[2]);
                arr[0]++; // 总数
                if ("已启用".equals(status)) arr[1]++;
                areaMap.put(areaId, arr);
            }

            // 地图数据
            ChargingStationChartRespVO.StationMap map = new ChargingStationChartRespVO.StationMap();
            map.setId(station.getId());
            map.setStationName(station.getStationName());
            map.setLon(station.getLon());
            map.setLat(station.getLat());
            map.setStationStatus(station.getStationStatus());
            mapList.add(map);
        }

        cardInfo.setTotalCount(total);
        cardInfo.setEnableCount(enable);
        cardInfo.setDisableCount(disable);
        cardInfo.setUnEnableCount(unEnable);

        // ===================== 区域名称转换 =====================
        List<ChargingStationChartRespVO.AreaBar> barList = new ArrayList<>();
        if (CollUtil.isNotEmpty(areaMap.keySet())) {
            List<AreaDO> areas = sysAreaMapper.selectBatchIds(areaMap.keySet());
            Map<Long, String> areaNameMap = areas.stream()
                    .collect(Collectors.toMap(AreaDO::getId, AreaDO::getName));

            for (Map.Entry<Long, int[]> entry : areaMap.entrySet()) {
                ChargingStationChartRespVO.AreaBar bar = new ChargingStationChartRespVO.AreaBar();
                bar.setAreaName(areaNameMap.getOrDefault(entry.getKey(), "未知区域"));
                bar.setTotalCount(entry.getValue()[0]);
                bar.setEnableCount(entry.getValue()[1]);
                barList.add(bar);
            }
        }

        // ===================== 最终返回 =====================
        ChargingStationChartRespVO resp = new ChargingStationChartRespVO();
        resp.setCardInfo(cardInfo);
        resp.setStationMapList(mapList);
        resp.setAreaBarList(barList);
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

    @Override
    public List<ChargingStationAreaCountRespVO> getAreaStationCount(Long id) {
        // 直接调用Mapper查询统计数据
        return chargingStationMapper.selectAreaStationCount(id);
    }
}
