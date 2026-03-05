package cn.iocoder.yudao.module.industry.service.park.asset.resourceaccount;

import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.asset.resourceaccount.vo.ParkResourceAccountSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.industry.dal.dataobject.park.asset.resourceaccount.ParkResourceAccountDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.industry.dal.mysql.park.asset.resourceaccount.ParkResourceAccountMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 资源台账 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class ParkResourceAccountServiceImpl implements ParkResourceAccountService {

    @Resource
    private ParkResourceAccountMapper parkResourceAccountMapper;

    @Override
    public Long createParkResourceAccount(ParkResourceAccountSaveReqVO createReqVO) {
        // 插入
        ParkResourceAccountDO parkResourceAccount = BeanUtils.toBean(createReqVO, ParkResourceAccountDO.class);
        parkResourceAccountMapper.insert(parkResourceAccount);
        // 返回
        return parkResourceAccount.getId();
    }

    @Override
    public void updateParkResourceAccount(ParkResourceAccountSaveReqVO updateReqVO) {
        // 校验存在
        validateParkResourceAccountExists(updateReqVO.getId());
        // 更新
        ParkResourceAccountDO updateObj = BeanUtils.toBean(updateReqVO, ParkResourceAccountDO.class);
        parkResourceAccountMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkResourceAccount(Long id) {
        // 校验存在
        validateParkResourceAccountExists(id);
        // 删除
        parkResourceAccountMapper.deleteById(id);
    }

    private void validateParkResourceAccountExists(Long id) {
        if (parkResourceAccountMapper.selectById(id) == null) {
            throw exception(PARK_RESOURCE_ACCOUNT_NOT_EXISTS);
        }
    }

    @Override
    public ParkResourceAccountDO getParkResourceAccount(Long id) {
        return parkResourceAccountMapper.selectById(id);
    }

    @Override
    public PageResult<ParkResourceAccountDO> getParkResourceAccountPage(ParkResourceAccountPageReqVO pageReqVO) {
        return parkResourceAccountMapper.selectPage(pageReqVO);
    }

}