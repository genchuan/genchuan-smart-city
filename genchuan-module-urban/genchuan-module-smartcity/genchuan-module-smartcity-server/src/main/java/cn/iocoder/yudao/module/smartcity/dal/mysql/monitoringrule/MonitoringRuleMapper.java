package cn.iocoder.yudao.module.smartcity.dal.mysql.monitoringrule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.monitoringrule.MonitoringRuleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.monitoringrule.vo.*;

/**
 * 监测项规则设置 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface MonitoringRuleMapper extends BaseMapperX<MonitoringRuleDO> {

    default PageResult<MonitoringRuleDO> selectPage(MonitoringRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonitoringRuleDO>()
                .likeIfPresent(MonitoringRuleDO::getWaterLevelOverlimitWarning, reqVO.getWaterLevelOverlimitWarning())
                .likeIfPresent(MonitoringRuleDO::getFlowAbnormalityWarning, reqVO.getFlowAbnormalityWarning())
                .likeIfPresent(MonitoringRuleDO::getWaterQualityExceedanceWarning, reqVO.getWaterQualityExceedanceWarning())
                .betweenIfPresent(MonitoringRuleDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MonitoringRuleDO::getId));
    }

}