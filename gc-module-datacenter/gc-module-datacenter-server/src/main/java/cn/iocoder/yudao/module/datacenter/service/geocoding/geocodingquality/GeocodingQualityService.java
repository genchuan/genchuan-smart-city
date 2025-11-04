package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingquality;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingquality.vo.GeocodingQualitySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingquality.GeocodingQualityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 地理编码数据质量统计报表 Service 接口
 *
 * @author zcq
 */
public interface GeocodingQualityService {

    /**
     * 创建地理编码数据质量统计报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingQuality(@Valid GeocodingQualitySaveReqVO createReqVO);

    /**
     * 更新地理编码数据质量统计报表
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingQuality(@Valid GeocodingQualitySaveReqVO updateReqVO);

    /**
     * 删除地理编码数据质量统计报表
     *
     * @param id 编号
     */
    void deleteGeocodingQuality(Long id);

    /**
     * 获得地理编码数据质量统计报表
     *
     * @param id 编号
     * @return 地理编码数据质量统计报表
     */
    GeocodingQualityDO getGeocodingQuality(Long id);

    /**
     * 获得地理编码数据质量统计报表分页
     *
     * @param pageReqVO 分页查询
     * @return 地理编码数据质量统计报表分页
     */
    PageResult<GeocodingQualityDO> getGeocodingQualityPage(GeocodingQualityPageReqVO pageReqVO);

}