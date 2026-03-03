package cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.point.PointPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.PointDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 点位 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PointMapper extends BaseMapperX<PointDO> {

    default PageResult<PointDO> selectPage(PointPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PointDO>()
                .eqIfPresent(PointDO::getPointId, reqVO.getPointId())
                .likeIfPresent(PointDO::getPointName, reqVO.getPointName())
                .eqIfPresent(PointDO::getAreaCode, reqVO.getAreaCode())
                .eqIfPresent(PointDO::getPointAddress, reqVO.getPointAddress())
                .eqIfPresent(PointDO::getLongitude, reqVO.getLongitude())
                .eqIfPresent(PointDO::getLatitude, reqVO.getLatitude())
                .eqIfPresent(PointDO::getStatus, reqVO.getStatus())
                .eqIfPresent(PointDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PointDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PointDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PointDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PointDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PointDO::getId));
    }

}