package cn.iocoder.yudao.module.envir.service.garbagecollection;

import cn.iocoder.yudao.module.envir.dal.dataobject.garbagecollection.*;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.garbagecollection.vo.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.garbagecollection.GarbageCollectionMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 收运计划 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class GarbageCollectionServiceImpl implements GarbageCollectionService {

    @Resource
    private GarbageCollectionMapper garbageCollectionMapper;

    @Override
    public Long createGarbageCollection(GarbageCollectionSaveReqVO createReqVO) {
        // 插入
        GarbageCollectionDO garbageCollection = BeanUtils.toBean(createReqVO, GarbageCollectionDO.class);
        garbageCollectionMapper.insert(garbageCollection);
        // 返回
        return garbageCollection.getId();
    }

    @Override
    public void updateGarbageCollection(GarbageCollectionSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageCollectionExists(updateReqVO.getId());
        // 更新
        GarbageCollectionDO updateObj = BeanUtils.toBean(updateReqVO, GarbageCollectionDO.class);
        garbageCollectionMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageCollection(Long id) {
        // 校验存在
        validateGarbageCollectionExists(id);
        // 删除
        garbageCollectionMapper.deleteById(id);
    }

    private void validateGarbageCollectionExists(Long id) {
        if (garbageCollectionMapper.selectById(id) == null) {
            throw exception(GARBAGE_COLLECTION_NOT_EXISTS);
        }
    }

    @Override
    public GarbageCollectionDO getGarbageCollection(Long id) {
        return garbageCollectionMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageCollectionDO> getGarbageCollectionPage(GarbageCollectionPageReqVO pageReqVO) {
        return garbageCollectionMapper.selectPage(pageReqVO);
    }

    @Override
    public List<GarbageCollectionDetailDO> getGarbageCollectionListDetail() {
        return garbageCollectionMapper.selectListDetail();
    }

    @Override
    public PageResult<GarbageCollectionDetailDO> getGarbageCollectionDetailPage(GarbageCollectionPageReqVO pageReqVO) {
        Long total = garbageCollectionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageCollectionDetailDO> list = garbageCollectionMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}