package cn.iocoder.yudao.module.smartcity.dal.mysql.floodsafety;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.floodsafety.FloodSafetyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.floodsafety.vo.*;

/**
 * 内涝安全 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface FloodSafetyMapper extends BaseMapperX<FloodSafetyDO> {

    default PageResult<FloodSafetyDO> selectPage(FloodSafetyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FloodSafetyDO>()
                .likeIfPresent(FloodSafetyDO::getMonitoringPointName, reqVO.getMonitoringPointName())
                .likeIfPresent(FloodSafetyDO::getLocationOfMonitoringPoints, reqVO.getLocationOfMonitoringPoints())
                .betweenIfPresent(FloodSafetyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FloodSafetyDO::getId));
    }

}