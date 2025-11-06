package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emerdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emerdeviceinfo.vo.EmerDeviceInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emerdeviceinfo.EmerDeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emerdeviceinfo.EmerDeviceInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 救援设备信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerDeviceInfoServiceImpl implements EmerDeviceInfoService {

    @Resource
    private EmerDeviceInfoMapper emerDeviceInfoMapper;

    @Override
    public Long createEmerDeviceInfo(EmerDeviceInfoSaveReqVO createReqVO) {
        // 插入
        EmerDeviceInfoDO emerDeviceInfo = BeanUtils.toBean(createReqVO, EmerDeviceInfoDO.class);
        emerDeviceInfoMapper.insert(emerDeviceInfo);
        // 返回
        return emerDeviceInfo.getId();
    }

    @Override
    public void updateEmerDeviceInfo(EmerDeviceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerDeviceInfoExists(updateReqVO.getId());
        // 更新
        EmerDeviceInfoDO updateObj = BeanUtils.toBean(updateReqVO, EmerDeviceInfoDO.class);
        emerDeviceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerDeviceInfo(Long id) {
        // 校验存在
        validateEmerDeviceInfoExists(id);
        // 删除
        emerDeviceInfoMapper.deleteById(id);
    }

    private void validateEmerDeviceInfoExists(Long id) {
        if (emerDeviceInfoMapper.selectById(id) == null) {
            throw exception(EMER_DEVICE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public EmerDeviceInfoDO getEmerDeviceInfo(Long id) {
        return emerDeviceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<EmerDeviceInfoDO> getEmerDeviceInfoPage(EmerDeviceInfoPageReqVO pageReqVO) {
        return emerDeviceInfoMapper.selectPage(pageReqVO);
    }

}