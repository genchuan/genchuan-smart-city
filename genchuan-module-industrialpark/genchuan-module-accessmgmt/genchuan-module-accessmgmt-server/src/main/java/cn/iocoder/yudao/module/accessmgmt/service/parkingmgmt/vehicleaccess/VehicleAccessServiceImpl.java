package cn.iocoder.yudao.module.accessmgmt.service.parkingmgmt.vehicleaccess;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.accessmgmt.controller.admin.parkingmgmt.vehicleaccess.vo.*;
import cn.iocoder.yudao.module.accessmgmt.dal.dataobject.parkingmgmt.vehicleaccess.VehicleAccessDO;
import cn.iocoder.yudao.module.accessmgmt.dal.mysql.parkingmgmt.vehicleaccess.VehicleAccessMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.accessmgmt.enums.ErrorCodeConstants.*;

/**
 * 车辆通行 Service 实现类
 * <p>
 * 提供车辆通行的全流程业务：分页查询、车牌识别、放行管控、费用计算、停车缴费、数据导出及态势统计。
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VehicleAccessServiceImpl implements VehicleAccessService {

    @Resource
    private VehicleAccessMapper vehicleAccessMapper;

    // ==================== 通行查询 ====================

    @Override
    public PageResult<VehicleAccessRespVO> getVehicleAccessPage(VehicleAccessPageReqVO pageReqVO) {
        // 分页查询，Mapper 层按 plateNo(模糊)/vehicleType(精确)/parkName(精确)/accessStatus(精确)/payStatus(精确)/accessTime 范围动态条件筛选，按主键倒序
        PageResult<VehicleAccessDO> pageResult = vehicleAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VehicleAccessRespVO.class);
    }

    @Override
    public VehicleAccessRespVO getVehicleAccess(Long id) {
        // 按主键查单条，不存在抛 VEHICLE_ACCESS_NOT_EXISTS 业务异常
        VehicleAccessDO entity = vehicleAccessMapper.selectById(id);
        if (entity == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VehicleAccessRespVO.class);
    }

    // ==================== 车牌识别 ====================

    @Override
    public VehicleAccessRecognizeRespVO recognizeVehicleAccess(VehicleAccessRecognizeReqVO reqVO) {
        // 模拟车牌识别：返回固定识别结果（车牌号 + 车辆类型）
        VehicleAccessRecognizeRespVO respVO = new VehicleAccessRecognizeRespVO();
        respVO.setPlateNo("闽A12345");
        respVO.setVehicleType("小型车");
        respVO.setSuccess(true);
        return respVO;
    }

    // ==================== 车辆管控（放行 / 拦截） ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean passVehicleAccess(VehicleAccessPassReqVO reqVO) {
        // 车辆放行：校验记录存在 → accessStatus → "已离场"
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        VehicleAccessDO updateObj = new VehicleAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setAccessStatus("已离场");
        vehicleAccessMapper.updateById(updateObj);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean blockVehicleAccess(VehicleAccessBlockReqVO reqVO) {
        // 车辆拦截：校验记录存在 → 拦截原因写入 reserve1
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        VehicleAccessDO updateObj = new VehicleAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setReserve1(reqVO.getBlockReason());
        vehicleAccessMapper.updateById(updateObj);
        return true;
    }

    // ==================== 费用计算 ====================

    @Override
    public VehicleAccessCalculateRespVO calculateVehicleAccess(VehicleAccessCalculateReqVO reqVO) {
        // 1. 校验通行记录存在
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        // 2. 计算停车时长：若 accessTime 为空则默认 30 分钟，否则计算 accessTime → now 的分钟数
        int duration = 30;
        if (exist.getAccessTime() != null) {
            duration = (int) java.time.Duration.between(exist.getAccessTime(), LocalDateTime.now()).toMinutes();
        }
        // 3. 按收费标准 0.1 元/分钟 计算费用，四舍五入保留两位小数
        BigDecimal rate = new BigDecimal("0.1");
        BigDecimal fee = rate.multiply(BigDecimal.valueOf(duration)).setScale(2, RoundingMode.HALF_UP);
        // 4. 返回停车时长和费用
        VehicleAccessCalculateRespVO respVO = new VehicleAccessCalculateRespVO();
        respVO.setParkDuration(duration);
        respVO.setFeeAmount(fee);
        return respVO;
    }

    // ==================== 停车缴费 ====================

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VehicleAccessPayRespVO payVehicleAccess(VehicleAccessPayReqVO reqVO) {
        // 缴费：校验记录存在 → payStatus → "已缴费"
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        VehicleAccessDO updateObj = new VehicleAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setPayStatus("已缴费");
        vehicleAccessMapper.updateById(updateObj);
        // 返回支付凭据链接
        VehicleAccessPayRespVO respVO = new VehicleAccessPayRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/receipt/" + reqVO.getId());
        return respVO;
    }

    // ==================== 数据导出 ====================

    @Override
    public List<VehicleAccessRespVO> getVehicleAccessList(VehicleAccessPageReqVO pageReqVO) {
        // 设置 PAGE_SIZE_NONE 绕过 MyBatis-Plus 分页限制，查询全量数据
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<VehicleAccessDO> pageResult = vehicleAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), VehicleAccessRespVO.class);
    }

    // ==================== 统计态势 ====================

    @Override
    public VehicleAccessChartRespVO getVehicleAccessChart(Long startTime, Long endTime) {
        // 四维度聚合：各时段通行量趋势 + 每日进出数量趋势 + 各停车场通行数量 + 车辆类型分布
        VehicleAccessChartRespVO chartVO = new VehicleAccessChartRespVO();
        chartVO.setTimeTrendList(vehicleAccessMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setDayTrendList(vehicleAccessMapper.selectDayTrendList(startTime, endTime));
        chartVO.setParkCountList(vehicleAccessMapper.selectParkCountList(startTime, endTime));
        chartVO.setVehicleTypeList(vehicleAccessMapper.selectVehicleTypeList(startTime, endTime));
        return chartVO;
    }

}
