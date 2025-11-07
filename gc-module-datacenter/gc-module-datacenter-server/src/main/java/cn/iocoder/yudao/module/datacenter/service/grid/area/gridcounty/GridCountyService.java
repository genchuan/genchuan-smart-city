package cn.iocoder.yudao.module.datacenter.service.grid.area.gridcounty;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountyPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcounty.vo.GridCountySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcounty.GridCountyDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 县级及以上行政区划配置 Service 接口
 *
 * @author zcq
 */
public interface GridCountyService {

    /**
     * 创建县级及以上行政区划配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridCounty(@Valid GridCountySaveReqVO createReqVO);

    /**
     * 更新县级及以上行政区划配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGridCounty(@Valid GridCountySaveReqVO updateReqVO);

    /**
     * 删除县级及以上行政区划配置
     *
     * @param id 编号
     */
    void deleteGridCounty(Long id);

    /**
     * 获得县级及以上行政区划配置
     *
     * @param id 编号
     * @return 县级及以上行政区划配置
     */
    GridCountyDO getGridCounty(Long id);

    /**
     * 获得县级及以上行政区划配置分页
     *
     * @param pageReqVO 分页查询
     * @return 县级及以上行政区划配置分页
     */
    PageResult<GridCountyDO> getGridCountyPage(GridCountyPageReqVO pageReqVO);

    /**
     * 获得所有县级及以上行政区划配置
     *
     * @return 所有县级及以上行政区划配置列表
     */
    List<GridCountyDO> getGridCountyList();

    /**
     * 根据上级行政区划ID查询下级行政区划列表
     * @param parentAdminId 上级行政区划ID
     * @return 下级行政区划列表
     */
    List<GridCountyDO> getGridCountyListByParentId(String parentAdminId);

    /**
     * 根据行政区划级别查询列表
     * @param adminLevel 行政区划级别(1-省级,2-市级,3-县级)
     * @return 行政区划列表
     */
    List<GridCountyDO> getGridCountyListByLevel(Integer adminLevel);
}