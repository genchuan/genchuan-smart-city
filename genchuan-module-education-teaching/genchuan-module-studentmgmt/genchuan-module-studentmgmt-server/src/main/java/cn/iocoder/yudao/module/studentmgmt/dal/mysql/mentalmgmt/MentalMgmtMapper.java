package cn.iocoder.yudao.module.studentmgmt.dal.mysql.mentalmgmt;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.mentalmgmt.MentalMgmtDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.mentalmgmt.vo.*;

/**
 * 心理管理 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface MentalMgmtMapper extends BaseMapperX<MentalMgmtDO> {

    default PageResult<MentalMgmtDO> selectPage(MentalMgmtPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<MentalMgmtDO>()
                .eqIfPresent(MentalMgmtDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(MentalMgmtDO::getMentalStatus, reqVO.getMentalStatus())
                .eqIfPresent(MentalMgmtDO::getRiskLevel, reqVO.getRiskLevel())
                .betweenIfPresent(MentalMgmtDO::getEvaluateTime, reqVO.getEvaluateTime())
                .betweenIfPresent(MentalMgmtDO::getConsultTime, reqVO.getConsultTime())
                .betweenIfPresent(MentalMgmtDO::getInterveneTime, reqVO.getInterveneTime())
                .eqIfPresent(MentalMgmtDO::getInterveneContent, reqVO.getInterveneContent())
                .eqIfPresent(MentalMgmtDO::getStatus, reqVO.getStatus())
                .eqIfPresent(MentalMgmtDO::getRemark, reqVO.getRemark())
                .eqIfPresent(MentalMgmtDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(MentalMgmtDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(MentalMgmtDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(MentalMgmtDO::getId));
    }

}