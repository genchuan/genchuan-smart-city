package cn.iocoder.yudao.module.park.service.park.order.settlement;

import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.order.settlement.vo.SettlementSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.order.settlement.SettlementDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.order.settlement.SettlementMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.*;

/**
 * 分账结算 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SettlementServiceImpl implements SettlementService {

    @Resource
    private SettlementMapper settlementMapper;

    @Override
    public Long createSettlement(SettlementSaveReqVO createReqVO) {
        // 插入
        SettlementDO settlement = BeanUtils.toBean(createReqVO, SettlementDO.class);
        settlementMapper.insert(settlement);
        // 返回
        return settlement.getId();
    }

    @Override
    public void updateSettlement(SettlementSaveReqVO updateReqVO) {
        // 校验存在
        validateSettlementExists(updateReqVO.getId());
        // 更新
        SettlementDO updateObj = BeanUtils.toBean(updateReqVO, SettlementDO.class);
        settlementMapper.updateById(updateObj);
    }

    @Override
    public void deleteSettlement(Long id) {
        // 校验存在
        validateSettlementExists(id);
        // 删除
        settlementMapper.deleteById(id);
    }

    private void validateSettlementExists(Long id) {
        if (settlementMapper.selectById(id) == null) {
            throw exception(SETTLEMENT_NOT_EXISTS);
        }
    }

    @Override
    public SettlementDO getSettlement(Long id) {
        return settlementMapper.selectById(id);
    }

    @Override
    public PageResult<SettlementDO> getSettlementPage(SettlementPageReqVO pageReqVO) {
        return settlementMapper.selectPage(pageReqVO);
    }

}
