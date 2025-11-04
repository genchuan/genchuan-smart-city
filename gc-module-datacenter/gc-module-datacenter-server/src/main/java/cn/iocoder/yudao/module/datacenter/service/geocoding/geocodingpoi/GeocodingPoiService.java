package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingpoi;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingpoi.vo.GeocodingPoiSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingpoi.GeocodingPoiDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 兴趣点数据管理 Service 接口
 *
 * @author zcq
 */
public interface GeocodingPoiService {

    /**
     * 创建兴趣点数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingPoi(@Valid GeocodingPoiSaveReqVO createReqVO);

    /**
     * 更新兴趣点数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingPoi(@Valid GeocodingPoiSaveReqVO updateReqVO);

    /**
     * 删除兴趣点数据管理
     *
     * @param id 编号
     */
    void deleteGeocodingPoi(Long id);

    /**
     * 获得兴趣点数据管理
     *
     * @param id 编号
     * @return 兴趣点数据管理
     */
    GeocodingPoiDO getGeocodingPoi(Long id);

    /**
     * 获得兴趣点数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 兴趣点数据管理分页
     */
    PageResult<GeocodingPoiDO> getGeocodingPoiPage(GeocodingPoiPageReqVO pageReqVO);

}