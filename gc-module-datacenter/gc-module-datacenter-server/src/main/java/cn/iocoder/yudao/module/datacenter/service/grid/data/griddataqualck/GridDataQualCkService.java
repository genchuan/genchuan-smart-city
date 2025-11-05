package cn.iocoder.yudao.module.datacenter.service.grid.data.griddataqualck;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.griddataqualck.vo.GridDataQualCkSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.griddataqualck.GridDataQualCkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格数据质量检查 Service 接口
 *
 * @author zcq
 */
public interface GridDataQualCkService {

    /**
     * 创建网格数据质量检查
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridDataQualCk(@Valid GridDataQualCkSaveReqVO createReqVO);

    /**
     * 更新网格数据质量检查
     *
     * @param updateReqVO 更新信息
     */
    void updateGridDataQualCk(@Valid GridDataQualCkSaveReqVO updateReqVO);

    /**
     * 删除网格数据质量检查
     *
     * @param id 编号
     */
    void deleteGridDataQualCk(Long id);

    /**
     * 获得网格数据质量检查
     *
     * @param id 编号
     * @return 网格数据质量检查
     */
    GridDataQualCkDO getGridDataQualCk(Long id);

    /**
     * 获得网格数据质量检查分页
     *
     * @param pageReqVO 分页查询
     * @return 网格数据质量检查分页
     */
    PageResult<GridDataQualCkDO> getGridDataQualCkPage(GridDataQualCkPageReqVO pageReqVO);

}