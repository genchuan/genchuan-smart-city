package cn.iocoder.yudao.module.datacenter.service.geocodinglayer;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodinglayer.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodinglayer.GeocodingLayerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 图层代码配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingLayerService {

    /**
     * 创建图层代码配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingLayer(@Valid GeocodingLayerSaveReqVO createReqVO);

    /**
     * 更新图层代码配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingLayer(@Valid GeocodingLayerSaveReqVO updateReqVO);

    /**
     * 删除图层代码配置
     *
     * @param id 编号
     */
    void deleteGeocodingLayer(Long id);

    /**
     * 获得图层代码配置
     *
     * @param id 编号
     * @return 图层代码配置
     */
    GeocodingLayerDO getGeocodingLayer(Long id);

    /**
     * 获得图层代码配置分页
     *
     * @param pageReqVO 分页查询
     * @return 图层代码配置分页
     */
    PageResult<GeocodingLayerDO> getGeocodingLayerPage(GeocodingLayerPageReqVO pageReqVO);

}