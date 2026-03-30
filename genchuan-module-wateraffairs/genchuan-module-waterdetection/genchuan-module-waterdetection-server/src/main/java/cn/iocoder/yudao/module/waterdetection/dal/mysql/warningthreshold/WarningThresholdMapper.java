package cn.iocoder.yudao.module.waterdetection.dal.mysql.warningthreshold;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningthreshold.WarningThresholdDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningthreshold.vo.*;

/**
 * 预警阈值管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WarningThresholdMapper extends BaseMapperX<WarningThresholdDO> {

    default PageResult<WarningThresholdDO> selectPage(WarningThresholdPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WarningThresholdDO>()
                .likeIfPresent(WarningThresholdDO::getIndicatorName, reqVO.getIndicatorName())
                .eqIfPresent(WarningThresholdDO::getThresholdType, reqVO.getThresholdType())
                .eqIfPresent(WarningThresholdDO::getThresholdValue, reqVO.getThresholdValue())
                .eqIfPresent(WarningThresholdDO::getUnit, reqVO.getUnit())
                .eqIfPresent(WarningThresholdDO::getApplicableScene, reqVO.getApplicableScene())
                .betweenIfPresent(WarningThresholdDO::getEffectiveTime, reqVO.getEffectiveTime())
                .betweenIfPresent(WarningThresholdDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WarningThresholdDO::getId));
    }

}