package cn.iocoder.yudao.module.stationresource.api.station;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
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

@RestController // 提供 RESTful API 接口,给 Feign 调用
@Validated
public class StationInfoApiImpl implements StationInfoApi {

    @Resource private StationInfoMapper stationInfoMapper;
    @Resource private AreaInfoMapper areaInfoMapper;

    @Override
    public CommonResult<StationInfoRespDTO> getStation(Long id) {
        if (id == null) {
            return success(null);
        }
        StationInfoDO station = stationInfoMapper.selectById(id);
        if (station == null) {
            return success(null);
        }
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(Collections.singletonList(station.getAreaId()));
        return success(toDTO(station, areaMap.get(station.getAreaId())));
    }

    @Override
    public CommonResult<List<StationInfoRespDTO>> getStationList(Collection<Long> ids) {
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
        List<Long> areaIds = stations.stream().map(StationInfoDO::getAreaId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(areaIds);
        return success(stations.stream().map(s -> toDTO(s, areaMap.get(s.getAreaId()))).collect(Collectors.toList()));
    }

    @Override
    public CommonResult<List<StationInfoRespDTO>> listStations() {
        // 只返回已生效场站,避免全表扫禁用/未生效记录
        List<StationInfoDO> stations = stationInfoMapper.selectList();
        if (stations.isEmpty()) {
            return success(Collections.emptyList());
        }
        List<Long> areaIds = stations.stream().map(StationInfoDO::getAreaId).filter(Objects::nonNull).distinct().collect(Collectors.toList());
        Map<Long, AreaInfoDO> areaMap = loadAreaMap(areaIds);
        return success(stations.stream().map(s -> toDTO(s, areaMap.get(s.getAreaId()))).collect(Collectors.toList()));
    }

    private Map<Long, AreaInfoDO> loadAreaMap(List<Long> areaIds) {
        List<Long> valid = areaIds.stream().filter(Objects::nonNull).distinct().collect(Collectors.toList());
        if (valid.isEmpty()) {
            return Collections.emptyMap();
        }
        return areaInfoMapper.selectBatchIds(valid).stream()
                .collect(Collectors.toMap(AreaInfoDO::getId, a -> a, (a, b) -> a));
    }

    private StationInfoRespDTO toDTO(StationInfoDO s, AreaInfoDO area) {
        StationInfoRespDTO dto = new StationInfoRespDTO();
        dto.setId(s.getId());
        dto.setName(s.getName());
        dto.setAddress(s.getAddress());
        dto.setSpaceTotal(s.getSpaceTotal());
        dto.setSpaceCount(s.getSpaceCount());
        dto.setAreaId(s.getAreaId());
        if (area != null) {
            dto.setLon(area.getLon() == null ? null : BigDecimal.valueOf(area.getLon()));
            dto.setLat(area.getLat() == null ? null : BigDecimal.valueOf(area.getLat()));
        }
        // 服务端统一算空位数,避免调用方各自复制口径
        int total = s.getSpaceTotal() == null ? 0 : s.getSpaceTotal();
        int used = s.getSpaceCount() == null ? 0 : s.getSpaceCount();
        dto.setEmptySpace(Math.max(total - used, 0));
        return dto;
    }

}
