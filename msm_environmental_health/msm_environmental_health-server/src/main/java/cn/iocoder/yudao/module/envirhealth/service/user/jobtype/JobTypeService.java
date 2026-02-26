package cn.iocoder.yudao.module.envirhealth.service.user.jobtype;

import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.jobtype.JobTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.jobtype.JobTypeSaveReqVO;
import jakarta.validation.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.JobTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;

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