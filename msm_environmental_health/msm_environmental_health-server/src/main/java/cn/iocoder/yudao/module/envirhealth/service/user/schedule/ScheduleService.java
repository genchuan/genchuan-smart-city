package cn.iocoder.yudao.module.envirhealth.service.user.schedule;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.SchedulePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.ScheduleSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

/**
 * 排班计划 Service 接口
 *
 * @author 芋道源码
 */
public interface ScheduleService {

    /**
     * 创建排班计划
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createSchedule(@Valid ScheduleSaveReqVO createReqVO);

    /**
     * 更新排班计划
     *
     * @param updateReqVO 更新信息
     */
    void updateSchedule(@Valid ScheduleSaveReqVO updateReqVO);

    /**
     * 删除排班计划
     *
     * @param id 编号
     */
    void deleteSchedule(Long id);

    /**
     * 获得排班计划
     *
     * @param id 编号
     * @return 排班计划
     */
    ScheduleDO getSchedule(Long id);

    /**
     * 获得排班计划分页
     *
     * @param pageReqVO 分页查询
     * @return 排班计划分页
     */
    PageResult<ScheduleDO> getSchedulePage(SchedulePageReqVO pageReqVO);

}