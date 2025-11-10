package cn.iocoder.yudao.module.datacenter.service.commanddispatch.dispatchcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.dispatchcommand.vo.DispatchCommandSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.dispatchcommand.DispatchCommandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.dispatchcommand.DispatchCommandMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 调度指令 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DispatchCommandServiceImpl implements DispatchCommandService {

    @Resource
    private DispatchCommandMapper dispatchCommandMapper;

    @Override
    public Long createDispatchCommand(DispatchCommandSaveReqVO createReqVO) {
        // 插入
        DispatchCommandDO dispatchCommand = BeanUtils.toBean(createReqVO, DispatchCommandDO.class);
        dispatchCommandMapper.insert(dispatchCommand);
        // 返回
        return dispatchCommand.getId();
    }

    @Override
    public void updateDispatchCommand(DispatchCommandSaveReqVO updateReqVO) {
        // 校验存在
        validateDispatchCommandExists(updateReqVO.getId());
        // 更新
        DispatchCommandDO updateObj = BeanUtils.toBean(updateReqVO, DispatchCommandDO.class);
        dispatchCommandMapper.updateById(updateObj);
    }

    @Override
    public void deleteDispatchCommand(Long id) {
        // 校验存在
        validateDispatchCommandExists(id);
        // 删除
        dispatchCommandMapper.deleteById(id);
    }

    private void validateDispatchCommandExists(Long id) {
        if (dispatchCommandMapper.selectById(id) == null) {
            throw exception(DISPATCH_COMMAND_NOT_EXISTS);
        }
    }

    @Override
    public DispatchCommandDO getDispatchCommand(Long id) {
        return dispatchCommandMapper.selectById(id);
    }

    @Override
    public PageResult<DispatchCommandDO> getDispatchCommandPage(DispatchCommandPageReqVO pageReqVO) {
        return dispatchCommandMapper.selectPage(pageReqVO);
    }

}