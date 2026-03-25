package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transfermaintenance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferMaintenanceMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferMaintenanceCodeGenerator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_MAINTENANCE_NOT_EXISTS;

/**
 * 设备维护 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferMaintenanceServiceImpl implements TransferMaintenanceService {

    @Resource
    private TransferMaintenanceMapper transferMaintenanceMapper;

    @Resource
    private TransferMaintenanceCodeGenerator codeGenerator;

    @Override
    public Long createTransferMaintenance(TransferMaintenanceSaveReqVO createReqVO) {
        // 插入
        TransferMaintenanceDO transferMaintenance = BeanUtils.toBean(createReqVO, TransferMaintenanceDO.class);

        transferMaintenance.setMaintenanceId(codeGenerator.generateMaintainId());

        transferMaintenanceMapper.insert(transferMaintenance);
        // 返回
        return transferMaintenance.getId();
    }

    @Override
    public void updateTransferMaintenance(TransferMaintenanceSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferMaintenanceExists(updateReqVO.getId());
        // 更新
        TransferMaintenanceDO updateObj = BeanUtils.toBean(updateReqVO, TransferMaintenanceDO.class);
        transferMaintenanceMapper.updateById(updateObj);
    }

    @Override
    public void deleteTransferMaintenance(Long id) {
        // 校验存在
        validateTransferMaintenanceExists(id);
        // 删除
        transferMaintenanceMapper.deleteById(id);
    }

    private void validateTransferMaintenanceExists(Long id) {
        if (transferMaintenanceMapper.selectById(id) == null) {
            throw exception(TRANSFER_MAINTENANCE_NOT_EXISTS);
        }
    }

    @Override
    public TransferMaintenanceDO getTransferMaintenance(Long id) {
        return transferMaintenanceMapper.selectById(id);
    }

    @Override
    public PageResult<TransferMaintenanceDO> getTransferMaintenancePage(TransferMaintenancePageReqVO pageReqVO) {
        return transferMaintenanceMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferMaintenanceDetailDO> getTransferMaintenanceDetailPage(TransferMaintenancePageReqVO pageReqVO) {
        Long total = transferMaintenanceMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferMaintenanceDetailDO> list = transferMaintenanceMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public TransferMaintenanceDashboardRespVO getMaintenanceDashboard() {
        TransferMaintenanceDashboardRespVO respVO = new TransferMaintenanceDashboardRespVO();

        // 1. 卡片数据
        respVO.setTotalPendingMaintenance(transferMaintenanceMapper.selectTotalPendingMaintenance());
        respVO.setTypePendingMaintenance(transferMaintenanceMapper.selectTypePendingMaintenance());
        respVO.setTimeoutUnmaintainedCount(transferMaintenanceMapper.selectTimeoutUnmaintainedCount());

        // 2. 圆环图数据
        respVO.setEquipmentTypeDistribution(transferMaintenanceMapper.selectEquipmentTypeDistribution());
        respVO.setMaintenanceStatusDistribution(transferMaintenanceMapper.selectMaintenanceStatusDistribution());

        // 3. 柱状图数据
        respVO.setStationPendingMaintenanceComparison(transferMaintenanceMapper.selectStationPendingMaintenanceComparison());

        return respVO;
    }
}