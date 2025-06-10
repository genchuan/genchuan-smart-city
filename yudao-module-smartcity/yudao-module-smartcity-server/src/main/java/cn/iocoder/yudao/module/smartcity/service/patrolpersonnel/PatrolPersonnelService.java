package cn.iocoder.yudao.module.smartcity.service.patrolpersonnel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo.PatrolPersonnelPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.patrolpersonnel.vo.PatrolPersonnelSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.patrolpersonnel.PatrolPersonnelDO;
import jakarta.validation.Valid;

/**
 * 巡查人员 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PatrolPersonnelService {

    /**
     * 创建巡查人员
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPatrolPersonnel(@Valid PatrolPersonnelSaveReqVO createReqVO);

    /**
     * 更新巡查人员
     *
     * @param updateReqVO 更新信息
     */
    void updatePatrolPersonnel(@Valid PatrolPersonnelSaveReqVO updateReqVO);

    /**
     * 删除巡查人员
     *
     * @param id 编号
     */
    void deletePatrolPersonnel(Long id);

    /**
     * 获得巡查人员
     *
     * @param id 编号
     * @return 巡查人员
     */
    PatrolPersonnelDO getPatrolPersonnel(Long id);

    /**
     * 获得巡查人员分页
     *
     * @param pageReqVO 分页查询
     * @return 巡查人员分页
     */
    PageResult<PatrolPersonnelDO> getPatrolPersonnelPage(PatrolPersonnelPageReqVO pageReqVO);

}