package cn.iocoder.yudao.module.industry.service.park.pay.parkpay;

import cn.iocoder.yudao.framework.common.exception.ErrorCode;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.ParkPayPageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.ParkPayPayReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.pay.parkpay.vo.ParkPaySaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.pay.parkpay.ParkPayDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.pay.parkpay.ParkPayMapper;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;



import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.industry.enums.ErrorCodeConstants.*;

/**
 * 停车缴费服务 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkPayServiceImpl implements ParkPayService {

    @Resource
    private ParkPayMapper parkPayMapper;

    @Override
    public Long createParkPay(ParkPaySaveReqVO createReqVO) {
        // 插入
        ParkPayDO parkPay = BeanUtils.toBean(createReqVO, ParkPayDO.class);
        parkPayMapper.insert(parkPay);
        // 返回
        return parkPay.getId();
    }

    @Override
    public void updateParkPay(ParkPaySaveReqVO updateReqVO) {
        // 校验存在
        validateParkPayExists(updateReqVO.getId());
        // 更新
        ParkPayDO updateObj = BeanUtils.toBean(updateReqVO, ParkPayDO.class);
        parkPayMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkPay(Long id) {
        // 校验存在
        validateParkPayExists(id);
        // 删除
        parkPayMapper.deleteById(id);
    }

    private void validateParkPayExists(Long id) {
        if (parkPayMapper.selectById(id) == null) {
            throw exception(PARK_PAY_NOT_EXISTS);
        }
    }

    @Override
    public ParkPayDO getParkPay(Long id) {
        return parkPayMapper.selectById(id);
    }

    @Override
    public PageResult<ParkPayDO> getParkPayPage(ParkPayPageReqVO pageReqVO) {
        return parkPayMapper.selectPage(pageReqVO);
    }

    @Override
    public void pay(ParkPayPayReqVO reqVO) {
        // 1. 查询缴费记录
        ParkPayDO pay = parkPayMapper.selectById(reqVO.getId());
        if (pay == null) {
            throw exception(new ErrorCode(500,"缴费订单不存在"));
        }

        // 2. 校验支付状态（避免重复支付）
        if (!"待支付".equals(pay.getPayStatus())) {
            throw exception(new ErrorCode(500,"该订单无法支付"));
        }

        // 3. 更新支付信息
        ParkPayDO update = new ParkPayDO();
        update.setId(pay.getId());
        update.setPayStatus("支付成功");
        update.setPayMethod(reqVO.getPayMethod());
        update.setActualPayAmount(reqVO.getActualPayAmount());
        update.setPayTime(LocalDateTime.now());

        parkPayMapper.updateById(update);
    }


}
