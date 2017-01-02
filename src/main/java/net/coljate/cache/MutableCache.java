package net.coljate.cache;

import java.util.function.Function;

import net.coljate.cache.impl.ConcurrentMutableMapBackedCache;
import net.coljate.map.MutableMap;

/**
 * A {@link Cache} that can also be written to.
 *
 * Implementations might not be thread-safe, unless they are a {@link ConcurrentCache}.
 *
 * @author ollie
 * @see ConcurrentCache
 */
public interface MutableCache<K, V> extends Cache<K, V>, MutableMap<K, V> {

    static <K, V> MutableCache<K, V> create(final Function<? super K, ? extends V> valueFunction) {
        return ConcurrentMutableMapBackedCache.create(valueFunction);
    }

}
