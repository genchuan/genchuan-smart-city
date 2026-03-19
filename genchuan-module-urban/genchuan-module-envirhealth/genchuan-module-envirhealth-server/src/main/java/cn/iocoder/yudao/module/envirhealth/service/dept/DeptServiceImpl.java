package cn.iocoder.yudao.module.envirhealth.service.dept;

import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.collection.CollectionUtils;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dept.vo.DeptPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.dept.vo.DeptSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dept.DeptDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.dictionary.FacilityDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.dept.DeptMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.vo.OptionVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.DEPT_NOT_EXISTS;

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

    @Override
    public List<OptionVO> getDeptOptions() {

        List<DeptDO> list;
        list = deptMapper.selectList(
                new LambdaQueryWrapperX<DeptDO>()
                        .eq(DeptDO::getDeleted, 0)
                        .orderByDesc(DeptDO::getId)
        );
        // 将DO转换为下拉框VO（label=name，value=id）
        return CollectionUtils.convertList(list, deptDO -> {
            OptionVO vo = new OptionVO();
            vo.setLabel(deptDO.getName());
            vo.setValue(deptDO.getSysDeptId());
            return vo;
        });
    }
}