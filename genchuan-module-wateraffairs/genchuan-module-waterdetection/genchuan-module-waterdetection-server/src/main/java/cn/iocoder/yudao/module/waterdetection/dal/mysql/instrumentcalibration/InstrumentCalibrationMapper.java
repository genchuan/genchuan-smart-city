package cn.iocoder.yudao.module.waterdetection.dal.mysql.instrumentcalibration;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.instrumentcalibration.InstrumentCalibrationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.instrumentcalibration.vo.*;

/**
 * 仪器零点/量程漂移校验 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InstrumentCalibrationMapper extends BaseMapperX<InstrumentCalibrationDO> {

    default PageResult<InstrumentCalibrationDO> selectPage(InstrumentCalibrationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstrumentCalibrationDO>()
                .eqIfPresent(InstrumentCalibrationDO::getInstrumentId, reqVO.getInstrumentId())
                .betweenIfPresent(InstrumentCalibrationDO::getCalibrationDate, reqVO.getCalibrationDate())
                .eqIfPresent(InstrumentCalibrationDO::getZeroPointConc, reqVO.getZeroPointConc())
                .eqIfPresent(InstrumentCalibrationDO::getZeroDrift, reqVO.getZeroDrift())
                .eqIfPresent(InstrumentCalibrationDO::getSpanConc, reqVO.getSpanConc())
                .eqIfPresent(InstrumentCalibrationDO::getSpanDrift, reqVO.getSpanDrift())
                .eqIfPresent(InstrumentCalibrationDO::getCalibrationResult, reqVO.getCalibrationResult())
                .eqIfPresent(InstrumentCalibrationDO::getOperatorId, reqVO.getOperatorId())
                .betweenIfPresent(InstrumentCalibrationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstrumentCalibrationDO::getId));
    }

}