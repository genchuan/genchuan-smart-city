package cn.iocoder.yudao.module.envirhealth.service.roadcleaning.cleaningproblem;

import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.roadcleaning.vo.cleaningproblem.CleaningProblemSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.Detail.CleaningProblemDetailDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;

import cn.iocoder.yudao.module.envirhealth.dal.dataobject.roadcleaning.CleaningProblemDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envirhealth.dal.mysql.roadcleaning.CleaningProblemMapper;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.*;

/**
 * 道路清扫问题 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class CleaningProblemServiceImpl implements CleaningProblemService {

    @Resource
    private CleaningProblemMapper cleaningProblemMapper;

    @Override
    public Long createCleaningProblem(CleaningProblemSaveReqVO createReqVO) {
        // 插入
        CleaningProblemDO cleaningProblem = BeanUtils.toBean(createReqVO, CleaningProblemDO.class);
        cleaningProblemMapper.insert(cleaningProblem);
        // 返回
        return cleaningProblem.getId();
    }

    @Override
    public void updateCleaningProblem(CleaningProblemSaveReqVO updateReqVO) {
        // 校验存在
        validateCleaningProblemExists(updateReqVO.getId());
        // 更新
        CleaningProblemDO updateObj = BeanUtils.toBean(updateReqVO, CleaningProblemDO.class);
        cleaningProblemMapper.updateById(updateObj);
    }

    @Override
    public void deleteCleaningProblem(Long id) {
        // 校验存在
        validateCleaningProblemExists(id);
        // 删除
        cleaningProblemMapper.deleteById(id);
    }

    private void validateCleaningProblemExists(Long id) {
        if (cleaningProblemMapper.selectById(id) == null) {
            throw exception(CLEANING_PROBLEM_NOT_EXISTS);
        }
    }

    @Override
    public CleaningProblemDO getCleaningProblem(Long id) {
        return cleaningProblemMapper.selectById(id);
    }

    @Override
    public PageResult<CleaningProblemDO> getCleaningProblemPage(CleaningProblemPageReqVO pageReqVO) {
        return cleaningProblemMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<CleaningProblemDetailDO> getCleaningProblemDetailPage(CleaningProblemPageReqVO pageReqVO) {
        Long total = cleaningProblemMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());

        List<CleaningProblemDetailDO> list = cleaningProblemMapper.selectDetailPage(pageReqVO);
        return new PageResult<>(list, total);
    }

}