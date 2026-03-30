package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferalarm;

import cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.GarbageTransferMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferAlarmMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferAlarmCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.garbagetransfer.GarbageTransferService;
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
    private GarbageTransferService garbageTransferService;

    @Resource
    private TransferAlarmCodeGenerator codeGenerator;

    @Override
    @Transactional(rollbackFor = Exception.class) // 新增事务，确保原子性
    public Long createTransferAlarm(TransferAlarmSaveReqVO createReqVO) {
        // 1. 校验transferId是否存在
        garbageTransferService.validateTransferIdExists(createReqVO.getTransferId());

        // 2. 转换并设置预警ID
        TransferAlarmDO transferAlarm = BeanUtils.toBean(createReqVO, TransferAlarmDO.class);
        transferAlarm.setId(null);
        transferAlarm.setAlarmId(codeGenerator.generateAlarmId());

        // 3. 插入预警数据
        transferAlarmMapper.insert(transferAlarm);

        // 4. 更新garbage_transfer表的未处理预警数+1
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
    public void deleteTransferAlarm(Long id) {
        // 校验存在
        validateTransferAlarmExists(id);

        // 1. 先获取预警信息（拿到 transferId）
        TransferAlarmDO alarm = getTransferAlarm(id);
        if (alarm == null) {
            throw exception(TRANSFER_ALARM_NOT_EXISTS);
        }

        // 2. 删除预警
        transferAlarmMapper.deleteById(id);

        // 3. 垃圾转运站未处理数量 -1
        garbageTransferService.decrementUnhandledAlarmCount(alarm.getTransferId());
    }

    private void validateTransferAlarmExists(Long id) {
        if (transferAlarmMapper.selectById(id) == null) {
            throw exception(TRANSFER_ALARM_NOT_EXISTS);
        }
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

        // 4. 垃圾转运站未处理预警数减1
        garbageTransferService.decrementUnhandledAlarmCount(alarmDO.getTransferId());
    }
}