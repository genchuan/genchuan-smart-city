package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;


import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceAttributeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device.DeviceDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.device.DeviceMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.DeviceInfo;
import org.thingsboard.server.common.data.alarm.AlarmInfo;
import org.thingsboard.server.common.data.kv.AttributeKvEntry;
import org.thingsboard.server.common.data.page.PageData;
import org.thingsboard.server.common.data.page.TimePageLink;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.DEVICE_NOT_EXISTS;


/**
 * 设备 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DeviceServiceImpl implements DeviceService {

    @Resource
    private DeviceMapper deviceMapper;
    @Resource
    private DeviceTbDao deviceTbDao;

    @Override
    public String createDevice(DeviceSaveReqVO createReqVO) {
        // 插入
        DeviceDO device = BeanUtils.toBean(createReqVO, DeviceDO.class);
        deviceMapper.insert(device);

        // 返回
        return device.getId();
    }

    @Override
    public void updateDevice(DeviceSaveReqVO updateReqVO) {
        // 校验存在
        validateDeviceExists(updateReqVO.getId());
        // 更新
        DeviceDO updateObj = BeanUtils.toBean(updateReqVO, DeviceDO.class);
        deviceMapper.updateById(updateObj);
    }

    @Override
    public void deleteDevice(String id) {
        // 校验存在
        validateDeviceExists(id);
        // 删除
        deviceMapper.deleteById(id);
    }

    @Override
    public void deleteDeviceListByIds(List<String> ids) {
        // 删除
        deviceMapper.deleteByIds(ids);
    }


    private void validateDeviceExists(String id) {
        if (deviceMapper.selectById(id) == null) {
            throw exception(DEVICE_NOT_EXISTS);
        }
    }

    @Override
    public DeviceInfo getDevice(String id) {
        return deviceTbDao.getDeviceInfoById(id);
    }

    @Override
    public PageResult<Device> getDevicePage(DevicePageReqVO pageReqVO) {
        return deviceTbDao.getDevicePage(pageReqVO);
    }

    @Override
    public List<AttributeKvEntry> getAttributeKvEntries(String id) {
        return deviceTbDao.getAttributeKvEntries(id);
    }

    @Override
    public PageResult<AlarmRespVO> getAlarmPage(Integer pageSize, Integer page) {
        TimePageLink pageLink = new TimePageLink(pageSize, page);
        PageData<AlarmInfo> alarmPageData = deviceTbDao.getAlarms(pageLink);

        if (alarmPageData == null) {
            return new PageResult<>(new ArrayList<>(), 0L);
        }

        // 转换告警信息，并为每个告警添加设备属性
        List<AlarmRespVO> alarmRespVOList = alarmPageData.getData().stream()
                .map(this::convertAlarmInfoToRespVO)
                .collect(Collectors.toList());

        return new PageResult<>(alarmRespVOList, alarmPageData.getTotalElements());
    }

    private AlarmRespVO convertAlarmInfoToRespVO(AlarmInfo alarmInfo) {
        AlarmRespVO respVO = new AlarmRespVO();

        // 使用完整嵌套对象版本
        respVO.setId(alarmInfo.getId());
        respVO.setCreatedTime(alarmInfo.getCreatedTime());
        respVO.setTenantId(alarmInfo.getTenantId());
        respVO.setCustomerId(alarmInfo.getCustomerId());
        respVO.setType(alarmInfo.getType());
        respVO.setOriginator(alarmInfo.getOriginator());
        respVO.setSeverity(alarmInfo.getSeverity());
        respVO.setAcknowledged(alarmInfo.isAcknowledged());
        respVO.setCleared(alarmInfo.isCleared());
        respVO.setAssigneeId(alarmInfo.getAssigneeId());
        respVO.setStartTs(alarmInfo.getStartTs());
        respVO.setEndTs(alarmInfo.getEndTs());
        respVO.setAckTs(alarmInfo.getAckTs());
        respVO.setClearTs(alarmInfo.getClearTs());
        respVO.setAssignTs(alarmInfo.getAssignTs());
        respVO.setPropagate(alarmInfo.isPropagate());
        respVO.setPropagateToOwner(alarmInfo.isPropagateToOwner());
        respVO.setPropagateToTenant(alarmInfo.isPropagateToTenant());
        respVO.setPropagateRelationTypes(alarmInfo.getPropagateRelationTypes());
        respVO.setOriginatorName(alarmInfo.getOriginatorName());
        respVO.setOriginatorLabel(alarmInfo.getOriginatorLabel());
        respVO.setAssignee(alarmInfo.getAssignee());
        respVO.setName(alarmInfo.getName());
        respVO.setStatus(alarmInfo.getStatus());
        respVO.setDetails(alarmInfo.getDetails());

        // 新增：获取告警对应设备的属性
        if (alarmInfo.getOriginator() != null) {
            String deviceId = alarmInfo.getOriginator().getId().toString();
            List<AttributeKvEntry> attributeEntries = deviceTbDao.getAttributeKvEntries(deviceId);

            // 将 AttributeKvEntry 转换为 DeviceAttributeRespVO
            if (attributeEntries != null && !attributeEntries.isEmpty()) {
                List<DeviceAttributeRespVO> deviceAttributes = attributeEntries.stream()
                        .map(this::convertAttributeKvEntryToRespVO)
                        .collect(Collectors.toList());
                respVO.setDeviceAttributes(deviceAttributes);
            }
        }


        return respVO;
    }

    private DeviceAttributeRespVO convertAttributeKvEntryToRespVO(AttributeKvEntry entry) {
        DeviceAttributeRespVO attrVO = new DeviceAttributeRespVO();
        attrVO.setKey(entry.getKey());
        attrVO.setDataType(entry.getDataType());
        attrVO.setLastUpdateTs(entry.getLastUpdateTs());

        // 设置通用值字段
        attrVO.setValue(entry.getValue());

        // 设置字符串形式的值（用于显示）
        attrVO.setValueAsString(entry.getValueAsString());

        return attrVO;
    }
}