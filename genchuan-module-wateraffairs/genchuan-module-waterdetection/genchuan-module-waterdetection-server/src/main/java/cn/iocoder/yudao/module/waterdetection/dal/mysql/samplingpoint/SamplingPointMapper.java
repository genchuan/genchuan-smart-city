package cn.iocoder.yudao.module.waterdetection.dal.mysql.samplingpoint;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.samplingpoint.SamplingPointDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.samplingpoint.vo.*;

/**
 * 采样点规划 Mapper
 *
 * @author zcq
 */
@Mapper
public interface SamplingPointMapper extends BaseMapperX<SamplingPointDO> {

    default PageResult<SamplingPointDO> selectPage(SamplingPointPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SamplingPointDO>()
                .eqIfPresent(SamplingPointDO::getPointCode, reqVO.getPointCode())
                .eqIfPresent(SamplingPointDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(SamplingPointDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(SamplingPointDO::getPointType, reqVO.getPointType())
                .eqIfPresent(SamplingPointDO::getCoveredPopulation, reqVO.getCoveredPopulation())
                .eqIfPresent(SamplingPointDO::getSurroundingDesc, reqVO.getSurroundingDesc())
                .eqIfPresent(SamplingPointDO::getPlanningBasis, reqVO.getPlanningBasis())
                .betweenIfPresent(SamplingPointDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(SamplingPointDO::getId));
    }

}