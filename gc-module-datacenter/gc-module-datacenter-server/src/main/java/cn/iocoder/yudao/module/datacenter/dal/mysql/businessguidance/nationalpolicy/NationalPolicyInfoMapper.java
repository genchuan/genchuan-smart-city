package cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.nationalpolicy;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.nationalpolicy.NationalPolicyInfoDO;
import org.apache.ibatis.annotations.Mapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.time.LocalDate;
import java.util.List;

/**
 * 国家政策信息 Mapper
 *
 * @author Gyh
 */
@Mapper
public interface NationalPolicyInfoMapper extends BaseMapperX<NationalPolicyInfoDO> {

    /**
     * 根据政策ID查询
     */
    default NationalPolicyInfoDO selectByPolicyId(String policyId) {
        return selectOne("policy_id", policyId);
    }

    /**
     * 根据政策编号查询
     */
    default NationalPolicyInfoDO selectByPolicyNo(String policyNo) {
        return selectOne("policy_no", policyNo);
    }

    /**
     * 根据政策类型查询
     */
    default List<NationalPolicyInfoDO> selectByPolicyType(String policyType) {
        return selectList("policy_type", policyType);
    }

    /**
     * 根据政策状态查询
     */
    default List<NationalPolicyInfoDO> selectByPolicyStatus(String policyStatus) {
        return selectList("policy_status", policyStatus);
    }

    /**
     * 根据发布单位查询
     */
    default List<NationalPolicyInfoDO> selectByIssueDept(String issueDept) {
        return selectList("issue_dept", issueDept);
    }

    /**
     * 分页查询国家政策
     */
    default PageResult<NationalPolicyInfoDO> selectPage(String policyName, String policyNo,
                                                        String policyType, String policyStatus,
                                                        String issueDept, LocalDate startTime,
                                                        LocalDate endTime, PageParam pageParam) {
        return selectPage(pageParam, new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .likeIfPresent(NationalPolicyInfoDO::getPolicyName, policyName)
                .likeIfPresent(NationalPolicyInfoDO::getPolicyNo, policyNo)
                .eqIfPresent(NationalPolicyInfoDO::getPolicyType, policyType)
                .eqIfPresent(NationalPolicyInfoDO::getPolicyStatus, policyStatus)
                .likeIfPresent(NationalPolicyInfoDO::getIssueDept, issueDept)
                .betweenIfPresent(NationalPolicyInfoDO::getIssueTime, startTime, endTime)
                .orderByDesc(NationalPolicyInfoDO::getIssueTime));
    }

    /**
     * 查询即将失效的政策（距失效时间≤30天）
     */
    default List<NationalPolicyInfoDO> selectExpiringPolicies() {
        LocalDate thresholdDate = LocalDate.now().plusDays(30);
        return selectList(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .isNotNull(NationalPolicyInfoDO::getExpireTime)
                .le(NationalPolicyInfoDO::getExpireTime, thresholdDate)
                .eq(NationalPolicyInfoDO::getPolicyStatus, "有效")
                .orderByAsc(NationalPolicyInfoDO::getExpireTime));
    }

    /**
     * 查询需要更新状态的政策
     */
    default List<NationalPolicyInfoDO> selectPoliciesNeedStatusUpdate() {
        LocalDate today = LocalDate.now();
        return selectList(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .and(wrapper -> wrapper
                        .and(sub -> sub
                                .eq(NationalPolicyInfoDO::getPolicyStatus, "未生效")
                                .le(NationalPolicyInfoDO::getEffectiveTime, today))
                        .or(sub -> sub
                                .eq(NationalPolicyInfoDO::getPolicyStatus, "有效")
                                .le(NationalPolicyInfoDO::getExpireTime, today))
                ));
    }

    /**
     * 检查政策编号是否存在
     */
    default boolean existsByPolicyNo(String policyNo) {
        return selectCount("policy_no", policyNo) > 0;
    }

    /**
     * 检查政策编号是否存在（排除自身）
     */
    default boolean existsByPolicyNoExcludeSelf(String policyNo, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .eq(NationalPolicyInfoDO::getPolicyNo, policyNo)
                .ne(NationalPolicyInfoDO::getId, excludeId)) > 0;
    }

    /**
     * 根据上传用户查询
     */
    default List<NationalPolicyInfoDO> selectByUploadUser(String uploadUserId) {
        return selectList("upload_user_id", uploadUserId);
    }

    /**
     * 查询所有有效的政策
     */
    default List<NationalPolicyInfoDO> selectAllValidPolicies() {
        return selectList(new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .eq(NationalPolicyInfoDO::getPolicyStatus, "有效")
                .orderByDesc(NationalPolicyInfoDO::getIssueTime));
    }

    /**
     * 批量更新政策状态
     */
    default int updatePolicyStatusBatch(List<Long> ids, String policyStatus) {
        if (ids == null || ids.isEmpty()) {
            return 0;
        }
        NationalPolicyInfoDO updateObj = new NationalPolicyInfoDO();
        updateObj.setPolicyStatus(policyStatus);
        return update(updateObj, new LambdaQueryWrapperX<NationalPolicyInfoDO>()
                .in(NationalPolicyInfoDO::getId, ids));
    }
}