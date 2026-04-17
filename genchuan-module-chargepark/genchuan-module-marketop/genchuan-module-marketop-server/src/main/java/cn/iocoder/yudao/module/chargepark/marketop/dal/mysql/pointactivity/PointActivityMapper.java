package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.pointactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.pointactivity.vo.PointActivityPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.PointActivityDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PointActivityMapper extends BaseMapperX<PointActivityDO> {

    default PageResult<PointActivityDO> selectPage(PointActivityPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PointActivityDO>()
                .likeIfPresent(PointActivityDO::getName, reqVO.getName())
                .eqIfPresent(PointActivityDO::getType, reqVO.getType())
                .eqIfPresent(PointActivityDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(PointActivityDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(PointActivityDO::getEndTime, reqVO.getEndTime())
                .orderByDesc(PointActivityDO::getId));
    }

}
