package cn.iocoder.yudao.module.datacenter.dal.mysql.alarm.region.earlywarnmatter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.alarm.region.earlywarnmatter.vo.EarlyWarnMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.alarm.region.earlywarnmatter.EarlyWarnMatterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 按管理事项预警告警统计 Mapper
 *
 * @author zcq
 */
@Mapper
public interface EarlyWarnMatterMapper extends BaseMapperX<EarlyWarnMatterDO> {

    default PageResult<EarlyWarnMatterDO> selectPage(EarlyWarnMatterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<EarlyWarnMatterDO>()
                .eqIfPresent(EarlyWarnMatterDO::getWarnMngMatterStatId, reqVO.getWarnMngMatterStatId())
                .eqIfPresent(EarlyWarnMatterDO::getStatCycle, reqVO.getStatCycle())
                .eqIfPresent(EarlyWarnMatterDO::getMatterMajorId, reqVO.getMatterMajorId())
                .likeIfPresent(EarlyWarnMatterDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(EarlyWarnMatterDO::getMatterMinorId, reqVO.getMatterMinorId())
                .likeIfPresent(EarlyWarnMatterDO::getMatterMinorName, reqVO.getMatterMinorName())
                .eqIfPresent(EarlyWarnMatterDO::getTotalWarnCount, reqVO.getTotalWarnCount())
                .eqIfPresent(EarlyWarnMatterDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(EarlyWarnMatterDO::getOvertimeCount, reqVO.getOvertimeCount())
                .eqIfPresent(EarlyWarnMatterDO::getHandleRate, reqVO.getHandleRate())
                .eqIfPresent(EarlyWarnMatterDO::getMainDeptCode, reqVO.getMainDeptCode())
                .likeIfPresent(EarlyWarnMatterDO::getMainDeptName, reqVO.getMainDeptName())
                .eqIfPresent(EarlyWarnMatterDO::getStatUserId, reqVO.getStatUserId())
                .betweenIfPresent(EarlyWarnMatterDO::getStatTime, reqVO.getStatTime())
                .eqIfPresent(EarlyWarnMatterDO::getExtCat1, reqVO.getExtCat1())
                .eqIfPresent(EarlyWarnMatterDO::getExtCat2, reqVO.getExtCat2())
                .eqIfPresent(EarlyWarnMatterDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(EarlyWarnMatterDO::getExtCommon2, reqVO.getExtCommon2())
                .betweenIfPresent(EarlyWarnMatterDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(EarlyWarnMatterDO::getId));
    }

}