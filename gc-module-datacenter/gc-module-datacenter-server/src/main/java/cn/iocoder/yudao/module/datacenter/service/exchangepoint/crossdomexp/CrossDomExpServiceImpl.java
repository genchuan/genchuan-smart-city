package cn.iocoder.yudao.module.datacenter.service.exchangepoint.crossdomexp;

import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.exchangepoint.crossdomexp.vo.CrossDomExpSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.exchangepoint.crossdomexp.CrossDomExpDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.exchangepoint.crossdomexp.CrossDomExpMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 跨域经验交流 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class CrossDomExpServiceImpl implements CrossDomExpService {

    @Resource
    private CrossDomExpMapper crossDomExpMapper;

    @Override
    public Long createCrossDomExp(CrossDomExpSaveReqVO createReqVO) {
        // 插入
        CrossDomExpDO crossDomExp = BeanUtils.toBean(createReqVO, CrossDomExpDO.class);
        crossDomExpMapper.insert(crossDomExp);
        // 返回
        return crossDomExp.getId();
    }

    @Override
    public void updateCrossDomExp(CrossDomExpSaveReqVO updateReqVO) {
        // 校验存在
        validateCrossDomExpExists(updateReqVO.getId());
        // 更新
        CrossDomExpDO updateObj = BeanUtils.toBean(updateReqVO, CrossDomExpDO.class);
        crossDomExpMapper.updateById(updateObj);
    }

    @Override
    public void deleteCrossDomExp(Long id) {
        // 校验存在
        validateCrossDomExpExists(id);
        // 删除
        crossDomExpMapper.deleteById(id);
    }

    private void validateCrossDomExpExists(Long id) {
        if (crossDomExpMapper.selectById(id) == null) {
            throw exception(CROSS_DOM_EXP_NOT_EXISTS);
        }
    }

    @Override
    public CrossDomExpDO getCrossDomExp(Long id) {
        return crossDomExpMapper.selectById(id);
    }

    @Override
    public PageResult<CrossDomExpDO> getCrossDomExpPage(CrossDomExpPageReqVO pageReqVO) {
        return crossDomExpMapper.selectPage(pageReqVO);
    }

}