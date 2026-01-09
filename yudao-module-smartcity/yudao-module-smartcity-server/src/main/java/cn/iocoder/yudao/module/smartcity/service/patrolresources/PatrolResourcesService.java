package cn.iocoder.yudao.module.smartcity.service.patrolresources;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.PatrolResourcesPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolresources.vo.PatrolResourcesSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolresources.PatrolResourcesDO;
import jakarta.validation.Valid;

/**
 * 巡查资源 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PatrolResourcesService {

    /**
     * 创建巡查资源
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatrolResources(@Valid PatrolResourcesSaveReqVO createReqVO);

    /**
     * 更新巡查资源
     *
     * @param updateReqVO 更新信息
     */
    void updatePatrolResources(@Valid PatrolResourcesSaveReqVO updateReqVO);

    /**
     * 删除巡查资源
     *
     * @param id 编号
     */
    void deletePatrolResources(Long id);

    /**
     * 获得巡查资源
     *
     * @param id 编号
     * @return 巡查资源
     */
    PatrolResourcesDO getPatrolResources(Long id);

    /**
     * 获得巡查资源分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查资源分页
     */
    PageResult<PatrolResourcesDO> getPatrolResourcesPage(PatrolResourcesPageReqVO pageReqVO);

}