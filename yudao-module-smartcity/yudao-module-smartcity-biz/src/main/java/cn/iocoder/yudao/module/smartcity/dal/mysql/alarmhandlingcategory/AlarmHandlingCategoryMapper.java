package cn.iocoder.yudao.module.smartcity.dal.mysql.alarmhandlingcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.*;

/**
 * 智慧城管 Mapper
 *
 * @author zcq
 */
@Mapper
public interface AlarmHandlingCategoryMapper extends BaseMapperX<AlarmHandlingCategoryDO> {

    default PageResult<AlarmHandlingCategoryDO> selectPage(AlarmHandlingCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmHandlingCategoryDO>()
                .betweenIfPresent(AlarmHandlingCategoryDO::getTime, reqVO.getTime())
                .eqIfPresent(AlarmHandlingCategoryDO::getAlarmSource, reqVO.getAlarmSource())
                .eqIfPresent(AlarmHandlingCategoryDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(AlarmHandlingCategoryDO::getAlarmDescription, reqVO.getAlarmDescription())
                .eqIfPresent(AlarmHandlingCategoryDO::getInvolvingRegions, reqVO.getInvolvingRegions())
                .eqIfPresent(AlarmHandlingCategoryDO::getDisposalMeasures, reqVO.getDisposalMeasures())
                .eqIfPresent(AlarmHandlingCategoryDO::getDisposalResults, reqVO.getDisposalResults())
                .eqIfPresent(AlarmHandlingCategoryDO::getNotes, reqVO.getNotes())
                .betweenIfPresent(AlarmHandlingCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlarmHandlingCategoryDO::getId));
    }

}