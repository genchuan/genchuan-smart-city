package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.detail.TransferOperationDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferOperationMapper;
import cn.iocoder.yudao.module.envirhealth.util.codegenerator.garbagetransfer.TransferOperationCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_OPERATION_NOT_EXISTS;

/**
 * 转运作业 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferOperationServiceImpl implements TransferOperationService {

    @Resource
    private TransferOperationMapper transferOperationMapper;

    @Resource
    private TransferOperationCodeGenerator codeGenerator;

    @Override
    public Long createTransferOperation(TransferOperationSaveReqVO createReqVO) {
        // 插入
        TransferOperationDO transferOperation = BeanUtils.toBean(createReqVO, TransferOperationDO.class);

        transferOperation.setOperationId(codeGenerator.generateOperationId());

        transferOperationMapper.insert(transferOperation);
        // 返回
        return transferOperation.getId();
    }

    @Override
    public void updateTransferOperation(TransferOperationSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferOperationExists(updateReqVO.getId());
        // 更新
        TransferOperationDO updateObj = BeanUtils.toBean(updateReqVO, TransferOperationDO.class);
        transferOperationMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferOperation(Long id) {
        // 校验存在
        validateTransferOperationExists(id);
        // 删除
        transferOperationMapper.deleteById(id);
    }

    private void validateTransferOperationExists(Long id) {
        if (transferOperationMapper.selectById(id) == null) {
            throw exception(TRANSFER_OPERATION_NOT_EXISTS);
        }
    }

    @Override
    public TransferOperationDO getTransferOperation(Long id) {
        return transferOperationMapper.selectById(id);
    }

    @Override
    public PageResult<TransferOperationDO> getTransferOperationPage(TransferOperationPageReqVO pageReqVO) {
        return transferOperationMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferOperationDetailDO> getTransferOperationDetailPage(TransferOperationPageReqVO pageReqVO) {
        Long total = transferOperationMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferOperationDetailDO> list = transferOperationMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}