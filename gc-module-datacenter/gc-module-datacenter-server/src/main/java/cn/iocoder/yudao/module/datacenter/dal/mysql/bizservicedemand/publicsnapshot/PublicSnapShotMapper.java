package cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publicsnapshot;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicsnapshot.PublicSnapShotDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 随手拍数据 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicSnapShotMapper extends BaseMapperX<PublicSnapShotDO> {

    default PageResult<PublicSnapShotDO> selectPage(PublicSnapShotPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicSnapShotDO>()
                .eqIfPresent(PublicSnapShotDO::getSnapShotId, reqVO.getSnapShotId())
                .eqIfPresent(PublicSnapShotDO::getSnapNo, reqVO.getSnapNo())
                .eqIfPresent(PublicSnapShotDO::getProblemTypeCode, reqVO.getProblemTypeCode())
                .likeIfPresent(PublicSnapShotDO::getProblemTypeName, reqVO.getProblemTypeName())
                .eqIfPresent(PublicSnapShotDO::getCoordX, reqVO.getCoordX())
                .eqIfPresent(PublicSnapShotDO::getCoordY, reqVO.getCoordY())
                .eqIfPresent(PublicSnapShotDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PublicSnapShotDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(PublicSnapShotDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(PublicSnapShotDO::getMediaPath, reqVO.getMediaPath())
                .eqIfPresent(PublicSnapShotDO::getMediaType, reqVO.getMediaType())
                .likeIfPresent(PublicSnapShotDO::getRptName, reqVO.getRptName())
                .eqIfPresent(PublicSnapShotDO::getRptPhone, reqVO.getRptPhone())
                .eqIfPresent(PublicSnapShotDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(PublicSnapShotDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(PublicSnapShotDO::getRptTime, reqVO.getRptTime())
                .betweenIfPresent(PublicSnapShotDO::getFinishTime, reqVO.getFinishTime())
                .eqIfPresent(PublicSnapShotDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicSnapShotDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicSnapShotDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicSnapShotDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicSnapShotDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicSnapShotDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicSnapShotDO::getId));
    }

}