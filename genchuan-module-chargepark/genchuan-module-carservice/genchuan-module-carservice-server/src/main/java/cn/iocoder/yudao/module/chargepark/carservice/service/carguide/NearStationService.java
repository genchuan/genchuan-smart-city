package cn.iocoder.yudao.module.chargepark.carservice.service.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationNearbyListRespVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.NearStationSaveReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.NearStationDO;

import java.util.List;

/**
 * 周边场站 Service 接口
 *
 * @author carservice
 */
public interface NearStationService {

    Long createNearStation(NearStationSaveReqVO createReqVO);

    void updateNearStation(NearStationSaveReqVO updateReqVO);

    void deleteNearStation(Long id);

    void deleteNearStationListByIds(List<Long> ids);

    NearStationDO getNearStation(Long id);

    PageResult<NearStationDO> getNearStationPage(NearStationPageReqVO pageReqVO);

    /**
     * 以某条 near_station 查询记录的 query_location 为圆心,从 stationresource 拉全部场站,
     * 按 Haversine 距离过滤 & 排序,返回附近场站明细。
     *
     * @param id        near_station 查询记录 ID
     * @param radiusKm  半径(km),null 时默认 5
     * @return 包含实际使用的 radiusKm + 升序(近→远)的场站列表
     */
    NearStationNearbyListRespVO listNearbyStations(Long id, Double radiusKm);

}
