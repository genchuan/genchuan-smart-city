package cn.iocoder.yudao.module.studentmgmt.service.classassign;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.classassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.classassign.ClassAssignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.classassign.ClassAssignMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 分班管理 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class ClassAssignServiceImpl implements ClassAssignService {

    @Resource
    private ClassAssignMapper classAssignMapper;

    @Override
    public Long createClassAssign(ClassAssignSaveReqVO createReqVO) {
        // 插入
        ClassAssignDO classAssign = BeanUtils.toBean(createReqVO, ClassAssignDO.class);
        classAssignMapper.insert(classAssign);

        // 返回
        return classAssign.getId();
    }

    @Override
    public void updateClassAssign(ClassAssignSaveReqVO updateReqVO) {
        // 校验存在
        validateClassAssignExists(updateReqVO.getId());
        // 更新
        ClassAssignDO updateObj = BeanUtils.toBean(updateReqVO, ClassAssignDO.class);
        classAssignMapper.updateById(updateObj);
    }

    @Override
    public void deleteClassAssign(Long id) {
        // 校验存在
        validateClassAssignExists(id);
        // 删除
        classAssignMapper.deleteById(id);
    }

    @Override
        public void deleteClassAssignListByIds(List<Long> ids) {
        // 删除
        classAssignMapper.deleteByIds(ids);
        }


    private void validateClassAssignExists(Long id) {
        if (classAssignMapper.selectById(id) == null) {
            throw exception(CLASS_ASSIGN_NOT_EXISTS);
        }
    }

    @Override
    public ClassAssignDO getClassAssign(Long id) {
        return classAssignMapper.selectById(id);
    }

    @Override
    public PageResult<ClassAssignDO> getClassAssignPage(ClassAssignPageReqVO pageReqVO) {
        return classAssignMapper.selectPage(pageReqVO);
    }

}