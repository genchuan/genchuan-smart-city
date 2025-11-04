package cn.iocoder.yudao.module.datacenter.service.grid.data.mnggridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.mnggridattr.vo.MngGridAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.mnggridattr.MngGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.mnggridattr.MngGridAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理网格属性 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class MngGridAttrServiceImpl implements MngGridAttrService {

    @Resource
    private MngGridAttrMapper mngGridAttrMapper;

    @Override
    public Long createMngGridAttr(MngGridAttrSaveReqVO createReqVO) {
        // 插入
        MngGridAttrDO mngGridAttr = BeanUtils.toBean(createReqVO, MngGridAttrDO.class);
        mngGridAttrMapper.insert(mngGridAttr);
        // 返回
        return mngGridAttr.getId();
    }

    @Override
    public void updateMngGridAttr(MngGridAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateMngGridAttrExists(updateReqVO.getId());
        // 更新
        MngGridAttrDO updateObj = BeanUtils.toBean(updateReqVO, MngGridAttrDO.class);
        mngGridAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngGridAttr(Long id) {
        // 校验存在
        validateMngGridAttrExists(id);
        // 删除
        mngGridAttrMapper.deleteById(id);
    }

    private void validateMngGridAttrExists(Long id) {
        if (mngGridAttrMapper.selectById(id) == null) {
            throw exception(MNG_GRID_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public MngGridAttrDO getMngGridAttr(Long id) {
        return mngGridAttrMapper.selectById(id);
    }

    @Override
    public PageResult<MngGridAttrDO> getMngGridAttrPage(MngGridAttrPageReqVO pageReqVO) {
        return mngGridAttrMapper.selectPage(pageReqVO);
    }

}