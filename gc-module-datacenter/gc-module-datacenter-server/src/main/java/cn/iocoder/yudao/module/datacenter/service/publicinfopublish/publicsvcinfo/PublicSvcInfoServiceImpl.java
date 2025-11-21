package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicsvcinfo;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicsvcinfo.vo.PublicSvcInfoSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicsvcinfo.PublicSvcInfoDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicsvcinfo.PublicSvcInfoMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 服务信息发布 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicSvcInfoServiceImpl implements PublicSvcInfoService {

    @Resource
    private PublicSvcInfoMapper publicSvcInfoMapper;

    @Override
    public Long createPublicSvcInfo(PublicSvcInfoSaveReqVO createReqVO) {
        // 插入
        PublicSvcInfoDO publicSvcInfo = BeanUtils.toBean(createReqVO, PublicSvcInfoDO.class);
        publicSvcInfoMapper.insert(publicSvcInfo);
        // 返回
        return publicSvcInfo.getId();
    }

    @Override
    public void updatePublicSvcInfo(PublicSvcInfoSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicSvcInfoExists(updateReqVO.getId());
        // 更新
        PublicSvcInfoDO updateObj = BeanUtils.toBean(updateReqVO, PublicSvcInfoDO.class);
        publicSvcInfoMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicSvcInfo(Long id) {
        // 校验存在
        validatePublicSvcInfoExists(id);
        // 删除
        publicSvcInfoMapper.deleteById(id);
    }

    private void validatePublicSvcInfoExists(Long id) {
        if (publicSvcInfoMapper.selectById(id) == null) {
            throw exception(PUBLIC_SVC_INFO_NOT_EXISTS);
        }
    }

    @Override
    public PublicSvcInfoDO getPublicSvcInfo(Long id) {
        return publicSvcInfoMapper.selectById(id);
    }

    @Override
    public PageResult<PublicSvcInfoDO> getPublicSvcInfoPage(PublicSvcInfoPageReqVO pageReqVO) {
        return publicSvcInfoMapper.selectPage(pageReqVO);
    }

}