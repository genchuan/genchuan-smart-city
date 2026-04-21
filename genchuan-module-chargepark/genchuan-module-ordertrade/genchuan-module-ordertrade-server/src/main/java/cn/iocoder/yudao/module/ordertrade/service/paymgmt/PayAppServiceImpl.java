package cn.iocoder.yudao.module.ordertrade.service.paymgmt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.ordertrade.controller.admin.paymgmt.vo.*;
import cn.iocoder.yudao.module.ordertrade.dal.dataobject.paymgmt.PayAppDO;
import cn.iocoder.yudao.module.ordertrade.dal.mysql.paymgmt.PayAppMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.ordertrade.enums.ErrorCodeConstants.*;

@Service
@Validated
public class PayAppServiceImpl implements PayAppService {

    @Resource
    private PayAppMapper payAppMapper;

    @Override
    public Long createPayApp(PayAppSaveReqVO createReqVO) {
        PayAppDO obj = BeanUtils.toBean(createReqVO, PayAppDO.class);
        if (obj.getStatus() == null) {
            obj.setStatus(0);
        }
        payAppMapper.insert(obj);
        return obj.getId();
    }

    @Override
    public void updatePayApp(PayAppSaveReqVO updateReqVO) {
        validateExists(updateReqVO.getId());
        payAppMapper.updateById(BeanUtils.toBean(updateReqVO, PayAppDO.class));
    }

    @Override
    public void deletePayApp(Long id) {
        validateExists(id);
        payAppMapper.deleteById(id);
    }

    @Override
    public PayAppDO getPayApp(Long id) {
        return payAppMapper.selectById(id);
    }

    @Override
    public PageResult<PayAppDO> getPayAppPage(PayAppPageReqVO pageReqVO) {
        return payAppMapper.selectPage(pageReqVO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void enablePayApp(Long id) {
        PayAppDO app = payAppMapper.selectById(id);
        if (app == null) throw exception(PAY_APP_NOT_EXISTS);
        if (Integer.valueOf(1).equals(app.getStatus())) throw exception(PAY_APP_STATUS_CANNOT_ENABLE);
        PayAppDO update = new PayAppDO();
        update.setId(id);
        update.setStatus(1);
        payAppMapper.updateById(update);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void disablePayApp(Long id) {
        PayAppDO app = payAppMapper.selectById(id);
        if (app == null) throw exception(PAY_APP_NOT_EXISTS);
        if (Integer.valueOf(0).equals(app.getStatus())) throw exception(PAY_APP_STATUS_CANNOT_DISABLE);
        PayAppDO update = new PayAppDO();
        update.setId(id);
        update.setStatus(0);
        payAppMapper.updateById(update);
    }

    @Override
    public PayAppChartRespVO getPayAppChart(PayAppChartReqVO chartReqVO) {
        PayAppChartRespVO resp = new PayAppChartRespVO();
        resp.setEnabledCount(payAppMapper.selectEnabledCount());
        resp.setTotalCount(payAppMapper.selectTotalCount());
        return resp;
    }

    private void validateExists(Long id) {
        if (payAppMapper.selectById(id) == null) throw exception(PAY_APP_NOT_EXISTS);
    }
}
