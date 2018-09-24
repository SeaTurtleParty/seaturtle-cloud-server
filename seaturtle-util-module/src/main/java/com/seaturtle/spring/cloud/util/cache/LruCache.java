package com.seaturtle.spring.cloud.util.cache;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于ConcurrentHashMap和双向链表实现的LRU缓存
 *
 * @author chufei 2018年6月27日
 * @param <K>
 * @param <V>
 */
public class LruCache<K, V> {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        CacheNode<K, V> node = first;
        while (node != null) {
            sb.append(String.format("%s:%s ", node.key, node.value));
            node = node.next;
        }
        return sb.toString();
    }

    private int cacheSize;

    private ConcurrentHashMap<K, CacheNode<K, V>> cache;

    private CacheNode<K, V> first;

    private CacheNode<K, V> last;

    @SuppressWarnings("hiding")
    class CacheNode<K, V> {
        public CacheNode<K, V> pre;
        public CacheNode<K, V> next;
        public K key;
        public V value;

        public CacheNode() {
        }
    }

    public LruCache(int cacheSize) {
        this.cacheSize = cacheSize;
        cache = new ConcurrentHashMap<>(cacheSize);
    }

    /**
     * 获取缓存
     *
     * @param key
     * @return
     */
    public V get(K key) {
        CacheNode<K, V> node = cache.get(key);
        // 如果缓存不存在，返回null
        if (node == null) {
            return null;
        }
        // 将缓存节点放到链表头
        moveToFirst(node);
        return node.value;
    }

    /**
     * 设置缓存
     *
     * @param key
     * @param value
     * @return
     */
    public CacheNode<K, V> put(K key, V value) {
        CacheNode<K, V> node = cache.get(key);
        // 如果之前没有缓存过该key，判断是否超过缓存容量
        if (node == null) {
            if (cache.size() >= cacheSize) {
                // 超过缓存容量，删除表尾缓存
                cache.remove(last.key);
                removeLast();
            }
            node = new CacheNode<>();
        }
        node.key = key;
        node.value = value;
        // 将node放到表头
        moveToFirst(node);
        return cache.put(key, node);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public void remove(K key) {
        CacheNode<K, V> node = cache.get(key);
        if (node != null) {
            if (node.pre != null) {
                node.pre.next = node.next;
            }
            if (node.next != null) {
                node.next.pre = node.pre;
            }
            if (node == first) {
                first = node.next;
            }
            if (node == last) {
                last = node.pre;
            }
        }
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    /**
     * 将node移到链表头，表示该node最近被访问过
     *
     * @param node
     */
    private void moveToFirst(CacheNode<K, V> node) {
        // 如果该node已经在表头
        if (node == first) {
            return;
        }
        if (node.pre != null) {
            node.pre.next = node.next;
        }
        if (node.next != null) {
            node.next.pre = node.pre;
        }
        // 如果该node在表尾，表尾后移一位
        if (node == last) {
            last = last.pre;
        }
        if (first == null || last == null) {
            first = last = node;
            return;
        }
        // 将node移到表头
        node.next = first;
        first.pre = node;
        first = node;
        node.pre = null;
    }

    /**
     * 删除链表尾节点
     */
    private void removeLast() {
        if (last != null) {
            last = last.pre;
            if (last == null) {
                first = null;
            } else {
                last.next = null;
            }
        }
    }

}
