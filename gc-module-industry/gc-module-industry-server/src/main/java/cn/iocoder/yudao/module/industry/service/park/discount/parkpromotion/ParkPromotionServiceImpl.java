package cn.iocoder.yudao.module.industry.service.park.discount.parkpromotion;

import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.discount.parkpromotion.vo.ParkPromotionSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.discount.parkpromotion.ParkPromotionDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.discount.parkpromotion.ParkPromotionMapper;
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
 * 优惠活动 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPromotionServiceImpl implements ParkPromotionService {

    @Resource
    private ParkPromotionMapper parkPromotionMapper;

    @Override
    public Long createParkPromotion(ParkPromotionSaveReqVO createReqVO) {
        // 插入
        ParkPromotionDO parkPromotion = BeanUtils.toBean(createReqVO, ParkPromotionDO.class);
        parkPromotionMapper.insert(parkPromotion);
        // 返回
        return parkPromotion.getId();
    }

    @Override
    public void updateParkPromotion(ParkPromotionSaveReqVO updateReqVO) {
        // 校验存在
        validateParkPromotionExists(updateReqVO.getId());
        // 更新
        ParkPromotionDO updateObj = BeanUtils.toBean(updateReqVO, ParkPromotionDO.class);
        parkPromotionMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPromotion(Long id) {
        // 校验存在
        validateParkPromotionExists(id);
        // 删除
        parkPromotionMapper.deleteById(id);
    }

    private void validateParkPromotionExists(Long id) {
        if (parkPromotionMapper.selectById(id) == null) {
            throw exception(PARK_PROMOTION_NOT_EXISTS);
        }
    }

    @Override
    public ParkPromotionDO getParkPromotion(Long id) {
        return parkPromotionMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPromotionDO> getParkPromotionPage(ParkPromotionPageReqVO pageReqVO) {
        return parkPromotionMapper.selectPage(pageReqVO);
    }

}
