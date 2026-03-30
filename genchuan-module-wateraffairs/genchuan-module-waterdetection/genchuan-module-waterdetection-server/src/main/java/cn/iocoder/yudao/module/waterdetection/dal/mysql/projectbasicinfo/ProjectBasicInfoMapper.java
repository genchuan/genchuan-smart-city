package cn.iocoder.yudao.module.waterdetection.dal.mysql.projectbasicinfo;

import java.util.*;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.projectbasicinfo.ProjectBasicInfoDO;
import org.apache.ibatis.annotations.Mapper;
import cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo.*;

/**
 * 工程基本信息管理 Mapper
 *
 * @author zcq
 */
@Mapper
public interface ProjectBasicInfoMapper extends BaseMapperX<ProjectBasicInfoDO> {

    default PageResult<ProjectBasicInfoDO> selectPage(ProjectBasicInfoPageReqVO reqVO) {
        return selectPage(reqVO, new LambdaQueryWrapperX<ProjectBasicInfoDO>()
                .eqIfPresent(ProjectBasicInfoDO::getProjectCode, reqVO.getProjectCode())
                .likeIfPresent(ProjectBasicInfoDO::getProjectName, reqVO.getProjectName())
                .eqIfPresent(ProjectBasicInfoDO::getDesignCapacity, reqVO.getDesignCapacity())
                .eqIfPresent(ProjectBasicInfoDO::getProcessType, reqVO.getProcessType())
                .betweenIfPresent(ProjectBasicInfoDO::getCommissioningDate, reqVO.getCommissioningDate())
                .eqIfPresent(ProjectBasicInfoDO::getManagementUnit, reqVO.getManagementUnit())
                .eqIfPresent(ProjectBasicInfoDO::getProjectStatus, reqVO.getProjectStatus())
                .eqIfPresent(ProjectBasicInfoDO::getAdministrativeRegion, reqVO.getAdministrativeRegion())
                .betweenIfPresent(ProjectBasicInfoDO::getCreateTime, reqVO.getCreateTime())
                .orderByDesc(ProjectBasicInfoDO::getId));
    }

}