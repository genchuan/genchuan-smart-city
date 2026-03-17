package cn.iocoder.yudao.module.evaluate.service.subject;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.exception.ServiceException;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.EvalSubjectOverviewVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectPageReqVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectRespVO;
import cn.iocoder.yudao.module.evaluate.controller.admin.evalsystem.subject.vo.SubjectSaveReqVO;
import cn.iocoder.yudao.module.evaluate.controller.common.vo.SelectOptionRespVO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subject.SubjectDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.evalsystem.subjectmember.SubjectMemberDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.status.StatusDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.subjecttype.SubjectTypeDO;
import cn.iocoder.yudao.module.evaluate.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.evaluate.dal.mysql.status.StatusMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subject.SubjectMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subjectmember.SubjectMemberMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.subjecttype.SubjectTypeMapper;
import cn.iocoder.yudao.module.evaluate.dal.mysql.user.UserMapper;
import cn.iocoder.yudao.module.evaluate.service.subjecttype.SubjectTypeService;
import cn.iocoder.yudao.module.evaluate.service.user.UserService;
import cn.iocoder.yudao.module.evaluate.util.ChangeLogUtils;
import cn.iocoder.yudao.module.system.api.user.AdminUserApi;
import cn.iocoder.yudao.module.system.api.user.dto.AdminUserRespDTO;
import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.NAME_ALREADY_EXIST;
import static cn.iocoder.yudao.module.evaluate.enums.ErrorCodeConstants.SUBJECT_NOT_EXISTS;

/**
 * 评价主体 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Slf4j
@Validated
public class SubjectServiceImpl implements SubjectService {

    @Resource
    private SubjectMapper subjectMapper;
    @Resource
    private SubjectMemberMapper subjectMemberMapper;
    @Resource
    private SubjectTypeMapper subjectTypeMapper;
    @Resource
    private StatusMapper statusMapper;
    @Resource
    private UserMapper userMapper;
    @Resource
    private AdminUserApi adminUserApi;
    @Resource
    private UserService userService;
    @Resource
    private SubjectTypeService subjectTypeService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createSubject(SubjectSaveReqVO createReqVO) {
        // ========== 新增：第一步先校验名称唯一性 ==========
        String subjectName = createReqVO.getName();
        // 1. 查询是否存在同名且未删除的主体
        LambdaQueryWrapper<SubjectDO> nameCheckWrapper = new LambdaQueryWrapper<>();
        nameCheckWrapper.eq(SubjectDO::getName, subjectName) // 名称匹配
                .eq(SubjectDO::getDeleted, 0); // 只查未删除的（逻辑删除）
        SubjectDO existSubject = subjectMapper.selectOne(nameCheckWrapper);
        // 2. 存在则抛异常，阻止新增
        if (existSubject != null) {
            throw exception(NAME_ALREADY_EXIST);
        }
        // 主表插入
        SubjectDO subject = BeanUtils.toBean(createReqVO, SubjectDO.class);
        //object.setObjectId(UUID.randomUUID().toString());
        subject.setSubjectId(UUID.randomUUID().toString());
        // 2. 构建新增日志，覆盖页面所有关键字段，提升可读性
        StringJoiner logContent = new StringJoiner("，");
        logContent.add(StrUtil.format("主体名称：{}", createReqVO.getName()));
        logContent.add(StrUtil.format("主体编码：{}", createReqVO.getCode()));
        logContent.add(StrUtil.format("主体类型：{}", getSubjectTypeNameById(createReqVO.getSubjectTypeId())));
        logContent.add(StrUtil.format("成员数量：{}", CollUtil.isEmpty(createReqVO.getMemberIds()) ? 0 : createReqVO.getMemberIds().size()));
        // 3. 调用工具类生成标准化日志，设置到DO中
        String changeLog = ChangeLogUtils.buildLog("【新增】", StrUtil.format("创建评价主体。{}", logContent));
        subject.setChangeLog(changeLog);

        subjectMapper.insert(subject);
        String subjectId = subject.getSubjectId();
        // 2. 打印成员列表，排查是否为空
        List<String> memberUserIds = createReqVO.getMemberIds();
        log.info("接收到的成员用户ID列表：{}", memberUserIds);

        // 3. 批量创建成员记录（如果有成员列表）
        if (CollUtil.isNotEmpty(createReqVO.getMemberIds())) {
            List<SubjectMemberDO> memberDOList = createReqVO.getMemberIds().stream()
                    .map(userId -> {
                        SubjectMemberDO memberDO = new SubjectMemberDO();
                        //memberDO.setMemberId(IdUtil.fastSimpleUUID());
                        memberDO.setMemberId(UUID.randomUUID().toString());
                        memberDO.setSubjectId(subjectId); // 关联到刚创建的主体
                        memberDO.setUserId(userId);
                        memberDO.setStatusId(Integer.valueOf(createReqVO.getStatusId())); // 成员状态和主体状态一致
                        //memberDO.setDeleted(0);
                        memberDO.setJoinTime(LocalDateTime.now());
                        memberDO.setCreateTime(LocalDateTime.now());
                        memberDO.setUpdateTime(LocalDateTime.now());
                        return memberDO;
                    })
                    .collect(Collectors.toList());
            // 打印要插入的成员数据，排查数据是否正确
            log.info("准备批量插入的成员数据：{}", memberDOList);
            // 批量插入成员（用MyBatis-Plus的批量插入）
            subjectMemberMapper.insertBatch(memberDOList);
        }

        // 4. 同步更新主表的成员数
        updateSubjectMemberCount(subjectId);
        System.out.println(subjectId);
        return subject.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSubject(SubjectSaveReqVO updateReqVO) {
        // 校验存在
        validateSubjectExists(updateReqVO.getId());
        SubjectDO oldSubject = subjectMapper.selectById(updateReqVO.getId());
        String bizSubjectId = oldSubject.getSubjectId();

        // ====================== 新增：变更日志核心逻辑 start ======================
        // 1.1 初始化变更内容拼接器（只记录有变化的字段）
        StringJoiner changeContent = new StringJoiner("；");

        // 1.2 对比基础字段 - 主体名称
        if (updateReqVO.getName() != null && !Objects.equals(oldSubject.getName(), updateReqVO.getName())) {
            changeContent.add(StrUtil.format("主体名称由【{}】改为【{}】", oldSubject.getName(), updateReqVO.getName()));
        }

        // 1.3 对比基础字段 - 主体编码
        if (updateReqVO.getCode() != null && !Objects.equals(oldSubject.getCode(), updateReqVO.getCode())) {
            changeContent.add(StrUtil.format("主体编码由【{}】改为【{}】", oldSubject.getCode(), updateReqVO.getCode()));
        }

        // 1.4 对比基础字段 - 主体类型（ID转名称，提升日志可读性）
        if (updateReqVO.getSubjectTypeId() != null && !Objects.equals(oldSubject.getSubjectTypeId(), updateReqVO.getSubjectTypeId())) {
            String oldTypeName = getSubjectTypeNameById(oldSubject.getSubjectTypeId());
            String newTypeName = getSubjectTypeNameById(updateReqVO.getSubjectTypeId());
            changeContent.add(StrUtil.format("主体类型由【{}】改为【{}】", oldTypeName, newTypeName));
        }

        // 1.5 对比基础字段 - 联系人（ID转姓名）
        if (updateReqVO.getContactId() != null && !Objects.equals(oldSubject.getContactId(), updateReqVO.getContactId())) {
            String oldContactName = getUserNameById(oldSubject.getContactId());
            String newContactName = getUserNameById(updateReqVO.getContactId());
            changeContent.add(StrUtil.format("联系人由【{}】改为【{}】", oldContactName, newContactName));
        }

        // 1.6 对比基础字段 - 状态（ID转名称）
        if (updateReqVO.getStatusId() != null && !Objects.equals(oldSubject.getStatusId(), updateReqVO.getStatusId())) {
            String oldStatusName = getStatusNameById(oldSubject.getStatusId());
            String newStatusName = getStatusNameById(updateReqVO.getStatusId());
            changeContent.add(StrUtil.format("状态由【{}】改为【{}】", oldStatusName, newStatusName));
        }

//        // 1.7 对比基础字段 - 联系电话（扩展字段1）
//        if (updateReqVO.getExtCommon1() != null && !Objects.equals(oldSubject.getExtCommon1(), updateReqVO.getExtCommon1())) {
//            changeContent.add(StrUtil.format("联系电话由【{}】改为【{}】", oldSubject.getExtCommon1(), updateReqVO.getExtCommon1()));
//        }

        // 1.8 对比成员列表（核心：计算新增/移除的成员）
        // 1.8.1 查询旧的成员列表（未被逻辑删除的）
        List<String> oldMemberIds = subjectMemberMapper.selectList(
                new LambdaQueryWrapperX<SubjectMemberDO>()
                        .eq(SubjectMemberDO::getSubjectId, bizSubjectId)
                        .eq(SubjectMemberDO::getDeleted, 0) // 只查未删除的旧成员
        ).stream().map(SubjectMemberDO::getUserId).collect(Collectors.toList());
        // 1.8.2 获取新的成员列表
        List<String> newMemberIds = updateReqVO.getMemberIds() == null ? new ArrayList<>() : updateReqVO.getMemberIds();

        // 1.8.3 计算差集：移除的成员 & 新增的成员
        Collection<String> removedMemberIds = CollUtil.subtract(oldMemberIds, newMemberIds);
        Collection<String> addedMemberIds = CollUtil.subtract(newMemberIds, oldMemberIds);

        // 1.8.4 拼接成员变更日志（ID转姓名，提升可读性）
        if (CollUtil.isNotEmpty(removedMemberIds)) {
            String removedNames = getUserNamesByIds(removedMemberIds);
            changeContent.add(StrUtil.format("移除成员：【{}】", removedNames));
        }
        if (CollUtil.isNotEmpty(addedMemberIds)) {
            String addedNames = getUserNamesByIds(addedMemberIds);
            changeContent.add(StrUtil.format("新增成员：【{}】", addedNames));
        }

        // 1.9 边界处理：无任何字段变更，直接返回（避免无效更新）
        if (changeContent.length() == 0) {
            log.info("评价主体{}无任何字段变更，无需更新日志和数据", bizSubjectId);
            return;
        }

        // 1.10 调用工具类追加日志（不覆盖原有历史日志）
        String newChangeLog = ChangeLogUtils.appendLog(oldSubject.getChangeLog(), "【编辑】", changeContent.toString());
        // ====================== 新增：变更日志核心逻辑 end ======================

        // 2. 构建更新对象 & 设置新的变更日志
        SubjectDO updateObj = BeanUtils.toBean(updateReqVO, SubjectDO.class);
        updateObj.setChangeLog(newChangeLog); // 关键：把拼接好的日志设置到更新对象中
        subjectMapper.updateById(updateObj);
        log.info("评价主体{}基础信息更新完成，变更日志已追加", bizSubjectId);


        // 3. 处理成员列表（核心：先删旧的，再加新的）
        //List<String> newMemberIds = updateReqVO.getMemberIds();
        // 3.1 删除该主体下所有旧成员（逻辑删除）
        LambdaUpdateWrapper<SubjectMemberDO> deleteWrapper = new LambdaUpdateWrapper<>();
        deleteWrapper.eq(SubjectMemberDO::getSubjectId, bizSubjectId)
                .set(SubjectMemberDO::getDeleted, 1); // 逻辑删除：deleted=1
        subjectMemberMapper.update(null, deleteWrapper);
        log.info("删除主体{}的旧成员完成", bizSubjectId);

        // 3.2 批量插入新成员（如果有新列表）
        if (CollUtil.isNotEmpty(newMemberIds)) {
            List<SubjectMemberDO> memberDOList = newMemberIds.stream()
                    .map(userId -> {
                        SubjectMemberDO memberDO = new SubjectMemberDO();
                        memberDO.setMemberId(UUID.randomUUID().toString());
                        memberDO.setSubjectId(bizSubjectId);
                        memberDO.setUserId(userId);
                        memberDO.setStatusId(Integer.valueOf(updateReqVO.getStatusId()));
                        //memberDO.setDeleted(0); // 必须设，避免非空约束
                        memberDO.setJoinTime(LocalDateTime.now());
                        // createTime/updateTime由Yudao自动填充，不用手动设
                        return memberDO;
                    })
                    .collect(Collectors.toList());

            // 批量插入新成员
            subjectMemberMapper.insertBatch(memberDOList);
            log.info("批量插入主体{}的新成员：共{}条", bizSubjectId, newMemberIds.size());
        } else {
            log.warn("主体{}的新成员列表为空，跳过插入", bizSubjectId);
        }

        // 4. 同步更新主表的成员数
        updateSubjectMemberCount(bizSubjectId);
    }
    // ====================== 辅助工具方法：ID转名称，提升日志可读性 ======================
    /**
     * 根据主体类型ID获取类型名称
     */
    private String getSubjectTypeNameById(String typeId) {
        if (StrUtil.isEmpty(typeId)) return "无";
        try {
            SubjectTypeDO typeDO = subjectTypeMapper.selectOne(
                    new LambdaQueryWrapperX<SubjectTypeDO>()
                            .eq(SubjectTypeDO::getTypeId, typeId)
            );
            return typeDO != null ? typeDO.getName() : typeId;
        } catch (Exception e) {
            log.error("获取主体类型名称失败，typeId:{}", typeId, e);
            return typeId;
        }
    }

    /**
     * 根据用户ID获取用户昵称/姓名
     */
    private String getUserNameById(String userId) {
        if (StrUtil.isEmpty(userId)) return "无";
        try {
            UserDO userDO = userMapper.selectById(Long.valueOf(userId));
            return userDO != null ? userDO.getUserName() : userId;
        } catch (Exception e) {
            log.error("获取用户名称失败，userId:{}", userId, e);
            return userId;
        }
    }

    /**
     * 批量根据String类型的userId（sys_user非主键）获取用户名称，用于成员列表日志
     * 适配：sys_user.userId = String类型、非主键
     */
    private String getUserNamesByIds(Collection<String> userIds) {
        // 1. 空值过滤：如果用户ID集合为空，直接返回空字符串
        if (CollUtil.isEmpty(userIds)) {
            return "";
        }
        try {
            // 2. 过滤空的/空白的ID，避免SQL中in('')的无效查询
            List<String> validUserIdList = userIds.stream()
                    .filter(StrUtil::isNotBlank) // 剔除null、""、"   "等无效ID
                    .collect(Collectors.toList());
            // 过滤后仍为空，直接返回
            if (CollUtil.isEmpty(validUserIdList)) {
                return "";
            }

            // 3. 核心：替换弃用方法，根据String类型userId（非主键）批量查询
            // 重点：in(AdminUserDO::getUserId, validUserIdList) 👉 字段为String类型，直接传String集合
            List<UserDO> userList = userMapper.selectList(
                    new LambdaQueryWrapperX<UserDO>()
                            .in(UserDO::getUserId, validUserIdList) // 精准匹配sys_user的String类型userId
            );

            // 4. 拼接用户名称（优先用nickname，无则用userId兜底）
            return userList.stream()
                    .filter(Objects::nonNull) // 过滤查询结果中的空对象
                    .map(user -> StrUtil.isNotBlank(user.getUserName()) ? user.getUserName() : user.getUserId())
                    .collect(Collectors.joining("、"));

        } catch (Exception e) {
            // 5. 异常兜底：查询失败时直接返回原始ID拼接，不影响主业务流程
            log.error("批量获取sys_user用户名称失败，userIds:{}", userIds, e);
            return userIds.stream()
                    .filter(StrUtil::isNotBlank)
                    .collect(Collectors.joining("、"));
        }
    }

    /**
     * 根据状态ID获取状态名称
     */
    private String getStatusNameById(String statusId) {
        if (statusId == null) return "无";
        try {
            StatusDO statusDO = statusMapper.selectOne(
                    new LambdaQueryWrapperX<StatusDO>()
                            .eq(StatusDO::getStatusId, statusId)
            );
            return statusDO != null ? statusDO.getName() : statusId;
        } catch (Exception e) {
            log.error("获取状态名称失败，statusId:{}", statusId, e);
            return statusId;
        }
    }
    @Override
    public void deleteSubject(Long id) {
        // 校验存在
        validateSubjectExists(id);
        // 删除
        subjectMapper.deleteById(id);
    }

    @Override
        public void deleteSubjectListByIds(List<Long> ids) {
        // 删除
        subjectMapper.deleteByIds(ids);
        }


    private void validateSubjectExists(Long id) {
        if (subjectMapper.selectById(id) == null) {
            throw exception(SUBJECT_NOT_EXISTS);
        }
    }

    @Override
    public SubjectDO getSubject(Long id) {
        return subjectMapper.selectById(id);
    }

    @Override
    public PageResult<SubjectDO> getSubjectPage(SubjectPageReqVO pageReqVO) {
        return subjectMapper.selectPage(pageReqVO);
    }
    // -------------------------- 新增联表查询 Service 方法 --------------------------
//    /**
//     * 联表分页查询（列表页展示用，含关联表名称字段）
//     * @param pageReqVO 分页+筛选参数
//     * @return 含联表字段的分页结果
//     */
//    @Override
//    public PageResult<SubjectRespVO> getSubjectPageWithJoin(SubjectPageReqVO pageReqVO) {
//        // 复用原有筛选条件逻辑
//        LambdaQueryWrapperX<SubjectDO> queryWrapper = new LambdaQueryWrapperX<SubjectDO>()
//                .eqIfPresent(SubjectDO::getSubjectId, pageReqVO.getSubjectId())
//                .likeIfPresent(SubjectDO::getName, pageReqVO.getName()) // 支持模糊查询
//                .eqIfPresent(SubjectDO::getCode, pageReqVO.getCode())
//                .eqIfPresent(SubjectDO::getSubjectTypeId, pageReqVO.getSubjectTypeId())
//                .eqIfPresent(SubjectDO::getContactId, pageReqVO.getContactId())
//                .eqIfPresent(SubjectDO::getStatusId, pageReqVO.getStatusId())
//                .betweenIfPresent(SubjectDO::getBizCreateTime, pageReqVO.getBizCreateTime())
//                .betweenIfPresent(SubjectDO::getCreateTime, pageReqVO.getCreateTime());
//
//        // 执行联表分页查询
//        Page<SubjectRespVO> page = subjectMapper.selectSubjectPageWithJoin(
//                Page.of(pageReqVO.getPageNo(), pageReqVO.getPageSize()), queryWrapper);
//        return new PageResult<>(page.getRecords(), page.getTotal());
//    }
//
//    /**
//     * 联表查询主体详情（含成员列表）
//     * @param detailReqVO 主体ID参数
//     * @return 完整详情（人工主体含成员列表）
//     */
//    @Override
//    public SubjectRespVO getSubjectDetailWithJoin(SubjectDetailReqVO detailReqVO) {
//        // 1. 查询主体基础联表信息
//        SubjectRespVO subjectDetail = subjectMapper.selectSubjectDetailWithJoin(detailReqVO.getSubjectId());
//        if (subjectDetail == null) {
//            throw exception(SUBJECT_NOT_EXISTS);
//        }
//
//        // 2. 若为人工主体，查询成员列表（通过主体类型名称判断，或查询sys_subject_type确认）
//        if ("人工主体".equals(subjectDetail.getSubjectTypeName())) {
//            List<SubjectRespVO.SubjectMemberRespVO> memberList = subjectMapper.selectSubjectMemberList(detailReqVO.getSubjectId());
//            subjectDetail.setMemberList(memberList);
//        }
//
//        return subjectDetail;
//    }
//
//    /**
//     * 查询评价主体统计指标
//     * @return 统计结果（总数量、人工/系统主体数、启用数）
//     */
//    @Override
//    public SubjectStatRespVO getSubjectStat() {
//        return subjectMapper.selectSubjectStat();
//    }
    @Override
    public PageResult<SubjectRespVO> getSubjectJoinPage(SubjectPageReqVO reqVO) {
        // 1. 调用你贴的那段 Mapper 代码，查主体分页
        PageResult<SubjectRespVO> pageResult = subjectMapper.selectSubjectJoinPage(reqVO);

        // 2. 如果没数据，直接返回
        if (CollUtil.isEmpty(pageResult.getList())) {
            return pageResult;
        }

        // ========== 【核心逻辑】根据 eval_subject.creator/updater 查 system_users ==========
        // 3. 提取当前页所有的 creator （去重，避免重复查询）
        Set<Long> allUserIds = new HashSet<>();
        pageResult.getList().forEach(vo -> {
            // 3.1 安全提取创建人ID（三重校验：非空、是数字、是纯整数）
            // 注意：这里假设你的VO里creator是String类型，如果是Long类型，直接判空即可
            String creatorStr = String.valueOf(vo.getCreator()); // 统一转String处理
            if (StrUtil.isNotBlank(creatorStr)
                    && NumberUtil.isNumber(creatorStr)
                    ) {
                allUserIds.add(Long.valueOf(creatorStr));
            }


        });

        // 4. 【关键】调用 AdminUserApi，批量查 system_users 表
        // 这里会远程调用 system 服务，执行 SQL：SELECT * FROM system_users WHERE id IN (1, 2, ...) AND deleted=0
        Map<Long, AdminUserRespDTO> userMap = CollUtil.isNotEmpty(allUserIds)
                ? adminUserApi.getUserMap(allUserIds)
                : new HashMap<>();

        // 5. 提取当前页所有的主体ID
        List<String> subjectIds = pageResult.getList().stream()
                .map(SubjectRespVO::getSubjectId)
                .collect(Collectors.toList());

        // 6. 批量查询这些主体的所有成员
        List<SubjectRespVO.SubjectMemberListRespVO> allMembers =
                subjectMemberMapper.selectMembersBySubjectIds(subjectIds);

        // 7. 按主体ID分组
        Map<String, List<SubjectRespVO.SubjectMemberListRespVO>> memberMap = new HashMap<>();
        if (CollUtil.isNotEmpty(allMembers)) {
            memberMap = allMembers.stream()
                    .collect(Collectors.groupingBy(SubjectRespVO.SubjectMemberListRespVO::getSubjectId));
        }

        // 8. 回填数据
        Map<String, List<SubjectRespVO.SubjectMemberListRespVO>> finalMemberMap = memberMap;
        // ========== 【核心修正】一次性初始化userMap，保证有效final ==========

        pageResult.getList().forEach(vo -> {
            // 6.1 回填成员列表 (这里可以加判断：只有人工主体才填)
             if ("人工主体".equals(vo.getSubjectTypeName())) {
            vo.setMemberList(finalMemberMap.get(vo.getSubjectId()));
             }
            // 6.2 回填创建人姓名
            String creatorStr = String.valueOf(vo.getCreator());
            if (StrUtil.isNotBlank(creatorStr)
                    && NumberUtil.isNumber(creatorStr)
                    ) {
                AdminUserRespDTO creatorUser = userMap.get(Long.valueOf(creatorStr));
                if (creatorUser != null) {
                    vo.setCreateUserName(creatorUser.getNickname());
                }
            }
            // 6.3 处理变更日志截取 (从 Mapper 移到这里)
            if (StrUtil.isNotBlank(vo.getChangeLog())) {
                vo.setChangeLogShort(StrUtil.sub(vo.getChangeLog(), 0, 50));
            }
        });

        return pageResult;
    }

        //


    // 辅助判断方法
//    private boolean isManualSubject(SubjectRespVO vo) {
//        // 根据你的业务判断，比如通过 subjectTypeName 或者 subjectTypeId
//        // 示例：return "人工主体".equals(vo.getSubjectTypeName());
//        return true; // 这里先默认都返回，你自己加判断
//    }



    /**
     * 公共方法：构造基础查询条件（过滤deleted=1）
     * @return LambdaQueryWrapper<SubjectDO>
     */
    private LambdaQueryWrapper<SubjectDO> getBaseWrapper() {
        // 固定过滤deleted=1的记录，所有统计都基于这个基础条件
        return new LambdaQueryWrapper<SubjectDO>()
                .ne(SubjectDO::getDeleted, 1);
    }
    @Override
    public SubjectRespVO getStatusCount(Integer statusId) {
        SubjectRespVO respVO = new SubjectRespVO();

        // 1. 构造基础条件 + 动态拼接statusId
        LambdaQueryWrapper<SubjectDO> baseWrapper = getBaseWrapper();
        if (statusId != null) {
            baseWrapper.eq(SubjectDO::getStatusId, statusId);
        }

        // 2. 统计总数量（核心修改：count → selectCount）
        Long totalCount = subjectMapper.selectCount(baseWrapper);
        respVO.setTotalCount(totalCount);

        // 3. 统计status_id=1的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<SubjectDO> status1Wrapper = baseWrapper.clone();
        status1Wrapper.eq(SubjectDO::getStatusId, 1);
        respVO.setStatus1Count(subjectMapper.selectCount(status1Wrapper));

        // 4. 统计status_id=2的数量（核心修改：count → selectCount）
        LambdaQueryWrapper<SubjectDO> status2Wrapper = baseWrapper.clone();
        status2Wrapper.eq(SubjectDO::getStatusId, 2);
        respVO.setStatus2Count(subjectMapper.selectCount(status2Wrapper));

        return respVO;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public int importSubjects(InputStream excelInputStream) {
        // ===== 1. 读取Excel原始单元格数据（适配你的表头结构）=====
        List<Map<Integer, String>> rawRows;
        try  {
            rawRows = EasyExcel.read(excelInputStream)
                    .headRowNumber(1)   // 第一行为表头，跳过不解析
                    .sheet()
                    .doReadSync();
        }catch (Exception e){
            log.error("读取Excel文件失败", e);
            throw new ServiceException(400, "Excel文件解析失败：" + e.getMessage());
        }
        log.info("评价主体导入-原始数据行数：{}", rawRows.size());

        // ===== 2. 构建名称->ID/编码的映射关系（核心：Excel名称转数据库ID）=====
        // 2.1 系统用户：姓名 -> 用户ID（用于联系人、成员列表）
        List<SelectOptionRespVO> userOptions = userService.getUserSimpleList();
        Map<String, String> userName2IdMap = userOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> option.getValue().toString(), (oldVal, newVal) -> oldVal));

        // 2.2 主体类型：类型名称 -> 类型ID
        List<SelectOptionRespVO> typeOptions = subjectTypeService.getSubjectTypeSimpleList();
        Map<String, String> typeName2IdMap = typeOptions.stream()
                .collect(Collectors.toMap(SelectOptionRespVO::getLabel,
                        option -> String.valueOf(option.getValue()), (oldVal, newVal) -> oldVal));

        // ===== 3. 逐行解析Excel，构建合法的导入VO =====
        List<SubjectSaveReqVO> validVOList = new ArrayList<>();
        for (int i = 0; i < rawRows.size(); i++) {
            Map<Integer, String> row = rawRows.get(i);
            int rowNum = i + 2; // Excel行号：数据从第2行开始，报错时精准定位

            // 【适配你的Excel表头】按列索引获取单元格数据
            String subjectName = row.get(0);    // A列：主体名称
            String subjectCode = row.get(1);    // B列：主体编码
            String subjectTypeName = row.get(2); // C列：主体类型
            String contactName = row.get(3);    // D列：联系人
            String contactPhone = row.get(4);   // E列：联系电话
            String memberCountStr = row.get(5); // F列：成员数量
            String memberNameList = row.get(6); // G列：成员列表（顿号分隔，如：王五、赵六）
            String statusId = row.get(7);       // H列：状态ID

            // 过滤空行：主体名称/编码为空直接跳过
            if (org.apache.commons.lang3.StringUtils.isBlank(subjectName)
                    || org.apache.commons.lang3.StringUtils.isBlank(subjectCode)) {
                log.warn("评价主体导入-第{}行：主体名称/编码为空，跳过该行", rowNum);
                continue;
            }

            // 打印调试日志，排查问题
            log.info("评价主体导入-第{}行原始数据：名称={}, 编码={}, 类型={}, 联系人={}",
                    rowNum, subjectName, subjectCode, subjectTypeName, contactName);

            try {
                // 3.1 联系人姓名 -> 联系人ID
                String contactId = userName2IdMap.get(contactName);
                if (contactId == null) {
                    throw new ServiceException(400, "未找到【启用状态】的联系人：" + contactName);
                }

                // 3.2 主体类型名称 -> 类型ID
                String subjectTypeId = typeName2IdMap.get(subjectTypeName);
                if (subjectTypeId == null) {
                    throw new ServiceException(400, "未找到【启用状态】的主体类型：" + subjectTypeName);
                }

                // 3.3 成员列表：姓名拆分 -> 用户ID列表（核心处理）
                List<String> memberIds = new ArrayList<>();
                if (org.apache.commons.lang3.StringUtils.isNotBlank(memberNameList)) {
                    // 兼容中文顿号、英文逗号、空格分隔
                    String[] nameArray = memberNameList.split("[、,，\\s]+");
                    for (String userName : nameArray) {
                        if (org.apache.commons.lang3.StringUtils.isBlank(userName)) continue;

                        String userId = userName2IdMap.get(userName.trim());
                        if (userId == null) {
                            throw new ServiceException(400, "成员【" + userName + "】未找到启用状态的用户");
                        }
                        memberIds.add(userId);
                    }
                }

                // 3.4 成员数量校验：Excel填写的数量和实际成员列表不一致时提示
                if (org.apache.commons.lang3.StringUtils.isNotBlank(memberCountStr)) {
                    int excelMemberCount = Integer.parseInt(memberCountStr.trim());
                    if (excelMemberCount != memberIds.size()) {
                        throw new ServiceException(400,
                                StrUtil.format("成员数量不匹配，Excel填写{}人，实际解析到{}人", excelMemberCount, memberIds.size()));
                    }
                }

                // 3.5 手动构建导入VO
                SubjectSaveReqVO importVO = new SubjectSaveReqVO();
                // 基础信息
                importVO.setName(subjectName.trim());
                importVO.setCode(subjectCode.trim());
                importVO.setSubjectTypeId(subjectTypeId);
                importVO.setContactId(contactId);
                importVO.setContactPhone(contactPhone); // 联系电话存入扩展字段1
                importVO.setStatusId(statusId);
                // 成员信息
                importVO.setMemberIds(memberIds);
                importVO.setMemberCount(memberIds.size()); // 以实际解析的成员数量为准
                // 导入标记（用于日志区分手动新增/批量导入）
                //importVO.setExtCommon2("批量导入");

                validVOList.add(importVO);

            } catch (Exception e) {
                // 行级错误提示，精准定位Excel哪一行出问题
                throw new ServiceException(400, "第" + rowNum + "行导入失败：" + e.getMessage());
            }
        }

//        // 无有效数据直接返回
//        if (validVOList.isEmpty()) {
//            return CommonResult.success(0);
//        }

        // ===== 4. 调用Service执行批量导入 =====
        if (CollUtil.isEmpty(validVOList)) {
            log.info("评价主体导入-无有效数据，直接返回");
            return 0;
        }

        // 循环调用新增方法，保证每条数据都有独立的事务、日志、成员处理
        for (SubjectSaveReqVO importVO : validVOList) {
            try {
                createSubject(importVO);
            } catch (ServiceException e) {
                log.error("评价主体导入失败，主体名称：{}", importVO.getName(), e);
                throw e; // 抛出异常，整体回滚
            }
        }
        log.info("评价主体批量导入完成，共导入{}条数据", validVOList.size());
        return validVOList.size();
    }

    @Override
    public EvalSubjectOverviewVO getEvalSubjectOverview() {
        EvalSubjectOverviewVO vo = new EvalSubjectOverviewVO();

        // 1. 组装卡片核心数据
        EvalSubjectOverviewVO.CardData cardData = subjectMapper.selectCardCoreData();
        vo.setCardData(cardData);

        // 2. 组装圆环图数据
        List<EvalSubjectOverviewVO.PieChartItem> typePieChart = subjectMapper.selectTypePieChart();
        vo.setTypePieChart(typePieChart);

        List<EvalSubjectOverviewVO.PieChartItem> statusPieChart = subjectMapper.selectStatusPieChart();
        vo.setStatusPieChart(statusPieChart);

        // 3. 组装柱状图数据
        List<EvalSubjectOverviewVO.BarChartItem> memberCountBarChart = subjectMapper.selectMemberCountBarChart();
        vo.setMemberCountBarChart(memberCountBarChart);

        return vo;
    }
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateSubjectMemberCount(String subjectId) {
        // 1. 统计该主体的有效成员数
        Integer memberCount = subjectMemberMapper.countValidMemberBySubjectId(subjectId);
        // 空值处理：没有成员则设为0
        memberCount = memberCount == null ? 0 : memberCount;
        log.info("统计主体{}的有效成员数：{}", subjectId, memberCount);

        // 2. 构造更新条件和字段（直接按subjectId更新）
        LambdaUpdateWrapper<SubjectDO> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.eq(SubjectDO::getSubjectId, subjectId) // 条件：subjectId匹配
                .set(SubjectDO::getMemberCount, memberCount); // 更新member_count字段

        // 3. 执行更新
        int updateCount = subjectMapper.update(null, updateWrapper);
        if (updateCount == 0) {
            log.warn("更新主体{}的成员数失败：未找到该主体", subjectId);
        } else {
            log.info("更新主体{}的成员数成功：{}", subjectId, memberCount);
        }
    }
}