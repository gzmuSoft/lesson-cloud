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
import java.io.Serializable;

/**
 * userconnection
 *
 * @author echo
 * @version 1.0
 * @date 2019-5-7 11:33:57
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
    private java.lang.String userId;

    /**
     * 
     */
    private java.lang.String providerId;

    /**
     * 
     */
    private java.lang.String providerUserId;

    /**
     * 
     */
    private java.lang.Integer rank;

    /**
     * 
     */
    private java.lang.String displayName;

    /**
     * 
     */
    private java.lang.String profileUrl;

    /**
     * 
     */
    private java.lang.String imageUrl;

    /**
     * 
     */
    private java.lang.String accessToken;

    /**
     * 
     */
    private java.lang.String secret;

    /**
     * 
     */
    private java.lang.String refreshToken;

    /**
     * 
     */
    private java.lang.Long expireTime;
}