/*
 * Copyright 2014-2021 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.webank.webase.transaction.repository.mapper;

import com.webank.webase.transaction.frontinterface.entity.FrontGroup;
import com.webank.webase.transaction.frontinterface.entity.MapListParam;
import com.webank.webase.transaction.repository.bean.TbFrontGroupMap;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;

public interface TbFrontGroupMapMapper {

    @SelectProvider(type = TbFrontGroupMapSqlProvider.class, method = "countByParam")
    int countByParam(MapListParam param);

    @Select({
            "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id, "
                    + "b.front_ip as front_ip,b.front_port as front_port from tb_front_group_map a left join tb_front b "
                    + "on(a.front_id=b.front_id) left join tb_group c "
                    + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d "
                    + "on(a.chain_id=d.chain_id) where a.group_id = #{groupId} "})
    List<FrontGroup> selectByGroupId(@Param("groupId") int groupId);

    @Select({
            "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id,"
                    + "b.front_ip as front_ip,b.front_port as front_port from tb_front_group_map a left join tb_front b "
                    + "on(a.front_id=b.front_id) left join tb_group c "
                    + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d "
                    + "on(a.chain_id=d.chain_id) where a.group_id = #{groupId} and a.chain_id = #{chainId} "})
    List<FrontGroup> selectByChainIdAndGroupId(@Param("chainId") int chainId,
            @Param("groupId") int groupId);

    @Select({
            "select a.map_id as map_id, a.chain_id as chain_id, a.group_id as group_id, a.front_id as front_id,"
                    + "b.front_ip as front_ip,b.front_port as front_port from tb_front_group_map a left join tb_front b "
                    + "on(a.front_id=b.front_id) left join tb_group c "
                    + "on(a.chain_id=c.chain_id and a.group_id=c.group_id) left join tb_chain d "
                    + "on(a.chain_id=d.chain_id) where a.chain_id = #{chainId} "})
    List<FrontGroup> selectByChainId(@Param("chainId") int chainId);

    @Delete({"delete from tb_front_group_map where chain_id = #{chainId}"})
    int deleteByChainId(@Param("chainId") int chainId);

    @Delete({"delete from tb_front_group_map where front_id = #{frontId}"})
    int deleteByFrontId(@Param("frontId") int frontId);

    @Delete({
            "delete from tb_front_group_map  where chain_id = #{chainId} and group_id = #{groupId}"})
    int deleteByGroupId(@Param("chainId") int chainId, @Param("groupId") int groupId);

    @Select({
            "select * from tb_front_group_map where chain_id = #{chainId} and front_id = #{frontId} and group_id = #{groupId}"})
    TbFrontGroupMap selectByChainIdAndFrontIdAndGroupId(@Param("chainId") int chainId,
            @Param("frontId") int frontId, @Param("groupId") int groupId);

    @Select({
            "select * from tb_front_group_map where chain_id=#{chainId} and group_id=#{groupId} order by create_time desc"})
    List<TbFrontGroupMap> selectListByGroupId(@Param("chainId") int chainId,
            @Param("groupId") int groupId);


    // @Select({
    // "update tb_front_group_map set modify_time = now(),front_status=#{status} where
    // front_id=#{frontId} and chain_id=#{chainId}"
    // })
    // void updateAllGroupsStatus(@Param("chainId") int chainId,@Param("frontId") int
    // frontId,@Param("status") int status);
    //
    // @Select({
    // "update tb_front_group_map set modify_time = now(),front_status=#{status} where
    // front_id=#{frontId} and group_id=${groupId} and chain_id=#{chainId}"
    // })
    // void updateOneGroupStatus(@Param("chainId") int chainId, @Param("frontId") int
    // frontId,@Param("status") byte status,@Param("groupId") int groupId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * tb_front_group_map
     *
     * @mbg.generated
     */
    @Delete({"delete from tb_front_group_map", "where map_id = #{mapId,jdbcType=INTEGER}"})
    int deleteByPrimaryKey(Integer mapId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * tb_front_group_map
     *
     * @mbg.generated
     */
    @InsertProvider(type = TbFrontGroupMapSqlProvider.class, method = "insertSelective")
    @SelectKey(statement = "SELECT LAST_INSERT_ID()", keyProperty = "mapId", before = false,
            resultType = Integer.class)
    int insertSelective(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * tb_front_group_map
     *
     * @mbg.generated
     */
    @Select({"select",
            "map_id, chain_id, front_id, group_id, create_time, modify_time, front_status",
            "from tb_front_group_map", "where map_id = #{mapId,jdbcType=INTEGER}"})
    @Results({
            @Result(column = "map_id", property = "mapId", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "chain_id", property = "chainId", jdbcType = JdbcType.INTEGER),
            @Result(column = "front_id", property = "frontId", jdbcType = JdbcType.INTEGER),
            @Result(column = "group_id", property = "groupId", jdbcType = JdbcType.INTEGER),
            @Result(column = "create_time", property = "createTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "modify_time", property = "modifyTime", jdbcType = JdbcType.TIMESTAMP),
            @Result(column = "front_status", property = "frontStatus",
                    jdbcType = JdbcType.TINYINT)})
    TbFrontGroupMap selectByPrimaryKey(Integer mapId);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * tb_front_group_map
     *
     * @mbg.generated
     */
    @UpdateProvider(type = TbFrontGroupMapSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(TbFrontGroupMap record);

    /**
     * This method was generated by MyBatis Generator. This method corresponds to the database table
     * tb_front_group_map
     *
     * @mbg.generated
     */
    @Options(useGeneratedKeys = true, keyProperty = "mapId", keyColumn = "map_id")
    @Insert({"<script>", "insert into tb_front_group_map (chain_id, ", "front_id, group_id, ",
            "create_time, modify_time, ", "front_status)",
            "values<foreach collection=\"list\" item=\"detail\" index=\"index\" separator=\",\">(#{detail.chainId,jdbcType=INTEGER}, ",
            "#{detail.frontId,jdbcType=INTEGER}, #{detail.groupId,jdbcType=INTEGER}, ",
            "#{detail.createTime,jdbcType=TIMESTAMP}, #{detail.modifyTime,jdbcType=TIMESTAMP}, ",
            "#{detail.frontStatus,jdbcType=TINYINT})</foreach></script>"})
    int batchInsert(java.util.List<TbFrontGroupMap> list);
}
