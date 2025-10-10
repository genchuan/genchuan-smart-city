package cn.iocoder.yudao.module.datacenter.service.businessguidance.policyinterpretation;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessguidance.policyinterpretation.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.policyinterpretation.PolicyInterpretationInfoDO;

import javax.validation.Valid;
import java.util.List;

/**
 * 政策解读信息 Service 接口
 *
 * @author Gyh
 */
public interface PolicyInterpretationInfoService {

    /**
     * 创建政策解读信息
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPolicyInterpretationInfo(@Valid PolicyInterpretationInfoCreateReqVO createReqVO);

    /**
     * 更新政策解读信息
     *
     * @param updateReqVO 更新信息
     */
    void updatePolicyInterpretationInfo(@Valid PolicyInterpretationInfoUpdateReqVO updateReqVO);

    /**
     * 删除政策解读信息
     *
     * @param id 编号
     */
    void deletePolicyInterpretationInfo(Long id);

    /**
     * 获得政策解读信息
     *
     * @param id 编号
     * @return 政策解读信息
     */
    PolicyInterpretationInfoDO getPolicyInterpretationInfo(Long id);

    /**
     * 获得政策解读信息分页
     *
     * @param pageReqVO 分页查询
     * @return 政策解读信息分页
     */
    PageResult<PolicyInterpretationInfoDO> getPolicyInterpretationInfoPage(@Valid PolicyInterpretationInfoPageReqVO pageReqVO);

    /**
     * 获得所有政策解读信息列表
     *
     * @return 政策解读信息列表
     */
    List<PolicyInterpretationInfoDO> getPolicyInterpretationInfoList();

    /**
     * 获得所有已发布的政策解读列表
     *
     * @return 政策解读信息列表
     */
    List<PolicyInterpretationInfoDO> getPublishedPolicyInterpretationInfoList();

    /**
     * 根据关联政策ID查询已发布的解读列表
     *
     * @param relatedPolicyId 关联政策ID
     * @return 政策解读信息列表
     */
    List<PolicyInterpretationInfoDO> getPublishedInterpretationByPolicyId(String relatedPolicyId);

    /**
     * 检查解读标题是否唯一
     *
     * @param interpretationTitle 解读标题
     * @param id 编号（排除自身）
     * @return 是否唯一
     */
    boolean checkInterpretationTitleUnique(String interpretationTitle, Long id);

    /**
     * 更新解读状态
     *
     * @param id 解读ID
     * @param interpretationStatus 解读状态
     * @param offShelfReason 下架原因
     */
    void updateInterpretationStatus(Long id, String interpretationStatus, String offShelfReason);

    /**
     * 自动下架关联政策已失效的解读
     */
    void autoOffShelfInvalidInterpretations();

    /**
     * 根据解读类型查询解读列表
     *
     * @param interpretationType 解读类型
     * @return 政策解读信息列表
     */
    List<PolicyInterpretationInfoDO> getPolicyInterpretationInfoListByType(String interpretationType);

    /**
     * 批量下架解读
     *
     * @param ids ID列表
     * @param offShelfReason 下架原因
     */
    void offShelfInterpretationBatch(List<Long> ids, String offShelfReason);
}