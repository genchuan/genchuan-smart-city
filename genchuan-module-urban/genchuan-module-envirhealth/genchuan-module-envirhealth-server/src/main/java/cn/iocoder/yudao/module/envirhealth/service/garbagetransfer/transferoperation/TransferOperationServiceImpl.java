package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferoperation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationCompletedDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationDashboardVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferoperation.TransferOperationSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferOperationDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferOperationMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferOperationCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbagecollection.GarbageCollectionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_OPERATION_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_OPERATION_PLAN_ID_EMPTY;

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
}