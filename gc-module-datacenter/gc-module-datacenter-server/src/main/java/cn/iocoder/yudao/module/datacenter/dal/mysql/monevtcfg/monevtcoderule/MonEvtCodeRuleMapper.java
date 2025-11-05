package cn.iocoder.yudao.module.datacenter.dal.mysql.monevtcfg.monevtcoderule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtcfg.monevtcoderule.vo.MonEvtCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtcfg.monevtcoderule.MonEvtCodeRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测事件标识码规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonEvtCodeRuleMapper extends BaseMapperX<MonEvtCodeRuleDO> {

    default PageResult<MonEvtCodeRuleDO> selectPage(MonEvtCodeRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonEvtCodeRuleDO>()
                .eqIfPresent(MonEvtCodeRuleDO::getMonEvtRuleId, reqVO.getMonEvtRuleId())
                .likeIfPresent(MonEvtCodeRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(MonEvtCodeRuleDO::getAdminCodeLen, reqVO.getAdminCodeLen())
                .eqIfPresent(MonEvtCodeRuleDO::getMajorCodeLen, reqVO.getMajorCodeLen())
                .eqIfPresent(MonEvtCodeRuleDO::getMidCodeLen, reqVO.getMidCodeLen())
                .eqIfPresent(MonEvtCodeRuleDO::getMinorCodeLen, reqVO.getMinorCodeLen())
                .eqIfPresent(MonEvtCodeRuleDO::getSeqCodeLen, reqVO.getSeqCodeLen())
                .eqIfPresent(MonEvtCodeRuleDO::getSeqGenRule, reqVO.getSeqGenRule())
                .eqIfPresent(MonEvtCodeRuleDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(MonEvtCodeRuleDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(MonEvtCodeRuleDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(MonEvtCodeRuleDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(MonEvtCodeRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonEvtCodeRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonEvtCodeRuleDO::getId));
    }

}