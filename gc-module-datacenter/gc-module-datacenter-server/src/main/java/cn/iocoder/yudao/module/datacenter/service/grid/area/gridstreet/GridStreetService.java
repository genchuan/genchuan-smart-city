package cn.iocoder.yudao.module.datacenter.service.grid.area.gridstreet;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridstreet.vo.GridStreetSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridstreet.GridStreetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 街道（镇、乡）行政区划配置 Service 接口
 *
 * @author zcq
 */
public interface GridStreetService {

    /**
     * 创建街道（镇、乡）行政区划配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridStreet(@Valid GridStreetSaveReqVO createReqVO);

    /**
     * 更新街道（镇、乡）行政区划配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGridStreet(@Valid GridStreetSaveReqVO updateReqVO);

    /**
     * 删除街道（镇、乡）行政区划配置
     *
     * @param id 编号
     */
    void deleteGridStreet(Long id);

    /**
     * 获得街道（镇、乡）行政区划配置
     *
     * @param id 编号
     * @return 街道（镇、乡）行政区划配置
     */
    GridStreetDO getGridStreet(Long id);

    /**
     * 获得街道（镇、乡）行政区划配置分页
     *
     * @param pageReqVO 分页查询
     * @return 街道（镇、乡）行政区划配置分页
     */
    PageResult<GridStreetDO> getGridStreetPage(GridStreetPageReqVO pageReqVO);

    /**
     * 获得所有街道（镇、乡）行政区划配置列表
     *
     * @return 街道（镇、乡）行政区划配置列表
     */
    List<GridStreetDO> getGridStreetList();

    /**
     * 根据县级行政区ID查询街道列表
     * @param countyAdminId 县级行政区ID
     * @return 街道列表
     */
    List<GridStreetDO> getGridStreetListByCountyId(String countyAdminId);

    /**
     * 根据街道级别查询列表
     * @param streetLevel 街道级别(1-街道,2-镇,3-乡)
     * @return 街道列表
     */
    List<GridStreetDO> getGridStreetListByLevel(Integer streetLevel);
}