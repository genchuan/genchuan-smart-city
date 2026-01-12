package cn.iocoder.yudao.module.industry.service.park.user.parkpaymentproxy;

import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxyPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.user.parkpaymentproxy.vo.ParkPaymentProxySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.user.parkpaymentproxy.ParkPaymentProxyDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.user.parkpaymentproxy.ParkPaymentProxyMapper;
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
 * 代付规则 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPaymentProxyServiceImpl implements ParkPaymentProxyService {

    @Resource
    private ParkPaymentProxyMapper parkPaymentProxyMapper;

    @Override
    public Long createParkPaymentProxy(ParkPaymentProxySaveReqVO createReqVO) {
        // 插入
        ParkPaymentProxyDO parkPaymentProxy = BeanUtils.toBean(createReqVO, ParkPaymentProxyDO.class);
        parkPaymentProxyMapper.insert(parkPaymentProxy);
        // 返回
        return parkPaymentProxy.getId();
    }

    @Override
    public void updateParkPaymentProxy(ParkPaymentProxySaveReqVO updateReqVO) {
        // 校验存在
        validateParkPaymentProxyExists(updateReqVO.getId());
        // 更新
        ParkPaymentProxyDO updateObj = BeanUtils.toBean(updateReqVO, ParkPaymentProxyDO.class);
        parkPaymentProxyMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPaymentProxy(Long id) {
        // 校验存在
        validateParkPaymentProxyExists(id);
        // 删除
        parkPaymentProxyMapper.deleteById(id);
    }

    private void validateParkPaymentProxyExists(Long id) {
        if (parkPaymentProxyMapper.selectById(id) == null) {
            throw exception(PARK_PAYMENT_PROXY_NOT_EXISTS);
        }
    }

    @Override
    public ParkPaymentProxyDO getParkPaymentProxy(Long id) {
        return parkPaymentProxyMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPaymentProxyDO> getParkPaymentProxyPage(ParkPaymentProxyPageReqVO pageReqVO) {
        return parkPaymentProxyMapper.selectPage(pageReqVO);
    }

}
