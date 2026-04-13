package cn.iocoder.yudao.module.studentmgmt.service.dormassign;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormassign.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormassign.DormAssignDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormassign.DormAssignMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 宿舍分配 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormAssignServiceImpl implements DormAssignService {

    @Resource
    private DormAssignMapper dormAssignMapper;

    @Override
    public Long createDormAssign(DormAssignSaveReqVO createReqVO) {
        // 插入
        DormAssignDO dormAssign = BeanUtils.toBean(createReqVO, DormAssignDO.class);
        dormAssignMapper.insert(dormAssign);

        // 返回
        return dormAssign.getId();
    }

    @Override
    public void updateDormAssign(DormAssignSaveReqVO updateReqVO) {
        // 校验存在
        validateDormAssignExists(updateReqVO.getId());
        // 更新
        DormAssignDO updateObj = BeanUtils.toBean(updateReqVO, DormAssignDO.class);
        dormAssignMapper.updateById(updateObj);
    }

    @Override
    public void deleteDormAssign(Long id) {
        // 校验存在
        validateDormAssignExists(id);
        // 删除
        dormAssignMapper.deleteById(id);
    }

    @Override
        public void deleteDormAssignListByIds(List<Long> ids) {
        // 删除
        dormAssignMapper.deleteByIds(ids);
        }


    private void validateDormAssignExists(Long id) {
        if (dormAssignMapper.selectById(id) == null) {
            throw exception(DORM_ASSIGN_NOT_EXISTS);
        }
    }

    @Override
    public DormAssignDO getDormAssign(Long id) {
        return dormAssignMapper.selectById(id);
    }

    @Override
    public PageResult<DormAssignDO> getDormAssignPage(DormAssignPageReqVO pageReqVO) {
        return dormAssignMapper.selectPage(pageReqVO);
    }

}