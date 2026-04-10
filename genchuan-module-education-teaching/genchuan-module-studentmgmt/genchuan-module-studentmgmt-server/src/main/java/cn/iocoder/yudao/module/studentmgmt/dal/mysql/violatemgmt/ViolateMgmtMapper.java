package cn.iocoder.yudao.module.studentmgmt.dal.mysql.violatemgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.violatemgmt.ViolateMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.violatemgmt.vo.*;
import org.apache.ibatis.annotations.Param;

/**
 * 违纪管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface ViolateMgmtMapper extends BaseMapperX<ViolateMgmtDO> {

    default PageResult<ViolateMgmtDO> selectPage(ViolateMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ViolateMgmtDO>()
                .eqIfPresent(ViolateMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(ViolateMgmtDO::getViolateType, reqVO.getViolateType())
                .eqIfPresent(ViolateMgmtDO::getPunishType, reqVO.getPunishType())
                .betweenIfPresent(ViolateMgmtDO::getViolateTime, reqVO.getViolateTime())
                .eqIfPresent(ViolateMgmtDO::getViolateReason, reqVO.getViolateReason())
                .eqIfPresent(ViolateMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(ViolateMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(ViolateMgmtDO::getPushTime, reqVO.getPushTime())
                .betweenIfPresent(ViolateMgmtDO::getWarnTime, reqVO.getWarnTime())
                .eqIfPresent(ViolateMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(ViolateMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(ViolateMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(ViolateMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(ViolateMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ViolateMgmtDO::getId));
    }

    Integer auditViolateMgmtListByIds(@Param("ids") List<Long> ids, @Param("status") String status, @Param("userId") Long userId);
}