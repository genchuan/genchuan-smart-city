package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtext;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtext.vo.MonEvtExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtext.MonEvtExtDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 扩展监测事件配置 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtExtMapper extends BaseMapperX<MonEvtExtDO> {

    default PageResult<MonEvtExtDO> selectPage(MonEvtExtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtExtDO>()
                .eqIfPresent(MonEvtExtDO::getMonEvtExtId, reqVO.getMonEvtExtId())
                .eqIfPresent(MonEvtExtDO::getExtMinorCode, reqVO.getExtMinorCode())
                .likeIfPresent(MonEvtExtDO::getExtMinorName, reqVO.getExtMinorName())
                .eqIfPresent(MonEvtExtDO::getParentMidId, reqVO.getParentMidId())
                .likeIfPresent(MonEvtExtDO::getParentMidName, reqVO.getParentMidName())
                .eqIfPresent(MonEvtExtDO::getRelCompCatId, reqVO.getRelCompCatId())
                .likeIfPresent(MonEvtExtDO::getRelCompCatName, reqVO.getRelCompCatName())
                .eqIfPresent(MonEvtExtDO::getExtDesc, reqVO.getExtDesc())
                .eqIfPresent(MonEvtExtDO::getApplyReason, reqVO.getApplyReason())
                .eqIfPresent(MonEvtExtDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonEvtExtDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonEvtExtDO::getId));
    }

}