package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.heart.inspectsupvfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.heart.inspectsupvfeedback.vo.InspectSupvFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.heart.inspectsupvfeedback.InspectSupvFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检督办结果反馈 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectSupvFeedbackMapper extends BaseMapperX<InspectSupvFeedbackDO> {

    default PageResult<InspectSupvFeedbackDO> selectPage(InspectSupvFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectSupvFeedbackDO>()
                .eqIfPresent(InspectSupvFeedbackDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(InspectSupvFeedbackDO::getFeedbackCode, reqVO.getFeedbackCode())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvTaskId, reqVO.getSupvTaskId())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvTaskCode, reqVO.getSupvTaskCode())
                .likeIfPresent(InspectSupvFeedbackDO::getProblemName, reqVO.getProblemName())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvResult, reqVO.getSupvResult())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvResultDesc, reqVO.getSupvResultDesc())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvFeedbackOpinion, reqVO.getSupvFeedbackOpinion())
                .eqIfPresent(InspectSupvFeedbackDO::getRectifyAcceptResult, reqVO.getRectifyAcceptResult())
                .eqIfPresent(InspectSupvFeedbackDO::getFeedbackUser, reqVO.getFeedbackUser())
                .betweenIfPresent(InspectSupvFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(InspectSupvFeedbackDO::getSupvStatusChange, reqVO.getSupvStatusChange())
                .eqIfPresent(InspectSupvFeedbackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectSupvFeedbackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectSupvFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectSupvFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectSupvFeedbackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectSupvFeedbackDO::getId));
    }

}