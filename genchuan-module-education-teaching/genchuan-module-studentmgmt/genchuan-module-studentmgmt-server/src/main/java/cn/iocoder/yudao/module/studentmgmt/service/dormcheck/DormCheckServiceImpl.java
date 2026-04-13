package cn.iocoder.yudao.module.studentmgmt.service.dormcheck;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcheck.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcheck.DormCheckDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcheck.DormCheckMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 宿舍考勤 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormCheckServiceImpl implements DormCheckService {

    @Resource
    private DormCheckMapper dormCheckMapper;

    @Override
    public Long createDormCheck(DormCheckSaveReqVO createReqVO) {
        // 插入
        DormCheckDO dormCheck = BeanUtils.toBean(createReqVO, DormCheckDO.class);
        dormCheckMapper.insert(dormCheck);

        // 返回
        return dormCheck.getId();
    }

    @Override
    public void updateDormCheck(DormCheckSaveReqVO updateReqVO) {
        // 校验存在
        validateDormCheckExists(updateReqVO.getId());
        // 更新
        DormCheckDO updateObj = BeanUtils.toBean(updateReqVO, DormCheckDO.class);
        dormCheckMapper.updateById(updateObj);
    }

    @Override
    public void deleteDormCheck(Long id) {
        // 校验存在
        validateDormCheckExists(id);
        // 删除
        dormCheckMapper.deleteById(id);
    }

    @Override
        public void deleteDormCheckListByIds(List<Long> ids) {
        // 删除
        dormCheckMapper.deleteByIds(ids);
        }


    private void validateDormCheckExists(Long id) {
        if (dormCheckMapper.selectById(id) == null) {
            throw exception(DORM_CHECK_NOT_EXISTS);
        }
    }

    @Override
    public DormCheckDO getDormCheck(Long id) {
        return dormCheckMapper.selectById(id);
    }

    @Override
    public PageResult<DormCheckDO> getDormCheckPage(DormCheckPageReqVO pageReqVO) {
        return dormCheckMapper.selectPage(pageReqVO);
    }

}