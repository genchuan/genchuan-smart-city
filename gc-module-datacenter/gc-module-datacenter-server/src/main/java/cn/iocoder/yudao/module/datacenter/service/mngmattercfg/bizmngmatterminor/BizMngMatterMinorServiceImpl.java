package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmatterminor;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmatterminor.vo.BizMngMatterMinorSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmatterminor.BizMngMatterMinorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmatterminor.BizMngMatterMinorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项小类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngMatterMinorServiceImpl implements BizMngMatterMinorService {

    @Resource
    private BizMngMatterMinorMapper bizMngMatterMinorMapper;

    @Override
    public Long createBizMngMatterMinor(BizMngMatterMinorSaveReqVO createReqVO) {
        // 插入
        BizMngMatterMinorDO bizMngMatterMinor = BeanUtils.toBean(createReqVO, BizMngMatterMinorDO.class);
        bizMngMatterMinorMapper.insert(bizMngMatterMinor);
        // 返回
        return bizMngMatterMinor.getId();
    }

    @Override
    public void updateBizMngMatterMinor(BizMngMatterMinorSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngMatterMinorExists(updateReqVO.getId());
        // 更新
        BizMngMatterMinorDO updateObj = BeanUtils.toBean(updateReqVO, BizMngMatterMinorDO.class);
        bizMngMatterMinorMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngMatterMinor(Long id) {
        // 校验存在
        validateBizMngMatterMinorExists(id);
        // 删除
        bizMngMatterMinorMapper.deleteById(id);
    }

    private void validateBizMngMatterMinorExists(Long id) {
        if (bizMngMatterMinorMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_MATTER_MINOR_NOT_EXISTS);
        }
    }

    @Override
    public BizMngMatterMinorDO getBizMngMatterMinor(Long id) {
        return bizMngMatterMinorMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngMatterMinorDO> getBizMngMatterMinorPage(BizMngMatterMinorPageReqVO pageReqVO) {
        return bizMngMatterMinorMapper.selectPage(pageReqVO);
    }

}