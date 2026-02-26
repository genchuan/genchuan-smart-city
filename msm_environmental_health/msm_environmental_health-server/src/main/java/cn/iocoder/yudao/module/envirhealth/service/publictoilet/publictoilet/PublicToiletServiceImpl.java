package cn.iocoder.yudao.module.envirhealth.service.publictoilet.publictoilet;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publictoilet.vo.publictoilet.PublicToiletSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.detail.PublicToiletDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.publictoilet.PublicToiletMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 公厕 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PublicToiletServiceImpl implements PublicToiletService {

    @Resource
    private PublicToiletMapper publicToiletMapper;

    @Override
    public Long createPublicToilet(PublicToiletSaveReqVO createReqVO) {
        // 插入
        PublicToiletDO publicToilet = BeanUtils.toBean(createReqVO, PublicToiletDO.class);
        publicToiletMapper.insert(publicToilet);
        // 返回
        return publicToilet.getId();
    }

    @Override
    public void updatePublicToilet(PublicToiletSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicToiletExists(updateReqVO.getId());
        // 更新
        PublicToiletDO updateObj = BeanUtils.toBean(updateReqVO, PublicToiletDO.class);
        publicToiletMapper.updateById(updateObj);
    }

    @Override
    public void deletePublicToilet(Long id) {
        // 校验存在
        validatePublicToiletExists(id);
        // 删除
        publicToiletMapper.deleteById(id);
    }

    private void validatePublicToiletExists(Long id) {
        if (publicToiletMapper.selectById(id) == null) {
            throw exception(PUBLIC_TOILET_NOT_EXISTS);
        }
    }

    @Override
    public PublicToiletDO getPublicToilet(Long id) {
        return publicToiletMapper.selectById(id);
    }

    @Override
    public PageResult<PublicToiletDO> getPublicToiletPage(PublicToiletPageReqVO pageReqVO) {
        return publicToiletMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PublicToiletDetailDO> getPublicToiletDetailPage(PublicToiletPageReqVO pageReqVO) {
        Long total = publicToiletMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<PublicToiletDetailDO> list = publicToiletMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}