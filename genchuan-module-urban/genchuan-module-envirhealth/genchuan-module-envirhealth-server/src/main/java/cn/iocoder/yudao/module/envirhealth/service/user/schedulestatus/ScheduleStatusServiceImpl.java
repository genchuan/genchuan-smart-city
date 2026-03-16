package cn.iocoder.yudao.module.envirhealth.service.user.schedulestatus;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.schedulestatus.ScheduleStatusSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.ScheduleStatusDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.ScheduleStatusMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.SCHEDULE_STATUS_NOT_EXISTS;

/**
 * 排班状态字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ScheduleStatusServiceImpl implements ScheduleStatusService {

    @Resource
    private ScheduleStatusMapper scheduleStatusMapper;

    @Override
    public Long createScheduleStatus(ScheduleStatusSaveReqVO createReqVO) {
        // 插入
        ScheduleStatusDO scheduleStatus = BeanUtils.toBean(createReqVO, ScheduleStatusDO.class);
        scheduleStatusMapper.insert(scheduleStatus);
        // 返回
        return scheduleStatus.getId();
    }

    @Override
    public void updateScheduleStatus(ScheduleStatusSaveReqVO updateReqVO) {
        // 校验存在
        validateScheduleStatusExists(updateReqVO.getId());
        // 更新
        ScheduleStatusDO updateObj = BeanUtils.toBean(updateReqVO, ScheduleStatusDO.class);
        scheduleStatusMapper.updateById(updateObj);
    }

    @Override
    public void deleteScheduleStatus(Long id) {
        // 校验存在
        validateScheduleStatusExists(id);
        // 删除
        scheduleStatusMapper.deleteById(id);
    }

    private void validateScheduleStatusExists(Long id) {
        if (scheduleStatusMapper.selectById(id) == null) {
            throw exception(SCHEDULE_STATUS_NOT_EXISTS);
        }
    }

    @Override
    public ScheduleStatusDO getScheduleStatus(Long id) {
        return scheduleStatusMapper.selectById(id);
    }

    @Override
    public PageResult<ScheduleStatusDO> getScheduleStatusPage(ScheduleStatusPageReqVO pageReqVO) {
        return scheduleStatusMapper.selectPage(pageReqVO);
    }

}