package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.mngcomp;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.mngcomp.vo.MngCompSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.mngcomp.MngCompDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.mngcomp.MngCompMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MngCompServiceImpl implements MngCompService {

    @Resource
    private MngCompMapper mngCompMapper;

    @Override
    public Long createMngComp(MngCompSaveReqVO createReqVO) {
        // 插入
        MngCompDO mngComp = BeanUtils.toBean(createReqVO, MngCompDO.class);
        mngCompMapper.insert(mngComp);
        // 返回
        return mngComp.getId();
    }

    @Override
    public void updateMngComp(MngCompSaveReqVO updateReqVO) {
        // 校验存在
        validateMngCompExists(updateReqVO.getId());
        // 更新
        MngCompDO updateObj = BeanUtils.toBean(updateReqVO, MngCompDO.class);
        mngCompMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngComp(Long id) {
        // 校验存在
        validateMngCompExists(id);
        // 删除
        mngCompMapper.deleteById(id);
    }

    private void validateMngCompExists(Long id) {
        if (mngCompMapper.selectById(id) == null) {
            throw exception(MNG_COMP_NOT_EXISTS);
        }
    }

    @Override
    public MngCompDO getMngComp(Long id) {
        return mngCompMapper.selectById(id);
    }

    @Override
    public PageResult<MngCompDO> getMngCompPage(MngCompPageReqVO pageReqVO) {
        return mngCompMapper.selectPage(pageReqVO);
    }

}