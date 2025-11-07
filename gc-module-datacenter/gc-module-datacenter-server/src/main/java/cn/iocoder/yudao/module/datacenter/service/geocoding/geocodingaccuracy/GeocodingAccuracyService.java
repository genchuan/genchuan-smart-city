package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingaccuracy;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracyPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingaccuracy.vo.GeocodingAccuracySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingaccuracy.GeocodingAccuracyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 位置精度标准配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingAccuracyService {

    /**
     * 创建位置精度标准配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingAccuracy(@Valid GeocodingAccuracySaveReqVO createReqVO);

    /**
     * 更新位置精度标准配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingAccuracy(@Valid GeocodingAccuracySaveReqVO updateReqVO);

    /**
     * 删除位置精度标准配置
     *
     * @param id 编号
     */
    void deleteGeocodingAccuracy(Long id);

    /**
     * 获得位置精度标准配置
     *
     * @param id 编号
     * @return 位置精度标准配置
     */
    GeocodingAccuracyDO getGeocodingAccuracy(Long id);

    /**
     * 获得位置精度标准配置分页
     *
     * @param pageReqVO 分页查询
     * @return 位置精度标准配置分页
     */
    PageResult<GeocodingAccuracyDO> getGeocodingAccuracyPage(GeocodingAccuracyPageReqVO pageReqVO);

}