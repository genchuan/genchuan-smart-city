package cn.iocoder.yudao.module.datacenter.service.gridcommunity;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.gridcommunity.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.gridcommunity.GridCommunityDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

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
}