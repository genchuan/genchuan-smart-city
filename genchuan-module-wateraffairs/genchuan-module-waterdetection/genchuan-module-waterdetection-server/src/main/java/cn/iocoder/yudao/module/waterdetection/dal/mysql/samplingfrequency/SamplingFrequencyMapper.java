package cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingfrequency;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingfrequency.SamplingFrequencyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingfrequency.vo.*;

/**
 * 采样频率设置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface SamplingFrequencyMapper extends BaseMapperX<SamplingFrequencyDO> {

    default PageResult<SamplingFrequencyDO> selectPage(SamplingFrequencyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SamplingFrequencyDO>()
                .eqIfPresent(SamplingFrequencyDO::getPointCode, reqVO.getPointCode())
                .likeIfPresent(SamplingFrequencyDO::getIndicatorName, reqVO.getIndicatorName())
                .eqIfPresent(SamplingFrequencyDO::getFrequency, reqVO.getFrequency())
                .eqIfPresent(SamplingFrequencyDO::getExecutionCycle, reqVO.getExecutionCycle())
                .eqIfPresent(SamplingFrequencyDO::getSpecialPeriodRule, reqVO.getSpecialPeriodRule())
                .betweenIfPresent(SamplingFrequencyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SamplingFrequencyDO::getId));
    }

}