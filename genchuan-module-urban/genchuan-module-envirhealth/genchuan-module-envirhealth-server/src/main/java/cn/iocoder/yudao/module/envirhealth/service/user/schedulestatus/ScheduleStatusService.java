package cn.iocoder.yudao.module.envirhealth.service.user.schedulestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleStatusDO;
import jakarta.validation.Valid;

/**
 * 排班状态字典表【通用复用】 Service 接口
 *
 * @author 芋道源码
 */
public interface ScheduleStatusService {

    /**
     * 创建排班状态字典表【通用复用】
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createScheduleStatus(@Valid ScheduleStatusSaveReqVO createReqVO);

    /**
     * 更新排班状态字典表【通用复用】
     *
     * @param updateReqVO 更新信息
     */
    void updateScheduleStatus(@Valid ScheduleStatusSaveReqVO updateReqVO);

    /**
     * 删除排班状态字典表【通用复用】
     *
     * @param id 编号
     */
    void deleteScheduleStatus(Long id);

    /**
     * 获得排班状态字典表【通用复用】
     *
     * @param id 编号
     * @return 排班状态字典表【通用复用】
     */
    ScheduleStatusDO getScheduleStatus(Long id);

    /**
     * 获得排班状态字典表【通用复用】分页
     *
     * @param pageReqVO 分页查询
     * @return 排班状态字典表【通用复用】分页
     */
    PageResult<ScheduleStatusDO> getScheduleStatusPage(ScheduleStatusPageReqVO pageReqVO);

}