package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.inspect.evalinspectplan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.inspect.evalinspectplan.vo.EvalInspectPlanPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.inspect.evalinspectplan.EvalInspectPlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考察计划管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalInspectPlanMapper extends BaseMapperX<EvalInspectPlanDO> {

    default PageResult<EvalInspectPlanDO> selectPage(EvalInspectPlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalInspectPlanDO>()
                .eqIfPresent(EvalInspectPlanDO::getInspectPlanId, reqVO.getInspectPlanId())
                .likeIfPresent(EvalInspectPlanDO::getInspectPlanName, reqVO.getInspectPlanName())
                .eqIfPresent(EvalInspectPlanDO::getInspectPlanCode, reqVO.getInspectPlanCode())
                .eqIfPresent(EvalInspectPlanDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalInspectPlanDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalInspectPlanDO::getEvalObjectId, reqVO.getEvalObjectId())
                .likeIfPresent(EvalInspectPlanDO::getEvalObjectName, reqVO.getEvalObjectName())
                .eqIfPresent(EvalInspectPlanDO::getInspectorId, reqVO.getInspectorId())
                .likeIfPresent(EvalInspectPlanDO::getInspectorName, reqVO.getInspectorName())
                .betweenIfPresent(EvalInspectPlanDO::getInspectTime, reqVO.getInspectTime())
                .eqIfPresent(EvalInspectPlanDO::getInspectMethod, reqVO.getInspectMethod())
                .eqIfPresent(EvalInspectPlanDO::getInspectContent, reqVO.getInspectContent())
                .eqIfPresent(EvalInspectPlanDO::getPlanStatus, reqVO.getPlanStatus())
                .eqIfPresent(EvalInspectPlanDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalInspectPlanDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalInspectPlanDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalInspectPlanDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalInspectPlanDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalInspectPlanDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalInspectPlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalInspectPlanDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalInspectPlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalInspectPlanDO::getId));
    }

}