package cn.iocoder.yudao.module.datacenter.service.businessguidance.policyinterpretation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.policyinterpretation.PolicyInterpretationInfoDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.policyinterpretation.PolicyInterpretationInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 政策解读信息 Service 实现类
 *
 * @author Gyh
 */
@Service
@Validated
@Slf4j
public class PolicyInterpretationInfoServiceImpl implements PolicyInterpretationInfoService {

    @Resource
    private PolicyInterpretationInfoMapper policyInterpretationInfoMapper;

    @Override
    public Long createPolicyInterpretationInfo(PolicyInterpretationInfoCreateReqVO createReqVO) {
        // 校验解读标题唯一性
        validateInterpretationTitleUnique(createReqVO.getInterpretationTitle(), null);
        // 校验关联政策有效性（预留）

        // 插入政策解读信息
        PolicyInterpretationInfoDO policyInterpretationInfo = BeanUtils.toBean(createReqVO, PolicyInterpretationInfoDO.class);
        // 设置系统字段
        policyInterpretationInfo.setInterpretationId(generateInterpretationId());
        policyInterpretationInfo.setIssueUserId(getCurrentUserId());
        policyInterpretationInfo.setIssueUserName(getCurrentUserName());
        policyInterpretationInfo.setIssueTime(LocalDateTime.now());
        policyInterpretationInfo.setInterpretationStatus("已发布");
        policyInterpretationInfo.setUpdateUserId(getCurrentUserId());
        policyInterpretationInfo.setUpdateTime(LocalDateTime.now());

        policyInterpretationInfoMapper.insert(policyInterpretationInfo);
        log.info("[createPolicyInterpretationInfo][创建政策解读信息成功：解读标题={}]", createReqVO.getInterpretationTitle());
        return policyInterpretationInfo.getId();
    }

    @Override
    public void updatePolicyInterpretationInfo(PolicyInterpretationInfoUpdateReqVO updateReqVO) {
        // 校验存在
        PolicyInterpretationInfoDO policyInterpretationInfo = validatePolicyInterpretationInfoExists(updateReqVO.getId());
        // 校验解读标题唯一性（排除自身）
        validateInterpretationTitleUnique(updateReqVO.getInterpretationTitle(), updateReqVO.getId());
        // 校验状态（仅已发布状态可修改）
        validateCanUpdate(policyInterpretationInfo.getInterpretationStatus());

        // 更新政策解读信息
        PolicyInterpretationInfoDO updateObj = BeanUtils.toBean(updateReqVO, PolicyInterpretationInfoDO.class);
        updateObj.setUpdateUserId(getCurrentUserId());
        updateObj.setUpdateTime(LocalDateTime.now());

        policyInterpretationInfoMapper.updateById(updateObj);
        log.info("[updatePolicyInterpretationInfo][更新政策解读信息成功：ID={}]", updateReqVO.getId());
    }

    @Override
    public void deletePolicyInterpretationInfo(Long id) {
        // 校验存在
        PolicyInterpretationInfoDO policyInterpretationInfo = validatePolicyInterpretationInfoExists(id);
        // 校验状态（仅已下架状态可删除）
        validateCanDelete(policyInterpretationInfo.getInterpretationStatus());
        // 校验是否被引用（预留）

        // 删除
        policyInterpretationInfoMapper.deleteById(id);
        log.info("[deletePolicyInterpretationInfo][删除政策解读信息成功：ID={}]", id);
    }

    @Override
    public PolicyInterpretationInfoDO getPolicyInterpretationInfo(Long id) {
        return policyInterpretationInfoMapper.selectById(id);
    }

    @Override
    public PageResult<PolicyInterpretationInfoDO> getPolicyInterpretationInfoPage(PolicyInterpretationInfoPageReqVO pageReqVO) {
        return policyInterpretationInfoMapper.selectPage(pageReqVO);
    }

    @Override
    public List<PolicyInterpretationInfoDO> getPolicyInterpretationInfoList() {
        return policyInterpretationInfoMapper.selectList();
    }

    @Override
    public List<PolicyInterpretationInfoDO> getPublishedPolicyInterpretationInfoList() {
        return policyInterpretationInfoMapper.selectByInterpretationStatus("已发布");
    }

    @Override
    public List<PolicyInterpretationInfoDO> getPublishedInterpretationByPolicyId(String relatedPolicyId) {
        return policyInterpretationInfoMapper.selectByRelatedPolicyIdAndStatus(relatedPolicyId, "已发布");
    }

    @Override
    public boolean checkInterpretationTitleUnique(String interpretationTitle, Long id) {
        if (id == null) {
            return !policyInterpretationInfoMapper.existsByInterpretationTitle(interpretationTitle);
        } else {
            return !policyInterpretationInfoMapper.existsByInterpretationTitleExcludeSelf(interpretationTitle, id);
        }
    }

    @Override
    public void updateInterpretationStatus(Long id, String interpretationStatus, String offShelfReason) {
        // 校验存在
        validatePolicyInterpretationInfoExists(id);

        policyInterpretationInfoMapper.updateInterpretationStatus(id, interpretationStatus, offShelfReason);
        log.info("[updateInterpretationStatus][更新解读状态成功：ID={}, 状态={}]", id, interpretationStatus);
    }

    @Override
    public void autoOffShelfInvalidInterpretations() {
        log.info("[autoOffShelfInvalidInterpretations][开始自动下架关联政策已失效的解读]");

        // 预留：查询关联政策已失效的解读
        // List<PolicyInterpretationInfoDO> invalidInterpretations = policyInterpretationInfoMapper.selectInvalidInterpretations();

        // 预留：批量下架
        // for (PolicyInterpretationInfoDO interpretation : invalidInterpretations) {
        //     policyInterpretationInfoMapper.updateInterpretationStatus(interpretation.getId(), "已下架", "关联政策已失效");
        // }

        log.info("[autoOffShelfInvalidInterpretations][自动下架完成]");
    }

    @Override
    public List<PolicyInterpretationInfoDO> getPolicyInterpretationInfoListByType(String interpretationType) {
        return policyInterpretationInfoMapper.selectList(new LambdaQueryWrapperX<PolicyInterpretationInfoDO>()
                .eq(PolicyInterpretationInfoDO::getInterpretationType, interpretationType)
                .eq(PolicyInterpretationInfoDO::getInterpretationStatus, "已发布")
                .orderByDesc(PolicyInterpretationInfoDO::getIssueTime));
    }

    @Override
    public void offShelfInterpretationBatch(List<Long> ids, String offShelfReason) {
        if (ids == null || ids.isEmpty()) {
            return;
        }

        for (Long id : ids) {
            policyInterpretationInfoMapper.updateInterpretationStatus(id, "已下架", offShelfReason);
        }

        log.info("[offShelfInterpretationBatch][批量下架解读成功：数量={}]", ids.size());
    }

    // ========== 私有方法 ==========

    /**
     * 校验政策解读信息是否存在
     */
    private PolicyInterpretationInfoDO validatePolicyInterpretationInfoExists(Long id) {
        PolicyInterpretationInfoDO policyInterpretationInfo = policyInterpretationInfoMapper.selectById(id);
        if (policyInterpretationInfo == null) {
            throw exception(POLICY_INTERPRETATION_INFO_NOT_EXISTS);
        }
        return policyInterpretationInfo;
    }

    /**
     * 校验解读标题唯一性
     */
    private void validateInterpretationTitleUnique(String interpretationTitle, Long id) {
        if (!checkInterpretationTitleUnique(interpretationTitle, id)) {
            throw exception(POLICY_INTERPRETATION_INFO_TITLE_DUPLICATE);
        }
    }

    /**
     * 校验是否可以更新（仅已发布状态可更新）
     */
    private void validateCanUpdate(String interpretationStatus) {
        if (!"已发布".equals(interpretationStatus)) {
            throw exception(POLICY_INTERPRETATION_INFO_CANNOT_UPDATE);
        }
    }

    /**
     * 校验是否可以删除（仅已下架状态可删除）
     */
    private void validateCanDelete(String interpretationStatus) {
        if (!"已下架".equals(interpretationStatus)) {
            throw exception(POLICY_INTERPRETATION_INFO_CANNOT_DELETE);
        }
    }

    /**
     * 生成解读ID（UUID）
     */
    private String generateInterpretationId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /**
     * 获取当前用户ID（模拟）
     */
    private String getCurrentUserId() {
        // 实际项目中从SecurityContext获取
        return "admin";
    }

    /**
     * 获取当前用户名（模拟）
     */
    private String getCurrentUserName() {
        // 实际项目中从SecurityContext获取
        return "系统管理员";
    }
}