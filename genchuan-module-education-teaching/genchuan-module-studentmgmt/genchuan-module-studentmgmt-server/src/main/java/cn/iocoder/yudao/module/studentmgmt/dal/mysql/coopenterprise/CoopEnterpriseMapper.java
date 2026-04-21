package cn.iocoder.yudao.module.studentmgmt.dal.mysql.coopenterprise;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.coopenterprise.CoopEnterpriseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.coopenterprise.vo.*;

/**
 * 校企合作 Mapper
 *
 * @author 芋道源码
 */
@Mapper
public interface CoopEnterpriseMapper extends BaseMapperX<CoopEnterpriseDO> {

    default PageResult<CoopEnterpriseDO> selectPage(CoopEnterprisePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<CoopEnterpriseDO>()
                .likeIfPresent(CoopEnterpriseDO::getEnterpriseName, reqVO.getEnterpriseName())
                .eqIfPresent(CoopEnterpriseDO::getEnterpriseType, reqVO.getEnterpriseType())
                .eqIfPresent(CoopEnterpriseDO::getDeptId, reqVO.getDeptId())
                .eqIfPresent(CoopEnterpriseDO::getContactUser, reqVO.getContactUser())
                .eqIfPresent(CoopEnterpriseDO::getContactPhone, reqVO.getContactPhone())
                .betweenIfPresent(CoopEnterpriseDO::getCoopStartTime, reqVO.getCoopStartTime())
                .betweenIfPresent(CoopEnterpriseDO::getCoopEndTime, reqVO.getCoopEndTime())
                .eqIfPresent(CoopEnterpriseDO::getStatus, reqVO.getStatus())
                .eqIfPresent(CoopEnterpriseDO::getRemark, reqVO.getRemark())
                .eqIfPresent(CoopEnterpriseDO::getReserve1, reqVO.getReserve1())
                .eqIfPresent(CoopEnterpriseDO::getReserve2, reqVO.getReserve2())
                .betweenIfPresent(CoopEnterpriseDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(CoopEnterpriseDO::getId));
    }

}