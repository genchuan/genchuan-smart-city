package cn.iocoder.yudao.module.smartcity.dal.mysql.alarmhandlingcategory;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.alarmhandlingcategory.AlarmHandlingCategoryDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.alarmhandlingcategory.vo.*;

/**
 * 报警处置类 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface AlarmHandlingCategoryMapper extends BaseMapperX<AlarmHandlingCategoryDO> {

    default PageResult<AlarmHandlingCategoryDO> selectPage(AlarmHandlingCategoryPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AlarmHandlingCategoryDO>()
                .betweenIfPresent(AlarmHandlingCategoryDO::getTime, reqVO.getTime())
                .likeIfPresent(AlarmHandlingCategoryDO::getAlarmSource, reqVO.getAlarmSource())
                .likeIfPresent(AlarmHandlingCategoryDO::getRiskLevel, reqVO.getRiskLevel())
                .betweenIfPresent(AlarmHandlingCategoryDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AlarmHandlingCategoryDO::getId));
    }

}