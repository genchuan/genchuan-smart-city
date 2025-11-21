package cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicaisvc;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicaisvc.PublicAiSvcDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 智能客服知识库 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicAiSvcMapper extends BaseMapperX<PublicAiSvcDO> {

    default PageResult<PublicAiSvcDO> selectPage(PublicAiSvcPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicAiSvcDO>()
                .eqIfPresent(PublicAiSvcDO::getAiSvcId, reqVO.getAiSvcId())
                .eqIfPresent(PublicAiSvcDO::getQuestionTypeCode, reqVO.getQuestionTypeCode())
                .likeIfPresent(PublicAiSvcDO::getQuestionTypeName, reqVO.getQuestionTypeName())
                .eqIfPresent(PublicAiSvcDO::getQuestionKeyword, reqVO.getQuestionKeyword())
                .eqIfPresent(PublicAiSvcDO::getStdQuestion, reqVO.getStdQuestion())
                .eqIfPresent(PublicAiSvcDO::getStdAnswer, reqVO.getStdAnswer())
                .eqIfPresent(PublicAiSvcDO::getAttachPath, reqVO.getAttachPath())
                .eqIfPresent(PublicAiSvcDO::getUseCount, reqVO.getUseCount())
                .eqIfPresent(PublicAiSvcDO::getUpdateUserId, reqVO.getUpdateUserId())
                .likeIfPresent(PublicAiSvcDO::getUpdateUserName, reqVO.getUpdateUserName())
                .eqIfPresent(PublicAiSvcDO::getEnableStatus, reqVO.getEnableStatus())
                .eqIfPresent(PublicAiSvcDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicAiSvcDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicAiSvcDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicAiSvcDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicAiSvcDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicAiSvcDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicAiSvcDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicAiSvcDO::getId));
    }

}