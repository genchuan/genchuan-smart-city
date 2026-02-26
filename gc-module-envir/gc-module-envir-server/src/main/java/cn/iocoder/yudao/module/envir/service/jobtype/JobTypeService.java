package cn.iocoder.yudao.module.envir.service.jobtype;

import java.util.*;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envir.controller.admin.jobtype.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.jobtype.JobTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;

/**
 * 岗位类型字典 Service 接口
 *
 * @author 芋道源码
 */
public interface JobTypeService {

    /**
     * 创建岗位类型字典
     *
     * @param createReqVO 创建信息
     * @return 编号
     */
    Long createJobType(@Valid JobTypeSaveReqVO createReqVO);

    /**
     * 更新岗位类型字典
     *
     * @param updateReqVO 更新信息
     */
    void updateJobType(@Valid JobTypeSaveReqVO updateReqVO);

    /**
     * 删除岗位类型字典
     *
     * @param id 编号
     */
    void deleteJobType(Long id);

    /**
     * 获得岗位类型字典
     *
     * @param id 编号
     * @return 岗位类型字典
     */
    JobTypeDO getJobType(Long id);

    /**
     * 获得岗位类型字典分页
     *
     * @param pageReqVO 分页查询
     * @return 岗位类型字典分页
     */
    PageResult<JobTypeDO> getJobTypePage(JobTypePageReqVO pageReqVO);

}