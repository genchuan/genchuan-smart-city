package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingcoordinate;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingcoordinate.vo.GeocodingCoordinateSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingcoordinate.GeocodingCoordinateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 坐标系配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingCoordinateService {

    /**
     * 创建坐标系配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingCoordinate(@Valid GeocodingCoordinateSaveReqVO createReqVO);

    /**
     * 更新坐标系配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingCoordinate(@Valid GeocodingCoordinateSaveReqVO updateReqVO);

    /**
     * 删除坐标系配置
     *
     * @param id 编号
     */
    void deleteGeocodingCoordinate(Long id);

    /**
     * 获得坐标系配置
     *
     * @param id 编号
     * @return 坐标系配置
     */
    GeocodingCoordinateDO getGeocodingCoordinate(Long id);

    /**
     * 获得坐标系配置分页
     *
     * @param pageReqVO 分页查询
     * @return 坐标系配置分页
     */
    PageResult<GeocodingCoordinateDO> getGeocodingCoordinatePage(GeocodingCoordinatePageReqVO pageReqVO);

}