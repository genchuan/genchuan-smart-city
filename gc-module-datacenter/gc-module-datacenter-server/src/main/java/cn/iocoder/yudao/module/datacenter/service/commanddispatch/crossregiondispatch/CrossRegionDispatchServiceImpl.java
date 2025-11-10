package cn.iocoder.yudao.module.datacenter.service.commanddispatch.crossregiondispatch;

import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.commanddispatch.crossregiondispatch.vo.CrossRegionDispatchSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.commanddispatch.crossregiondispatch.CrossRegionDispatchDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.commanddispatch.crossregiondispatch.CrossRegionDispatchMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 跨域调度协调 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CrossRegionDispatchServiceImpl implements CrossRegionDispatchService {

    @Resource
    private CrossRegionDispatchMapper crossRegionDispatchMapper;

    @Override
    public Long createCrossRegionDispatch(CrossRegionDispatchSaveReqVO createReqVO) {
        // 插入
        CrossRegionDispatchDO crossRegionDispatch = BeanUtils.toBean(createReqVO, CrossRegionDispatchDO.class);
        crossRegionDispatchMapper.insert(crossRegionDispatch);
        // 返回
        return crossRegionDispatch.getId();
    }

    @Override
    public void updateCrossRegionDispatch(CrossRegionDispatchSaveReqVO updateReqVO) {
        // 校验存在
        validateCrossRegionDispatchExists(updateReqVO.getId());
        // 更新
        CrossRegionDispatchDO updateObj = BeanUtils.toBean(updateReqVO, CrossRegionDispatchDO.class);
        crossRegionDispatchMapper.updateById(updateObj);
    }

    @Override
    public void deleteCrossRegionDispatch(Long id) {
        // 校验存在
        validateCrossRegionDispatchExists(id);
        // 删除
        crossRegionDispatchMapper.deleteById(id);
    }

    private void validateCrossRegionDispatchExists(Long id) {
        if (crossRegionDispatchMapper.selectById(id) == null) {
            throw exception(CROSS_REGION_DISPATCH_NOT_EXISTS);
        }
    }

    @Override
    public CrossRegionDispatchDO getCrossRegionDispatch(Long id) {
        return crossRegionDispatchMapper.selectById(id);
    }

    @Override
    public PageResult<CrossRegionDispatchDO> getCrossRegionDispatchPage(CrossRegionDispatchPageReqVO pageReqVO) {
        return crossRegionDispatchMapper.selectPage(pageReqVO);
    }

}