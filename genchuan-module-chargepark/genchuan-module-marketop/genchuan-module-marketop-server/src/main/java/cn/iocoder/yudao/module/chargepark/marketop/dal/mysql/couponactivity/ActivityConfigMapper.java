package cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.couponactivity;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.couponactivity.activityconfig.vo.ActivityConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.couponactivity.ActivityConfigDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ActivityConfigMapper extends BaseMapperX<ActivityConfigDO> {

    default PageResult<ActivityConfigDO> selectPage(ActivityConfigPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ActivityConfigDO>()
                .likeIfPresent(ActivityConfigDO::getName, reqVO.getName())
                .eqIfPresent(ActivityConfigDO::getType, reqVO.getType())
                .eqIfPresent(ActivityConfigDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ActivityConfigDO::getUserGroup, reqVO.getUserGroup())
                .orderByDesc(ActivityConfigDO::getId));
    }

}
