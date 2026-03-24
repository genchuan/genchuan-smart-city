package cn.iocoder.yudao.module.envirhealth.service.user.jobtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.jobtype.JobTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.user.vo.jobtype.JobTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.EquipmentDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.JobTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.JobTypeMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.JOB_TYPE_NOT_EXISTS;

/**
 * 岗位类型字典 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class JobTypeServiceImpl implements JobTypeService {

    @Resource
    private JobTypeMapper jobTypeMapper;

    @Override
    public Long createJobType(JobTypeSaveReqVO createReqVO) {
        // 插入
        JobTypeDO jobType = BeanUtils.toBean(createReqVO, JobTypeDO.class);
        jobTypeMapper.insert(jobType);
        // 返回
        return jobType.getId();
    }

    @Override
    public void updateJobType(JobTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateJobTypeExists(updateReqVO.getId());
        // 更新
        JobTypeDO updateObj = BeanUtils.toBean(updateReqVO, JobTypeDO.class);
        jobTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteJobType(Long id) {
        // 校验存在
        validateJobTypeExists(id);
        // 删除
        jobTypeMapper.deleteById(id);
    }

    private void validateJobTypeExists(Long id) {
        if (jobTypeMapper.selectById(id) == null) {
            throw exception(JOB_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public JobTypeDO getJobType(Long id) {
        return jobTypeMapper.selectById(id);
    }

    @Override
    public PageResult<JobTypeDO> getJobTypePage(JobTypePageReqVO pageReqVO) {
        return jobTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public List<OptionVO> getJobTypeOptions() {

        List<JobTypeDO> list;
        list = jobTypeMapper.selectList(
                new LambdaQueryWrapperX<JobTypeDO>()
                        .select(
                                JobTypeDO::getId,
                                JobTypeDO::getJobTypeId,
                                JobTypeDO::getName
                        )
                        .eq(JobTypeDO::getDeleted, 0)
                        .orderByDesc(JobTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, jobTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(jobTypeDO.getName());
            vo.setValue(jobTypeDO.getJobTypeId());
            return vo;
        });
    }
}