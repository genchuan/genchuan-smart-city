package cn.iocoder.yudao.module.datacenter.service.mngmattercfg.bizmngmattermajor;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngmattercfg.bizmngmattermajor.vo.BizMngMatterMajorSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngmattercfg.bizmngmattermajor.BizMngMatterMajorDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngmattercfg.bizmngmattermajor.BizMngMatterMajorMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理事项大类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngMatterMajorServiceImpl implements BizMngMatterMajorService {

    @Resource
    private BizMngMatterMajorMapper bizMngMatterMajorMapper;

    @Override
    public Long createBizMngMatterMajor(BizMngMatterMajorSaveReqVO createReqVO) {
        // 插入
        BizMngMatterMajorDO bizMngMatterMajor = BeanUtils.toBean(createReqVO, BizMngMatterMajorDO.class);
        bizMngMatterMajorMapper.insert(bizMngMatterMajor);
        // 返回
        return bizMngMatterMajor.getId();
    }

    @Override
    public void updateBizMngMatterMajor(BizMngMatterMajorSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngMatterMajorExists(updateReqVO.getId());
        // 更新
        BizMngMatterMajorDO updateObj = BeanUtils.toBean(updateReqVO, BizMngMatterMajorDO.class);
        bizMngMatterMajorMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngMatterMajor(Long id) {
        // 校验存在
        validateBizMngMatterMajorExists(id);
        // 删除
        bizMngMatterMajorMapper.deleteById(id);
    }

    private void validateBizMngMatterMajorExists(Long id) {
        if (bizMngMatterMajorMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_MATTER_MAJOR_NOT_EXISTS);
        }
    }

    @Override
    public BizMngMatterMajorDO getBizMngMatterMajor(Long id) {
        return bizMngMatterMajorMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngMatterMajorDO> getBizMngMatterMajorPage(BizMngMatterMajorPageReqVO pageReqVO) {
        return bizMngMatterMajorMapper.selectPage(pageReqVO);
    }

}