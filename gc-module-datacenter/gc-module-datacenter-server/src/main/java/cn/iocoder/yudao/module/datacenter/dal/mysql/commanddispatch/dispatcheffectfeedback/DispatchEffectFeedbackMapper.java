package cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatcheffectfeedback;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatcheffectfeedback.vo.DispatchEffectFeedbackPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatcheffectfeedback.DispatchEffectFeedbackDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 调度效果反馈 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface DispatchEffectFeedbackMapper extends BaseMapperX<DispatchEffectFeedbackDO> {

    default PageResult<DispatchEffectFeedbackDO> selectPage(DispatchEffectFeedbackPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DispatchEffectFeedbackDO>()
                .eqIfPresent(DispatchEffectFeedbackDO::getFeedbackId, reqVO.getFeedbackId())
                .eqIfPresent(DispatchEffectFeedbackDO::getCommandId, reqVO.getCommandId())
                .eqIfPresent(DispatchEffectFeedbackDO::getCommandNo, reqVO.getCommandNo())
                .eqIfPresent(DispatchEffectFeedbackDO::getExecUnitId, reqVO.getExecUnitId())
                .likeIfPresent(DispatchEffectFeedbackDO::getExecUnitName, reqVO.getExecUnitName())
                .eqIfPresent(DispatchEffectFeedbackDO::getEffectDesc, reqVO.getEffectDesc())
                .eqIfPresent(DispatchEffectFeedbackDO::getFeedbackAttachPath, reqVO.getFeedbackAttachPath())
                .eqIfPresent(DispatchEffectFeedbackDO::getFeedbackUserId, reqVO.getFeedbackUserId())
                .likeIfPresent(DispatchEffectFeedbackDO::getFeedbackUserName, reqVO.getFeedbackUserName())
                .betweenIfPresent(DispatchEffectFeedbackDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(DispatchEffectFeedbackDO::getEvalResult, reqVO.getEvalResult())
                .eqIfPresent(DispatchEffectFeedbackDO::getEvalDesc, reqVO.getEvalDesc())
                .eqIfPresent(DispatchEffectFeedbackDO::getEvalUserId, reqVO.getEvalUserId())
                .betweenIfPresent(DispatchEffectFeedbackDO::getEvalTime, reqVO.getEvalTime())
                .eqIfPresent(DispatchEffectFeedbackDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(DispatchEffectFeedbackDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(DispatchEffectFeedbackDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(DispatchEffectFeedbackDO::getExtCommon2, reqVO.getExtCommon2())
                .orderByDesc(DispatchEffectFeedbackDO::getId));
    }

}