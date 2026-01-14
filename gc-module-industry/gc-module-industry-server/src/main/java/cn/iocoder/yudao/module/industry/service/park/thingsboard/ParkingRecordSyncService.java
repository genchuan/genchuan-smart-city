package cn.iocoder.yudao.module.industry.service.park.thingsboard;

import cn.iocoder.yudao.framework.tenant.core.context.TenantContextHolder;
import cn.iocoder.yudao.module.industry.controller.admin.park.thingsboard.ParkingTelemetryDTO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveinrecord.CarDriveinRecordDO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.cardriveoutrecord.CarDriveoutRecordDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveinrecord.CarDriveinRecordMapper;
import cn.iocoder.yudao.module.industry.dal.mysql.park.cardriveoutrecord.CarDriveoutRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 停车记录同步服务
 * 负责从Thingsboard获取数据并同步到本地数据库
 *
 * @author zhucongquan
 */
@Slf4j
@Service
public class ParkingRecordSyncService {

    @Resource
    private ParkingRecordTbService parkingRecordTbService;

    @Resource
    private TelemetryDataConverter telemetryDataConverter;

    @Resource
    private CarDriveinRecordMapper carDriveinRecordMapper;

    @Resource
    private CarDriveoutRecordMapper carDriveoutRecordMapper;

    // 设备ID常量（根据您的描述，可以写死）
    private static final String DEVICE_ID = "50501110-ea16-11f0-a23c-6b31e613548a";

    // 日期时间格式化器
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * 同步停车记录数据
     * 从Thingsboard获取遥测数据，拆分并插入到对应的数据库表
     */
    @Transactional(rollbackFor = Exception.class)
    public void syncParkingRecords() {

        // 检查租户上下文
        Long tenantId = TenantContextHolder.getTenantId();
        if (tenantId == null) {
            log.warn("租户上下文为空，设置默认租户ID为1");
            TenantContextHolder.setTenantId(1L);
            tenantId = 1L;
        }

        log.info("开始同步停车记录数据，租户ID: {}", tenantId);

        try {
            log.info("开始同步停车记录数据...");

            // 步骤1: 获取遥测key列表
            List<String> keys = parkingRecordTbService.getTelemetryKeys(DEVICE_ID);
            log.info("获取到遥测key列表: {}", keys);

            // 步骤2: 获取遥测值
            Map<String, Object> telemetryValues = parkingRecordTbService.getTelemetryValues(DEVICE_ID, keys);
            log.info("获取到遥测值: {}", telemetryValues);

            // 步骤3: 转换为DTO
            ParkingTelemetryDTO telemetryDTO = telemetryDataConverter.convertToDto(telemetryValues);

            // 步骤4: 根据dataType拆分并插入数据库
            if (telemetryDTO.getDataType() == null) {
                log.warn("遥测数据中没有dataType字段，无法区分记录类型");
                return;
            }

            String dataType = telemetryDTO.getDataType().toLowerCase();
            switch (dataType) {
                case "inpark":
                    // 入场记录
                    saveDriveInRecord(telemetryDTO);
                    break;
                case "outpark":
                    // 出场记录
                    saveDriveOutRecord(telemetryDTO);
                    break;
                default:
                    log.warn("未知的数据类型: {}", dataType);
                    break;
            }

            log.info("停车记录数据同步完成");

        } catch (Exception e) {
            log.error("同步停车记录数据失败: {}", e.getMessage(), e);
            throw new RuntimeException("同步停车记录数据失败", e);
        }
    }

    /**
     * 保存入场记录
     */
    private void saveDriveInRecord(ParkingTelemetryDTO dto) {
        try {
            // 检查记录是否已存在（根据recordId）
            if (StringUtils.hasText(dto.getRecordId())) {
                CarDriveinRecordDO existingRecord = carDriveinRecordMapper.selectByRecordId(dto.getRecordId());
                if (existingRecord != null) {
                    log.info("入场记录已存在，recordId: {}", dto.getRecordId());
                    return;
                }
            }

            // 创建入场记录DO对象
            CarDriveinRecordDO driveinRecord = new CarDriveinRecordDO();

            // 设置字段值
            driveinRecord.setRecordId(dto.getRecordId());
            driveinRecord.setPlateNumber(dto.getPlateNumber());
            driveinRecord.setPlateType(dto.getPlateType());
            driveinRecord.setEntranceNo(dto.getEntranceNo());
            driveinRecord.setEntranceName(dto.getEntranceName());
            driveinRecord.setDriveInTime(parseDateTime(dto.getDriveInTime()));
//            driveinRecord.setSign(dto.getSign());
//            driveinRecord.setVersion(dto.getVersion());
            driveinRecord.setEmptyPlot(parseInteger(dto.getEmptyPlot()));
//            driveinRecord.setService(dto.getService());
            driveinRecord.setOperatorId(dto.getOperaterId());
            driveinRecord.setOperatorName(dto.getOperaterName());
            driveinRecord.setDriveInPhoto(dto.getDriveInPhoto());

            // 设置系统字段
            driveinRecord.setCreateTime(LocalDateTime.now());
            driveinRecord.setUpdateTime(LocalDateTime.now());
            driveinRecord.setCreator("system");
            driveinRecord.setUpdater("system");
            driveinRecord.setDeleted(false);


            // 插入数据库
            int result = carDriveinRecordMapper.insert(driveinRecord);
            if (result > 0) {
                log.info("成功保存入场记录，车牌号: {}, 入场时间: {}",
                        dto.getPlateNumber(), dto.getDriveInTime());
            } else {
                log.warn("保存入场记录失败");
            }

        } catch (Exception e) {
            log.error("保存入场记录失败: {}", e.getMessage(), e);
            throw new RuntimeException("保存入场记录失败", e);
        }
    }

    /**
     * 保存出场记录
     */
    private void saveDriveOutRecord(ParkingTelemetryDTO dto) {
        try {
            // 检查记录是否已存在（根据recordId）
            if (StringUtils.hasText(dto.getRecordId())) {
                CarDriveoutRecordDO existingRecord = carDriveoutRecordMapper.selectByRecordId(dto.getRecordId());
                if (existingRecord != null) {
                    log.info("出场记录已存在，recordId: {}", dto.getRecordId());
                    return;
                }
            }

            // 创建出场记录DO对象
            CarDriveoutRecordDO driveoutRecord = new CarDriveoutRecordDO();

            // 设置字段值
            driveoutRecord.setRecordId(dto.getRecordId());
            driveoutRecord.setPlateNumber(dto.getPlateNumber());
            driveoutRecord.setPlateType(dto.getPlateType());
            driveoutRecord.setExitNo(dto.getExitNo());
            driveoutRecord.setExitName(dto.getExitName());
            driveoutRecord.setDriveInTime(parseDateTime(dto.getDriveInTime()));
            driveoutRecord.setDriveOutTime(parseDateTime(dto.getDriveOutTime()));
            driveoutRecord.setOutType(dto.getOutType());
            driveoutRecord.setOutRemark(dto.getOutRemark());
            driveoutRecord.setShouldPay(parseBigDecimal(dto.getShouldPay()));
            driveoutRecord.setActualPay(parseBigDecimal(dto.getActualPay()));
            driveoutRecord.setPayMethod(dto.getPayMethod());
            driveoutRecord.setDriveOutPhoto(dto.getDriveOutPhoto());
//            driveoutRecord.setSign(dto.getSign());
//            driveoutRecord.setVersion(dto.getVersion());
            driveoutRecord.setOperatorId(dto.getOperaterId());
            driveoutRecord.setOperatorName(dto.getOperaterName());

            // 设置系统字段
            driveoutRecord.setCreateTime(LocalDateTime.now());
            driveoutRecord.setUpdateTime(LocalDateTime.now());
            driveoutRecord.setCreator("system");
            driveoutRecord.setUpdater("system");
            driveoutRecord.setDeleted(false);

            // 插入数据库
            int result = carDriveoutRecordMapper.insert(driveoutRecord);
            if (result > 0) {
                log.info("成功保存出场记录，车牌号: {}, 出场时间: {}, 实付金额: {}",
                        dto.getPlateNumber(), dto.getDriveOutTime(), dto.getActualPay());
            } else {
                log.warn("保存出场记录失败");
            }

        } catch (Exception e) {
            log.error("保存出场记录失败: {}", e.getMessage(), e);
            throw new RuntimeException("保存出场记录失败", e);
        }
    }

    /**
     * 解析日期时间字符串为LocalDateTime
     */
    private LocalDateTime parseDateTime(String dateTimeStr) {
        if (!StringUtils.hasText(dateTimeStr)) {
            return null;
        }
        try {
            return LocalDateTime.parse(dateTimeStr, DATE_TIME_FORMATTER);
        } catch (Exception e) {
            log.warn("解析日期时间失败: {}, 使用当前时间", dateTimeStr);
            return LocalDateTime.now();
        }
    }

    /**
     * 解析车牌类型
     */
    private Integer parsePlateType(String plateTypeStr) {
        if (!StringUtils.hasText(plateTypeStr)) {
            return 0;
        }
        try {
            return Integer.parseInt(plateTypeStr);
        } catch (Exception e) {
            log.warn("解析车牌类型失败: {}, 使用默认值0", plateTypeStr);
            return 0;
        }
    }

    /**
     * 解析整数字符串
     */
    private Integer parseInteger(String intStr) {
        if (!StringUtils.hasText(intStr)) {
            return 0;
        }
        try {
            return Integer.parseInt(intStr);
        } catch (Exception e) {
            log.warn("解析整数失败: {}, 使用默认值0", intStr);
            return 0;
        }
    }

    /**
     * 解析金额字符串为BigDecimal
     */
    private java.math.BigDecimal parseBigDecimal(String decimalStr) {
        if (!StringUtils.hasText(decimalStr)) {
            return java.math.BigDecimal.ZERO;
        }
        try {
            return new java.math.BigDecimal(decimalStr);
        } catch (Exception e) {
            log.warn("解析金额失败: {}, 使用默认值0", decimalStr);
            return java.math.BigDecimal.ZERO;
        }
    }
}
