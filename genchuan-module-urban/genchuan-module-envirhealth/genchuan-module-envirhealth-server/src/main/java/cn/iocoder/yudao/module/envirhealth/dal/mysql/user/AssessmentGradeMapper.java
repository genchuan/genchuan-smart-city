package cn.iocoder.yudao.module.envirhealth.dal.mysql.user;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.assessmentgrade.AssessmentGradePageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.AssessmentGradeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 考核等级字典表 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AssessmentGradeMapper extends BaseMapperX<AssessmentGradeDO> {

    default PageResult<AssessmentGradeDO> selectPage(AssessmentGradePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AssessmentGradeDO>()
                .eqIfPresent(AssessmentGradeDO::getAssessmentGradeId, reqVO.getAssessmentGradeId())
                .likeIfPresent(AssessmentGradeDO::getGradeName, reqVO.getGradeName())
                .eqIfPresent(AssessmentGradeDO::getScoreRange, reqVO.getScoreRange())
                .eqIfPresent(AssessmentGradeDO::getDescription, reqVO.getDescription())
                .eqIfPresent(AssessmentGradeDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AssessmentGradeDO::getSort, reqVO.getSort())
                .eqIfPresent(AssessmentGradeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AssessmentGradeDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AssessmentGradeDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AssessmentGradeDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AssessmentGradeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AssessmentGradeDO::getId));
    }

}