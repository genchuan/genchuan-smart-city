package cn.iocoder.yudao.module.datacenter.service.mnggriddiv;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.mnggriddiv.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.mnggriddiv.MngGridDivDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mnggriddiv.MngGridDivMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格划分 Service 实现类
 *
 * @author zcq
 */
@Service
@Validated
public class MngGridDivServiceImpl implements MngGridDivService {

    @Resource
    private MngGridDivMapper mngGridDivMapper;

    @Override
    public Long createMngGridDiv(MngGridDivSaveReqVO createReqVO) {
        // 插入
        MngGridDivDO mngGridDiv = BeanUtils.toBean(createReqVO, MngGridDivDO.class);
        mngGridDivMapper.insert(mngGridDiv);
        // 返回
        return mngGridDiv.getId();
    }

    @Override
    public void updateMngGridDiv(MngGridDivSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridDivExists(updateReqVO.getId());
        // 更新
        MngGridDivDO updateObj = BeanUtils.toBean(updateReqVO, MngGridDivDO.class);
        mngGridDivMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridDiv(Long id) {
        // 校验存在
        validateMngGridDivExists(id);
        // 删除
        mngGridDivMapper.deleteById(id);
    }

    private void validateMngGridDivExists(Long id) {
        if (mngGridDivMapper.selectById(id) == null) {
            throw exception(MNG_GRID_DIV_NOT_EXISTS);
        }
    }

    @Override
    public MngGridDivDO getMngGridDiv(Long id) {
        return mngGridDivMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridDivDO> getMngGridDivPage(MngGridDivPageReqVO pageReqVO) {
        return mngGridDivMapper.selectPage(pageReqVO);
    }

}