package cn.iocoder.yudao.module.datacenter.service.publicinfopublish.publicpolinterpret;

import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.publicinfopublish.publicpolinterpret.vo.PublicPolInterpretSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.publicinfopublish.publicpolinterpret.PublicPolInterpretDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.publicinfopublish.publicpolinterpret.PublicPolInterpretMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 政策解读发布 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class PublicPolInterpretServiceImpl implements PublicPolInterpretService {

    @Resource
    private PublicPolInterpretMapper publicPolInterpretMapper;

    @Override
    public Long createPublicPolInterpret(PublicPolInterpretSaveReqVO createReqVO) {
        // 插入
        PublicPolInterpretDO publicPolInterpret = BeanUtils.toBean(createReqVO, PublicPolInterpretDO.class);
        publicPolInterpretMapper.insert(publicPolInterpret);
        // 返回
        return publicPolInterpret.getId();
    }

    @Override
    public void updatePublicPolInterpret(PublicPolInterpretSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicPolInterpretExists(updateReqVO.getId());
        // 更新
        PublicPolInterpretDO updateObj = BeanUtils.toBean(updateReqVO, PublicPolInterpretDO.class);
        publicPolInterpretMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicPolInterpret(Long id) {
        // 校验存在
        validatePublicPolInterpretExists(id);
        // 删除
        publicPolInterpretMapper.deleteById(id);
    }

    private void validatePublicPolInterpretExists(Long id) {
        if (publicPolInterpretMapper.selectById(id) == null) {
            throw exception(PUBLIC_POL_INTERPRET_NOT_EXISTS);
        }
    }

    @Override
    public PublicPolInterpretDO getPublicPolInterpret(Long id) {
        return publicPolInterpretMapper.selectById(id);
    }

    @Override
    public PageResult<PublicPolInterpretDO> getPublicPolInterpretPage(PublicPolInterpretPageReqVO pageReqVO) {
        return publicPolInterpretMapper.selectPage(pageReqVO);
    }

}