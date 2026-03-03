package cn.iocoder.yudao.module.evaluate.dal.mysql.plan;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.inspection.plan.vo.PlanPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.plan.PlanDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考察计划 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface PlanMapper extends BaseMapperX<PlanDO> {

    default PageResult<PlanDO> selectPage(PlanPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PlanDO>()
                .eqIfPresent(PlanDO::getPlanId, reqVO.getPlanId())
                .likeIfPresent(PlanDO::getName, reqVO.getName())
                .eqIfPresent(PlanDO::getCode, reqVO.getCode())
                .eqIfPresent(PlanDO::getTaskId, reqVO.getTaskId())
                .betweenIfPresent(PlanDO::getInspectionTime, reqVO.getInspectionTime())
                .eqIfPresent(PlanDO::getInspectionTypeId, reqVO.getInspectionTypeId())
                .eqIfPresent(PlanDO::getNotifyStatusId, reqVO.getNotifyStatusId())
                .eqIfPresent(PlanDO::getConfirmCount, reqVO.getConfirmCount())
                .eqIfPresent(PlanDO::getStatusId, reqVO.getStatusId())
                .eqIfPresent(PlanDO::getCreateBy, reqVO.getCreateBy())
                .betweenIfPresent(PlanDO::getBizCreateTime, reqVO.getBizCreateTime())
                .betweenIfPresent(PlanDO::getBizUpdateTime, reqVO.getBizUpdateTime())
                .eqIfPresent(PlanDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PlanDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PlanDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(PlanDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(PlanDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PlanDO::getId));
    }

}