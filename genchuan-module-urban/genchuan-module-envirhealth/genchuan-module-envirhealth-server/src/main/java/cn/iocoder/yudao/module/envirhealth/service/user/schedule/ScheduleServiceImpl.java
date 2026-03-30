package cn.iocoder.yudao.module.envirhealth.service.user.schedule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.SchedulePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedule.ScheduleSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.ScheduleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.SCHEDULE_NOT_EXISTS;

/**
 * 排班计划 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ScheduleServiceImpl implements ScheduleService {

    @Resource
    private ScheduleMapper scheduleMapper;

    @Override
    public Long createSchedule(ScheduleSaveReqVO createReqVO) {
        // 插入
        ScheduleDO schedule = BeanUtils.toBean(createReqVO, ScheduleDO.class);
        scheduleMapper.insert(schedule);
        // 返回
        return schedule.getId();
    }

    @Override
    public void updateSchedule(ScheduleSaveReqVO updateReqVO) {
        // 校验存在
        validateScheduleExists(updateReqVO.getId());
        // 更新
        ScheduleDO updateObj = BeanUtils.toBean(updateReqVO, ScheduleDO.class);
        scheduleMapper.updateById(updateObj);
    }

    @Override
    public void deleteSchedule(Long id) {
        // 校验存在
        validateScheduleExists(id);
        // 删除
        scheduleMapper.deleteById(id);
    }

    private void validateScheduleExists(Long id) {
        if (scheduleMapper.selectById(id) == null) {
            throw exception(SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public ScheduleDO getSchedule(Long id) {
        return scheduleMapper.selectById(id);
    }

    @Override
    public PageResult<ScheduleDO> getSchedulePage(SchedulePageReqVO pageReqVO) {
        return scheduleMapper.selectPage(pageReqVO);
    }

}