package cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.road.RoadPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.RoadDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 道路 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RoadMapper extends BaseMapperX<RoadDO> {

    default PageResult<RoadDO> selectPage(RoadPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RoadDO>()
                .eqIfPresent(RoadDO::getRoadId, reqVO.getRoadId())
                .likeIfPresent(RoadDO::getRoadName, reqVO.getRoadName())
                .eqIfPresent(RoadDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(RoadDO::getRoadLevel, reqVO.getRoadLevel())
                .eqIfPresent(RoadDO::getLength, reqVO.getLength())
                .eqIfPresent(RoadDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(RoadDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(RoadDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(RoadDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(RoadDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RoadDO::getId));
    }

}