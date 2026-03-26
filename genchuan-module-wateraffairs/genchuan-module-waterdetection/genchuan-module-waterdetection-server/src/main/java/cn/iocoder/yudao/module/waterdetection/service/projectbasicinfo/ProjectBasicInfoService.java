package cn.iocoder.yudao.module.waterdetection.service.projectbasicinfo;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.waterdetection.controller.admin.projectbasicinfo.vo.*;
import cn.iocoder.yudao.module.waterdetection.dal.dataobject.projectbasicinfo.ProjectBasicInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 工程基本信息管理 Service 接口
 *
 * @author zcq
 */
public interface ProjectBasicInfoService {

    /**
     * 创建工程基本信息管理
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createProjectBasicInfo(@Valid ProjectBasicInfoSaveReqVO createReqVO);

    /**
     * 更新工程基本信息管理
     *
     * @param updateReqVO 更新信息
     */
    void updateProjectBasicInfo(@Valid ProjectBasicInfoSaveReqVO updateReqVO);

    /**
     * 删除工程基本信息管理
     *
     * @param id 编号
     */
    void deleteProjectBasicInfo(Long id);

    /**
     * 获得工程基本信息管理
     *
     * @param id 编号
     * @return 工程基本信息管理
     */
    ProjectBasicInfoDO getProjectBasicInfo(Long id);

    /**
     * 获得工程基本信息管理分页
     *
     * @param pageReqVO 分页查询
     * @return 工程基本信息管理分页
     */
    PageResult<ProjectBasicInfoDO> getProjectBasicInfoPage(ProjectBasicInfoPageReqVO pageReqVO);

}