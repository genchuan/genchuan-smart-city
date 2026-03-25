package cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.institutionproblem.InstitutionProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.InstitutionProblemDetailDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 公共机构问题 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface InstitutionProblemMapper extends BaseMapperX<InstitutionProblemDO> {

    default PageResult<InstitutionProblemDO> selectPage(InstitutionProblemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InstitutionProblemDO>()
                .eqIfPresent(InstitutionProblemDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InstitutionProblemDO::getInstitutionId, reqVO.getInstitutionId())
                .eqIfPresent(InstitutionProblemDO::getProblemTypeId, reqVO.getProblemTypeId())
                .eqIfPresent(InstitutionProblemDO::getLocation, reqVO.getLocation())
                .eqIfPresent(InstitutionProblemDO::getReportBy, reqVO.getReportBy())
                .betweenIfPresent(InstitutionProblemDO::getReportTime, reqVO.getReportTime())
                .eqIfPresent(InstitutionProblemDO::getProblemDesc, reqVO.getProblemDesc())
                .eqIfPresent(InstitutionProblemDO::getDispatchStatus, reqVO.getDispatchStatus())
                .eqIfPresent(InstitutionProblemDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(InstitutionProblemDO::getHandleBy, reqVO.getHandleBy())
                .eqIfPresent(InstitutionProblemDO::getIsTimeout, reqVO.getIsTimeout())
                .eqIfPresent(InstitutionProblemDO::getHandleResult, reqVO.getHandleResult())
                .betweenIfPresent(InstitutionProblemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InstitutionProblemDO::getId));
    }

    /**
     * 查询全局最大序号（用于problem_id）
     */
    @Select("SELECT IFNULL(MAX(SUBSTRING_INDEX(problem_id, '-', -1)), 0) FROM public_institution_problem")
    Integer selectMaxSeq();

    List<InstitutionProblemDetailDO> selectDetailPage(@Param("reqVO") InstitutionProblemPageReqVO pageReqVO);

    Long selectCount(@Param("reqVO") InstitutionProblemPageReqVO pageReqVO);
}