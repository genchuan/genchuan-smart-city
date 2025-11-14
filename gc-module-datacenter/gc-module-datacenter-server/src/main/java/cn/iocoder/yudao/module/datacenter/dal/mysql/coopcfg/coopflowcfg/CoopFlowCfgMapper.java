package cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopflowcfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopflowcfg.vo.CoopFlowCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopflowcfg.CoopFlowCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联动流程配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CoopFlowCfgMapper extends BaseMapperX<CoopFlowCfgDO> {

    default PageResult<CoopFlowCfgDO> selectPage(CoopFlowCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CoopFlowCfgDO>()
                .eqIfPresent(CoopFlowCfgDO::getFlowCfgId, reqVO.getFlowCfgId())
                .likeIfPresent(CoopFlowCfgDO::getFlowCfgName, reqVO.getFlowCfgName())
                .eqIfPresent(CoopFlowCfgDO::getFlowSteps, reqVO.getFlowSteps())
                .eqIfPresent(CoopFlowCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(CoopFlowCfgDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(CoopFlowCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .orderByDesc(CoopFlowCfgDO::getId));
    }

}