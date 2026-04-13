package cn.iocoder.yudao.module.studentmgmt.dal.mysql.fundsystem;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.fundsystem.FundSystemDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.fundsystem.vo.*;

/**
 * 资助系统 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface FundSystemMapper extends BaseMapperX<FundSystemDO> {

    default PageResult<FundSystemDO> selectPage(FundSystemPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<FundSystemDO>()
                .eqIfPresent(FundSystemDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(FundSystemDO::getFundType, reqVO.getFundType())
                .eqIfPresent(FundSystemDO::getApplyAmount, reqVO.getApplyAmount())
                .betweenIfPresent(FundSystemDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(FundSystemDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(FundSystemDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(FundSystemDO::getStatus, reqVO.getStatus())
                .eqIfPresent(FundSystemDO::getRemark, reqVO.getRemark())
                .eqIfPresent(FundSystemDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(FundSystemDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(FundSystemDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(FundSystemDO::getId));
    }

}