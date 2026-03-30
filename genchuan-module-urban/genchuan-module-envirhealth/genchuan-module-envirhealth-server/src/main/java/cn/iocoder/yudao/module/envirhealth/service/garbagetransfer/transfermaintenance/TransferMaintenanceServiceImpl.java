package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transfermaintenance;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenancePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transfermaintenance.TransferMaintenanceSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferMaintenanceDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferMaintenanceMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferMaintenanceCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer.GarbageTransferService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

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
    private GarbageTransferService garbageTransferService;

    @Resource
    private TransferMaintenanceCodeGenerator codeGenerator;

    // 新增：注入 garbageTransferMapper
    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) // 加事务
    public Long createTransferMaintenance(TransferMaintenanceSaveReqVO createReqVO) {
        // 1. 校验转运站是否存在
        garbageTransferService.validateTransferIdExists(createReqVO.getTransferId());

        // 2. 转换并生成维修单号
        TransferMaintenanceDO transferMaintenance = BeanUtils.toBean(createReqVO, TransferMaintenanceDO.class);
        transferMaintenance.setId(null);
        transferMaintenance.setMaintenanceId(codeGenerator.generateMaintainId());
        transferMaintenance.setMaintenanceStatus("待维修"); // 初始化状态

        // 3. 插入维修单
        transferMaintenanceMapper.insert(transferMaintenance);
        Long maintenanceId = transferMaintenance.getId();

        // 4. 同步新增 garbage_transfer 记录
        syncAddNewGarbageTransferByMaintenance(createReqVO.getTransferId(), maintenanceId);

        // 5. 待维修数量 +1
        garbageTransferService.incrementPendingMaintenanceCount(createReqVO.getTransferId());

        return maintenanceId;
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
    @Transactional(rollbackFor = Exception.class) // 加事务
    public void deleteTransferMaintenance(Long id) {
        // 校验存在
        TransferMaintenanceDO maintenance = validateTransferMaintenanceExists(id);
        String transferId = maintenance.getTransferId();

        // 1. 删除 garbage_transfer 中对应这条维护单的记录
        syncDeleteTransferByMaintenanceId(transferId, id);

        // 2. 删除维修单
        transferMaintenanceMapper.deleteById(id);

        // 3. 待维修数量 -1
        garbageTransferService.decrementPendingMaintenanceCount(transferId);
    }

    /**
     * 校验维护单是否存在，并返回 DO
     */
    private TransferMaintenanceDO validateTransferMaintenanceExists(Long id) {
        TransferMaintenanceDO maintenance = transferMaintenanceMapper.selectById(id);
        if (maintenance == null) {
            throw exception(TRANSFER_MAINTENANCE_NOT_EXISTS);
        }
        return maintenance;
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

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reviewTransferMaintenance(Long maintenanceId, String result) {
        // 1. 校验维护单是否存在
        TransferMaintenanceDO maintenance = validateTransferMaintenanceExists(maintenanceId);
        String transferId = maintenance.getTransferId();

        // 只有 维护中 状态，才允许验收
        if (!"维护中".equals(maintenance.getMaintenanceStatus())) {
            throw exception(MAINTENANCE_NOT_IN_REPAIRING);
        }

        // 2. 根据验收结果更新状态
        if ("合格".equals(result)) {
            // 合格 → 状态改为【已完成】
            maintenance.setMaintenanceStatus("已完成");
            // 删除对应的 garbage_transfer 记录
            syncDeleteTransferByMaintenanceId(transferId, maintenanceId);
            // 待维护数量 -1
            garbageTransferService.decrementPendingMaintenanceCount(transferId);
        } else if ("不合格".equals(result)) {
            // 不合格 → 状态改为【待维修】
            maintenance.setMaintenanceStatus("待维修");
        } else {
            // 非法参数
            throw exception(UNKNOWN_REVIEW_RESULT);
        }

        // 3. 更新数据库
        transferMaintenanceMapper.updateById(maintenance);
    }

    /**
     * 新增维护单时 → 新增一条 garbage_transfer 记录
     */
    private void syncAddNewGarbageTransferByMaintenance(String transferId, Long maintenanceId) {
        if (transferId == null || maintenanceId == null) {
            return;
        }

        GarbageTransferDO newTransfer;
        // 1. 优先：查询该转运站的任意一条原始数据
        GarbageTransferDO original = garbageTransferMapper.selectOne(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getDeleted, 0)
                        .last("LIMIT 1")
        );

        if (original != null) {
            newTransfer = BeanUtils.toBean(original, GarbageTransferDO.class);
            newTransfer.setId(null);
            newTransfer.setReserveId(null);
            newTransfer.setAlarmId(null);
            newTransfer.setOperationId(null);
            newTransfer.setMaintenanceId(null);
        } else {
            // 无数据 → 手动新建对象
            newTransfer = new GarbageTransferDO();
            newTransfer.setTransferId(transferId); // 只填必须的转运站ID
        }

        // 2. 统一设置：维护单关联信息
        newTransfer.setMaintenanceId(maintenanceId);
        newTransfer.setProgressStatus("维护待处理");

        // 3. 插入新记录
        garbageTransferMapper.insert(newTransfer);
    }

    /**
     * 根据 transferId + maintenanceId 删除对应的 garbage_transfer 记录
     */
    private void syncDeleteTransferByMaintenanceId(String transferId, Long maintenanceId) {
        if (transferId == null || maintenanceId == null) {
            return;
        }
        garbageTransferMapper.delete(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getMaintenanceId, maintenanceId)
        );
    }
}