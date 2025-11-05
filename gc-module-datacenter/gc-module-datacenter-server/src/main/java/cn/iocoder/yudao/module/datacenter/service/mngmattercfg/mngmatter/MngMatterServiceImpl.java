package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.mngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.mngmatter.vo.MngMatterSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.mngmatter.MngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.mngmatter.MngMatterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项统计 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MngMatterServiceImpl implements MngMatterService {

    @Resource
    private MngMatterMapper mngMatterMapper;

    @Override
    public Long createMngMatter(MngMatterSaveReqVO createReqVO) {
        // 插入
        MngMatterDO mngMatter = BeanUtils.toBean(createReqVO, MngMatterDO.class);
        mngMatterMapper.insert(mngMatter);
        // 返回
        return mngMatter.getId();
    }

    @Override
    public void updateMngMatter(MngMatterSaveReqVO updateReqVO) {
        // 校验存在
        validateMngMatterExists(updateReqVO.getId());
        // 更新
        MngMatterDO updateObj = BeanUtils.toBean(updateReqVO, MngMatterDO.class);
        mngMatterMapper.updateById(updateObj);
    }

    @Override
    public void deleteMngMatter(Long id) {
        // 校验存在
        validateMngMatterExists(id);
        // 删除
        mngMatterMapper.deleteById(id);
    }

    private void validateMngMatterExists(Long id) {
        if (mngMatterMapper.selectById(id) == null) {
            throw exception(MNG_MATTER_NOT_EXISTS);
        }
    }

    @Override
    public MngMatterDO getMngMatter(Long id) {
        return mngMatterMapper.selectById(id);
    }

    @Override
    public PageResult<MngMatterDO> getMngMatterPage(MngMatterPageReqVO pageReqVO) {
        return mngMatterMapper.selectPage(pageReqVO);
    }

}