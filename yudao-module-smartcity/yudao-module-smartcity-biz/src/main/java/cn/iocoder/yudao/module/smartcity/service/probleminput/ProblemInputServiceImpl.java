package cn.iocoder.yudao.module.smartcity.service.probleminput;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.smartcity.controller.admin.probleminput.vo.*;
import cn.iocoder.yudao.module.smartcity.dal.dataobject.probleminput.ProblemInputDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.smartcity.dal.mysql.probleminput.ProblemInputMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.smartcity.enums.ErrorCodeConstants.*;

/**
 * 问题录入 Service 实现类
 *
 * @author 智慧城市运行管理服务平台
 */
@Service
@Validated
public class ProblemInputServiceImpl implements ProblemInputService {

    @Resource
    private ProblemInputMapper problemInputMapper;

    @Override
    public Long createProblemInput(ProblemInputSaveReqVO createReqVO) {
        // 插入
        ProblemInputDO problemInput = BeanUtils.toBean(createReqVO, ProblemInputDO.class);
        problemInputMapper.insert(problemInput);
        // 返回
        return problemInput.getId();
    }

    @Override
    public void updateProblemInput(ProblemInputSaveReqVO updateReqVO) {
        // 校验存在
        validateProblemInputExists(updateReqVO.getId());
        // 更新
        ProblemInputDO updateObj = BeanUtils.toBean(updateReqVO, ProblemInputDO.class);
        problemInputMapper.updateById(updateObj);
    }

    @Override
    public void deleteProblemInput(Long id) {
        // 校验存在
        validateProblemInputExists(id);
        // 删除
        problemInputMapper.deleteById(id);
    }

    private void validateProblemInputExists(Long id) {
        if (problemInputMapper.selectById(id) == null) {
            throw exception(PROBLEM_INPUT_NOT_EXISTS);
        }
    }

    @Override
    public ProblemInputDO getProblemInput(Long id) {
        return problemInputMapper.selectById(id);
    }

    @Override
    public PageResult<ProblemInputDO> getProblemInputPage(ProblemInputPageReqVO pageReqVO) {
        return problemInputMapper.selectPage(pageReqVO);
    }

}