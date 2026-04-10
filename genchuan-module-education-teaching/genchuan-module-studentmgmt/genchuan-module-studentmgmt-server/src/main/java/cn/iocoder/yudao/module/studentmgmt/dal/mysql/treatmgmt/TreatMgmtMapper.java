package cn.iocoder.yudao.module.studentmgmt.dal.mysql.treatmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.treatmgmt.TreatMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.treatmgmt.vo.*;

/**
 * 就诊管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface TreatMgmtMapper extends BaseMapperX<TreatMgmtDO> {

    default PageResult<TreatMgmtDO> selectPage(TreatMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<TreatMgmtDO>()
                .eqIfPresent(TreatMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(TreatMgmtDO::getTreatType, reqVO.getTreatType())
                .eqIfPresent(TreatMgmtDO::getSymptom, reqVO.getSymptom())
                .betweenIfPresent(TreatMgmtDO::getRegisterTime, reqVO.getRegisterTime())
                .eqIfPresent(TreatMgmtDO::getTreatContent, reqVO.getTreatContent())
                .betweenIfPresent(TreatMgmtDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(TreatMgmtDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(TreatMgmtDO::getAuditTime, reqVO.getAuditTime())
                .betweenIfPresent(TreatMgmtDO::getFeedbackTime, reqVO.getFeedbackTime())
                .eqIfPresent(TreatMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(TreatMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(TreatMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(TreatMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(TreatMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(TreatMgmtDO::getId));
    }

}