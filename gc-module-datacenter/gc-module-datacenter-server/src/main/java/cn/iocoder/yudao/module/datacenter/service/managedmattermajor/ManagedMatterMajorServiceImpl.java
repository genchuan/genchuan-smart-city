package cn.iocoder.yudao.module.datacenter.service.managedmattermajor;


import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorListReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorPageReqVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorRespVO;
import cn.iocoder.yudao.module.datacenter.controller.admin.managedmattermajor.vo.ManagedMatterMajorSaveReqVO;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.managedmattermajor.ManagedMatterMajorDO;
import cn.iocoder.yudao.module.datacenter.dal.mysql.managedmattermajor.ManagedMatterMajorMapper;
import cn.iocoder.yudao.module.system.dal.dataobject.dept.DeptDO;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.datacenter.enums.ErrorCodeConstants.MANAGED_MATTER_MAJOR_NOT_EXISTS;

/**
 * 管理事项分类表（含大类和小类） Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class ManagedMatterMajorServiceImpl implements ManagedMatterMajorService {

    @Resource
    private ManagedMatterMajorMapper managedMatterMajorMapper;

    @Override
    public Long createManagedMatterMajor(ManagedMatterMajorSaveReqVO createReqVO) {
        // 插入
        ManagedMatterMajorDO managedMatterMajor = BeanUtils.toBean(createReqVO, ManagedMatterMajorDO.class);
        managedMatterMajorMapper.insert(managedMatterMajor);
        // 返回
        return managedMatterMajor.getId();
    }

    @Override
    public void updateManagedMatterMajor(ManagedMatterMajorSaveReqVO updateReqVO) {
        // 校验存在
        validateManagedMatterMajorExists(updateReqVO.getId());
        // 更新
        ManagedMatterMajorDO updateObj = BeanUtils.toBean(updateReqVO, ManagedMatterMajorDO.class);
        managedMatterMajorMapper.updateById(updateObj);
    }

    @Override
    public void deleteManagedMatterMajor(Long id) {
        // 校验存在
        validateManagedMatterMajorExists(id);
        // 删除
        managedMatterMajorMapper.deleteById(id);
    }

    private void validateManagedMatterMajorExists(Long id) {
        if (managedMatterMajorMapper.selectById(id) == null) {
            throw exception(MANAGED_MATTER_MAJOR_NOT_EXISTS);
        }
    }

    @Override
    public ManagedMatterMajorDO getManagedMatterMajor(Long id) {
        return managedMatterMajorMapper.selectById(id);
    }

    @Override
    public List<ManagedMatterMajorDO> getManagedMatterMajorList(ManagedMatterMajorListReqVO ReqVO) {
        List<ManagedMatterMajorDO> list = managedMatterMajorMapper.selectList(ReqVO);
        if(!list.isEmpty()){
            list.sort(Comparator.comparing(ManagedMatterMajorDO::getSortNum));
            return list;
        }
        return new ArrayList<>();
    }

    @Override
    public ManagedMatterMajorDO getManagedMatterMajor(ManagedMatterMajorPageReqVO managedMatterMajorRespVO) {
        return managedMatterMajorMapper.selectPage(managedMatterMajorRespVO).getList().get(0);
    }

}