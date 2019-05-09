package cn.edu.gzmu.model.entity;

import cn.edu.gzmu.model.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Where;


import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * userconnection
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-8 16:03:14
 */
@Data
@Cacheable
@Table(name = "userconnection")
@Entity(name = "userconnection")
@Where(clause = "is_enable = 1")
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Cache(region = "userconnection", usage = CacheConcurrencyStrategy.READ_WRITE)
public class Userconnection extends BaseEntity implements Serializable {

    /**
     * 
     */
    @javax.validation.constraints.NotNull(message = "userId 为必填项")
    @Size(max = 255, message = "userId 不能大于 255 位")
    private java.lang.String userId;

    /**
     * 
     */
    @javax.validation.constraints.NotNull(message = "providerId 为必填项")
    @Size(max = 255, message = "providerId 不能大于 255 位")
    private java.lang.String providerId;

    /**
     * 
     */
    @javax.validation.constraints.NotNull(message = "providerUserId 为必填项")
    @Size(max = 255, message = "providerUserId 不能大于 255 位")
    private java.lang.String providerUserId;

    /**
     * 
     */
    @javax.validation.constraints.NotNull(message = "rank 为必填项")
    private java.lang.Integer rank;

    /**
     * 
     */
    @Size(max = 255, message = "displayName 不能大于 255 位")
    private java.lang.String displayName;

    /**
     * 
     */
    @Size(max = 512, message = "profileUrl 不能大于 512 位")
    private java.lang.String profileUrl;

    /**
     * 
     */
    @Size(max = 512, message = "imageUrl 不能大于 512 位")
    private java.lang.String imageUrl;

    /**
     * 
     */
    @javax.validation.constraints.NotNull(message = "accessToken 为必填项")
    @Size(max = 512, message = "accessToken 不能大于 512 位")
    private java.lang.String accessToken;

    /**
     * 
     */
    @Size(max = 512, message = "secret 不能大于 512 位")
    private java.lang.String secret;

    /**
     * 
     */
    @Size(max = 512, message = "refreshToken 不能大于 512 位")
    private java.lang.String refreshToken;

    /**
     * 
     */
    private java.lang.Long expireTime;
}