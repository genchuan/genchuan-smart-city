package cn.iocoder.yudao.module.smartcity.service.policyandregulationinput;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo.PolicyAndRegulationInputPageReqVO;
import cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo.PolicyAndRegulationInputSaveReqVO;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.policyandregulationinput.PolicyAndRegulationInputDO;
import jakarta.validation.Valid;

/**
 * 政策法规录入 Service 接口
 *
 * @author 智慧城市运行管理服务平台
 */
public interface PolicyAndRegulationInputService {

    /**
     * 创建政策法规录入
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createPolicyAndRegulationInput(@Valid PolicyAndRegulationInputSaveReqVO createReqVO);

    /**
     * 更新政策法规录入
     *
     * @param updateReqVO 更新信息
     */
    void updatePolicyAndRegulationInput(@Valid PolicyAndRegulationInputSaveReqVO updateReqVO);

    /**
     * 删除政策法规录入
     *
     * @param id 编号
     */
    void deletePolicyAndRegulationInput(Long id);

    /**
     * 获得政策法规录入
     *
     * @param id 编号
     * @return 政策法规录入
     */
    PolicyAndRegulationInputDO getPolicyAndRegulationInput(Long id);

    /**
     * 获得政策法规录入分页
     *
     * @param pageReqVO 分页查询
     * @return 政策法规录入分页
     */
    PageResult<PolicyAndRegulationInputDO> getPolicyAndRegulationInputPage(PolicyAndRegulationInputPageReqVO pageReqVO);

}