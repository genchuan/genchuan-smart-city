package cn.iocoder.yudao.module.smartcity.dal.mysql.roadcollapsesafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.roadcollapsesafety.RoadCollapseSafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.roadcollapsesafety.vo.*;

/**
 * 路面塌陷安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface RoadCollapseSafetyMapper extends BaseMapperX<RoadCollapseSafetyDO> {

    default PageResult<RoadCollapseSafetyDO> selectPage(RoadCollapseSafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadCollapseSafetyDO>()
                .likeIfPresent(RoadCollapseSafetyDO::getMonitoringPointName, reqVO.getMonitoringPointName())
                .likeIfPresent(RoadCollapseSafetyDO::getLocationMonitoringPoints, reqVO.getLocationMonitoringPoints())
                .betweenIfPresent(RoadCollapseSafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadCollapseSafetyDO::getId));
    }

}