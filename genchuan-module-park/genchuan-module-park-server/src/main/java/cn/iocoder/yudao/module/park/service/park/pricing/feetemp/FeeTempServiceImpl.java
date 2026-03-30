package cn.iocoder.yudao.module.park.service.park.pricing.feetemp;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempPageReqVO;
import cn.iocoder.yudao.module.park.controller.admin.park.pricing.feetemp.vo.FeeTempSaveReqVO;
import cn.iocoder.yudao.module.park.dal.dataobject.park.pricing.feetemp.FeeTempDO;
import cn.iocoder.yudao.module.park.dal.mysql.park.pricing.feetemp.FeeTempMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.park.enums.ErrorCodeConstants.FEE_TEMP_NOT_EXISTS;

/**
 * 临停收费规则 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FeeTempServiceImpl implements FeeTempService {

    @Resource
    private FeeTempMapper feeTempMapper;

    @Override
    public Long createFeeTemp(FeeTempSaveReqVO createReqVO) {
        // 插入
        FeeTempDO feeTemp = BeanUtils.toBean(createReqVO, FeeTempDO.class);
        feeTempMapper.insert(feeTemp);
        // 返回
        return feeTemp.getId();
    }

    @Override
    public void updateFeeTemp(FeeTempSaveReqVO updateReqVO) {
        // 校验存在
        validateFeeTempExists(updateReqVO.getId());
        // 更新
        FeeTempDO updateObj = BeanUtils.toBean(updateReqVO, FeeTempDO.class);
        feeTempMapper.updateById(updateObj);
    }

    @Override
    public void deleteFeeTemp(Long id) {
        // 校验存在
        validateFeeTempExists(id);
        // 删除
        feeTempMapper.deleteById(id);
    }

    private void validateFeeTempExists(Long id) {
        if (feeTempMapper.selectById(id) == null) {
            throw exception(FEE_TEMP_NOT_EXISTS);
        }
    }

    @Override
    public FeeTempDO getFeeTemp(Long id) {
        return feeTempMapper.selectById(id);
    }

    @Override
    public PageResult<FeeTempDO> getFeeTempPage(FeeTempPageReqVO pageReqVO) {
        return feeTempMapper.selectPage(pageReqVO);
    }

}
