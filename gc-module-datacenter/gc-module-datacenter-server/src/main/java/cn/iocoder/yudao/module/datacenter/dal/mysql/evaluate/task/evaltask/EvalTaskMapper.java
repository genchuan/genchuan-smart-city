package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.task.evaltask;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.task.evaltask.vo.EvalTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.task.evaltask.EvalTaskDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 任务管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalTaskMapper extends BaseMapperX<EvalTaskDO> {

    default PageResult<EvalTaskDO> selectPage(EvalTaskPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalTaskDO>()
                .eqIfPresent(EvalTaskDO::getEvalTaskId, reqVO.getEvalTaskId())
                .likeIfPresent(EvalTaskDO::getEvalTaskName, reqVO.getEvalTaskName())
                .eqIfPresent(EvalTaskDO::getEvalTaskCode, reqVO.getEvalTaskCode())
                .eqIfPresent(EvalTaskDO::getTaskTemplateId, reqVO.getTaskTemplateId())
                .likeIfPresent(EvalTaskDO::getTaskTemplateName, reqVO.getTaskTemplateName())
                .eqIfPresent(EvalTaskDO::getObjectScope, reqVO.getObjectScope())
                .eqIfPresent(EvalTaskDO::getRelateObjectIds, reqVO.getRelateObjectIds())
                .eqIfPresent(EvalTaskDO::getRelateObjectNames, reqVO.getRelateObjectNames())
                .betweenIfPresent(EvalTaskDO::getTaskStartTime, reqVO.getTaskStartTime())
                .betweenIfPresent(EvalTaskDO::getTaskEndTime, reqVO.getTaskEndTime())
                .eqIfPresent(EvalTaskDO::getTaskStatus, reqVO.getTaskStatus())
                .eqIfPresent(EvalTaskDO::getDataCollectMethod, reqVO.getDataCollectMethod())
                .eqIfPresent(EvalTaskDO::getTaskDesc, reqVO.getTaskDesc())
                .eqIfPresent(EvalTaskDO::getCreateUserBiz, reqVO.getCreateUserBiz())
                .eqIfPresent(EvalTaskDO::getCreateTimeBiz, reqVO.getCreateTimeBiz())
                .eqIfPresent(EvalTaskDO::getUpdateUserBiz, reqVO.getUpdateUserBiz())
                .eqIfPresent(EvalTaskDO::getUpdateTimeBiz, reqVO.getUpdateTimeBiz())
                .eqIfPresent(EvalTaskDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalTaskDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalTaskDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalTaskDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EvalTaskDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EvalTaskDO::getId));
    }

}