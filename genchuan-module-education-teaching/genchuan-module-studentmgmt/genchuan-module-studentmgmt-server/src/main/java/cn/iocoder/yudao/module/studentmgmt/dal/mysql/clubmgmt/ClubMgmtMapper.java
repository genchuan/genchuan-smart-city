package cn.iocoder.yudao.module.studentmgmt.dal.mysql.clubmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.clubmgmt.ClubMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.clubmgmt.vo.*;

/**
 * 社团管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ClubMgmtMapper extends BaseMapperX<ClubMgmtDO> {

    default PageResult<ClubMgmtDO> selectPage(ClubMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ClubMgmtDO>()
                .likeIfPresent(ClubMgmtDO::getClubName, reqVO.getClubName())
                .eqIfPresent(ClubMgmtDO::getClubType, reqVO.getClubType())
                .eqIfPresent(ClubMgmtDO::getStudentId, reqVO.getStudentId())
                .betweenIfPresent(ClubMgmtDO::getApplyTime, reqVO.getApplyTime())
                .likeIfPresent(ClubMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(ClubMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(ClubMgmtDO::getArchiveTime, reqVO.getArchiveTime())
                .eqIfPresent(ClubMgmtDO::getVenueApplyStatus, reqVO.getVenueApplyStatus())
                .eqIfPresent(ClubMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ClubMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ClubMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ClubMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ClubMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ClubMgmtDO::getId));
    }

}