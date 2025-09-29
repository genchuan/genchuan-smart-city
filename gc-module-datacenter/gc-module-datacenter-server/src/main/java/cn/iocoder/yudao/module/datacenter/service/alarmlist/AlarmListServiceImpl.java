package cn.iocoder.yudao.module.datacenter.service.alarmlist;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarmlist.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarmlist.AlarmListDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.alarmlist.AlarmListMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 预警告警列 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AlarmListServiceImpl implements AlarmListService {

    @Resource
    private AlarmListMapper alarmListMapper;

    @Override
    public Long createAlarmList(AlarmListSaveReqVO createReqVO) {
        // 插入
        AlarmListDO alarmList = BeanUtils.toBean(createReqVO, AlarmListDO.class);
        alarmListMapper.insert(alarmList);
        // 返回
        return alarmList.getId();
    }

    @Override
    public void updateAlarmList(AlarmListSaveReqVO updateReqVO) {
        // 校验存在
        validateAlarmListExists(updateReqVO.getId());
        // 更新
        AlarmListDO updateObj = BeanUtils.toBean(updateReqVO, AlarmListDO.class);
        alarmListMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlarmList(Long id) {
        // 校验存在
        validateAlarmListExists(id);
        // 删除
        alarmListMapper.deleteById(id);
    }

    private void validateAlarmListExists(Long id) {
        if (alarmListMapper.selectById(id) == null) {
            throw exception(ALARM_LIST_NOT_EXISTS);
        }
    }

    @Override
    public AlarmListDO getAlarmList(Long id) {
        return alarmListMapper.selectById(id);
    }

    @Override
    public PageResult<AlarmListDO> getAlarmListPage(AlarmListPageReqVO pageReqVO) {
        return alarmListMapper.selectPage(pageReqVO);
    }

}