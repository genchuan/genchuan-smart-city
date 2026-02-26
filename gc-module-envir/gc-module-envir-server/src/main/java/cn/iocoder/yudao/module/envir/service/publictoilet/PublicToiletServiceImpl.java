package cn.iocoder.yudao.module.envir.service.publictoilet;

import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.publictoilet.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.publictoilet.PublicToiletDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.publictoilet.PublicToiletMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

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
    public List<PublicToiletDetailDO> getPublicToiletListDetail() {
        return publicToiletMapper.selectListDetail();
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