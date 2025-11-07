package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdevicecat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdevicecat.vo.EmerDeviceCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdevicecat.EmerDeviceCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerdevicecat.EmerDeviceCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;


/**
 * 救援设备分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerDeviceCatServiceImpl implements EmerDeviceCatService {

    @Resource
    private EmerDeviceCatMapper emerDeviceCatMapper;

    @Override
    public Long createEmerDeviceCat(EmerDeviceCatSaveReqVO createReqVO) {
        // 插入
        EmerDeviceCatDO emerDeviceCat = BeanUtils.toBean(createReqVO, EmerDeviceCatDO.class);
        emerDeviceCatMapper.insert(emerDeviceCat);
        // 返回
        return emerDeviceCat.getId();
    }

    @Override
    public void updateEmerDeviceCat(EmerDeviceCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerDeviceCatExists(updateReqVO.getId());
        // 更新
        EmerDeviceCatDO updateObj = BeanUtils.toBean(updateReqVO, EmerDeviceCatDO.class);
        emerDeviceCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerDeviceCat(Long id) {
        // 校验存在
        validateEmerDeviceCatExists(id);
        // 删除
        emerDeviceCatMapper.deleteById(id);
    }

    private void validateEmerDeviceCatExists(Long id) {
        if (emerDeviceCatMapper.selectById(id) == null) {
            throw exception(EMER_DEVICE_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EmerDeviceCatDO getEmerDeviceCat(Long id) {
        return emerDeviceCatMapper.selectById(id);
    }

    @Override
    public PageResult<EmerDeviceCatDO> getEmerDeviceCatPage(EmerDeviceCatPageReqVO pageReqVO) {
        return emerDeviceCatMapper.selectPage(pageReqVO);
    }

}