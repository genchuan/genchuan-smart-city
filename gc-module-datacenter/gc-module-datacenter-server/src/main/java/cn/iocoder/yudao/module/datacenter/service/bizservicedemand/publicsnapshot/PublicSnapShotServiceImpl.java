package cn.iocoder.yudao.module.datacenter.service.bizservicedemand.publicsnapshot;

import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.bizservicedemand.publicsnapshot.vo.PublicSnapShotSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.bizservicedemand.publicsnapshot.PublicSnapShotDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.bizservicedemand.publicsnapshot.PublicSnapShotMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 随手拍数据 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicSnapShotServiceImpl implements PublicSnapShotService {

    @Resource
    private PublicSnapShotMapper publicSnapShotMapper;

    @Override
    public Long createPublicSnapShot(PublicSnapShotSaveReqVO createReqVO) {
        // 插入
        PublicSnapShotDO publicSnapShot = BeanUtils.toBean(createReqVO, PublicSnapShotDO.class);
        publicSnapShotMapper.insert(publicSnapShot);
        // 返回
        return publicSnapShot.getId();
    }

    @Override
    public void updatePublicSnapShot(PublicSnapShotSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicSnapShotExists(updateReqVO.getId());
        // 更新
        PublicSnapShotDO updateObj = BeanUtils.toBean(updateReqVO, PublicSnapShotDO.class);
        publicSnapShotMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicSnapShot(Long id) {
        // 校验存在
        validatePublicSnapShotExists(id);
        // 删除
        publicSnapShotMapper.deleteById(id);
    }

    private void validatePublicSnapShotExists(Long id) {
        if (publicSnapShotMapper.selectById(id) == null) {
            throw exception(PUBLIC_SNAP_SHOT_NOT_EXISTS);
        }
    }

    @Override
    public PublicSnapShotDO getPublicSnapShot(Long id) {
        return publicSnapShotMapper.selectById(id);
    }

    @Override
    public PageResult<PublicSnapShotDO> getPublicSnapShotPage(PublicSnapShotPageReqVO pageReqVO) {
        return publicSnapShotMapper.selectPage(pageReqVO);
    }

}