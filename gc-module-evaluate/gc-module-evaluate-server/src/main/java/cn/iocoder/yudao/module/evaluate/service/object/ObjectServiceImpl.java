package cn.iocoder.yudao.module.evaluate.service.object;

import cn.hutool.core.collection.CollUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.CommonResult;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.EvalObjectOverviewVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.object.vo.ObjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.area.AreaDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.baseinfo.relatedobject.RelatedObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.object.ObjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.objecttype.ObjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.object.ObjectMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import groovy.util.logging.Slf4j;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.util.List;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NOT_EXISTS;

/**
 * 评价对象 Service 实现类
 *
 * @author 亘川智城
 */
@Service
@Validated
@Slf4j
@RequiredArgsConstructor
public class ObjectServiceImpl implements ObjectService {

    @Resource
    private ObjectMapper objectMapper;
    private static final Logger log = LoggerFactory.getLogger(ObjectServiceImpl.class);
    @Override
    public Long createObject(ObjectSaveReqVO createReqVO) {
        // 插入
        ObjectDO object = BeanUtils.toBean(createReqVO, ObjectDO.class);
        objectMapper.insert(object);
        // 返回
        return object.getId();
    }

    @Override
    public void updateObject(ObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateObjectExists(updateReqVO.getId());
        // 更新
        ObjectDO updateObj = BeanUtils.toBean(updateReqVO, ObjectDO.class);
        objectMapper.updateById(updateObj);
    }

    @Override
    public void deleteObject(Long id) {
        // 校验存在
        validateObjectExists(id);
        // 删除
        objectMapper.deleteById(id);
    }

    private void validateObjectExists(Long id) {
        if (objectMapper.selectById(id) == null) {
            throw exception(OBJECT_NOT_EXISTS);
        }
    }

    @Override
    public ObjectDO getObject(Long id) {
        return objectMapper.selectById(id);
    }

    @Override
    public PageResult<ObjectDO> getObjectPage(ObjectPageReqVO pageReqVO) {
        return objectMapper.selectPage(pageReqVO);
    }

    /**
     * 详情查询（文档弹窗需求）
     */
    @Override
    public ObjectRespVO getDetail(Long id) {
        return objectMapper.selectJoinDetail(id);
    }



    /**
     * 新增（文档右侧抽屉，含数据校验）
     * 框架生成：方法壳、事务注解、DO赋值、Mapper调用
     * 手动补充：文档要求的唯一性校验、手机号校验、变更日志
     */
    @Override
    public PageResult<ObjectRespVO> pageJoinQuery(ObjectPageReqVO reqVO) {
        // 1. 构建MPJ查询条件Wrapper（支持联表）
        MPJLambdaWrapper<ObjectDO> wrapper = new MPJLambdaWrapper<ObjectDO>()
                // 固定过滤软删除：deleted=1的记录不参与查询
                .ne(ObjectDO::getDeleted, 1)
                // 动态条件：前端传了statusId才拼接过滤条件
                .eq(reqVO.getStatusId() != null, ObjectDO::getStatusId, reqVO.getStatusId())
                // ===== 主表字段 =====
                .selectAll(ObjectDO.class)

                // ===== 关联表字段（完全模仿 selectAs 写法）=====
                .selectAs(AreaDO::getAreaName, ObjectRespVO::getAreaName)
                .selectAs(ObjectTypeDO::getName, ObjectRespVO::getObjectTypeName)
                .selectAs(StatusDO::getName, ObjectRespVO::getStatusName)
                .selectAs(UserDO::getUserName, ObjectRespVO::getManagerName)
                .selectAs(UserDO::getUserPhone, ObjectRespVO::getManagerPhone)
                // 创建人（别名 u1）
                .selectAs("u1", UserDO::getUserName, ObjectRespVO::getCreateUserName)
                // 更新人（别名 u2）
                .selectAs("u2", UserDO::getUserName, ObjectRespVO::getUpdateUserName)
                .selectAs(RelatedObjectDO::getRelatedName, ObjectRespVO::getRelatedName)

                // ===== 联表关系（模仿参考代码）=====
                .leftJoin(AreaDO.class, AreaDO::getAreaCode, ObjectDO::getAreaCode)
                .leftJoin(ObjectTypeDO.class, ObjectTypeDO::getTypeId, ObjectDO::getObjectTypeId)
                .leftJoin(StatusDO.class, StatusDO::getStatusId, ObjectDO::getStatusId)
                .leftJoin(UserDO.class, UserDO::getUserId, ObjectDO::getManagerId)
                // 创建人关联（别名 u1）
                .leftJoin(UserDO.class, "u1", UserDO::getUserId, ObjectDO::getCreateBy)
                // 更新人关联（别名 u2）
                .leftJoin(UserDO.class, "u2", UserDO::getUserId, ObjectDO::getUpdateBy)
                .leftJoin(RelatedObjectDO.class, RelatedObjectDO::getRelatedId, ObjectDO::getRelatedId)

//                // ===== 过滤条件（模仿参考代码的 like/eq）=====
//                .like(StrUtil.isNotBlank(reqVO.getKeyword()), ObjectDO::getName, reqVO.getKeyword())
//                .like(StrUtil.isNotBlank(reqVO.getKeyword()), ObjectDO::getCode, reqVO.getKeyword())

                // ===== 排序 =====
                .orderByDesc(ObjectDO::getCreateTime);
        // 【如需联表，在这里补充逻辑，示例如下】
        // .leftJoin(OtherDO.class, OtherDO::getId, ObjectDO::getRelationId)
        // .selectAll(ObjectDO.class) // 查询主表全部字段
        // .select(OtherDO::getRelationName) // 查询关联表字段

        // 2. 直接调用MPJ内置的selectJoinPage，完全匹配你项目的默认方法签名
        // 入参1：reqVO（继承PageParam，自带pageNo/pageSize）
        // 入参2：返回结果的类型Class
        // 入参3：构建好的MPJ查询Wrapper
        return objectMapper.selectJoinPage(reqVO, ObjectRespVO.class, wrapper);

    }

    @Override
    public ObjectRespVO getDetailById(String objectId) {
        return objectMapper.selectDetailById(objectId);
    }



    @Override
    @Transactional(rollbackFor = Exception.class)
    public void importObjects(List<ObjectSaveReqVO> importList) {
        if (CollUtil.isEmpty(importList)) {
            return;
        }

        // 批量导入
        for (ObjectSaveReqVO saveReqVO : importList) {
            try {
                createObject(saveReqVO);
            } catch (ServiceException e) {
                log.error("导入评价对象失败：{}", saveReqVO.getName(), e);
                throw e;
            }
        }
    }

    @Override
    public CommonResult<String> validateNameUnique(String name, String areaCode, String excludeObjectId) {
        ObjectDO object = objectMapper.selectByNameAndArea(name, areaCode);
        if (object != null && !object.getObjectId().equals(excludeObjectId)) {
            throw exception(OBJECT_NAME_DUPLICATE);
        }
        return CommonResult.success("评价对象唯一");
    }
//新
    @Override
    public PageResult<ObjectRespVO> getAllObjectPage(ObjectPageReqVO pageParam) {
        // 直接调用Mapper的联表方法
        return  objectMapper.selectAllObjectJoinPage(pageParam);
    }
    /**
     * 公共方法：构造基础查询条件（过滤deleted=1）
     * @return LambdaQueryWrapper<ObjectDO>
     */
    private LambdaQueryWrapper<ObjectDO> getBaseWrapper() {
        // 固定过滤deleted=1的记录，所有统计都基于这个基础条件
        return new LambdaQueryWrapper<ObjectDO>()
                .ne(ObjectDO::getDeleted, 1); //
    }
    /**
     * 状态统计核心方法
     * @param statusId 前端可选入参，null则查全部
     */
    @Override
    public ObjectRespVO getStatusCount(Integer statusId) {
        ObjectRespVO respVO = new ObjectRespVO();

        // 1. 构造基础条件 + 动态拼接statusId
        LambdaQueryWrapper<ObjectDO> baseWrapper = getBaseWrapper();
        if (statusId != null) {
            baseWrapper.eq(ObjectDO::getStatusId, statusId);
        }

        // 2. 统计总数量（核心修改：count → selectCount）
        Long totalCount = objectMapper.selectCount(baseWrapper);
        respVO.setTotalCount(totalCount);

        // 3. 统计status_id=1的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<ObjectDO> status1Wrapper = baseWrapper.clone();
        status1Wrapper.eq(ObjectDO::getStatusId, 1);
        respVO.setStatus1Count(objectMapper.selectCount(status1Wrapper));

        // 4. 统计status_id=2的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<ObjectDO> status2Wrapper = baseWrapper.clone();
        status2Wrapper.eq(ObjectDO::getStatusId, 2);
        respVO.setStatus2Count(objectMapper.selectCount(status2Wrapper));

        return respVO;
    }
    @Override
    public EvalObjectOverviewVO getOverview() {
        EvalObjectOverviewVO vo = new EvalObjectOverviewVO();

        // 1. 组装卡片数据
        EvalObjectOverviewVO.CardData cardData = objectMapper.selectCardCoreData();
        cardData.setTypeCounts(objectMapper.selectTypeCounts());
        vo.setCardData(cardData);

        // 2. 组装圆环图数据
        vo.setTypePieChart(objectMapper.selectTypePieChart());
        vo.setAreaPieChart(objectMapper.selectAreaPieChart());
        vo.setStatusPieChart(objectMapper.selectStatusPieChart());

        // 3. 组装柱状图数据
        vo.setAreaBarChart(objectMapper.selectAreaBarChart());

        return vo;
    }


}