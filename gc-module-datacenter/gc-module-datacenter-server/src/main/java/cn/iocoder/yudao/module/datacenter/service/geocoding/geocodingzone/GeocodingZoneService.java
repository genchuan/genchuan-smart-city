package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingzone;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZonePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingzone.vo.GeocodingZoneSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingzone.GeocodingZoneDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 地片与区片数据管理 Service 接口
 *
 * @author zcq
 */
public interface GeocodingZoneService {

    /**
     * 创建地片与区片数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingZone(@Valid GeocodingZoneSaveReqVO createReqVO);

    /**
     * 更新地片与区片数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingZone(@Valid GeocodingZoneSaveReqVO updateReqVO);

    /**
     * 删除地片与区片数据管理
     *
     * @param id 编号
     */
    void deleteGeocodingZone(Long id);

    /**
     * 获得地片与区片数据管理
     *
     * @param id 编号
     * @return 地片与区片数据管理
     */
    GeocodingZoneDO getGeocodingZone(Long id);

    /**
     * 获得地片与区片数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 地片与区片数据管理分页
     */
    PageResult<GeocodingZoneDO> getGeocodingZonePage(GeocodingZonePageReqVO pageReqVO);

}