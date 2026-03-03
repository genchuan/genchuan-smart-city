package cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.roadcleaning.RoadCleaningPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.RoadCleaningDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 道路清扫问题 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CleaningProblemMapper extends BaseMapperX<CleaningProblemDO> {

    default PageResult<CleaningProblemDO> selectPage(CleaningProblemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CleaningProblemDO>()
                .eqIfPresent(CleaningProblemDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(CleaningProblemDO::getPlanId, reqVO.getPlanId())
                .eqIfPresent(CleaningProblemDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(CleaningProblemDO::getLocation, reqVO.getLocation())
                .eqIfPresent(CleaningProblemDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(CleaningProblemDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(CleaningProblemDO::getDesc, reqVO.getDesc())
                .eqIfPresent(CleaningProblemDO::getTeamId, reqVO.getTeamId())
                .eqIfPresent(CleaningProblemDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(CleaningProblemDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(CleaningProblemDO::getHandleResult, reqVO.getHandleResult())
                .eqIfPresent(CleaningProblemDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(CleaningProblemDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(CleaningProblemDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(CleaningProblemDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(CleaningProblemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CleaningProblemDO::getId));
    }

    List<CleaningProblemDetailDO> selectDetailPage(@Param("reqVO") CleaningProblemPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") CleaningProblemPageReqVO pageReqVO);
}