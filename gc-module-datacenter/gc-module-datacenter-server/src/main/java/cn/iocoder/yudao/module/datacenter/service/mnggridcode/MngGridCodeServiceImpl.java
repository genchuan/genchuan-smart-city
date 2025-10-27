package cn.iocoder.yudao.module.datacenter.service.mnggridcode;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggridcode.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggridcode.MngGridCodeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mnggridcode.MngGridCodeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格编码 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MngGridCodeServiceImpl implements MngGridCodeService {

    @Resource
    private MngGridCodeMapper mngGridCodeMapper;

    @Override
    public Long createMngGridCode(MngGridCodeSaveReqVO createReqVO) {
        // 插入
        MngGridCodeDO mngGridCode = BeanUtils.toBean(createReqVO, MngGridCodeDO.class);
        mngGridCodeMapper.insert(mngGridCode);
        // 返回
        return mngGridCode.getId();
    }

    @Override
    public void updateMngGridCode(MngGridCodeSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridCodeExists(updateReqVO.getId());
        // 更新
        MngGridCodeDO updateObj = BeanUtils.toBean(updateReqVO, MngGridCodeDO.class);
        mngGridCodeMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridCode(Long id) {
        // 校验存在
        validateMngGridCodeExists(id);
        // 删除
        mngGridCodeMapper.deleteById(id);
    }

    private void validateMngGridCodeExists(Long id) {
        if (mngGridCodeMapper.selectById(id) == null) {
            throw exception(MNG_GRID_CODE_NOT_EXISTS);
        }
    }

    @Override
    public MngGridCodeDO getMngGridCode(Long id) {
        return mngGridCodeMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridCodeDO> getMngGridCodePage(MngGridCodePageReqVO pageReqVO) {
        return mngGridCodeMapper.selectPage(pageReqVO);
    }

}