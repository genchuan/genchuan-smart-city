package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingdensity;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingdensity.vo.GeocodingDensitySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingdensity.GeocodingDensityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 数据采集密度配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingDensityService {

    /**
     * 创建数据采集密度配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingDensity(@Valid GeocodingDensitySaveReqVO createReqVO);

    /**
     * 更新数据采集密度配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingDensity(@Valid GeocodingDensitySaveReqVO updateReqVO);

    /**
     * 删除数据采集密度配置
     *
     * @param id 编号
     */
    void deleteGeocodingDensity(Long id);

    /**
     * 获得数据采集密度配置
     *
     * @param id 编号
     * @return 数据采集密度配置
     */
    GeocodingDensityDO getGeocodingDensity(Long id);

    /**
     * 获得数据采集密度配置分页
     *
     * @param pageReqVO 分页查询
     * @return 数据采集密度配置分页
     */
    PageResult<GeocodingDensityDO> getGeocodingDensityPage(GeocodingDensityPageReqVO pageReqVO);

}