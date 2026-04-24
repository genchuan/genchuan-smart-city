package cn.iocoder.yudao.module.chargepark.marketop.service.exchangemgmt.exchangecategory;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryChartRespVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryCreateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryPageReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.controller.admin.exchangemgmt.exchangecategory.vo.ExchangeCategoryUpdateReqVO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.dataobject.exchangemgmt.ExchangeCategoryDO;
import cn.iocoder.yudao.module.chargepark.marketop.dal.mysql.exchangemgmt.ExchangeCategoryMapper;
import cn.iocoder.yudao.module.chargepark.marketop.enums.ExchangeCategoryStatusEnum;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.chargepark.marketop.enums.ErrorCodeConstants.*;

@Service
@Validated
public class ExchangeCategoryServiceImpl implements ExchangeCategoryService {

    @Resource
    private ExchangeCategoryMapper exchangeCategoryMapper;

    @Override
    public PageResult<ExchangeCategoryDO> getPage(ExchangeCategoryPageReqVO reqVO) {
        return exchangeCategoryMapper.selectPage(reqVO);
    }

    @Override
    public ExchangeCategoryDO get(Long id) {
        return exchangeCategoryMapper.selectById(id);
    }

    @Override
    public Long create(ExchangeCategoryCreateReqVO reqVO) {
        // 校验名称唯一
        validateNameUnique(null, reqVO.getName());
        // 插入，默认状态为「未生效」
        ExchangeCategoryDO exchangeCategory = BeanUtils.toBean(reqVO, ExchangeCategoryDO.class);
        exchangeCategory.setStatus(ExchangeCategoryStatusEnum.NOT_EFFECTIVE.getValue());
        exchangeCategoryMapper.insert(exchangeCategory);
        return exchangeCategory.getId();
    }

    @Override
    public void update(ExchangeCategoryUpdateReqVO reqVO) {
        validateExists(reqVO.getId());
        // 校验名称唯一
        validateNameUnique(reqVO.getId(), reqVO.getName());
        ExchangeCategoryDO updateObj = BeanUtils.toBean(reqVO, ExchangeCategoryDO.class);
        exchangeCategoryMapper.updateById(updateObj);
    }

    @Override
    public void enable(Long id) {
        ExchangeCategoryDO exchangeCategory = validateExists(id);
        // 未生效→已生效，或 已禁用→已生效
        if (!ExchangeCategoryStatusEnum.NOT_EFFECTIVE.getValue().equals(exchangeCategory.getStatus()) &&
                !ExchangeCategoryStatusEnum.DISABLED.getValue().equals(exchangeCategory.getStatus())) {
            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
        }
        exchangeCategory.setStatus(ExchangeCategoryStatusEnum.EFFECTIVE.getValue());
        exchangeCategoryMapper.updateById(exchangeCategory);
    }

    @Override
    public void disable(Long id) {
        ExchangeCategoryDO exchangeCategory = validateExists(id);
        // 已生效→已禁用
        if (!ExchangeCategoryStatusEnum.EFFECTIVE.getValue().equals(exchangeCategory.getStatus())) {
            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
        }
        exchangeCategory.setStatus("0");
        exchangeCategoryMapper.updateById(exchangeCategory);
    }

    @Override
    public ExchangeCategoryChartRespVO getChart() {
        ExchangeCategoryChartRespVO respVO = new ExchangeCategoryChartRespVO();
        respVO.setCategoryCount(0);
        respVO.setProductCount(0);
        respVO.setTypeList(new ArrayList<>());
        return respVO;
    }

    private ExchangeCategoryDO validateExists(Long id) {
        ExchangeCategoryDO exchangeCategory = exchangeCategoryMapper.selectById(id);
        if (exchangeCategory == null) {
            throw exception(EXCHANGE_CATEGORY_NOT_EXISTS);
        }
        return exchangeCategory;
    }

    private void validateNameUnique(Long id, String name) {
        if (name == null) {
            return;
        }
        ExchangeCategoryDO existing = exchangeCategoryMapper.selectOne(ExchangeCategoryDO::getName, name);
        if (existing != null && !existing.getId().equals(id)) {
            throw exception(EXCHANGE_CATEGORY_NAME_EXISTS);
        }
    }

}
