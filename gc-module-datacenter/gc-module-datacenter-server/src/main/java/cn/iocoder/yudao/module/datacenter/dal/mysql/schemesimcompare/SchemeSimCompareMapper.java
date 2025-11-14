package cn.iocoder.yudao.module.datacenter.dal.mysql.schemesimcompare;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.schemesimcompare.SchemeSimCompareDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.datacenter.controller.admin.schemesimcompare.vo.*;

/**
 * 方案模拟对比统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SchemeSimCompareMapper extends BaseMapperX<SchemeSimCompareDO> {

    default PageResult<SchemeSimCompareDO> selectPage(SchemeSimComparePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SchemeSimCompareDO>()
                .eqIfPresent(SchemeSimCompareDO::getSimCompareId, reqVO.getSimCompareId())
                .likeIfPresent(SchemeSimCompareDO::getCompareName, reqVO.getCompareName())
                .eqIfPresent(SchemeSimCompareDO::getParticipateSchemeIds, reqVO.getParticipateSchemeIds())
                .eqIfPresent(SchemeSimCompareDO::getParticipateSchemeNames, reqVO.getParticipateSchemeNames())
                .eqIfPresent(SchemeSimCompareDO::getResInputWeight, reqVO.getResInputWeight())
                .eqIfPresent(SchemeSimCompareDO::getExpectedEffectWeight, reqVO.getExpectedEffectWeight())
                .eqIfPresent(SchemeSimCompareDO::getImplementationCycleWeight, reqVO.getImplementationCycleWeight())
                .eqIfPresent(SchemeSimCompareDO::getScheme1Id, reqVO.getScheme1Id())
                .eqIfPresent(SchemeSimCompareDO::getScheme1ResScore, reqVO.getScheme1ResScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme1EffectScore, reqVO.getScheme1EffectScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme1CycleScore, reqVO.getScheme1CycleScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme1CompositeScore, reqVO.getScheme1CompositeScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme2Id, reqVO.getScheme2Id())
                .eqIfPresent(SchemeSimCompareDO::getScheme2ResScore, reqVO.getScheme2ResScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme2EffectScore, reqVO.getScheme2EffectScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme2CycleScore, reqVO.getScheme2CycleScore())
                .eqIfPresent(SchemeSimCompareDO::getScheme2CompositeScore, reqVO.getScheme2CompositeScore())
                .eqIfPresent(SchemeSimCompareDO::getCompareConclusion, reqVO.getCompareConclusion())
                .eqIfPresent(SchemeSimCompareDO::getCompareUser, reqVO.getCompareUser())
                .betweenIfPresent(SchemeSimCompareDO::getCompareTime, reqVO.getCompareTime())
                .eqIfPresent(SchemeSimCompareDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(SchemeSimCompareDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(SchemeSimCompareDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SchemeSimCompareDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SchemeSimCompareDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(SchemeSimCompareDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(SchemeSimCompareDO::getId));
    }

}