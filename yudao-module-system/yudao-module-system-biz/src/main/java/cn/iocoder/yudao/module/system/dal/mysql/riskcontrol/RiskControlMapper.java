package cn.iocoder.yudao.module.system.dal.mysql.riskcontrol;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.system.dal.dataobject.riskcontrol.RiskControlDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.system.controller.admin.riskcontrol.vo.*;

/**
 * 风险管控 Mapper
 *
 * @author zhucongquan
 */
@Mapper
public interface RiskControlMapper extends BaseMapperX<RiskControlDO> {

    default PageResult<RiskControlDO> selectPage(RiskControlPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RiskControlDO>()
                .likeIfPresent(RiskControlDO::getRiskName, reqVO.getRiskName())
                .eqIfPresent(RiskControlDO::getRiskDescription, reqVO.getRiskDescription())
                .eqIfPresent(RiskControlDO::getRiskLevel, reqVO.getRiskLevel())
                .eqIfPresent(RiskControlDO::getRiskStatus, reqVO.getRiskStatus())
                .orderByDesc(RiskControlDO::getId));
    }

}