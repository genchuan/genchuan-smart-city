package cn.iocoder.yudao.module.stationresource.service.stationresource.rulecontrol.offtimerule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.OfftimeRulePageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleChartRespVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleCreateReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleImportResp;
import cn.iocoder.yudao.module.stationresource.controller.admin.stationresource.rulecontrol.offtimerule.vo.ops.OfftimeRuleUpdateReqVO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.stationresource.rulecontrol.offtimerule.OfftimeRuleDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 错时规则 Service 接口
 *
 * @author 亘川智城
 */
public interface OfftimeRuleService {

    /**
     * 获得错时规则
     *
     * @param id 编号
     * @return 错时规则
     */
    OfftimeRuleDO getOfftimeRule(Long id);

    /**
     * 获得错时规则分页
     *
     * @param pageReqVO 分页查询
     * @return 错时规则分页
     */
    PageResult<OfftimeRuleDO> getOfftimeRulePage(OfftimeRulePageReqVO pageReqVO);

    /**
     * 新增错时规则
     *
     * @param createReqVO 新增信息
     */
    void createOfftimeRule(OfftimeRuleCreateReqVO createReqVO);

    /**
     * 更新错时规则
     *
     * @param updateReqVO 更新信息
     */
    void updateOfftimeRule(OfftimeRuleUpdateReqVO updateReqVO);

    /**
     * 导入错时规则
     *
     * @param file 文件
     * @param updateSupport 是否支持更新
     * @return 导入结果
     */
    OfftimeRuleImportResp importOfftimeRule(MultipartFile file, boolean updateSupport);

    /**
     * 批量生效错时规则
     *
     * @param ids 编号列表
     */
    void enableOfftimeRule(List<Long> ids);

    /**
     * 批量禁用错时规则
     *
     * @param ids 编号列表
     */
    void disableOfftimeRule(List<Long> ids);

    /**
     * 获取错时规则统计图表
     *
     * @return 图表数据
     */
    OfftimeRuleChartRespVO getOfftimeRuleChart();

}
