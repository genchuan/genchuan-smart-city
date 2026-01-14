package cn.iocoder.yudao.module.industry.service.park.thingsboard;

import cn.iocoder.yudao.module.industry.controller.admin.park.thingsboard.ParkingTelemetryDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 遥测数据转换器
 *
 * @author zhucongquan
 */
@Slf4j
@Component
public class TelemetryDataConverter {

    /**
     * 将Thingsboard的遥测数据Map转换为ParkingTelemetryDTO
     *
     * @param telemetryData Thingsboard返回的遥测数据
     * @return 转换后的DTO对象
     */
    public ParkingTelemetryDTO convertToDto(Map<String, Object> telemetryData) {
        ParkingTelemetryDTO dto = new ParkingTelemetryDTO();

        try {
            // 使用反射或直接设置字段，这里采用直接设置的方式
            dto.setTimestamp(getStringValue(telemetryData, "timestamp"));
            dto.setEntranceNo(getStringValue(telemetryData, "entranceNo"));
            dto.setPlateType(getStringValue(telemetryData, "plateType"));
            dto.setDataType(getStringValue(telemetryData, "dataType"));
            dto.setOperaterName(getStringValue(telemetryData, "operaterName"));
            dto.setSign(getStringValue(telemetryData, "sign"));
            dto.setPlateNumber(getStringValue(telemetryData, "plateNumber"));
            dto.setVersion(getStringValue(telemetryData, "version"));
            dto.setRecordId(getStringValue(telemetryData, "recordId"));
            dto.setEmptyPlot(getStringValue(telemetryData, "emptyPlot"));
            dto.setService(getStringValue(telemetryData, "service"));
            dto.setOperaterId(getStringValue(telemetryData, "operaterId"));
            dto.setEntranceName(getStringValue(telemetryData, "entranceName"));
            dto.setDriveInTime(getStringValue(telemetryData, "driveInTime"));
            dto.setShouldPay(getStringValue(telemetryData, "shouldPay"));
            dto.setDriveOutTime(getStringValue(telemetryData, "driveOutTime"));
            dto.setOutType(getStringValue(telemetryData, "outType"));
            dto.setDriveInPhoto(getStringValue(telemetryData, "driveInPhoto"));
            dto.setOutRemark(getStringValue(telemetryData, "outRemark"));
            dto.setPayMethod(getStringValue(telemetryData, "payMethod"));
            dto.setExitNo(getStringValue(telemetryData, "exitNo"));
            dto.setExitName(getStringValue(telemetryData, "exitName"));
            dto.setDriveOutPhoto(getStringValue(telemetryData, "driveOutPhoto"));
            dto.setActualPay(getStringValue(telemetryData, "actualPay"));

            // 尝试从timestamp字段获取时间戳
            Object timestampObj = telemetryData.get("timestamp");
            if (timestampObj != null) {
                dto.setTs(System.currentTimeMillis()); // 如果没有单独的时间戳字段，使用当前时间
            }

        } catch (Exception e) {
            log.error("转换遥测数据失败: {}", e.getMessage(), e);
        }

        return dto;
    }

    /**
     * 从Map中安全获取字符串值
     */
    private String getStringValue(Map<String, Object> data, String key) {
        Object value = data.get(key);
        return value != null ? value.toString() : null;
    }
}