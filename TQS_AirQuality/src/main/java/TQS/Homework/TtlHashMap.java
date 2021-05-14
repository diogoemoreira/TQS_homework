package TQS.Homework;

import static java.util.Collections.*;

import java.util.*;
import java.util.concurrent.TimeUnit;

//from https://gist.github.com/joelittlejohn/5565410
/**
 * A hash map that expires and removes items if they are older than a given
 * time-to-live.
 * <p>
 * The expiry is a passive process, items aren't removed until they are
 * retrieved and deemed to be expired by {@link #get(Object)}. Certain operations
 * do cause the cache to clear all expired values in order to provide an accurate
 * result (keySet, entrySet, values, size) and these operations are therefore O(n).
 */
public class TtlHashMap<K, V> implements Map<K, V> {

    private final HashMap<K, V> store = new HashMap<>();
    private final HashMap<K, Long> timestamps = new HashMap<>();
    private final long ttl;

    public TtlHashMap(TimeUnit ttlUnit, long ttlValue) {
        this.ttl = ttlUnit.toNanos(ttlValue);
    }

    @Override
    public V get(Object key) {
        V value = this.store.get(key);

        if (value != null && expired(key, value)) {
            store.remove(key);
            timestamps.remove(key);
            return null;
        } else {
            return value;
        }
    }

    private boolean expired(Object key, V value) {
        return (System.nanoTime() - timestamps.get(key)) > this.ttl;
    }

    @Override
    public V put(K key, V value) {
        timestamps.put(key, System.nanoTime());
        return store.put(key, value);
    }

    @Override
    public int size() {
        clearExpired();
        return store.size();
    }

    @Override
    public boolean isEmpty() {
        return store.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        if(this.get(key) != null)
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(Object value) {
        return store.containsValue(value);
    }

    @Override
    public V remove(Object key) {
        timestamps.remove(key);
        return store.remove(key);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Map.Entry<? extends K, ? extends V> e : m.entrySet()) {
            this.put(e.getKey(), e.getValue());
        }
    }

    @Override
    public void clear() {
        timestamps.clear();
        store.clear();
    }

    @Override
    public Set<K> keySet() {
        clearExpired();
        return unmodifiableSet(store.keySet());
    }

    @Override
    public Collection<V> values() {
        clearExpired();
        return unmodifiableCollection(store.values());
    }

    @Override
    public Set<java.util.Map.Entry<K, V>> entrySet() {
        clearExpired();
        return unmodifiableSet(store.entrySet());
    }

    private void clearExpired() { Iterator<K> iterator = new ArrayList<>(store.keySet()).iterator(); while (iterator.hasNext()){ this.get(iterator.next()); } }

}
