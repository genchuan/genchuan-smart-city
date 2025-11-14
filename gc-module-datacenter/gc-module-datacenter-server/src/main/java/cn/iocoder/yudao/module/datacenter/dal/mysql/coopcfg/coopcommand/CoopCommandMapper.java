package cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopcommand;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopcommand.CoopCommandDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 联动指令 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface CoopCommandMapper extends BaseMapperX<CoopCommandDO> {

    default PageResult<CoopCommandDO> selectPage(CoopCommandPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CoopCommandDO>()
                .eqIfPresent(CoopCommandDO::getCommandId, reqVO.getCommandId())
                .eqIfPresent(CoopCommandDO::getCommandNo, reqVO.getCommandNo())
                .likeIfPresent(CoopCommandDO::getCommandName, reqVO.getCommandName())
                .eqIfPresent(CoopCommandDO::getFlowId, reqVO.getFlowId())
                .eqIfPresent(CoopCommandDO::getEvtCode, reqVO.getEvtCode())
                .eqIfPresent(CoopCommandDO::getUnitId, reqVO.getUnitId())
                .orderByDesc(CoopCommandDO::getId));
    }

}