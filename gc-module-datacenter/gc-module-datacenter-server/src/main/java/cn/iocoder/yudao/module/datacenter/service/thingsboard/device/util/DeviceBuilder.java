package cn.iocoder.yudao.module.datacenter.service.thingsboard.device.util;

import cn.iocoder.yudao.module.datacenter.controller.admin.thingsboard.device.vo.DeviceSaveReqVO;
import org.thingsboard.server.common.data.Device;
import org.thingsboard.server.common.data.id.DeviceId;
import org.thingsboard.server.common.data.id.DeviceProfileId;
import org.thingsboard.server.common.data.id.CustomerId;
import org.thingsboard.server.common.data.id.TenantId;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.UUID;

public class DeviceBuilder {

    public static Device buildDeviceFromReqVO(DeviceSaveReqVO reqVO) {
        Device device = new Device();

        // 设置设备名称 - 必填字段
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
        } else {
            throw new IllegalArgumentException("设备档案ID不能为空");
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

        // 设置租户ID
        if (reqVO.getTbTenantId() != null && !reqVO.getTbTenantId().isEmpty()) {
            try {
                UUID tenantUuid = UUID.fromString(reqVO.getTbTenantId());
                TenantId tenantIdObj = new TenantId(tenantUuid);
                device.setTenantId(tenantIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的租户ID格式: " + reqVO.getTbTenantId(), e);
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
                String additionalInfoJson = mapper.writeValueAsString(reqVO.getAdditionalInfo());
                JsonNode additionalInfo = mapper.readTree(additionalInfoJson);
                device.setAdditionalInfo(additionalInfo);
            } catch (Exception e) {
                throw new RuntimeException("设置附加信息失败", e);
            }
        }

        return device;
    }

    public static Device buildDeviceForUpdate(DeviceSaveReqVO reqVO, String deviceId) {
        Device device = buildDeviceFromReqVO(reqVO);

        if (deviceId != null && !deviceId.isEmpty()) {
            try {
                DeviceId deviceIdObj = new DeviceId(UUID.fromString(deviceId));
                device.setId(deviceIdObj);
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("无效的设备ID格式: " + deviceId, e);
            }
        }

        return device;
    }
}