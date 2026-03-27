package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationCompletedDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferOperationMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferOperationCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection.GarbageCollectionService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

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
    private GarbageCollectionService garbageCollectionService;

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Resource
    private TransferOperationCodeGenerator codeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class) // 新增事务，和预约一致
    public Long createTransferOperation(TransferOperationSaveReqVO createReqVO) {
        // 插入
        TransferOperationDO transferOperation = BeanUtils.toBean(createReqVO, TransferOperationDO.class);
        transferOperation.setId(null);
        transferOperation.setOperationId(codeGenerator.generateOperationId());

        transferOperationMapper.insert(transferOperation);

        // ======================== 同步新增 garbage_transfer 数据 ========================
        Long newOperationId = transferOperation.getId();
        String transferId = transferOperation.getTransferId();
        syncAddNewGarbageTransferByOperation(transferId, newOperationId);

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
    @Transactional(rollbackFor = Exception.class) // 新增事务
    public void deleteTransferOperation(Long id) {
        // 1. 校验存在，拿到作业信息
        TransferOperationDO operation = validateTransferOperationExists(id);
        String transferId = operation.getTransferId();

        // 2. 同步删除 garbage_transfer 对应数据
        syncDeleteTransferByOperationId(transferId, id);

        // 3. 删除作业
        transferOperationMapper.deleteById(id);
    }

    // 改成返回DO，和预约的validate一致，方便拿数据
    private TransferOperationDO validateTransferOperationExists(Long id) {
        TransferOperationDO operation = transferOperationMapper.selectById(id);
        if (operation == null) {
            throw exception(TRANSFER_OPERATION_NOT_EXISTS);
        }
        return operation;
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

    @Override
    public TransferOperationDashboardVO getDashboardStats() {
        TransferOperationDashboardVO dashboardVO = new TransferOperationDashboardVO();

        // 获取当前作业总数（所有未删除的作业）
        Long totalCount = transferOperationMapper.selectTotalCount();

        // 获取正常运行数（非异常）
        Long normalCount = transferOperationMapper.selectNormalCount();

        // 获取异常标记数
        Long abnormalCount = transferOperationMapper.selectAbnormalCount();

        dashboardVO.setTotalCount(totalCount);
        dashboardVO.setNormalCount(normalCount);
        dashboardVO.setAbnormalCount(abnormalCount);

        return dashboardVO;
    }

    @Override
    public TransferOperationCompletedDashboardVO getTransferOperationDashboard(String timeDimension) {
        TransferOperationCompletedDashboardVO respVO = new TransferOperationCompletedDashboardVO();

        // 默认按日统计
        String finalDimension = (timeDimension == null || !List.of("day", "week", "month").contains(timeDimension))
                ? "day" : timeDimension;

        // 1. 填充卡片数据
        respVO.setTotalCompletedTasks(transferOperationMapper.selectTotalCompletedTasks());
        respVO.setTotalInboundVolume(transferOperationMapper.selectTotalInboundVolume());
//        respVO.setEquipmentHealthRate(transferOperationMapper.selectEquipmentHealthRate());
        respVO.setEnvironmentComplianceRate(transferOperationMapper.selectEnvironmentComplianceRate());

        // 2. 填充柱状图：按时间维度进站量
        List<BarItemVO> inboundVolumeData;
        switch (finalDimension) {
            case "week":
                // 调用按周统计的方法
                inboundVolumeData = transferOperationMapper.selectInboundVolumeByWeek();
                break;
            case "month":
                // 调用按月统计的方法
                inboundVolumeData = transferOperationMapper.selectInboundVolumeByMonth();
                break;
            default: // day（默认）
                // 调用按日统计的方法
                inboundVolumeData = transferOperationMapper.selectInboundVolumeByDay();
                break;
        }
        respVO.setInboundVolumeByTimeDimension(inboundVolumeData);

        // 3. 填充折线图：设备完好率近30天趋势
//        respVO.setEquipmentHealthRateTrend(transferOperationMapper.selectEquipmentHealthRateTrend());

        // 4. 填充圆环图：任务类型占比 + 转运站完成量占比
        respVO.setTaskTypeDistribution(transferOperationMapper.selectTaskTypeDistribution());
        respVO.setStationCompletionDistribution(transferOperationMapper.selectStationCompletionDistribution());

        return respVO;
    }

    @Override
    public void pauseTransferOperation(Long operationId, String pauseStatusId) {
        // 1. 校验转运作业是否存在
        TransferOperationDO operation = getTransferOperation(operationId);
        if (operation == null) {
            throw exception(TRANSFER_OPERATION_NOT_EXISTS);
        }

        // 2. 获取转运作业关联的planId
        String planId = operation.getPlanId();
        if (planId == null || planId.isEmpty()) {
            throw exception(TRANSFER_OPERATION_PLAN_ID_EMPTY);
        }

        // 3. 更新收运计划状态为「已暂停」
        garbageCollectionService.updatePlanStatus(planId, pauseStatusId);

        //4. 更新垃圾收运表状态为「已暂停」
        TransferOperationDO updateOperation = new TransferOperationDO();
        updateOperation.setId(operationId);
        updateOperation.setOperationStatus("暂停"); // 作业状态设为暂停
        transferOperationMapper.updateById(updateOperation);
    }

    @Override
    public void startTransferOperation(Long operationId, String startStatusId) {
        // 1. 校验转运作业是否存在
        TransferOperationDO operation = getTransferOperation(operationId);
        if (operation == null) {
            throw exception(TRANSFER_OPERATION_NOT_EXISTS);
        }

        String currentStatus = operation.getOperationStatus();
        if (!"暂停".equals(currentStatus)) {
            throw exception(TRANSFER_OPERATION_CANNOT_START_NOT_PAUSED); // 自定义异常：只有暂停状态可启动
        }

        // 2. 获取转运作业关联的planId
        String planId = operation.getPlanId();
        if (planId == null || planId.isEmpty()) {
            throw exception(TRANSFER_OPERATION_PLAN_ID_EMPTY);
        }

        // 3. 更新收运计划状态为「已启动」
        garbageCollectionService.updatePlanStatus(planId, startStatusId);

        //4. 更新垃圾收运表状态为「已启动」
        TransferOperationDO updateOperation = new TransferOperationDO();
        updateOperation.setId(operationId);
        updateOperation.setOperationStatus("运行"); // 作业状态设为运行
        transferOperationMapper.updateById(updateOperation);
    }

    /**
     * 新增作业时 → 新增一条 garbage_transfer 记录
     * 1. 从现有 transferId 复制基础数据
     * 2. reserve_id 设为当前作业ID
     * 3. 状态改为 作业待启动（可根据业务调整）
     */
    private void syncAddNewGarbageTransferByOperation(String transferId, Long operationId) {
        if (transferId == null || operationId == null) {
            return;
        }

        // 1. 查询该转运站的任意一条原始数据（用来复制）
        GarbageTransferDO original = garbageTransferMapper.selectOne(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .last("LIMIT 1")
        );
        if (original == null) {
            return;
        }

        // 2. 复制基础信息
        GarbageTransferDO newTransfer = BeanUtils.toBean(original, GarbageTransferDO.class);
        newTransfer.setId(null); // 清空ID，自动生成新主键

        // 3. 设置关键数据
        newTransfer.setReserveId(operationId);        // 绑定本次作业ID
        newTransfer.setProgressStatus("作业待启动"); // 固定状态，可根据业务修改

        // 4. 插入新记录
        garbageTransferMapper.insert(newTransfer);
    }

    /**
     * 根据 transferId + operationId 删除对应的 garbage_transfer 记录
     */
    private void syncDeleteTransferByOperationId(String transferId, Long operationId) {
        if (transferId == null || operationId == null) {
            return;
        }
        garbageTransferMapper.delete(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getReserveId, operationId)
        );
    }
}