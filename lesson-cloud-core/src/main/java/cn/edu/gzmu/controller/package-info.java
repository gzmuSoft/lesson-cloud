/**
 * 控制器，用来处理 <a href="https://docs.spring.io/spring-data/rest/docs/current/reference/html/">Spring data rest</> 未提供的服务.
 *
 * <p>
 * 注意的是，在路径相同的时候，他可能会完全覆盖 spring data rest 的配置，例如路径为
 * {@code /{resource}/search/} 时，会覆盖 {@link cn.edu.gzmu.repository.BaseRepository} 中的自定义方法。
 * 所以在 此处 进行书写时，请注意路径不要覆盖以及重复的问题。
 * </p>
 *
 * <b>另一方面，在每次资源处理的时候，如果响应中拥有响应体，请务必将其封装为符合 restful 规范的响应体。</b>
 *
 * <p>
 * 这里的 {@code redis} 同样被 redisson 进行管理，  {@link cn.edu.gzmu.config.RedisConfig} 配置文件路径。
 * </p>
 *
 * @author echo
 * @version 1.0
 * @date 19-4-13 20:19
 */
package cn.edu.gzmu.controller;