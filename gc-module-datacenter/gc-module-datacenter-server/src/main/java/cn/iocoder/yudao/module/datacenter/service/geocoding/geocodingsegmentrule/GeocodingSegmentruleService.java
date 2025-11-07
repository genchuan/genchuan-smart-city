package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingsegmentrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingsegmentrule.vo.GeocodingSegmentruleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingsegmentrule.GeocodingSegmentruleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 地理编码分段组合规则配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingSegmentruleService {

    /**
     * 创建地理编码分段组合规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingSegmentrule(@Valid GeocodingSegmentruleSaveReqVO createReqVO);

    /**
     * 更新地理编码分段组合规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingSegmentrule(@Valid GeocodingSegmentruleSaveReqVO updateReqVO);

    /**
     * 删除地理编码分段组合规则配置
     *
     * @param id 编号
     */
    void deleteGeocodingSegmentrule(Long id);

    /**
     * 获得地理编码分段组合规则配置
     *
     * @param id 编号
     * @return 地理编码分段组合规则配置
     */
    GeocodingSegmentruleDO getGeocodingSegmentrule(Long id);

    /**
     * 获得地理编码分段组合规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 地理编码分段组合规则配置分页
     */
    PageResult<GeocodingSegmentruleDO> getGeocodingSegmentrulePage(GeocodingSegmentrulePageReqVO pageReqVO);

}