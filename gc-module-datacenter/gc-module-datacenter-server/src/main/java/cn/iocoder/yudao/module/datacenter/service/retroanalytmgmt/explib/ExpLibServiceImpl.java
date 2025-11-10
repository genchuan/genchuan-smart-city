package cn.iocoder.yudao.module.datacenter.service.retroanalytmgmt.explib;

import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.retroanalytmgmt.explib.vo.ExpLibSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.retroanalytmgmt.explib.ExpLibDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.retroanalytmgmt.explib.ExpLibMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;
/**
 * 经验库 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ExpLibServiceImpl implements ExpLibService {

    @Resource
    private ExpLibMapper expLibMapper;

    @Override
    public Long createExpLib(ExpLibSaveReqVO createReqVO) {
        // 插入
        ExpLibDO expLib = BeanUtils.toBean(createReqVO, ExpLibDO.class);
        expLibMapper.insert(expLib);
        // 返回
        return expLib.getId();
    }

    @Override
    public void updateExpLib(ExpLibSaveReqVO updateReqVO) {
        // 校验存在
        validateExpLibExists(updateReqVO.getId());
        // 更新
        ExpLibDO updateObj = BeanUtils.toBean(updateReqVO, ExpLibDO.class);
        expLibMapper.updateById(updateObj);
    }

    @Override
    public void deleteExpLib(Long id) {
        // 校验存在
        validateExpLibExists(id);
        // 删除
        expLibMapper.deleteById(id);
    }

    private void validateExpLibExists(Long id) {
        if (expLibMapper.selectById(id) == null) {
            throw exception(EXP_LIB_NOT_EXISTS);
        }
    }

    @Override
    public ExpLibDO getExpLib(Long id) {
        return expLibMapper.selectById(id);
    }

    @Override
    public PageResult<ExpLibDO> getExpLibPage(ExpLibPageReqVO pageReqVO) {
        return expLibMapper.selectPage(pageReqVO);
    }

}