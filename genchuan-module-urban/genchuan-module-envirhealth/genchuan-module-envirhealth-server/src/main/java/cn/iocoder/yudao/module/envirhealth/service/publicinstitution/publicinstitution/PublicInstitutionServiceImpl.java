package cn.iocoder.yudao.module.envirhealth.service.publicinstitution.publicinstitution;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.common.util.object.BeanUtils;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionDashboardRespVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionPageReqVO;
import cn.iocoder.yudao.module.envirhealth.controller.admin.publicinstitution.vo.publicinstitution.PublicInstitutionSaveReqVO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.publicinstitution.PublicInstitutionDetailDO;
import cn.iocoder.yudao.module.envirhealth.dal.dataobject.user.UserDO;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.publicinstitution.PublicInstitutionMapper;
import cn.iocoder.yudao.module.envirhealth.dal.mysql.user.UserMapper;
import cn.iocoder.yudao.module.envirhealth.framework.util.codegenerator.publicinstitution.PublicInstitutionCodeGenerator;
import com.alibaba.fastjson.JSON;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.*;
import java.util.stream.Collectors;

import static cn.iocoder.yudao.framework.common.exception.util.ServiceExceptionUtil.exception;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_INSTITUTION_NAME_DUPLICATE;
import static cn.iocoder.yudao.module.envirhealth.enums.ErrorCodeConstants.PUBLIC_INSTITUTION_NOT_EXISTS;

/**
 * 公共机构 Service 实现类
 *
 * @author 芋道源码
 */
@Service
@Validated
public class PublicInstitutionServiceImpl implements PublicInstitutionService {

    @Resource
    private PublicInstitutionMapper publicInstitutionMapper;

    @Resource
    private UserMapper userMapper;

    @Resource
    private PublicInstitutionCodeGenerator codeGenerator;

    @Override
    public Long createPublicInstitution(PublicInstitutionSaveReqVO createReqVO) {
        //检验重复名称
        checkNameUnique(createReqVO.getName(), null);

        //CleanerIds空值处理
        if (createReqVO.getCleanerIds() == null || createReqVO.getCleanerIds().trim().isEmpty()) {
            createReqVO.setCleanerIds("[]");
        }

        // 插入
        PublicInstitutionDO publicInstitution = BeanUtils.toBean(createReqVO, PublicInstitutionDO.class);

        publicInstitution.setInstitutionId(codeGenerator.generateInstitutionId());

        publicInstitutionMapper.insert(publicInstitution);
        // 返回
        return publicInstitution.getId();
    }

    @Override
    public void updatePublicInstitution(PublicInstitutionSaveReqVO updateReqVO) {
        // 校验存在
        validatePublicInstitutionExists(updateReqVO.getId());
        //CleanerIds空值处理
        if (updateReqVO.getCleanerIds() == null || updateReqVO.getCleanerIds().trim().isEmpty()) {
            updateReqVO.setCleanerIds("[]");
        }
        //检验重复名称
        checkNameUnique(updateReqVO.getName(), updateReqVO.getId());
        // 更新
        PublicInstitutionDO updateObj = BeanUtils.toBean(updateReqVO, PublicInstitutionDO.class);
        publicInstitutionMapper.updateById(updateObj);
    }

    private void checkNameUnique(String name, Long id) {
        if (name == null || name.trim().isEmpty()) {
            return;
        }

        LambdaQueryWrapperX<PublicInstitutionDO> query = new LambdaQueryWrapperX<PublicInstitutionDO>()
                .eq(PublicInstitutionDO::getName, name)
                .eq(PublicInstitutionDO::getDeleted, false);

        if (id != null) {
            query.ne(PublicInstitutionDO::getId, id);
        }

        PublicInstitutionDO existing = publicInstitutionMapper.selectOne(query);
        if (existing != null) {
            throw exception(PUBLIC_INSTITUTION_NAME_DUPLICATE);
        }
    }

    @Override
    public void deletePublicInstitution(Long id) {
        // 校验存在
        validatePublicInstitutionExists(id);
        // 删除
        publicInstitutionMapper.deleteById(id);
    }

    private void validatePublicInstitutionExists(Long id) {
        if (publicInstitutionMapper.selectById(id) == null) {
            throw exception(PUBLIC_INSTITUTION_NOT_EXISTS);
        }
    }

    @Override
    public PublicInstitutionDO getPublicInstitution(Long id) {
        return publicInstitutionMapper.selectById(id);
    }

    @Override
    public PageResult<PublicInstitutionDO> getPublicInstitutionPage(PublicInstitutionPageReqVO pageReqVO) {
        return publicInstitutionMapper.selectPage(pageReqVO);
    }

    @Override
    public PageResult<PublicInstitutionDetailDO> getPublicInstitutionDetailPage(PublicInstitutionPageReqVO pageReqVO) {
        // 1. 查询总数
        Long total = publicInstitutionMapper.selectCount(pageReqVO);
        if (total == 0) {
            return PageResult.empty();
        }

        // 2. 分页查询列表
        pageReqVO.setOffset(pageReqVO.getPageNo(), pageReqVO.getPageSize());
        List<PublicInstitutionDetailDO> list = publicInstitutionMapper.selectDetailPage(pageReqVO);

        // ====================== 核心处理：JSON保洁员ID → 姓名 ======================
        // 3. 提取所有不重复的保洁员ID
        Set<String> allCleanerIds = new HashSet<>();
        for (PublicInstitutionDetailDO detail : list) {
            String cleanerIdsJson = detail.getCleanerIds();
            if (StrUtil.isBlank(cleanerIdsJson)) {
                continue;
            }
            try {
                List<String> ids = JSON.parseArray(cleanerIdsJson, String.class);
                allCleanerIds.addAll(ids);
            } catch (Exception ignored) {}
        }

        // 4. 一次性查询所有用户，生成 ID -> 姓名 Map
        Map<String, String> cleanerNameMap = new HashMap<>();
        if (CollUtil.isNotEmpty(allCleanerIds)) {
            // 根据ID批量查询用户
            List<UserDO> userList = userMapper.selectList(
                    new LambdaQueryWrapperX<UserDO>()
                            .in(UserDO::getUserId, allCleanerIds)
                            .eq(UserDO::getDeleted, 0)
            );
            // 封装成Map
            for (UserDO user : userList) {
                cleanerNameMap.put(user.getUserId(), user.getUserName());
            }
        }

        // 5. 遍历列表，把JSON ID 转成 姓名拼接字符串
        for (PublicInstitutionDetailDO detail : list) {
            String cleanerIdsJson = detail.getCleanerIds();
            if (StrUtil.isBlank(cleanerIdsJson)) {
                detail.setCleanersNameStr("");
                continue;
            }

            try {
                List<String> cleanerIds = JSON.parseArray(cleanerIdsJson, String.class);
                // ID 转名称，用逗号拼接
                String nameStr = cleanerIds.stream()
                        .map(id -> cleanerNameMap.getOrDefault(id, ""))
                        .filter(StrUtil::isNotBlank)
                        .collect(Collectors.joining(","));
                detail.setCleanersNameStr(nameStr);
            } catch (Exception e) {
                detail.setCleanersNameStr("");
            }
        }
        // ======================================================================

        return new PageResult<>(list, total);
    }

    @Override
    public PublicInstitutionDashboardRespVO getPublicInstitutionDashboard() {
        PublicInstitutionDashboardRespVO resp = new PublicInstitutionDashboardRespVO();

        // 1. 卡片数据
        resp.setTotalInstitutions(publicInstitutionMapper.selectTotalInstitutions());
        resp.setCleaningStandardMetCount(publicInstitutionMapper.selectCleaningStandardMetCount());
        resp.setProblemClosedCount(publicInstitutionMapper.selectProblemClosedCount());
        resp.setInspectionPassCount(publicInstitutionMapper.selectInspectionPassCount());

        // 2. 圆环图数据 - 机构类型分布
        resp.setInstitutionTypeDistribution(publicInstitutionMapper.selectInstitutionTypePie());

        // 3. 圆环图数据 - 区域分布
        resp.setAreaDistribution(publicInstitutionMapper.selectAreaPie());

        // 4. 柱状图数据 - 不同类型机构保洁达标率对比
        resp.setCleaningRateByType(publicInstitutionMapper.selectCleaningRateByTypeBar());

        return resp;
    }
}