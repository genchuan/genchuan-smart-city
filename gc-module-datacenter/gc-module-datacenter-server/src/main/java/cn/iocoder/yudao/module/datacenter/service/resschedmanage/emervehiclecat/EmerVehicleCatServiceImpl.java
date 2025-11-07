package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emervehiclecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emervehiclecat.vo.EmerVehicleCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emervehiclecat.EmerVehicleCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emervehiclecat.EmerVehicleCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 救援车辆分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerVehicleCatServiceImpl implements EmerVehicleCatService {

    @Resource
    private EmerVehicleCatMapper emerVehicleCatMapper;

    @Override
    public Long createEmerVehicleCat(EmerVehicleCatSaveReqVO createReqVO) {
        // 插入
        EmerVehicleCatDO emerVehicleCat = BeanUtils.toBean(createReqVO, EmerVehicleCatDO.class);
        emerVehicleCatMapper.insert(emerVehicleCat);
        // 返回
        return emerVehicleCat.getId();
    }

    @Override
    public void updateEmerVehicleCat(EmerVehicleCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerVehicleCatExists(updateReqVO.getId());
        // 更新
        EmerVehicleCatDO updateObj = BeanUtils.toBean(updateReqVO, EmerVehicleCatDO.class);
        emerVehicleCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerVehicleCat(Long id) {
        // 校验存在
        validateEmerVehicleCatExists(id);
        // 删除
        emerVehicleCatMapper.deleteById(id);
    }

    private void validateEmerVehicleCatExists(Long id) {
        if (emerVehicleCatMapper.selectById(id) == null) {
            throw exception(EMER_VEHICLE_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EmerVehicleCatDO getEmerVehicleCat(Long id) {
        return emerVehicleCatMapper.selectById(id);
    }

    @Override
    public PageResult<EmerVehicleCatDO> getEmerVehicleCatPage(EmerVehicleCatPageReqVO pageReqVO) {
        return emerVehicleCatMapper.selectPage(pageReqVO);
    }

}