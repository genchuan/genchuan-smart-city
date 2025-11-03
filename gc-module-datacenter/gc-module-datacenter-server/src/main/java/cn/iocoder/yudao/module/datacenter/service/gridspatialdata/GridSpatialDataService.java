package cn.iocoder.yudao.module.datacenter.service.gridspatialdata;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.gridspatialdata.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.gridspatialdata.GridSpatialDataDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 网格空间数据 Service 接口
 *
 * @author zhucongquan
 */
public interface GridSpatialDataService {

    /**
     * 创建网格空间数据
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridSpatialData(@Valid GridSpatialDataSaveReqVO createReqVO);

    /**
     * 更新网格空间数据
     *
     * @param updateReqVO 更新信息
     */
    void updateGridSpatialData(@Valid GridSpatialDataSaveReqVO updateReqVO);

    /**
     * 删除网格空间数据
     *
     * @param id 编号
     */
    void deleteGridSpatialData(Long id);

    /**
     * 获得网格空间数据
     *
     * @param id 编号
     * @return 网格空间数据
     */
    GridSpatialDataDO getGridSpatialData(Long id);

    /**
     * 获得网格空间数据分页
     *
     * @param pageReqVO 分页查询
     * @return 网格空间数据分页
     */
    PageResult<GridSpatialDataDO> getGridSpatialDataPage(GridSpatialDataPageReqVO pageReqVO);

}