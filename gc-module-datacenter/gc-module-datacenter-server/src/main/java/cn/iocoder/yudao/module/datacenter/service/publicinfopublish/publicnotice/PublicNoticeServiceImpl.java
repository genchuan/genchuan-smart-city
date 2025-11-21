package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicnotice;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticePageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicnotice.vo.PublicNoticeSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicnotice.PublicNoticeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicnotice.PublicNoticeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 通知公告发布 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicNoticeServiceImpl implements PublicNoticeService {

    @Resource
    private PublicNoticeMapper publicNoticeMapper;

    @Override
    public Long createPublicNotice(PublicNoticeSaveReqVO createReqVO) {
        // 插入
        PublicNoticeDO publicNotice = BeanUtils.toBean(createReqVO, PublicNoticeDO.class);
        publicNoticeMapper.insert(publicNotice);
        // 返回
        return publicNotice.getId();
    }

    @Override
    public void updatePublicNotice(PublicNoticeSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicNoticeExists(updateReqVO.getId());
        // 更新
        PublicNoticeDO updateObj = BeanUtils.toBean(updateReqVO, PublicNoticeDO.class);
        publicNoticeMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicNotice(Long id) {
        // 校验存在
        validatePublicNoticeExists(id);
        // 删除
        publicNoticeMapper.deleteById(id);
    }

    private void validatePublicNoticeExists(Long id) {
        if (publicNoticeMapper.selectById(id) == null) {
            throw exception(PUBLIC_NOTICE_NOT_EXISTS);
        }
    }

    @Override
    public PublicNoticeDO getPublicNotice(Long id) {
        return publicNoticeMapper.selectById(id);
    }

    @Override
    public PageResult<PublicNoticeDO> getPublicNoticePage(PublicNoticePageReqVO pageReqVO) {
        return publicNoticeMapper.selectPage(pageReqVO);
    }

}