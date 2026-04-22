package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.PathPlanPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.PathPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 路径规划 Mapper
 *
 * @author carservice
 */
@Mapper
public interface PathPlanMapper extends BaseMapperX<PathPlanDO> {

    default PageResult<PathPlanDO> selectPage(PathPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PathPlanDO>()
                .eqIfPresent(PathPlanDO::getUserId, reqVO.getUserId())
                // 前端传的是汉字地址(如"入口"/"A 区"),匹配 DB start_location_name/end_location_name 列;
                // start_location/end_location 存经纬度坐标,无法做汉字模糊匹配
                .likeIfPresent(PathPlanDO::getStartLocationName, reqVO.getStartLocation())
                .likeIfPresent(PathPlanDO::getEndLocationName, reqVO.getEndLocation())
                .betweenIfPresent(PathPlanDO::getPlanTime, reqVO.getPlanTime())
                .orderByDesc(PathPlanDO::getId));
    }

}
