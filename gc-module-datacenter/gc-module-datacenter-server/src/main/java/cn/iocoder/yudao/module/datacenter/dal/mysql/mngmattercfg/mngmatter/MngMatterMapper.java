package cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.mngmatter;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.mngmatter.MngMatterDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 管理事项统计 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface MngMatterMapper extends BaseMapperX<MngMatterDO> {

    default PageResult<MngMatterDO> selectPage(MngMatterPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MngMatterDO>()
                .eqIfPresent(MngMatterDO::getStatMngMatterId, reqVO.getStatMngMatterId())
                .eqIfPresent(MngMatterDO::getStatCycle, reqVO.getStatCycle())
                .likeIfPresent(MngMatterDO::getStatCycleName, reqVO.getStatCycleName())
                .eqIfPresent(MngMatterDO::getAreaCode, reqVO.getAreaCode())
                .likeIfPresent(MngMatterDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(MngMatterDO::getMatterMajorId, reqVO.getMatterMajorId())
                .likeIfPresent(MngMatterDO::getMatterMajorName, reqVO.getMatterMajorName())
                .eqIfPresent(MngMatterDO::getMatterMinorId, reqVO.getMatterMinorId())
                .likeIfPresent(MngMatterDO::getMatterMinorName, reqVO.getMatterMinorName())
                .eqIfPresent(MngMatterDO::getDeptCode, reqVO.getDeptCode())
                .likeIfPresent(MngMatterDO::getDeptName, reqVO.getDeptName())
                .eqIfPresent(MngMatterDO::getTotalRptCount, reqVO.getTotalRptCount())
                .eqIfPresent(MngMatterDO::getPendCount, reqVO.getPendCount())
                .eqIfPresent(MngMatterDO::getHandlCount, reqVO.getHandlCount())
                .eqIfPresent(MngMatterDO::getCompletedCount, reqVO.getCompletedCount())
                .eqIfPresent(MngMatterDO::getRejectedCount, reqVO.getRejectedCount())
                .eqIfPresent(MngMatterDO::getCompleteRate, reqVO.getCompleteRate())
                .eqIfPresent(MngMatterDO::getAvgHandleEndure, reqVO.getAvgHandleEndure())
                .eqIfPresent(MngMatterDO::getCreateTimeSys, reqVO.getCreateTimeSys())
                .eqIfPresent(MngMatterDO::getUpdateTimeSys, reqVO.getUpdateTimeSys())
                .orderByDesc(MngMatterDO::getId));
    }

}