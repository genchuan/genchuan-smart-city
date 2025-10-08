package cn.iocoder.yudao.module.datacenter.service.geocodingquality;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingquality.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingquality.GeocodingQualityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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