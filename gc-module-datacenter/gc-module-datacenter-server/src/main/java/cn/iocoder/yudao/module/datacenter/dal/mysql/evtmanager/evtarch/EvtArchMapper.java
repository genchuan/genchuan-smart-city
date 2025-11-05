package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtarch.vo.EvtArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtarch.EvtArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件办结归档 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtArchMapper extends BaseMapperX<EvtArchDO> {

    default PageResult<EvtArchDO> selectPage(EvtArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtArchDO>()
                .eqIfPresent(EvtArchDO::getArchId, reqVO.getArchId())
                .eqIfPresent(EvtArchDO::getWoId, reqVO.getWoId())
                .eqIfPresent(EvtArchDO::getEvtCode, reqVO.getEvtCode())
                .betweenIfPresent(EvtArchDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(EvtArchDO::getCompleteDesc, reqVO.getCompleteDesc())
                .orderByDesc(EvtArchDO::getId));
    }

}