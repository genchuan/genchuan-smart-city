package cn.iocoder.yudao.module.smartcity.service.parking;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.parking.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parking.ParkingDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.parking.ParkingMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 停车管理用户管理与服务 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ParkingServiceImpl implements ParkingService {

    @Resource
    private ParkingMapper parkingMapper;

    @Override
    public Long createParking(ParkingSaveReqVO createReqVO) {
        // 插入
        ParkingDO parking = BeanUtils.toBean(createReqVO, ParkingDO.class);
        parkingMapper.insert(parking);
        // 返回
        return parking.getId();
    }

    @Override
    public void updateParking(ParkingSaveReqVO updateReqVO) {
        // 校验存在
        validateParkingExists(updateReqVO.getId());
        // 更新
        ParkingDO updateObj = BeanUtils.toBean(updateReqVO, ParkingDO.class);
        parkingMapper.updateById(updateObj);
    }

    @Override
    public void deleteParking(Long id) {
        // 校验存在
        validateParkingExists(id);
        // 删除
        parkingMapper.deleteById(id);
    }

    private void validateParkingExists(Long id) {
        if (parkingMapper.selectById(id) == null) {
            throw exception(PARKING_NOT_EXISTS);
        }
    }

    @Override
    public ParkingDO getParking(Long id) {
        return parkingMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingDO> getParkingPage(ParkingPageReqVO pageReqVO) {
        return parkingMapper.selectPage(pageReqVO);
    }

}