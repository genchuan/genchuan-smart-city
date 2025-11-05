package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingstreet;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstreet.vo.GeocodingStreetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstreet.GeocodingStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 街巷数据管理 Service 接口
 *
 * @author zcq
 */
public interface GeocodingStreetService {

    /**
     * 创建街巷数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingStreet(@Valid GeocodingStreetSaveReqVO createReqVO);

    /**
     * 更新街巷数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingStreet(@Valid GeocodingStreetSaveReqVO updateReqVO);

    /**
     * 删除街巷数据管理
     *
     * @param id 编号
     */
    void deleteGeocodingStreet(Long id);

    /**
     * 获得街巷数据管理
     *
     * @param id 编号
     * @return 街巷数据管理
     */
    GeocodingStreetDO getGeocodingStreet(Long id);

    /**
     * 获得街巷数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 街巷数据管理分页
     */
    PageResult<GeocodingStreetDO> getGeocodingStreetPage(GeocodingStreetPageReqVO pageReqVO);

}