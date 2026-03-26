package cn.iocoder.yudao.module.waterdetection.service.metercalibration;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.metercalibration.MeterCalibrationDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.waterdetection.dal.mysql.metercalibration.MeterCalibrationMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.waterdetection.enums.ErrorCodeConstants.*;

/**
 * 监测仪表校准管理 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MeterCalibrationServiceImpl implements MeterCalibrationService {

    @Resource
    private MeterCalibrationMapper meterCalibrationMapper;

    @Override
    public Long createMeterCalibration(MeterCalibrationSaveReqVO createReqVO) {
        // 插入
        MeterCalibrationDO meterCalibration = BeanUtils.toBean(createReqVO, MeterCalibrationDO.class);
        meterCalibrationMapper.insert(meterCalibration);
        // 返回
        return meterCalibration.getId();
    }

    @Override
    public void updateMeterCalibration(MeterCalibrationSaveReqVO updateReqVO) {
        // 校验存在
        validateMeterCalibrationExists(updateReqVO.getId());
        // 更新
        MeterCalibrationDO updateObj = BeanUtils.toBean(updateReqVO, MeterCalibrationDO.class);
        meterCalibrationMapper.updateById(updateObj);
    }

    @Override
    public void deleteMeterCalibration(Long id) {
        // 校验存在
        validateMeterCalibrationExists(id);
        // 删除
        meterCalibrationMapper.deleteById(id);
    }

    private void validateMeterCalibrationExists(Long id) {
        if (meterCalibrationMapper.selectById(id) == null) {
            throw exception(METER_CALIBRATION_NOT_EXISTS);
        }
    }

    @Override
    public MeterCalibrationDO getMeterCalibration(Long id) {
        return meterCalibrationMapper.selectById(id);
    }

    @Override
    public PageResult<MeterCalibrationDO> getMeterCalibrationPage(MeterCalibrationPageReqVO pageReqVO) {
        return meterCalibrationMapper.selectPage(pageReqVO);
    }

}