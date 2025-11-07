package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatterext;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterext.vo.BizMngMatterExtSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterext.BizMngMatterExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatterext.BizMngMatterExtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项扩展管理事项配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngMatterExtServiceImpl implements BizMngMatterExtService {

    @Resource
    private BizMngMatterExtMapper bizMngMatterExtMapper;

    @Override
    public Long createBizMngMatterExt(BizMngMatterExtSaveReqVO createReqVO) {
        // 插入
        BizMngMatterExtDO bizMngMatterExt = BeanUtils.toBean(createReqVO, BizMngMatterExtDO.class);
        bizMngMatterExtMapper.insert(bizMngMatterExt);
        // 返回
        return bizMngMatterExt.getId();
    }

    @Override
    public void updateBizMngMatterExt(BizMngMatterExtSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngMatterExtExists(updateReqVO.getId());
        // 更新
        BizMngMatterExtDO updateObj = BeanUtils.toBean(updateReqVO, BizMngMatterExtDO.class);
        bizMngMatterExtMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngMatterExt(Long id) {
        // 校验存在
        validateBizMngMatterExtExists(id);
        // 删除
        bizMngMatterExtMapper.deleteById(id);
    }

    private void validateBizMngMatterExtExists(Long id) {
        if (bizMngMatterExtMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_MATTER_EXT_NOT_EXISTS);
        }
    }

    @Override
    public BizMngMatterExtDO getBizMngMatterExt(Long id) {
        return bizMngMatterExtMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngMatterExtDO> getBizMngMatterExtPage(BizMngMatterExtPageReqVO pageReqVO) {
        return bizMngMatterExtMapper.selectPage(pageReqVO);
    }

}