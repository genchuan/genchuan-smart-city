package cn.iocoder.yudao.module.industry.service.park.pay.parkarrearsrecovery;

import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoveryPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkarrearsrecovery.vo.ParkArrearsRecoverySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkarrearsrecovery.ParkArrearsRecoveryDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkarrearsrecovery.ParkArrearsRecoveryMapper;
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
 * 欠费追缴 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkArrearsRecoveryServiceImpl implements ParkArrearsRecoveryService {

    @Resource
    private ParkArrearsRecoveryMapper parkArrearsRecoveryMapper;

    @Override
    public Long createParkArrearsRecovery(ParkArrearsRecoverySaveReqVO createReqVO) {
        // 插入
        ParkArrearsRecoveryDO parkArrearsRecovery = BeanUtils.toBean(createReqVO, ParkArrearsRecoveryDO.class);
        parkArrearsRecoveryMapper.insert(parkArrearsRecovery);
        // 返回
        return parkArrearsRecovery.getId();
    }

    @Override
    public void updateParkArrearsRecovery(ParkArrearsRecoverySaveReqVO updateReqVO) {
        // 校验存在
        validateParkArrearsRecoveryExists(updateReqVO.getId());
        // 更新
        ParkArrearsRecoveryDO updateObj = BeanUtils.toBean(updateReqVO, ParkArrearsRecoveryDO.class);
        parkArrearsRecoveryMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkArrearsRecovery(Long id) {
        // 校验存在
        validateParkArrearsRecoveryExists(id);
        // 删除
        parkArrearsRecoveryMapper.deleteById(id);
    }

    private void validateParkArrearsRecoveryExists(Long id) {
        if (parkArrearsRecoveryMapper.selectById(id) == null) {
            throw exception(PARK_ARREARS_RECOVERY_NOT_EXISTS);
        }
    }

    @Override
    public ParkArrearsRecoveryDO getParkArrearsRecovery(Long id) {
        return parkArrearsRecoveryMapper.selectById(id);
    }

    @Override
    public PageResult<ParkArrearsRecoveryDO> getParkArrearsRecoveryPage(ParkArrearsRecoveryPageReqVO pageReqVO) {
        return parkArrearsRecoveryMapper.selectPage(pageReqVO);
    }

}
