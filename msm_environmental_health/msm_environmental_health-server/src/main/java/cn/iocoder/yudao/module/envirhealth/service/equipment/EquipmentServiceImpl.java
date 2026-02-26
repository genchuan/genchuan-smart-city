package cn.iocoder.yudao.module.envirhealth.service.equipment;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.equipment.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.equipment.EquipmentDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.equipment.EquipmentMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 设备 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    private EquipmentMapper equipmentMapper;

    @Override
    public Long createEquipment(EquipmentSaveReqVO createReqVO) {
        // 插入
        EquipmentDO equipment = BeanUtils.toBean(createReqVO, EquipmentDO.class);
        equipmentMapper.insert(equipment);
        // 返回
        return equipment.getId();
    }

    @Override
    public void updateEquipment(EquipmentSaveReqVO updateReqVO) {
        // 校验存在
        validateEquipmentExists(updateReqVO.getId());
        // 更新
        EquipmentDO updateObj = BeanUtils.toBean(updateReqVO, EquipmentDO.class);
        equipmentMapper.updateById(updateObj);
    }

    @Override
    public void deleteEquipment(Long id) {
        // 校验存在
        validateEquipmentExists(id);
        // 删除
        equipmentMapper.deleteById(id);
    }

    private void validateEquipmentExists(Long id) {
        if (equipmentMapper.selectById(id) == null) {
            throw exception(EQUIPMENT_NOT_EXISTS);
        }
    }

    @Override
    public EquipmentDO getEquipment(Long id) {
        return equipmentMapper.selectById(id);
    }

    @Override
    public PageResult<EquipmentDO> getEquipmentPage(EquipmentPageReqVO pageReqVO) {
        return equipmentMapper.selectPage(pageReqVO);
    }

}