package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.report.inspectproblemSb;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemSb.vo.InspectProblemSbPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemSb.InspectProblemSbDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检问题上报记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectProblemSbMapper extends BaseMapperX<InspectProblemSbDO> {

    default PageResult<InspectProblemSbDO> selectPage(InspectProblemSbPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectProblemSbDO>()
                .eqIfPresent(InspectProblemSbDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectProblemSbDO::getProblemCode, reqVO.getProblemCode())
                .likeIfPresent(InspectProblemSbDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectProblemSbDO::getMatterTypeId, reqVO.getMatterTypeId())
                .likeIfPresent(InspectProblemSbDO::getMatterTypeName, reqVO.getMatterTypeName())
                .eqIfPresent(InspectProblemSbDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectProblemSbDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectProblemSbDO::getRptUserId, reqVO.getRptUserId())
                .likeIfPresent(InspectProblemSbDO::getRptUserName, reqVO.getRptUserName())
                .eqIfPresent(InspectProblemSbDO::getGridId, reqVO.getGridId())
                .likeIfPresent(InspectProblemSbDO::getGridName, reqVO.getGridName())
                .eqIfPresent(InspectProblemSbDO::getProblemLocX, reqVO.getProblemLocX())
                .eqIfPresent(InspectProblemSbDO::getProblemLocY, reqVO.getProblemLocY())
                .eqIfPresent(InspectProblemSbDO::getProblemLocDesc, reqVO.getProblemLocDesc())
                .eqIfPresent(InspectProblemSbDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(InspectProblemSbDO::getProblemPhotoUrls, reqVO.getProblemPhotoUrls())
                .betweenIfPresent(InspectProblemSbDO::getRptTime, reqVO.getRptTime())
                .eqIfPresent(InspectProblemSbDO::getProblemStatus, reqVO.getProblemStatus())
                .eqIfPresent(InspectProblemSbDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(InspectProblemSbDO::getBizCreateUser, reqVO.getBizCreateUser())
                .betweenIfPresent(InspectProblemSbDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectProblemSbDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectProblemSbDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectProblemSbDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectProblemSbDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectProblemSbDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectProblemSbDO::getId));
    }

}