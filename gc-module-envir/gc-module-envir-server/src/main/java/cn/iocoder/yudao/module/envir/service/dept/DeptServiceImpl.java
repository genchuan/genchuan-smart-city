package cn.iocoder.yudao.module.envir.service.dept;

import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.envir.controller.admin.dept.vo.*;
import cn.iocoder.yudao.module.envir.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.envir.dal.mysql.dept.DeptMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envir.enums.ErrorCodeConstants.*;

/**
 * 部门 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DeptServiceImpl implements DeptService {

    @Resource
    private DeptMapper deptMapper;

    @Override
    public Long createDept(DeptSaveReqVO createReqVO) {
        // 插入
        DeptDO dept = BeanUtils.toBean(createReqVO, DeptDO.class);
        deptMapper.insert(dept);
        // 返回
        return dept.getId();
    }

    @Override
    public void updateDept(DeptSaveReqVO updateReqVO) {
        // 校验存在
        validateDeptExists(updateReqVO.getId());
        // 更新
        DeptDO updateObj = BeanUtils.toBean(updateReqVO, DeptDO.class);
        deptMapper.updateById(updateObj);
    }

    @Override
    public void deleteDept(Long id) {
        // 校验存在
        validateDeptExists(id);
        // 删除
        deptMapper.deleteById(id);
    }

    private void validateDeptExists(Long id) {
        if (deptMapper.selectById(id) == null) {
            throw exception(DEPT_NOT_EXISTS);
        }
    }

    @Override
    public DeptDO getDept(Long id) {
        return deptMapper.selectById(id);
    }

    @Override
    public PageResult<DeptDO> getDeptPage(DeptPageReqVO pageReqVO) {
        return deptMapper.selectPage(pageReqVO);
    }

}