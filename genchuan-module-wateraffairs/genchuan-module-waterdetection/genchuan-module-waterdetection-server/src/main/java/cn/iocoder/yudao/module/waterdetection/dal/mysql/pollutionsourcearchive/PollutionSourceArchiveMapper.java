package cn.iocoder.yudao.module.waterdetection.dal.mysql.pollutionsourcearchive;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.pollutionsourcearchive.PollutionSourceArchiveDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.pollutionsourcearchive.vo.*;

/**
 * 周边污染源档案管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface PollutionSourceArchiveMapper extends BaseMapperX<PollutionSourceArchiveDO> {

    default PageResult<PollutionSourceArchiveDO> selectPage(PollutionSourceArchivePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PollutionSourceArchiveDO>()
                .eqIfPresent(PollutionSourceArchiveDO::getPollutionNo, reqVO.getPollutionNo())
                .eqIfPresent(PollutionSourceArchiveDO::getPollutionType, reqVO.getPollutionType())
                .eqIfPresent(PollutionSourceArchiveDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(PollutionSourceArchiveDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(PollutionSourceArchiveDO::getPollutionLevel, reqVO.getPollutionLevel())
                .eqIfPresent(PollutionSourceArchiveDO::getTreatmentMeasures, reqVO.getTreatmentMeasures())
                .eqIfPresent(PollutionSourceArchiveDO::getTreatmentStatus, reqVO.getTreatmentStatus())
                .betweenIfPresent(PollutionSourceArchiveDO::getInspectionTime, reqVO.getInspectionTime())
                .betweenIfPresent(PollutionSourceArchiveDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PollutionSourceArchiveDO::getId));
    }

}