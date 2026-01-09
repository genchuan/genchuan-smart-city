package cn.iocoder.yudao.module.smartcity.service.parkingfee;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.parkingfee.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.parkingfee.ParkingFeeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.parkingfee.ParkingFeeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 停车收费管理 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ParkingFeeServiceImpl implements ParkingFeeService {

    @Resource
    private ParkingFeeMapper parkingFeeMapper;

    @Override
    public Long createParkingFee(ParkingFeeSaveReqVO createReqVO) {
        // 插入
        ParkingFeeDO parkingFee = BeanUtils.toBean(createReqVO, ParkingFeeDO.class);
        parkingFeeMapper.insert(parkingFee);
        // 返回
        return parkingFee.getId();
    }

    @Override
    public void updateParkingFee(ParkingFeeSaveReqVO updateReqVO) {
        // 校验存在
        validateParkingFeeExists(updateReqVO.getId());
        // 更新
        ParkingFeeDO updateObj = BeanUtils.toBean(updateReqVO, ParkingFeeDO.class);
        parkingFeeMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkingFee(Long id) {
        // 校验存在
        validateParkingFeeExists(id);
        // 删除
        parkingFeeMapper.deleteById(id);
    }

    private void validateParkingFeeExists(Long id) {
        if (parkingFeeMapper.selectById(id) == null) {
            throw exception(PARKING_FEE_NOT_EXISTS);
        }
    }

    @Override
    public ParkingFeeDO getParkingFee(Long id) {
        return parkingFeeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkingFeeDO> getParkingFeePage(ParkingFeePageReqVO pageReqVO) {
        return parkingFeeMapper.selectPage(pageReqVO);
    }

}