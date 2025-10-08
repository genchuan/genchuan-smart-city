package cn.iocoder.yudao.module.datacenter.service.geocodingupdatecycle;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocodingupdatecycle.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocodingupdatecycle.GeocodingUpdatecycleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 数据更新周期配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingUpdatecycleService {

    /**
     * 创建数据更新周期配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingUpdatecycle(@Valid GeocodingUpdatecycleSaveReqVO createReqVO);

    /**
     * 更新数据更新周期配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingUpdatecycle(@Valid GeocodingUpdatecycleSaveReqVO updateReqVO);

    /**
     * 删除数据更新周期配置
     *
     * @param id 编号
     */
    void deleteGeocodingUpdatecycle(Long id);

    /**
     * 获得数据更新周期配置
     *
     * @param id 编号
     * @return 数据更新周期配置
     */
    GeocodingUpdatecycleDO getGeocodingUpdatecycle(Long id);

    /**
     * 获得数据更新周期配置分页
     *
     * @param pageReqVO 分页查询
     * @return 数据更新周期配置分页
     */
    PageResult<GeocodingUpdatecycleDO> getGeocodingUpdatecyclePage(GeocodingUpdatecyclePageReqVO pageReqVO);

}