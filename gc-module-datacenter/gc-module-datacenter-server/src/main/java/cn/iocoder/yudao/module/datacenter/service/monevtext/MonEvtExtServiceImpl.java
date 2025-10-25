package cn.iocoder.yudao.module.datacenter.service.monevtext;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.datacenter.controller.admin.monevtext.vo.*;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.monevtext.MonEvtExtDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.monevtext.MonEvtExtMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 扩展监测事件配置 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class MonEvtExtServiceImpl implements MonEvtExtService {

    @Resource
    private MonEvtExtMapper monEvtExtMapper;

    @Override
    public Long createMonEvtExt(MonEvtExtSaveReqVO createReqVO) {
        // 插入
        MonEvtExtDO monEvtExt = BeanUtils.toBean(createReqVO, MonEvtExtDO.class);
        monEvtExtMapper.insert(monEvtExt);
        // 返回
        return monEvtExt.getId();
    }

    @Override
    public void updateMonEvtExt(MonEvtExtSaveReqVO updateReqVO) {
        // 校验存在
        validateMonEvtExtExists(updateReqVO.getId());
        // 更新
        MonEvtExtDO updateObj = BeanUtils.toBean(updateReqVO, MonEvtExtDO.class);
        monEvtExtMapper.updateById(updateObj);
    }

    @Override
    public void deleteMonEvtExt(Long id) {
        // 校验存在
        validateMonEvtExtExists(id);
        // 删除
        monEvtExtMapper.deleteById(id);
    }

    private void validateMonEvtExtExists(Long id) {
        if (monEvtExtMapper.selectById(id) == null) {
            throw exception(MON_EVT_EXT_NOT_EXISTS);
        }
    }

    @Override
    public MonEvtExtDO getMonEvtExt(Long id) {
        return monEvtExtMapper.selectById(id);
    }

    @Override
    public PageResult<MonEvtExtDO> getMonEvtExtPage(MonEvtExtPageReqVO pageReqVO) {
        return monEvtExtMapper.selectPage(pageReqVO);
    }

}