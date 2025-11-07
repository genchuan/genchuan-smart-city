package cn.iocoder.yudao.module.datacenter.service.grid.area.gridcommunity;

import java.util.*;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunityPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.area.gridcommunity.vo.GridCommunitySaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.area.gridcommunity.GridCommunityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 社区（村）行政区划配置 Service 接口
 *
 * @author zcq
 */
public interface GridCommunityService {

    /**
     * 创建社区（村）行政区划配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridCommunity(@Valid GridCommunitySaveReqVO createReqVO);

    /**
     * 更新社区（村）行政区划配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGridCommunity(@Valid GridCommunitySaveReqVO updateReqVO);

    /**
     * 删除社区（村）行政区划配置
     *
     * @param id 编号
     */
    void deleteGridCommunity(Long id);

    /**
     * 获得社区（村）行政区划配置
     *
     * @param id 编号
     * @return 社区（村）行政区划配置
     */
    GridCommunityDO getGridCommunity(Long id);

    /**
     * 获得社区（村）行政区划配置分页
     *
     * @param pageReqVO 分页查询
     * @return 社区（村）行政区划配置分页
     */
    PageResult<GridCommunityDO> getGridCommunityPage(GridCommunityPageReqVO pageReqVO);

    /**
     * 获得所有社区（村）行政区划配置列表
     *
     * @return 社区（村）行政区划配置列表
     */
    List<GridCommunityDO> getGridCommunityList();

    /**
     * 根据街道ID获取社区列表
     * @param streetId 街道ID
     * @return 社区列表
     */
    List<GridCommunityDO> getGridCommunityListByStreetId(String streetId);

    /**
     * 根据街道ID列表批量获取社区列表
     * @param streetIds 街道ID列表
     * @return 社区列表
     */
    List<GridCommunityDO> getGridCommunityListByStreetIds(List<String> streetIds);

    /**
     * 根据社区名称模糊查询
     * @param communityName 社区名称
     * @return 社区列表
     */
    List<GridCommunityDO> getGridCommunityListByName(String communityName);
}