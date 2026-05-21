package cn.iocoder.yudao.module.stationresource.api.stationresource;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.stationresource.dto.StationInfoRespDTO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.areamgmt.areainfo.AreaInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.stationmgmt.stationinfo.StationInfoDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.areamgmt.areainfo.AreaInfoMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.stationresource.stationmgmt.stationinfo.StationInfoMapper;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.pojo.CommonResult.success;

/**
 * 场站信息 RPC 实现 V2（完整版）
 *
 * <p>类名加 V2 后缀，避免与旧 api/station/StationInfoApiImpl 的 Spring Bean 名冲突
 */
@RestController
@Validated
public class StationInfoApiImplV2 implements StationInfoApi {

    @Resource
    private StationInfoMapper stationInfoMapper;

    @Resource
    private AreaInfoMapper areaInfoMapper;

    @Override
    public CommonResult<StationInfoRespDTO> getStation(Long id) {
        if (id == null) {
            return success(null);
        }
        StationInfoDO station = stationInfoMapper.selectById(id);
        if (station == null) {
            return success(null);
        }
        // 批量加载片区信息（单条也走批量，代码统一）
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(Collections.singletonList(station.getAreaId()));
        return success(toDTO(station, areaMap.get(station.getAreaId())));
    }

    @Override
    public CommonResult<List<StationInfoRespDTO>> listStations() {
        List<StationInfoDO> stations = stationInfoMapper.selectList();
        if (stations.isEmpty()) {
            return success(Collections.emptyList());
        }
        // 收集所有 areaId 批量加载片区
        List<Long> areaIds = stations.stream()
                .map(StationInfoDO::getAreaId)
                .filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(areaIds);
        List<StationInfoRespDTO> result = stations.stream()
                .map(s -> toDTO(s, areaMap.get(s.getAreaId())))
                .collect(Collectors.toList());
        return success(result);
    }

    @Override
    public CommonResult<List<StationInfoRespDTO>> listStationsByIds(Collection<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<Long> distinctIds = ids.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (distinctIds.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<StationInfoDO> stations = stationInfoMapper.selectBatchIds(distinctIds);
        if (stations.isEmpty()) {
            return success(new ArrayList<>());
        }
        List<Long> areaIds = stations.stream()
                .map(StationInfoDO::getAreaId)
                .filter(Objects::nonNull).distinct()
                .collect(Collectors.toList());
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(areaIds);
        List<StationInfoRespDTO> result = stations.stream()
                .map(s -> toDTO(s, areaMap.get(s.getAreaId())))
                .collect(Collectors.toList());
        return success(result);
    }

    // ==================== 私有工具方法 ====================

    /**
     * 批量加载片区信息
     */
    private Map<Long, AreaInfoDO> loadAreaMap(List<Long> areaIds) {
        List<Long> valid = areaIds.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (valid.isEmpty()) {
            return Collections.emptyMap();
        }
        return areaInfoMapper.selectBatchIds(valid).stream()
                .collect(Collectors.toMap(AreaInfoDO::getId, a -> a, (a, b) -> a));
    }

    /**
     * StationInfoDO + AreaInfoDO → StationInfoRespDTO
     *
     * <p>完整映射 station_info + BaseDO 全部字段 + 片区坐标/名称 + 空位数计算
     */
    private StationInfoRespDTO toDTO(StationInfoDO s, AreaInfoDO area) {
        StationInfoRespDTO dto = new StationInfoRespDTO();
        // station_info 本体字段
        dto.setId(s.getId());
        dto.setStationNo(s.getStationNo());
        dto.setName(s.getName());
        dto.setType(s.getType());
        dto.setAddress(s.getAddress());
        dto.setSpaceTotal(s.getSpaceTotal());
        dto.setUserId(s.getUserId());
        // leaderName 需要跨模块查 system_user,RPC 层不填充,调用方自行注入
        dto.setLeaderName(null);
        dto.setFeeStandard(s.getFeeStandard());
        dto.setAreaId(s.getAreaId());
        dto.setOperateType(s.getOperateType());
        dto.setStatus(s.getStatus());
        dto.setBindTime(s.getBindTime());
        dto.setBindUserId(s.getBindUserId());
        dto.setDeviceCount(s.getDeviceCount());
        dto.setSpaceCount(s.getSpaceCount());
        dto.setRemark(s.getRemark());
        dto.setReserve1(s.getReserve1());
        dto.setReserve2(s.getReserve2());
        // BaseDO 审计字段
        dto.setCreateTime(s.getCreateTime());
        dto.setCreator(s.getCreator());
        dto.setUpdater(s.getUpdater());
        dto.setUpdateTime(s.getUpdateTime());
        // 片区跨表字段
        if (area != null) {
            dto.setAreaName(area.getName());
            dto.setLon(area.getLon() == null ? null : BigDecimal.valueOf(area.getLon()));
            dto.setLat(area.getLat() == null ? null : BigDecimal.valueOf(area.getLat()));
        }
        // 空位数计算
        int total = s.getSpaceTotal() == null ? 0 : s.getSpaceTotal();
        int used = s.getSpaceCount() == null ? 0 : s.getSpaceCount();
        dto.setEmptySpace(Math.max(total - used, 0));
        return dto;
    }
}
