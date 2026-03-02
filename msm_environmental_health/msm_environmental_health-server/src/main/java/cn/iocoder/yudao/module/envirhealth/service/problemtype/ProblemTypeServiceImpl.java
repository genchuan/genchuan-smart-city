package cn.iocoder.yudao.module.envirhealth.service.problemtype;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.*;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.problemtype.ProblemTypeDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.problemtype.ProblemTypeMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 问题类型字典表【通用复用】 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ProblemTypeServiceImpl implements ProblemTypeService {

    @Resource
    private ProblemTypeMapper problemTypeMapper;

    @Override
    public Long createProblemType(ProblemTypeSaveReqVO createReqVO) {
        // 插入
        ProblemTypeDO problemType = BeanUtils.toBean(createReqVO, ProblemTypeDO.class);
        problemTypeMapper.insert(problemType);
        // 返回
        return problemType.getId();
    }

    @Override
    public void updateProblemType(ProblemTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateProblemTypeExists(updateReqVO.getId());
        // 更新
        ProblemTypeDO updateObj = BeanUtils.toBean(updateReqVO, ProblemTypeDO.class);
        problemTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteProblemType(Long id) {
        // 校验存在
        validateProblemTypeExists(id);
        // 删除
        problemTypeMapper.deleteById(id);
    }

    private void validateProblemTypeExists(Long id) {
        if (problemTypeMapper.selectById(id) == null) {
            throw exception(PROBLEM_TYPE_NOT_EXISTS);
        }
    }

    @Override
    public ProblemTypeDO getProblemType(Long id) {
        return problemTypeMapper.selectById(id);
    }

    @Override
    public PageResult<ProblemTypeDO> getProblemTypePage(ProblemTypePageReqVO pageReqVO) {
        return problemTypeMapper.selectPage(pageReqVO);
    }

}