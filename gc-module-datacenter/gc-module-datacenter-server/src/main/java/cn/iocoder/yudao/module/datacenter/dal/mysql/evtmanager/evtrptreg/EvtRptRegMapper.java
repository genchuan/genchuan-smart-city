package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evtrptreg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evtrptreg.vo.EvtRptRegPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evtrptreg.EvtRptRegDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件接报登记 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtRptRegMapper extends BaseMapperX<EvtRptRegDO> {

    default PageResult<EvtRptRegDO> selectPage(EvtRptRegPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtRptRegDO>()
                .eqIfPresent(EvtRptRegDO::getRptId, reqVO.getRptId())
                .eqIfPresent(EvtRptRegDO::getEvtCode, reqVO.getEvtCode())
                .eqIfPresent(EvtRptRegDO::getEvtTypeId, reqVO.getEvtTypeId())
                .likeIfPresent(EvtRptRegDO::getEvtTypeName, reqVO.getEvtTypeName())
                .eqIfPresent(EvtRptRegDO::getIncidentRegionCode, reqVO.getIncidentRegionCode())
                .likeIfPresent(EvtRptRegDO::getIncidentRegionName, reqVO.getIncidentRegionName())
                .eqIfPresent(EvtRptRegDO::getIncidentLocation, reqVO.getIncidentLocation())
                .eqIfPresent(EvtRptRegDO::getIncidentCoordX, reqVO.getIncidentCoordX())
                .eqIfPresent(EvtRptRegDO::getIncidentCoordY, reqVO.getIncidentCoordY())
                .eqIfPresent(EvtRptRegDO::getEvtDesc, reqVO.getEvtDesc())
                .orderByDesc(EvtRptRegDO::getId));
    }

}