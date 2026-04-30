package cn.iocoder.yudao.module.studentmgmt.dal.mysql.registermgmt;

import java.time.LocalDateTime;
import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.basevo.ChartTrendVO;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.registermgmt.RegisterMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.registermgmt.vo.*;

/**
 * 报名管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface RegisterMgmtMapper extends BaseMapperX<RegisterMgmtDO> {

    default PageResult<RegisterMgmtDO> selectPage(RegisterMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<RegisterMgmtDO>()
                .likeIfPresent(RegisterMgmtDO::getStudentName, reqVO.getStudentName())
                .eqIfPresent(RegisterMgmtDO::getIdCard, reqVO.getIdCard())
                .eqIfPresent(RegisterMgmtDO::getPhone, reqVO.getPhone())
                .eqIfPresent(RegisterMgmtDO::getMajor, reqVO.getMajor())
                .betweenIfPresent(RegisterMgmtDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(RegisterMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(RegisterMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(RegisterMgmtDO::getConfirmTime, reqVO.getConfirmTime())
                .eqIfPresent(RegisterMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(RegisterMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(RegisterMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(RegisterMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(RegisterMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(RegisterMgmtDO::getId));
    }

    RegisterMgmtChartRespVO selectTotalCount(LocalDateTime startTime, LocalDateTime endTime, String pending, String admitted);

    List<ChartTrendVO> select7dayTrendCount();

    List<EnrollCountVO> selectMajorEnrollCount(LocalDateTime startTime, LocalDateTime endTime, String status);

}