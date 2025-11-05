package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatter;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatter.vo.BizMngMatterSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatter.BizMngMatterDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatter.BizMngMatterMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项信息 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngMatterServiceImpl implements BizMngMatterService {

    @Resource
    private BizMngMatterMapper bizMngMatterMapper;

    @Override
    public Long createBizMngMatter(BizMngMatterSaveReqVO createReqVO) {
        // 插入
        BizMngMatterDO bizMngMatter = BeanUtils.toBean(createReqVO, BizMngMatterDO.class);
        bizMngMatterMapper.insert(bizMngMatter);
        // 返回
        return bizMngMatter.getId();
    }

    @Override
    public void updateBizMngMatter(BizMngMatterSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngMatterExists(updateReqVO.getId());
        // 更新
        BizMngMatterDO updateObj = BeanUtils.toBean(updateReqVO, BizMngMatterDO.class);
        bizMngMatterMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngMatter(Long id) {
        // 校验存在
        validateBizMngMatterExists(id);
        // 删除
        bizMngMatterMapper.deleteById(id);
    }

    private void validateBizMngMatterExists(Long id) {
        if (bizMngMatterMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_MATTER_NOT_EXISTS);
        }
    }

    @Override
    public BizMngMatterDO getBizMngMatter(Long id) {
        return bizMngMatterMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngMatterDO> getBizMngMatterPage(BizMngMatterPageReqVO pageReqVO) {
        return bizMngMatterMapper.selectPage(pageReqVO);
    }

}