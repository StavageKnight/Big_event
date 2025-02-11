package com.example.bigevent.mapper;

import com.example.bigevent.dto.Category;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper
public interface CategoryRepository {
    @Insert("insert into category(category_name, category_alias, create_user, create_time, update_time) " +
            "values (#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user=#{id}")
    List<Category> findCategoryById(Integer id);

    @Select("select * from category where id=#{id}")
    Category findDetailById(Integer id);

    @Update("update category set category_name=#{categoryName},category_alias=#{categoryAlias} ,update_time=#{updateTime} where id=#{id}")
    void update(Category category);
}
