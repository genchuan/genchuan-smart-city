package cn.iocoder.yudao.module.industry.service.park.order.parksettlement;

import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.order.parksettlement.vo.ParkSettlementSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.order.parksettlement.ParkSettlementDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.order.parksettlement.ParkSettlementMapper;
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
 * 分账结算表 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkSettlementServiceImpl implements ParkSettlementService {

    @Resource
    private ParkSettlementMapper parkSettlementMapper;

    @Override
    public Long createParkSettlement(ParkSettlementSaveReqVO createReqVO) {
        // 插入
        ParkSettlementDO parkSettlement = BeanUtils.toBean(createReqVO, ParkSettlementDO.class);
        parkSettlementMapper.insert(parkSettlement);
        // 返回
        return parkSettlement.getId();
    }

    @Override
    public void updateParkSettlement(ParkSettlementSaveReqVO updateReqVO) {
        // 校验存在
        validateParkSettlementExists(updateReqVO.getId());
        // 更新
        ParkSettlementDO updateObj = BeanUtils.toBean(updateReqVO, ParkSettlementDO.class);
        parkSettlementMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkSettlement(Long id) {
        // 校验存在
        validateParkSettlementExists(id);
        // 删除
        parkSettlementMapper.deleteById(id);
    }

    private void validateParkSettlementExists(Long id) {
        if (parkSettlementMapper.selectById(id) == null) {
            throw exception(PARK_SETTLEMENT_NOT_EXISTS);
        }
    }

    @Override
    public ParkSettlementDO getParkSettlement(Long id) {
        return parkSettlementMapper.selectById(id);
    }

    @Override
    public PageResult<ParkSettlementDO> getParkSettlementPage(ParkSettlementPageReqVO pageReqVO) {
        return parkSettlementMapper.selectPage(pageReqVO);
    }

}
