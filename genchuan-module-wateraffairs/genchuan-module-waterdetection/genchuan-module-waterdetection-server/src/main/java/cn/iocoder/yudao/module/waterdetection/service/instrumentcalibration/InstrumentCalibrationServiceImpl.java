package cn.iocoder.yudao.module.waterdetection.service.instrumentcalibration;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.instrumentcalibration.InstrumentCalibrationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.instrumentcalibration.InstrumentCalibrationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 仪器零点/量程漂移校验 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InstrumentCalibrationServiceImpl implements InstrumentCalibrationService {

    @Resource
    private InstrumentCalibrationMapper instrumentCalibrationMapper;

    @Override
    public Long createInstrumentCalibration(InstrumentCalibrationSaveReqVO createReqVO) {
        // 插入
        InstrumentCalibrationDO instrumentCalibration = BeanUtils.toBean(createReqVO, InstrumentCalibrationDO.class);
        instrumentCalibrationMapper.insert(instrumentCalibration);
        // 返回
        return instrumentCalibration.getId();
    }

    @Override
    public void updateInstrumentCalibration(InstrumentCalibrationSaveReqVO updateReqVO) {
        // 校验存在
        validateInstrumentCalibrationExists(updateReqVO.getId());
        // 更新
        InstrumentCalibrationDO updateObj = BeanUtils.toBean(updateReqVO, InstrumentCalibrationDO.class);
        instrumentCalibrationMapper.updateById(updateObj);
    }

    @Override
    public void deleteInstrumentCalibration(Long id) {
        // 校验存在
        validateInstrumentCalibrationExists(id);
        // 删除
        instrumentCalibrationMapper.deleteById(id);
    }

    private void validateInstrumentCalibrationExists(Long id) {
        if (instrumentCalibrationMapper.selectById(id) == null) {
            throw exception(INSTRUMENT_CALIBRATION_NOT_EXISTS);
        }
    }

    @Override
    public InstrumentCalibrationDO getInstrumentCalibration(Long id) {
        return instrumentCalibrationMapper.selectById(id);
    }

    @Override
    public PageResult<InstrumentCalibrationDO> getInstrumentCalibrationPage(InstrumentCalibrationPageReqVO pageReqVO) {
        return instrumentCalibrationMapper.selectPage(pageReqVO);
    }

}