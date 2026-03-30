package cn.iocoder.yudao.module.waterdetection.service.equipmentasset;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.equipmentasset.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.equipmentasset.EquipmentAssetDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.equipmentasset.EquipmentAssetMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 设备资产台账管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class EquipmentAssetServiceImpl implements EquipmentAssetService {

    @Resource
    private EquipmentAssetMapper equipmentAssetMapper;

    @Override
    public Long createEquipmentAsset(EquipmentAssetSaveReqVO createReqVO) {
        // 插入
        EquipmentAssetDO equipmentAsset = BeanUtils.toBean(createReqVO, EquipmentAssetDO.class);
        equipmentAssetMapper.insert(equipmentAsset);
        // 返回
        return equipmentAsset.getId();
    }

    @Override
    public void updateEquipmentAsset(EquipmentAssetSaveReqVO updateReqVO) {
        // 校验存在
        validateEquipmentAssetExists(updateReqVO.getId());
        // 更新
        EquipmentAssetDO updateObj = BeanUtils.toBean(updateReqVO, EquipmentAssetDO.class);
        equipmentAssetMapper.updateById(updateObj);
    }

    @Override
    public void deleteEquipmentAsset(Long id) {
        // 校验存在
        validateEquipmentAssetExists(id);
        // 删除
        equipmentAssetMapper.deleteById(id);
    }

    private void validateEquipmentAssetExists(Long id) {
        if (equipmentAssetMapper.selectById(id) == null) {
            throw exception(EQUIPMENT_ASSET_NOT_EXISTS);
        }
    }

    @Override
    public EquipmentAssetDO getEquipmentAsset(Long id) {
        return equipmentAssetMapper.selectById(id);
    }

    @Override
    public PageResult<EquipmentAssetDO> getEquipmentAssetPage(EquipmentAssetPageReqVO pageReqVO) {
        return equipmentAssetMapper.selectPage(pageReqVO);
    }

}