package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspect.evalinspectrec;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectrec.vo.EvalInspectRecPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectrec.EvalInspectRecDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考察记录数据 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalInspectRecMapper extends BaseMapperX<EvalInspectRecDO> {

    default PageResult<EvalInspectRecDO> selectPage(EvalInspectRecPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalInspectRecDO>()
                .eqIfPresent(EvalInspectRecDO::getInspectRecId, reqVO.getInspectRecId())
                .eqIfPresent(EvalInspectRecDO::getInspectPlanId, reqVO.getInspectPlanId())
                .likeIfPresent(EvalInspectRecDO::getInspectPlanName, reqVO.getInspectPlanName())
                .eqIfPresent(EvalInspectRecDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalInspectRecDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalInspectRecDO::getIdxItemId, reqVO.getIdxItemId())
                .likeIfPresent(EvalInspectRecDO::getIdxItemName, reqVO.getIdxItemName())
                .eqIfPresent(EvalInspectRecDO::getInspectScore, reqVO.getInspectScore())
                .eqIfPresent(EvalInspectRecDO::getInspectLevel, reqVO.getInspectLevel())
                .eqIfPresent(EvalInspectRecDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(EvalInspectRecDO::getOnSitePhotos, reqVO.getOnSitePhotos())
                .betweenIfPresent(EvalInspectRecDO::getInspectTime, reqVO.getInspectTime())
                .eqIfPresent(EvalInspectRecDO::getInspectorId, reqVO.getInspectorId())
                .likeIfPresent(EvalInspectRecDO::getInspectorName, reqVO.getInspectorName())
                .eqIfPresent(EvalInspectRecDO::getRecStatus, reqVO.getRecStatus())
                .eqIfPresent(EvalInspectRecDO::getAuditorId, reqVO.getAuditorId())
                .likeIfPresent(EvalInspectRecDO::getAuditorName, reqVO.getAuditorName())
                .eqIfPresent(EvalInspectRecDO::getAuditOpinion, reqVO.getAuditOpinion())
                .eqIfPresent(EvalInspectRecDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalInspectRecDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalInspectRecDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalInspectRecDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalInspectRecDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalInspectRecDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalInspectRecDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalInspectRecDO::getId));
    }

}