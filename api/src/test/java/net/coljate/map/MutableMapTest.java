package net.coljate.map;

import net.coljate.set.MutableSetTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
/**
 *
 * @author Ollie
 */
public interface MutableMapTest<K, V> extends MapTest<K, V>, MutableSetTest<Entry<K, V>> {

    @Override
    MutableMap<K, V> createTestCollection();

    interface ZeroEntryTests<K, V> extends MutableMapTest<K, V>, MapTest.ZeroEntryTests<K, V>, MutableSetTest.ZeroElementTests<Entry<K, V>> {

        @Test
        default void testPut() {
            final MutableMap<K, V> map = this.createTestCollection();
            final Entry<K, V> entry = this.createTestObject();
            final V previous = map.put(entry.key(), entry.value());
            assertNull(previous);
            assertThat(map.get(entry.key())).isEqualTo(entry.value());
        }

        @Test
        default void testEvict_NullKey() {
            assertNull(this.createTestCollection().evict(null));
        }

    }

    interface OneEntryTests<K, V> extends MutableMapTest<K, V>, MapTest.OneEntryTests<K, V>, MutableSetTest.OneElementTests<Entry<K, V>> {

        @Test
        default void testGetEntry_View() {

            final MutableMap<K, V> map = this.createTestCollection();
            final Entry<K, V> entryWritten = this.getCollectionElement();
            final Entry<K, V> entryView = map.getEntry(entryWritten.key());

            assertThat(entryView.value()).isEqualTo(entryWritten.value());

            final Entry<K, V> newEntry = this.createTestObject();
            assertThat(newEntry.value()).isNotEqualTo(entryWritten.value());

            map.put(entryWritten.key(), newEntry.value());

            assertThat(entryView.value()).isEqualTo(newEntry.value());

        }

    }

}
