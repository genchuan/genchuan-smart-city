package cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifyaccept;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifyaccept.vo.InspectRectifyAcceptPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifyaccept.InspectRectifyAcceptDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 巡查巡检整改结果验收 Mapper
 *
 * @author zcq
 */
@Mapper
public interface InspectRectifyAcceptMapper extends BaseMapperX<InspectRectifyAcceptDO> {

    default PageResult<InspectRectifyAcceptDO> selectPage(InspectRectifyAcceptPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<InspectRectifyAcceptDO>()
                .eqIfPresent(InspectRectifyAcceptDO::getAcceptId, reqVO.getAcceptId())
                .eqIfPresent(InspectRectifyAcceptDO::getAcceptCode, reqVO.getAcceptCode())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifyTaskId, reqVO.getRectifyTaskId())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifyTaskCode, reqVO.getRectifyTaskCode())
                .eqIfPresent(InspectRectifyAcceptDO::getProblemId, reqVO.getProblemId())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifyResultDesc, reqVO.getRectifyResultDesc())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifyAfterPhotoUrls, reqVO.getRectifyAfterPhotoUrls())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifyBeforePhotoUrls, reqVO.getRectifyBeforePhotoUrls())
                .eqIfPresent(InspectRectifyAcceptDO::getAcceptResult, reqVO.getAcceptResult())
                .eqIfPresent(InspectRectifyAcceptDO::getAcceptOpinion, reqVO.getAcceptOpinion())
                .eqIfPresent(InspectRectifyAcceptDO::getAcceptUser, reqVO.getAcceptUser())
                .betweenIfPresent(InspectRectifyAcceptDO::getAcceptTime, reqVO.getAcceptTime())
                .eqIfPresent(InspectRectifyAcceptDO::getRectifySubmitUser, reqVO.getRectifySubmitUser())
                .betweenIfPresent(InspectRectifyAcceptDO::getRectifySubmitTime, reqVO.getRectifySubmitTime())
                .eqIfPresent(InspectRectifyAcceptDO::getTaskStatusChange, reqVO.getTaskStatusChange())
                .eqIfPresent(InspectRectifyAcceptDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(InspectRectifyAcceptDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(InspectRectifyAcceptDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(InspectRectifyAcceptDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(InspectRectifyAcceptDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(InspectRectifyAcceptDO::getId));
    }

}