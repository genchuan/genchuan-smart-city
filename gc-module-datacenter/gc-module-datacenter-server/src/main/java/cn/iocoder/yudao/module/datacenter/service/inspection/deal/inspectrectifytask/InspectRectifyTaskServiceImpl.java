package cn.iocoder.yudao.module.datacenter.service.inspection.deal.inspectrectifytask;

import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.inspection.deal.inspectrectifytask.vo.InspectRectifyTaskSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.inspection.deal.inspectrectifytask.InspectRectifyTaskDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.inspection.deal.inspectrectifytask.InspectRectifyTaskMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 巡查巡检整改任务派发 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class InspectRectifyTaskServiceImpl implements InspectRectifyTaskService {

    @Resource
    private InspectRectifyTaskMapper inspectRectifyTaskMapper;

    @Override
    public Long createInspectRectifyTask(InspectRectifyTaskSaveReqVO createReqVO) {
        // 插入
        InspectRectifyTaskDO inspectRectifyTask = BeanUtils.toBean(createReqVO, InspectRectifyTaskDO.class);
        inspectRectifyTaskMapper.insert(inspectRectifyTask);
        // 返回
        return inspectRectifyTask.getId();
    }

    @Override
    public void updateInspectRectifyTask(InspectRectifyTaskSaveReqVO updateReqVO) {
        // 校验存在
        validateInspectRectifyTaskExists(updateReqVO.getId());
        // 更新
        InspectRectifyTaskDO updateObj = BeanUtils.toBean(updateReqVO, InspectRectifyTaskDO.class);
        inspectRectifyTaskMapper.updateById(updateObj);
    }

    @Override
    public void deleteInspectRectifyTask(Long id) {
        // 校验存在
        validateInspectRectifyTaskExists(id);
        // 删除
        inspectRectifyTaskMapper.deleteById(id);
    }

    private void validateInspectRectifyTaskExists(Long id) {
        if (inspectRectifyTaskMapper.selectById(id) == null) {
            throw exception(INSPECT_RECTIFY_TASK_NOT_EXISTS);
        }
    }

    @Override
    public InspectRectifyTaskDO getInspectRectifyTask(Long id) {
        return inspectRectifyTaskMapper.selectById(id);
    }

    @Override
    public PageResult<InspectRectifyTaskDO> getInspectRectifyTaskPage(InspectRectifyTaskPageReqVO pageReqVO) {
        return inspectRectifyTaskMapper.selectPage(pageReqVO);
    }

}