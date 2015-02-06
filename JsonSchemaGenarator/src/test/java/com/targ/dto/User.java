package com.targ.dto;

import com.targ.jsonSchemaGenerator.annotation.IgnoreField;
import com.targ.jsonSchemaGenerator.annotation.IsRequired;
import com.targ.jsonSchemaGenerator.annotation.array.ListClass;
import com.targ.jsonSchemaGenerator.annotation.numeric.Maximum;
import com.targ.jsonSchemaGenerator.annotation.numeric.Minimum;
import com.targ.jsonSchemaGenerator.annotation.string.Format;
import com.targ.jsonSchemaGenerator.annotation.string.MaxLength;
import com.targ.jsonSchemaGenerator.annotation.string.MinLength;
import com.targ.jsonSchemaGenerator.annotation.string.Pattern;

import java.util.List;

/**
 * Created by 单兵 on 15-1-30.
 */
public class User {

    @MaxLength(50)@MinLength(10)@Pattern("\\d+")
    private String id;

    private String name;

    @IsRequired(true)
    @Minimum(0)@Maximum(value = 100,exclusiveMaximum = true)
    private Integer age;

    @Minimum(value = 0,exclusiveMinimum = true)
    private Double money;

    @IsRequired
    private Dept dept;

    @ListClass(Post.class)
    private List<Post> posts;

    @Format(Format.FormatType.Email)
    private String email;

    @IgnoreField
    private String notNeed;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNotNeed() {
        return notNeed;
    }

    public void setNotNeed(String notNeed) {
        this.notNeed = notNeed;
    }
}
