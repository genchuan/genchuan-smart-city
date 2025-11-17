package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publiconlineinterview;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publiconlineinterview.vo.PublicOnlineInterviewSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publiconlineinterview.PublicOnlineInterviewDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publiconlineinterview.PublicOnlineInterviewMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 在线访谈 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicOnlineInterviewServiceImpl implements PublicOnlineInterviewService {

    @Resource
    private PublicOnlineInterviewMapper publicOnlineInterviewMapper;

    @Override
    public Long createPublicOnlineInterview(PublicOnlineInterviewSaveReqVO createReqVO) {
        // 插入
        PublicOnlineInterviewDO publicOnlineInterview = BeanUtils.toBean(createReqVO, PublicOnlineInterviewDO.class);
        publicOnlineInterviewMapper.insert(publicOnlineInterview);
        // 返回
        return publicOnlineInterview.getId();
    }

    @Override
    public void updatePublicOnlineInterview(PublicOnlineInterviewSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicOnlineInterviewExists(updateReqVO.getId());
        // 更新
        PublicOnlineInterviewDO updateObj = BeanUtils.toBean(updateReqVO, PublicOnlineInterviewDO.class);
        publicOnlineInterviewMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicOnlineInterview(Long id) {
        // 校验存在
        validatePublicOnlineInterviewExists(id);
        // 删除
        publicOnlineInterviewMapper.deleteById(id);
    }

    private void validatePublicOnlineInterviewExists(Long id) {
        if (publicOnlineInterviewMapper.selectById(id) == null) {
            throw exception(PUBLIC_ONLINE_INTERVIEW_NOT_EXISTS);
        }
    }

    @Override
    public PublicOnlineInterviewDO getPublicOnlineInterview(Long id) {
        return publicOnlineInterviewMapper.selectById(id);
    }

    @Override
    public PageResult<PublicOnlineInterviewDO> getPublicOnlineInterviewPage(PublicOnlineInterviewPageReqVO pageReqVO) {
        return publicOnlineInterviewMapper.selectPage(pageReqVO);
    }

}