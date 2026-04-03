package cn.iocoder.yudao.module.vehiclecharging.service.abnormalorder;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.vehiclecharging.controller.admin.abnormalorder.vo.*;
import cn.iocoder.yudao.module.vehiclecharging.dal.dataobject.abnormalorder.AbnormalOrderDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.vehiclecharging.dal.mysql.abnormalorder.AbnormalOrderMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.vehiclecharging.enums.ErrorCodeConstants.*;

/**
 * 异常订单 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class AbnormalOrderServiceImpl implements AbnormalOrderService {

    @Resource
    private AbnormalOrderMapper abnormalOrderMapper;

    @Override
    public Long createAbnormalOrder(AbnormalOrderSaveReqVO createReqVO) {
        // 插入
        AbnormalOrderDO abnormalOrder = BeanUtils.toBean(createReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.insert(abnormalOrder);

        // 返回
        return abnormalOrder.getId();
    }

    @Override
    public void updateAbnormalOrder(AbnormalOrderSaveReqVO updateReqVO) {
        // 校验存在
        validateAbnormalOrderExists(updateReqVO.getId());
        // 更新
        AbnormalOrderDO updateObj = BeanUtils.toBean(updateReqVO, AbnormalOrderDO.class);
        abnormalOrderMapper.updateById(updateObj);
    }

    @Override
    public void deleteAbnormalOrder(Long id) {
        // 校验存在
        validateAbnormalOrderExists(id);
        // 删除
        abnormalOrderMapper.deleteById(id);
    }

    @Override
    public void deleteAbnormalOrderListByIds(List<Long> ids) {
        // 删除
        abnormalOrderMapper.deleteByIds(ids);
    }


    private void validateAbnormalOrderExists(Long id) {
        if (abnormalOrderMapper.selectById(id) == null) {
            throw exception(ABNORMAL_ORDER_NOT_EXISTS);
        }
    }

    @Override
    public AbnormalOrderDO getAbnormalOrder(Long id) {
        return abnormalOrderMapper.selectById(id);
    }

    @Override
    public PageResult<AbnormalOrderDO> getAbnormalOrderPage(AbnormalOrderPageReqVO pageReqVO) {
        return abnormalOrderMapper.selectPage(pageReqVO);
    }

}