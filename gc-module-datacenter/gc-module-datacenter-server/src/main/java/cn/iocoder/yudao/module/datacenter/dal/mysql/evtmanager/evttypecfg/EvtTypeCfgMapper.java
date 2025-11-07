package cn.iocoder.yudao.module.datacenter.dal.mysql.evtmanager.evttypecfg;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evtmanager.evttypecfg.vo.EvtTypeCfgPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evtmanager.evttypecfg.EvtTypeCfgDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 事件类型配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface EvtTypeCfgMapper extends BaseMapperX<EvtTypeCfgDO> {

    default PageResult<EvtTypeCfgDO> selectPage(EvtTypeCfgPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvtTypeCfgDO>()
                .eqIfPresent(EvtTypeCfgDO::getEvtTypeId, reqVO.getEvtTypeId())
                .eqIfPresent(EvtTypeCfgDO::getParentTypeId, reqVO.getParentTypeId())
                .eqIfPresent(EvtTypeCfgDO::getTypeLevel, reqVO.getTypeLevel())
                .eqIfPresent(EvtTypeCfgDO::getTypeCode, reqVO.getTypeCode())
                .likeIfPresent(EvtTypeCfgDO::getTypeName, reqVO.getTypeName())
                .eqIfPresent(EvtTypeCfgDO::getTypeDesc, reqVO.getTypeDesc())
                .eqIfPresent(EvtTypeCfgDO::getRegionCode, reqVO.getRegionCode())
                .likeIfPresent(EvtTypeCfgDO::getRegionName, reqVO.getRegionName())
                .eqIfPresent(EvtTypeCfgDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvtTypeCfgDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EvtTypeCfgDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EvtTypeCfgDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(EvtTypeCfgDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvtTypeCfgDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvtTypeCfgDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvtTypeCfgDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(EvtTypeCfgDO::getId));
    }

}