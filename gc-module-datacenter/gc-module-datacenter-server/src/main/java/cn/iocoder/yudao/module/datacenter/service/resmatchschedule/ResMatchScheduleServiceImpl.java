package cn.iocoder.yudao.module.datacenter.service.resmatchschedule;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resmatchschedule.ResMatchScheduleDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resmatchschedule.ResMatchScheduleMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 资源匹配调度 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ResMatchScheduleServiceImpl implements ResMatchScheduleService {

    @Resource
    private ResMatchScheduleMapper resMatchScheduleMapper;

    @Override
    public Long createResMatchSchedule(ResMatchScheduleSaveReqVO createReqVO) {
        // 插入
        ResMatchScheduleDO resMatchSchedule = BeanUtils.toBean(createReqVO, ResMatchScheduleDO.class);
        resMatchScheduleMapper.insert(resMatchSchedule);
        // 返回
        return resMatchSchedule.getId();
    }

    @Override
    public void updateResMatchSchedule(ResMatchScheduleSaveReqVO updateReqVO) {
        // 校验存在
        validateResMatchScheduleExists(updateReqVO.getId());
        // 更新
        ResMatchScheduleDO updateObj = BeanUtils.toBean(updateReqVO, ResMatchScheduleDO.class);
        resMatchScheduleMapper.updateById(updateObj);
    }

    @Override
    public void deleteResMatchSchedule(Long id) {
        // 校验存在
        validateResMatchScheduleExists(id);
        // 删除
        resMatchScheduleMapper.deleteById(id);
    }

    private void validateResMatchScheduleExists(Long id) {
        if (resMatchScheduleMapper.selectById(id) == null) {
            throw exception(RES_MATCH_SCHEDULE_NOT_EXISTS);
        }
    }

    @Override
    public ResMatchScheduleDO getResMatchSchedule(Long id) {
        return resMatchScheduleMapper.selectById(id);
    }

    @Override
    public PageResult<ResMatchScheduleDO> getResMatchSchedulePage(ResMatchSchedulePageReqVO pageReqVO) {
        return resMatchScheduleMapper.selectPage(pageReqVO);
    }

}