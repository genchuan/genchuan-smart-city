package cn.iocoder.yudao.module.datacenter.service.inspection.resource.inspectdeviceinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.resource.inspectdeviceinfo.vo.InspectDeviceInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.resource.inspectdeviceinfo.InspectDeviceInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.resource.inspectdeviceinfo.InspectDeviceInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检设备信息 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectDeviceInfoServiceImpl implements InspectDeviceInfoService {

    @Resource
    private InspectDeviceInfoMapper inspectDeviceInfoMapper;

    @Override
    public Long createInspectDeviceInfo(InspectDeviceInfoSaveReqVO createReqVO) {
        // 插入
        InspectDeviceInfoDO inspectDeviceInfo = BeanUtils.toBean(createReqVO, InspectDeviceInfoDO.class);
        inspectDeviceInfoMapper.insert(inspectDeviceInfo);
        // 返回
        return inspectDeviceInfo.getId();
    }

    @Override
    public void updateInspectDeviceInfo(InspectDeviceInfoSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectDeviceInfoExists(updateReqVO.getId());
        // 更新
        InspectDeviceInfoDO updateObj = BeanUtils.toBean(updateReqVO, InspectDeviceInfoDO.class);
        inspectDeviceInfoMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectDeviceInfo(Long id) {
        // 校验存在
        validateInspectDeviceInfoExists(id);
        // 删除
        inspectDeviceInfoMapper.deleteById(id);
    }

    private void validateInspectDeviceInfoExists(Long id) {
        if (inspectDeviceInfoMapper.selectById(id) == null) {
            throw exception(INSPECT_DEVICE_INFO_NOT_EXISTS);
        }
    }

    @Override
    public InspectDeviceInfoDO getInspectDeviceInfo(Long id) {
        return inspectDeviceInfoMapper.selectById(id);
    }

    @Override
    public PageResult<InspectDeviceInfoDO> getInspectDeviceInfoPage(InspectDeviceInfoPageReqVO pageReqVO) {
        return inspectDeviceInfoMapper.selectPage(pageReqVO);
    }

}