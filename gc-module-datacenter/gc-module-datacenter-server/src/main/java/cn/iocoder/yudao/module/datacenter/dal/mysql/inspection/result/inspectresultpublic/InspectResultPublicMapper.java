package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.result.inspectresultpublic;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.result.inspectresultpublic.vo.InspectResultPublicPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.result.inspectresultpublic.InspectResultPublicDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检结果公示 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectResultPublicMapper extends BaseMapperX<InspectResultPublicDO> {

    default PageResult<InspectResultPublicDO> selectPage(InspectResultPublicPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectResultPublicDO>()
                .eqIfPresent(InspectResultPublicDO::getPublicId, reqVO.getPublicId())
                .eqIfPresent(InspectResultPublicDO::getPublicCode, reqVO.getPublicCode())
                .eqIfPresent(InspectResultPublicDO::getPublicTitle, reqVO.getPublicTitle())
                .eqIfPresent(InspectResultPublicDO::getPublicType, reqVO.getPublicType())
                .eqIfPresent(InspectResultPublicDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectResultPublicDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectResultPublicDO::getPublicContent, reqVO.getPublicContent())
                .eqIfPresent(InspectResultPublicDO::getPublicAttachUrl, reqVO.getPublicAttachUrl())
                .betweenIfPresent(InspectResultPublicDO::getPublicStartTime, reqVO.getPublicStartTime())
                .betweenIfPresent(InspectResultPublicDO::getPublicEndTime, reqVO.getPublicEndTime())
                .eqIfPresent(InspectResultPublicDO::getPublicStatus, reqVO.getPublicStatus())
                .eqIfPresent(InspectResultPublicDO::getPublicDesc, reqVO.getPublicDesc())
                .eqIfPresent(InspectResultPublicDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(InspectResultPublicDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectResultPublicDO::getUpdateUser, reqVO.getUpdateUser())
                .betweenIfPresent(InspectResultPublicDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(InspectResultPublicDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectResultPublicDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectResultPublicDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectResultPublicDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectResultPublicDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectResultPublicDO::getId));
    }

}