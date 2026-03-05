package cn.iocoder.yudao.module.industry.service.park.user.parkmerchant;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkmerchant.vo.ParkMerchantSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkmerchant.ParkMerchantDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkmerchant.ParkMerchantMapper;
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
 * 商户 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkMerchantServiceImpl implements ParkMerchantService {

    @Resource
    private ParkMerchantMapper parkMerchantMapper;

    @Override
    public Long createParkMerchant(ParkMerchantSaveReqVO createReqVO) {
        // 插入
        ParkMerchantDO parkMerchant = BeanUtils.toBean(createReqVO, ParkMerchantDO.class);
        parkMerchantMapper.insert(parkMerchant);
        // 返回
        return parkMerchant.getId();
    }

    @Override
    public void updateParkMerchant(ParkMerchantSaveReqVO updateReqVO) {
        // 校验存在
        validateParkMerchantExists(updateReqVO.getId());
        // 更新
        ParkMerchantDO updateObj = BeanUtils.toBean(updateReqVO, ParkMerchantDO.class);
        parkMerchantMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkMerchant(Long id) {
        // 校验存在
        validateParkMerchantExists(id);
        // 删除
        parkMerchantMapper.deleteById(id);
    }

    private void validateParkMerchantExists(Long id) {
        if (parkMerchantMapper.selectById(id) == null) {
            throw exception(PARK_MERCHANT_NOT_EXISTS);
        }
    }

    @Override
    public ParkMerchantDO getParkMerchant(Long id) {
        return parkMerchantMapper.selectById(id);
    }

    @Override
    public PageResult<ParkMerchantDO> getParkMerchantPage(ParkMerchantPageReqVO pageReqVO) {
        return parkMerchantMapper.selectPage(pageReqVO);
    }

}
