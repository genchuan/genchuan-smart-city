package cn.iocoder.yudao.module.datacenter.service.thingsboard.device;


import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.thingsboard.device.DeviceDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.thingsboard.device.DeviceMapper;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.asset.AssetServiceImpl;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.Dao.DeviceTbDao;
import cn.iocoder.yudao.module.datacenter.service.thingsboard.device.util.DeviceBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
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
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.DeviceProfileId;
import org.thingsboard.server.common.data.id.TenantId;
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
        try {
            log.info("开始创建设备，请求参数: {}", createReqVO);

            // 1. 先同步到ThingsBoard
            Device deviceToCreate = DeviceBuilder.buildDeviceFromReqVO(createReqVO);
            log.info("构建的ThingsBoard设备对象: {}", deviceToCreate);

            Device createdDevice = deviceTbDao.createDevice(deviceToCreate);
            log.info("ThingsBoard创建成功，返回设备: {}", createdDevice);

            // 2. 再保存到本地数据库
            DeviceDO deviceDO = convertToDeviceDO(createReqVO, createdDevice);
            deviceMapper.insert(deviceDO);

            log.info("设备创建成功，本地ID: {}, ThingsBoard ID: {}",
                    deviceDO.getId(), deviceDO.getId());

            return deviceDO.getId();

        } catch (Exception e) {
            log.error("创建设备失败", e);
            throw new RuntimeException("创建设备失败: " + e.getMessage());
        }
    }

    @Override
    public void updateDevice(DeviceSaveReqVO updateReqVO) {
        try {
            log.info("开始更新设备，请求参数: {}", updateReqVO);

            // 1. 校验本地设备存在并获取设备信息
            DeviceDO existingDevice = validateDeviceExists(updateReqVO.getId());
            if (existingDevice.getId() == null || existingDevice.getId().isEmpty()) {
                throw new IllegalArgumentException("设备未同步到ThingsBoard，无法更新");
            }

            // 2. 构建 ThingsBoard 设备对象（包含完整的ID信息）
            Device deviceToUpdate = buildDeviceForUpdate(updateReqVO, existingDevice);
            log.info("构建的ThingsBoard更新设备对象: {}", deviceToUpdate);

            // 3. 先更新到 ThingsBoard
            Device updatedDevice = deviceTbDao.updateDevice(deviceToUpdate);
            log.info("ThingsBoard更新成功，返回设备: {}", updatedDevice);

            // 4. 再更新本地数据库
            DeviceDO updateObj = convertToDeviceDOForUpdate(updateReqVO, updatedDevice, existingDevice);
            deviceMapper.updateById(updateObj);

            log.info("设备更新成功，设备ID: {}", updateReqVO.getId());

        } catch (Exception e) {
            log.error("更新设备失败", e);
            throw new RuntimeException("更新设备失败: " + e.getMessage());
        }
    }

    @Override
    public void deleteDevice(String id) {
        try {
            // 1. 先校验存在并获取设备信息
            DeviceDO deviceDO = validateDeviceExists(id);

            // 2. 从ThingsBoard删除
            if (deviceDO.getId() != null) {
                deviceTbDao.deleteDevice(deviceDO.getId());
            }

            // 3. 从本地数据库删除
            deviceMapper.deleteById(id);

            log.info("设备删除成功，设备ID: {}", id);

        } catch (Exception e) {
            log.error("删除设备失败", e);
            throw new RuntimeException("删除设备失败: " + e.getMessage());
        }
    }

    @Override
    public void deleteDeviceListByIds(List<String> ids) {
        // 删除
        deviceMapper.deleteByIds(ids);
    }


    private DeviceDO validateDeviceExists(String id) {
        DeviceDO deviceDO = deviceMapper.selectById(id);
        if (deviceMapper.selectById(id) == null) {
            throw exception(DEVICE_NOT_EXISTS);
        }
        return deviceDO;
    }

    @Override
    public DeviceDO getDevice(String id) {
        return deviceMapper.selectById(id);
    }

    @Override
    public PageResult<DeviceDO> getDevicePage(DevicePageReqVO pageReqVO) {
        return deviceMapper.selectPage(pageReqVO);
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

    // 添加设备属性
    @Override
    public void addDeviceAttributes(String deviceId, Map<String, Object> attributes) {
        try {
            deviceTbDao.addDeviceAttributes(deviceId, attributes);
            updateLocalDeviceAttributes(deviceId);
            log.info("设备属性添加成功，设备ID: {}", deviceId);
        } catch (Exception e) {
            log.error("添加设备属性失败", e);
            throw new RuntimeException("添加设备属性失败: " + e.getMessage());
        }
    }

    // 删除设备属性
    @Override
    public void deleteDeviceAttributes(String deviceId, String scope, List<String> keys) {
        try {
            deviceTbDao.deleteDeviceAttributes(deviceId, scope, keys);
            updateLocalDeviceAttributes(deviceId);
            log.info("设备属性删除成功，设备ID: {}", deviceId);
        } catch (Exception e) {
            log.error("删除设备属性失败", e);
            throw new RuntimeException("删除设备属性失败: " + e.getMessage());
        }
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

        // 获取设备属性
        String attributesJson = getDeviceAttributesJson(deviceId);
        DeviceDO deviceDO = buildDeviceDO(deviceInfo, attributesJson);

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
    private DeviceDO buildDeviceDO(DeviceInfo deviceInfo, String attributesJson) {
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
                // 新增字段
                .attributes(attributesJson)
                .extCommon1(null)  // 可以从additionalInfo中提取或留空
                .extCommon2(null)
                .extCommon3(null)
                .extCommon4(null)
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
                !Objects.equals(existing.getAttributes(), latest.getAttributes()) ||
                !Objects.equals(existing.getExtCommon1(), latest.getExtCommon1()) ||
                !Objects.equals(existing.getExtCommon2(), latest.getExtCommon2()) ||
                !Objects.equals(existing.getExtCommon3(), latest.getExtCommon3()) ||
                !Objects.equals(existing.getExtCommon4(), latest.getExtCommon4());
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

    // 辅助方法：构建用于更新的设备对象
    private Device buildDeviceForUpdate(DeviceSaveReqVO reqVO, DeviceDO existingDevice) {
        Device device = new Device();

        // 设置设备ID（这是更新操作的关键）
        try {
            DeviceId deviceIdObj = new DeviceId(UUID.fromString(existingDevice.getId()));
            device.setId(deviceIdObj);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("无效的设备ID格式: " + existingDevice.getId(), e);
        }

        // 设置设备名称
        if (reqVO.getName() != null && !reqVO.getName().isEmpty()) {
            device.setName(reqVO.getName());
        } else {
            throw new IllegalArgumentException("设备名称不能为空");
        }

        // 设置设备配置ID
        if (reqVO.getDeviceProfileId() != null && !reqVO.getDeviceProfileId().isEmpty()) {
            try {
                DeviceProfileId deviceProfileIdObj = new DeviceProfileId(UUID.fromString(reqVO.getDeviceProfileId()));
                device.setDeviceProfileId(deviceProfileIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的设备档案ID格式: " + reqVO.getDeviceProfileId(), e);
            }
        }

        // 设置标签
        if (reqVO.getLabel() != null && !reqVO.getLabel().isEmpty()) {
            device.setLabel(reqVO.getLabel());
        }

        // 设置客户ID
        if (reqVO.getCustomerId() != null && !reqVO.getCustomerId().isEmpty()) {
            try {
                UUID customerUuid = UUID.fromString(reqVO.getCustomerId());
                CustomerId customerIdObj = new CustomerId(customerUuid);
                device.setCustomerId(customerIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的客户ID格式: " + reqVO.getCustomerId(), e);
            }
        }

        // 设置设备类型
        if (reqVO.getType() != null && !reqVO.getType().isEmpty()) {
            device.setType(reqVO.getType());
        }

        // 设置附加信息
        if (reqVO.getAdditionalInfo() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                JsonNode additionalInfo = mapper.valueToTree(reqVO.getAdditionalInfo());
                device.setAdditionalInfo(additionalInfo);
            } catch (Exception e) {
                throw new RuntimeException("设置附加信息失败", e);
            }
        }

        // 设置版本号（用于乐观锁）
        if (reqVO.getVersion() != null) {
            device.setVersion(reqVO.getVersion());
        } else {
            // 如果没有提供版本号，则使用现有版本号+1
            device.setVersion(existingDevice.getVersion() != null ?
                    existingDevice.getVersion() + 1 : 1L);
        }

        // 设置创建时间（从现有设备获取）
        device.setCreatedTime(existingDevice.getCreateTime() != null ?
                existingDevice.getCreateTime().atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli() :
                System.currentTimeMillis());

        return device;
    }

    // 辅助方法：将请求VO和创建的Device对象转换为本地数据库对象
    private DeviceDO convertToDeviceDO(DeviceSaveReqVO createReqVO, Device createdDevice) {
        DeviceDO deviceDO = new DeviceDO();

        // 设置从ThingsBoard返回的信息
        if (createdDevice != null && createdDevice.getId() != null) {
            deviceDO.setId(createdDevice.getId().getId().toString());
        }

        // 设置基本信息
        deviceDO.setName(createReqVO.getName());
        deviceDO.setLabel(createReqVO.getLabel());
        deviceDO.setType(createReqVO.getType());
        deviceDO.setDeviceProfileId(createReqVO.getDeviceProfileId());
        deviceDO.setCustomerId(createReqVO.getCustomerId());
        deviceDO.setTbTenantId(createReqVO.getTbTenantId());
        deviceDO.setExtCommon1(createReqVO.getExtCommon1());
        deviceDO.setExtCommon2(createReqVO.getExtCommon2());
        deviceDO.setExtCommon3(createReqVO.getExtCommon3());
        deviceDO.setExtCommon4(createReqVO.getExtCommon4());

        // 设置版本号
        if (createdDevice != null && createdDevice.getVersion() != null) {
            deviceDO.setVersion(createdDevice.getVersion());
        }

        // 设置附加信息
        if (createReqVO.getAdditionalInfo() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                deviceDO.setAdditionalInfo(mapper.writeValueAsString(createReqVO.getAdditionalInfo()));
            } catch (Exception e) {
                log.warn("转换附加信息失败", e);
            }
        }

        // 设置系统字段
        deviceDO.setCreateTime(java.time.LocalDateTime.now());

        return deviceDO;
    }

    //  获取设备属性JSON字符串
    private String getDeviceAttributesJson(String deviceId) {
        try {
            List<Map<String, Object>> attributes = deviceTbDao.getDeviceAttributes(deviceId);
            if (attributes != null && !attributes.isEmpty()) {
                ObjectMapper mapper = new ObjectMapper();
                return mapper.writeValueAsString(attributes);
            }
        } catch (Exception e) {
            log.warn("获取设备属性失败: {}", deviceId, e);
        }
        return null;
    }


    // 辅助方法：转换为本地数据库对象（更新专用）
    private DeviceDO convertToDeviceDOForUpdate(DeviceSaveReqVO reqVO, Device updatedDevice, DeviceDO existingDevice) {
        DeviceDO deviceDO = new DeviceDO();

        // 设置主键ID
        deviceDO.setId(reqVO.getId());

        // 保留原有的创建时间
        deviceDO.setCreateTime(existingDevice.getCreateTime());

        // 设置从 ThingsBoard 返回的更新信息
        if (updatedDevice != null) {
            deviceDO.setId(updatedDevice.getId().getId().toString());
            deviceDO.setVersion(updatedDevice.getVersion());
        }

        // 设置基本字段（从请求VO）
        deviceDO.setName(reqVO.getName());
        deviceDO.setLabel(reqVO.getLabel());
        deviceDO.setType(reqVO.getType());
        deviceDO.setDeviceProfileId(reqVO.getDeviceProfileId());
        deviceDO.setCustomerId(reqVO.getCustomerId());
        deviceDO.setTbTenantId(reqVO.getTbTenantId());

        // 设置附加信息
        if (reqVO.getAdditionalInfo() != null) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                String additionalInfoJson = mapper.writeValueAsString(reqVO.getAdditionalInfo());
                deviceDO.setAdditionalInfo(additionalInfoJson);
            } catch (Exception e) {
                log.warn("转换附加信息失败", e);
                // 如果转换失败，保留原有的附加信息
                deviceDO.setAdditionalInfo(existingDevice.getAdditionalInfo());
            }
        } else {
            // 如果请求中没有附加信息，保留原有的
            deviceDO.setAdditionalInfo(existingDevice.getAdditionalInfo());
        }

        return deviceDO;
    }

    // 更新本地设备属性
    private void updateLocalDeviceAttributes(String deviceId) {
        try {
            DeviceDO deviceDO = deviceMapper.selectById(deviceId);
            if (deviceDO == null) return;

            String attributesJson = getDeviceAttributesJson(deviceId);
            DeviceDO updateObj = new DeviceDO();
            updateObj.setId(deviceDO.getId());
            updateObj.setAttributes(attributesJson);

            deviceMapper.updateById(updateObj);
            log.debug("本地设备属性更新成功，设备ID: {}", deviceId);
        } catch (Exception e) {
            log.error("更新本地设备属性失败", e);
            // 这里不抛出异常，因为ThingsBoard操作已经成功
        }
    }
}
