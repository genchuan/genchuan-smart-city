package cn.iocoder.yudao.module.park.service.park.user.maintainschedule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.user.maintainschedule.vo.MaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.user.maintainschedule.MaintainScheduleDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.user.maintainschedule.MaintainScheduleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.MAINTAIN_SCHEDULE_NOT_EXISTS;

/**
 * 运维排班 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MaintainScheduleServiceImpl implements MaintainScheduleService {

    @Resource
    private MaintainScheduleMapper maintainScheduleMapper;

    @Override
    public Long createMaintainSchedule(MaintainScheduleSaveReqVO createReqVO) {
        // 插入
        MaintainScheduleDO maintainSchedule = BeanUtils.toBean(createReqVO, MaintainScheduleDO.class);
        maintainScheduleMapper.insert(maintainSchedule);
        // 返回
        return maintainSchedule.getId();
    }

    @Override
    public void updateMaintainSchedule(MaintainScheduleSaveReqVO updateReqVO) {
        // 校验存在
        validateMaintainScheduleExists(updateReqVO.getId());
        // 更新
        MaintainScheduleDO updateObj = BeanUtils.toBean(updateReqVO, MaintainScheduleDO.class);
        maintainScheduleMapper.updateById(updateObj);
    }

    @Override
    public void deleteMaintainSchedule(Long id) {
        // 校验存在
        validateMaintainScheduleExists(id);
        // 删除
        maintainScheduleMapper.deleteById(id);
    }

    private void validateMaintainScheduleExists(Long id) {
        if (maintainScheduleMapper.selectById(id) == null) {
            throw exception(MAINTAIN_SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public MaintainScheduleDO getMaintainSchedule(Long id) {
        return maintainScheduleMapper.selectById(id);
    }

    @Override
    public PageResult<MaintainScheduleDO> getMaintainSchedulePage(MaintainSchedulePageReqVO pageReqVO) {
        return maintainScheduleMapper.selectPage(pageReqVO);
    }

}
