package cn.iocoder.yudao.module.evaluate.dal.mysql.appealfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalresult.appealfeedback.vo.AppealFeedbackPageReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.appealfeedback.AppealFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 申诉反馈 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface AppealFeedbackMapper extends BaseMapperX<AppealFeedbackDO> {

    default PageResult<AppealFeedbackDO> selectPage(AppealFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AppealFeedbackDO>()
                .eqIfPresent(AppealFeedbackDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(AppealFeedbackDO::getAppealId, reqVO.getAppealId())
                .eqIfPresent(AppealFeedbackDO::getFeedbackContent, reqVO.getFeedbackContent())
                .betweenIfPresent(AppealFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(AppealFeedbackDO::getFeedbackBy, reqVO.getFeedbackBy())
                .eqIfPresent(AppealFeedbackDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AppealFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(AppealFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(AppealFeedbackDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(AppealFeedbackDO::getExtCommon4, reqVO.getExtCommon4())
                .betweenIfPresent(AppealFeedbackDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AppealFeedbackDO::getId));
    }

}