package cn.iocoder.yudao.module.industry.service.park.user.parkmaintainschedule;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainSchedulePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmaintainschedule.vo.ParkMaintainScheduleSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmaintainschedule.ParkMaintainScheduleDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmaintainschedule.ParkMaintainScheduleMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 运维排班 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkMaintainScheduleServiceImpl implements ParkMaintainScheduleService {

    @Resource
    private ParkMaintainScheduleMapper parkMaintainScheduleMapper;

    @Override
    public Long createParkMaintainSchedule(ParkMaintainScheduleSaveReqVO createReqVO) {
        // 插入
        ParkMaintainScheduleDO parkMaintainSchedule = BeanUtils.toBean(createReqVO, ParkMaintainScheduleDO.class);
        parkMaintainScheduleMapper.insert(parkMaintainSchedule);
        // 返回
        return parkMaintainSchedule.getId();
    }

    @Override
    public void updateParkMaintainSchedule(ParkMaintainScheduleSaveReqVO updateReqVO) {
        // 校验存在
        validateParkMaintainScheduleExists(updateReqVO.getId());
        // 更新
        ParkMaintainScheduleDO updateObj = BeanUtils.toBean(updateReqVO, ParkMaintainScheduleDO.class);
        parkMaintainScheduleMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkMaintainSchedule(Long id) {
        // 校验存在
        validateParkMaintainScheduleExists(id);
        // 删除
        parkMaintainScheduleMapper.deleteById(id);
    }

    private void validateParkMaintainScheduleExists(Long id) {
        if (parkMaintainScheduleMapper.selectById(id) == null) {
            throw exception(PARK_MAINTAIN_SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public ParkMaintainScheduleDO getParkMaintainSchedule(Long id) {
        return parkMaintainScheduleMapper.selectById(id);
    }

    @Override
    public PageResult<ParkMaintainScheduleDO> getParkMaintainSchedulePage(ParkMaintainSchedulePageReqVO pageReqVO) {
        return parkMaintainScheduleMapper.selectPage(pageReqVO);
    }

}
