package cn.iocoder.yudao.module.datacenter.service.gridtopiclayer;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.gridtopiclayer.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.gridtopiclayer.GridTopicLayerDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 网格专题图层管理 Service 接口
 *
 * @author zcq
 */
public interface GridTopicLayerService {

    /**
     * 创建网格专题图层管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridTopicLayer(@Valid GridTopicLayerSaveReqVO createReqVO);

    /**
     * 更新网格专题图层管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGridTopicLayer(@Valid GridTopicLayerSaveReqVO updateReqVO);

    /**
     * 删除网格专题图层管理
     *
     * @param id 编号
     */
    void deleteGridTopicLayer(Long id);

    /**
     * 获得网格专题图层管理
     *
     * @param id 编号
     * @return 网格专题图层管理
     */
    GridTopicLayerDO getGridTopicLayer(Long id);

    /**
     * 获得网格专题图层管理分页
     *
     * @param pageReqVO 分页查询
     * @return 网格专题图层管理分页
     */
    PageResult<GridTopicLayerDO> getGridTopicLayerPage(GridTopicLayerPageReqVO pageReqVO);

}