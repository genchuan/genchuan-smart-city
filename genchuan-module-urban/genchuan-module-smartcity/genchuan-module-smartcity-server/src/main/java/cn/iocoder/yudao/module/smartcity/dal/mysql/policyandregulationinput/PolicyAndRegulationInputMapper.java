package cn.iocoder.yudao.module.smartcity.dal.mysql.policyandregulationinput;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.policyandregulationinput.PolicyAndRegulationInputDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.policyandregulationinput.vo.*;

/**
 * 政策法规录入 Mapper
 *
 * @author 智慧城市运行管理服务平台
 */
@Mapper
public interface PolicyAndRegulationInputMapper extends BaseMapperX<PolicyAndRegulationInputDO> {

    default PageResult<PolicyAndRegulationInputDO> selectPage(PolicyAndRegulationInputPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PolicyAndRegulationInputDO>()
                .eqIfPresent(PolicyAndRegulationInputDO::getDevelopingAgencies, reqVO.getDevelopingAgencies())
                .eqIfPresent(PolicyAndRegulationInputDO::getNameOfPolicyAndRegulation, reqVO.getNameOfPolicyAndRegulation())
                .eqIfPresent(PolicyAndRegulationInputDO::getDocumentNumber, reqVO.getDocumentNumber())
                .eqIfPresent(PolicyAndRegulationInputDO::getMainContent, reqVO.getMainContent())
                .betweenIfPresent(PolicyAndRegulationInputDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(PolicyAndRegulationInputDO::getId));
    }

}