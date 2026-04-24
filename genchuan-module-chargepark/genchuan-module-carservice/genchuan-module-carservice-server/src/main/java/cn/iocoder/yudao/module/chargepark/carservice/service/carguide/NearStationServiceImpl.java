package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationNearbyListRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationNearbyRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide.NearStationMapper;
import cn.iocoder.yudao.module.stationresource.api.station.StationInfoApi;
import cn.iocoder.yudao.module.stationresource.api.station.dto.StationInfoRespDTO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.carservice.enums.ErrorCodeConstants.NEAR_STATION_NOT_EXISTS;

/**
 * 周边场站 Service 实现类
 *
 * @author carservice
 */
@Slf4j
@Service
@Validated
public class NearStationServiceImpl implements NearStationService {

    /** 默认半径 5km(和看板柱状图的 >5km 桶口径一致) */
    private static final double DEFAULT_RADIUS_KM = 5.0;

    @Resource
    private NearStationMapper nearStationMapper;

    @Resource
    private StationInfoApi stationInfoApi;

    @Override
    public Long createNearStation(NearStationSaveReqVO createReqVO) {
        NearStationDO nearStation = BeanUtils.toBean(createReqVO, NearStationDO.class);
        if (nearStation.getQueryTime() == null) {
            nearStation.setQueryTime(LocalDateTime.now());
        }
        nearStationMapper.insert(nearStation);
        return nearStation.getId();
    }

    @Override
    public void updateNearStation(NearStationSaveReqVO updateReqVO) {
        validateNearStationExists(updateReqVO.getId());
        NearStationDO updateObj = BeanUtils.toBean(updateReqVO, NearStationDO.class);
        nearStationMapper.updateById(updateObj);
    }

    @Override
    public void deleteNearStation(Long id) {
        validateNearStationExists(id);
        nearStationMapper.deleteById(id);
    }

    @Override
    public void deleteNearStationListByIds(List<Long> ids) {
        nearStationMapper.deleteByIds(ids);
    }

    private void validateNearStationExists(Long id) {
        if (nearStationMapper.selectById(id) == null) {
            throw exception(NEAR_STATION_NOT_EXISTS);
        }
    }

    @Override
    public NearStationDO getNearStation(Long id) {
        return nearStationMapper.selectById(id);
    }

    @Override
    public PageResult<NearStationDO> getNearStationPage(NearStationPageReqVO pageReqVO) {
        return nearStationMapper.selectPage(pageReqVO);
    }

    @Override
    public NearStationNearbyListRespVO listNearbyStations(Long id, Double radiusKm) {
        NearStationDO record = nearStationMapper.selectById(id);
        if (record == null) {
            throw exception(NEAR_STATION_NOT_EXISTS);
        }
        double r = (radiusKm == null || radiusKm <= 0) ? DEFAULT_RADIUS_KM : radiusKm;
        NearStationNearbyListRespVO resp = new NearStationNearbyListRespVO();
        resp.setRadiusKm(BigDecimal.valueOf(r).setScale(2, RoundingMode.HALF_UP));

        double[] center = parseLonLat(record.getQueryLocation());
        if (center == null) {
            resp.setList(Collections.emptyList());
            return resp;
        }

        List<StationInfoRespDTO> stations;
        try {
            CommonResult<List<StationInfoRespDTO>> rpc = stationInfoApi.listStations();
            stations = rpc == null || rpc.getData() == null ? new ArrayList<>() : rpc.getData();
        } catch (Exception ex) {
            log.warn("[listNearbyStations] stationresource RPC 失败,降级返回空列表", ex);
            resp.setList(Collections.emptyList());
            return resp;
        }

        List<NearStationNearbyRespVO> list = stations.stream()
                .filter(s -> s.getLon() != null && s.getLat() != null)
                .map(s -> {
                    double km = haversineKm(center[0], center[1],
                            s.getLon().doubleValue(), s.getLat().doubleValue());
                    NearStationNearbyRespVO vo = new NearStationNearbyRespVO();
                    vo.setStationId(s.getId());
                    vo.setStationName(s.getName());
                    vo.setLon(s.getLon());
                    vo.setLat(s.getLat());
                    vo.setDistanceKm(BigDecimal.valueOf(km).setScale(2, RoundingMode.HALF_UP));
                    vo.setEmptySpace(s.getEmptySpace());
                    return vo;
                })
                .filter(vo -> vo.getDistanceKm().doubleValue() <= r)
                .sorted(Comparator.comparing(NearStationNearbyRespVO::getDistanceKm))
                .collect(Collectors.toList());
        resp.setList(list);
        return resp;
    }

    /** 解析 "lon,lat" 字符串,不合法返回 null */
    private double[] parseLonLat(String location) {
        if (location == null || location.isEmpty()) return null;
        String[] parts = location.split(",");
        if (parts.length != 2) return null;
        try {
            return new double[]{Double.parseDouble(parts[0].trim()), Double.parseDouble(parts[1].trim())};
        } catch (NumberFormatException e) {
            return null;
        }
    }

    /** Haversine 大圆距离(km),地球平均半径 6371km */
    private double haversineKm(double lon1, double lat1, double lon2, double lat2) {
        double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }

}
