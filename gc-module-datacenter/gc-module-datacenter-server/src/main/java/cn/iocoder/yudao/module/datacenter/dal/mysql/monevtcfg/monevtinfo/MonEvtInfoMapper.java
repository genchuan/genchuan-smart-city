package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtinfo;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtinfo.vo.MonEvtInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtinfo.MonEvtInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件信息 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtInfoMapper extends BaseMapperX<MonEvtInfoDO> {

    default PageResult<MonEvtInfoDO> selectPage(MonEvtInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtInfoDO>()
                .eqIfPresent(MonEvtInfoDO::getMonEvtId, reqVO.getMonEvtId())
                .eqIfPresent(MonEvtInfoDO::getEvtCode, reqVO.getEvtCode())
                .likeIfPresent(MonEvtInfoDO::getEvtName, reqVO.getEvtName())
                .eqIfPresent(MonEvtInfoDO::getEvtCatId, reqVO.getEvtCatId())
                .eqIfPresent(MonEvtInfoDO::getRelCompId, reqVO.getRelCompId())
                .likeIfPresent(MonEvtInfoDO::getRelCompName, reqVO.getRelCompName())
                .eqIfPresent(MonEvtInfoDO::getIncidentPos, reqVO.getIncidentPos())
                .eqIfPresent(MonEvtInfoDO::getIncidentX, reqVO.getIncidentX())
                .eqIfPresent(MonEvtInfoDO::getIncidentY, reqVO.getIncidentY())
                .eqIfPresent(MonEvtInfoDO::getEvtLevel, reqVO.getEvtLevel())
                .eqIfPresent(MonEvtInfoDO::getHandleStatus, reqVO.getHandleStatus())
                .eqIfPresent(MonEvtInfoDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonEvtInfoDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonEvtInfoDO::getId));
    }

}