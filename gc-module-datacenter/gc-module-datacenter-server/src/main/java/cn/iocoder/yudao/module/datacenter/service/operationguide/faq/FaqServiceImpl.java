package cn.iocoder.yudao.module.datacenter.service.operationguide.faq;

import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.operationguide.faq.vo.FaqSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.operationguide.faq.FaqDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.operationguide.faq.FaqMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 常见问题解答 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class FaqServiceImpl implements FaqService {

    @Resource
    private FaqMapper faqMapper;

    @Override
    public Long createFaq(FaqSaveReqVO createReqVO) {
        // 插入
        FaqDO faq = BeanUtils.toBean(createReqVO, FaqDO.class);
        faqMapper.insert(faq);
        // 返回
        return faq.getId();
    }

    @Override
    public void updateFaq(FaqSaveReqVO updateReqVO) {
        // 校验存在
        validateFaqExists(updateReqVO.getId());
        // 更新
        FaqDO updateObj = BeanUtils.toBean(updateReqVO, FaqDO.class);
        faqMapper.updateById(updateObj);
    }

    @Override
    public void deleteFaq(Long id) {
        // 校验存在
        validateFaqExists(id);
        // 删除
        faqMapper.deleteById(id);
    }

    private void validateFaqExists(Long id) {
        if (faqMapper.selectById(id) == null) {
            throw exception(FAQ_NOT_EXISTS);
        }
    }

    @Override
    public FaqDO getFaq(Long id) {
        return faqMapper.selectById(id);
    }

    @Override
    public PageResult<FaqDO> getFaqPage(FaqPageReqVO pageReqVO) {
        return faqMapper.selectPage(pageReqVO);
    }

}