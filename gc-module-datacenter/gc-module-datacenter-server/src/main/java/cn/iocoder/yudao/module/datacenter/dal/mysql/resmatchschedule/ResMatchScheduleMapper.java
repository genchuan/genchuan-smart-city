package cn.iocoder.yudao.module.datacenter.dal.mysql.resmatchschedule;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.resmatchschedule.ResMatchScheduleDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.resmatchschedule.vo.*;

/**
 * 资源匹配调度 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface ResMatchScheduleMapper extends BaseMapperX<ResMatchScheduleDO> {

    default PageResult<ResMatchScheduleDO> selectPage(ResMatchSchedulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ResMatchScheduleDO>()
                .eqIfPresent(ResMatchScheduleDO::getResScheduleId, reqVO.getResScheduleId())
                .eqIfPresent(ResMatchScheduleDO::getRelSchemeId, reqVO.getRelSchemeId())
                .likeIfPresent(ResMatchScheduleDO::getRelSchemeName, reqVO.getRelSchemeName())
                .eqIfPresent(ResMatchScheduleDO::getResDemandDetail, reqVO.getResDemandDetail())
                .eqIfPresent(ResMatchScheduleDO::getMatchedResDetail, reqVO.getMatchedResDetail())
                .eqIfPresent(ResMatchScheduleDO::getScheduleStatus, reqVO.getScheduleStatus())
                .eqIfPresent(ResMatchScheduleDO::getMatchFailReason, reqVO.getMatchFailReason())
                .eqIfPresent(ResMatchScheduleDO::getScheduleWoId, reqVO.getScheduleWoId())
                .eqIfPresent(ResMatchScheduleDO::getScheduleUser, reqVO.getScheduleUser())
                .betweenIfPresent(ResMatchScheduleDO::getScheduleTime, reqVO.getScheduleTime())
                .betweenIfPresent(ResMatchScheduleDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(ResMatchScheduleDO::getResMngr, reqVO.getResMngr())
                .likeIfPresent(ResMatchScheduleDO::getResMngrName, reqVO.getResMngrName())
                .eqIfPresent(ResMatchScheduleDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(ResMatchScheduleDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(ResMatchScheduleDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(ResMatchScheduleDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(ResMatchScheduleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(ResMatchScheduleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(ResMatchScheduleDO::getId));
    }

}