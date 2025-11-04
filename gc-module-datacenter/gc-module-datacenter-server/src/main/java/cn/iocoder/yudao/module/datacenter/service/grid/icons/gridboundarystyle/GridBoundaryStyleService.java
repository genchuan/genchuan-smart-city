package cn.iocoder.yudao.module.datacenter.service.grid.icons.gridboundarystyle;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStylePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridboundarystyle.vo.GridBoundaryStyleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridboundarystyle.GridBoundaryStyleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格边界线样式配置 Service 接口
 *
 * @author zcq
 */
public interface GridBoundaryStyleService {

    /**
     * 创建网格边界线样式配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridBoundaryStyle(@Valid GridBoundaryStyleSaveReqVO createReqVO);

    /**
     * 更新网格边界线样式配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGridBoundaryStyle(@Valid GridBoundaryStyleSaveReqVO updateReqVO);

    /**
     * 删除网格边界线样式配置
     *
     * @param id 编号
     */
    void deleteGridBoundaryStyle(Long id);

    /**
     * 获得网格边界线样式配置
     *
     * @param id 编号
     * @return 网格边界线样式配置
     */
    GridBoundaryStyleDO getGridBoundaryStyle(Long id);

    /**
     * 获得网格边界线样式配置分页
     *
     * @param pageReqVO 分页查询
     * @return 网格边界线样式配置分页
     */
    PageResult<GridBoundaryStyleDO> getGridBoundaryStylePage(GridBoundaryStylePageReqVO pageReqVO);

}