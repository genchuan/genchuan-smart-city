package cn.iocoder.yudao.module.chargepark.marketop.service.pointactivity.ruleconfig;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.pointactivity.ruleconfig.vo.RuleConfigUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.pointactivity.RuleConfigDO;
import jakarta.validation.Valid;

public interface RuleConfigService {

    PageResult<RuleConfigDO> getPage(RuleConfigPageReqVO reqVO);

    RuleConfigDO get(Long id);

    Long create(@Valid RuleConfigCreateReqVO reqVO);

    void update(@Valid RuleConfigUpdateReqVO reqVO);

    void enable(Long id);

    void disable(Long id);

    RuleConfigChartRespVO getChart(String timeRange);

}
