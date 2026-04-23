package cn.iocoder.yudao.module.usermerchant.service.merchantmgmt.merchantlink;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.usermerchant.controller.admin.merchantmgmt.merchantlink.vo.*;
import cn.iocoder.yudao.module.usermerchant.dal.dataobject.merchantmgmt.merchantlink.MerchantLinkDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.usermerchant.dal.mysql.merchantmgmt.merchantlink.MerchantLinkMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.usermerchant.enums.ErrorCodeConstants.*;

/**
 * 商户对接 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MerchantLinkServiceImpl implements MerchantLinkService {

    @Resource
    private MerchantLinkMapper merchantLinkMapper;

    @Override
    public Long createMerchantLink(MerchantLinkSaveReqVO createReqVO) {
        // 插入
        MerchantLinkDO merchantLink = BeanUtils.toBean(createReqVO, MerchantLinkDO.class);
        merchantLinkMapper.insert(merchantLink);

        // 返回
        return merchantLink.getId();
    }

    @Override
    public void updateMerchantLink(MerchantLinkSaveReqVO updateReqVO) {
        // 校验存在
        validateMerchantLinkExists(updateReqVO.getId());
        // 更新
        MerchantLinkDO updateObj = BeanUtils.toBean(updateReqVO, MerchantLinkDO.class);
        merchantLinkMapper.updateById(updateObj);
    }

    @Override
    public void deleteMerchantLink(Long id) {
        // 校验存在
        validateMerchantLinkExists(id);
        // 删除
        merchantLinkMapper.deleteById(id);
    }

    @Override
        public void deleteMerchantLinkListByIds(List<Long> ids) {
        // 删除
        merchantLinkMapper.deleteByIds(ids);
        }


    private void validateMerchantLinkExists(Long id) {
        if (merchantLinkMapper.selectById(id) == null) {
            throw exception(MERCHANT_LINK_NOT_EXISTS);
        }
    }

    @Override
    public MerchantLinkDO getMerchantLink(Long id) {
        return merchantLinkMapper.selectById(id);
    }

    @Override
    public PageResult<MerchantLinkDO> getMerchantLinkPage(MerchantLinkPageReqVO pageReqVO) {
        return merchantLinkMapper.selectPage(pageReqVO);
    }

}