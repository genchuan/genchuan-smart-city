package cn.iocoder.yudao.module.smartcity.service.managementofpatrolpersonnel;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.ManagementOfPatrolPersonnelPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.managementofpatrolpersonnel.vo.ManagementOfPatrolPersonnelSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.managementofpatrolpersonnel.ManagementOfPatrolPersonnelDO;
import jakarta.validation.Valid;

/**
 * 巡査人员管理 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface ManagementOfPatrolPersonnelService {

    /**
     * 创建巡査人员管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createManagementOfPatrolPersonnel(@Valid ManagementOfPatrolPersonnelSaveReqVO createReqVO);

    /**
     * 更新巡査人员管理
     *
     * @param updateReqVO 更新信息
     */
    void updateManagementOfPatrolPersonnel(@Valid ManagementOfPatrolPersonnelSaveReqVO updateReqVO);

    /**
     * 删除巡査人员管理
     *
     * @param id 编号
     */
    void deleteManagementOfPatrolPersonnel(Long id);

    /**
     * 获得巡査人员管理
     *
     * @param id 编号
     * @return 巡査人员管理
     */
    ManagementOfPatrolPersonnelDO getManagementOfPatrolPersonnel(Long id);

    /**
     * 获得巡査人员管理分页
     *
     * @param pageReqVO 分页查询
     * @return 巡査人员管理分页
     */
    PageResult<ManagementOfPatrolPersonnelDO> getManagementOfPatrolPersonnelPage(ManagementOfPatrolPersonnelPageReqVO pageReqVO);

}