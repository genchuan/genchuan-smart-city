package cn.iocoder.yudao.module.gc.service.alarminformation;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.gc.controller.admin.alarminformation.vo.*;
import cn.iocoder.yudao.module.gc.dal.dataobject.alarminformation.AlarmInformationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.gc.dal.mysql.alarminformation.AlarmInformationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AlarmInformationServiceImpl implements AlarmInformationService {

    @Resource
    private AlarmInformationMapper alarmInformationMapper;

    @Override
    public String createAlarmInformation(AlarmInformationSaveReqVO createReqVO) {
        // 插入
        AlarmInformationDO alarmInformation = BeanUtils.toBean(createReqVO, AlarmInformationDO.class);
        alarmInformationMapper.insert(alarmInformation);
        // 返回
        return alarmInformation.getAlarmId();
    }

    @Override
    public void updateAlarmInformation(AlarmInformationSaveReqVO updateReqVO) {
        // 校验存在
        validateAlarmInformationExists(updateReqVO.getAlarmId());
        // 更新
        AlarmInformationDO updateObj = BeanUtils.toBean(updateReqVO, AlarmInformationDO.class);
        alarmInformationMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlarmInformation(String id) {
        // 校验存在
        validateAlarmInformationExists(id);
        // 删除
        alarmInformationMapper.deleteById(id);
    }

    private void validateAlarmInformationExists(String id) {
        if (alarmInformationMapper.selectById(id) == null) {
            throw exception(ALARM_INFORMATION_NOT_EXISTS);
        }
    }

    @Override
    public AlarmInformationDO getAlarmInformation(String id) {
        return alarmInformationMapper.selectById(id);
    }

    @Override
    public PageResult<AlarmInformationDO> getAlarmInformationPage(AlarmInformationPageReqVO pageReqVO) {
        return alarmInformationMapper.selectPage(pageReqVO);
    }

}