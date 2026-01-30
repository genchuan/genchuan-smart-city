package cn.iocoder.yudao.module.park.service.park.basicAssociation.gridmanage;

import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManagePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.basicAssociation.gridmanage.vo.GridManageSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.park.dal.dataobject.park.basicAssociation.gridmanage.GridManageDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 网格管理 Service 接口
 *
 * @author zhucongquan
 */
public interface GridManageService {

    /**
     * 创建网格管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createGridManage(@Valid GridManageSaveReqVO createReqVO);

    /**
     * 更新网格管理
     *
     * @param updateReqVO 更新信息
     */
    void updateGridManage(@Valid GridManageSaveReqVO updateReqVO);

    /**
     * 删除网格管理
     *
     * @param id 编号
     */
    void deleteGridManage(Long id);

    /**
     * 获得网格管理
     *
     * @param id 编号
     * @return 网格管理
     */
    GridManageDO getGridManage(Long id);

    /**
     * 获得网格管理分页
     *
     * @param pageReqVO 分页查询
     * @return 网格管理分页
     */
    PageResult<GridManageDO> getGridManagePage(GridManagePageReqVO pageReqVO);

}