package cn.iocoder.yudao.module.evaluate.service.object;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
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
import cn.iocoder.yudao.module.evaluate.dal.mysql.area.AreaMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.object.ObjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.objecttype.ObjectTypeMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.relatedobject.RelatedObjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.user.UserMapper;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
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

import java.util.*;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.OBJECT_NOT_EXISTS;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.appendLog;
import static cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils.buildLog;

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
    @Resource
    private UserMapper userMapper;
    @Resource
    private AreaMapper areaMapper;
    @Resource
    private ObjectTypeMapper objectTypeMapper;
    @Resource
    private StatusMapper statusMapper;
    @Resource
    private RelatedObjectMapper relatedObjectMapper;
    @Resource
    private AdminUserApi adminUserApi;
    private static final Logger log = LoggerFactory.getLogger(ObjectServiceImpl.class);
    @Override
    public Long createObject(ObjectSaveReqVO createReqVO) {
        // 插入
        ObjectDO object = BeanUtils.toBean(createReqVO, ObjectDO.class);
        object.setObjectId(UUID.randomUUID().toString());
        // 2. 构建【新增】日志
        String log = buildLog("【新增】",
                StrUtil.format("创建评价对象。名称：{}，所属区域编码：{}，负责人：{}",
                        createReqVO.getName(), createReqVO.getAreaCode(), createReqVO.getManagerId()));

        // 3. 直接设置（因为是新增，没有旧日志）
        object.setChangeLog(log);

        objectMapper.insert(object);
        // 返回
        return object.getId();
    }
    /**
     * 构建单条日志片段，自动加上时间戳
     */

    @Override
    public void updateObject(ObjectSaveReqVO updateReqVO) {
        // 校验存在
        validateObjectExists(updateReqVO.getId());
        ObjectDO oldObject = getObject(updateReqVO.getId());
        // 2. 【变更日志逻辑】对比新旧数据，生成日志
        // 2. 逐字段对比，构建变更内容（核心：只记录有变化的字段，无变更不记录）
        StringJoiner changeContent = new StringJoiner("；");
        // 2.1 对比对象名称
        if (!Objects.equals(oldObject.getName(), updateReqVO.getName()) && updateReqVO.getName()!= null) {
            changeContent.add(StrUtil.format("对象名称由【{}】改为【{}】", oldObject.getName(), updateReqVO.getName()));
        }
        // 2.2 对比对象编码
        if (!Objects.equals(oldObject.getCode(), updateReqVO.getCode()) && updateReqVO.getCode() != null) {
            changeContent.add(StrUtil.format("对象编码由【{}】改为【{}】", oldObject.getCode(), updateReqVO.getCode()));
        }
        // 2.3 对比所属区域（ID转名称，提升可读性）
        if (!Objects.equals(oldObject.getAreaCode(), updateReqVO.getAreaCode()) && updateReqVO.getAreaCode()!=null) {
            AreaDO oldAreaName = areaMapper.selectOne(
                    new LambdaQueryWrapperX<AreaDO>()
                            .eq(AreaDO::getAreaCode,oldObject.getAreaCode())
            );
            AreaDO newAreaName = areaMapper.selectOne(
                    new LambdaQueryWrapperX<AreaDO>()
                            .eq(AreaDO::getAreaCode,updateReqVO.getAreaCode())
            );
            if (oldAreaName != null && newAreaName !=null){
            changeContent.add(StrUtil.format("所属区域由【{}】改为【{}】", oldAreaName.getAreaName(), newAreaName.getAreaName()));
            }
        }
        // 2.4 对比对象类型
        if (!Objects.equals(oldObject.getObjectTypeId(), updateReqVO.getObjectTypeId()) && updateReqVO.getObjectTypeId()!=null) {
//            String oldTypeName = oldObject.getObjectTypeId();
//            String newTypeName = updateReqVO.getObjectTypeId();
            ObjectTypeDO oldTypeName = objectTypeMapper.selectOne(
                    new LambdaQueryWrapperX<ObjectTypeDO>()
                            .eq(ObjectTypeDO::getTypeId,oldObject.getObjectTypeId())
            );
            ObjectTypeDO newTypeName = objectTypeMapper.selectOne(
                    new LambdaQueryWrapperX<ObjectTypeDO>()
                            .eq(ObjectTypeDO::getTypeId,updateReqVO.getObjectTypeId())
            );
            changeContent.add(StrUtil.format("对象类型由【{}】改为【{}】", oldTypeName.getName(), newTypeName.getName()));
        }
        // 2.5 对比负责人
        if (!Objects.equals(oldObject.getManagerId(), updateReqVO.getManagerId()) && updateReqVO.getManagerId()!=null) {
            //String newUserName = updateReqVO.getManagerName();
            UserDO oldUserName = userMapper.selectOne(
                    new LambdaQueryWrapperX<UserDO>()
                            .eq(UserDO::getUserId,oldObject.getManagerId())
            );
            UserDO newUserName = userMapper.selectOne(
                    new LambdaQueryWrapperX<UserDO>()
                            .eq(UserDO::getUserId,updateReqVO.getManagerId())
            );
            if (oldUserName != null && newUserName != null){
                changeContent.add(StrUtil.format("负责人由【{}】改为【{}】", oldUserName.getUserName(), newUserName.getUserName()));
            }
        }
//        // 2.6 对比联系电话
//        if (!Objects.equals(oldObject.getExtCommon1(), updateReqVO.getExtCommon1())) {
//            changeContent.add(StrUtil.format("联系电话由【{}】改为【{}】", oldObject.getExtCommon1(), updateReqVO.getExtCommon1()));
//        }
        // 2.7 对比关联网格/部门
        if (!Objects.equals(oldObject.getRelatedId(), updateReqVO.getRelatedId()) && updateReqVO.getRelatedId()!=null) {
//            String oldRelatedName = oldObject.getRelatedId();
//            String newRelatedName = updateReqVO.getRelatedId();
            RelatedObjectDO oldRelatedName =relatedObjectMapper.selectOne(
                    new LambdaQueryWrapperX<RelatedObjectDO>()
                            .eq(RelatedObjectDO::getRelatedId,oldObject.getRelatedId())
            );
            RelatedObjectDO newRelatedName =relatedObjectMapper.selectOne(
                    new LambdaQueryWrapperX<RelatedObjectDO>()
                            .eq(RelatedObjectDO::getRelatedId,updateReqVO.getRelatedId())
            );
            if (oldRelatedName!=null&&newRelatedName!=null){
            changeContent.add(StrUtil.format("关联网格/部门由【{}】改为【{}】", oldRelatedName.getRelatedName(), newRelatedName.getRelatedName()));
            }
        }

        // 2.8 对比状态
        if (!Objects.equals(oldObject.getStatusId(), updateReqVO.getStatusId()) && updateReqVO.getStatusId()!= null) {
            String oldObjectStatus = getStatusNameById(oldObject.getStatusId());
            String newObjectStatus = getStatusNameById(updateReqVO.getStatusId());
            changeContent.add(StrUtil.format("状态由【{}】改为【{}】", oldObjectStatus, newObjectStatus));
        }
        // 3. 边界处理：无任何字段变更，直接返回，不执行更新和日志生成
        if (changeContent.length() == 0) {
            return;
        }

        // 4. 【核心】追加日志，不覆盖原有历史记录
        String newChangeLog = appendLog(oldObject.getChangeLog(), "【编辑】", changeContent.toString());

        // 更新
        ObjectDO updateObj = BeanUtils.toBean(updateReqVO, ObjectDO.class);
        updateObj.setChangeLog(newChangeLog);
        objectMapper.updateById(updateObj);
    }

    @Override
    public void deleteObject(Long id) {
        // 校验存在
        validateObjectExists(id);
        // 删除
        objectMapper.deleteById(id);
    }
    // ====================== 辅助工具方法：状态ID转名称 ======================
    /**
     * 根据状态ID(sys_status.status_id)获取状态名称
     * @param statusId 状态ID
     * @return 状态名称，若查询失败则返回ID本身，避免日志报错
     */
    private String getStatusNameById(String statusId) {
        if (StrUtil.isEmpty(statusId)) {
            return "无";
        }

        try {
            // ========== 方式 A：直接 Mapper 查询实现 ==========
            StatusDO status = statusMapper.selectOne(
                    new LambdaQueryWrapperX<StatusDO>()
                            .eq(StatusDO::getStatusId, statusId) // 假设 StatusDO 的主键字段叫 statusId
            );

            if (status != null) {
                // 假设 StatusDO 里存名称的字段叫 statusName 或 name
                return status.getName();
            }
            // ===================================================

            // ========== 方式 B：调用系统 Api 实现（二选一） ==========
            // StatusRespDTO statusResp = adminStatusApi.getStatus(Long.valueOf(statusId));
            // if (statusResp != null) {
            //     return statusResp.getName();
            // }
            // ========================================================

        } catch (Exception e) {
            // 捕获异常，防止因为关联数据缺失导致整个编辑接口报错
            log.error("[getStatusNameById] 获取状态名称失败，statusId:{}", statusId, e);
        }

        // 兜底策略：查不到或报错时，直接返回 ID 字符串，保证日志不中断
        return statusId;
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
    public void validateNameUnique(String name, String areaCode, String excludeObjectId) {
        ObjectDO object = objectMapper.selectByNameAndArea(name, areaCode);
        if (object != null && !Objects.equals(object.getObjectId(), excludeObjectId)) {
            throw exception(OBJECT_NAME_DUPLICATE);
        }
        System.out.println("评价对象唯一");
    }
//新
    @Override
    public PageResult<ObjectRespVO> getAllObjectPage(ObjectPageReqVO pageParam) {
//        // 直接调用Mapper的联表方法
//        return  objectMapper.selectAllObjectJoinPage(pageParam);
        // 1. 先调用 Mapper 查主表（去掉 UserDO 的跨库联表）
        PageResult<ObjectRespVO> pageResult = objectMapper.selectAllObjectJoinPage(pageParam);

        // 2. 如果没数据，直接返回
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        // ========== 【核心新增】跨库批量查创建人和更新人 ==========
        // 3. 提取所有创建人/更新人ID（去重，Long类型，无类型转换）
        Set<Long> allUserIds = new HashSet<>();
        pageResult.getList().forEach(vo -> {
            // 3.1 安全提取创建人ID（String转Long，带三重校验）
            String creator = vo.getCreator(); // 假设你的VO字段名是creator
            if (StrUtil.isNotBlank(creator) // 1. 非空
                    && NumberUtil.isNumber(creator) // 2. 是数字
                     ) {
                allUserIds.add(Long.valueOf(creator));
            }

            // 3.2 安全提取更新人ID（同上）
            String updater = vo.getUpdater(); // 假设你的VO字段名是updater
            if (StrUtil.isNotBlank(updater)
                    && NumberUtil.isNumber(updater)) {
                allUserIds.add(Long.valueOf(updater));
            }
        });

// 4. 批量调用系统用户 API，跨库查询用户信息（一次性赋值，userMap为有效final）
        final Map<Long, AdminUserRespDTO> userMap = CollUtil.isNotEmpty(allUserIds)
                ? adminUserApi.getUserMap(allUserIds)
                : new HashMap<>();

// 5. 回填用户姓名到VO
        pageResult.getList().forEach(vo -> {    // 5.1 安全回填创建人姓名
            String creator = vo.getCreator();
            if (StrUtil.isNotBlank(creator)
                    && NumberUtil.isNumber(creator)
                    ) {
                AdminUserRespDTO creatorUser = userMap.get(Long.valueOf(creator));
                if (creatorUser != null) {
                    vo.setCreateUserName(creatorUser.getNickname());
                }
            }

            // 5.2 安全回填更新人姓名
            String updater = vo.getUpdater();
            if (StrUtil.isNotBlank(updater)
                    && NumberUtil.isNumber(updater)
                   ) {
                AdminUserRespDTO updaterUser = userMap.get(Long.valueOf(updater));
                if (updaterUser != null) {
                    vo.setUpdateUserName(updaterUser.getNickname());
                }
            }
            // 6. 保留原有的变更日志截取
            if (StrUtil.isNotBlank(vo.getChangeLog())) {
                vo.setChangeLogShort(vo.getChangeLog().length() > 50
                        ? vo.getChangeLog().substring(0, 50) + "..."
                        : vo.getChangeLog());
            }
        });

        return pageResult;

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