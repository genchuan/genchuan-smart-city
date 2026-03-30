package cn.iocoder.yudao.module.park.service.park.user.maintainschedule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainschedule.MaintainScheduleDO;
import jakarta.validation.Valid;

/**
 * 运维排班 Service 接口
 *
 * @author 亘川智城
 */
public interface MaintainScheduleService {

    /**
     * 创建运维排班
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createMaintainSchedule(@Valid MaintainScheduleSaveReqVO createReqVO);

    /**
     * 更新运维排班
     *
     * @param updateReqVO 更新信息
     */
    void updateMaintainSchedule(@Valid MaintainScheduleSaveReqVO updateReqVO);

    /**
     * 删除运维排班
     *
     * @param id 编号
     */
    void deleteMaintainSchedule(Long id);

    /**
     * 获得运维排班
     *
     * @param id 编号
     * @return 运维排班
     */
    MaintainScheduleDO getMaintainSchedule(Long id);

    /**
     * 获得运维排班分页
     *
     * @param pageReqVO 分页查询
     * @return 运维排班分页
     */
    PageResult<MaintainScheduleDO> getMaintainSchedulePage(MaintainSchedulePageReqVO pageReqVO);

}
