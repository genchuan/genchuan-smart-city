package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.alarmtype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype.AlarmTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.alarmtype.AlarmTypeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.AlarmTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.AlarmTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 预警类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class AlarmTypeServiceImpl implements AlarmTypeService {

    @Resource
    private AlarmTypeMapper alarmTypeMapper;

    @Override
    public Long createAlarmType(AlarmTypeSaveReqVO createReqVO) {
        // 插入
        AlarmTypeDO alarmType = BeanUtils.toBean(createReqVO, AlarmTypeDO.class);
        alarmTypeMapper.insert(alarmType);
        // 返回
        return alarmType.getId();
    }

    @Override
    public void updateAlarmType(AlarmTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateAlarmTypeExists(updateReqVO.getId());
        // 更新
        AlarmTypeDO updateObj = BeanUtils.toBean(updateReqVO, AlarmTypeDO.class);
        alarmTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlarmType(Long id) {
        // 校验存在
        validateAlarmTypeExists(id);
        // 删除
        alarmTypeMapper.deleteById(id);
    }

    private void validateAlarmTypeExists(Long id) {
        if (alarmTypeMapper.selectById(id) == null) {
            throw exception(ALARM_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public AlarmTypeDO getAlarmType(Long id) {
        return alarmTypeMapper.selectById(id);
    }

    @Override
    public PageResult<AlarmTypeDO> getAlarmTypePage(AlarmTypePageReqVO pageReqVO) {
        return alarmTypeMapper.selectPage(pageReqVO);
    }

}