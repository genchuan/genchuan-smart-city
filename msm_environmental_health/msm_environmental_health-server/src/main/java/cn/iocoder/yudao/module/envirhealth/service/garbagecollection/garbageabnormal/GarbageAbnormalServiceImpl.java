package cn.iocoder.yudao.module.envirhealth.service.garbagecollection.garbageabnormal;

import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbageabnormal.GarbageAbnormalSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.garbagecollection.vo.garbagecollection.GarbageCollectionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageAbnormalDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.detail.GarbageCollectionDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.garbagecollection.GarbageAbnormalDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.garbagecollection.GarbageAbnormalMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 垃圾异常记录 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class GarbageAbnormalServiceImpl implements GarbageAbnormalService {

    @Resource
    private GarbageAbnormalMapper garbageAbnormalMapper;

    @Override
    public Long createGarbageAbnormal(GarbageAbnormalSaveReqVO createReqVO) {
        // 插入
        GarbageAbnormalDO garbageAbnormal = BeanUtils.toBean(createReqVO, GarbageAbnormalDO.class);
        garbageAbnormalMapper.insert(garbageAbnormal);
        // 返回
        return garbageAbnormal.getId();
    }

    @Override
    public void updateGarbageAbnormal(GarbageAbnormalSaveReqVO updateReqVO) {
        // 校验存在
        validateGarbageAbnormalExists(updateReqVO.getId());
        // 更新
        GarbageAbnormalDO updateObj = BeanUtils.toBean(updateReqVO, GarbageAbnormalDO.class);
        garbageAbnormalMapper.updateById(updateObj);
    }

    @Override
    public void deleteGarbageAbnormal(Long id) {
        // 校验存在
        validateGarbageAbnormalExists(id);
        // 删除
        garbageAbnormalMapper.deleteById(id);
    }

    private void validateGarbageAbnormalExists(Long id) {
        if (garbageAbnormalMapper.selectById(id) == null) {
            throw exception(GARBAGE_ABNORMAL_NOT_EXISTS);
        }
    }

    @Override
    public GarbageAbnormalDO getGarbageAbnormal(Long id) {
        return garbageAbnormalMapper.selectById(id);
    }

    @Override
    public PageResult<GarbageAbnormalDO> getGarbageAbnormalPage(GarbageAbnormalPageReqVO pageReqVO) {
        return garbageAbnormalMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<GarbageAbnormalDetailDO> getGarbageAbnormalDetailPage(GarbageAbnormalPageReqVO pageReqVO) {
        Long total = garbageAbnormalMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<GarbageAbnormalDetailDO> list = garbageAbnormalMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }
}