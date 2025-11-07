package cn.iocoder.yudao.module.datacenter.service.mngcompcfg.bizmngcompspatial;

import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.mngcompcfg.bizmngcompspatial.vo.BizMngCompSpatialSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.mngcompcfg.bizmngcompspatial.BizMngCompSpatialDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.mngcompcfg.bizmngcompspatial.BizMngCompSpatialMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 管理部件空间数据 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizMngCompSpatialServiceImpl implements BizMngCompSpatialService {

    @Resource
    private BizMngCompSpatialMapper bizMngCompSpatialMapper;

    @Override
    public Long createBizMngCompSpatial(BizMngCompSpatialSaveReqVO createReqVO) {
        // 插入
        BizMngCompSpatialDO bizMngCompSpatial = BeanUtils.toBean(createReqVO, BizMngCompSpatialDO.class);
        bizMngCompSpatialMapper.insert(bizMngCompSpatial);
        // 返回
        return bizMngCompSpatial.getId();
    }

    @Override
    public void updateBizMngCompSpatial(BizMngCompSpatialSaveReqVO updateReqVO) {
        // 校验存在
        validateBizMngCompSpatialExists(updateReqVO.getId());
        // 更新
        BizMngCompSpatialDO updateObj = BeanUtils.toBean(updateReqVO, BizMngCompSpatialDO.class);
        bizMngCompSpatialMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizMngCompSpatial(Long id) {
        // 校验存在
        validateBizMngCompSpatialExists(id);
        // 删除
        bizMngCompSpatialMapper.deleteById(id);
    }

    private void validateBizMngCompSpatialExists(Long id) {
        if (bizMngCompSpatialMapper.selectById(id) == null) {
            throw exception(BIZ_MNG_COMP_SPATIAL_NOT_EXISTS);
        }
    }

    @Override
    public BizMngCompSpatialDO getBizMngCompSpatial(Long id) {
        return bizMngCompSpatialMapper.selectById(id);
    }

    @Override
    public PageResult<BizMngCompSpatialDO> getBizMngCompSpatialPage(BizMngCompSpatialPageReqVO pageReqVO) {
        return bizMngCompSpatialMapper.selectPage(pageReqVO);
    }

}