package cn.iocoder.yudao.module.envirhealth.service.publictoilet.toiletcleaningtask;

import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.toiletcleaningtask.ToiletCleaningTaskWithJoinRespVO;
import cn.iocoder.yudao.module.envirhealth.util.publictoilet.codegenerator.ToiletCleaningTaskCodeGenerator;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.ToiletCleaningTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.ToiletCleaningTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕保洁任务 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ToiletCleaningTaskServiceImpl implements ToiletCleaningTaskService {

    @Resource
    private ToiletCleaningTaskMapper toiletCleaningTaskMapper;

    @Resource
    private ToiletCleaningTaskCodeGenerator codeGenerator;

    @Override
    public Long createToiletCleaningTask(ToiletCleaningTaskSaveReqVO createReqVO) {
        //CleanerIds空值处理
        if (createReqVO.getCleanerIds() == null || createReqVO.getCleanerIds().trim().isEmpty()) {
            createReqVO.setCleanerIds("[]");
        }
        //ProofUrls空值处理
        if (createReqVO.getProofUrls() == null || createReqVO.getProofUrls().trim().isEmpty()) {
            createReqVO.setProofUrls("[]");
        }
        // 插入
        ToiletCleaningTaskDO toiletCleaningTask = BeanUtils.toBean(createReqVO, ToiletCleaningTaskDO.class);

        toiletCleaningTask.setTaskNo(codeGenerator.generateTaskNo());
        toiletCleaningTaskMapper.insert(toiletCleaningTask);
        // 返回
        return toiletCleaningTask.getId();
    }

    @Override
    public void updateToiletCleaningTask(ToiletCleaningTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateToiletCleaningTaskExists(updateReqVO.getId());
        if (updateReqVO.getCleanerIds() == null || updateReqVO.getCleanerIds().trim().isEmpty()) {
            updateReqVO.setCleanerIds("[]");
        }
        //ProofUrls空值处理
        if (updateReqVO.getProofUrls() == null || updateReqVO.getProofUrls().trim().isEmpty()) {
            updateReqVO.setProofUrls("[]");
        }
        // 更新
        ToiletCleaningTaskDO updateObj = BeanUtils.toBean(updateReqVO, ToiletCleaningTaskDO.class);
        toiletCleaningTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteToiletCleaningTask(Long id) {
        // 校验存在
        validateToiletCleaningTaskExists(id);
        // 删除
        toiletCleaningTaskMapper.deleteById(id);
    }

    private void validateToiletCleaningTaskExists(Long id) {
        if (toiletCleaningTaskMapper.selectById(id) == null) {
            throw exception(TOILET_CLEANING_TASK_NOT_EXISTS);
        }
    }

    @Override
    public ToiletCleaningTaskDO getToiletCleaningTask(Long id) {
        return toiletCleaningTaskMapper.selectById(id);
    }

    @Override
    public PageResult<ToiletCleaningTaskDO> getToiletCleaningTaskPage(ToiletCleaningTaskPageReqVO pageReqVO) {
        return toiletCleaningTaskMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<ToiletCleaningTaskWithJoinRespVO> getToiletCleaningTaskJoinPage(ToiletCleaningTaskPageReqVO pageReqVO) {
        return toiletCleaningTaskMapper.selectJoinPage(pageReqVO);
    }
}