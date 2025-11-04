package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingstat;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingstat.vo.GeocodingStatSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingstat.GeocodingStatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 基本地点数据统计报表 Service 接口
 *
 * @author zcq
 */
public interface GeocodingStatService {

    /**
     * 按行政区域统计各类数据数量
     *
     * @return 统计结果
     */
    Map<String, Object> countAllTables();

    /**
     * 获取统计摘要信息
     */
    Map<String, Object> getStatisticsSummary();


//    ================================以下方法都废弃===================================
    /**
     * 创建基本地点数据统计报表
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingStat(@Valid GeocodingStatSaveReqVO createReqVO);

    /**
     * 更新基本地点数据统计报表
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingStat(@Valid GeocodingStatSaveReqVO updateReqVO);

    /**
     * 删除基本地点数据统计报表
     *
     * @param id 编号
     */
    void deleteGeocodingStat(Long id);

    /**
     * 获得基本地点数据统计报表
     *
     * @param id 编号
     * @return 基本地点数据统计报表
     */
    GeocodingStatDO getGeocodingStat(Long id);

    /**
     * 获得基本地点数据统计报表分页
     *
     * @param pageReqVO 分页查询
     * @return 基本地点数据统计报表分页
     */
    PageResult<GeocodingStatDO> getGeocodingStatPage(GeocodingStatPageReqVO pageReqVO);

}