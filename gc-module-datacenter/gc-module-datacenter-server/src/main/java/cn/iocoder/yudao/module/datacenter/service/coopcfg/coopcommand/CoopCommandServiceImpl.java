package cn.iocoder.yudao.module.datacenter.service.coopcfg.coopcommand;

import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.coopcfg.coopcommand.vo.CoopCommandSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.coopcfg.coopcommand.CoopCommandDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.coopcfg.coopcommand.CoopCommandMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 联动指令 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CoopCommandServiceImpl implements CoopCommandService {

    @Resource
    private CoopCommandMapper coopCommandMapper;

    @Override
    public Long createCoopCommand(CoopCommandSaveReqVO createReqVO) {
        // 插入
        CoopCommandDO coopCommand = BeanUtils.toBean(createReqVO, CoopCommandDO.class);
        coopCommandMapper.insert(coopCommand);
        // 返回
        return coopCommand.getId();
    }

    @Override
    public void updateCoopCommand(CoopCommandSaveReqVO updateReqVO) {
        // 校验存在
        validateCoopCommandExists(updateReqVO.getId());
        // 更新
        CoopCommandDO updateObj = BeanUtils.toBean(updateReqVO, CoopCommandDO.class);
        coopCommandMapper.updateById(updateObj);
    }

    @Override
    public void deleteCoopCommand(Long id) {
        // 校验存在
        validateCoopCommandExists(id);
        // 删除
        coopCommandMapper.deleteById(id);
    }

    private void validateCoopCommandExists(Long id) {
        if (coopCommandMapper.selectById(id) == null) {
            throw exception(COOP_COMMAND_NOT_EXISTS);
        }
    }

    @Override
    public CoopCommandDO getCoopCommand(Long id) {
        return coopCommandMapper.selectById(id);
    }

    @Override
    public PageResult<CoopCommandDO> getCoopCommandPage(CoopCommandPageReqVO pageReqVO) {
        return coopCommandMapper.selectPage(pageReqVO);
    }

}