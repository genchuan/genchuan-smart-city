package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;


import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.AlarmRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceAttributeRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DevicePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device.DeviceDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.device.DeviceMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.thingsboard.server.common.data.page.PageLink;
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

    private static final Logger log = LoggerFactory.getLogger(AssetServiceImpl.class);


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

    @Override
    public Map<String, Object> syncDevicesFromThingsBoard() {
        try {
            log.info("开始自动同步ThingsBoard设备数据");
            int pageSize = 50;
            int currentPage = 0;
            int totalSynced = 0;

            while (true) {
                PageLink pageLink = new PageLink(pageSize, currentPage);
                PageData<DeviceInfo> devicePageData = deviceTbDao.getAllDevices(pageLink);

                if (devicePageData == null || devicePageData.getData() == null || devicePageData.getData().isEmpty()) {
                    break;
                }

                // 批量处理当前页的设备
                for (DeviceInfo deviceInfo : devicePageData.getData()) {
                    try {
                        syncSingleDevice(deviceInfo);
                        totalSynced++;
                    } catch (Exception e) {
                        log.error("同步单个设备失败: {}", deviceInfo.getName(), e);
                        // 继续同步其他设备，不中断整个流程
                    }
                }

                log.info("已同步第{}页设备数据，共{}条", currentPage + 1, devicePageData.getData().size());

                if (devicePageData.getData().size() < pageSize) {
                    break;
                }
                currentPage++;
            }

            log.info("设备同步完成，共处理{}条数据", totalSynced);
            return Map.of("success", true, "totalSynced", totalSynced);

        } catch (Exception e) {
            log.error("自动同步设备数据失败", e);
            return Map.of("success", false, "error", e.getMessage());
        }
    }

    @Override
    public PageResult<DeviceInfo> getDevicePageWithDetails(Integer pageSize, Integer page) {
        PageLink pageLink = new PageLink(pageSize, page);
        PageData<DeviceInfo> devicePageData = deviceTbDao.getAllDevices(pageLink);

        if (devicePageData == null || devicePageData.getData() == null) {
            return new PageResult<>(Collections.emptyList(), 0L);
        }

        return new PageResult<>(devicePageData.getData(), devicePageData.getTotalElements());
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

    /**
     * 同步单个设备
     */
    private void syncSingleDevice(DeviceInfo deviceInfo) {
        String deviceId = deviceInfo.getId().getId().toString();
        DeviceDO existingDevice = deviceMapper.selectById(deviceId);

        DeviceDO deviceDO = buildDeviceDO(deviceInfo);

        if (existingDevice != null) {
            deviceDO.setId(existingDevice.getId());
            deviceDO.setCreateTime(existingDevice.getCreateTime());
            if (isDeviceChanged(existingDevice, deviceDO)) {
                deviceMapper.updateById(deviceDO);
                log.debug("更新设备: {}", deviceInfo.getName());
            }
        } else {
            deviceMapper.insert(deviceDO);
            log.debug("新增设备: {}", deviceInfo.getName());
        }
    }

    /**
     * 构建 DeviceDO 对象
     */
    private DeviceDO buildDeviceDO(DeviceInfo deviceInfo) {
        JsonNode additionalInfo = deviceInfo.getAdditionalInfo();
        String customerTitle = "";
        Boolean customerIsPublic = null;
        String description = "";

        if (additionalInfo != null) {
            if (additionalInfo.has("customerTitle")) {
                customerTitle = additionalInfo.get("customerTitle").asText();
            }
            if (additionalInfo.has("customerIsPublic")) {
                customerIsPublic = additionalInfo.get("customerIsPublic").asBoolean();
            }
            if (additionalInfo.has("description")) {
                description = additionalInfo.get("description").asText();
            }
        }

        return DeviceDO.builder()
                .id(deviceInfo.getId().getId().toString())
                .tbTenantId(deviceInfo.getTenantId() != null ? deviceInfo.getTenantId().getId().toString() : null)
                .customerId(deviceInfo.getCustomerId() != null ? deviceInfo.getCustomerId().getId().toString() : null)
                .name(deviceInfo.getName())
                .type(deviceInfo.getType())
                .label(deviceInfo.getLabel())
                .deviceProfileId(deviceInfo.getDeviceProfileId() != null ? deviceInfo.getDeviceProfileId().getId().toString() : null)
                .firmwareId(deviceInfo.getFirmwareId() != null ? deviceInfo.getFirmwareId().toString() : null)
                .softwareId(deviceInfo.getSoftwareId() != null ? deviceInfo.getSoftwareId().toString() : null)
                .externalId(deviceInfo.getExternalId() != null ? deviceInfo.getExternalId().toString() : null)
                .version(deviceInfo.getVersion())
                .active(deviceInfo.isActive())
                .deviceProfileName(deviceInfo.getDeviceProfileName())
                .customerTitle(customerTitle)
                .customerIsPublic(customerIsPublic)
                .additionalInfo(convertAdditionalInfoToJson(additionalInfo))
                .build();
    }

    /**
     * 判断设备数据是否发生变化
     */
    private boolean isDeviceChanged(DeviceDO existing, DeviceDO latest) {
        return !Objects.equals(existing.getName(), latest.getName()) ||
                !Objects.equals(existing.getType(), latest.getType()) ||
                !Objects.equals(existing.getLabel(), latest.getLabel()) ||
                !Objects.equals(existing.getVersion(), latest.getVersion()) ||
                !Objects.equals(existing.getActive(), latest.getActive()) ||
                !Objects.equals(existing.getAdditionalInfo(), latest.getAdditionalInfo());
    }

    /**
     * 转换附加信息为JSON字符串
     */
    private String convertAdditionalInfoToJson(JsonNode additionalInfo) {
        if (additionalInfo == null || additionalInfo.isNull()) {
            return null;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(additionalInfo);
        } catch (Exception e) {
            log.warn("转换附加信息失败", e);
            return null;
        }
    }

}