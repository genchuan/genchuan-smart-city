package cn.iocoder.yudao.module.evaluate.service.status;

import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.sys.status.vo.StatusSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.*;

/**
 * 状态字典 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class StatusServiceImpl implements StatusService {

    @Resource
    private StatusMapper statusMapper;

    @Override
    public Long createStatus(StatusSaveReqVO createReqVO) {
        // 插入
        StatusDO status = BeanUtils.toBean(createReqVO, StatusDO.class);
        statusMapper.insert(status);
        // 返回
        return status.getId();
    }

    @Override
    public void updateStatus(StatusSaveReqVO updateReqVO) {
        // 校验存在
        validateStatusExists(updateReqVO.getId());
        // 更新
        StatusDO updateObj = BeanUtils.toBean(updateReqVO, StatusDO.class);
        statusMapper.updateById(updateObj);
    }

    @Override
    public void deleteStatus(Long id) {
        // 校验存在
        validateStatusExists(id);
        // 删除
        statusMapper.deleteById(id);
    }

    private void validateStatusExists(Long id) {
        if (statusMapper.selectById(id) == null) {
            throw exception(STATUS_NOT_EXISTS);
        }
    }

    @Override
    public StatusDO getStatus(Long id) {
        return statusMapper.selectById(id);
    }

    @Override
    public PageResult<StatusDO> getStatusPage(StatusPageReqVO pageReqVO) {
        return statusMapper.selectPage(pageReqVO);
    }

}