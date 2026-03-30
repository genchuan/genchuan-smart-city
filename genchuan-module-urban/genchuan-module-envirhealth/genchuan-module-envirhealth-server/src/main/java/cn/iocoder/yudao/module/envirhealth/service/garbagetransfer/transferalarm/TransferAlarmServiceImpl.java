package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferalarm;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.GarbageTransferDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferAlarmMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferAlarmCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer.GarbageTransferService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_ALARM_NOT_EXISTS;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_ID_NOT_EXISTS;

/**
 * 转运站预警 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class TransferAlarmServiceImpl implements TransferAlarmService {

    @Resource
    private TransferAlarmMapper transferAlarmMapper;

    @Resource
    private GarbageTransferMapper garbageTransferMapper;

    @Resource
    private GarbageTransferService garbageTransferService;

    @Resource
    private TransferAlarmCodeGenerator codeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class) // 加事务，原子性
    public Long createTransferAlarm(TransferAlarmSaveReqVO createReqVO) {
        // 1. 校验transferId是否存在
        garbageTransferService.validateTransferIdExists(createReqVO.getTransferId());

        // 2. 转换并设置预警ID
        TransferAlarmDO transferAlarm = BeanUtils.toBean(createReqVO, TransferAlarmDO.class);
        transferAlarm.setId(null);
        transferAlarm.setHandleStatus("待处置");
        transferAlarm.setAlarmId(codeGenerator.generateAlarmId());

        // 3. 插入预警数据
        transferAlarmMapper.insert(transferAlarm);
        Long newAlarmId = transferAlarm.getId();

        // 4. 新增一条 garbage_transfer 记录（对应这条预警）
        syncAddNewGarbageTransferByAlarm(createReqVO.getTransferId(), newAlarmId);

        // 5. 所有同 transfer_id 的 gt 记录，未处理预警数+1
        garbageTransferService.incrementUnhandledAlarmCount(createReqVO.getTransferId());

        // 返回主键
        return transferAlarm.getId();
    }

    @Override
    public void updateTransferAlarm(TransferAlarmSaveReqVO updateReqVO) {
        // 校验存在
        validateTransferAlarmExists(updateReqVO.getId());
        // 更新
        TransferAlarmDO updateObj = BeanUtils.toBean(updateReqVO, TransferAlarmDO.class);
        transferAlarmMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class) // 加事务
    public void deleteTransferAlarm(Long id) {
        // 1. 校验存在，拿到预警信息
        TransferAlarmDO alarm = validateTransferAlarmExists(id);
        String transferId = alarm.getTransferId();

        // 2. 删除 garbage_transfer 中对应这条预警的记录
        syncDeleteTransferByAlarmId(transferId, id);

        // 3. 删除预警
        transferAlarmMapper.deleteById(id);

        // 4. 所有同 transfer_id 的 gt 记录，未处理预警数-1
        garbageTransferService.decrementUnhandledAlarmCount(transferId);
    }

    private TransferAlarmDO validateTransferAlarmExists(Long id) {
        TransferAlarmDO alarm = transferAlarmMapper.selectById(id);
        if (alarm == null) {
            throw exception(TRANSFER_ALARM_NOT_EXISTS);
        }
        return alarm;
    }

    @Override
    public TransferAlarmDO getTransferAlarm(Long id) {
        return transferAlarmMapper.selectById(id);
    }

    @Override
    public PageResult<TransferAlarmDO> getTransferAlarmPage(TransferAlarmPageReqVO pageReqVO) {
        return transferAlarmMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<TransferAlarmDetailDO> getTransferAlarmDetailPage(TransferAlarmPageReqVO pageReqVO) {
        Long total = transferAlarmMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<TransferAlarmDetailDO> list = transferAlarmMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

    @Override
    public TransferAlarmDashboardRespVO getTransferAlarmDashboard() {
        TransferAlarmDashboardRespVO respVO = new TransferAlarmDashboardRespVO();

        // 卡片数据
        respVO.setTotalPendingAlarm(transferAlarmMapper.selectTotalPendingAlarm());
        respVO.setHighPriorityCount(transferAlarmMapper.selectHighPriorityCount());
        respVO.setTimeoutUnprocessedCount(transferAlarmMapper.selectTimeoutUnprocessedCount());

        // 圆环图数据
        List<PieItemVO> alarmTypeDistribution = transferAlarmMapper.selectAlarmTypeDistribution();
        respVO.setAlarmTypeDistribution(alarmTypeDistribution);

        List<PieItemVO> transferStationDistribution = transferAlarmMapper.selectTransferStationDistribution();
        respVO.setTransferStationDistribution(transferStationDistribution);

        // 柱状图数据
        List<BarItemVO> handlerPendingAlarmComparison = transferAlarmMapper.selectHandlerPendingAlarmComparison();
        respVO.setHandlerPendingAlarmComparison(handlerPendingAlarmComparison);

        return respVO;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void relieveTransferAlarm(Long alarmId) {
        // 1. 校验预警ID是否存在
        TransferAlarmDO alarmDO = transferAlarmMapper.selectById(alarmId);
        if (alarmDO == null) {
            throw ServiceExceptionUtil.exception(TRANSFER_ALARM_NOT_EXISTS);
        }

        // 2. 校验当前状态是否为未解除（避免重复操作）
        if ("已解除".equals(alarmDO.getHandleStatus())) {
            return; // 已解除则直接返回，无需处理
        }

        // 3. 更新预警状态为「已解除」
        TransferAlarmDO updateDO = new TransferAlarmDO();
        updateDO.setId(alarmId);
        updateDO.setHandleStatus("已解除");
        transferAlarmMapper.updateById(updateDO);

        // 4. 删除 garbage_transfer 中对应这条预警的记录
        syncDeleteTransferByAlarmId(alarmDO.getTransferId(), alarmId);

        // 5. 垃圾转运站未处理预警数减1
        garbageTransferService.decrementUnhandledAlarmCount(alarmDO.getTransferId());
    }

    /**
     * 新增预警时 → 新增一条 garbage_transfer 记录
     * 1. 从现有 transferId 复制基础数据
     * 2. alarmId 设为当前预警ID
     * 3. 状态改为 预警待处理
     */
    private void syncAddNewGarbageTransferByAlarm(String transferId, Long alarmId) {
        if (transferId == null || alarmId == null) {
            return;
        }

        GarbageTransferDO newTransfer;
        // 1. 查询该转运站的任意一条原始数据
        GarbageTransferDO original = garbageTransferMapper.selectOne(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getDeleted, 0)
                        .last("LIMIT 1")
        );

        if (original != null) {
            // 有数据 → 复制
            newTransfer = BeanUtils.toBean(original, GarbageTransferDO.class);
            newTransfer.setId(null);
            newTransfer.setReserveId(null);
            newTransfer.setAlarmId(null);
            newTransfer.setOperationId(null);
            newTransfer.setMaintenanceId(null);
        } else {
            // 无数据 → 手动新建
            newTransfer = new GarbageTransferDO();
            newTransfer.setTransferId(transferId); // 必须字段
        }

        // 3. 设置关键数据
        newTransfer.setAlarmId(alarmId);        // 绑定本次预警ID
        newTransfer.setProgressStatus("预警待处理"); // 固定状态

        // 4. 插入新记录
        garbageTransferMapper.insert(newTransfer);
    }

    /**
     * 根据 transferId + alarmId 删除对应的 garbage_transfer 记录
     */
    private void syncDeleteTransferByAlarmId(String transferId, Long alarmId) {
        if (transferId == null || alarmId == null) {
            return;
        }
        garbageTransferMapper.delete(
                new LambdaQueryWrapper<GarbageTransferDO>()
                        .eq(GarbageTransferDO::getTransferId, transferId)
                        .eq(GarbageTransferDO::getAlarmId, alarmId)
        );
    }
}