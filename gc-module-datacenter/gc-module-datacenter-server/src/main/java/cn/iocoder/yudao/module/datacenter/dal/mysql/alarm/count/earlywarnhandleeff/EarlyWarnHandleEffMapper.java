package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.count.earlywarnhandleeff;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.count.earlywarnhandleeff.vo.EarlyWarnHandleEffPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.count.earlywarnhandleeff.EarlyWarnHandleEffDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 预警告警处置效率统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnHandleEffMapper extends BaseMapperX<EarlyWarnHandleEffDO> {

    default PageResult<EarlyWarnHandleEffDO> selectPage(EarlyWarnHandleEffPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnHandleEffDO>()
                .eqIfPresent(EarlyWarnHandleEffDO::getEfficacyStatId, reqVO.getEfficacyStatId())
                .eqIfPresent(EarlyWarnHandleEffDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnHandleEffDO::getHandleDeptId, reqVO.getHandleDeptId())
                .likeIfPresent(EarlyWarnHandleEffDO::getHandleDeptName, reqVO.getHandleDeptName())
                .eqIfPresent(EarlyWarnHandleEffDO::getTotalHandleCount, reqVO.getTotalHandleCount())
                .eqIfPresent(EarlyWarnHandleEffDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnHandleEffDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnHandleEffDO::getAvgHandleEndure, reqVO.getAvgHandleEndure())
                .eqIfPresent(EarlyWarnHandleEffDO::getCriticalAvgEndure, reqVO.getCriticalAvgEndure())
                .eqIfPresent(EarlyWarnHandleEffDO::getMainHandleType, reqVO.getMainHandleType())
                .eqIfPresent(EarlyWarnHandleEffDO::getMainTypeProportion, reqVO.getMainTypeProportion())
                .eqIfPresent(EarlyWarnHandleEffDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnHandleEffDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnHandleEffDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnHandleEffDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnHandleEffDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnHandleEffDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnHandleEffDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnHandleEffDO::getId));
    }

}