package cn.iocoder.yudao.module.waterdetection.service.watersourcemanagement;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.watersourcemanagement.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.watersourcemanagement.WaterSourceManagementDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.watersourcemanagement.WaterSourceManagementMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 水源类型及属性管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class WaterSourceManagementServiceImpl implements WaterSourceManagementService {

    @Resource
    private WaterSourceManagementMapper waterSourceManagementMapper;

    @Override
    public Long createWaterSourceManagement(WaterSourceManagementSaveReqVO createReqVO) {
        // 插入
        WaterSourceManagementDO waterSourceManagement = BeanUtils.toBean(createReqVO, WaterSourceManagementDO.class);
        waterSourceManagementMapper.insert(waterSourceManagement);
        // 返回
        return waterSourceManagement.getId();
    }

    @Override
    public void updateWaterSourceManagement(WaterSourceManagementSaveReqVO updateReqVO) {
        // 校验存在
        validateWaterSourceManagementExists(updateReqVO.getId());
        // 更新
        WaterSourceManagementDO updateObj = BeanUtils.toBean(updateReqVO, WaterSourceManagementDO.class);
        waterSourceManagementMapper.updateById(updateObj);
    }

    @Override
    public void deleteWaterSourceManagement(Long id) {
        // 校验存在
        validateWaterSourceManagementExists(id);
        // 删除
        waterSourceManagementMapper.deleteById(id);
    }

    private void validateWaterSourceManagementExists(Long id) {
        if (waterSourceManagementMapper.selectById(id) == null) {
            throw exception(WATER_SOURCE_MANAGEMENT_NOT_EXISTS);
        }
    }

    @Override
    public WaterSourceManagementDO getWaterSourceManagement(Long id) {
        return waterSourceManagementMapper.selectById(id);
    }

    @Override
    public PageResult<WaterSourceManagementDO> getWaterSourceManagementPage(WaterSourceManagementPageReqVO pageReqVO) {
        return waterSourceManagementMapper.selectPage(pageReqVO);
    }

}