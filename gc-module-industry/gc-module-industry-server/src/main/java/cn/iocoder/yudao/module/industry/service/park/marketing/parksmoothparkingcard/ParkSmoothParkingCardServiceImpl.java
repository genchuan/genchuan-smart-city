package cn.iocoder.yudao.module.industry.service.park.marketing.parksmoothparkingcard;

import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.marketing.parksmoothparkingcard.vo.ParkSmoothParkingCardSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.marketing.parksmoothparkingcard.ParkSmoothParkingCardMapper;
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
 * 畅停卡 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkSmoothParkingCardServiceImpl implements ParkSmoothParkingCardService {

    @Resource
    private ParkSmoothParkingCardMapper parkSmoothParkingCardMapper;

    @Override
    public Long createParkSmoothParkingCard(ParkSmoothParkingCardSaveReqVO createReqVO) {
        // 插入
        ParkSmoothParkingCardDO parkSmoothParkingCard = BeanUtils.toBean(createReqVO, ParkSmoothParkingCardDO.class);
        parkSmoothParkingCardMapper.insert(parkSmoothParkingCard);
        // 返回
        return parkSmoothParkingCard.getId();
    }

    @Override
    public void updateParkSmoothParkingCard(ParkSmoothParkingCardSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSmoothParkingCardExists(updateReqVO.getId());
        // 更新
        ParkSmoothParkingCardDO updateObj = BeanUtils.toBean(updateReqVO, ParkSmoothParkingCardDO.class);
        parkSmoothParkingCardMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSmoothParkingCard(Long id) {
        // 校验存在
        validateParkSmoothParkingCardExists(id);
        // 删除
        parkSmoothParkingCardMapper.deleteById(id);
    }

    private void validateParkSmoothParkingCardExists(Long id) {
        if (parkSmoothParkingCardMapper.selectById(id) == null) {
            throw exception(PARK_SMOOTH_PARKING_CARD_NOT_EXISTS);
        }
    }

    @Override
    public ParkSmoothParkingCardDO getParkSmoothParkingCard(Long id) {
        return parkSmoothParkingCardMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSmoothParkingCardDO> getParkSmoothParkingCardPage(ParkSmoothParkingCardPageReqVO pageReqVO) {
        return parkSmoothParkingCardMapper.selectPage(pageReqVO);
    }

}
