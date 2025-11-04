package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingidrule;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo.GeocodingIdrulePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingidrule.vo.GeocodingIdruleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingidrule.GeocodingIdruleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 标识码编码规则配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingIdruleService {

    /**
     * 创建标识码编码规则配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingIdrule(@Valid GeocodingIdruleSaveReqVO createReqVO);

    /**
     * 更新标识码编码规则配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingIdrule(@Valid GeocodingIdruleSaveReqVO updateReqVO);

    /**
     * 删除标识码编码规则配置
     *
     * @param id 编号
     */
    void deleteGeocodingIdrule(Long id);

    /**
     * 获得标识码编码规则配置
     *
     * @param id 编号
     * @return 标识码编码规则配置
     */
    GeocodingIdruleDO getGeocodingIdrule(Long id);

    /**
     * 获得标识码编码规则配置分页
     *
     * @param pageReqVO 分页查询
     * @return 标识码编码规则配置分页
     */
    PageResult<GeocodingIdruleDO> getGeocodingIdrulePage(GeocodingIdrulePageReqVO pageReqVO);

}