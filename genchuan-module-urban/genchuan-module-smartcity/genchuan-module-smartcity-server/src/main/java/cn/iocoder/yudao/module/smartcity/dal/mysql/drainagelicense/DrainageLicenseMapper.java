package cn.iocoder.yudao.module.smartcity.dal.mysql.drainagelicense;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.drainagelicense.DrainageLicenseDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.smartcity.controller.admin.drainagelicense.vo.*;

/**
 * 排水电子许可证信息 Mapper
 *
 * @author 超级管理员
 */
@Mapper
public interface DrainageLicenseMapper extends BaseMapperX<DrainageLicenseDO> {

    default PageResult<DrainageLicenseDO> selectPage(DrainageLicensePageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<DrainageLicenseDO>()
                .eqIfPresent(DrainageLicenseDO::getLicenseNo, reqVO.getLicenseNo())
                .likeIfPresent(DrainageLicenseDO::getDrainageType, reqVO.getDrainageType())
                .eqIfPresent(DrainageLicenseDO::getApprovalUnit, reqVO.getApprovalUnit())
                .orderByDesc(DrainageLicenseDO::getId));
    }

}