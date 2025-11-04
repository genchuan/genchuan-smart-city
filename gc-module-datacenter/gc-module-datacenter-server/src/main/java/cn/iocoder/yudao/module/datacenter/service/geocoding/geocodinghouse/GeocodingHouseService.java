package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodinghouse;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHousePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodinghouse.vo.GeocodingHouseSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodinghouse.GeocodingHouseDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 门(楼)牌数据管理 Service 接口
 *
 * @author zcq
 */
public interface GeocodingHouseService {

    /**
     * 创建门(楼)牌数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingHouse(@Valid GeocodingHouseSaveReqVO createReqVO);

    /**
     * 更新门(楼)牌数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingHouse(@Valid GeocodingHouseSaveReqVO updateReqVO);

    /**
     * 删除门(楼)牌数据管理
     *
     * @param id 编号
     */
    void deleteGeocodingHouse(Long id);

    /**
     * 获得门(楼)牌数据管理
     *
     * @param id 编号
     * @return 门(楼)牌数据管理
     */
    GeocodingHouseDO getGeocodingHouse(Long id);

    /**
     * 获得门(楼)牌数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 门(楼)牌数据管理分页
     */
    PageResult<GeocodingHouseDO> getGeocodingHousePage(GeocodingHousePageReqVO pageReqVO);

}