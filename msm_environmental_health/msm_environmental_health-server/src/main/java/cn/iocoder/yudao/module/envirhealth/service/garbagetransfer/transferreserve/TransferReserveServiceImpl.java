package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferreserve;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReservePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferreserve.TransferReserveSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferReserveDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferReserveDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferReserveMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 进站预约 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferReserveServiceImpl implements TransferReserveService {

    @Resource
    private TransferReserveMapper transferReserveMapper;

    @Override
    public Long createTransferReserve(TransferReserveSaveReqVO createReqVO) {
        // 插入
        TransferReserveDO transferReserve = BeanUtils.toBean(createReqVO, TransferReserveDO.class);
        transferReserveMapper.insert(transferReserve);
        // 返回
        return transferReserve.getId();
    }

    @Override
    public void updateTransferReserve(TransferReserveSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferReserveExists(updateReqVO.getId());
        // 更新
        TransferReserveDO updateObj = BeanUtils.toBean(updateReqVO, TransferReserveDO.class);
        transferReserveMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferReserve(Long id) {
        // 校验存在
        validateTransferReserveExists(id);
        // 删除
        transferReserveMapper.deleteById(id);
    }

    private void validateTransferReserveExists(Long id) {
        if (transferReserveMapper.selectById(id) == null) {
            throw exception(TRANSFER_RESERVE_NOT_EXISTS);
        }
    }

    @Override
    public TransferReserveDO getTransferReserve(Long id) {
        return transferReserveMapper.selectById(id);
    }

    @Override
    public PageResult<TransferReserveDO> getTransferReservePage(TransferReservePageReqVO pageReqVO) {
        return transferReserveMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferReserveDetailDO> getTransferReserveDetailPage(TransferReservePageReqVO pageReqVO) {
        Long total = transferReserveMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferReserveDetailDO> list = transferReserveMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}