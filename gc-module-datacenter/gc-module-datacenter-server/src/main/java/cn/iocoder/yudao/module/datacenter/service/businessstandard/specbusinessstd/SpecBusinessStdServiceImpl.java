package cn.iocoder.yudao.module.datacenter.service.businessstandard.specbusinessstd;

import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.businessstandard.specbusinessstd.vo.SpecBusinessStdSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessstandard.specbusinessstd.SpecBusinessStdDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.businessstandard.specbusinessstd.SpecBusinessStdMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.SPEC_BUSINESS_STD_NOT_EXISTS;

/**
 * 专项业务标准 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class SpecBusinessStdServiceImpl implements SpecBusinessStdService {

    @Resource
    private SpecBusinessStdMapper specBusinessStdMapper;

    @Override
    public Long createSpecBusinessStd(SpecBusinessStdSaveReqVO createReqVO) {
        // 插入
        SpecBusinessStdDO specBusinessStd = BeanUtils.toBean(createReqVO, SpecBusinessStdDO.class);
        specBusinessStdMapper.insert(specBusinessStd);
        // 返回
        return specBusinessStd.getId();
    }

    @Override
    public void updateSpecBusinessStd(SpecBusinessStdSaveReqVO updateReqVO) {
        // 校验存在
        validateSpecBusinessStdExists(updateReqVO.getId());
        // 更新
        SpecBusinessStdDO updateObj = BeanUtils.toBean(updateReqVO, SpecBusinessStdDO.class);
        specBusinessStdMapper.updateById(updateObj);
    }

    @Override
    public void deleteSpecBusinessStd(Long id) {
        // 校验存在
        validateSpecBusinessStdExists(id);
        // 删除
        specBusinessStdMapper.deleteById(id);
    }

    private void validateSpecBusinessStdExists(Long id) {
        if (specBusinessStdMapper.selectById(id) == null) {
            throw exception(SPEC_BUSINESS_STD_NOT_EXISTS);
        }
    }

    @Override
    public SpecBusinessStdDO getSpecBusinessStd(Long id) {
        return specBusinessStdMapper.selectById(id);
    }

    @Override
    public PageResult<SpecBusinessStdDO> getSpecBusinessStdPage(SpecBusinessStdPageReqVO pageReqVO) {
        return specBusinessStdMapper.selectPage(pageReqVO);
    }

}