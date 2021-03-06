package com.pallas.cache.cache;

import org.springframework.data.redis.core.HashOperations;

import java.util.Map;

/**
 * @author: jax
 * @time: 2020/8/31 9:37
 * @desc:
 */
public abstract class AbstractHashCache<T> extends AbstractDataloader<Map<String, T>> {

    public HashOperations<String, String, T> ops() {
        return this.getTemplate().opsForHash();
    }

    @Override
    public Boolean ifExist() {
        return this.getTemplate().hasKey(this.getKey());
    }

    @Override
    public Boolean clear() {
        if (this.getTemplate().hasKey(this.getKey())) {
            return this.getTemplate().delete(this.getKey());
        }
        return true;
    }

    @Override
    public void cacheData(Map<String, T> data) {
        this.ops().putAll(getKey(), data);
    }

    @Override
    public Map<String, T> getCache() {
        return this.ops().entries(getKey());
    }

    public T getData(String key) {
        if (this.ifExist(key)) {
          return this.getCache(key);
        }
        Map<String, T> data = this.getData();
        return data.get(key);
    }

    public Boolean ifExist(String key) {
        return this.ops().hasKey(getKey(), key);
    }

    public void cacheData(String key, T data) {
        this.ops().put(getKey(), key, data);
    }

    public T getCache(String key) {
        return this.ops().get(getKey(), key);
    }

}
