package cn.iocoder.yudao.module.industry.service.park.through.carparking;

import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.through.carparking.vo.ParkCarParkingSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.through.carparking.ParkCarParkingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.through.carparking.ParkCarParkingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 在停车辆 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkCarParkingServiceImpl implements ParkCarParkingService {

    @Resource
    private ParkCarParkingMapper parkCarParkingMapper;

    @Override
    public Long createParkCarParking(ParkCarParkingSaveReqVO createReqVO) {
        // 插入
        ParkCarParkingDO parkCarParking = BeanUtils.toBean(createReqVO, ParkCarParkingDO.class);
        parkCarParkingMapper.insert(parkCarParking);
        // 返回
        return parkCarParking.getId();
    }

    @Override
    public void updateParkCarParking(ParkCarParkingSaveReqVO updateReqVO) {
        // 校验存在
        validateParkCarParkingExists(updateReqVO.getId());
        // 更新
        ParkCarParkingDO updateObj = BeanUtils.toBean(updateReqVO, ParkCarParkingDO.class);
        parkCarParkingMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkCarParking(Long id) {
        // 校验存在
        validateParkCarParkingExists(id);
        // 删除
        parkCarParkingMapper.deleteById(id);
    }

    private void validateParkCarParkingExists(Long id) {
        if (parkCarParkingMapper.selectById(id) == null) {
            throw exception(PARK_CAR_PARKING_NOT_EXISTS);
        }
    }

    @Override
    public ParkCarParkingDO getParkCarParking(Long id) {
        return parkCarParkingMapper.selectById(id);
    }

    @Override
    public PageResult<ParkCarParkingDO> getParkCarParkingPage(ParkCarParkingPageReqVO pageReqVO) {
        return parkCarParkingMapper.selectPage(pageReqVO);
    }

}