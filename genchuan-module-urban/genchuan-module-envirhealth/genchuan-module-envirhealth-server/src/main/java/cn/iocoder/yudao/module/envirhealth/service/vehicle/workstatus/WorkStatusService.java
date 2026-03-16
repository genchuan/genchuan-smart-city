package cn.iocoder.yudao.module.envirhealth.service.vehicle.workstatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.vehicle.vo.workstatus.WorkStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.vehicle.WorkStatusDO;
import jakarta.validation.Valid;

/**
 * 作业状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface WorkStatusService {

    /**
     * 创建作业状态字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createWorkStatus(@Valid WorkStatusSaveReqVO createReqVO);

    /**
     * 更新作业状态字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateWorkStatus(@Valid WorkStatusSaveReqVO updateReqVO);

    /**
     * 删除作业状态字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteWorkStatus(Long id);

    /**
     * 获得作业状态字典表【通用复用】
     *
     * @param id 编号
     * @return 作业状态字典表【通用复用】
     */
    WorkStatusDO getWorkStatus(Long id);

    /**
     * 获得作业状态字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 作业状态字典表【通用复用】分页
     */
    PageResult<WorkStatusDO> getWorkStatusPage(WorkStatusPageReqVO pageReqVO);

}