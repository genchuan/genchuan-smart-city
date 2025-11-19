package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectrectifyarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectrectifyarch.vo.InspectRectifyArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectrectifyarch.InspectRectifyArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检整改档案 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRectifyArchMapper extends BaseMapperX<InspectRectifyArchDO> {

    default PageResult<InspectRectifyArchDO> selectPage(InspectRectifyArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRectifyArchDO>()
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchId, reqVO.getRectifyArchId())
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchCode, reqVO.getRectifyArchCode())
                .likeIfPresent(InspectRectifyArchDO::getRectifyArchName, reqVO.getRectifyArchName())
                .eqIfPresent(InspectRectifyArchDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectRectifyArchDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchCycle, reqVO.getRectifyArchCycle())
                .likeIfPresent(InspectRectifyArchDO::getProblemTypeName, reqVO.getProblemTypeName())
                .eqIfPresent(InspectRectifyArchDO::getIncludeTaskCount, reqVO.getIncludeTaskCount())
                .eqIfPresent(InspectRectifyArchDO::getRectifyCompleteRate, reqVO.getRectifyCompleteRate())
                .eqIfPresent(InspectRectifyArchDO::getRectifyAttachUrls, reqVO.getRectifyAttachUrls())
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchUser, reqVO.getRectifyArchUser())
                .betweenIfPresent(InspectRectifyArchDO::getRectifyArchTime, reqVO.getRectifyArchTime())
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchStatus, reqVO.getRectifyArchStatus())
                .betweenIfPresent(InspectRectifyArchDO::getRectifyDestroyTime, reqVO.getRectifyDestroyTime())
                .eqIfPresent(InspectRectifyArchDO::getRectifyArchDesc, reqVO.getRectifyArchDesc())
                .eqIfPresent(InspectRectifyArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRectifyArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRectifyArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRectifyArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRectifyArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRectifyArchDO::getId));
    }

}