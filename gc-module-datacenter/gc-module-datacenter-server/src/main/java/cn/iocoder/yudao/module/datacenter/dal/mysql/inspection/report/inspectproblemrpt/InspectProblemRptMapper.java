package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.report.inspectproblemrpt;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.report.inspectproblemrpt.vo.InspectProblemRptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.report.inspectproblemrpt.InspectProblemRptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检问题上报记录 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectProblemRptMapper extends BaseMapperX<InspectProblemRptDO> {

    default PageResult<InspectProblemRptDO> selectPage(InspectProblemRptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectProblemRptDO>()
                .eqIfPresent(InspectProblemRptDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectProblemRptDO::getProblemCode, reqVO.getProblemCode())
                .likeIfPresent(InspectProblemRptDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectProblemRptDO::getMatterTypeId, reqVO.getMatterTypeId())
                .likeIfPresent(InspectProblemRptDO::getMatterTypeName, reqVO.getMatterTypeName())
                .eqIfPresent(InspectProblemRptDO::getTaskId, reqVO.getTaskId())
                .eqIfPresent(InspectProblemRptDO::getTaskCode, reqVO.getTaskCode())
                .eqIfPresent(InspectProblemRptDO::getRptUserId, reqVO.getRptUserId())
                .likeIfPresent(InspectProblemRptDO::getRptUserName, reqVO.getRptUserName())
                .eqIfPresent(InspectProblemRptDO::getGridId, reqVO.getGridId())
                .likeIfPresent(InspectProblemRptDO::getGridName, reqVO.getGridName())
                .eqIfPresent(InspectProblemRptDO::getProblemLocX, reqVO.getProblemLocX())
                .eqIfPresent(InspectProblemRptDO::getProblemLocY, reqVO.getProblemLocY())
                .eqIfPresent(InspectProblemRptDO::getProblemLocDesc, reqVO.getProblemLocDesc())
                .eqIfPresent(InspectProblemRptDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(InspectProblemRptDO::getProblemPhotoUrls, reqVO.getProblemPhotoUrls())
                .betweenIfPresent(InspectProblemRptDO::getRptTime, reqVO.getRptTime())
                .eqIfPresent(InspectProblemRptDO::getProblemStatus, reqVO.getProblemStatus())
                .eqIfPresent(InspectProblemRptDO::getRejectReason, reqVO.getRejectReason())
                .eqIfPresent(InspectProblemRptDO::getBizCreateUser, reqVO.getBizCreateUser())
                .betweenIfPresent(InspectProblemRptDO::getBizCreateTime, reqVO.getBizCreateTime())
                .eqIfPresent(InspectProblemRptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectProblemRptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectProblemRptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectProblemRptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectProblemRptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectProblemRptDO::getId));
    }

}