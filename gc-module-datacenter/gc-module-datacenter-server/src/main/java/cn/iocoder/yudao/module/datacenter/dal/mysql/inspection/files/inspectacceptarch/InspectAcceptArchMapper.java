package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.files.inspectacceptarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.files.inspectacceptarch.vo.InspectAcceptArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.files.inspectacceptarch.InspectAcceptArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检验收档案 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectAcceptArchMapper extends BaseMapperX<InspectAcceptArchDO> {

    default PageResult<InspectAcceptArchDO> selectPage(InspectAcceptArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectAcceptArchDO>()
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchId, reqVO.getAcceptArchId())
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchCode, reqVO.getAcceptArchCode())
                .likeIfPresent(InspectAcceptArchDO::getAcceptArchName, reqVO.getAcceptArchName())
                .eqIfPresent(InspectAcceptArchDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(InspectAcceptArchDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchCycle, reqVO.getAcceptArchCycle())
                .eqIfPresent(InspectAcceptArchDO::getIncludeAcceptCount, reqVO.getIncludeAcceptCount())
                .eqIfPresent(InspectAcceptArchDO::getAcceptQualifiedRate, reqVO.getAcceptQualifiedRate())
                .eqIfPresent(InspectAcceptArchDO::getAcceptAttachUrls, reqVO.getAcceptAttachUrls())
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchUser, reqVO.getAcceptArchUser())
                .betweenIfPresent(InspectAcceptArchDO::getAcceptArchTime, reqVO.getAcceptArchTime())
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchStatus, reqVO.getAcceptArchStatus())
                .betweenIfPresent(InspectAcceptArchDO::getAcceptDestroyTime, reqVO.getAcceptDestroyTime())
                .eqIfPresent(InspectAcceptArchDO::getAcceptArchDesc, reqVO.getAcceptArchDesc())
                .eqIfPresent(InspectAcceptArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectAcceptArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectAcceptArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectAcceptArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectAcceptArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectAcceptArchDO::getId));
    }

}