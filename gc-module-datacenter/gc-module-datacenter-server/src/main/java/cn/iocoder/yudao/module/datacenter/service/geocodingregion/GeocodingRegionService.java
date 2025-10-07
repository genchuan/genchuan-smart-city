package cn.iocoder.yudao.module.datacenter.service.geocodingregion;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingregion.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingregion.GeocodingRegionDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 区域数据管理 Service 接口
 *
 * @author zcq
 */
public interface GeocodingRegionService {

    /**
     * 创建区域数据管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingRegion(@Valid GeocodingRegionSaveReqVO createReqVO);

    /**
     * 更新区域数据管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingRegion(@Valid GeocodingRegionSaveReqVO updateReqVO);

    /**
     * 删除区域数据管理
     *
     * @param id 编号
     */
    void deleteGeocodingRegion(Long id);

    /**
     * 获得区域数据管理
     *
     * @param id 编号
     * @return 区域数据管理
     */
    GeocodingRegionDO getGeocodingRegion(Long id);

    /**
     * 获得区域数据管理分页
     *
     * @param pageReqVO 分页查询
     * @return 区域数据管理分页
     */
    PageResult<GeocodingRegionDO> getGeocodingRegionPage(GeocodingRegionPageReqVO pageReqVO);

}