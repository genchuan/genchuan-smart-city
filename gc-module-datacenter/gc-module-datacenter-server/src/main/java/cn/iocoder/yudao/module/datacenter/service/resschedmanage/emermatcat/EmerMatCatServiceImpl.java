package cn.iocoder.yudao.module.datacenter.service.resschedmanage.emermatcat;

import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.resschedmanage.emermatcat.vo.EmerMatCatSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.resschedmanage.emermatcat.EmerMatCatDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.resschedmanage.emermatcat.EmerMatCatMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 应急物资分类配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class EmerMatCatServiceImpl implements EmerMatCatService {

    @Resource
    private EmerMatCatMapper emerMatCatMapper;

    @Override
    public Long createEmerMatCat(EmerMatCatSaveReqVO createReqVO) {
        // 插入
        EmerMatCatDO emerMatCat = BeanUtils.toBean(createReqVO, EmerMatCatDO.class);
        emerMatCatMapper.insert(emerMatCat);
        // 返回
        return emerMatCat.getId();
    }

    @Override
    public void updateEmerMatCat(EmerMatCatSaveReqVO updateReqVO) {
        // 校验存在
        validateEmerMatCatExists(updateReqVO.getId());
        // 更新
        EmerMatCatDO updateObj = BeanUtils.toBean(updateReqVO, EmerMatCatDO.class);
        emerMatCatMapper.updateById(updateObj);
    }

    @Override
    public void deleteEmerMatCat(Long id) {
        // 校验存在
        validateEmerMatCatExists(id);
        // 删除
        emerMatCatMapper.deleteById(id);
    }

    private void validateEmerMatCatExists(Long id) {
        if (emerMatCatMapper.selectById(id) == null) {
            throw exception(EMER_MAT_CAT_NOT_EXISTS);
        }
    }

    @Override
    public EmerMatCatDO getEmerMatCat(Long id) {
        return emerMatCatMapper.selectById(id);
    }

    @Override
    public PageResult<EmerMatCatDO> getEmerMatCatPage(EmerMatCatPageReqVO pageReqVO) {
        return emerMatCatMapper.selectPage(pageReqVO);
    }

}