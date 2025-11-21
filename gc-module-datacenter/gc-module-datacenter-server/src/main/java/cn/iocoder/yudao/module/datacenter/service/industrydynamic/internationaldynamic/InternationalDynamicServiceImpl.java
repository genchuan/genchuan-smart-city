package cn.iocoder.yudao.module.datacenter.service.industrydynamic.internationaldynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.internationaldynamic.vo.InternationalDynamicSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.internationaldynamic.InternationalDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.internationaldynamic.InternationalDynamicMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 国际行业动态 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class InternationalDynamicServiceImpl implements InternationalDynamicService {

    @Resource
    private InternationalDynamicMapper internationalDynamicMapper;

    @Override
    public Long createInternationalDynamic(InternationalDynamicSaveReqVO createReqVO) {
        // 插入
        InternationalDynamicDO internationalDynamic = BeanUtils.toBean(createReqVO, InternationalDynamicDO.class);
        internationalDynamicMapper.insert(internationalDynamic);
        // 返回
        return internationalDynamic.getId();
    }

    @Override
    public void updateInternationalDynamic(InternationalDynamicSaveReqVO updateReqVO) {
        // 校验存在
        validateInternationalDynamicExists(updateReqVO.getId());
        // 更新
        InternationalDynamicDO updateObj = BeanUtils.toBean(updateReqVO, InternationalDynamicDO.class);
        internationalDynamicMapper.updateById(updateObj);
    }

    @Override
    public void deleteInternationalDynamic(Long id) {
        // 校验存在
        validateInternationalDynamicExists(id);
        // 删除
        internationalDynamicMapper.deleteById(id);
    }

    private void validateInternationalDynamicExists(Long id) {
        if (internationalDynamicMapper.selectById(id) == null) {
            throw exception(INTERNATIONAL_DYNAMIC_NOT_EXISTS);
        }
    }

    @Override
    public InternationalDynamicDO getInternationalDynamic(Long id) {
        return internationalDynamicMapper.selectById(id);
    }

    @Override
    public PageResult<InternationalDynamicDO> getInternationalDynamicPage(InternationalDynamicPageReqVO pageReqVO) {
        return internationalDynamicMapper.selectPage(pageReqVO);
    }

}