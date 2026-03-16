package cn.iocoder.yudao.module.envirhealth.service.problemtype;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.ProblemTypePageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.problemtype.vo.ProblemTypeSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.problemtype.ProblemTypeDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.problemtype.ProblemTypeMapper;
import cn.iocoder.yudao.module.envirhealth.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PROBLEM_TYPE_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getProblemTypeOptions() {

        List<ProblemTypeDO> list;
        list = problemTypeMapper.selectList(
                new LambdaQueryWrapperX<ProblemTypeDO>()
                        .eq(ProblemTypeDO::getDeleted, 0)
                        .orderByDesc(ProblemTypeDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, ProblemTypeDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(ProblemTypeDO.getName());
            vo.setValue(ProblemTypeDO.getSysProblemTypeId());
            return vo;
        });
    }
}