package cn.iocoder.yudao.module.system.service.alarmhandlingcategory;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.system.controller.admin.alarmhandlingcategory.vo.*;
import cn.iocoder.yudao.module.system.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.system.dal.mysql.alarmhandlingcategory.AlarmHandlingCategoryMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.system.enums.ErrorCodeConstants.*;

/**
 * 警报处理类别 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class AlarmHandlingCategoryServiceImpl implements AlarmHandlingCategoryService {

    @Resource
    private AlarmHandlingCategoryMapper alarmHandlingCategoryMapper;

    @Override
    public Long createAlarmHandlingCategory(AlarmHandlingCategorySaveReqVO createReqVO) {
        // 插入
        AlarmHandlingCategoryDO alarmHandlingCategory = BeanUtils.toBean(createReqVO, AlarmHandlingCategoryDO.class);
        alarmHandlingCategoryMapper.insert(alarmHandlingCategory);
        // 返回
        return alarmHandlingCategory.getId();
    }

    @Override
    public void updateAlarmHandlingCategory(AlarmHandlingCategorySaveReqVO updateReqVO) {
        // 校验存在
        validateAlarmHandlingCategoryExists(updateReqVO.getId());
        // 更新
        AlarmHandlingCategoryDO updateObj = BeanUtils.toBean(updateReqVO, AlarmHandlingCategoryDO.class);
        alarmHandlingCategoryMapper.updateById(updateObj);
    }

    @Override
    public void deleteAlarmHandlingCategory(Long id) {
        // 校验存在
        validateAlarmHandlingCategoryExists(id);
        // 删除
        alarmHandlingCategoryMapper.deleteById(id);
    }

    private void validateAlarmHandlingCategoryExists(Long id) {
        if (alarmHandlingCategoryMapper.selectById(id) == null) {
            throw exception(ALARM_HANDLING_CATEGORY_NOT_EXISTS);
        }
    }

    @Override
    public AlarmHandlingCategoryDO getAlarmHandlingCategory(Long id) {
        return alarmHandlingCategoryMapper.selectById(id);
    }

    @Override
    public PageResult<AlarmHandlingCategoryDO> getAlarmHandlingCategoryPage(AlarmHandlingCategoryPageReqVO pageReqVO) {
        return alarmHandlingCategoryMapper.selectPage(pageReqVO);
    }

}