package cn.iocoder.yudao.module.datacenter.service.grid.data.evalgridattr;

import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.grid.data.evalgridattr.vo.EvalGridAttrSaveReqVO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.datacenter.dal.dataobject.grid.data.evalgridattr.EvalGridAttrDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.datacenter.dal.mysql.grid.data.evalgridattr.EvalGridAttrMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.*;

/**
 * 评价网格属性 Service 实现类
 *
 * @author zhucongquan
 */
@Service
@Validated
public class EvalGridAttrServiceImpl implements EvalGridAttrService {

    @Resource
    private EvalGridAttrMapper evalGridAttrMapper;

    @Override
    public Long createEvalGridAttr(EvalGridAttrSaveReqVO createReqVO) {
        // 插入
        EvalGridAttrDO evalGridAttr = BeanUtils.toBean(createReqVO, EvalGridAttrDO.class);
        evalGridAttrMapper.insert(evalGridAttr);
        // 返回
        return evalGridAttr.getId();
    }

    @Override
    public void updateEvalGridAttr(EvalGridAttrSaveReqVO updateReqVO) {
        // 校验存在
        validateEvalGridAttrExists(updateReqVO.getId());
        // 更新
        EvalGridAttrDO updateObj = BeanUtils.toBean(updateReqVO, EvalGridAttrDO.class);
        evalGridAttrMapper.updateById(updateObj);
    }

    @Override
    public void deleteEvalGridAttr(Long id) {
        // 校验存在
        validateEvalGridAttrExists(id);
        // 删除
        evalGridAttrMapper.deleteById(id);
    }

    private void validateEvalGridAttrExists(Long id) {
        if (evalGridAttrMapper.selectById(id) == null) {
            throw exception(EVAL_GRID_ATTR_NOT_EXISTS);
        }
    }

    @Override
    public EvalGridAttrDO getEvalGridAttr(Long id) {
        return evalGridAttrMapper.selectById(id);
    }

    @Override
    public PageResult<EvalGridAttrDO> getEvalGridAttrPage(EvalGridAttrPageReqVO pageReqVO) {
        return evalGridAttrMapper.selectPage(pageReqVO);
    }

}