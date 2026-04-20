package cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdicttype;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypePageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.BizDictTypeSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops.AddReq;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops.BatchResult;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdicttype.vo.ops.UpdateReq;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdictitem.BizDictItemMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdicttype.BizDictTypeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;


import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.convertList;
import static cn.iocoder.yudao.framework.common.util.collection.CollectionUtils.diffList;
import static cn.iocoder.yudao.module.kitchen.enums.ErrorCodeConstants.*;

/**
 * 业务字典分类 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
public class BizDictTypeServiceImpl implements BizDictTypeService {

    @Resource
    private BizDictTypeMapper bizDictTypeMapper;

//    @Resource
//    private BizDictTypeService bizDictTypeService;

    // 第一步：在这里注入校验器
    @Resource
    private jakarta.validation.Validator validator;
    @Resource
    private BizDictItemMapper bizDictItemMapper;

    @Override
    public Long createBizDictType(BizDictTypeSaveReqVO createReqVO) {
        // 插入
        BizDictTypeDO bizDictType = BeanUtils.toBean(createReqVO, BizDictTypeDO.class);
        bizDictTypeMapper.insert(bizDictType);

        // 返回
        return bizDictType.getId();
    }

    @Override
    public void updateBizDictType(BizDictTypeSaveReqVO updateReqVO) {
        // 校验存在
        validateBizDictTypeExists(updateReqVO.getId());
        // 更新
        BizDictTypeDO updateObj = BeanUtils.toBean(updateReqVO, BizDictTypeDO.class);
        bizDictTypeMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizDictType(Long id) {
        // 校验存在
        validateBizDictTypeExists(id);
        // 删除
        bizDictTypeMapper.deleteById(id);
    }

    @Override
    public void deleteBizDictTypeListByIds(List<Long> ids) {
        // 删除
        bizDictTypeMapper.deleteByIds(ids);
    }


    private void validateBizDictTypeExists(Long id) {
        if (bizDictTypeMapper.selectById(id) == null) {
        }
    }

    @Override
    public BizDictTypeDO getBizDictType(Long id) {
        return bizDictTypeMapper.selectById(id);
    }

    @Override
    public PageResult<BizDictTypeDO> getBizDictTypePage(BizDictTypePageReqVO pageReqVO) {
        return bizDictTypeMapper.selectPage(pageReqVO);
    }

    @Override
    public Long addBizDictType(AddReq createReqVO) {
        // 1. 校验 uni_code 唯一（只查未删除的数据）
        LambdaQueryWrapper<BizDictTypeDO> uniCodeWrapper = Wrappers.lambdaQuery();
        uniCodeWrapper.eq(BizDictTypeDO::getUniCode, createReqVO.getUniCode())
                .eq(BizDictTypeDO::getDeleted, false); // 只查未删除
        BizDictTypeDO existByUniCode = bizDictTypeMapper.selectOne(uniCodeWrapper);
        if (existByUniCode != null) {
            throw exception("uni_code已存在，不能重复");
        }

        // 2. 校验  name 唯一（只查未删除）
        LambdaQueryWrapper<BizDictTypeDO> comboWrapper = Wrappers.lambdaQuery();
        comboWrapper.eq(BizDictTypeDO::getName, createReqVO.getName())
                .eq(BizDictTypeDO::getDeleted, false); // 只查未删除
        BizDictTypeDO existByCombo = bizDictTypeMapper.selectOne(comboWrapper);
        if (existByCombo != null) {
            throw exception("该名称已经存在，请重新设置名称");
        }

        // 2. 获取当前【未删除】的最大排序号（wrapper 方式，无SQL）
        LambdaQueryWrapper<BizDictTypeDO> sortWrapper = Wrappers.lambdaQuery();
        sortWrapper.eq(BizDictTypeDO::getDeleted, false) // 只查未删除
                .orderByDesc(BizDictTypeDO::getSort)
                .last("LIMIT 1");
        BizDictTypeDO maxSortDO = bizDictTypeMapper.selectOne(sortWrapper);
        int newSort = (maxSortDO == null ? 0 : maxSortDO.getSort()) + 1;

        // 3. 实体拷贝 + 赋值
        BizDictTypeDO bizDictType = BeanUtils.toBean(createReqVO, BizDictTypeDO.class);
        bizDictType.setSort(newSort);
        bizDictType.setStatus(1);

        // 4. 插入
        bizDictTypeMapper.insert(bizDictType);

        return bizDictType.getId();
    }

    @Override
    public void updateBiz(UpdateReq updateReqVO) {
        Long id = updateReqVO.getId();
        // 1. 校验数据是否存在（只查未删除）
        LambdaQueryWrapper<BizDictTypeDO> existWrapper = Wrappers.lambdaQuery();
        existWrapper.eq(BizDictTypeDO::getId, id)
                .eq(BizDictTypeDO::getDeleted, false);
        BizDictTypeDO existDict = bizDictTypeMapper.selectOne(existWrapper);
        if (existDict == null) {
            throw exception("字典分类不存在或已删除");
        }

        // 2. 校验修改后的名称不能重复（排除自身ID）
        LambdaQueryWrapper<BizDictTypeDO> nameWrapper = Wrappers.lambdaQuery();
        nameWrapper.eq(BizDictTypeDO::getName, updateReqVO.getName())
                .eq(BizDictTypeDO::getDeleted, false)
                .ne(BizDictTypeDO::getId, id); // 排除自己
        BizDictTypeDO nameExist = bizDictTypeMapper.selectOne(nameWrapper);
        if (nameExist != null) {
            throw exception("该名称已存在，请更换名称");
        }

        // 3. 校验状态只能是 0 或 1
        Integer status = updateReqVO.getStatus();
        if (status != null && !Arrays.asList(0, 1).contains(status)) {
            throw exception("状态只能是 0-禁用 或 1-启用");
        }

        // 4. 构造更新实体（Bean拷贝）
        BizDictTypeDO updateObj = BeanUtils.toBean(updateReqVO, BizDictTypeDO.class);

        // 5. 执行更新
        bizDictTypeMapper.updateById(updateObj);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBizDictTypeBatch(List<Long> ids) {
        if (CollUtil.isEmpty(ids)) {
            throw exception("批量删除ID集合不能为空");
        }

        // 1. 查询要删除的分类
        LambdaQueryWrapper<BizDictTypeDO> typeQuery = Wrappers.lambdaQuery();
        typeQuery.in(BizDictTypeDO::getId, ids)
                .eq(BizDictTypeDO::getDeleted, false);
        List<BizDictTypeDO> typeList = bizDictTypeMapper.selectList(typeQuery);
        if (CollUtil.isEmpty(typeList)) {
            return;
        }

        // 提取编码
        List<String> uniCodeList = typeList.stream()
                .map(BizDictTypeDO::getUniCode)
                .toList();

        // ==========================
        // 2. 批量逻辑删除 字典项（正确用法！）
        // ==========================
        LambdaQueryWrapper<BizDictItemDO> itemQuery = Wrappers.lambdaQuery();
        itemQuery.in(BizDictItemDO::getTypeCode, uniCodeList)
                .eq(BizDictItemDO::getDeleted, false);
        int itemDeleteCount = bizDictItemMapper.delete(itemQuery); // <--- 就用这个！

        // ==========================
        // 3. 批量逻辑删除 分类（正确用法！）
        // ==========================
        int typeDeleteCount = bizDictTypeMapper.delete(typeQuery); // <--- 就用这个！

        log.info("删除的字典项{}条；删除的字典分类{}条",itemDeleteCount,typeDeleteCount);
    }

    @Override
    public BatchResult batchAddBizDictType(List<AddReq> addReqList) {
        BatchResult result = new BatchResult();
        List<BatchResult.FailItem> failList = new ArrayList<>();
        int success = 0;

        // 1. 空数据判断
        if (CollUtil.isEmpty(addReqList)) {
            result.setSuccessCount(0);
            result.setFailureCount(0);
            result.setTotalCount(0);
            result.setFailList(failList);
            return result;
        }

        // 2. 逐条处理（复用单条新增逻辑）
        for (int i = 0; i < addReqList.size(); i++) {
            AddReq req = addReqList.get(i);
            int index = i + 1;
            try {
                // ==============================================
                // 使用 AddReq 里的 @NotEmpty 注解校验！
                // 不用写任何 if！
                // ==============================================
                var violations = validator.validate(req);
                if (!violations.isEmpty()) {
                    throw new IllegalArgumentException(violations.iterator().next().getMessage());
                }
                // 核心：直接调用写好的 add 方法！
                addBizDictType(req);
                success++;
            } catch (Exception e) {
                // 失败了，收集错误信息
                BatchResult.FailItem failItem = new BatchResult.FailItem();
                failItem.setIndex(index);
                failItem.setData(req.toString());
                failItem.setErrorReason(e.getMessage());
                failList.add(failItem);
            }
        }

        // 3. 封装返回结果
        result.setSuccessCount(success);
        result.setFailureCount(failList.size());
        result.setTotalCount(addReqList.size());
        result.setFailList(failList);
        return result;
    }

}
