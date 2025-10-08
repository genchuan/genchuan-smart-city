package cn.iocoder.yudao.module.datacenter.dal.mysql.businessguidance.localregulation;

import cn.iocoder.yudao.framework.common.pojo.PageParam;
import cn.iocoder.yudao.framework.common.pojo.PageResult;
import cn.iocoder.yudao.framework.mybatis.core.mapper.BaseMapperX;
import cn.iocoder.yudao.framework.mybatis.core.query.LambdaQueryWrapperX;
import cn.iocoder.yudao.module.datacenter.dal.dataobject.businessguidance.localregulation.LocalRegulationInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDate;
import java.util.List;

/**
 * 地方法规信息 Mapper
 *
 * @author Gyh
 */
@Mapper
public interface LocalRegulationInfoMapper extends BaseMapperX<LocalRegulationInfoDO> {

    /**
     * 根据法规ID查询
     */
    default LocalRegulationInfoDO selectByRegulationId(String regulationId) {
        return selectOne("regulation_id", regulationId);
    }

    /**
     * 根据法规编号查询
     */
    default LocalRegulationInfoDO selectByRegulationNo(String regulationNo) {
        return selectOne("regulation_no", regulationNo);
    }

    /**
     * 根据行政区划代码查询
     */
    default List<LocalRegulationInfoDO> selectByAdminCode(String adminCode) {
        return selectList("admin_code", adminCode);
    }

    /**
     * 根据法规类型查询
     */
    default List<LocalRegulationInfoDO> selectByRegulationType(String regulationType) {
        return selectList("regulation_type", regulationType);
    }

    /**
     * 根据法规状态查询
     */
    default List<LocalRegulationInfoDO> selectByRegulationStatus(String regulationStatus) {
        return selectList("regulation_status", regulationStatus);
    }

    /**
     * 根据关联的国家政策ID查询
     */
    default List<LocalRegulationInfoDO> selectByRelatedNationalPolicyId(String relatedNationalPolicyId) {
        return selectList("related_national_policy_id", relatedNationalPolicyId);
    }

    /**
     * 分页查询地方法规信息
     */
    default PageResult<LocalRegulationInfoDO> selectPage(String regulationName, String regulationNo,
                                                         String regulationType, String regulationStatus,
                                                         String adminCode, String adminName,
                                                         String issueDept, PageParam pageParam) {
        return selectPage(pageParam, new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .likeIfPresent(LocalRegulationInfoDO::getRegulationName, regulationName)
                .likeIfPresent(LocalRegulationInfoDO::getRegulationNo, regulationNo)
                .eqIfPresent(LocalRegulationInfoDO::getRegulationType, regulationType)
                .eqIfPresent(LocalRegulationInfoDO::getRegulationStatus, regulationStatus)
                .eqIfPresent(LocalRegulationInfoDO::getAdminCode, adminCode)
                .likeIfPresent(LocalRegulationInfoDO::getAdminName, adminName)
                .likeIfPresent(LocalRegulationInfoDO::getIssueDept, issueDept)
                .orderByDesc(LocalRegulationInfoDO::getIssueTime));
    }

    /**
     * 查询即将失效的法规（距失效时间≤30天）
     */
    default List<LocalRegulationInfoDO> selectExpiringRegulations() {
        return selectList(new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .isNotNull(LocalRegulationInfoDO::getExpireTime)
                .le(LocalRegulationInfoDO::getExpireTime, LocalDate.now().plusDays(30))
                .eq(LocalRegulationInfoDO::getRegulationStatus, "有效")
                .orderByAsc(LocalRegulationInfoDO::getExpireTime));
    }

    /**
     * 查询需要更新状态的法规
     */
    default List<LocalRegulationInfoDO> selectRegulationsNeedStatusUpdate() {
        LocalDate today = LocalDate.now();
        return selectList(new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .and(wrapper -> wrapper
                        .and(sub -> sub
                                .eq(LocalRegulationInfoDO::getRegulationStatus, "未生效")
                                .le(LocalRegulationInfoDO::getEffectiveTime, today))
                        .or(sub -> sub
                                .eq(LocalRegulationInfoDO::getRegulationStatus, "有效")
                                .le(LocalRegulationInfoDO::getExpireTime, today))
                ));
    }

    /**
     * 检查法规编号是否存在
     */
    default boolean existsByRegulationNo(String regulationNo) {
        return selectCount("regulation_no", regulationNo) > 0;
    }

    /**
     * 检查法规编号是否存在（排除自身）
     */
    default boolean existsByRegulationNoExcludeSelf(String regulationNo, Long excludeId) {
        return selectCount(new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .eq(LocalRegulationInfoDO::getRegulationNo, regulationNo)
                .ne(LocalRegulationInfoDO::getId, excludeId)) > 0;
    }

    /**
     * 查询同一法规的所有修订版本
     */
    default List<LocalRegulationInfoDO> selectRevisionVersions(String originalRegulationNo) {
        return selectList(new LambdaQueryWrapperX<LocalRegulationInfoDO>()
                .like(LocalRegulationInfoDO::getRegulationNo, originalRegulationNo)
                .orderByDesc(LocalRegulationInfoDO::getEffectiveTime));
    }
}