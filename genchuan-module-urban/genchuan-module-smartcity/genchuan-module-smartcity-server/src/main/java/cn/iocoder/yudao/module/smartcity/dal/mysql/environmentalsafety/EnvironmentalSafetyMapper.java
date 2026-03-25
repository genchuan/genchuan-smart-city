package cn.iocoder.yudao.module.smartcity.dal.mysql.environmentalsafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.environmentalsafety.EnvironmentalSafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.environmentalsafety.vo.*;

/**
 * 环卫设施安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface EnvironmentalSafetyMapper extends BaseMapperX<EnvironmentalSafetyDO> {

    default PageResult<EnvironmentalSafetyDO> selectPage(EnvironmentalSafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EnvironmentalSafetyDO>()
                .likeIfPresent(EnvironmentalSafetyDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(EnvironmentalSafetyDO::getFacilityType, reqVO.getFacilityType())
                .likeIfPresent(EnvironmentalSafetyDO::getFacilityLocation, reqVO.getFacilityLocation())
                .betweenIfPresent(EnvironmentalSafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EnvironmentalSafetyDO::getId));
    }

}