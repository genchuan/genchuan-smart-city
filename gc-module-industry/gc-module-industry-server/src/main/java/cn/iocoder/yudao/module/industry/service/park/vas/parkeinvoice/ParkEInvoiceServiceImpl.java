package cn.iocoder.yudao.module.industry.service.park.vas.parkeinvoice;

import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoicePageReqVO;
import cn.iocoder.yudao.module.industry.controller.admin.park.vas.parkeinvoice.vo.ParkEInvoiceSaveReqVO;
import cn.iocoder.yudao.module.industry.dal.dataobject.park.vas.parkeinvoice.ParkEInvoiceDO;
import cn.iocoder.yudao.module.industry.dal.mysql.park.vas.parkeinvoice.ParkEInvoiceMapper;
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
 * 电子发票 Service 实现类
 *
 * @author lxs
 */
@Service
@Validated
public class ParkEInvoiceServiceImpl implements ParkEInvoiceService {

    @Resource
    private ParkEInvoiceMapper parkEInvoiceMapper;

    @Override
    public Long createParkEInvoice(ParkEInvoiceSaveReqVO createReqVO) {
        // 插入
        ParkEInvoiceDO parkEInvoice = BeanUtils.toBean(createReqVO, ParkEInvoiceDO.class);
        parkEInvoiceMapper.insert(parkEInvoice);
        // 返回
        return parkEInvoice.getId();
    }

    @Override
    public void updateParkEInvoice(ParkEInvoiceSaveReqVO updateReqVO) {
        // 校验存在
        validateParkEInvoiceExists(updateReqVO.getId());
        // 更新
        ParkEInvoiceDO updateObj = BeanUtils.toBean(updateReqVO, ParkEInvoiceDO.class);
        parkEInvoiceMapper.updateById(updateObj);
    }

    @Override
    public void deleteParkEInvoice(Long id) {
        // 校验存在
        validateParkEInvoiceExists(id);
        // 删除
        parkEInvoiceMapper.deleteById(id);
    }

    private void validateParkEInvoiceExists(Long id) {
        if (parkEInvoiceMapper.selectById(id) == null) {
            throw exception(PARK_E_INVOICE_NOT_EXISTS);
        }
    }

    @Override
    public ParkEInvoiceDO getParkEInvoice(Long id) {
        return parkEInvoiceMapper.selectById(id);
    }

    @Override
    public PageResult<ParkEInvoiceDO> getParkEInvoicePage(ParkEInvoicePageReqVO pageReqVO) {
        return parkEInvoiceMapper.selectPage(pageReqVO);
    }

}
