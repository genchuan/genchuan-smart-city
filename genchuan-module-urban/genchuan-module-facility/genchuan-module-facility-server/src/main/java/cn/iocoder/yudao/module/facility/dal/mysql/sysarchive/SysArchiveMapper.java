package cn.iocoder.yudao.module.facility.dal.mysql.sysarchive;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.facility.controller.admin.sysarchive.vo.SysArchivePageReqVO;
import cn.iocoder.yudao.module.facility.dal.dataobject.sysarchive.SysArchiveDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * 归档 Mapper
 *
 * @author 亘川智城
 */
@Mapper
public interface SysArchiveMapper extends BaseMapperX<SysArchiveDO> {

    default PageResult<SysArchiveDO> selectPage(SysArchivePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<SysArchiveDO>()
                .eqIfPresent(SysArchiveDO::getArchiveNo, reqVO.getArchiveNo())
                .eqIfPresent(SysArchiveDO::getOrderNo, reqVO.getOrderNo())
                .eqIfPresent(SysArchiveDO::getOrderType, reqVO.getOrderType())
                .eqIfPresent(SysArchiveDO::getBizType, reqVO.getBizType())
                .betweenIfPresent(SysArchiveDO::getCompleteTime, reqVO.getCompleteTime())
                .eqIfPresent(SysArchiveDO::getWarnNo, reqVO.getWarnNo())
                .eqIfPresent(SysArchiveDO::getFacilityCode, reqVO.getFacilityCode())
                .likeIfPresent(SysArchiveDO::getFacilityName, reqVO.getFacilityName())
                .eqIfPresent(SysArchiveDO::getFacilityType, reqVO.getFacilityType())
                .eqIfPresent(SysArchiveDO::getAssignStaffId, reqVO.getAssignStaffId())
                .likeIfPresent(SysArchiveDO::getAssignStaffName, reqVO.getAssignStaffName())
                .eqIfPresent(SysArchiveDO::getCheckStaffId, reqVO.getCheckStaffId())
                .likeIfPresent(SysArchiveDO::getCheckStaffName, reqVO.getCheckStaffName())
                .eqIfPresent(SysArchiveDO::getAreaFullCode, reqVO.getAreaFullCode())
                .likeIfPresent(SysArchiveDO::getAreaName, reqVO.getAreaName())
                .eqIfPresent(SysArchiveDO::getDealDuration, reqVO.getDealDuration())
                .eqIfPresent(SysArchiveDO::getCheckResult, reqVO.getCheckResult())
                .eqIfPresent(SysArchiveDO::getCheckSuggest, reqVO.getCheckSuggest())
                .eqIfPresent(SysArchiveDO::getFileNum, reqVO.getFileNum())
                .likeIfPresent(SysArchiveDO::getOverIndexName, reqVO.getOverIndexName())
                .eqIfPresent(SysArchiveDO::getBeforeIndexValue, reqVO.getBeforeIndexValue())
                .eqIfPresent(SysArchiveDO::getAfterIndexValue, reqVO.getAfterIndexValue())
                .eqIfPresent(SysArchiveDO::getRecoverValue, reqVO.getRecoverValue())
                .eqIfPresent(SysArchiveDO::getThresholdValue, reqVO.getThresholdValue())
                .betweenIfPresent(SysArchiveDO::getCreateTime, reqVO.getCreateTime())
                .eqIfPresent(SysArchiveDO::getExtCommon1, reqVO.getExtCommon1())
                .eqIfPresent(SysArchiveDO::getExtCommon2, reqVO.getExtCommon2())
                .eqIfPresent(SysArchiveDO::getExtCommon3, reqVO.getExtCommon3())
                .eqIfPresent(SysArchiveDO::getExtCommon4, reqVO.getExtCommon4())
                .orderByDesc(SysArchiveDO::getId));
    }

    SysArchiveDO selectByArchiveNo(String archiveNo);
}
