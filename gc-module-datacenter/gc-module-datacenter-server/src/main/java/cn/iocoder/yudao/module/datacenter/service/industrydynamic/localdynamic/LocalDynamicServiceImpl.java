package cn.iocoder.yudao.module.datacenter.service.industrydynamic.localdynamic;

import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.industrydynamic.localdynamic.vo.LocalDynamicSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.industrydynamic.localdynamic.LocalDynamicDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.industrydynamic.localdynamic.LocalDynamicMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 本地行业动态 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class LocalDynamicServiceImpl implements LocalDynamicService {

    @Resource
    private LocalDynamicMapper localDynamicMapper;

    @Override
    public Long createLocalDynamic(LocalDynamicSaveReqVO createReqVO) {
        // 插入
        LocalDynamicDO localDynamic = BeanUtils.toBean(createReqVO, LocalDynamicDO.class);
        localDynamicMapper.insert(localDynamic);
        // 返回
        return localDynamic.getId();
    }

    @Override
    public void updateLocalDynamic(LocalDynamicSaveReqVO updateReqVO) {
        // 校验存在
        validateLocalDynamicExists(updateReqVO.getId());
        // 更新
        LocalDynamicDO updateObj = BeanUtils.toBean(updateReqVO, LocalDynamicDO.class);
        localDynamicMapper.updateById(updateObj);
    }

    @Override
    public void deleteLocalDynamic(Long id) {
        // 校验存在
        validateLocalDynamicExists(id);
        // 删除
        localDynamicMapper.deleteById(id);
    }

    private void validateLocalDynamicExists(Long id) {
        if (localDynamicMapper.selectById(id) == null) {
            throw exception(LOCAL_DYNAMIC_NOT_EXISTS);
        }
    }

    @Override
    public LocalDynamicDO getLocalDynamic(Long id) {
        return localDynamicMapper.selectById(id);
    }

    @Override
    public PageResult<LocalDynamicDO> getLocalDynamicPage(LocalDynamicPageReqVO pageReqVO) {
        return localDynamicMapper.selectPage(pageReqVO);
    }

}