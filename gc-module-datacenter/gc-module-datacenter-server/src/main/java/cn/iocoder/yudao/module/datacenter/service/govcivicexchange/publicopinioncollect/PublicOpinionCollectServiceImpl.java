package cn.iocoder.yudao.module.datacenter.service.govcivicexchange.publicopinioncollect;

import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.govcivicexchange.publicopinioncollect.vo.PublicOpinionCollectSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.govcivicexchange.publicopinioncollect.PublicOpinionCollectDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.govcivicexchange.publicopinioncollect.PublicOpinionCollectMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 民意征集 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicOpinionCollectServiceImpl implements PublicOpinionCollectService {

    @Resource
    private PublicOpinionCollectMapper publicOpinionCollectMapper;

    @Override
    public Long createPublicOpinionCollect(PublicOpinionCollectSaveReqVO createReqVO) {
        // 插入
        PublicOpinionCollectDO publicOpinionCollect = BeanUtils.toBean(createReqVO, PublicOpinionCollectDO.class);
        publicOpinionCollectMapper.insert(publicOpinionCollect);
        // 返回
        return publicOpinionCollect.getId();
    }

    @Override
    public void updatePublicOpinionCollect(PublicOpinionCollectSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicOpinionCollectExists(updateReqVO.getId());
        // 更新
        PublicOpinionCollectDO updateObj = BeanUtils.toBean(updateReqVO, PublicOpinionCollectDO.class);
        publicOpinionCollectMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicOpinionCollect(Long id) {
        // 校验存在
        validatePublicOpinionCollectExists(id);
        // 删除
        publicOpinionCollectMapper.deleteById(id);
    }

    private void validatePublicOpinionCollectExists(Long id) {
        if (publicOpinionCollectMapper.selectById(id) == null) {
            throw exception(PUBLIC_OPINION_COLLECT_NOT_EXISTS);
        }
    }

    @Override
    public PublicOpinionCollectDO getPublicOpinionCollect(Long id) {
        return publicOpinionCollectMapper.selectById(id);
    }

    @Override
    public PageResult<PublicOpinionCollectDO> getPublicOpinionCollectPage(PublicOpinionCollectPageReqVO pageReqVO) {
        return publicOpinionCollectMapper.selectPage(pageReqVO);
    }

}