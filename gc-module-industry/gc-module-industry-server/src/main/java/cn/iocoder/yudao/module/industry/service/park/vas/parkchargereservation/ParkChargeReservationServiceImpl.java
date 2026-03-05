package cn.iocoder.yudao.module.industry.service.park.vas.parkchargereservation;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkchargereservation.vo.ParkChargeReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkchargereservation.ParkChargeReservationDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkchargereservation.ParkChargeReservationMapper;
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
 * 充电预约 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkChargeReservationServiceImpl implements ParkChargeReservationService {

    @Resource
    private ParkChargeReservationMapper parkChargeReservationMapper;

    @Override
    public Long createParkChargeReservation(ParkChargeReservationSaveReqVO createReqVO) {
        // 插入
        ParkChargeReservationDO parkChargeReservation = BeanUtils.toBean(createReqVO, ParkChargeReservationDO.class);
        parkChargeReservationMapper.insert(parkChargeReservation);
        // 返回
        return parkChargeReservation.getId();
    }

    @Override
    public void updateParkChargeReservation(ParkChargeReservationSaveReqVO updateReqVO) {
        // 校验存在
        validateParkChargeReservationExists(updateReqVO.getId());
        // 更新
        ParkChargeReservationDO updateObj = BeanUtils.toBean(updateReqVO, ParkChargeReservationDO.class);
        parkChargeReservationMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkChargeReservation(Long id) {
        // 校验存在
        validateParkChargeReservationExists(id);
        // 删除
        parkChargeReservationMapper.deleteById(id);
    }

    private void validateParkChargeReservationExists(Long id) {
        if (parkChargeReservationMapper.selectById(id) == null) {
            throw exception(PARK_CHARGE_RESERVATION_NOT_EXISTS);
        }
    }

    @Override
    public ParkChargeReservationDO getParkChargeReservation(Long id) {
        return parkChargeReservationMapper.selectById(id);
    }

    @Override
    public PageResult<ParkChargeReservationDO> getParkChargeReservationPage(ParkChargeReservationPageReqVO pageReqVO) {
        return parkChargeReservationMapper.selectPage(pageReqVO);
    }

}
