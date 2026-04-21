package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.feerule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.FeeRuleSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.AddFeeRuleReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.feerule.vo.ops.FeeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.feerule.FeeRuleDO;
import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 收费规则 Service 接口
 *
 * @author 亘川智城
 */
public interface FeeRuleService {

    /**
     * 获得收费规则
     *
     * @param id 编号
     * @return 收费规则
     */
    FeeRuleDO getFeeRule(Long id);

    /**
     * 获得收费规则分页
     *
     * @param pageReqVO 分页查询
     * @return 收费规则分页
     */
    PageResult<FeeRuleDO> getFeeRulePage(FeeRulePageReqVO pageReqVO);

    void addFeeRule(AddFeeRuleReqVO reqVO);

    FeeRuleImportResp importFeeRule(MultipartFile file, boolean updateSupport);

    void updateFeeRuleBiz(FeeRuleUpdateReqVO updateReqVO);
    void enableFeeRule(List<Long> ids);

    void disableFeeRule(List<Long> ids);

    FeeRuleChartRespVO getFeeRuleChart();
}
