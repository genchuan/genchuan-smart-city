package cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicopinioncollect;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicopinioncollect.PublicOpinionCollectDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 民意征集 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface PublicOpinionCollectMapper extends BaseMapperX<PublicOpinionCollectDO> {

    default PageResult<PublicOpinionCollectDO> selectPage(PublicOpinionCollectPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<PublicOpinionCollectDO>()
                .eqIfPresent(PublicOpinionCollectDO::getCollectId, reqVO.getCollectId())
                .eqIfPresent(PublicOpinionCollectDO::getCollectTopic, reqVO.getCollectTopic())
                .eqIfPresent(PublicOpinionCollectDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(PublicOpinionCollectDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(PublicOpinionCollectDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(PublicOpinionCollectDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(PublicOpinionCollectDO::getCollectContent, reqVO.getCollectContent())
                .betweenIfPresent(PublicOpinionCollectDO::getStartTime, reqVO.getStartTime())
                .betweenIfPresent(PublicOpinionCollectDO::getEndTime, reqVO.getEndTime())
                .eqIfPresent(PublicOpinionCollectDO::getCollectStatus, reqVO.getCollectStatus())
                .eqIfPresent(PublicOpinionCollectDO::getResultContent, reqVO.getResultContent())
                .betweenIfPresent(PublicOpinionCollectDO::getPublishResultTime, reqVO.getPublishResultTime())
                .eqIfPresent(PublicOpinionCollectDO::getPublishUserId, reqVO.getPublishUserId())
                .betweenIfPresent(PublicOpinionCollectDO::getPublishTime, reqVO.getPublishTime())
                .eqIfPresent(PublicOpinionCollectDO::getRemark, reqVO.getRemark())
                .eqIfPresent(PublicOpinionCollectDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(PublicOpinionCollectDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(PublicOpinionCollectDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(PublicOpinionCollectDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(PublicOpinionCollectDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(PublicOpinionCollectDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(PublicOpinionCollectDO::getId));
    }

}