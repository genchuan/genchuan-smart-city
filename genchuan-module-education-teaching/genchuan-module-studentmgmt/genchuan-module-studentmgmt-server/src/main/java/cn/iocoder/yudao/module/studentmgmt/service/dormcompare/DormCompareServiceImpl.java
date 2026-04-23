package cn.iocoder.yudao.module.studentmgmt.service.dormcompare;

import cn.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.module.studentmgmt.controller.admin.dormcompare.vo.*;
import cn.iocoder.yudao.module.studentmgmt.dal.dataobject.dormcompare.DormCompareDO;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;

import cn.iocoder.yudao.module.studentmgmt.dal.mysql.dormcompare.DormCompareMapper;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.studentmgmt.enums.ErrorCodeConstants.*;

/**
 * 宿舍评比 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class DormCompareServiceImpl implements DormCompareService {

    @Resource
    private DormCompareMapper dormCompareMapper;

    @Override
    public Long createDormCompare(DormCompareSaveReqVO createReqVO) {
        // 插入
        DormCompareDO dormCompare = BeanUtils.toBean(createReqVO, DormCompareDO.class);
        dormCompareMapper.insert(dormCompare);

        // 返回
        return dormCompare.getId();
    }

    @Override
    public void updateDormCompare(DormCompareSaveReqVO updateReqVO) {
        // 校验存在
        validateDormCompareExists(updateReqVO.getId());
        // 更新
        DormCompareDO updateObj = BeanUtils.toBean(updateReqVO, DormCompareDO.class);
        dormCompareMapper.updateById(updateObj);
    }

    @Override
    public void deleteDormCompare(Long id) {
        // 校验存在
        validateDormCompareExists(id);
        // 删除
        dormCompareMapper.deleteById(id);
    }

    @Override
        public void deleteDormCompareListByIds(List<Long> ids) {
        // 删除
        dormCompareMapper.deleteByIds(ids);
        }


    private void validateDormCompareExists(Long id) {
        if (dormCompareMapper.selectById(id) == null) {
            throw exception(DORM_COMPARE_NOT_EXISTS);
        }
    }

    @Override
    public DormCompareDO getDormCompare(Long id) {
        return dormCompareMapper.selectById(id);
    }

    @Override
    public PageResult<DormCompareDO> getDormComparePage(DormComparePageReqVO pageReqVO) {
        return dormCompareMapper.selectPage(pageReqVO);
    }

}