package cn.iocoder.yudao.module.envirhealth.util.codegenerator.vehicle;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.vehicle.VehicleMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * 功能:
 * 作者:SWE22008
 * 日期:2026/3/18 14:18
 */
@Component
public class VehicleCodeGenerator {

    @Resource
    private VehicleMapper vehicleMapper;

    /**
     * 生成sys_vehicle_id（格式：uuid-vehicle- + 3位序号，如 uuid-vehicle-001）
     */
    public String generateVehicleId() {
        // 1. 查询全局最大序号（也可按日期维度，根据需求调整）
        Integer maxSeq = vehicleMapper.selectMaxSeq();
        // 2. 新序号
        int newSeq = maxSeq == null ? 1 : maxSeq + 1;
        // 3. 拼接sys_vehicle_id
        return String.format("uuid-vehicle-%03d", newSeq);
    }
}