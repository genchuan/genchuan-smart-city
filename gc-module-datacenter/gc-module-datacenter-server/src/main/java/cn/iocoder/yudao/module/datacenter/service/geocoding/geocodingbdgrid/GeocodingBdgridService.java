package cn.iocoder.yudao.module.datacenter.service.geocoding.geocodingbdgrid;

import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.geocoding.geocodingbdgrid.vo.GeocodingBdgridSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.geocoding.geocodingbdgrid.GeocodingBdgridDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 北斗网格位置码配置 Service 接口
 *
 * @author zcq
 */
public interface GeocodingBdgridService {

    /**
     * 创建北斗网格位置码配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGeocodingBdgrid(@Valid GeocodingBdgridSaveReqVO createReqVO);

    /**
     * 更新北斗网格位置码配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGeocodingBdgrid(@Valid GeocodingBdgridSaveReqVO updateReqVO);

    /**
     * 删除北斗网格位置码配置
     *
     * @param id 编号
     */
    void deleteGeocodingBdgrid(Long id);

    /**
     * 获得北斗网格位置码配置
     *
     * @param id 编号
     * @return 北斗网格位置码配置
     */
    GeocodingBdgridDO getGeocodingBdgrid(Long id);

    /**
     * 获得北斗网格位置码配置分页
     *
     * @param pageReqVO 分页查询
     * @return 北斗网格位置码配置分页
     */
    PageResult<GeocodingBdgridDO> getGeocodingBdgridPage(GeocodingBdgridPageReqVO pageReqVO);

}