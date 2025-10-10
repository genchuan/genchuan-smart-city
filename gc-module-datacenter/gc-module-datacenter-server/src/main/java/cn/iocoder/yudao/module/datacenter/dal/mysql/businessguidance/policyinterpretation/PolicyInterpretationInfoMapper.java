package cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.policyinterpretation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo.PolicyInterpretationInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.policyinterpretation.PolicyInterpretationInfoDO;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

/**
 * 政策解读信息 Mapper
 *
 * @author Gyh
 */
@Mapper
public interface PolicyInterpretationInfoMapper extends BaseMapperX<PolicyInterpretationInfoDO> {

    /**
     * 根据解读ID查询
     */
    default PolicyInterpretationInfoDO selectByInterpretationId(String interpretationId) {
        return selectOne("interpretation_id", interpretationId);
    }

    /**
     * 根据关联政策ID查询
     */
    default List<PolicyInterpretationInfoDO> selectByRelatedPolicyId(String relatedPolicyId) {
        return selectList("related_policy_id", relatedPolicyId);
    }

    /**
     * 根据解读状态查询
     */
    default List<PolicyInterpretationInfoDO> selectByInterpretationStatus(String interpretationStatus) {
        return selectList("interpretation_status", interpretationStatus);
    }

    /**
     * 分页查询政策解读信息
     */
    default PageResult<PolicyInterpretationInfoDO> selectPage(PolicyInterpretationInfoPageReqVO pageReqVO) {
        return selectPage(pageReqVO, new LambdaQueryWrapperX<PolicyInterpretationInfoDO>()
                .likeIfPresent(PolicyInterpretationInfoDO::getInterpretationTitle, pageReqVO.getInterpretationTitle())
                .likeIfPresent(PolicyInterpretationInfoDO::getRelatedPolicyName, pageReqVO.getRelatedPolicyName())
                .eqIfPresent(PolicyInterpretationInfoDO::getRelatedPolicyType, pageReqVO.getRelatedPolicyType())
                .eqIfPresent(PolicyInterpretationInfoDO::getInterpretationType, pageReqVO.getInterpretationType())
                .eqIfPresent(PolicyInterpretationInfoDO::getInterpretationStatus, pageReqVO.getInterpretationStatus())
                .orderByDesc(PolicyInterpretationInfoDO::getIssueTime));
    }

    /**
     * 检查解读标题是否唯一
     */
    default boolean existsByInterpretationTitle(String interpretationTitle) {
        return selectCount("interpretation_title", interpretationTitle) > 0;
    }

    /**
     * 检查解读标题是否唯一（排除自身）
     */
    default boolean existsByInterpretationTitleExcludeSelf(String interpretationTitle, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<PolicyInterpretationInfoDO>()
                .eq(PolicyInterpretationInfoDO::getInterpretationTitle, interpretationTitle)
                .ne(PolicyInterpretationInfoDO::getId, excludeId)) > 0;
    }

    /**
     * 根据关联政策ID和状态查询
     */
    default List<PolicyInterpretationInfoDO> selectByRelatedPolicyIdAndStatus(String relatedPolicyId, String interpretationStatus) {
        return selectList(new LambdaQueryWrapperX<PolicyInterpretationInfoDO>()
                .eq(PolicyInterpretationInfoDO::getRelatedPolicyId, relatedPolicyId)
                .eq(PolicyInterpretationInfoDO::getInterpretationStatus, interpretationStatus)
                .orderByDesc(PolicyInterpretationInfoDO::getIssueTime));
    }

    /**
     * 更新解读状态
     */
    default void updateInterpretationStatus(Long id, String interpretationStatus, String offShelfReason) {
        PolicyInterpretationInfoDO updateObj = new PolicyInterpretationInfoDO();
        updateObj.setId(id);
        updateObj.setInterpretationStatus(interpretationStatus);
        updateObj.setOffShelfReason(offShelfReason);
        updateObj.setUpdateTime(java.time.LocalDateTime.now());
        updateById(updateObj);
    }
}