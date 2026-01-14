package cn.iocoder.yudao.module.industry.service.park.discount.parkchargefee;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkchargefee.vo.ParkChargeFeeSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkchargefee.ParkChargeFeeDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkchargefee.ParkChargeFeeMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 充电收费 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkChargeFeeServiceImpl implements ParkChargeFeeService {

    @Resource
    private ParkChargeFeeMapper parkChargeFeeMapper;

    @Override
    public Long createParkChargeFee(ParkChargeFeeSaveReqVO createReqVO) {
        // 插入
        ParkChargeFeeDO parkChargeFee = BeanUtils.toBean(createReqVO, ParkChargeFeeDO.class);
        parkChargeFeeMapper.insert(parkChargeFee);
        // 返回
        return parkChargeFee.getId();
    }

    @Override
    public void updateParkChargeFee(ParkChargeFeeSaveReqVO updateReqVO) {
        // 校验存在
        validateParkChargeFeeExists(updateReqVO.getId());
        // 更新
        ParkChargeFeeDO updateObj = BeanUtils.toBean(updateReqVO, ParkChargeFeeDO.class);
        parkChargeFeeMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkChargeFee(Long id) {
        // 校验存在
        validateParkChargeFeeExists(id);
        // 删除
        parkChargeFeeMapper.deleteById(id);
    }

    private void validateParkChargeFeeExists(Long id) {
        if (parkChargeFeeMapper.selectById(id) == null) {
            throw exception(PARK_CHARGE_FEE_NOT_EXISTS);
        }
    }

    @Override
    public ParkChargeFeeDO getParkChargeFee(Long id) {
        return parkChargeFeeMapper.selectById(id);
    }

    @Override
    public PageResult<ParkChargeFeeDO> getParkChargeFeePage(ParkChargeFeePageReqVO pageReqVO) {
        return parkChargeFeeMapper.selectPage(pageReqVO);
    }

}
