package cn.iocoder.yudao.module.waterdetection.dal.mysql.warningindicator;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningindicator.WarningIndicatorDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningindicator.vo.*;

/**
 * 预警指标配置 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WarningIndicatorMapper extends BaseMapperX<WarningIndicatorDO> {

    default PageResult<WarningIndicatorDO> selectPage(WarningIndicatorPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WarningIndicatorDO>()
                .likeIfPresent(WarningIndicatorDO::getIndicatorName, reqVO.getIndicatorName())
                .eqIfPresent(WarningIndicatorDO::getIndicatorType, reqVO.getIndicatorType())
                .eqIfPresent(WarningIndicatorDO::getRelatedPointType, reqVO.getRelatedPointType())
                .eqIfPresent(WarningIndicatorDO::getDataSource, reqVO.getDataSource())
                .betweenIfPresent(WarningIndicatorDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WarningIndicatorDO::getId));
    }

}