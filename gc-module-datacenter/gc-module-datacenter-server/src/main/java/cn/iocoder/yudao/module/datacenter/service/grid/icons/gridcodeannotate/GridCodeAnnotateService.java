package cn.iocoder.yudao.module.datacenter.service.grid.icons.gridcodeannotate;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotatePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.icons.gridcodeannotate.vo.GridCodeAnnotateSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.icons.gridcodeannotate.GridCodeAnnotateDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格标识码注记配置 Service 接口
 *
 * @author zcq
 */
public interface GridCodeAnnotateService {

    /**
     * 创建网格标识码注记配置
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridCodeAnnotate(@Valid GridCodeAnnotateSaveReqVO createReqVO);

    /**
     * 更新网格标识码注记配置
     *
     * @param updateReqVO 更新信息
     */
    void updateGridCodeAnnotate(@Valid GridCodeAnnotateSaveReqVO updateReqVO);

    /**
     * 删除网格标识码注记配置
     *
     * @param id 编号
     */
    void deleteGridCodeAnnotate(Long id);

    /**
     * 获得网格标识码注记配置
     *
     * @param id 编号
     * @return 网格标识码注记配置
     */
    GridCodeAnnotateDO getGridCodeAnnotate(Long id);

    /**
     * 获得网格标识码注记配置分页
     *
     * @param pageReqVO 分页查询
     * @return 网格标识码注记配置分页
     */
    PageResult<GridCodeAnnotateDO> getGridCodeAnnotatePage(GridCodeAnnotatePageReqVO pageReqVO);

}