package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.disposal.earlywarnarch;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.disposal.earlywarnarch.vo.EarlyWarnArchPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.disposal.earlywarnarch.EarlyWarnArchDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警处置归档 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnArchMapper extends BaseMapperX<EarlyWarnArchDO> {

    default PageResult<EarlyWarnArchDO> selectPage(EarlyWarnArchPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnArchDO>()
                .eqIfPresent(EarlyWarnArchDO::getArchId, reqVO.getArchId())
                .eqIfPresent(EarlyWarnArchDO::getArchNo, reqVO.getArchNo())
                .eqIfPresent(EarlyWarnArchDO::getEarlyWarnId, reqVO.getEarlyWarnId())
                .likeIfPresent(EarlyWarnArchDO::getEarlyWarnName, reqVO.getEarlyWarnName())
                .eqIfPresent(EarlyWarnArchDO::getWarnType, reqVO.getWarnType())
                .eqIfPresent(EarlyWarnArchDO::getWarnLevel, reqVO.getWarnLevel())
                .likeIfPresent(EarlyWarnArchDO::getRegionName, reqVO.getRegionName())
                .likeIfPresent(EarlyWarnArchDO::getHandleUserName, reqVO.getHandleUserName())
                .eqIfPresent(EarlyWarnArchDO::getComprehensiveScore, reqVO.getComprehensiveScore())
                .eqIfPresent(EarlyWarnArchDO::getReleaseStatus, reqVO.getReleaseStatus())
                .betweenIfPresent(EarlyWarnArchDO::getArchTime, reqVO.getArchTime())
                .eqIfPresent(EarlyWarnArchDO::getArchUserId, reqVO.getArchUserId())
                .likeIfPresent(EarlyWarnArchDO::getArchUserName, reqVO.getArchUserName())
                .eqIfPresent(EarlyWarnArchDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnArchDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnArchDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnArchDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnArchDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnArchDO::getId));
    }

}