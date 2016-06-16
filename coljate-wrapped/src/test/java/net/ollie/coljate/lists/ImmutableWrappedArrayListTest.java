package net.ollie.coljate.lists;

import net.ollie.coljate.list.ImmutableList;
import net.ollie.coljate.list.ImmutableWrappedArrayList;

/**
 *
 * @author Ollie
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class ImmutableWrappedArrayListTest extends ImmutableListTest {

    @Override
    protected ImmutableList<Object> create(final Object... objects) {
        return ImmutableWrappedArrayList.copyOf(objects);
    }

}
