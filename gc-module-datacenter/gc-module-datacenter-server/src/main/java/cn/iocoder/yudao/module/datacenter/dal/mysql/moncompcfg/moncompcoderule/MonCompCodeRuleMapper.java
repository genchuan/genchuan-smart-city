package cn.iocoder.yudao.module.datacenter.dal.mysql.moncompcfg.moncompcoderule;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.moncompcfg.moncompcoderule.vo.MonCompCodeRulePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.moncompcfg.moncompcoderule.MonCompCodeRuleDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 监测部件标识码规则 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MonCompCodeRuleMapper extends BaseMapperX<MonCompCodeRuleDO> {

    default PageResult<MonCompCodeRuleDO> selectPage(MonCompCodeRulePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MonCompCodeRuleDO>()
                .eqIfPresent(MonCompCodeRuleDO::getCodeRuleId, reqVO.getCodeRuleId())
                .likeIfPresent(MonCompCodeRuleDO::getRuleName, reqVO.getRuleName())
                .eqIfPresent(MonCompCodeRuleDO::getRuleDesc, reqVO.getRuleDesc())
                .eqIfPresent(MonCompCodeRuleDO::getCodeFormat, reqVO.getCodeFormat())
                .eqIfPresent(MonCompCodeRuleDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MonCompCodeRuleDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MonCompCodeRuleDO::getId));
    }

}