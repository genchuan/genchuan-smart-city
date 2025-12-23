package cn.iocoder.yudao.module.industry.service.park.pay.parkreservation;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkreservation.vo.ParkReservationSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkreservation.ParkReservationDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkreservation.ParkReservationMapper;
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
 * 停车预约服务 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkReservationServiceImpl implements ParkReservationService {

    @Resource
    private ParkReservationMapper parkReservationMapper;

    @Override
    public Long createParkReservation(ParkReservationSaveReqVO createReqVO) {
        // 插入
        ParkReservationDO parkReservation = BeanUtils.toBean(createReqVO, ParkReservationDO.class);
        parkReservationMapper.insert(parkReservation);
        // 返回
        return parkReservation.getId();
    }

    @Override
    public void updateParkReservation(ParkReservationSaveReqVO updateReqVO) {
        // 校验存在
        validateParkReservationExists(updateReqVO.getId());
        // 更新
        ParkReservationDO updateObj = BeanUtils.toBean(updateReqVO, ParkReservationDO.class);
        parkReservationMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkReservation(Long id) {
        // 校验存在
        validateParkReservationExists(id);
        // 删除
        parkReservationMapper.deleteById(id);
    }

    private void validateParkReservationExists(Long id) {
        if (parkReservationMapper.selectById(id) == null) {
            throw exception(PARK_RESERVATION_NOT_EXISTS);
        }
    }

    @Override
    public ParkReservationDO getParkReservation(Long id) {
        return parkReservationMapper.selectById(id);
    }

    @Override
    public PageResult<ParkReservationDO> getParkReservationPage(ParkReservationPageReqVO pageReqVO) {
        return parkReservationMapper.selectPage(pageReqVO);
    }

}
