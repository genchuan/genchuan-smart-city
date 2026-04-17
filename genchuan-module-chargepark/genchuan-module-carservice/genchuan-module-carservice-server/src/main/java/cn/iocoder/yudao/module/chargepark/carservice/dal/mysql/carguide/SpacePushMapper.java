package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.carguide;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.carguide.vo.SpacePushPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.carguide.SpacePushDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 空位推送 Mapper
 *
 * @author carservice
 */
@Mapper
public interface SpacePushMapper extends BaseMapperX<SpacePushDO> {

    default PageResult<SpacePushDO> selectPage(SpacePushPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SpacePushDO>()
                .eqIfPresent(SpacePushDO::getUserId, reqVO.getUserId())
                .eqIfPresent(SpacePushDO::getStationId, reqVO.getStationId())
                .eqIfPresent(SpacePushDO::getStatus, reqVO.getStatus())
                .betweenIfPresent(SpacePushDO::getPushTime, reqVO.getPushTime())
                .orderByDesc(SpacePushDO::getId));
    }

}
