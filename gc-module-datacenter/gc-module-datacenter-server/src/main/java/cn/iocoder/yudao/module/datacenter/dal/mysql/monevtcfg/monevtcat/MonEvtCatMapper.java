package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtcat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcat.vo.MonEvtCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcat.MonEvtCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件分类配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtCatMapper extends BaseMapperX<MonEvtCatDO> {

    default PageResult<MonEvtCatDO> selectPage(MonEvtCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtCatDO>()
                .eqIfPresent(MonEvtCatDO::getMonEvtCatId, reqVO.getMonEvtCatId())
                .eqIfPresent(MonEvtCatDO::getParentEvtId, reqVO.getParentEvtId())
                .eqIfPresent(MonEvtCatDO::getEvtCatLevel, reqVO.getEvtCatLevel())
                .eqIfPresent(MonEvtCatDO::getEvtCatCode, reqVO.getEvtCatCode())
                .likeIfPresent(MonEvtCatDO::getEvtCatName, reqVO.getEvtCatName())
                .eqIfPresent(MonEvtCatDO::getEvtCatDesc, reqVO.getEvtCatDesc())
                .eqIfPresent(MonEvtCatDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(MonEvtCatDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(MonEvtCatDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MonEvtCatDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(MonEvtCatDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(MonEvtCatDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(MonEvtCatDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(MonEvtCatDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(MonEvtCatDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonEvtCatDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonEvtCatDO::getId));
    }

}