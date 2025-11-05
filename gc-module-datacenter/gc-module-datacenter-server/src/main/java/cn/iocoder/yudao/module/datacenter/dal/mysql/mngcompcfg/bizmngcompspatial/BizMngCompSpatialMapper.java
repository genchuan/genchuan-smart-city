package cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompspatial;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompspatial.BizMngCompSpatialDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理部件空间数据 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface BizMngCompSpatialMapper extends BaseMapperX<BizMngCompSpatialDO> {

    default PageResult<BizMngCompSpatialDO> selectPage(BizMngCompSpatialPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<BizMngCompSpatialDO>()
                .eqIfPresent(BizMngCompSpatialDO::getMngCompSpatialId, reqVO.getMngCompSpatialId())
                .eqIfPresent(BizMngCompSpatialDO::getMngCompId, reqVO.getMngCompId())
                .likeIfPresent(BizMngCompSpatialDO::getCompName, reqVO.getCompName())
                .eqIfPresent(BizMngCompSpatialDO::getCoordSystem, reqVO.getCoordSystem())
                .eqIfPresent(BizMngCompSpatialDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(BizMngCompSpatialDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(BizMngCompSpatialDO::getElevation, reqVO.getElevation())
                .eqIfPresent(BizMngCompSpatialDO::getAccuracy, reqVO.getAccuracy())
                .eqIfPresent(BizMngCompSpatialDO::getAccuracyLevel, reqVO.getAccuracyLevel())
                .eqIfPresent(BizMngCompSpatialDO::getSurveyUnit, reqVO.getSurveyUnit())
                .eqIfPresent(BizMngCompSpatialDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(BizMngCompSpatialDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(BizMngCompSpatialDO::getId));
    }

}