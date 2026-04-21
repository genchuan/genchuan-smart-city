package cn.iocoder.yudao.module.kitchen.service.vrv.dictionary.bizdictitem;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemPageReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemSaveReqVO;
import cn.iocoder.yudao.module.kitchen.controller.admin.vrv.dictionary.bizdictitem.vo.ops.*;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import cn.iocoder.yudao.module.kitchen.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdictitem.BizDictItemMapper;
import cn.iocoder.yudao.module.kitchen.dal.mysql.vrv.dictionary.bizdicttype.BizDictTypeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Valid;
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
 * 业务字典项 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
public class BizDictItemServiceImpl implements BizDictItemService {

    @Resource
    private BizDictItemMapper bizDictItemMapper;

    @Resource
    private BizDictTypeMapper bizDictTypeMapper;
    @Resource
    private jakarta.validation.Validator validator;


    @Override
    public Long createBizDictItem(BizDictItemSaveReqVO createReqVO) {
        // 插入
        BizDictItemDO bizDictItem = BeanUtils.toBean(createReqVO, BizDictItemDO.class);
        bizDictItemMapper.insert(bizDictItem);

        // 返回
        return bizDictItem.getId();
    }

    @Override
    public void updateBizDictItem(BizDictItemSaveReqVO updateReqVO) {
        // 校验存在
        validateBizDictItemExists(updateReqVO.getId());
        // 更新
        BizDictItemDO updateObj = BeanUtils.toBean(updateReqVO, BizDictItemDO.class);
        bizDictItemMapper.updateById(updateObj);
    }

    @Override
    public void deleteBizDictItem(Long id) {
        // 校验存在
        validateBizDictItemExists(id);
        // 删除
        bizDictItemMapper.deleteById(id);
    }

    @Override
        public void deleteBizDictItemListByIds(List<Long> ids) {
        // 删除
        bizDictItemMapper.deleteByIds(ids);
        }


    private void validateBizDictItemExists(Long id) {
        if (bizDictItemMapper.selectById(id) == null) {
            throw exception("字典项不存在");
        }
    }

    @Override
    public BizDictItemDO getBizDictItem(Long id) {
        return bizDictItemMapper.selectById(id);
    }

    @Override
    public PageResult<BizDictItemDO> getBizDictItemPage(BizDictItemPageReqVO pageReqVO) {
        return bizDictItemMapper.selectPage(pageReqVO);
    }

//    @Override
//    public List<ListByTypeResp> listByType(ListByTypeReq req) {
//        // 1. 构建 MP 条件构造器
//        LambdaQueryWrapper<BizDictItemDO> wrapper = new LambdaQueryWrapper<>();
//
//        // 2. 拼接条件：根据 type_code 查询 + 未删除 + 启用
//        wrapper.eq(BizDictItemDO::getTypeCode, req.getTypeCode())
//                .eq(BizDictItemDO::getDeleted, 0)  // 逻辑删除
//                .eq(BizDictItemDO::getStatus, 1)     // 状态启用
//                .orderByAsc(BizDictItemDO::getSort)  // 按 sort 排序
//                .orderByAsc(BizDictItemDO::getCreateTime); // 再按创建时间
//
//        // 3. 查询数据
//        List<BizDictItemDO> items = bizDictItemMapper.selectList(wrapper);
//
//        // 4. 转成 VO 返回
//        return BeanUtils.toBean(items, ListByTypeResp.class);
//
//
//
//
//    }

    @Override
    public List<ListByTypeResp> listByType(ListByTypeReq req) {
        // 1. 构建条件
        LambdaQueryWrapper<BizDictItemDO> wrapper = new LambdaQueryWrapper<>();

        // ====================== 通用条件 ======================
        wrapper.eq(BizDictItemDO::getDeleted, false)
                .eq(BizDictItemDO::getStatus, 1);

        // ====================== 1. 根据 typeCode 精确查询 ======================
        if (req.getTypeCode() != null && !req.getTypeCode().isEmpty()) {
            wrapper.eq(BizDictItemDO::getTypeCode, req.getTypeCode());
        }

        // ====================== 2. 根据 字典分类名称 查询（连表逻辑） ======================
        if (req.getTypeName() != null && !req.getTypeName().isEmpty()) {
            // 先根据名称查询类型，拿到 typeCode 集合
            LambdaQueryWrapper<BizDictTypeDO> typeWrapper = new LambdaQueryWrapper<>();
            typeWrapper.eq(BizDictTypeDO::getDeleted, false)
                    .eq(BizDictTypeDO::getName, req.getTypeName()); // 精确匹配

            List<BizDictTypeDO> typeList = bizDictTypeMapper.selectList(typeWrapper);
            if (CollUtil.isEmpty(typeList)) {
                return Collections.emptyList(); // 无数据直接返回
            }

            // 提取 typeCode 进行 IN 查询
            List<String> typeCodeList = typeList.stream()
                    .map(BizDictTypeDO::getUniCode)
                    .toList();
            wrapper.in(BizDictItemDO::getTypeCode, typeCodeList);
        }

        // ====================== 排序 ======================
        wrapper.orderByAsc(BizDictItemDO::getSort)
                .orderByAsc(BizDictItemDO::getCreateTime);

        // ====================== 查询 ======================
        List<BizDictItemDO> items = bizDictItemMapper.selectList(wrapper);
        // ====================== 转换 VO ======================
        return BeanUtils.toBean(items, ListByTypeResp.class);

    }

    @Override
    public Long addBizDictItem(AddReq createReqVO) {
        String typeCode = createReqVO.getTypeCode();
        String dictKey = createReqVO.getDictKey();

        // ====================== 1. 校验该 typeCode 在分类表中存在 ======================
        LambdaQueryWrapper<BizDictTypeDO> typeWrapper = new LambdaQueryWrapper<>();
        typeWrapper.eq(BizDictTypeDO::getUniCode, typeCode)
                .eq(BizDictTypeDO::getDeleted, 0);
        BizDictTypeDO typeDO = bizDictTypeMapper.selectOne(typeWrapper);

        if (typeDO == null) {
            throw exception("字典类型【" + typeCode + "】不存在，请先创建字典分类");
        }

        // ====================== 2. 校验【同类型下 dictKey 唯一】 ======================
        LambdaQueryWrapper<BizDictItemDO> keyWrapper = new LambdaQueryWrapper<>();
        keyWrapper.eq(BizDictItemDO::getTypeCode, typeCode)
                .eq(BizDictItemDO::getDictKey, dictKey)
                .eq(BizDictItemDO::getDeleted, 0);

        Long count = bizDictItemMapper.selectCount(keyWrapper);
        if (count > 0) {
            throw exception("字典类型【" + typeCode + "】下，字典键【" + dictKey + "】已存在，请勿重复添加");
        }

        // ====================== 3. 自动获取当前类型下最大 sort，+1 ======================
        LambdaQueryWrapper<BizDictItemDO> sortWrapper = new LambdaQueryWrapper<>();
        sortWrapper.eq(BizDictItemDO::getTypeCode, typeCode)
                .eq(BizDictItemDO::getDeleted, 0)
                .orderByDesc(BizDictItemDO::getSort)
                .last("LIMIT 1"); // 只取最大一条

        BizDictItemDO maxSortItem = bizDictItemMapper.selectOne(sortWrapper);
        int newSort = (maxSortItem == null || maxSortItem.getSort() == null) ? 1 : maxSortItem.getSort() + 1;

        // ====================== 4. 完善实体 ======================
        BizDictItemDO item = new BizDictItemDO();
        BeanUtils.copyProperties(createReqVO, item);

        // 自动设置的字段
        item.setSort(newSort);
        item.setStatus(1); // 默认启用
        if (item.getColor() == null || item.getColor().isEmpty()) {
            item.setColor("#1890ff"); // 默认蓝色
        }

        // ====================== 5. 插入数据库并返回 ID ======================
        bizDictItemMapper.insert(item);
        return item.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateBiz(UpdateReq updateReqVO) {
        // 要更新的字段：dict_key，dict_label，color，sort，description，remark，status

        // ====================== 1. 校验字典项存在 ======================
        BizDictItemDO oldItem = bizDictItemMapper.selectById(updateReqVO.getId());
        if (oldItem == null) {
            throw exception("字典项不存在");
        }
        // 从旧数据里拿 typeCode（更新不允许改 typeCode，安全！）
        String typeCode = oldItem.getTypeCode();
        String newDictKey = updateReqVO.getDictKey();

        // ====================== 2. 校验同类型下 dictKey 唯一（排除自身） ======================
        LambdaQueryWrapper<BizDictItemDO> keyWrapper = new LambdaQueryWrapper<>();
        keyWrapper.eq(BizDictItemDO::getTypeCode, typeCode)
                .eq(BizDictItemDO::getDictKey, newDictKey)
                .ne(BizDictItemDO::getId, updateReqVO.getId()) // 排除自己
                .eq(BizDictItemDO::getDeleted, 0);

        Long count = bizDictItemMapper.selectCount(keyWrapper);
        if (count > 0) {
            // 先拼接字符串，再抛异常
            String message = String.format("字典类型【%s】下，字典键【%s】已存在", typeCode, newDictKey);
            throw exception(message);
        }

        // ====================== 3. 校验 color 必须是合法十六进制颜色 ======================
        String color = updateReqVO.getColor();
        if (color != null && !color.isEmpty()) {
            // 正则校验：# 开头 + 6位十六进制字符
            if (!color.matches("^#([0-9a-fA-F]{6})$")) {
                throw exception("颜色格式不正确，请输入 # 开头的6位十六进制值，例如：#1890ff");
            }
        }

        // ====================== 4. 校验 status 只能是 0 或 1 ======================
        Integer status = updateReqVO.getStatus();
        if (status == null || (!status.equals(0) && !status.equals(1))) {
            throw exception("状态只能是 0-禁用 或 1-启用");
        }

        // ====================== 5. 构建更新对象（只更新你指定的8个字段） ======================
        BizDictItemDO updateItem = new BizDictItemDO();
        updateItem.setId(updateReqVO.getId());
        updateItem.setDictKey(updateReqVO.getDictKey());
        updateItem.setDictLabel(updateReqVO.getDictLabel());
        updateItem.setColor(updateReqVO.getColor());
        updateItem.setSort(updateReqVO.getSort());
        updateItem.setDescription(updateReqVO.getDescription());
        updateItem.setRemark(updateReqVO.getRemark());
        updateItem.setStatus(updateReqVO.getStatus());

        // ====================== 6. 执行更新 ======================
        bizDictItemMapper.updateById(updateItem);
    }

//    @Override
    @Override
    public BatchResult batchAddBizDictItem(List<AddReq> addReqList) {
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
                // 核心：调用你已写好的单条新增方法
                addBizDictItem(req);
//                // ✅ 关键：用 self 调用，触发 @Valid 校验！
//                self.addBizDictItem(req);
                success++;
            } catch (Exception e) {
                // 失败收集
                BatchResult.FailItem failItem = new BatchResult.FailItem();
                failItem.setIndex(index);
                failItem.setData(req.toString());
                failItem.setErrorReason(e.getMessage());
                failList.add(failItem);
            }
        }

        // 3. 封装返回
        result.setSuccessCount(success);
        result.setFailureCount(failList.size());
        result.setTotalCount(addReqList.size());
        result.setFailList(failList);
        return result;
    }
}
