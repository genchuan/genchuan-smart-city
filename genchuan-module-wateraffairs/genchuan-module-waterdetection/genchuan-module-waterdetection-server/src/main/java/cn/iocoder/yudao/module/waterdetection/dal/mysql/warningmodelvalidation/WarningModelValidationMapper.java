package cn.iocoder.yudao.module.waterdetection.dal.mysql.warningmodelvalidation;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.warningmodelvalidation.WarningModelValidationDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.warningmodelvalidation.vo.*;

/**
 * 预警模型校验 Mapper
 *
 * @author zcq
 */
@Mapper
public interface WarningModelValidationMapper extends BaseMapperX<WarningModelValidationDO> {

    default PageResult<WarningModelValidationDO> selectPage(WarningModelValidationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<WarningModelValidationDO>()
                .likeIfPresent(WarningModelValidationDO::getModelName, reqVO.getModelName())
                .eqIfPresent(WarningModelValidationDO::getValidationPeriod, reqVO.getValidationPeriod())
                .eqIfPresent(WarningModelValidationDO::getWarningCount, reqVO.getWarningCount())
                .eqIfPresent(WarningModelValidationDO::getAccurateWarningCount, reqVO.getAccurateWarningCount())
                .eqIfPresent(WarningModelValidationDO::getFalseAlarmCount, reqVO.getFalseAlarmCount())
                .eqIfPresent(WarningModelValidationDO::getAccuracyRate, reqVO.getAccuracyRate())
                .eqIfPresent(WarningModelValidationDO::getAdjustmentSuggestion, reqVO.getAdjustmentSuggestion())
                .betweenIfPresent(WarningModelValidationDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(WarningModelValidationDO::getId));
    }

}