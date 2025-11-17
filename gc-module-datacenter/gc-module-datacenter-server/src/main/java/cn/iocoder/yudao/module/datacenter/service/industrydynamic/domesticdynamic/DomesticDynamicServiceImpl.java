package cn.iocoder.yudao.module.datacenter.service.industrydynamic.domesticdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.domesticdynamic.vo.DomesticDynamicSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.domesticdynamic.DomesticDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.domesticdynamic.DomesticDynamicMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 国内行业动态 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class DomesticDynamicServiceImpl implements DomesticDynamicService {

    @Resource
    private DomesticDynamicMapper domesticDynamicMapper;

    @Override
    public Long createDomesticDynamic(DomesticDynamicSaveReqVO createReqVO) {
        // 插入
        DomesticDynamicDO domesticDynamic = BeanUtils.toBean(createReqVO, DomesticDynamicDO.class);
        domesticDynamicMapper.insert(domesticDynamic);
        // 返回
        return domesticDynamic.getId();
    }

    @Override
    public void updateDomesticDynamic(DomesticDynamicSaveReqVO updateReqVO) {
        // 校验存在
        validateDomesticDynamicExists(updateReqVO.getId());
        // 更新
        DomesticDynamicDO updateObj = BeanUtils.toBean(updateReqVO, DomesticDynamicDO.class);
        domesticDynamicMapper.updateById(updateObj);
    }

    @Override
    public void deleteDomesticDynamic(Long id) {
        // 校验存在
        validateDomesticDynamicExists(id);
        // 删除
        domesticDynamicMapper.deleteById(id);
    }

    private void validateDomesticDynamicExists(Long id) {
        if (domesticDynamicMapper.selectById(id) == null) {
            throw exception(DOMESTIC_DYNAMIC_NOT_EXISTS);
        }
    }

    @Override
    public DomesticDynamicDO getDomesticDynamic(Long id) {
        return domesticDynamicMapper.selectById(id);
    }

    @Override
    public PageResult<DomesticDynamicDO> getDomesticDynamicPage(DomesticDynamicPageReqVO pageReqVO) {
        return domesticDynamicMapper.selectPage(pageReqVO);
    }

}