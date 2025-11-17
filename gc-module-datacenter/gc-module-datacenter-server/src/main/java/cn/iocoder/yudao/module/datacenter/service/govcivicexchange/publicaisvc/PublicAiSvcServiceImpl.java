package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicaisvc;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicaisvc.vo.PublicAiSvcSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicaisvc.PublicAiSvcDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicaisvc.PublicAiSvcMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 智能客服知识库 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicAiSvcServiceImpl implements PublicAiSvcService {

    @Resource
    private PublicAiSvcMapper publicAiSvcMapper;

    @Override
    public Long createPublicAiSvc(PublicAiSvcSaveReqVO createReqVO) {
        // 插入
        PublicAiSvcDO publicAiSvc = BeanUtils.toBean(createReqVO, PublicAiSvcDO.class);
        publicAiSvcMapper.insert(publicAiSvc);
        // 返回
        return publicAiSvc.getId();
    }

    @Override
    public void updatePublicAiSvc(PublicAiSvcSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicAiSvcExists(updateReqVO.getId());
        // 更新
        PublicAiSvcDO updateObj = BeanUtils.toBean(updateReqVO, PublicAiSvcDO.class);
        publicAiSvcMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicAiSvc(Long id) {
        // 校验存在
        validatePublicAiSvcExists(id);
        // 删除
        publicAiSvcMapper.deleteById(id);
    }

    private void validatePublicAiSvcExists(Long id) {
        if (publicAiSvcMapper.selectById(id) == null) {
            throw exception(PUBLIC_AI_SVC_NOT_EXISTS);
        }
    }

    @Override
    public PublicAiSvcDO getPublicAiSvc(Long id) {
        return publicAiSvcMapper.selectById(id);
    }

    @Override
    public PageResult<PublicAiSvcDO> getPublicAiSvcPage(PublicAiSvcPageReqVO pageReqVO) {
        return publicAiSvcMapper.selectPage(pageReqVO);
    }

}