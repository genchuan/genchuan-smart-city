package cn.iocoder.yudao.module.chargepark.marketop.service.couponactivity.couponmgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.couponmgmt.vo.CouponMgmtUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.CouponMgmtDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity.CouponMgmtMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class CouponMgmtServiceImpl implements CouponMgmtService {

    @Resource
    private CouponMgmtMapper couponMgmtMapper;

    @Override
    public PageResult<CouponMgmtDO> getPage(CouponMgmtPageReqVO reqVO) {
        return couponMgmtMapper.selectPage(reqVO);
    }

    @Override
    public CouponMgmtDO get(Long id) {
        return couponMgmtMapper.selectById(id);
    }

    @Override
    public Long create(CouponMgmtCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        CouponMgmtDO couponMgmt = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmt.setStatus("未领取");
        couponMgmtMapper.insert(couponMgmt);
        return couponMgmt.getId();
    }

    @Override
    public void update(CouponMgmtUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        CouponMgmtDO updateObj = BeanUtils.toBean(reqVO, CouponMgmtDO.class);
        couponMgmtMapper.updateById(updateObj);
    }

    @Override
    public void send(Long id, java.util.List<Long> userIds) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"未领取".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        couponMgmt.setStatus("已领取");
        couponMgmt.setSenderId(id); // 发放人为当前操作用户，由Controller层设置
        couponMgmt.setSendTime(LocalDateTime.now());
        if (!userIds.isEmpty()) {
            couponMgmt.setReceiverId(userIds.get(0));
        }
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public void verify(Long id) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"已领取".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        couponMgmt.setStatus("已使用");
        couponMgmt.setVerifyTime(LocalDateTime.now());
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public void resend(Long id) {
        CouponMgmtDO couponMgmt = validateExists(id);
        if (!"已过期".equals(couponMgmt.getStatus())) {
            throw exception(COUPON_MGMT_STATUS_ERROR);
        }
        couponMgmt.setStatus("未领取");
        couponMgmt.setSenderId(null);
        couponMgmt.setSendTime(null);
        couponMgmt.setReceiverId(null);
        couponMgmtMapper.updateById(couponMgmt);
    }

    @Override
    public CouponMgmtChartRespVO getChart(String timeRange) {
        // TODO: 实现图表统计逻辑，暂时返回空数据
        CouponMgmtChartRespVO respVO = new CouponMgmtChartRespVO();
        respVO.setSendCount(0);
        respVO.setVerifyRate(java.math.BigDecimal.ZERO);
        respVO.setTrendList(new ArrayList<>());
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private CouponMgmtDO validateExists(Long id) {
        CouponMgmtDO couponMgmt = couponMgmtMapper.selectById(id);
        if (couponMgmt == null) {
            throw exception(COUPON_MGMT_NOT_EXISTS);
        }
        return couponMgmt;
    }

    private void validateNameUnique(Long id, String name) {
        CouponMgmtDO existing = couponMgmtMapper.selectOne(CouponMgmtDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(COUPON_MGMT_NAME_EXISTS);
        }
    }

}
