package cn.iocoder.yudao.module.studentmgmt.dal.mysql.accessapply;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.accessapply.AccessApplyDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.accessapply.vo.*;

/**
 * 出入申请 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface AccessApplyMapper extends BaseMapperX<AccessApplyDO> {

    default PageResult<AccessApplyDO> selectPage(AccessApplyPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<AccessApplyDO>()
                .eqIfPresent(AccessApplyDO::getStudentId, reqVO.getStudentId())
                .eqIfPresent(AccessApplyDO::getApplyType, reqVO.getApplyType())
                .eqIfPresent(AccessApplyDO::getApplyReason, reqVO.getApplyReason())
                .betweenIfPresent(AccessApplyDO::getApplyTime, reqVO.getApplyTime())
                .eqIfPresent(AccessApplyDO::getAuditUser, reqVO.getAuditUser())
                .betweenIfPresent(AccessApplyDO::getAuditTime, reqVO.getAuditTime())
                .eqIfPresent(AccessApplyDO::getStatus, reqVO.getStatus())
                .eqIfPresent(AccessApplyDO::getRemark, reqVO.getRemark())
                .eqIfPresent(AccessApplyDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(AccessApplyDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(AccessApplyDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(AccessApplyDO::getId));
    }

}