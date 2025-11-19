package cn.iocoder.yudao.module.datacenter.dal.mysql.evaluate.rule.evalrulecat;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.evaluate.rule.evalrulecat.vo.EvalRuleCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.evaluate.rule.evalrulecat.EvalRuleCatDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 规则分类管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EvalRuleCatMapper extends BaseMapperX<EvalRuleCatDO> {

    default PageResult<EvalRuleCatDO> selectPage(EvalRuleCatPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EvalRuleCatDO>()
                .eqIfPresent(EvalRuleCatDO::getRuleCatId, reqVO.getRuleCatId())
                .likeIfPresent(EvalRuleCatDO::getRuleCatName, reqVO.getRuleCatName())
                .eqIfPresent(EvalRuleCatDO::getRuleCatCode, reqVO.getRuleCatCode())
                .eqIfPresent(EvalRuleCatDO::getIdxSystemId, reqVO.getIdxSystemId())
                .likeIfPresent(EvalRuleCatDO::getIdxSystemName, reqVO.getIdxSystemName())
                .eqIfPresent(EvalRuleCatDO::getCatDesc, reqVO.getCatDesc())
                .eqIfPresent(EvalRuleCatDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(EvalRuleCatDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(EvalRuleCatDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(EvalRuleCatDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(EvalRuleCatDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EvalRuleCatDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EvalRuleCatDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EvalRuleCatDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(EvalRuleCatDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(EvalRuleCatDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(EvalRuleCatDO::getId));
    }

}