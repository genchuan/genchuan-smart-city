package cn.iocoder.yudao.module.waterdetection.dal.mysql.metercalibration;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.metercalibration.MeterCalibrationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.metercalibration.vo.*;

/**
 * 监测仪表校准管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface MeterCalibrationMapper extends BaseMapperX<MeterCalibrationDO> {

    default PageResult<MeterCalibrationDO> selectPage(MeterCalibrationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MeterCalibrationDO>()
                .eqIfPresent(MeterCalibrationDO::getMeterId, reqVO.getMeterId())
                .eqIfPresent(MeterCalibrationDO::getMeterType, reqVO.getMeterType())
                .eqIfPresent(MeterCalibrationDO::getCalibrationCycle, reqVO.getCalibrationCycle())
                .betweenIfPresent(MeterCalibrationDO::getLastCalibrationDate, reqVO.getLastCalibrationDate())
                .betweenIfPresent(MeterCalibrationDO::getCurrentCalibrationDate, reqVO.getCurrentCalibrationDate())
                .eqIfPresent(MeterCalibrationDO::getStandardSolutionConc, reqVO.getStandardSolutionConc())
                .eqIfPresent(MeterCalibrationDO::getBeforeCalibrationValue, reqVO.getBeforeCalibrationValue())
                .eqIfPresent(MeterCalibrationDO::getAfterCalibrationValue, reqVO.getAfterCalibrationValue())
                .eqIfPresent(MeterCalibrationDO::getOperatorId, reqVO.getOperatorId())
                .betweenIfPresent(MeterCalibrationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MeterCalibrationDO::getId));
    }

}