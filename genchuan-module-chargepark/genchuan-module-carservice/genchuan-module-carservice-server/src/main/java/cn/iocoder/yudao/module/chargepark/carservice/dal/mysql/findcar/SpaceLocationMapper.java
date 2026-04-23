package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.findcar;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.findcar.vo.SpaceLocationPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.findcar.SpaceLocationDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 车位定位 Mapper
 *
 * @author carservice
 */
@Mapper
public interface SpaceLocationMapper extends BaseMapperX<SpaceLocationDO> {

    default PageResult<SpaceLocationDO> selectPage(SpaceLocationPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpaceLocationDO>()
                .eqIfPresent(SpaceLocationDO::getUserId, reqVO.getUserId())
                .likeIfPresent(SpaceLocationDO::getPlateNo, reqVO.getPlateNo())
                .eqIfPresent(SpaceLocationDO::getLocationResult, reqVO.getLocationResult())
                .betweenIfPresent(SpaceLocationDO::getQueryTime, reqVO.getQueryTime())
                .orderByDesc(SpaceLocationDO::getId));
    }

}
