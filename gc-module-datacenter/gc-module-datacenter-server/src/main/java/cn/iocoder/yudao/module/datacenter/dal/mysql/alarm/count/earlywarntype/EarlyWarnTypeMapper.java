package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarntype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarntype.vo.EarlyWarnTypePageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarntype.EarlyWarnTypeDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警类型维度统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnTypeMapper extends BaseMapperX<EarlyWarnTypeDO> {

    default PageResult<EarlyWarnTypeDO> selectPage(EarlyWarnTypePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnTypeDO>()
                .eqIfPresent(EarlyWarnTypeDO::getTypeStatId, reqVO.getTypeStatId())
                .eqIfPresent(EarlyWarnTypeDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnTypeDO::getWarnType, reqVO.getWarnType())
                .eqIfPresent(EarlyWarnTypeDO::getWarnTypeCode, reqVO.getWarnTypeCode())
                .eqIfPresent(EarlyWarnTypeDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnTypeDO::getProportion, reqVO.getProportion())
                .eqIfPresent(EarlyWarnTypeDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnTypeDO::getHandleRate, reqVO.getHandleRate())
                .likeIfPresent(EarlyWarnTypeDO::getMainRegionName, reqVO.getMainRegionName())
                .eqIfPresent(EarlyWarnTypeDO::getMainRegionCount, reqVO.getMainRegionCount())
                .eqIfPresent(EarlyWarnTypeDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnTypeDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnTypeDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnTypeDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnTypeDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnTypeDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnTypeDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnTypeDO::getId));
    }

}