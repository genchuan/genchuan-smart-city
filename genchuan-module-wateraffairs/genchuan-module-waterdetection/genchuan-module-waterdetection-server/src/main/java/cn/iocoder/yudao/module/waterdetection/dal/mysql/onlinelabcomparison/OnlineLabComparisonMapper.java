package cn.iocoder.yudao.module.waterdetection.dal.mysql.onlinelabcomparison;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.onlinelabcomparison.OnlineLabComparisonDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.onlinelabcomparison.vo.*;

/**
 * 在线数据与实验室比对 Mapper
 *
 * @author zcq
 */
@Mapper
public interface OnlineLabComparisonMapper extends BaseMapperX<OnlineLabComparisonDO> {

    default PageResult<OnlineLabComparisonDO> selectPage(OnlineLabComparisonPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<OnlineLabComparisonDO>()
                .betweenIfPresent(OnlineLabComparisonDO::getComparisonDate, reqVO.getComparisonDate())
                .eqIfPresent(OnlineLabComparisonDO::getMonitorPointId, reqVO.getMonitorPointId())
                .eqIfPresent(OnlineLabComparisonDO::getInstrumentType, reqVO.getInstrumentType())
                .eqIfPresent(OnlineLabComparisonDO::getOnlineValue, reqVO.getOnlineValue())
                .eqIfPresent(OnlineLabComparisonDO::getLabValue, reqVO.getLabValue())
                .eqIfPresent(OnlineLabComparisonDO::getDeviationValue, reqVO.getDeviationValue())
                .eqIfPresent(OnlineLabComparisonDO::getIsExceeded, reqVO.getIsExceeded())
                .eqIfPresent(OnlineLabComparisonDO::getWarningStatus, reqVO.getWarningStatus())
                .betweenIfPresent(OnlineLabComparisonDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(OnlineLabComparisonDO::getId));
    }

}