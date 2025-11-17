package cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.faq;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.faq.FaqDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 常见问题解答 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface FaqMapper extends BaseMapperX<FaqDO> {

    default PageResult<FaqDO> selectPage(FaqPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FaqDO>()
                .eqIfPresent(FaqDO::getFaqId, reqVO.getFaqId())
                .eqIfPresent(FaqDO::getQuestionTypeCode, reqVO.getQuestionTypeCode())
                .likeIfPresent(FaqDO::getQuestionTypeName, reqVO.getQuestionTypeName())
                .eqIfPresent(FaqDO::getQuestionDesc, reqVO.getQuestionDesc())
                .eqIfPresent(FaqDO::getAnswerSolution, reqVO.getAnswerSolution())
                .eqIfPresent(FaqDO::getRelatedGuideId, reqVO.getRelatedGuideId())
                .likeIfPresent(FaqDO::getRelatedGuideName, reqVO.getRelatedGuideName())
                .eqIfPresent(FaqDO::getSortNum, reqVO.getSortNum())
                .eqIfPresent(FaqDO::getIsTop, reqVO.getIsTop())
                .eqIfPresent(FaqDO::getViewCount, reqVO.getViewCount())
                .eqIfPresent(FaqDO::getCreateUser, reqVO.getCreateUser())
                .betweenIfPresent(FaqDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(FaqDO::getUpdateUser, reqVO.getUpdateUser())
                .eqIfPresent(FaqDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(FaqDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(FaqDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(FaqDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(FaqDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(FaqDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(FaqDO::getId));
    }

}