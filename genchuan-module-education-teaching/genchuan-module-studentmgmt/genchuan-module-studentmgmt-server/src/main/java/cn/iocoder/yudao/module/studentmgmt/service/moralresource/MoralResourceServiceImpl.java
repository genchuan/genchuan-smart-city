package cn.iocoder.yudao.module.studentmgmt.service.moralresource;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.moralresource.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.moralresource.MoralResourceDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.moralresource.MoralResourceMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 德育资源 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class MoralResourceServiceImpl implements MoralResourceService {

    @Resource
    private MoralResourceMapper moralResourceMapper;

    @Override
    public Long createMoralResource(MoralResourceSaveReqVO createReqVO) {
        // 插入
        MoralResourceDO moralResource = BeanUtils.toBean(createReqVO, MoralResourceDO.class);
        moralResourceMapper.insert(moralResource);

        // 返回
        return moralResource.getId();
    }

    @Override
    public void updateMoralResource(MoralResourceSaveReqVO updateReqVO) {
        // 校验存在
        validateMoralResourceExists(updateReqVO.getId());
        // 更新
        MoralResourceDO updateObj = BeanUtils.toBean(updateReqVO, MoralResourceDO.class);
        moralResourceMapper.updateById(updateObj);
    }

    @Override
    public void deleteMoralResource(Long id) {
        // 校验存在
        validateMoralResourceExists(id);
        // 删除
        moralResourceMapper.deleteById(id);
    }

    @Override
        public void deleteMoralResourceListByIds(List<Long> ids) {
        // 删除
        moralResourceMapper.deleteByIds(ids);
        }


    private void validateMoralResourceExists(Long id) {
        if (moralResourceMapper.selectById(id) == null) {
            throw exception(MORAL_RESOURCE_NOT_EXISTS);
        }
    }

    @Override
    public MoralResourceDO getMoralResource(Long id) {
        return moralResourceMapper.selectById(id);
    }

    @Override
    public PageResult<MoralResourceDO> getMoralResourcePage(MoralResourcePageReqVO pageReqVO) {
        return moralResourceMapper.selectPage(pageReqVO);
    }

}