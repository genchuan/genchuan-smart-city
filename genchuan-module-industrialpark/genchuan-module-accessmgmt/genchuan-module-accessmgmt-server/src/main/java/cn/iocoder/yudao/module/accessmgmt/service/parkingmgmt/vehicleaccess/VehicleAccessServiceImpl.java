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
 *
 * @author 亘川智城
 */
@Service
@Validated
public class VehicleAccessServiceImpl implements VehicleAccessService {

    @Resource
    private VehicleAccessMapper vehicleAccessMapper;

    @Override
    public PageResult<VehicleAccessRespVO> getVehicleAccessPage(VehicleAccessPageReqVO pageReqVO) {
        PageResult<VehicleAccessDO> pageResult = vehicleAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult, VehicleAccessRespVO.class);
    }

    @Override
    public VehicleAccessRespVO getVehicleAccess(Long id) {
        VehicleAccessDO entity = vehicleAccessMapper.selectById(id);
        if (entity == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        return BeanUtils.toBean(entity, VehicleAccessRespVO.class);
    }

    @Override
    public VehicleAccessRecognizeRespVO recognizeVehicleAccess(VehicleAccessRecognizeReqVO reqVO) {
        VehicleAccessRecognizeRespVO respVO = new VehicleAccessRecognizeRespVO();
        // 模拟车牌识别结果
        respVO.setPlateNo("闽A12345");
        respVO.setVehicleType("小型车");
        respVO.setSuccess(true);
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean passVehicleAccess(VehicleAccessPassReqVO reqVO) {
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

    @Override
    public VehicleAccessCalculateRespVO calculateVehicleAccess(VehicleAccessCalculateReqVO reqVO) {
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        // 模拟计算: 假设收费标准为每分钟0.1元
        int duration = 30;
        if (exist.getAccessTime() != null) {
            duration = (int) java.time.Duration.between(exist.getAccessTime(), LocalDateTime.now()).toMinutes();
        }
        BigDecimal rate = new BigDecimal("0.1");
        BigDecimal fee = rate.multiply(BigDecimal.valueOf(duration)).setScale(2, RoundingMode.HALF_UP);

        VehicleAccessCalculateRespVO respVO = new VehicleAccessCalculateRespVO();
        respVO.setParkDuration(duration);
        respVO.setFeeAmount(fee);
        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public VehicleAccessPayRespVO payVehicleAccess(VehicleAccessPayReqVO reqVO) {
        VehicleAccessDO exist = vehicleAccessMapper.selectById(reqVO.getId());
        if (exist == null) {
            throw exception(VEHICLE_ACCESS_NOT_EXISTS);
        }
        VehicleAccessDO updateObj = new VehicleAccessDO();
        updateObj.setId(reqVO.getId());
        updateObj.setPayStatus("已缴费");
        vehicleAccessMapper.updateById(updateObj);

        VehicleAccessPayRespVO respVO = new VehicleAccessPayRespVO();
        respVO.setSuccess(true);
        respVO.setPayUrl("https://pay.example.com/receipt/" + reqVO.getId());
        return respVO;
    }

    @Override
    public List<VehicleAccessRespVO> getVehicleAccessList(VehicleAccessPageReqVO pageReqVO) {
        pageReqVO.setPageSize(PageParam.PAGE_SIZE_NONE);
        PageResult<VehicleAccessDO> pageResult = vehicleAccessMapper.selectPage(pageReqVO);
        return BeanUtils.toBean(pageResult.getList(), VehicleAccessRespVO.class);
    }

    @Override
    public VehicleAccessChartRespVO getVehicleAccessChart(Long startTime, Long endTime) {
        VehicleAccessChartRespVO chartVO = new VehicleAccessChartRespVO();
        chartVO.setTimeTrendList(vehicleAccessMapper.selectTimeTrendList(startTime, endTime));
        chartVO.setDayTrendList(vehicleAccessMapper.selectDayTrendList(startTime, endTime));
        chartVO.setParkCountList(vehicleAccessMapper.selectParkCountList(startTime, endTime));
        chartVO.setVehicleTypeList(vehicleAccessMapper.selectVehicleTypeList(startTime, endTime));
        return chartVO;
    }

}
