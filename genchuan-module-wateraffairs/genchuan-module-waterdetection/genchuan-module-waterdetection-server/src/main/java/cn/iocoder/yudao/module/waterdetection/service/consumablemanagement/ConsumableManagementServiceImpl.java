package cn.iocoder.yudao.module.waterdetection.service.consumablemanagement;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.consumablemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.consumablemanagement.ConsumableManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.consumablemanagement.ConsumableManagementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 耗材库存与更换管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class ConsumableManagementServiceImpl implements ConsumableManagementService {

    @Resource
    private ConsumableManagementMapper consumableManagementMapper;

    @Override
    public Long createConsumableManagement(ConsumableManagementSaveReqVO createReqVO) {
        // 插入
        ConsumableManagementDO consumableManagement = BeanUtils.toBean(createReqVO, ConsumableManagementDO.class);
        consumableManagementMapper.insert(consumableManagement);
        // 返回
        return consumableManagement.getId();
    }

    @Override
    public void updateConsumableManagement(ConsumableManagementSaveReqVO updateReqVO) {
        // 校验存在
        validateConsumableManagementExists(updateReqVO.getId());
        // 更新
        ConsumableManagementDO updateObj = BeanUtils.toBean(updateReqVO, ConsumableManagementDO.class);
        consumableManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteConsumableManagement(Long id) {
        // 校验存在
        validateConsumableManagementExists(id);
        // 删除
        consumableManagementMapper.deleteById(id);
    }

    private void validateConsumableManagementExists(Long id) {
        if (consumableManagementMapper.selectById(id) == null) {
            throw exception(CONSUMABLE_MANAGEMENT_NOT_EXISTS);
        }
    }

    @Override
    public ConsumableManagementDO getConsumableManagement(Long id) {
        return consumableManagementMapper.selectById(id);
    }

    @Override
    public PageResult<ConsumableManagementDO> getConsumableManagementPage(ConsumableManagementPageReqVO pageReqVO) {
        return consumableManagementMapper.selectPage(pageReqVO);
    }

}