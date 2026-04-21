package cn.iocoder.yudao.module.stationresource.service.vrv.dictionary.bizdictitem;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemPageReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.BizDictItemSaveReqVO;
import cn.iocoder.yudao.module.stationresource.controller.admin.vrv.dictionary.bizdictitem.vo.ops.*;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.vrv.dictionary.bizdictitem.BizDictItemDO;
import cn.iocoder.yudao.module.stationresource.dal.dataobject.vrv.dictionary.bizdicttype.BizDictTypeDO;
import cn.iocoder.yudao.module.stationresource.dal.mysql.vrv.dictionary.bizdictitem.BizDictItemMapper;
import cn.iocoder.yudao.module.stationresource.dal.mysql.vrv.dictionary.bizdicttype.BizDictTypeMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.stationresource.enums.ErrorCodeConstants.*;

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

    @Override
    public List<ListByTypeResp> listByType(ListByTypeReq req) {
        // 1. 构建字典项查询条件
        LambdaQueryWrapper<BizDictItemDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizDictItemDO::getDeleted, false)
                .eq(BizDictItemDO::getStatus, 1);

        List<String> queryTypeCodes = null;

        // 2. 根据 typeCode 精确查询
        if (req.getTypeCode() != null && !req.getTypeCode().isEmpty()) {
            wrapper.eq(BizDictItemDO::getTypeCode, req.getTypeCode());
            queryTypeCodes = Collections.singletonList(req.getTypeCode());
        }

        // 3. 根据类型名称精确查询（连表）
        if (req.getTypeName() != null && !req.getTypeName().isEmpty()) {
            LambdaQueryWrapper<BizDictTypeDO> typeWrapper = new LambdaQueryWrapper<>();
            typeWrapper.eq(BizDictTypeDO::getDeleted, false)
                    .eq(BizDictTypeDO::getName, req.getTypeName());

            List<BizDictTypeDO> typeList = bizDictTypeMapper.selectList(typeWrapper);
            if (CollUtil.isEmpty(typeList)) {
                return Collections.emptyList();
            }

            queryTypeCodes = typeList.stream()
                    .map(BizDictTypeDO::getUniCode)
                    .toList();
            wrapper.in(BizDictItemDO::getTypeCode, queryTypeCodes);
        }

        // 4. 无查询条件直接返回空
        if (queryTypeCodes == null || queryTypeCodes.isEmpty()) {
            return Collections.emptyList();
        }

        // 5. 查询字典项
        wrapper.orderByAsc(BizDictItemDO::getSort)
                .orderByAsc(BizDictItemDO::getCreateTime);
        List<BizDictItemDO> itemList = bizDictItemMapper.selectList(wrapper);

        // 6. 查询对应的类型信息（用于返回 typeName）
        List<BizDictTypeDO> typeList = bizDictTypeMapper.selectList(
                new LambdaQueryWrapper<BizDictTypeDO>()
                        .in(BizDictTypeDO::getUniCode, queryTypeCodes)
        );

        // 7. 封装成【新结构】返回（核心！）
        return typeList.stream().map(type -> {
            ListByTypeResp resp = new ListByTypeResp();
            resp.setTypeCode(type.getUniCode());
            resp.setTypeName(type.getName());

            // 组装当前类型的字典项
            List<ListByTypeResp.DictItem> items = itemList.stream()
                    .filter(i -> type.getUniCode().equals(i.getTypeCode()))
                    .map(i -> {
                        ListByTypeResp.DictItem item = new ListByTypeResp.DictItem();
                        item.setId(i.getId());
                        item.setDictKey(i.getDictKey());
                        item.setDictLabel(i.getDictLabel());
                        item.setDescription(i.getDescription());
                        return item;
                    }).toList();

            resp.setItemList(items);
            return resp;
        }).toList();
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

    @Override
    public List<ListByTypeResp> listByTypeFuzzy(ListByTypeFuzzyReq reqVO) {
        String typeCode = reqVO.getTypeCode();
        String typeName = reqVO.getTypeName();
        String all = reqVO.getAll();
        Boolean allowMultiType = reqVO.getAllowMultiType();

        // 1. 查询字典分类
        LambdaQueryWrapper<BizDictTypeDO> typeQuery = new LambdaQueryWrapper<>();
        typeQuery.eq(BizDictTypeDO::getDeleted, false)
                .eq(BizDictTypeDO::getStatus, 1);

        boolean hasSearch = false;
        if (all != null && !all.isBlank()) {
            typeQuery.and(q -> q.like(BizDictTypeDO::getUniCode, all)
                    .or()
                    .like(BizDictTypeDO::getName, all));
            hasSearch = true;
        } else {
            if (typeCode != null && !typeCode.isBlank()) {
                typeQuery.like(BizDictTypeDO::getUniCode, typeCode);
                hasSearch = true;
            }
            if (typeName != null && !typeName.isBlank()) {
                typeQuery.like(BizDictTypeDO::getName, typeName);
                hasSearch = true;
            }
        }

        if (!hasSearch) {
            return Collections.emptyList();
        }

        List<BizDictTypeDO> typeList = bizDictTypeMapper.selectList(typeQuery);
        if (CollUtil.isEmpty(typeList)) {
            return Collections.emptyList();
        }

        // 严格模式校验
        List<String> typeCodes = typeList.stream().map(BizDictTypeDO::getUniCode).toList();
        if (!allowMultiType && typeCodes.size() > 1) {
            throw exception("搜索结果集包含多种字典类型，请使用【完整类型编码】进行精准搜索");
        }

        // 2. 查询字典项
        LambdaQueryWrapper<BizDictItemDO> itemQuery = new LambdaQueryWrapper<>();
        itemQuery.eq(BizDictItemDO::getDeleted, false)
                .eq(BizDictItemDO::getStatus, 1)
                .in(BizDictItemDO::getTypeCode, typeCodes);
        List<BizDictItemDO> itemList = bizDictItemMapper.selectList(itemQuery);

        // 3. 按类型分组封装成你要的格式
        return typeList.stream().map(type -> {
            ListByTypeResp resp = new ListByTypeResp();
            resp.setTypeCode(type.getUniCode());
            resp.setTypeName(type.getName());

            // 筛选当前类型的字典项
            List<ListByTypeResp.DictItem> items = itemList.stream()
                    .filter(i -> type.getUniCode().equals(i.getTypeCode()))
                    .map(i -> {
                        ListByTypeResp.DictItem item = new ListByTypeResp.DictItem();
                        item.setId(i.getId());
                        item.setDictKey(i.getDictKey());
                        item.setDictLabel(i.getDictLabel());
                        item.setDescription(i.getDescription());
                        return item;
                    }).toList();

            resp.setItemList(items);
            return resp;
        }).toList();
    }
}
