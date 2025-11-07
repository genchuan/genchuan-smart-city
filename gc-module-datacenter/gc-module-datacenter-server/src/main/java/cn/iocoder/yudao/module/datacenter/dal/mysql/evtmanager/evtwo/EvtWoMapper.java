package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtwo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtwo.vo.EvtWoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtwo.EvtWoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件工单 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtWoMapper extends BaseMapperX<EvtWoDO> {

    default PageResult<EvtWoDO> selectPage(EvtWoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtWoDO>()
                .eqIfPresent(EvtWoDO::getWoId, reqVO.getWoId())
                .eqIfPresent(EvtWoDO::getWoNo, reqVO.getWoNo())
                .eqIfPresent(EvtWoDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(EvtWoDO::getEvtTypeName, reqVO.getEvtTypeName())
                .eqIfPresent(EvtWoDO::getAllocateDeptCode, reqVO.getAllocateDeptCode())
                .likeIfPresent(EvtWoDO::getAllocateDeptName, reqVO.getAllocateDeptName())
                .eqIfPresent(EvtWoDO::getWoStatus, reqVO.getWoStatus())
                .eqIfPresent(EvtWoDO::getChargerId, reqVO.getChargerId())
                .likeIfPresent(EvtWoDO::getChargerName, reqVO.getChargerName())
                .orderByDesc(EvtWoDO::getId));
    }

}