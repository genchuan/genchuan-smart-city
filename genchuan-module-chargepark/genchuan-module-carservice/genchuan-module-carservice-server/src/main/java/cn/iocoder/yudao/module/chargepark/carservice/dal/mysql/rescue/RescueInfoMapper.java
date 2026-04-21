package cn.iocoder.yudao.module.chargepark.carservice.dal.mysql.rescue;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.chargepark.carservice.controller.admin.rescue.vo.RescueInfoPageReqVO;
import cn.iocoder.yudao.module.chargepark.carservice.dal.dataobject.rescue.RescueInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 救援信息 Mapper
 *
 * @author carservice
 */
@Mapper
public interface RescueInfoMapper extends BaseMapperX<RescueInfoDO> {

    default PageResult<RescueInfoDO> selectPage(RescueInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RescueInfoDO>()
                .eqIfPresent(RescueInfoDO::getUserId, reqVO.getUserId())
                .eqIfPresent(RescueInfoDO::getRescueType, reqVO.getRescueType())
                .eqIfPresent(RescueInfoDO::getStatus, reqVO.getStatus())
                .likeIfPresent(RescueInfoDO::getLocationName, reqVO.getLocationName())
                .betweenIfPresent(RescueInfoDO::getCreateTime, reqVO.getCreateTime())
                .betweenIfPresent(RescueInfoDO::getDispatchTime, reqVO.getDispatchTime())
                .betweenIfPresent(RescueInfoDO::getFinishTime, reqVO.getFinishTime())
                .orderByDesc(RescueInfoDO::getId));
    }

}
