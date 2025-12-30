package cn.iocoder.yudao.module.industry.service.park.pay.parkdiscountactivity;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivityPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkdiscountactivity.vo.ParkDiscountActivitySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkdiscountactivity.ParkDiscountActivityDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkdiscountactivity.ParkDiscountActivityMapper;
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
public class ParkDiscountActivityServiceImpl implements ParkDiscountActivityService {

    @Resource
    private ParkDiscountActivityMapper parkDiscountActivityMapper;

    @Override
    public Long createParkDiscountActivity(ParkDiscountActivitySaveReqVO createReqVO) {
        // 插入
        ParkDiscountActivityDO parkDiscountActivity = BeanUtils.toBean(createReqVO, ParkDiscountActivityDO.class);
        parkDiscountActivityMapper.insert(parkDiscountActivity);
        // 返回
        return parkDiscountActivity.getId();
    }

    @Override
    public void updateParkDiscountActivity(ParkDiscountActivitySaveReqVO updateReqVO) {
        // 校验存在
        validateParkDiscountActivityExists(updateReqVO.getId());
        // 更新
        ParkDiscountActivityDO updateObj = BeanUtils.toBean(updateReqVO, ParkDiscountActivityDO.class);
        parkDiscountActivityMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkDiscountActivity(Long id) {
        // 校验存在
        validateParkDiscountActivityExists(id);
        // 删除
        parkDiscountActivityMapper.deleteById(id);
    }

    private void validateParkDiscountActivityExists(Long id) {
        if (parkDiscountActivityMapper.selectById(id) == null) {
            throw exception(PARK_DISCOUNT_ACTIVITY_NOT_EXISTS);
        }
    }

    @Override
    public ParkDiscountActivityDO getParkDiscountActivity(Long id) {
        return parkDiscountActivityMapper.selectById(id);
    }

    @Override
    public PageResult<ParkDiscountActivityDO> getParkDiscountActivityPage(ParkDiscountActivityPageReqVO pageReqVO) {
        return parkDiscountActivityMapper.selectPage(pageReqVO);
    }

}
