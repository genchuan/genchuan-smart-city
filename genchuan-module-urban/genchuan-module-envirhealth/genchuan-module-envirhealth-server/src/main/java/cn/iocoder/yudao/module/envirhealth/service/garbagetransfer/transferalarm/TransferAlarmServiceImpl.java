package cn.iocoder.yudao.module.envirhealth.service.garbagetransfer.transferalarm;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagetransfer.vo.transferalarm.TransferAlarmSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagetransfer.TransferAlarmDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagetransfer.TransferAlarmMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.garbagetransfer.TransferAlarmCodeGenerator;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.BarItemVO;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.PieItemVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.TRANSFER_ALARM_NOT_EXISTS;

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
    private TransferAlarmCodeGenerator codeGenerator;

    @Override
    public Long createTransferAlarm(TransferAlarmSaveReqVO createReqVO) {
        // 插入
        TransferAlarmDO transferAlarm = BeanUtils.toBean(createReqVO, TransferAlarmDO.class);

        transferAlarm.setAlarmId(codeGenerator.generateAlarmId());

        transferAlarmMapper.insert(transferAlarm);
        // 返回
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
        // 删除
        transferAlarmMapper.deleteById(id);
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
}