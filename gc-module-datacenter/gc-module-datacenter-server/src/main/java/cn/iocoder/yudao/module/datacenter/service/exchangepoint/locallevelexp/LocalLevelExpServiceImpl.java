package cn.iocoder.yudao.module.datacenter.service.exchangepoint.locallevelexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.locallevelexp.vo.LocalLevelExpSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.locallevelexp.LocalLevelExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.locallevelexp.LocalLevelExpMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 本级经验分享 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LocalLevelExpServiceImpl implements LocalLevelExpService {

    @Resource
    private LocalLevelExpMapper localLevelExpMapper;

    @Override
    public Long createLocalLevelExp(LocalLevelExpSaveReqVO createReqVO) {
        // 插入
        LocalLevelExpDO localLevelExp = BeanUtils.toBean(createReqVO, LocalLevelExpDO.class);
        localLevelExpMapper.insert(localLevelExp);
        // 返回
        return localLevelExp.getId();
    }

    @Override
    public void updateLocalLevelExp(LocalLevelExpSaveReqVO updateReqVO) {
        // 校验存在
        validateLocalLevelExpExists(updateReqVO.getId());
        // 更新
        LocalLevelExpDO updateObj = BeanUtils.toBean(updateReqVO, LocalLevelExpDO.class);
        localLevelExpMapper.updateById(updateObj);
    }

    @Override
    public void deleteLocalLevelExp(Long id) {
        // 校验存在
        validateLocalLevelExpExists(id);
        // 删除
        localLevelExpMapper.deleteById(id);
    }

    private void validateLocalLevelExpExists(Long id) {
        if (localLevelExpMapper.selectById(id) == null) {
            throw exception(LOCAL_LEVEL_EXP_NOT_EXISTS);
        }
    }

    @Override
    public LocalLevelExpDO getLocalLevelExp(Long id) {
        return localLevelExpMapper.selectById(id);
    }

    @Override
    public PageResult<LocalLevelExpDO> getLocalLevelExpPage(LocalLevelExpPageReqVO pageReqVO) {
        return localLevelExpMapper.selectPage(pageReqVO);
    }

}